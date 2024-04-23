package org.example.demohtmlweb.spec;

import org.example.demohtmlweb.domain.Student;
import org.springframework.data.jpa.domain.Specification;

public interface StudentSpec {
    static Specification<Student> nameLike(String name) {
        if(name == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" +  name + "%");
    }
    static Specification<Student> majorEq(Integer major) {
        if(major == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("major").get("id"), major);
    }
}
