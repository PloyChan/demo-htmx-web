package org.example.demohtmlweb.repo;

import org.example.demohtmlweb.domain.Student;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo  extends CrudRepository<Student, Integer>, JpaSpecificationExecutor<Student> {

}
