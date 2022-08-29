package com.karinapinchuk.photostorage.service;

import com.karinapinchuk.photostorage.entity.PhotoStorage;
import com.karinapinchuk.photostorage.enums.CityEnum;
import com.karinapinchuk.photostorage.repository.PhotoStorageRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Validated
public class PhotoStorageService {
    private final PhotoStorageRepository photoStorageRepository;

    public PhotoStorage addPhoto (MultipartFile file, CityEnum city, String description) {
        PhotoStorage photoStorage = new PhotoStorage();
        photoStorage.setCity(city);
        photoStorage.setDescription(description);
        try {
            photoStorage.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return photoStorageRepository.save(photoStorage);

    }

    public void deletePhoto (@NonNull Long id) {
        PhotoStorage photoStorage = photoStorageRepository.findById(id)
                                    .orElseThrow(() -> new NoSuchElementException("Photo not found"));
        photoStorageRepository.delete(photoStorage);
    }

    public void changeCity(@NonNull Long id, CityEnum newCity) {
        PhotoStorage photoStorage = photoStorageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Photo not found"));
        photoStorage.setCity(newCity);
        photoStorageRepository.save(photoStorage);
    }

    public void changeDescription(@NonNull Long id, String description) {
        PhotoStorage photoStorage = photoStorageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Photo not found"));
        photoStorage.setDescription(description);
        photoStorageRepository.save(photoStorage);
    }
}
