package com.example.pfa.Controller;

import com.example.pfa.Dto.RequestChangePassword;
import com.example.pfa.Dto.RequestUser;
import com.example.pfa.Dto.RequestUserUpdate;
import com.example.pfa.Dto.ResponseUser;
import com.example.pfa.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/Users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService ;

    @GetMapping
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Object> getAllUsers(){
        List<ResponseUser> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping ("{id}")
    @PreAuthorize("hasRole('Admin') or #id == principal.id")
    public ResponseEntity<Object> getUserById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Object> addUser(@RequestBody @Valid RequestUser requestUser){
        userService.createUser(requestUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message","save success !"));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity <Object> deleteUser (@PathVariable(name = "id") Long id){
        boolean deleteUser = userService.deleteUser(id);
        if(deleteUser){
            return ResponseEntity.status(HttpStatus.OK).body( Collections.singletonMap("message"," delete success !!!"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( Collections.singletonMap("message"," user does not exist !!!"));
    }


    @PutMapping(value = "{id}")
    @PreAuthorize("hasRole('Admin') or #id == principal.id")
    public ResponseEntity<Object> updateUser(@PathVariable(name = "id") Long id, @RequestBody RequestUserUpdate request){
        userService.updateUser(id,request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("message", "update success !!!"));
    }

    @PatchMapping(value = "{id}")
    @PreAuthorize("#id == principal.id")
    public ResponseEntity<Object> updatePassword(@PathVariable(name="id") Long id, @RequestBody @Valid RequestChangePassword changePassword) throws BadRequestException {
        userService.changePassword(id,changePassword);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("message","update password success !!!"));
    }
}
