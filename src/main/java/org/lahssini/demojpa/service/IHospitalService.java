package org.lahssini.demojpa.service;

import org.lahssini.demojpa.entities.Consultation;
import org.lahssini.demojpa.entities.Medecin;
import org.lahssini.demojpa.entities.Patient;
import org.lahssini.demojpa.entities.RendezVous;

public interface IHospitalService {
     Patient savePatient(Patient patient);
     Medecin saveMedecin(Medecin medecin);

     RendezVous saveRendezVous(RendezVous rendezVous);

     Consultation saveConsultation(Consultation consultation);
}
