package edu.ccm.tstites.personalexpenditures.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import edu.ccm.tstites.personalexpenditures.CoreObjects.Paycheck;
import edu.ccm.tstites.personalexpenditures.CoreObjects.Receipt;

/**
 * Created by tstites on 4/26/2018.
 */

public class AccountCursor extends CursorWrapper {

    public AccountCursor(Cursor cursor) {
        super(cursor);
    }

    public Receipt getReceipt() {
        String id = getString(getColumnIndex(AccountDBSchema.Accounts.Columns.UUID));
        String title = getString(getColumnIndex(AccountDBSchema.Accounts.Columns.TITLE));
        long date = getLong(getColumnIndex(AccountDBSchema.Accounts.Columns.DATE));
        String category = getString(getColumnIndex(AccountDBSchema.Accounts.Columns.CATEGORY));
        String location = getString(getColumnIndex(AccountDBSchema.Accounts.Columns.LOCATION));
        String cost = getString(getColumnIndex(AccountDBSchema.Accounts.Columns.COST));

        Receipt receipt = new Receipt();
        receipt.setUUID(UUID.fromString(id));
        receipt.setTitle(title);
        receipt.setDate(new Date(date));
        receipt.setCategory(category);
        receipt.setLocation(location);
        receipt.setCost(Double.parseDouble(cost));

        return receipt;
    }

    public Paycheck getPaycheck() {
        String id = getString(getColumnIndex(AccountDBSchema.Paychecks.Columns.UUID));
        long date = getLong(getColumnIndex(AccountDBSchema.Paychecks.Columns.DATE));
        String payAmount = getString(getColumnIndex(AccountDBSchema.Paychecks.Columns.AMOUNT));
        String employer = getString(getColumnIndex(AccountDBSchema.Paychecks.Columns.EMPLOYER));

        Paycheck paycheck = new Paycheck();
        paycheck.setUUID(UUID.fromString(id));
        paycheck.setDate(new Date(date));
        paycheck.setPayAmount(Double.parseDouble(payAmount));
        paycheck.setEmployer(employer);

        paycheck.setTitle(payAmount);
        paycheck.setCategory(employer);

        return paycheck;
    }
}
