package com.kartik.diabetesmonitoring;

/**
 * Created by kartikn on 26-09-2015.
 */
public class Medicine {
    private long ID;
    private boolean isInsulin;
    private String Name;
    private String Purpose;
    private boolean isMorning;
    private boolean isNoon;
    private boolean isEvening;
    private boolean isBeforeFood;
    private String quantityType;
    private double quantity;

    public long getID() {
        return ID;
    }

    public boolean getIsInsulin() {
        return isInsulin;
    }

    public void setIsInsulin(boolean isInsulin) {
        this.isInsulin = isInsulin;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public boolean isMorning() {
        return isMorning;
    }

    public void setIsMorning(boolean isMorning) {
        this.isMorning = isMorning;
    }

    public boolean isNoon() {
        return isNoon;
    }

    public void setIsNoon(boolean isNoon) {
        this.isNoon = isNoon;
    }

    public boolean isEvening() {
        return isEvening;
    }

    public void setIsEvening(boolean isEvening) {
        this.isEvening = isEvening;
    }

    public boolean isBeforeFood() {
        return isBeforeFood;
    }

    public void setIsBeforeFood(boolean isBeforeFood) {
        this.isBeforeFood = isBeforeFood;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
