package org.lahssini.demojpa.repository;

import org.lahssini.demojpa.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    //*************Consulter tous les patients********************
    List<Patient> findAll();
    //*************Consulter un patient********************
    public List<Patient> findPatientById(Long id);
    @Query ("select p from Patient p where p.nom like %:mc% and p.score > :p")
    public List<Patient> chercher(@Param("mc") String kw, @Param("p") double score);
    //***********************************************















}
