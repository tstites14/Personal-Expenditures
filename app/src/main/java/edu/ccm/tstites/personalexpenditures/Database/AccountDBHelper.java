package edu.ccm.tstites.personalexpenditures.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.ccm.tstites.personalexpenditures.Database.AccountDBSchema.Accounts;
import edu.ccm.tstites.personalexpenditures.Database.AccountDBSchema.Paychecks;

/**
 * Created by tstites on 4/26/2018.
 */

public class AccountDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "accounts.db";
    private static final int VERSION = 1;

    public AccountDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Accounts.NAME +
        "(_id integer primary key autoincrement, " +
                Accounts.Columns.UUID + ", " +
                Accounts.Columns.TITLE + ", " +
                Accounts.Columns.CATEGORY + ", " +
                Accounts.Columns.DATE + ", " +
                Accounts.Columns.LOCATION + ", " +
                Accounts.Columns.COST + ", " +
                Accounts.Columns.IMAGE + ")");

        db.execSQL("CREATE TABLE " + Paychecks.NAME +
        "(_id integer primary key autoincrement, " +
                Paychecks.Columns.UUID + ", " +
                Paychecks.Columns.DATE + ", " +
                Paychecks.Columns.AMOUNT + ", " +
                Paychecks.Columns.EMPLOYER + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + Accounts.NAME);
        db.execSQL("drop table if exists " + Paychecks.NAME);
        onCreate(db);
    }
}
