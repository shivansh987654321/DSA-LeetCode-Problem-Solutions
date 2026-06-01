from flask import Blueprint, request, jsonify
from flask_jwt_extended import jwt_required, get_jwt
from extensions import db
from models import Doctor, Department

doctors_bp = Blueprint('doctors', __name__)


@doctors_bp.route('/doctors', methods=['GET'])
@jwt_required()
def get_doctors():
    search = request.args.get('search', '')
    dept_id = request.args.get('department_id')
    query = Doctor.query
    if search:
        query = query.filter(
            Doctor.name.ilike(f'%{search}%') |
            Doctor.specialization.ilike(f'%{search}%')
        )
    if dept_id:
        query = query.filter_by(department_id=int(dept_id))
    return jsonify([d.to_dict() for d in query.all()]), 200


@doctors_bp.route('/doctors/<int:doctor_id>', methods=['GET'])
@jwt_required()
def get_doctor(doctor_id):
    doctor = Doctor.query.get_or_404(doctor_id)
    return jsonify(doctor.to_dict()), 200


@doctors_bp.route('/doctors', methods=['POST'])
@jwt_required()
def create_doctor():
    claims = get_jwt()
    if claims.get('role') != 'admin':
        return jsonify({'error': 'Admin access required'}), 403

    data = request.get_json()
    if not data or not data.get('name'):
        return jsonify({'error': 'Doctor name is required'}), 400

    doctor = Doctor(
        name=data['name'],
        specialization=data.get('specialization'),
        phone=data.get('phone'),
        department_id=data.get('department_id'),
    )
    db.session.add(doctor)
    db.session.commit()
    return jsonify({'message': 'Doctor added', 'doctor': doctor.to_dict()}), 201


@doctors_bp.route('/doctors/<int:doctor_id>', methods=['PUT'])
@jwt_required()
def update_doctor(doctor_id):
    claims = get_jwt()
    if claims.get('role') != 'admin':
        return jsonify({'error': 'Admin access required'}), 403

    doctor = Doctor.query.get_or_404(doctor_id)
    data = request.get_json()

    for field in ('name', 'specialization', 'phone', 'department_id'):
        if field in data:
            setattr(doctor, field, data[field])

    db.session.commit()
    return jsonify({'message': 'Doctor updated', 'doctor': doctor.to_dict()}), 200


@doctors_bp.route('/doctors/<int:doctor_id>', methods=['DELETE'])
@jwt_required()
def delete_doctor(doctor_id):
    claims = get_jwt()
    if claims.get('role') != 'admin':
        return jsonify({'error': 'Admin access required'}), 403

    doctor = Doctor.query.get_or_404(doctor_id)
    db.session.delete(doctor)
    db.session.commit()
    return jsonify({'message': 'Doctor deleted'}), 200


@doctors_bp.route('/departments', methods=['GET'])
@jwt_required()
def get_departments():
    return jsonify([d.to_dict() for d in Department.query.all()]), 200


@doctors_bp.route('/departments', methods=['POST'])
@jwt_required()
def create_department():
    claims = get_jwt()
    if claims.get('role') != 'admin':
        return jsonify({'error': 'Admin access required'}), 403

    data = request.get_json()
    if not data or not data.get('dept_name'):
        return jsonify({'error': 'Department name required'}), 400

    dept = Department(dept_name=data['dept_name'], location=data.get('location'))
    db.session.add(dept)
    db.session.commit()
    return jsonify({'message': 'Department created', 'department': dept.to_dict()}), 201


@doctors_bp.route('/departments/<int:dept_id>', methods=['DELETE'])
@jwt_required()
def delete_department(dept_id):
    claims = get_jwt()
    if claims.get('role') != 'admin':
        return jsonify({'error': 'Admin access required'}), 403

    dept = Department.query.get_or_404(dept_id)
    db.session.delete(dept)
    db.session.commit()
    return jsonify({'message': 'Department deleted'}), 200
