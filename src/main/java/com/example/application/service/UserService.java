package com.example.application.service;

import com.example.application.domain.User;
import com.example.application.repository.UserRepository;
import com.vaadin.flow.server.VaadinServletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements CrudListener<User> {


    private UserRepository userRepository;





    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;


    }
    public Optional<User> findByID (long id) {
        return userRepository.findById(id);

    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User add(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    @Override
    public User update(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findByFirstName(String name) {

        return userRepository.findByFirstName(name);
    }

    public Optional<User> findByUserName(String name) {
        return userRepository.findByUsername(name);
    }

    public List<User> findAllFilter(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return userRepository.findAll();
        } else {
            return userRepository.search(stringFilter);
        }

    }

    public String getRole() {
        String name = VaadinServletService.getCurrentServletRequest().getUserPrincipal().getName();
        Optional<User> user = userRepository.findByUsername(name);
        return user.get().getRole();

    }

    public User getUser() {
        String name = VaadinServletService.getCurrentServletRequest().getUserPrincipal().getName();
        Optional<User> user = userRepository.findByUsername(name);
        return user.get();
    }

    public void registerUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        }

    }






