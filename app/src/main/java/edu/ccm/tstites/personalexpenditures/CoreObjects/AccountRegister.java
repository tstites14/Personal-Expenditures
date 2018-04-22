package edu.ccm.tstites.personalexpenditures.CoreObjects;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tstites on 4/21/2018.
 */

public class AccountRegister {
    private static AccountRegister sRegister;
    private Context mContext;

    public AccountRegister(Context context) {
        mContext = context.getApplicationContext();
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
