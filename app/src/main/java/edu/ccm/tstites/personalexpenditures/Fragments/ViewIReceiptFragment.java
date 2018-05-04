package edu.ccm.tstites.personalexpenditures.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;
import java.util.UUID;

import edu.ccm.tstites.personalexpenditures.CoreObjects.AccountRegister;
import edu.ccm.tstites.personalexpenditures.CoreObjects.Paycheck;
import edu.ccm.tstites.personalexpenditures.CoreObjects.Receipt;
import edu.ccm.tstites.personalexpenditures.R;

/**
 * Created by tstites on 5/3/2018.
 */

public class ViewIReceiptFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        String id = this.getArguments().getString("transactionID");
        Receipt receipt = (Receipt) AccountRegister.get(getActivity()).findTransaction(id);

        EditText edtTitle = v.findViewById(R.id.edt_title);
        edtTitle.setText(receipt.getTitle());

        EditText edtCategory = v.findViewById(R.id.edt_category);
        edtCategory.setText(receipt.getCategory());

        EditText edtLocation = v.findViewById(R.id.edt_location);
        edtLocation.setText(receipt.getLocation());

        EditText edtCost = v.findViewById(R.id.edt_cost);
        edtCost.setText(String.valueOf(receipt.getCost()));

        return v;
    }
}
