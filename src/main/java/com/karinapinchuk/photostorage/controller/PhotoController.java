package com.karinapinchuk.photostorage.controller;

import com.karinapinchuk.photostorage.entity.ObjectProfile;
import com.karinapinchuk.photostorage.entity.PhotoProfile;
import com.karinapinchuk.photostorage.enums.CityEnum;
import com.karinapinchuk.photostorage.repository.PhotoRepository;
import com.karinapinchuk.photostorage.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class PhotoController {
    private final PhotoRepository photoRepository;
    private final PhotoService photoService;

    @PostMapping(value = "photo/add")
    public void addPhoto (MultipartFile file, String name, String description, ObjectProfile objectProfile) {
        photoService.addPhoto(file, name, description, objectProfile);
    }

    @GetMapping(value = "/photo/{id}")
    public PhotoProfile getPhoto(@PathVariable Long id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No photo with such id"));
    }

    @PutMapping(value = "photo/update/name/{id}")
    public void updatePhotoName(@PathVariable Long id, String newName) {
        PhotoProfile photoProfile = photoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No photo with such id"));
        photoProfile.setName(newName);
        photoRepository.save(photoProfile);
    }

    @PutMapping(value = "photo/update/description/{id}")
    public void updatePhotoDescription(@PathVariable Long id, String newDescription) {
        PhotoProfile photoProfile = photoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No photo with such id"));
        photoProfile.setDescription(newDescription);
        photoRepository.save(photoProfile);
    }

    @DeleteMapping(value = "photo/delete/{id}")
    public void deleteObject(@PathVariable Long id) {
        photoRepository.deleteById(id);
    }
}
