package org.lahssini.demojpa.repository;
import org.lahssini.demojpa.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin,Long>{
    Medecin  findByNom(String name);
}
