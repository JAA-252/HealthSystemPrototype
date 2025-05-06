package com.codecamp.NHS;

import com.codecamp.NHS.models.Doctor;
import com.codecamp.NHS.models.Patient;
import com.codecamp.NHS.models.Residence;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class NhsApplicationTests {

	Residence r1 = new Residence(1,"Ade","Lon",100);
	Doctor d1 = new Doctor(1,"Def","Jam",1, LocalDate.now());
	Patient p1 = new Patient(1,r1,"Def","Maj",LocalDate.now());

	@Test
	void contextLoads() {
	}

}
