package framework.jpa.repo;

import org.springframework.stereotype.Repository;

import coliseum.jpa.repo.BaseRepo;
import framework.jpa.entity.Student;

@Repository
public interface StudentRepo extends BaseRepo<Student, Long> {
}
