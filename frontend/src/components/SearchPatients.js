import React, { useState, useEffect } from 'react';
import axios from 'axios';

function SearchPatients() {
  const [query, setQuery] = useState('');
  const [doctorId, setDoctorId] = useState('');
  const [residenceId, setResidenceId] = useState('');
  const [patients, setPatients] = useState([]);
  const [doctors, setDoctors] = useState([]);
  const [residences, setResidences] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/doctors').then(res => setDoctors(res.data));
    axios.get('http://localhost:8080/api/residences').then(res => setResidences(res.data));
    axios.get('http://localhost:8080/api/patients').then(res => setPatients(res.data));
  }, []);

  const filtered = patients.filter(p => {
    return (
      (!query || `${p.firstName} ${p.lastName}`.toLowerCase().includes(query.toLowerCase())) &&
      (!doctorId || p.doctorId === parseInt(doctorId)) &&
      (!residenceId || p.residenceId === parseInt(residenceId))
    );
  });

  return (
    <div>
      <h2>Search Patients</h2>
      <input
        placeholder="Search by name..."
        value={query}
        onChange={e => setQuery(e.target.value)}
      />
      <select onChange={e => setDoctorId(e.target.value)} value={doctorId}>
        <option value="">All Doctors</option>
        {doctors.map(d => (
          <option key={d.doctorId} value={d.doctorId}>
            Dr. {d.firstName} {d.lastName}
          </option>
        ))}
      </select>

      <select onChange={e => setResidenceId(e.target.value)} value={residenceId}>
        <option value="">All Residences</option>
        {residences.map(r => (
          <option key={r.residenceId} value={r.residenceId}>
            {r.town}, {r.city}
          </option>
        ))}
      </select>

      <ul>
        {filtered.map(p => (
          <li key={p.patientId}>
            {p.firstName} {p.lastName} (Doctor ID: {p.doctorId || 'None'}, Residence ID: {p.residenceId || 'None'})
          </li>
        ))}
      </ul>
    </div>
  );
}

export default SearchPatients;
