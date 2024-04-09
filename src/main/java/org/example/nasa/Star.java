/*
Name -> Ranshiv Kumar
Student id -> 200555490
Purpose -> Assignment 2
Date -> 8-April-2024
 */

package org.example.nasa;

public class Star {
//    Variables defined
    private final String name;
    private final String apparentMagnitude;
    private final String absoluteMagnitude;
    private final String distanceLightYear;
    private final String spectralClass;

//    Constructor
    public Star(String name, String apparentMagnitude, String absoluteMagnitude, String distanceLightYear, String spectralClass) {
        this.name = name;
        this.apparentMagnitude = apparentMagnitude;
        this.absoluteMagnitude = absoluteMagnitude;
        this.distanceLightYear = distanceLightYear;
        this.spectralClass = spectralClass;
    }

//    Getters and Setters
    public String getName() {
        return name;
    }

    public String getApparentMagnitude() {
        return apparentMagnitude;
    }

    public String getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }

    public String getDistanceLightYear() {
        return distanceLightYear;
    }

    public String getSpectralClass() {
        return spectralClass;
    }
}