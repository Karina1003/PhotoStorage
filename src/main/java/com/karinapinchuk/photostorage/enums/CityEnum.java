package com.karinapinchuk.photostorage.enums;

public enum CityEnum {
    MOTYZHYN(1L, "MOTYZHYN"),
    MAKARIV(2L, "MAKARIV"),
    KOLOMYYA(3L, "KOLOMYYA"),
    DROHOBYCH(4L, "DROHOBYCH");

    private final Long id;
    private final String city;

    private CityEnum(Long id, String city) {
        this.id = id;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }
}
