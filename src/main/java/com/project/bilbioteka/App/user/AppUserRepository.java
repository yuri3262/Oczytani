package com.project.bilbioteka.App.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
//@Transactional(readOnly = true)
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findById(long id);
    AppUser findUserByEmail(String email);
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByName(String name);

    AppUser findByResetPasswordToken(String token);
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = 1 WHERE a.email = ?1")
    int enableUser(String email);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM AppUser a WHERE a.id = ?1")
    int deleteAppUser(Long id);
}
