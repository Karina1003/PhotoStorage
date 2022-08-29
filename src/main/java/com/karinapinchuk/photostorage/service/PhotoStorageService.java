package com.karinapinchuk.photostorage.service;

import com.karinapinchuk.photostorage.entity.ObjectProfile;
import com.karinapinchuk.photostorage.entity.PhotoStorage;
import com.karinapinchuk.photostorage.enums.CityEnum;
import com.karinapinchuk.photostorage.repository.ObjectRepository;
import com.karinapinchuk.photostorage.repository.PhotoStorageRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PhotoStorageService {
    private final PhotoStorageRepository photoStorageRepository;
    private final ObjectRepository objectRepository;

    public void addCity (CityEnum city) {
        photoStorageRepository.save(new PhotoStorage(city));
    }

    public PhotoStorage getCitybyId (@NonNull Long id) {
        return photoStorageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("City not found"));
    }

    public void deleteCity (@NonNull Long id) {
        PhotoStorage photoStorage = photoStorageRepository.findById(id)
                                    .orElseThrow(() -> new NoSuchElementException("City not found"));
        photoStorageRepository.delete(photoStorage);
    }

    public void updateCity(@NonNull Long id, CityEnum newCity) {
        PhotoStorage photoStorage = photoStorageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("City not found"));
        photoStorage.setCity(newCity);
        photoStorageRepository.save(photoStorage);
    }

    public List<ObjectProfile> getObjectList (@NonNull Long id) {
        return new ArrayList<>(objectRepository.findAllByCityId(id));
    }
}
