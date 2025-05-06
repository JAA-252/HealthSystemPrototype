import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import DoctorList from './components/DoctorList';
import PatientList from './components/PatientList';
import ResidenceList from './components/ResidenceList';
import PatientForm from './components/PatientForm';
import AssignPatient from './components/AssignPatient';
import UnassignPatient from './components/UnassignPatient';
import DoctorMappings from './components/DoctorMappings';
import AssignResidence from './components/AssignResidence';
import SearchPatients from './components/SearchPatients';

function App() {
  return (
    <Router>
      <div className="App">
        <h1>NHS Dashboard</h1>
        <nav>
          <Link to="/">Doctors</Link> |{' '}
          <Link to="/patients">Patients</Link> |{' '}
          <Link to="/residences">Residences</Link> |{' '}
          <Link to="/patients/new">+ Add Patient</Link> |{' '}
          <Link to="/assign">Assign Patient</Link>
          <Link to="/unassign">Unassign Patient</Link> |{' '}
          <Link to="/mappings">Doctor Mappings</Link>
          <Link to="/residences/assign">Assign Residence</Link> |{' '}
          <Link to="/patients/search">Search Patients</Link>
        </nav>
        <Routes>
          <Route path="/" element={<DoctorList />} />
          <Route path="/patients" element={<PatientList />} />
          <Route path="/residences" element={<ResidenceList />} />
          <Route path="/patients/new" element={<PatientForm />} />
          <Route path="/assign" element={<AssignPatient />} />
          <Route path="/unassign" element={<UnassignPatient />} />
          <Route path="/mappings" element={<DoctorMappings />} />
          <Route path="/residences/assign" element={<AssignResidence />} />
          <Route path="/patients/search" element={<SearchPatients />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
