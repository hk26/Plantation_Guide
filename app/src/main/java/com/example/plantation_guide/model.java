package com.example.plantation_guide;


public class model {
    String title,image,details,sunlight,water,humadity,temperature;
    model()
    {

    }

    public model(String title, String image,String details,String sunlight,String water,String humadity,String temperature) {
        this.title = title;
        this.image = image;
        this.details=details;

        this.sunlight=sunlight;
        this.water=water;
        this.humadity=humadity;
        this.temperature=temperature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSunlight() {
        return sunlight;
    }

    public String getWater() {
        return water;
    }

    public String getHumadity() {
        return humadity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setSunlight(String sunlight) {
        this.sunlight = sunlight;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public void setHumadity(String humadity) {
        this.humadity = humadity;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

}