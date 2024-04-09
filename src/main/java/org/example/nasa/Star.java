package org.example.nasa;

public class Star {
    private final String name;
    private final String apparentMagnitude;
    private final String absoluteMagnitude;
    private final String distanceLightYear;
    private final String spectralClass;

    public Star(String name, String apparentMagnitude, String absoluteMagnitude, String distanceLightYear, String spectralClass) {
        this.name = name;
        this.apparentMagnitude = apparentMagnitude;
        this.absoluteMagnitude = absoluteMagnitude;
        this.distanceLightYear = distanceLightYear;
        this.spectralClass = spectralClass;
    }

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