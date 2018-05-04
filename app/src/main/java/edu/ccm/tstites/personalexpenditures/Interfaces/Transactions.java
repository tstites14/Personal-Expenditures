package edu.ccm.tstites.personalexpenditures.Interfaces;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by tstites on 5/3/2018.
 */

public interface Transactions extends Comparable<Transactions> {
    String mType = "";
    String mTitle = "";
    String mCategory = "";
    Date mDate = new Date();

    String getType();

    void setTitle(String title);
    String getTitle();

    void setCategory(String category);
    String getCategory();

    Date getDate();

    @Override
    int compareTo(@NonNull Transactions o);
}
