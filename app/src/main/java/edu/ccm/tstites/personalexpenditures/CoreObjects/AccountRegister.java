package edu.ccm.tstites.personalexpenditures.CoreObjects;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import edu.ccm.tstites.personalexpenditures.Database.AccountCursor;
import edu.ccm.tstites.personalexpenditures.Database.AccountDBHelper;
import edu.ccm.tstites.personalexpenditures.Database.AccountDBSchema;

/**
 * Created by tstites on 4/21/2018.
 */

public class AccountRegister {
    private static AccountRegister sRegister;
    private Context mContext;
    private SQLiteDatabase mDB;
    private double mCurrentCash;

    public AccountRegister(Context context) {
        mContext = context.getApplicationContext();
        mDB = new AccountDBHelper(mContext).getWritableDatabase();

        SharedPreferences pref = mContext.getSharedPreferences("CashValues",
                Context.MODE_PRIVATE);
        mCurrentCash = Double.longBitsToDouble(pref.getLong("CurrentCash", 0));
    }

    public static AccountRegister get(Context context) {
        if (sRegister == null) {
            sRegister = new AccountRegister(context);
        }

        return sRegister;
    }

    public void addReceipt(Receipt receipt) {
        ContentValues cv = getCV(receipt);

        mDB.insert(AccountDBSchema.Accounts.NAME, null, cv);
    }

    public List<Receipt> getReceipts() {
        List<Receipt> receipts = new ArrayList<>();
        AccountCursor cursor = queryReceipts(null, null);

        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                receipts.add(cursor.getReceipt());

                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return receipts;
    }

    private void addCash(double cash) {
        mCurrentCash += cash;

        SharedPreferences pref = mContext.getSharedPreferences("CashValues",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("CurrentCash", Double.doubleToLongBits(mCurrentCash));
        editor.apply();
    }

    private void subtractCash(double cash) {
        mCurrentCash -= cash;

        SharedPreferences pref = mContext.getSharedPreferences("CashValues",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("CurrentCash", Double.doubleToLongBits(mCurrentCash));
        editor.apply();
    }

    private double getCash() {
        SharedPreferences pref = mContext.getSharedPreferences("CashValues",
                Context.MODE_PRIVATE);
        long longCashValue = pref.getLong("CurrentCash", 0);

        return Double.longBitsToDouble(longCashValue);
    }

    private static ContentValues getCV(Receipt receipt) {
        ContentValues cv = new ContentValues();

        cv.put(AccountDBSchema.Accounts.Columns.UUID, receipt.getUUID().toString());
        cv.put(AccountDBSchema.Accounts.Columns.TITLE, receipt.getTitle());
        cv.put(AccountDBSchema.Accounts.Columns.CATEGORY, receipt.getCategory());
        cv.put(AccountDBSchema.Accounts.Columns.DATE, receipt.getDate().toString());
        cv.put(AccountDBSchema.Accounts.Columns.LOCATION, receipt.getLocation());
        cv.put(AccountDBSchema.Accounts.Columns.COST, receipt.getCost());

        return cv;
    }

    private AccountCursor queryReceipts(String where, String[] whereArgs) {
        Cursor cursor = mDB.query(AccountDBSchema.Accounts.NAME, null, where, whereArgs,
                null, null, null);

        return new AccountCursor(cursor);
    }
}
