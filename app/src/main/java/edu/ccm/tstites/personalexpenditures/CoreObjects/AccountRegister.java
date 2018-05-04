package edu.ccm.tstites.personalexpenditures.CoreObjects;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public void updateReceipt(Receipt receipt) {
        ContentValues cv = getCV(receipt);

        mDB.update(AccountDBSchema.Accounts.NAME, cv,
                AccountDBSchema.Accounts.Columns.UUID + " = ? ",
                new String[] {receipt.getUUID().toString()});
    }

    public void addPaycheck(Paycheck paycheck) {
        ContentValues cv = getCV(paycheck);

        mDB.insert(AccountDBSchema.Paychecks.NAME, null, cv);
    }

    public void updatePaycheck(Paycheck paycheck) {
        ContentValues cv = getCV(paycheck);

        mDB.update(AccountDBSchema.Paychecks.NAME, cv,
                AccountDBSchema.Paychecks.Columns.UUID + " = ? ",
                new String[] {paycheck.getUUID().toString()});
    }

    public List<Receipt> getReceipts() {
        List<Receipt> receipts = new ArrayList<>();
        AccountCursor receiptCursor = queryReceipts(null, null);

        try {
            receiptCursor.moveToFirst();

            while (!receiptCursor.isAfterLast()) {
                receipts.add(receiptCursor.getReceipt());

                receiptCursor.moveToNext();
            }
        } finally {
            receiptCursor.close();
        }

        return receipts;
    }

    public List<Paycheck> getPaychecks() {
        List<Paycheck> paychecks = new ArrayList<>();
        AccountCursor paycheckCursor = queryPaychecks(null, null);

        try {
            paycheckCursor.moveToFirst();

            while (!paycheckCursor.isAfterLast()) {
                paychecks.add(paycheckCursor.getPaycheck());

                paycheckCursor.moveToNext();
            }
        } finally {
            paycheckCursor.close();
        }

        return paychecks;
    }

    public void addCash(double cash) {
        mCurrentCash += cash;

        SharedPreferences pref = mContext.getSharedPreferences("CashValues",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("CurrentCash", Double.doubleToLongBits(mCurrentCash));
        editor.apply();
    }

    public void subtractCash(double cash) {
        mCurrentCash -= cash;

        SharedPreferences pref = mContext.getSharedPreferences("CashValues",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("CurrentCash", Double.doubleToLongBits(mCurrentCash));
        editor.apply();
    }

    public double getCash() {
        SharedPreferences pref = mContext.getSharedPreferences("CashValues",
                Context.MODE_PRIVATE);
        long longCashValue = pref.getLong("CurrentCash", 0);

        return Double.longBitsToDouble(longCashValue);
    }

    public Object findTransaction(String id) {
        UUID uuid = UUID.fromString(id);
        List<Receipt> receipts = getReceipts();
        List<Paycheck> paychecks = getPaychecks();

        for (int i = 0; i < receipts.size(); i++) {
            if (receipts.get(i).getUUID().equals(uuid)) {
                return receipts.get(i);
            }
        }
        for (int i = 0; i < paychecks.size(); i++) {
            if (paychecks.get(i).getUUID().equals(uuid)) {
                return paychecks.get(i);
            }
        }

        return null;
    }

    public static ContentValues getCV(Receipt receipt) {
        ContentValues cv = new ContentValues();

        cv.put(AccountDBSchema.Accounts.Columns.UUID, receipt.getUUID().toString());
        cv.put(AccountDBSchema.Accounts.Columns.TITLE, receipt.getTitle());
        cv.put(AccountDBSchema.Accounts.Columns.CATEGORY, receipt.getCategory());
        cv.put(AccountDBSchema.Accounts.Columns.DATE, receipt.getDate().getTime());
        cv.put(AccountDBSchema.Accounts.Columns.LOCATION, receipt.getLocation());
        cv.put(AccountDBSchema.Accounts.Columns.COST, receipt.getCost());

        return cv;
    }

    public static ContentValues getCV(Paycheck paycheck) {
        ContentValues cv = new ContentValues();

        cv.put(AccountDBSchema.Paychecks.Columns.UUID, paycheck.getUUID().toString());
        cv.put(AccountDBSchema.Paychecks.Columns.DATE, paycheck.getDate().getTime());
        cv.put(AccountDBSchema.Paychecks.Columns.AMOUNT, paycheck.getPayAmount());
        cv.put(AccountDBSchema.Paychecks.Columns.EMPLOYER, paycheck.getEmployer());

        return cv;
    }

    private AccountCursor queryReceipts(String where, String[] whereArgs) {
        Cursor cursor = mDB.query(AccountDBSchema.Accounts.NAME, null, where, whereArgs,
                null, null, null);

        return new AccountCursor(cursor);
    }

    private AccountCursor queryPaychecks(String where, String[] whereArgs) {
        Cursor cursor = mDB.query(AccountDBSchema.Paychecks.NAME, null, where, whereArgs,
                null, null, null);

        return new AccountCursor(cursor);
    }
}
