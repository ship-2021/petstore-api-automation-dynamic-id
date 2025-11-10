package com.petstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    private Long id;
    private String name;
    private String status;
    private String[] photoUrls;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String[] getPhotoUrls() { return photoUrls; }
    public void setPhotoUrls(String[] photoUrls) { this.photoUrls = photoUrls; }
}
