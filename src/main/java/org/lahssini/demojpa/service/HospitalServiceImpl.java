package org.lahssini.demojpa.service;
import java.util.UUID;

import org.lahssini.demojpa.entities.Consultation;
import org.lahssini.demojpa.entities.Medecin;
import org.lahssini.demojpa.entities.Patient;
import org.lahssini.demojpa.entities.RendezVous;
import org.lahssini.demojpa.repository.ConsultationRepository;
import org.lahssini.demojpa.repository.MedecinRepository;
import org.lahssini.demojpa.repository.PatientRepository;
import org.lahssini.demojpa.repository.RendezVousRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService{
private PatientRepository patientRepository;
private MedecinRepository medecinRepository;
private RendezVousRepository rendezVousRepository;
private ConsultationRepository consultationRepository;
 // Constructor with all fields
 public HospitalServiceImpl(PatientRepository patientRepository,
 MedecinRepository medecinRepository,
 RendezVousRepository rendezVousRepository,
 ConsultationRepository consultationRepository) {
this.patientRepository = patientRepository;
this.medecinRepository = medecinRepository;
this.rendezVousRepository = rendezVousRepository;
this.consultationRepository = consultationRepository;
}
@Override
public Patient savePatient(Patient patient) {
    // Implement logic to save the patient
    return patientRepository.save(patient);
}

@Override
public Medecin saveMedecin(Medecin medecin) {
    // Implement logic to save the medecin
    return medecinRepository.save(medecin);
}

@Override
public RendezVous saveRendezVous(RendezVous rendezVous) {
    rendezVous.setId(UUID.randomUUID().toString());
    return rendezVousRepository.save(rendezVous);
}

@Override
public Consultation saveConsultation(Consultation consultation) {
    // Implement logic to save the consultation
    return consultationRepository.save(consultation);
}
    
}
