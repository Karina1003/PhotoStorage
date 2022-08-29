package com.karinapinchuk.photostorage.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "photos")
@Data
@NoArgsConstructor
public class PhotoProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column
    @Lob
    private String photo;

    @Column(nullable = false)
    @Size(max = 50)
    private String name;

    @Column
    @Size(max = 250)
    private String description;

    @Column (nullable = false)
    private Long objectId;
}
