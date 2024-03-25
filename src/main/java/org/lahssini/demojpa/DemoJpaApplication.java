package org.lahssini.demojpa;
import org.lahssini.demojpa.entities.*;
import org.lahssini.demojpa.repository.ConsultationRepository;
import org.lahssini.demojpa.repository.MedecinRepository;
import org.lahssini.demojpa.repository.PatientRepository;
import org.lahssini.demojpa.repository.RendezVousRepository;
import org.lahssini.demojpa.service.IHospitalService;
import org.lahssini.demojpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
			System.out.println(p.getDateNaissance());
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
			System.out.println(p.getDateNaissance());
			System.out.println(p.getMalade());
			System.out.println(p.getScore());
			System.out.println("********************************");
		});
		//******************** Mettre à jour un patient  *********************
		Optional<Patient> patientmodifier = patientRepository.findById(3L);
		if (patientmodifier.isPresent()) {
			Patient patient = patientmodifier.get();
			patient.setNom("safiri");
			patient.setDateNaissance(new Date());
			patient.setMalade((true));
			patient.setScore(9);
			patientRepository.save(patient);
		}
		//******************************** supprimer un patient******************************
		patientRepository.deleteById(5L);
		patientRepository.deleteById(6L);

	}
	//************************************************* AJOUT DES ENTITES DE LA QUESTION 8 ********
	@Bean
	CommandLineRunner start(IHospitalService hospitalService,
							PatientRepository patientRepository,
							MedecinRepository medecinRepository,
							RendezVousRepository rendezVousRepository,
							ConsultationRepository consultationRepository,
							UserService userService
	){
		return args->{
//LES PATIENTS***********************************
			Stream.of("sara", "mona","mohamed")
					.forEach(name -> {
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						patient.setMalade(false);
						hospitalService.savePatient(patient);
					});
//************************LES MEDECINS
			Stream.of("jad", "hanane","amine")
					.forEach(name -> {
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setSpecialite(Math.random()>0.5?"Gynecologue":"Dentiste");
						medecin.setEmail(name+"@gmail.com");
						hospitalService.saveMedecin(medecin);
					});
			Patient patient=patientRepository.findById(1L).orElse(null);
			Patient patient1=patientRepository.findByNom("mohamed");
			Medecin medecin=medecinRepository.findByNom("hanane");
			//***************************** */
			RendezVous rendezVous=new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(statusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			hospitalService.saveRendezVous(rendezVous);
			RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
			//****************************************** */
			Consultation consultation=new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de la consultation.........");
			consultationRepository.save(consultation);
			//***************************************************************MANY TO MANY
			User u=new User();
			u.setUsername("user1");
			u.setPassword("123456");
			userService.addNewUser(u);
			//
			User u2=new User();
			u2.setUsername("admin");
			u2.setPassword("123456");
			userService.addNewUser(u2);
			//

			Stream.of("STUDENTS","USER","ADMIN").forEach(r->{
				Role role1=new Role();
				role1.setRoleName(r);
				userService.addNewRole(role1);


			});

			userService.addRoleToUser("user1","STUDENTS");
			userService.addRoleToUser("user1","USER");
			userService.addRoleToUser("admin","ADMIN");
			userService.addRoleToUser("admin","USER");
			try {
				User user=userService.authenticate("user1", "123456");
				System.out.println(user.getUserId());
				System.out.println(user.getUsername());
				System.out.println("Roles =>");
				user.getRoles().forEach(r->{
					System.out.println("Roles"+r.toString());
				});
			} catch (Exception e) {
				e.printStackTrace();
			}

		};
	}
}
