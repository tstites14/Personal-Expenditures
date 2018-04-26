package edu.ccm.tstites.personalexpenditures.CoreObjects;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.ccm.tstites.personalexpenditures.Database.AccountDBHelper;

/**
 * Created by tstites on 4/21/2018.
 */

public class AccountRegister {
    private static AccountRegister sRegister;
    private Context mContext;
    private SQLiteDatabase mDB;

    public AccountRegister(Context context) {
        mContext = context.getApplicationContext();
        mDB = new AccountDBHelper(mContext).getWritableDatabase();
    }

    public static AccountRegister get(Context context) {
        if (sRegister == null) {
            sRegister = new AccountRegister(context);
        }

        return sRegister;
    }

    public List<Receipt> getReceipts() {
        List<Receipt> receipts = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            receipts.add(new Receipt());
        }

        return receipts;
    }
}
