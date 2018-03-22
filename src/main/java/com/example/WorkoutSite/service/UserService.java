package com.example.WorkoutSite.service;

import com.example.WorkoutSite.Util.ErrorText;
import com.example.WorkoutSite.model.User;
import com.example.WorkoutSite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    private String getEncodedPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    private boolean checkPassword(String password, User user) {
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    public User createUser(User user){
        user.setPassword(getEncodedPassword(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    public ErrorText registerUser(User user)
    {

        ErrorText errorText = new ErrorText();
        User existingUser = userRepo.findUserByEmailId(user.getEmailId());
        if(existingUser == null ) {
            createUser(user);
            errorText.setMessage("User registered successfully");
            errorText.setStatus("Success");
        }
        else {
            errorText.setMessage("User already registered, please try with a new email id");
            errorText.setStatus("Failed");
        }
        return  errorText;
    }

    public ErrorText authenticate(String emailId , String password){
        User existingUser = userRepo.findUserByEmailId(emailId);
        ErrorText resposeText = new ErrorText();
        if(existingUser != null && checkPassword(password,existingUser))
           {
                resposeText.setMessage("User logged in successfully");
                resposeText.setStatus("Success");
            }
            else
            {
                resposeText.setMessage("Invalid emailId or password");
                resposeText.setStatus("Failed");

            }
            return resposeText;
    }

    public User updateUser(User user, Integer userId){
        User findUser = userRepo.findOne(userId);
        findUser.setUserName(user.getUserName());
        findUser.setEmailId(user.getEmailId());
        findUser.setPassword(getEncodedPassword(user.getPassword()));
        return createUser(findUser);

    }

    public Iterable<User> getAllUsers(){
        Iterable<User> userList = userRepo.findAll();
        return userList;
    }

    public User getUserById(Integer userId)
    {
        User user =userRepo.findOne(userId);
        return user;
    }

    public void deleteUser(Integer userId)
    {
        userRepo.delete(userId);
    }




}
