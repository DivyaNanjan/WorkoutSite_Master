package com.example.WorkoutSite.controller;


import com.example.WorkoutSite.Util.ErrorText;
import com.example.WorkoutSite.model.User;
import com.example.WorkoutSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity<ErrorText> registerUser(@RequestBody User user)
    {
        ErrorText serviceResponse = userService.registerUser(user);
        return new ResponseEntity<ErrorText>(serviceResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<ErrorText> userLogin(@RequestHeader("emailId") String emailId ,
                                               @RequestHeader("password")String password)
    {
        ErrorText resposeText = userService.authenticate(emailId,password);
        return new ResponseEntity<ErrorText>(resposeText, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT , value = "/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable Integer userId){
        User responseUser = userService.updateUser(user,userId);
        return new ResponseEntity<User>(responseUser,HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<User>> getAllUsers(){
        Iterable<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<Iterable<User>>(allUsers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET , value = "/{userId}")
    public ResponseEntity<User> getUserbyId(@PathVariable Integer userId){
        User responseUser = userService.getUserById(userId);
        return new ResponseEntity<User>(responseUser, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE , value = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }




}
