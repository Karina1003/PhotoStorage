package com.karinapinchuk.photostorage.repository;

import com.karinapinchuk.photostorage.entity.ObjectProfile;
import com.karinapinchuk.photostorage.entity.PhotoProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoProfile, Long> {
    List<PhotoProfile> findAllByObjectId(Long id);
}
