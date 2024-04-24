package org.itrun.com.repository;

import org.itrun.com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    List<User> getByFirstName(String firstName);

    List<User> getByLastName(String lastName);

    List<User> getByType(String type);

    User getByMobile(String mobile);

    User getByEmail(String email);

    User getByPesel(String pesel);

    @Query("SELECT u FROM User u WHERE " +
            "(:firstName IS NULL OR u.firstName = :firstName) AND " +
            "(:lastName IS NULL OR u.lastName = :lastName) AND " +
            "(:email IS NULL OR u.email = :email) AND " +
            "(:mobile IS NULL OR u.mobile = :mobile) AND " +
            "(:pesel IS NULL OR u.pesel = :pesel)")
    List<User> searchUsersByCriteria(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            @Param("mobile") String mobile,
            @Param("pesel") String pesel);
}