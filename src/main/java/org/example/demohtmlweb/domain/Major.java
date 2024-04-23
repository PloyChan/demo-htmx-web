package org.example.demohtmlweb.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "major2")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "major2_seq")
    @SequenceGenerator(name = "major2_seq", sequenceName = "major2_seq", allocationSize = 1)
    private Integer id;
    private String name;

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
}
