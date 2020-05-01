package com.donat.crypto.repository;

import com.donat.crypto.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    User findByEmail(String email);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.events WHERE u.email= ?1")
    User findByEmailAndFetched(String email);

}
