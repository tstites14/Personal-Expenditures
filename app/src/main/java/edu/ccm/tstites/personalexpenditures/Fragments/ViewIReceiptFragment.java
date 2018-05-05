package edu.ccm.tstites.personalexpenditures.Fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

import edu.ccm.tstites.personalexpenditures.CoreObjects.AccountRegister;
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
    private File mImage;
    private ImageView mReceiptImage;

    private ImageButton mSave;



    private static final int REQUEST_CAMERA = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        String mID = this.getArguments().getString("transactionID");
        final Receipt mReceipt = (Receipt) AccountRegister.get(getActivity()).findTransaction(mID);

        mImage = AccountRegister.get(getActivity()).getPhotoFile(mReceipt);

        mTitle = v.findViewById(R.id.edt_title);
        mTitle.setText(mReceipt.getTitle());

        mCategory = v.findViewById(R.id.edt_category);
        mCategory.setText(mReceipt.getCategory());

        mLocation = v.findViewById(R.id.edt_location);
        mLocation.setText(mReceipt.getLocation());

        mCost = v.findViewById(R.id.edt_cost);
        mCost.setText(String.valueOf(mReceipt.getCost()));

        mReceiptImage = v.findViewById(R.id.add_receipt_image);
        mReceiptImage.setImageBitmap((mImage != null) ? getImage(mImage) : getImage(R.drawable.null_image));
        final Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        PackageManager packageManager = getActivity().getPackageManager();
        boolean hasCameraApp = mImage != null  &&imageIntent.resolveActivity(packageManager) != null;
        mReceiptImage.setEnabled(hasCameraApp);
        mReceiptImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ADDRECEIPT", "Receipt image clicked");
                Uri uri = FileProvider.getUriForFile(getActivity(),
                        "edu.ccm.tstites.personalexpenditures.fileprovider", mImage);
                imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                List<ResolveInfo> cameras = getActivity().getPackageManager()
                        .queryIntentActivities(imageIntent, PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo info : cameras) {
                    getActivity().grantUriPermission(info.activityInfo.packageName, uri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }

                startActivityForResult(imageIntent, REQUEST_CAMERA);
            }
        });

        mSave = v.findViewById(R.id.btn_saveReceipt);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountRegister.get(getActivity()).addCash(Double.parseDouble(
                        mCost.getText().toString()) - mReceipt.getCost());
                mReceipt.setTitle(mTitle.getText().toString());
                mReceipt.setCategory(mCategory.getText().toString());
                mReceipt.setLocation(mLocation.getText().toString());
                mReceipt.setCost(Double.parseDouble(mCost.getText().toString()));
                //receipt.setImage();

                AccountRegister.get(getActivity()).updateReceipt(mReceipt);
                Log.i("ADDRECEIPT", "Save button clicked");
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA) {
            setImageFromFile(mImage, mReceiptImage);
        }
    }

    private void setImageFromFile(File file, ImageView imageView) {
        if (file.exists()) {
            Bitmap imgBitmap = getImage(file);
            Log.i("ADDRECEIPT", file.getAbsolutePath());

            imageView.setImageBitmap(imgBitmap);
        } else {
            imageView.setImageResource(R.drawable.null_image);
        }
    }

    private Bitmap getImage(File file) {
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    private Bitmap getImage(int id) {
        return BitmapFactory.decodeResource(getResources(), id);
    }
}
