package com.my.eduardarefjev.aviaapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	10.02.2017		Eduard Arefjev 		Created
 */

public class EngineInformation implements Parcelable {

    private int engineNumber;
    private int engineRunningTime;
    private int engineRunningTimeAfterRepair;
    private int engineResource;

    public EngineInformation(){

    }

    public EngineInformation(int engineNumber, int engineRunningTime, int engineRunningTimeAfterRepair, int engineResource) {
        this.engineNumber = engineNumber;
        this.engineRunningTime = engineRunningTime;
        this.engineRunningTimeAfterRepair = engineRunningTimeAfterRepair;
        this.engineResource = engineResource;
    }


    public int getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(int engineNumber) {
        this.engineNumber = engineNumber;
    }

    public int getEngineRunningTime() {
        return engineRunningTime;
    }

    public void setEngineRunningTime(int engineRunningTime) {
        this.engineRunningTime = engineRunningTime;
    }

    public int getEngineRunningTimeAfterRepair() {
        return engineRunningTimeAfterRepair;
    }

    public void setEngineRunningTimeAfterRepair(int engineRunningTimeAfterRepair) {
        this.engineRunningTimeAfterRepair = engineRunningTimeAfterRepair;
    }

    public int getEngineResource() {
        return engineResource;
    }

    public void setEngineResource(int engineResource) {
        this.engineResource = engineResource;
    }

    private EngineInformation(Parcel in) {
        engineNumber = in.readInt();
        engineRunningTime = in.readInt();
        engineRunningTimeAfterRepair = in.readInt();
        engineResource = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(engineNumber);
        dest.writeInt(engineRunningTime);
        dest.writeInt(engineRunningTimeAfterRepair);
        dest.writeInt(engineResource);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EngineInformation> CREATOR = new Creator<EngineInformation>() {
        @Override
        public EngineInformation createFromParcel(Parcel in) {
            return new EngineInformation(in);
        }

        @Override
        public EngineInformation[] newArray(int size) {
            return new EngineInformation[size];
        }
    };
}
