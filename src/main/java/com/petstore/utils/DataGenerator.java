package com.petstore.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.models.Pet;

import java.io.File;
import java.util.List;

public class DataGenerator {

    public static Pet getPetFromFile(int index) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Pet> pets = mapper.readValue(new File("src/test/resources/testdata/pets.json"),
                                          mapper.getTypeFactory().constructCollectionType(List.class, Pet.class));
        Pet petData = pets.get(index);

        Pet p = new Pet();
        p.setId(System.currentTimeMillis());  // dynamic unique ID
        p.setName(petData.getName());
        p.setStatus(petData.getStatus());
        p.setPhotoUrls(petData.getPhotoUrls());

        return p;
    }
}
