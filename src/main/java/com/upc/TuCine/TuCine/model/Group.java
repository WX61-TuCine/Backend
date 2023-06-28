package com.upc.TuCine.TuCine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "name",length = 100,nullable = false)
    private String name;

    @Column(name = "ubication",length = 100,nullable = false)
    private String ubication;


    @Column(name = "description",length = 200,nullable = false)
    private String description;

    @JsonIgnore
    @ManyToMany
    private List<Person> persons;

}
