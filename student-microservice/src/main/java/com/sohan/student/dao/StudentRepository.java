package com.sohan.student.dao;

import com.sohan.student.dao.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface provides handles to database, to perform CRUD operations on the table `STUDENT`.
 * The table is represented by the JPA entity {@link StudentEntity}.
 *
 * @author Sohan
 * @see JpaRepository
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

}
