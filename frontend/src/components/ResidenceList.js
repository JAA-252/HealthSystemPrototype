import React, { useEffect, useState } from 'react';
import { getResidences } from '../api/residenceService';

function ResidenceList() {
  const [residences, setResidences] = useState([]);

  useEffect(() => {
    const fetchResidences = async () => {
      try {
        const data = await getResidences();
        setResidences(data);
      } catch (error) {
        console.error('Error fetching residences:', error);
      }
    };

    fetchResidences();
  }, []);

  return (
    <div>
      <h2>Residences</h2>
      <ul>
        {residences.map((residence) => (
          <li key={residence.residenceId}>
            {residence.town}, {residence.city} (Max Capacity: {residence.maxCapacity})
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ResidenceList;
