package edu.ccm.tstites.personalexpenditures.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import edu.ccm.tstites.personalexpenditures.CoreObjects.AccountRegister;
import edu.ccm.tstites.personalexpenditures.CoreObjects.Paycheck;
import edu.ccm.tstites.personalexpenditures.MainActivity;
import edu.ccm.tstites.personalexpenditures.R;

/**
 * Created by tstites on 5/3/2018.
 */

public class AddPaycheckFragment extends Fragment {

    private EditText mPayAmount;
    private EditText mEmployer;
    private ImageButton mDeletePaycheck;
    private ImageButton mSavePaycheck;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_paycheck, container, false);

        mPayAmount = v.findViewById(R.id.edt_payAmount);

        mEmployer = v.findViewById(R.id.edt_employer);

        mDeletePaycheck = v.findViewById(R.id.btn_deletePaycheck);
        mDeletePaycheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        mSavePaycheck = v.findViewById(R.id.btn_savePaycheck);
        mSavePaycheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paycheck paycheck = new Paycheck();
                paycheck.setPayAmount(Double.parseDouble(mPayAmount.getText().toString()));
                paycheck.setEmployer(mEmployer.getText().toString());

                AccountRegister.get(getActivity()).addPaycheck(paycheck);
                AccountRegister.get(getActivity()).addCash(Double.parseDouble(
                    mPayAmount.getText().toString()));
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        return v;
    }
}
