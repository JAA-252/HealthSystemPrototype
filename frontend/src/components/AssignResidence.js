import React, { useEffect, useState } from 'react';
import axios from 'axios';

function AssignResidence() {
  const [patients, setPatients] = useState([]);
  const [residences, setResidences] = useState([]);
  const [form, setForm] = useState({ patientId: '', residenceId: '' });

  useEffect(() => {
    axios.get('http://localhost:8080/api/patients')
      .then(res => {
        const unassigned = res.data.filter(p => !p.residenceId);
        setPatients(unassigned);
      });
    axios.get('http://localhost:8080/api/residences').then(res => setResidences(res.data));
  }, []);

  const handleChange = (e) => {
    setForm(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleAssign = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:8080/api/residences/${form.residenceId}/assign/${form.patientId}`);
      alert('Patient assigned to residence!');
    } catch (err) {
      alert(err.response?.data?.message || 'Assignment failed');
    }
  };

  return (
    <form onSubmit={handleAssign}>
      <h2>Assign Patient to Residence</h2>
      <select name="patientId" onChange={handleChange} required>
        <option value="">Select Patient</option>
        {patients.map(p => (
          <option key={p.patientId} value={p.patientId}>
            {p.firstName} {p.lastName}
          </option>
        ))}
      </select>

      <select name="residenceId" onChange={handleChange} required>
        <option value="">Select Residence</option>
        {residences.map(r => (
          <option key={r.residenceId} value={r.residenceId}>
            {r.town}, {r.city} (ID: {r.residenceId})
          </option>
        ))}
      </select>

      <button type="submit">Assign</button>
    </form>
  );
}

export default AssignResidence;
