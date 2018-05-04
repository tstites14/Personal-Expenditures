package edu.ccm.tstites.personalexpenditures.CoreObjects;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

import edu.ccm.tstites.personalexpenditures.Interfaces.Transactions;

/**
 * Created by tstites on 5/3/2018.
 */

public class Paycheck implements Transactions, Comparable<Transactions>{
    private UUID mUUID;
    private Date mDate;
    private double mPayAmount;
    private String mEmployer;

    private String mType;
    private String mTitle;
    private String mCategory;


    public Paycheck() {
        mUUID = UUID.randomUUID();
        mDate = new Date();
        mType = "Paycheck";
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID id) {
        this.mUUID = id;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public double getPayAmount() {
        return mPayAmount;
    }

    public void setPayAmount(double payAmount) {
        this.mPayAmount = payAmount;
    }

    public String getEmployer() {
        return mEmployer;
    }

    public void setEmployer(String employer) {
        this.mEmployer = employer;
    }

    public String getType() {
        return mType;
    }

    @Override
    public void setTitle(String title) {
        mTitle = title;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public void setCategory(String category) {
        mCategory = category;
    }

    @Override
    public String getCategory() {
        return mCategory;
    }

    @Override
    public int compareTo(@NonNull Transactions o) {
        return mDate.compareTo(o.getDate());
    }
}
