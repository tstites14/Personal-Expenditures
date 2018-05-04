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

    private EditText edtPayAmount;
    private EditText edtEmployer;
    private ImageButton btnSavePaycheck;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_paycheck, container, false);

        edtPayAmount = v.findViewById(R.id.edt_payAmount);

        edtEmployer = v.findViewById(R.id.edt_employer);

        btnSavePaycheck = v.findViewById(R.id.btn_savePaycheck);
        btnSavePaycheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paycheck paycheck = new Paycheck();
                paycheck.setPayAmount(Double.parseDouble(edtPayAmount.getText().toString()));
                paycheck.setEmployer(edtEmployer.getText().toString());

                AccountRegister.get(getActivity()).addPaycheck(paycheck);
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        return v;
    }
}
