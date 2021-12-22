package com.example.lab2.data;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ThingDescription {
    private UUID thingId;
    private String name;
    private String description;
    private Date foundDate;
    private boolean wasReturned;

    public ThingDescription(UUID thingId, String name, String description, Date foundDate, boolean wasReturned) {
        this.thingId = thingId;
        this.name = name;
        this.description = description;
        this.foundDate = foundDate;
        this.wasReturned = wasReturned;
    }

    public UUID getThingId() {
        return thingId;
    }

    public void setThingId(UUID thingId) {
        this.thingId = thingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }

    public boolean isWasReturned() {
        return wasReturned;
    }

    public void setWasReturned(boolean wasReturned) {
        this.wasReturned = wasReturned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThingDescription that = (ThingDescription) o;
        return wasReturned == that.wasReturned && Objects.equals(description, that.description) && Objects.equals(foundDate, that.foundDate);
    }

    @Override
    public String toString() {
        return "ThingDescription{" +
                "description='" + description + '\'' +
                ", foundDate=" + foundDate +
                ", wasReturned=" + wasReturned +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, foundDate, wasReturned);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
