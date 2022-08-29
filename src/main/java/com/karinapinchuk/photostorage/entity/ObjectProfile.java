package com.karinapinchuk.photostorage.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "objects")
@Data
@NoArgsConstructor
public class ObjectProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 50)
    private String name;

    @Column
    @Size(max = 250)
    private String description;

    @Column (nullable = false)
    private Long cityId;

    public ObjectProfile(String name, String description, Long cityId) {
        this.name = name;
        this.description = description;
        this.cityId = cityId;
    }
}
