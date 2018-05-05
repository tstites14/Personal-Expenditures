package edu.ccm.tstites.personalexpenditures.CoreObjects;

import android.support.annotation.NonNull;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import edu.ccm.tstites.personalexpenditures.Interfaces.Transactions;

/**
 * Created by tstites on 4/21/2018.
 */

public class Receipt implements Transactions, Comparable<Transactions> {
    private UUID mUUID;
    private String mTitle;
    private String mCategory;
    private String mLocation;
    private double mCost;
    private Date mDate;

    private String mType;
    private String mImage;


    public Receipt() {
        mUUID = UUID.randomUUID();
        mDate = new Date();
        mType = "Receipt";
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

    public void setTitle(String title) {
        this.mTitle = title;
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

    public void setCost(double cost) {
        this.mCost = cost;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public void setImage(String  image) {
        mImage = image;
    }

    public String getReceiptImage() {
        return "IMG_" + getUUID().toString() + ".jpg";
    }

    public String getType() {
        return mType;
    }

    @Override
    public int compareTo(@NonNull Transactions o) {
        return mDate.compareTo(o.getDate());
    }
}
