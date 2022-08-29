package com.karinapinchuk.photostorage.entity;

import com.karinapinchuk.photostorage.enums.CityEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@Data
@NoArgsConstructor
public class PhotoStorage {
    @Id
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CityEnum city;

    public PhotoStorage(CityEnum city) {
        this.id = city.getId();
        this.city = city;
    }
}
