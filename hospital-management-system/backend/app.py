from flask import Flask, send_from_directory
from flask_cors import CORS
import os

from config import Config
from extensions import db, jwt
from models import Department, Doctor, Patient, UserAccount

from routes.auth import auth_bp
from routes.patients import patients_bp
from routes.doctors import doctors_bp
from routes.appointments import appointments_bp
from routes.medical_records import records_bp
from routes.billing import billing_bp
from routes.dashboard import dashboard_bp

FRONTEND_DIR = os.path.join(os.path.dirname(__file__), '..', 'frontend')


def create_app():
    app = Flask(__name__, static_folder=os.path.join(FRONTEND_DIR, 'static'))
    app.config.from_object(Config)

    CORS(app, supports_credentials=True)
    db.init_app(app)
    jwt.init_app(app)

    app.register_blueprint(auth_bp, url_prefix='/api')
    app.register_blueprint(patients_bp, url_prefix='/api')
    app.register_blueprint(doctors_bp, url_prefix='/api')
    app.register_blueprint(appointments_bp, url_prefix='/api')
    app.register_blueprint(records_bp, url_prefix='/api')
    app.register_blueprint(billing_bp, url_prefix='/api')
    app.register_blueprint(dashboard_bp, url_prefix='/api')

    @app.route('/')
    def index():
        return send_from_directory(FRONTEND_DIR, 'index.html')

    @app.route('/<path:filename>')
    def serve_frontend(filename):
        return send_from_directory(FRONTEND_DIR, filename)

    with app.app_context():
        db.create_all()
        _seed_data()

    return app


def _seed_data():
    if UserAccount.query.first():
        return

    cardiology = Department(dept_name='Cardiology', location='Block A')
    neurology = Department(dept_name='Neurology', location='Block B')
    orthopedics = Department(dept_name='Orthopedics', location='Block C')
    general = Department(dept_name='General Medicine', location='Block D')
    db.session.add_all([cardiology, neurology, orthopedics, general])
    db.session.flush()

    d1 = Doctor(name='Dr. Priya Sharma', specialization='Cardiologist',
                phone='9876543210', department_id=cardiology.department_id)
    d2 = Doctor(name='Dr. Rahul Verma', specialization='Neurologist',
                phone='9876543211', department_id=neurology.department_id)
    d3 = Doctor(name='Dr. Anita Singh', specialization='Orthopedic Surgeon',
                phone='9876543212', department_id=orthopedics.department_id)
    d4 = Doctor(name='Dr. Suresh Patel', specialization='General Physician',
                phone='9876543213', department_id=general.department_id)
    db.session.add_all([d1, d2, d3, d4])

    p1 = Patient(name='Amit Kumar', gender='Male', dob='1990-05-15',
                 phone='9001234567', address='123 Main St, Delhi', blood_group='O+')
    p2 = Patient(name='Sunita Rao', gender='Female', dob='1985-08-22',
                 phone='9001234568', address='456 Park Ave, Mumbai', blood_group='A+')
    p3 = Patient(name='Raj Malhotra', gender='Male', dob='1978-11-10',
                 phone='9001234569', address='789 Lake Rd, Chennai', blood_group='B-')
    db.session.add_all([p1, p2, p3])
    db.session.flush()

    admin = UserAccount(username='admin', role='admin', email='admin@hospital.com')
    admin.set_password('admin123')

    receptionist = UserAccount(username='receptionist', role='receptionist',
                               email='reception@hospital.com')
    receptionist.set_password('recep123')

    doctor_user = UserAccount(username='doctor', role='doctor', email='doctor@hospital.com')
    doctor_user.set_password('doctor123')

    patient_user = UserAccount(username='patient', role='patient',
                               email='patient@hospital.com', patient_id=p1.patient_id)
    patient_user.set_password('patient123')

    db.session.add_all([admin, receptionist, doctor_user, patient_user])
    db.session.commit()


if __name__ == '__main__':
    app = create_app()
    app.run(debug=True, host='0.0.0.0', port=5000)
