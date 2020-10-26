package com.example.plantation_guide;


public class model {
    String title,image,details,sunlight,water,humidity,temperature;
    model()
    {

    }

    public model(String title, String image,String details,String sunlight,String water,String humidity,String temperature) {
        this.title = title;
        this.image = image;
        this.details=details;

        this.sunlight=sunlight;
        this.water=water;
        this.humidity=humidity;
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

    public String getHumidity() {
        return humidity;
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

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

}