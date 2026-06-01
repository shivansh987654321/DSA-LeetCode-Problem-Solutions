from flask import Blueprint, request, jsonify
from flask_jwt_extended import jwt_required, get_jwt
from extensions import db
from models import Patient

patients_bp = Blueprint('patients', __name__)

ALLOWED_ROLES = ('admin', 'doctor', 'receptionist')


@patients_bp.route('/patients', methods=['GET'])
@jwt_required()
def get_patients():
    claims = get_jwt()
    if claims.get('role') not in ALLOWED_ROLES:
        return jsonify({'error': 'Access denied'}), 403

    search = request.args.get('search', '')
    query = Patient.query
    if search:
        query = query.filter(
            Patient.name.ilike(f'%{search}%') |
            Patient.phone.ilike(f'%{search}%')
        )
    patients = query.all()
    return jsonify([p.to_dict() for p in patients]), 200


@patients_bp.route('/patients/<int:patient_id>', methods=['GET'])
@jwt_required()
def get_patient(patient_id):
    patient = Patient.query.get_or_404(patient_id)
    return jsonify(patient.to_dict()), 200


@patients_bp.route('/patients', methods=['POST'])
@jwt_required()
def create_patient():
    claims = get_jwt()
    if claims.get('role') not in ('admin', 'receptionist'):
        return jsonify({'error': 'Access denied'}), 403

    data = request.get_json()
    if not data or not data.get('name'):
        return jsonify({'error': 'Patient name is required'}), 400

    patient = Patient(
        name=data['name'],
        gender=data.get('gender'),
        dob=data.get('dob'),
        phone=data.get('phone'),
        address=data.get('address'),
        blood_group=data.get('blood_group'),
    )
    db.session.add(patient)
    db.session.commit()
    return jsonify({'message': 'Patient registered', 'patient': patient.to_dict()}), 201


@patients_bp.route('/patients/<int:patient_id>', methods=['PUT'])
@jwt_required()
def update_patient(patient_id):
    claims = get_jwt()
    if claims.get('role') not in ('admin', 'receptionist'):
        return jsonify({'error': 'Access denied'}), 403

    patient = Patient.query.get_or_404(patient_id)
    data = request.get_json()

    for field in ('name', 'gender', 'dob', 'phone', 'address', 'blood_group'):
        if field in data:
            setattr(patient, field, data[field])

    db.session.commit()
    return jsonify({'message': 'Patient updated', 'patient': patient.to_dict()}), 200


@patients_bp.route('/patients/<int:patient_id>', methods=['DELETE'])
@jwt_required()
def delete_patient(patient_id):
    claims = get_jwt()
    if claims.get('role') != 'admin':
        return jsonify({'error': 'Admin access required'}), 403

    patient = Patient.query.get_or_404(patient_id)
    db.session.delete(patient)
    db.session.commit()
    return jsonify({'message': 'Patient deleted'}), 200
