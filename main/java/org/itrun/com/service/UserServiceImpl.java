package org.itrun.com.service;

import org.itrun.com.entity.User;
import org.itrun.com.exceptions.UserNotFoundException;
import org.itrun.com.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserJpaRepository repository;

    @Autowired
    public UserServiceImpl(UserJpaRepository repository) {
        this.repository = repository;
    }

    public UserServiceImpl() {

    }

    @Override
    public User create(User user) {
        user.setType(user.getType());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setMobile(user.getMobile());
        user.setEmail(user.getEmail());
        user.setPesel(user.getPesel());
        return repository.save(user);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User edit(long id, User user) {
        User existingUser = repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        existingUser.setType(user.getType());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setMobile(user.getMobile());
        existingUser.setEmail(user.getEmail());

        User updatedUser = repository.save(existingUser);
        return updatedUser;
    }

    @Override
    public User getById(long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return user;
    }

    @Override
    public List<User> getByName(String firstName) {
        List<User> users = repository.getByFirstName(firstName);
        if (users.isEmpty()) {
            throw new UserNotFoundException("Users not found with firstName: " + firstName);
        }
        return users;
    }

    @Override
    public List<User> getByLastName(String lastName) {
        List<User> users = repository.getByLastName(lastName);
        if (users.isEmpty()) {
            throw new UserNotFoundException("Users not found with last name: " + lastName);
        }
        return users;
    }

    @Override
    public List<User> getByType(String type) {
        List<User> users = repository.getByType(type);
        if (users.isEmpty()) {
            throw new UserNotFoundException("Users not found with type: " + type);
        }
        return users;
    }

    @Override
    public User getByMobile(String mobile) {
        User user = repository.getByMobile(mobile);
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = repository.getByEmail(email);
        return user;
    }

    @Override
    public User getByPesel(String pesel) {
        User user = repository.getByPesel(pesel);
        return user;
    }

    @Override
    public void delete(long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public List<User> searchUsers(Map<String, String> searchParams) {
        String firstName = searchParams.get("firstName");
        String lastName = searchParams.get("lastName");
        String email = searchParams.get("email");
        String mobile = searchParams.get("mobile");
        String pesel = searchParams.get("pesel");

        return repository.searchUsersByCriteria(firstName, lastName, email, mobile, pesel);
    }
}