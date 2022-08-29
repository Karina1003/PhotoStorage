package com.karinapinchuk.photostorage.service;

import com.karinapinchuk.photostorage.entity.ObjectProfile;
import com.karinapinchuk.photostorage.entity.PhotoProfile;
import com.karinapinchuk.photostorage.enums.CityEnum;
import com.karinapinchuk.photostorage.repository.PhotoRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.NoSuchElementException;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void addPhoto(@NonNull MultipartFile file, @NonNull String name, String description, @NonNull ObjectProfile objectProfile) {
        PhotoProfile photoProfile = new PhotoProfile();
        photoProfile.setName(name);
        photoProfile.setDescription(description);
        photoProfile.setObjectId(objectProfile.getId());
        try {
            photoProfile.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        photoRepository.save(photoProfile);
    }

    public PhotoProfile getPhoto (@NonNull Long id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Photo not found"));
    }

    public void updatePhotoName (@NonNull Long id, @NonNull String newName) {
        PhotoProfile photoProfile = photoRepository.findById(id)
                                    .orElseThrow(() -> new NoSuchElementException("Photo not found"));
        photoProfile.setName(newName);
        photoRepository.save(photoProfile);
    }

    public void updatePhotoDescription (@NonNull Long id, @NonNull String newDescription) {
        PhotoProfile photoProfile = photoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Photo not found"));
        photoProfile.setDescription(newDescription);
        photoRepository.save(photoProfile);
    }

    public void deletePhoto (@NonNull Long id) {
        PhotoProfile photoProfile = photoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Photo not found"));
        photoRepository.delete(photoProfile);
    }
}
