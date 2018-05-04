package edu.ccm.tstites.personalexpenditures.Interfaces;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

/**
 * Created by tstites on 5/3/2018.
 */

public interface Transactions extends Comparable<Transactions> {
    String mType = "";
    String mTitle = "";
    String mCategory = "";
    Date mDate = new Date();
    UUID mUUID = UUID.randomUUID();

    String getType();

    void setTitle(String title);
    String getTitle();

    void setCategory(String category);
    String getCategory();

    Date getDate();

    void setUUID(UUID id);
    UUID getUUID();

    @Override
    int compareTo(@NonNull Transactions o);
}
