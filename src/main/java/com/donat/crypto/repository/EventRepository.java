package com.donat.crypto.repository;

import java.util.List;
import javax.persistence.LockModeType;
import com.donat.crypto.events.Event;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {

	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	@EntityGraph(attributePaths = {"user"})
	@Query(value="select e from Event e where e.fullFilled = false")
	List<Event> findAllNotFullfilled();
}
