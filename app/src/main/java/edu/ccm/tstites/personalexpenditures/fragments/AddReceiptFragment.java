package edu.ccm.tstites.personalexpenditures.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import edu.ccm.tstites.personalexpenditures.R;

/**
 * Created by tstites on 4/20/2018.
 */

public class AddReceiptFragment extends Fragment {

    private EditText mTitle;
    private EditText mCategory;
    private EditText mLocation;
    private ImageView mReceiptImage;
    private ImageButton mSaveButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        mTitle = v.findViewById(R.id.edt_title);

        mCategory = v.findViewById(R.id.edt_category);

        mLocation = v.findViewById(R.id.edt_location);

        mReceiptImage = v.findViewById(R.id.add_receipt_image);

        mSaveButton = v.findViewById(R.id.btn_saveReceipt);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }
}
