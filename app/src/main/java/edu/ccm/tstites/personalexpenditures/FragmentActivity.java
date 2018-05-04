package edu.ccm.tstites.personalexpenditures;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tstites on 4/20/2018.
 */

public abstract class FragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

    protected void startFragment(int parentID) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(parentID);

        if (fragment == null)
            fragment = createFragment();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(parentID, fragment);
        transaction.commit();
    }
}
