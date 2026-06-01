from flask import Blueprint, request, jsonify
from flask_jwt_extended import jwt_required, get_jwt, get_jwt_identity
from extensions import db
from models import Appointment, Doctor, Patient, UserAccount

appointments_bp = Blueprint('appointments', __name__)


def _slot_available(doctor_id, date, time, exclude_id=None):
    query = Appointment.query.filter_by(
        doctor_id=doctor_id, date=date, time=time, status='scheduled'
    )
    if exclude_id:
        query = query.filter(Appointment.appointment_id != exclude_id)
    return query.first() is None


@appointments_bp.route('/appointments', methods=['GET'])
@jwt_required()
def get_appointments():
    claims = get_jwt()
    role = claims.get('role')
    user_id = int(get_jwt_identity())

    query = Appointment.query

    if role == 'patient':
        user = UserAccount.query.get(user_id)
        if user and user.patient_id:
            query = query.filter_by(patient_id=user.patient_id)
        else:
            return jsonify([]), 200
    elif role == 'doctor':
        doctor = Doctor.query.filter_by(name=claims.get('username')).first()
        if doctor:
            query = query.filter_by(doctor_id=doctor.doctor_id)

    patient_id = request.args.get('patient_id')
    doctor_id = request.args.get('doctor_id')
    status = request.args.get('status')
    date = request.args.get('date')

    if patient_id:
        query = query.filter_by(patient_id=int(patient_id))
    if doctor_id:
        query = query.filter_by(doctor_id=int(doctor_id))
    if status:
        query = query.filter_by(status=status)
    if date:
        query = query.filter_by(date=date)

    appointments = query.order_by(Appointment.date.desc(), Appointment.time).all()
    return jsonify([a.to_dict() for a in appointments]), 200


@appointments_bp.route('/appointments/<int:appt_id>', methods=['GET'])
@jwt_required()
def get_appointment(appt_id):
    appt = Appointment.query.get_or_404(appt_id)
    return jsonify(appt.to_dict()), 200


@appointments_bp.route('/appointments', methods=['POST'])
@jwt_required()
def book_appointment():
    claims = get_jwt()
    if claims.get('role') not in ('admin', 'receptionist', 'patient'):
        return jsonify({'error': 'Access denied'}), 403

    data = request.get_json()
    required = ['patient_id', 'doctor_id', 'date', 'time']
    if not data or not all(k in data for k in required):
        return jsonify({'error': 'patient_id, doctor_id, date, time are required'}), 400

    if not Doctor.query.get(data['doctor_id']):
        return jsonify({'error': 'Doctor not found'}), 404
    if not Patient.query.get(data['patient_id']):
        return jsonify({'error': 'Patient not found'}), 404

    if not _slot_available(data['doctor_id'], data['date'], data['time']):
        return jsonify({'error': 'Slot not available. Please choose another time.'}), 409

    appt = Appointment(
        patient_id=data['patient_id'],
        doctor_id=data['doctor_id'],
        date=data['date'],
        time=data['time'],
        status='scheduled',
    )
    db.session.add(appt)
    db.session.commit()
    return jsonify({'message': 'Appointment booked', 'appointment': appt.to_dict()}), 201


@appointments_bp.route('/appointments/<int:appt_id>', methods=['PUT'])
@jwt_required()
def update_appointment(appt_id):
    claims = get_jwt()
    if claims.get('role') not in ('admin', 'receptionist'):
        return jsonify({'error': 'Access denied'}), 403

    appt = Appointment.query.get_or_404(appt_id)
    data = request.get_json()

    new_date = data.get('date', appt.date)
    new_time = data.get('time', appt.time)
    new_doctor = data.get('doctor_id', appt.doctor_id)

    if (new_date != appt.date or new_time != appt.time or new_doctor != appt.doctor_id):
        if not _slot_available(new_doctor, new_date, new_time, exclude_id=appt_id):
            return jsonify({'error': 'Slot not available. Please choose another time.'}), 409

    appt.date = new_date
    appt.time = new_time
    appt.doctor_id = new_doctor
    if 'status' in data:
        appt.status = data['status']

    db.session.commit()
    return jsonify({'message': 'Appointment updated', 'appointment': appt.to_dict()}), 200


@appointments_bp.route('/appointments/<int:appt_id>/cancel', methods=['POST'])
@jwt_required()
def cancel_appointment(appt_id):
    claims = get_jwt()
    if claims.get('role') not in ('admin', 'receptionist', 'patient'):
        return jsonify({'error': 'Access denied'}), 403

    appt = Appointment.query.get_or_404(appt_id)
    appt.status = 'cancelled'
    db.session.commit()
    return jsonify({'message': 'Appointment cancelled', 'appointment': appt.to_dict()}), 200
