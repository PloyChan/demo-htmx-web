package org.example.demohtmlweb.domain;
import jakarta.persistence.*;
import org.example.demohtmlweb.enums.Gender;

@Entity
@Table(name = "students2")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "students2_seq")
    @SequenceGenerator(name = "students2_seq", sequenceName = "students2_seq", allocationSize = 1)
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToOne
    private Major major;
    private float gpa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
}
