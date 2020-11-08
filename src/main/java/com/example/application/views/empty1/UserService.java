package com.example.application.views.empty1;

import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
public class UserService implements CrudListener<User> {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User add(User user) {
      return   userRepository.save(user);
    }

    @Override
    public User update(User user) {
      return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
   userRepository.delete(user);
    }

    public User findByFirstName(String name) {
       return userRepository.findByFirstName(name);
    }

    public User findByUserName(String name) {
        return userRepository.findByUsername(name);
    }

}
