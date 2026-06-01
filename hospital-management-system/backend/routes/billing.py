from flask import Blueprint, request, jsonify
from flask_jwt_extended import jwt_required, get_jwt, get_jwt_identity
from datetime import date
from extensions import db
from models import Bill, Patient, Appointment, UserAccount

billing_bp = Blueprint('billing', __name__)


@billing_bp.route('/bills', methods=['GET'])
@jwt_required()
def get_bills():
    claims = get_jwt()
    role = claims.get('role')
    user_id = int(get_jwt_identity())

    query = Bill.query

    if role == 'patient':
        user = UserAccount.query.get(user_id)
        if user and user.patient_id:
            query = query.filter_by(patient_id=user.patient_id)
        else:
            return jsonify([]), 200

    patient_id = request.args.get('patient_id')
    status = request.args.get('payment_status')

    if patient_id:
        query = query.filter_by(patient_id=int(patient_id))
    if status:
        query = query.filter_by(payment_status=status)

    bills = query.order_by(Bill.date.desc()).all()
    return jsonify([b.to_dict() for b in bills]), 200


@billing_bp.route('/bills/<int:bill_id>', methods=['GET'])
@jwt_required()
def get_bill(bill_id):
    bill = Bill.query.get_or_404(bill_id)
    return jsonify(bill.to_dict()), 200


@billing_bp.route('/bills', methods=['POST'])
@jwt_required()
def create_bill():
    claims = get_jwt()
    if claims.get('role') not in ('admin', 'receptionist'):
        return jsonify({'error': 'Access denied'}), 403

    data = request.get_json()
    if not data or not data.get('patient_id') or not data.get('amount'):
        return jsonify({'error': 'patient_id and amount are required'}), 400

    if not Patient.query.get(data['patient_id']):
        return jsonify({'error': 'Patient not found'}), 404

    bill = Bill(
        patient_id=data['patient_id'],
        appointment_id=data.get('appointment_id'),
        amount=float(data['amount']),
        payment_status=data.get('payment_status', 'pending'),
        date=data.get('date', str(date.today())),
    )
    db.session.add(bill)
    db.session.commit()
    return jsonify({'message': 'Bill generated', 'bill': bill.to_dict()}), 201


@billing_bp.route('/bills/<int:bill_id>', methods=['PUT'])
@jwt_required()
def update_bill(bill_id):
    claims = get_jwt()
    if claims.get('role') not in ('admin', 'receptionist'):
        return jsonify({'error': 'Access denied'}), 403

    bill = Bill.query.get_or_404(bill_id)
    data = request.get_json()

    for field in ('amount', 'payment_status', 'date'):
        if field in data:
            setattr(bill, field, data[field])

    db.session.commit()
    return jsonify({'message': 'Bill updated', 'bill': bill.to_dict()}), 200


@billing_bp.route('/bills/<int:bill_id>/pay', methods=['POST'])
@jwt_required()
def pay_bill(bill_id):
    bill = Bill.query.get_or_404(bill_id)
    bill.payment_status = 'paid'
    db.session.commit()
    return jsonify({'message': 'Payment recorded', 'bill': bill.to_dict()}), 200


@billing_bp.route('/bills/<int:bill_id>', methods=['DELETE'])
@jwt_required()
def delete_bill(bill_id):
    claims = get_jwt()
    if claims.get('role') != 'admin':
        return jsonify({'error': 'Admin access required'}), 403

    bill = Bill.query.get_or_404(bill_id)
    db.session.delete(bill)
    db.session.commit()
    return jsonify({'message': 'Bill deleted'}), 200
