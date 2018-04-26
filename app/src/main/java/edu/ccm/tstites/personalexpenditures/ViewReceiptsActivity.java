package edu.ccm.tstites.personalexpenditures;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import edu.ccm.tstites.personalexpenditures.Fragments.ViewReceiptsFragment;

public class ViewReceiptsActivity extends FragmentActivity {

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
}
