package edu.ccm.tstites.personalexpenditures;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import edu.ccm.tstites.personalexpenditures.fragments.AddReceiptFragment;

public class AddReceipt extends FragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new AddReceiptFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receipt);

        startFragment(R.id.addReceipt);
    }
}
