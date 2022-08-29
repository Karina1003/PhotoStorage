package com.karinapinchuk.photostorage.controller;

import com.karinapinchuk.photostorage.entity.ObjectProfile;
import com.karinapinchuk.photostorage.enums.CityEnum;
import com.karinapinchuk.photostorage.repository.ObjectRepository;
import com.karinapinchuk.photostorage.service.ObjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@AllArgsConstructor
public class ObjectController {
    private final ObjectRepository objectRepository;
    private final ObjectService objectService;

    @PostMapping(value = "object/add")
    public void addObject (@RequestParam String name, @RequestParam String description, @RequestParam CityEnum city) {
        objectService.addObject(name, description, city);
    }

    @GetMapping(value = "/object/{id}")
    public ObjectProfile getObject(@PathVariable Long id) {
        return objectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No object with such id"));
    }

    @PutMapping(value = "object/update/name/{id}")
    public void updateObjectName(@PathVariable Long id, String newName) {
        ObjectProfile objectProfile = objectRepository.findById(id)
                                    .orElseThrow(() -> new ResponseStatusException(
                                            HttpStatus.NOT_FOUND, "No object with such id"));
        objectProfile.setName(newName);
        objectRepository.save(objectProfile);
    }

    @PutMapping(value = "object/update/description/{id}")
    public void updateObjectDescription(@PathVariable Long id, String newDescription) {
        ObjectProfile objectProfile = objectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No object with such id"));
        objectProfile.setDescription(newDescription);
        objectRepository.save(objectProfile);
    }

    @PutMapping(value = "object/update/city/{id}")
    public void updateObjectCity(@PathVariable Long id, CityEnum city) {
        ObjectProfile objectProfile = objectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No object with such id"));
        objectProfile.setCityId(city.getId());
        objectRepository.save(objectProfile);
    }

    @DeleteMapping(value = "object/delete/{id}")
    public void deleteObject(@PathVariable Long id) {
        objectRepository.deleteById(id);
    }
}
