package com.karinapinchuk.photostorage.controller;

import com.karinapinchuk.photostorage.entity.PhotoStorage;
import com.karinapinchuk.photostorage.enums.CityEnum;
import com.karinapinchuk.photostorage.repository.PhotoStorageRepository;
import com.karinapinchuk.photostorage.service.PhotoStorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


@RestController
@AllArgsConstructor
public class PhotoStorageController {
    private final PhotoStorageService photoStorageService;
    private final PhotoStorageRepository photoStorageRepository;

    @GetMapping(value = "/photo/{id}")
    public PhotoStorage getPhoto(@PathVariable Long id) {
        return photoStorageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No photo with such id"));
    }

    @PostMapping(value = "/photo/save")
    public void savePhoto(@RequestParam MultipartFile file, @RequestParam CityEnum city, @RequestParam String description) {
        photoStorageService.addPhoto(file, city, description);
    }

    @DeleteMapping(value = "photo/delete/{id}")
    public void deletePhoto(@PathVariable Long id) {
        photoStorageRepository.deleteById(id);
    }

    @PutMapping(value = "/photo/updateCity/{id}")
    public void updateCity(@PathVariable Long id, @RequestParam CityEnum city) {
        photoStorageService.changeCity(id, city);
    }

    @PutMapping(value = "/photo/updateDescription/{id}")
    public void updateDescription(@PathVariable Long id, @RequestParam String description) {
        photoStorageService.changeDescription(id, description);
    }
}
