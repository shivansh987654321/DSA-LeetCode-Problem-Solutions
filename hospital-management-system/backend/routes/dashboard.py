from flask import Blueprint, jsonify
from flask_jwt_extended import jwt_required, get_jwt
from extensions import db
from models import Patient, Doctor, Appointment, Bill, MedicalRecord

dashboard_bp = Blueprint('dashboard', __name__)


@dashboard_bp.route('/dashboard/stats', methods=['GET'])
@jwt_required()
def get_stats():
    claims = get_jwt()
    role = claims.get('role')

    total_patients = Patient.query.count()
    total_doctors = Doctor.query.count()
    total_appointments = Appointment.query.count()
    scheduled = Appointment.query.filter_by(status='scheduled').count()
    cancelled = Appointment.query.filter_by(status='cancelled').count()
    completed = Appointment.query.filter_by(status='completed').count()

    total_revenue = db.session.query(
        db.func.sum(Bill.amount)
    ).filter_by(payment_status='paid').scalar() or 0

    pending_bills = Bill.query.filter_by(payment_status='pending').count()

    return jsonify({
        'total_patients': total_patients,
        'total_doctors': total_doctors,
        'total_appointments': total_appointments,
        'scheduled_appointments': scheduled,
        'cancelled_appointments': cancelled,
        'completed_appointments': completed,
        'total_revenue': round(float(total_revenue), 2),
        'pending_bills': pending_bills,
        'role': role,
    }), 200


@dashboard_bp.route('/dashboard/recent-appointments', methods=['GET'])
@jwt_required()
def recent_appointments():
    appts = Appointment.query.order_by(Appointment.date.desc()).limit(10).all()
    return jsonify([a.to_dict() for a in appts]), 200


@dashboard_bp.route('/dashboard/appointment-chart', methods=['GET'])
@jwt_required()
def appointment_chart():
    results = db.session.query(
        Appointment.date,
        db.func.count(Appointment.appointment_id)
    ).group_by(Appointment.date).order_by(Appointment.date.desc()).limit(7).all()

    return jsonify([{'date': r[0], 'count': r[1]} for r in reversed(results)]), 200
