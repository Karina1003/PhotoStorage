package com.karinapinchuk.photostorage.entity;

import com.karinapinchuk.photostorage.enums.CityEnum;
import lombok.*;
import org.hibernate.annotations.Loader;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "photo_storage")
@Data
@NoArgsConstructor
public class PhotoStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CityEnum city;

    @Column(nullable = false)
    @Lob
    private String photo;

    @Column
    private String description;

    public PhotoStorage(CityEnum city, String photo, String description) {
        this.city = city;
        this.photo = photo;
        this.description = description;
    }
}
