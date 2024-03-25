package org.lahssini.demojpa.web;

import java.util.List;

import org.lahssini.demojpa.entities.Patient;
import org.lahssini.demojpa.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PatientRestController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping("/patients")
    public List<Patient> patientList(){
return patientRepository.findAll();
    }
    
}
