from flask import Blueprint, request, jsonify
from flask_jwt_extended import jwt_required, get_jwt, get_jwt_identity
from datetime import date
from extensions import db
from models import MedicalRecord, Patient, Doctor, UserAccount

records_bp = Blueprint('records', __name__)


@records_bp.route('/records', methods=['GET'])
@jwt_required()
def get_records():
    claims = get_jwt()
    role = claims.get('role')
    user_id = int(get_jwt_identity())

    query = MedicalRecord.query

    if role == 'patient':
        user = UserAccount.query.get(user_id)
        if user and user.patient_id:
            query = query.filter_by(patient_id=user.patient_id)
        else:
            return jsonify([]), 200

    patient_id = request.args.get('patient_id')
    if patient_id:
        query = query.filter_by(patient_id=int(patient_id))

    records = query.order_by(MedicalRecord.date.desc()).all()
    return jsonify([r.to_dict() for r in records]), 200


@records_bp.route('/records/<int:patient_id>', methods=['GET'])
@jwt_required()
def get_patient_records(patient_id):
    records = MedicalRecord.query.filter_by(patient_id=patient_id).order_by(
        MedicalRecord.date.desc()
    ).all()
    return jsonify([r.to_dict() for r in records]), 200


@records_bp.route('/records', methods=['POST'])
@jwt_required()
def create_record():
    claims = get_jwt()
    if claims.get('role') not in ('admin', 'doctor'):
        return jsonify({'error': 'Access denied'}), 403

    data = request.get_json()
    required = ['patient_id', 'doctor_id']
    if not data or not all(k in data for k in required):
        return jsonify({'error': 'patient_id and doctor_id are required'}), 400

    if not Patient.query.get(data['patient_id']):
        return jsonify({'error': 'Patient not found'}), 404
    if not Doctor.query.get(data['doctor_id']):
        return jsonify({'error': 'Doctor not found'}), 404

    record = MedicalRecord(
        patient_id=data['patient_id'],
        doctor_id=data['doctor_id'],
        diagnosis=data.get('diagnosis', ''),
        prescription=data.get('prescription', ''),
        date=data.get('date', str(date.today())),
    )
    db.session.add(record)
    db.session.commit()
    return jsonify({'message': 'Medical record created', 'record': record.to_dict()}), 201


@records_bp.route('/records/<int:record_id>', methods=['PUT'])
@jwt_required()
def update_record(record_id):
    claims = get_jwt()
    if claims.get('role') not in ('admin', 'doctor'):
        return jsonify({'error': 'Access denied'}), 403

    record = MedicalRecord.query.get_or_404(record_id)
    data = request.get_json()

    for field in ('diagnosis', 'prescription', 'date'):
        if field in data:
            setattr(record, field, data[field])

    db.session.commit()
    return jsonify({'message': 'Record updated', 'record': record.to_dict()}), 200


@records_bp.route('/records/<int:record_id>', methods=['DELETE'])
@jwt_required()
def delete_record(record_id):
    claims = get_jwt()
    if claims.get('role') != 'admin':
        return jsonify({'error': 'Admin access required'}), 403

    record = MedicalRecord.query.get_or_404(record_id)
    db.session.delete(record)
    db.session.commit()
    return jsonify({'message': 'Record deleted'}), 200
