package com.karinapinchuk.photostorage.service;

import com.karinapinchuk.photostorage.entity.ObjectProfile;
import com.karinapinchuk.photostorage.entity.PhotoProfile;
import com.karinapinchuk.photostorage.enums.CityEnum;
import com.karinapinchuk.photostorage.repository.ObjectRepository;
import com.karinapinchuk.photostorage.repository.PhotoRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ObjectService {
    private final ObjectRepository objectRepository;
    private final PhotoRepository photoRepository;

    public ObjectService(ObjectRepository objectRepository, PhotoRepository photoRepository) {
        this.objectRepository = objectRepository;
        this.photoRepository = photoRepository;
    }

    public void addObject (@NotBlank String name, String description, CityEnum city) {
        ObjectProfile objectProfile = new ObjectProfile();
        objectProfile.setName(name);
        objectProfile.setDescription(description);
        objectProfile.setCityId(city.getId());
        objectRepository.save(objectProfile);
    }

    public ObjectProfile getObject (Long id) {
        return objectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Object not found"));
    }

    public void updateObjectName (@NonNull Long id, String newName) {
        ObjectProfile objectProfile = objectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Object not found"));
        objectProfile.setName(newName);
        objectRepository.save(objectProfile);
    }

    public void updateObjectDescription (@NonNull Long id, String newDescription) {
        ObjectProfile objectProfile = objectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Object not found"));
        objectProfile.setDescription(newDescription);
        objectRepository.save(objectProfile);
    }

    public void updateObjectCity (@NonNull Long id, CityEnum newCity) {
        ObjectProfile objectProfile = objectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Object not found"));
        objectProfile.setCityId(newCity.getId());
        objectRepository.save(objectProfile);
    }

    public void deleteObject (@NonNull Long id) {
        ObjectProfile objectProfile = objectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Object not found"));
        objectRepository.delete(objectProfile);
    }

    public List<PhotoProfile> getPhotoList (@NonNull Long id) {
        return new ArrayList<>(photoRepository.findAllByObjectId(id));
    }
}
