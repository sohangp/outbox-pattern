package com.sohan.student.outbox.dao;

import com.sohan.student.outbox.models.OutBoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface provides handles to database, to perform CRUD operations on the table `OUTBOX`.
 * The table is represented by the JPA entity {@link OutBoxEntity}.
 *
 * @author Sohan
 * @see JpaRepository
 */
@Repository
public interface OutBoxRepository extends JpaRepository<OutBoxEntity, Integer> {

}
