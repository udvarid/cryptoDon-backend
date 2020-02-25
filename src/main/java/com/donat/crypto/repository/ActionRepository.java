package com.donat.crypto.repository;

import com.donat.crypto.events.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, Long> {
}
