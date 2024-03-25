package org.lahssini.demojpa.repository;
import org.lahssini.demojpa.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByRoleName(String roleName);
}
