package com.example.plantation_guide;


public class model {
    String title,image,details;
    model()
    {

    }

    public model(String title, String image,String details) {
        this.title = title;
        this.image = image;
        this.details=details;
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


}