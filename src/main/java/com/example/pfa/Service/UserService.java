package com.example.pfa.Service;

import com.example.pfa.Dto.RequestChangePassword;
import com.example.pfa.Dto.RequestUser;
import com.example.pfa.Dto.RequestUserUpdate;
import com.example.pfa.Dto.ResponseUser;
import com.example.pfa.Entities.User;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UserService {
    List<ResponseUser> getAllUsers();
    void createUser(RequestUser userRequest);
    ResponseUser getUserById(Long id);
    boolean deleteUser(Long id);
    User updateUser(Long id, RequestUserUpdate userRequest);
    void changePassword(Long id , RequestChangePassword change) throws BadRequestException;

}
