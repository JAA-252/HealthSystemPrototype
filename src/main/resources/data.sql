-- Insert sample Residences
INSERT INTO residence (residence_id, town, city, max_capacity) VALUES
(1, 'Greenwich', 'London', 100),
(2, 'Cambridge Town', 'Cambridge', 80);

-- Insert sample Doctors
INSERT INTO doctor (doctor_id, first_name, last_name, age, birth_date, residence_id) VALUES
(1, 'Alice', 'Smith', 45, '1980-04-15', 1),
(2, 'Bob', 'Johnson', 50, '1975-09-20', 2);

-- Insert sample Patients
INSERT INTO patient (patient_id, first_name, last_name, age, birth_date, doctor_id, residence_id) VALUES
(1, 'Emily', 'Clark', 25, '2000-03-10', 1, 1),
(2, 'James', 'Anderson', 30, '1995-11-05', 1, 1),
(3, 'Laura', 'Brown', 40, '1984-01-22', 2, 2);
