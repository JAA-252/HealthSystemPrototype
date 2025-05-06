import React, { useEffect, useState } from 'react';
import { getPatients } from '../api/patientService';

function PatientList() {
  const [patients, setPatients] = useState([]);

  useEffect(() => {
    const fetchPatients = async () => {
      try {
        const data = await getPatients();
        setPatients(data);
      } catch (error) {
        console.error('Error fetching patients:', error);
      }
    };

    fetchPatients();
  }, []);

  return (
    <div>
      <h2>Patients</h2>
      <ul>
        {patients.map((patient) => (
          <li key={patient.patientId}>
            {patient.firstName} {patient.lastName} (Age: {patient.age})
          </li>
        ))}
      </ul>
    </div>
  );
}

export default PatientList;
