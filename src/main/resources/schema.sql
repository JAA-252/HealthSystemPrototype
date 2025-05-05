-- Create table for Residence
CREATE TABLE residence (
    residence_id INT PRIMARY KEY AUTO_INCREMENT,
    town VARCHAR(100),
    city VARCHAR(100),
    max_capacity INT DEFAULT 100
);

-- Create table for Doctor
CREATE TABLE doctor (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    age INT,
    birth_date DATE,
    residence_id INT,
    FOREIGN KEY (residence_id) REFERENCES residence(residence_id)
);

-- Create table for Patient
CREATE TABLE patient (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    age INT,
    birth_date DATE,
    doctor_id INT,
    residence_id INT,
    FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id),
    FOREIGN KEY (residence_id) REFERENCES residence(residence_id)
);
