package com.ey.springboot3security.service.user;

import com.ey.springboot3security.entity.Admin;
import com.ey.springboot3security.entity.Etudiant;
import com.ey.springboot3security.entity.User;
import com.ey.springboot3security.repository.AdminRepository;
import com.ey.springboot3security.repository.EtudiantRepository;
import com.ey.springboot3security.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserInfoRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;
    public Admin getAdminByEmail(String email){
        return this.adminRepository.findAdminByUserEmail(email);
    }


    public String addAdmin(Map<String, String> data) {
        User user = new User();
        user.setEmail(data.get("email"));
        user.setPassword(data.get("password"));
        user.setRole(data.get("role"));
        Admin admin = new Admin();
        admin.setUser(user);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        adminRepository.save(admin);
        return "admin Added Successfully";
    }
}
