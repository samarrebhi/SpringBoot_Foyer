package com.ey.springboot3security.service.user;

import com.ey.springboot3security.entity.Admin;
import com.ey.springboot3security.entity.Etudiant;
import com.ey.springboot3security.entity.User;
import com.ey.springboot3security.repository.EtudiantRepository;
import com.ey.springboot3security.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor

public class UserService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userDetail = userRepository.findUserByEmail(email);

        // Converting userDetail to UserDetails
        return userDetail.map(UserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
    }

    public String addEtudiant(Map<String, String> data) {
        User user = new User();
        user.setEmail(data.get("email"));
        user.setPassword(data.get("password"));
        user.setRole(data.get("role"));
        Etudiant etudiant = new Etudiant();
        etudiant.setUser(user);
        //etudiant.setCin(Long.parseLong(data.get("cin")));
        etudiant.setNomEt(data.get("nomEt"));
        etudiant.setPrenomEt(data.get("prenomEt"));
        etudiant.setEcole(data.get("ecole"));
        etudiant.setCin(Long.parseLong(data.get("cin")));
        etudiant.setDateNaissance(LocalDate.parse(data.get("dateNaissance")));
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        etudiantRepository.save(etudiant);
        return "User Added Successfully";
    }

    public String addAdmin(Admin admin, User user) {
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);
        return "User Added Successfully";
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email).orElse(null);
    }

}
