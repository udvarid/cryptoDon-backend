package com.donat.crypto.repository;

import com.donat.crypto.events.Actions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionsRepository extends JpaRepository<Actions, Long> {
}
