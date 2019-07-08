package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinates {

    private Double[] coordinates = new Double[2];
    String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return Arrays.equals(getCoordinates(), that.getCoordinates()) &&
                Objects.equals(getType(), that.getType());
    }
}

