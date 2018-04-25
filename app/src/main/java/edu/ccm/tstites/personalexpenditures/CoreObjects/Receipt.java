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

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public File getmReceiptImage() {
        return mReceiptImage;
    }

    public void setmReceiptImage(File mReceiptImage) {
        this.mReceiptImage = mReceiptImage;
    }
}
