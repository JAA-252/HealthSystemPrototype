import React, { useEffect, useState } from 'react';
import { getDoctors } from '../api/doctorService';

function DoctorList() {
  const [doctors, setDoctors] = useState([]);

  useEffect(() => {
    const fetchDoctors = async () => {
      try {
        const data = await getDoctors();
        setDoctors(data);
      } catch (error) {
        console.error('Error fetching doctors:', error);
      }
    };

    fetchDoctors();
  }, []);

  return (
    <div>
      <h2>Doctors</h2>
      <ul>
        {doctors.map((doctor) => (
          <li key={doctor.doctorId}>
            Dr. {doctor.firstName} {doctor.lastName} (Age: {doctor.age})
          </li>
        ))}
      </ul>
    </div>
  );
}

export default DoctorList;
