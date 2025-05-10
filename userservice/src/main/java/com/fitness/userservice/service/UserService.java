package com.fitness.userservice.service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.repository.UserRepository;
import com.fitness.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private UserResponse assignUser(User user){
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setPassword(user.getPassword());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        return userResponse;
    }

    public UserResponse register(RegisterRequest request) {

        if (repository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email Already Exist");
        }
        User user = new User();

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        User savedUser = repository.save(user);
        return this.assignUser(savedUser);
    }

    public UserResponse getUserProfile(String userId) {
        User user = repository.findById(userId).orElseThrow( () -> new RuntimeException("User Not Found"));
        return this.assignUser(user);
    }

    public List<UserResponse> getAllProfiles() {
        List<User> users = repository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }

        return users.stream().map(this::assignUser).toList(); // Converts all users
    }


}
