package edu.ccm.tstites.personalexpenditures.CoreObjects;

import java.io.File;
import java.util.Date;

/**
 * Created by tstites on 4/21/2018.
 */

public class Receipt {
    private String mTitle;
    private String mCategory;
    private String mLocation;
    private Date mDate;
    private File mReceiptImage;

    public Receipt() {
        mDate = new Date();
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
