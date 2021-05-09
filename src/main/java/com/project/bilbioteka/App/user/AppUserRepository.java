package com.project.bilbioteka.App.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findById(long id);

    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = 1 WHERE a.email = ?1")
    int enableUser(String email);
}
