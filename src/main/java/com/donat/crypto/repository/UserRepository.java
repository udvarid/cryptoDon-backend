package com.donat.crypto.repository;

import com.donat.crypto.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByEmail(String email);

    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.events WHERE u.email= ?1")
    User findByEmailAndFetched(String email);

}
