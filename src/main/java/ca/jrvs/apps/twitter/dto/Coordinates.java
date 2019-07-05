package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinates {

    private Double[] coordinates = new Double[2];

    public Coordinates(){
    }

    public Coordinates(Double longtitude, Double latitude){
        this.coordinates[0] = longtitude;
        this.coordinates[1] = latitude;
    }

    @Override
    public String toString() {
        return "longitude:" + this.coordinates[0] + " latitude:" + this.coordinates[1];
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    public Double getLongtitude() {
        return this.coordinates[0];
    }


    public Double getLatitude() {
        return this.coordinates[1];
    }


}

