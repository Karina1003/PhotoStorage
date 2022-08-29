package com.karinapinchuk.photostorage.repository;

import com.karinapinchuk.photostorage.entity.PhotoStorage;
import com.karinapinchuk.photostorage.enums.CityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoStorageRepository extends JpaRepository<PhotoStorage,Long> {
    public Optional<PhotoStorage> findByCity(CityEnum city);
}
