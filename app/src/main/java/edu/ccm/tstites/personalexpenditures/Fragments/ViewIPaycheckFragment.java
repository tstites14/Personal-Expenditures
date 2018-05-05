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

import java.util.List;
import java.util.UUID;

import edu.ccm.tstites.personalexpenditures.CoreObjects.AccountRegister;
import edu.ccm.tstites.personalexpenditures.CoreObjects.Paycheck;
import edu.ccm.tstites.personalexpenditures.CoreObjects.Receipt;
import edu.ccm.tstites.personalexpenditures.MainActivity;
import edu.ccm.tstites.personalexpenditures.R;

/**
 * Created by tstites on 5/3/2018.
 */

public class ViewIPaycheckFragment extends Fragment {

    EditText mPayAmount;
    EditText mEmployer;
    ImageButton mDelete;
    ImageButton mSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_paycheck, container, false);

        String id = this.getArguments().getString("transactionID");
        final Paycheck paycheck = (Paycheck) AccountRegister.get(getActivity()).findTransaction(id);

        mPayAmount = v.findViewById(R.id.edt_payAmount);
        mPayAmount.setText(String.valueOf(paycheck.getPayAmount()));

        mEmployer = v.findViewById(R.id.edt_employer);
        mEmployer.setText(paycheck.getEmployer());

        mDelete = v.findViewById(R.id.btn_deletePaycheck);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountRegister.get(getActivity()).subtractCash(paycheck.getPayAmount());
                AccountRegister.get(getActivity()).deletePaycheck(paycheck);
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        mSave = v.findViewById(R.id.btn_savePaycheck);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountRegister.get(getActivity()).addCash(
                        Double.parseDouble(mPayAmount.getText().toString()) - paycheck.getPayAmount());
                paycheck.setPayAmount(Double.parseDouble(mPayAmount.getText().toString()));
                paycheck.setEmployer(mEmployer.getText().toString());

                AccountRegister.get(getActivity()).updatePaycheck(paycheck);
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        return v;
    }
}
