package com.kartik.diabetesmonitoring;

/**
 * Created by kartikn on 20-09-2015.
 */
public class InsulinReading {
    private long ID;
    private String Name;
    private String Type;
    private String TimeOfDay;
    private int Quantity;
    private long InjectedDateTime;
    private long CreatedDateTime;

    public InsulinReading(String name, String type, String timeOfDay, int quantity, long injectedDateTime, long createdDateTime) {
        Name = name;
        Type = type;
        TimeOfDay = timeOfDay;
        Quantity = quantity;
        InjectedDateTime = injectedDateTime;
        CreatedDateTime = createdDateTime;
    }

    public InsulinReading() {
    }

    public long getCreatedDateTime() {
        return CreatedDateTime;
    }

    public void setCreatedDateTime(long mCreatedDateTime) {
        this.CreatedDateTime = mCreatedDateTime;
    }

    public long getID() {
        return ID;
    }

    public void setID(long mID) {
        this.ID = mID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String mName) {
        this.Name = mName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String mType) {
        this.Type = mType;
    }

    public String getTimeOfDay() {
        return TimeOfDay;
    }

    public void setTimeOfDay(String mTimeOfDay) {
        this.TimeOfDay = mTimeOfDay;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int mQuantity) {
        this.Quantity = mQuantity;
    }

    public long getInjecteDateTime() {
        return InjectedDateTime;
    }

    public void setInjecteDateTime(long mInjecteDateTime) {
        this.InjectedDateTime = mInjecteDateTime;
    }

}
