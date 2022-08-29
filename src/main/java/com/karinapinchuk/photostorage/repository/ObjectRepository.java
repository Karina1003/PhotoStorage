package com.karinapinchuk.photostorage.repository;

import com.karinapinchuk.photostorage.entity.ObjectProfile;
import com.karinapinchuk.photostorage.entity.PhotoStorage;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObjectRepository extends JpaRepository<ObjectProfile, Long> {
    List<ObjectProfile> findAllByCityId(Long id);
}
