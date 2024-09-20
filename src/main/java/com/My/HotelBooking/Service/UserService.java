package com.My.HotelBooking.Service;

import com.My.HotelBooking.Entity.User;
import com.My.HotelBooking.Entity.Role;
import com.My.HotelBooking.Repository.UserRepository;
import com.My.HotelBooking.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Create or Save a User with Roles
    public User createUser(User user, Set<String> roleNames) {
        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                role = new Role(roleName);
                roleRepository.save(role); // Save new role to the database
            }
            roles.add(role);
        }
        user.setRoles(roles);
        return userRepository.save(user); // Save the user with roles
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber);
    }


    // Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update user
    public User updateUser(Long id, User userDetails, Set<String> roleNames) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());

            Set<Role> roles = new HashSet<>();
            for (String roleName : roleNames) {
                Role role = roleRepository.findByName(roleName);
                if (role == null) {
                    role = new Role(roleName);
                    roleRepository.save(role);
                }
                roles.add(role);
            }
            user.setRoles(roles);

            return userRepository.save(user);
        }
        return null; // or throw a custom exception
    }

    // Delete user by ID
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }


    public boolean existsByMobileNumber(String mobileNumber) {
        return userRepository.existsByMobileNumber(mobileNumber);
    }
}
