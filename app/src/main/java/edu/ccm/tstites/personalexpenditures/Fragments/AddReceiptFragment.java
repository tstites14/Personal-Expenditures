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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
 * Created by tstites on 4/20/2018.
 */

public class AddReceiptFragment extends Fragment {

    private Receipt mReceipt = new Receipt();

    private EditText mTitle;
    private EditText mCategory;
    private EditText mLocation;
    private EditText mCost;
    private ImageButton mDeleteButton;
    private ImageView mReceiptImage;
    private File mImage;
    private ImageButton mSaveButton;

    private static final int REQUEST_CAMERA = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImage = AccountRegister.get(getActivity()).getPhotoFile(mReceipt);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        mTitle = v.findViewById(R.id.edt_title);

        mCategory = v.findViewById(R.id.edt_category);

        mLocation = v.findViewById(R.id.edt_location);

        mCost = v.findViewById(R.id.edt_cost);

        mDeleteButton = v.findViewById(R.id.btn_deleteReceipt);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        mReceiptImage = v.findViewById(R.id.add_receipt_image);
        final Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        PackageManager packageManager = getActivity().getPackageManager();
        boolean hasCameraApp = mImage != null && imageIntent.resolveActivity(packageManager) != null;
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

        mSaveButton = v.findViewById(R.id.btn_saveReceipt);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isReceiptFilled()) {
                    Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.invald_data);
                    mSaveButton.startAnimation(anim);

                    return;
                }

                mReceipt.setTitle(mTitle.getText().toString());
                mReceipt.setCategory(mCategory.getText().toString());
                mReceipt.setLocation(mLocation.getText().toString());
                mReceipt.setCost(Double.parseDouble(mCost.getText().toString()));
                mReceipt.setImage(mImage.getAbsolutePath());

                AccountRegister.get(getActivity()).addReceipt(mReceipt);
                AccountRegister.get(getActivity()).subtractCash(Double.parseDouble(
                        mCost.getText().toString()));
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
            Bitmap imgBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            Log.i("ADDRECEIPT", file.getAbsolutePath());

            imageView.setImageBitmap(imgBitmap);
        } else {
            imageView.setImageResource(R.drawable.null_image);
        }
    }

    private boolean isReceiptFilled() {
        return (mTitle.getText().toString().equals("") ||
                mCategory.getText().toString().equals("") ||
                mLocation.getText().toString().equals("") ||
                mCost.getText().toString().equals(""));
    }
}

