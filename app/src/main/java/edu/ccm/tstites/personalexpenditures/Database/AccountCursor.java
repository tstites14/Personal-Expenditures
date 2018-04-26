package edu.ccm.tstites.personalexpenditures.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.sql.Date;
import java.util.UUID;

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

        Receipt receipt = new Receipt();
        receipt.setUUID(UUID.fromString(id));
        receipt.setTitle(title);
        receipt.setDate(new Date(date));
        receipt.setCategory(category);
        receipt.setLocation(location);

        return receipt;
    }
}
