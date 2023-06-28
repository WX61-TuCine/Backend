package com.upc.TuCine.TuCine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "MyGroup")
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

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "FK_GROUP_PERSON"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Person person;

    @JsonIgnore
    @ManyToMany
    private List<Topic> topics;

}
