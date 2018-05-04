package edu.ccm.tstites.personalexpenditures.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

public class ViewIReceiptFragment extends Fragment {

    private EditText mTitle;
    private EditText mCategory;
    private EditText mLocation;
    private EditText mCost;

    private ImageButton mSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        String id = this.getArguments().getString("transactionID");
        final Receipt receipt = (Receipt) AccountRegister.get(getActivity()).findTransaction(id);

        mTitle = v.findViewById(R.id.edt_title);
        mTitle.setText(receipt.getTitle());

        mCategory = v.findViewById(R.id.edt_category);
        mCategory.setText(receipt.getCategory());

        mLocation = v.findViewById(R.id.edt_location);
        mLocation.setText(receipt.getLocation());

        mCost = v.findViewById(R.id.edt_cost);
        mCost.setText(String.valueOf(receipt.getCost()));

        mSave = v.findViewById(R.id.btn_saveReceipt);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountRegister.get(getActivity()).addCash(Double.parseDouble(
                        mCost.getText().toString()) - receipt.getCost());
                receipt.setTitle(mTitle.getText().toString());
                receipt.setCategory(mCategory.getText().toString());
                receipt.setLocation(mLocation.getText().toString());
                receipt.setCost(Double.parseDouble(mCost.getText().toString()));
                receipt.setReceiptImage(null);

                AccountRegister.get(getActivity()).updateReceipt(receipt);
                Log.i("ADDRECEIPT", "Save button clicked");
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        return v;
    }
}
