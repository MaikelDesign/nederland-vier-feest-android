package nl.fressh.nederlandviertfeest.model;

/**
 * Created by Ruben on 16-11-16.
 */

public class EventsInformation {
    private String name;
    private String thumbnailUrl;
    private String timeStampB;
    private String description;
    private String locationName;
    private String address;
    private String place;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTimeStampB() {
        return timeStampB;
    }

    public void setTimeStampB(String timeStampB) {
        this.timeStampB = timeStampB;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}