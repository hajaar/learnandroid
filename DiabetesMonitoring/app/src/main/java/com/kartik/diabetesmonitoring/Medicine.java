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
    private boolean quantityType;
    private double quantity;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    public boolean getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(boolean quantityType) {
        this.quantityType = quantityType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getDosage() {
        String tmp_dosage = "";
        if (isMorning == true) tmp_dosage += " Morning ";
        if (isNoon == true) tmp_dosage += " Noon ";
        if (isEvening == true) tmp_dosage += " Evening ";
        if (isBeforeFood == true) {
            tmp_dosage += " Before Food ";
        } else {
            tmp_dosage += " After Food ";
        }
        return tmp_dosage;
    }

    public String getTypeOfMedicine() {
        if (isInsulin == true) {
            return " Insulin ";
        } else {
            return " Tablet ";
        }
    }

    public String getActualQuantity() {
        if (quantityType == true) {
            return "Fixed " + quantity;
        } else {
            return "Variable ";
        }
    }
}
