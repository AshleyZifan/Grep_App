package ca.jrvs.apps.twitter.dto;

public class coordinates{
    private Double longtitude;
    private Double latitude;

    public coordinates(Double longtitude, Double latitude){
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}

