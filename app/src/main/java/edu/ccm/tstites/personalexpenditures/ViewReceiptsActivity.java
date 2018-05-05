package edu.ccm.tstites.personalexpenditures;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import java.util.UUID;

import edu.ccm.tstites.personalexpenditures.Fragments.ViewIPaycheckFragment;
import edu.ccm.tstites.personalexpenditures.Fragments.ViewIReceiptFragment;
import edu.ccm.tstites.personalexpenditures.Fragments.ViewReceiptsFragment;

public class ViewReceiptsActivity extends FragmentActivity
        implements ViewReceiptsFragment.OnListItemSelected {

    @Override
    protected Fragment createFragment() {
        return new ViewReceiptsFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receipts);

        startFragment(R.id.viewReceipts);
    }

    @Override
    public void onItemSelected(int position, String type, UUID id) {
        swapFragment(type, id);
    }

    public void swapFragment(String type, UUID id) {
        Fragment fragment;

        //Determine what type of transaction this is
        if (type.equals("Receipt")) {
            fragment = new ViewIReceiptFragment();
        } else {
            fragment = new ViewIPaycheckFragment();
        }

        Bundle args = new Bundle();
        args.putString("transactionID", id.toString());
        fragment.setArguments(args);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.viewReceipts, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
