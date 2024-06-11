package com.example.pfa.ServiceImpl;

import com.example.pfa.Dto.RequestChangePassword;
import com.example.pfa.Dto.RequestUser;
import com.example.pfa.Dto.RequestUserUpdate;
import com.example.pfa.Dto.ResponseUser;
import com.example.pfa.Entities.Etablissement;
import com.example.pfa.Entities.User;
import com.example.pfa.Repository.EtablissementRepository;
import com.example.pfa.Repository.UserRepository;
import com.example.pfa.Service.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired // taawed t'injecti l hajet
    private UserRepository userRepository ;
    @Autowired
    private EtablissementRepository etablissementRepository ;
    private PasswordEncoder passwordEncoder ;
    @Override
    public List<ResponseUser> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<ResponseUser> usersFormated = new ArrayList<>();
        for (User user : users) {
            ResponseUser member = ResponseUser.makeUser(user);
            usersFormated.add(member);
        }
        return usersFormated;
    }

    @Override
    public void createUser(RequestUser userRequest) {
       Etablissement etablissement = etablissementRepository.findById(userRequest.getEtablissementId()).orElseThrow();
        User user = User.builder()
                .Nom(userRequest.getNom())
                .Prenom(userRequest.getPrenom())
                .email(userRequest.getEmail())
                .status(true)
                .etablissement(etablissement)
                .Telephone(userRequest.getTelephone())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .Adresse(userRequest.getAdresse())
                .build();
        userRepository.save(user);
    }

    @Override
    public ResponseUser getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow() ;
        return ResponseUser.makeUser(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        if(! userRepository.existsById(id)){
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public User updateUser(Long id, RequestUserUpdate userRequest) {
        User user = userRepository.findById(id).orElseThrow();
        if(userRequest.getEmail() != null){
            user.setEmail(userRequest.getEmail());
        }
        if(userRequest.getNom() != null){
            user.setNom(userRequest.getNom());
        }
        if(userRequest.getPrenom() != null){
            user.setPrenom(userRequest.getPrenom());
        }
        if(userRequest.getStatus() != null){
            user.setStatus(userRequest.getStatus());
        }
        if(userRequest.getEtablissement_id() != null ){
            Etablissement etablissement = etablissementRepository.findById(userRequest.getEtablissement_id()).orElseThrow();
            user.setEtablissement(etablissement);
        }
        if(userRequest.getTelephone() != null){
            user.setTelephone(userRequest.getTelephone());
        } if(userRequest.getAdresse() != null){
            user.setAdresse(userRequest.getAdresse());
        }

        return userRepository.save(user);
    }

    @Override
    public void changePassword(Long id, RequestChangePassword change) throws BadRequestException {
        User user = userRepository.findById(id).orElseThrow();
        if(  !passwordEncoder.matches(change.getOldPassword(),user.getPassword()) ){
            throw new BadRequestException("Error : old password is incorrect !");
        }
        user.setPassword(passwordEncoder.encode(change.getNewPassword()));
        userRepository.save(user);
    }
}
