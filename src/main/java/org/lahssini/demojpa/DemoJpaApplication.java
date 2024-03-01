package org.lahssini.demojpa;
import org.lahssini.demojpa.entities.Patient;
import org.lahssini.demojpa.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@SpringBootApplication
public class DemoJpaApplication implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoJpaApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		//*************Ajouter des patients********************
		//patientRepository.save(new Patient(null,"lahssini", LocalDate.of(1994, 1, 21),true,2));
		//patientRepository.save(new Patient(null,"fathi", LocalDate.of(1999, 1, 21),false,8));
		//*************Consulter tous les patients********************
		List<Patient> touspatients=patientRepository.findAll();
		touspatients.forEach(p->{
			System.out.println(p.getNom());
			System.out.println(p.getDateNaissanec());
			System.out.println(p.getMalade());
			System.out.println(p.getScore());
			System.out.println("********************************");

		});
		//*************Consulter un patient********************
		Optional<Patient> patientOptional = patientRepository.findById(2L);
		//*************Chercher des patients********************
		List<Patient> patientList=patientRepository.chercher("h",6);
		patientList.forEach(p->{
			System.out.println(p.getNom());
			System.out.println(p.getDateNaissanec());
			System.out.println(p.getMalade());
			System.out.println(p.getScore());
			System.out.println("********************************");
		});
		//******************** Mettre à jour un patient  *********************
		Optional<Patient> patientmodifier = patientRepository.findById(3L);
		if (patientmodifier.isPresent()) {
			Patient patient = patientmodifier.get();
			patient.setNom("safiri");
			patient.setDateNaissanec(LocalDate.now());
			patient.setMalade((true));
			patient.setScore(9);
			patientRepository.save(patient);
		}
		//******************************** supprimer un patient******************************
		patientRepository.deleteById(5L);
		patientRepository.deleteById(6L);

























	}
}
