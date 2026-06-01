from datetime import date
from extensions import db
import bcrypt


class Department(db.Model):
    __tablename__ = 'department'
    department_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    dept_name = db.Column(db.String(100), nullable=False, unique=True)
    location = db.Column(db.String(100))
    doctors = db.relationship('Doctor', backref='department', lazy=True)

    def to_dict(self):
        return {
            'department_id': self.department_id,
            'dept_name': self.dept_name,
            'location': self.location,
        }


class Doctor(db.Model):
    __tablename__ = 'doctor'
    doctor_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    name = db.Column(db.String(100), nullable=False)
    specialization = db.Column(db.String(100))
    phone = db.Column(db.String(20))
    department_id = db.Column(db.Integer, db.ForeignKey('department.department_id'))
    appointments = db.relationship('Appointment', backref='doctor', lazy=True)
    medical_records = db.relationship('MedicalRecord', backref='doctor', lazy=True)

    def to_dict(self):
        return {
            'doctor_id': self.doctor_id,
            'name': self.name,
            'specialization': self.specialization,
            'phone': self.phone,
            'department_id': self.department_id,
            'department_name': self.department.dept_name if self.department else None,
        }


class Patient(db.Model):
    __tablename__ = 'patient'
    patient_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    name = db.Column(db.String(100), nullable=False)
    gender = db.Column(db.String(10))
    dob = db.Column(db.String(20))
    phone = db.Column(db.String(20))
    address = db.Column(db.String(200))
    blood_group = db.Column(db.String(5))
    appointments = db.relationship('Appointment', backref='patient', lazy=True)
    medical_records = db.relationship('MedicalRecord', backref='patient', lazy=True)
    bills = db.relationship('Bill', backref='patient', lazy=True)
    user_account = db.relationship('UserAccount', backref='patient', uselist=False)

    def to_dict(self):
        return {
            'patient_id': self.patient_id,
            'name': self.name,
            'gender': self.gender,
            'dob': self.dob,
            'phone': self.phone,
            'address': self.address,
            'blood_group': self.blood_group,
        }


class Appointment(db.Model):
    __tablename__ = 'appointment'
    appointment_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    patient_id = db.Column(db.Integer, db.ForeignKey('patient.patient_id'), nullable=False)
    doctor_id = db.Column(db.Integer, db.ForeignKey('doctor.doctor_id'), nullable=False)
    date = db.Column(db.String(20), nullable=False)
    time = db.Column(db.String(10), nullable=False)
    status = db.Column(db.String(20), default='scheduled')
    bills = db.relationship('Bill', backref='appointment', lazy=True)

    def to_dict(self):
        return {
            'appointment_id': self.appointment_id,
            'patient_id': self.patient_id,
            'patient_name': self.patient.name if self.patient else None,
            'doctor_id': self.doctor_id,
            'doctor_name': self.doctor.name if self.doctor else None,
            'date': self.date,
            'time': self.time,
            'status': self.status,
        }


class MedicalRecord(db.Model):
    __tablename__ = 'medical_record'
    record_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    patient_id = db.Column(db.Integer, db.ForeignKey('patient.patient_id'), nullable=False)
    doctor_id = db.Column(db.Integer, db.ForeignKey('doctor.doctor_id'), nullable=False)
    diagnosis = db.Column(db.Text)
    prescription = db.Column(db.Text)
    date = db.Column(db.String(20), default=str(date.today()))

    def to_dict(self):
        return {
            'record_id': self.record_id,
            'patient_id': self.patient_id,
            'patient_name': self.patient.name if self.patient else None,
            'doctor_id': self.doctor_id,
            'doctor_name': self.doctor.name if self.doctor else None,
            'diagnosis': self.diagnosis,
            'prescription': self.prescription,
            'date': self.date,
        }


class Bill(db.Model):
    __tablename__ = 'bill'
    bill_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    patient_id = db.Column(db.Integer, db.ForeignKey('patient.patient_id'), nullable=False)
    appointment_id = db.Column(db.Integer, db.ForeignKey('appointment.appointment_id'))
    amount = db.Column(db.Float, nullable=False)
    payment_status = db.Column(db.String(20), default='pending')
    date = db.Column(db.String(20), default=str(date.today()))

    def to_dict(self):
        return {
            'bill_id': self.bill_id,
            'patient_id': self.patient_id,
            'patient_name': self.patient.name if self.patient else None,
            'appointment_id': self.appointment_id,
            'amount': self.amount,
            'payment_status': self.payment_status,
            'date': self.date,
        }


class UserAccount(db.Model):
    __tablename__ = 'user_account'
    user_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    username = db.Column(db.String(80), unique=True, nullable=False)
    password_hash = db.Column(db.String(200), nullable=False)
    role = db.Column(db.String(20), nullable=False)
    patient_id = db.Column(db.Integer, db.ForeignKey('patient.patient_id'), nullable=True)
    email = db.Column(db.String(120))

    def set_password(self, password):
        self.password_hash = bcrypt.hashpw(
            password.encode('utf-8'), bcrypt.gensalt()
        ).decode('utf-8')

    def check_password(self, password):
        return bcrypt.checkpw(
            password.encode('utf-8'),
            self.password_hash.encode('utf-8')
        )

    def to_dict(self):
        return {
            'user_id': self.user_id,
            'username': self.username,
            'role': self.role,
            'patient_id': self.patient_id,
            'email': self.email,
        }
