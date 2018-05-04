package edu.ccm.tstites.personalexpenditures;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.ccm.tstites.personalexpenditures.Fragments.AddPaycheckFragment;

public class AddPaycheckActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paycheck);

        startFragment(R.id.addPaycheck);
    }

    @Override
    protected Fragment createFragment() {
        return new AddPaycheckFragment();
    }
}
