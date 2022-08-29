package com.karinapinchuk.photostorage.controller;

import com.karinapinchuk.photostorage.entity.PhotoStorage;
import com.karinapinchuk.photostorage.enums.CityEnum;
import com.karinapinchuk.photostorage.repository.PhotoStorageRepository;
import com.karinapinchuk.photostorage.service.PhotoStorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@AllArgsConstructor
public class PhotoStorageController {
    private final PhotoStorageService photoStorageService;
    private final PhotoStorageRepository photoStorageRepository;

    @GetMapping(value = "/city/{id}")
    public PhotoStorage getCity(@PathVariable Long id) {
        return photoStorageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No city with such id"));
    }

    @PostMapping(value = "/city/add")
    public void addCity(@RequestParam CityEnum city) {
        photoStorageService.addCity(city);
    }

    @DeleteMapping(value = "city/delete/{id}")
    public void deleteCity(@PathVariable Long id) {
        photoStorageRepository.deleteById(id);
    }
}
