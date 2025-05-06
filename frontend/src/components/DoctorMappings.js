import React, { useEffect, useState } from 'react';
import axios from 'axios';

function DoctorMappings() {
  const [doctors, setDoctors] = useState([]);

  useEffect(() => {
    const fetchMappings = async () => {
      const doctorRes = await axios.get('http://localhost:8080/api/doctors');
      const doctorsWithPatients = await Promise.all(
        doctorRes.data.map(async (doctor) => {
          const patientsRes = await axios.get(`http://localhost:8080/api/doctors/${doctor.doctorId}/patients`);
          return { ...doctor, patients: patientsRes.data };
        })
      );
      setDoctors(doctorsWithPatients);
    };

    fetchMappings();
  }, []);

  return (
    <div>
      <h2>Doctor-Patient Assignments</h2>
      {doctors.map((doctor) => (
        <div key={doctor.doctorId} style={{ marginBottom: '1em' }}>
          <strong>Dr. {doctor.firstName} {doctor.lastName}</strong>
          {doctor.patients.length > 0 ? (
            <ul>
              {doctor.patients.map(p => (
                <li key={p.patientId}>{p.firstName} {p.lastName} (Age: {p.age})</li>
              ))}
            </ul>
          ) : (
            <p><em>No assigned patients</em></p>
          )}
        </div>
      ))}
    </div>
  );
}

export default DoctorMappings;
