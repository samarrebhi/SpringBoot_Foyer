package com.ey.springboot3security.repository;

import com.ey.springboot3security.entity.Admin;
import com.ey.springboot3security.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findAdminByUserEmail(String email);

}
