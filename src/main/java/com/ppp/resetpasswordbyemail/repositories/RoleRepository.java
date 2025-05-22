package com.ppp.resetpasswordbyemail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.resetpasswordbyemail.entities.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {

    public abstract Role findByName(String name);

}
