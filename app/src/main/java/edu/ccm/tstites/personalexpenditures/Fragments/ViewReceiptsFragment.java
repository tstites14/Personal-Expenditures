package edu.ccm.tstites.personalexpenditures.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.ccm.tstites.personalexpenditures.CoreObjects.AccountRegister;
import edu.ccm.tstites.personalexpenditures.CoreObjects.Receipt;
import edu.ccm.tstites.personalexpenditures.R;

/**
 * Created by tstites on 4/20/2018.
 */

public class ViewReceiptsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RVAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view, container, false);

        mRecyclerView = v.findViewById(R.id.viewFragment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        update();

        return v;
    }

    private void update() {
        List<Receipt> receipts = AccountRegister.get(getActivity()).getReceipts();

        if (mAdapter == null) {
            mAdapter = new RVAdapter();
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class RVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mReceiptImage;
        TextView mTitle;
        TextView mCategory;

        public RVHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.receipt_item, parent, false));

            mTitle = itemView.findViewById(R.id.txt_title);
            mCategory = itemView.findViewById(R.id.txt_category);
            mReceiptImage = itemView.findViewById(R.id.receipt_image);
            itemView.setOnClickListener(this);
        }

        public void bind(Receipt receipt) {
            mTitle.setText(receipt.getTitle());
            mCategory.setText(receipt.getCategory());
        }

        @Override
        public void onClick(View v) {

        }
    }

    private class RVAdapter extends RecyclerView.Adapter<RVHolder> {
        AccountRegister register = AccountRegister.get(getActivity());
        List<Receipt> mReceipts = register.getReceipts();

        @NonNull
        @Override
        public RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            return new RVHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RVHolder holder, int position) {
            Receipt receipt = mReceipts.get(position);
            holder.bind(receipt);
        }

        @Override
        public int getItemCount() {
            Log.i("VIEWRECEIPTS", "mReceipts size is: " + mReceipts.size());
            return mReceipts.size();
        }
    }
}
