package com.example.jobportal.classes;

import java.util.List;
/*
* This model class is used ti store information that populated the
* recyclerview in ServiceSubCategoryAdapter
* */
public class ServiceSubCategory {
    //TODO Add subcategory name
    private String name;
    private String imageUrl;//Might be changed to Uri

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public ServiceSubCategory(String name, String imageUrl, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
