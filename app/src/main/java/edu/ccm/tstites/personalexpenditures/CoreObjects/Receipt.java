package edu.ccm.tstites.personalexpenditures.CoreObjects;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tstites on 4/21/2018.
 */

public class Receipt {
    private UUID mUUID;
    private String mTitle;
    private String mCategory;
    private String mLocation;
    private double mCost;
    private Date mDate;
    private File mReceiptImage;

    public Receipt() {
        mUUID = UUID.randomUUID();
        mDate = new Date();
    }

    public Receipt(UUID id) {
        mUUID = id;
        mDate = new Date();
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID mUUID) {
        this.mUUID = mUUID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public double getCost() {
        return mCost;
    }

    public void setCost(double mCost) {
        this.mCost = mCost;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public File getReceiptImage() {
        return mReceiptImage;
    }

    public void setReceiptImage(File mReceiptImage) {
        this.mReceiptImage = mReceiptImage;
    }
}
