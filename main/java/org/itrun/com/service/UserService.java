package org.itrun.com.service;

import org.itrun.com.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User create(User user);

    List<User> getAll();

    User edit(long id, User user);

    User getById(long id);

    List<User> getByName(String firstName);

    List<User> getByLastName(String lastName);

    List<User> getByType(String type);

    User getByMobile(String mobile);

    User getByEmail(String email);

    User getByPesel(String pesel);

    void delete(long id);

    List<User> searchUsers(Map<String, String> searchParams);
}