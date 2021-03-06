package edu.ccm.tstites.personalexpenditures.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import edu.ccm.tstites.personalexpenditures.CoreObjects.AccountRegister;
import edu.ccm.tstites.personalexpenditures.CoreObjects.Paycheck;
import edu.ccm.tstites.personalexpenditures.CoreObjects.Receipt;
import edu.ccm.tstites.personalexpenditures.Interfaces.Transactions;
import edu.ccm.tstites.personalexpenditures.R;

/**
 * Created by tstites on 4/20/2018.
 */

public class ViewReceiptsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RVAdapter mAdapter;

    OnListItemSelected mCallback;

    public interface OnListItemSelected {
        void onItemSelected(int position, String type, UUID id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AppCompatActivity activity = (AppCompatActivity) context;

        try {
            mCallback = (OnListItemSelected) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnListItemSelected");
        }
    }

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
        TextView mDate;
        TextView mType;
        TextView mTitle;
        TextView mCategory;

        public RVHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.receipt_item, parent, false));

            mTitle = itemView.findViewById(R.id.txt_title);
            mCategory = itemView.findViewById(R.id.txt_category);
            mReceiptImage = itemView.findViewById(R.id.receipt_image);
            mDate = itemView.findViewById(R.id.txt_date);
            mType = itemView.findViewById(R.id.txt_type);
            itemView.setOnClickListener(this);
        }

        public void bind(Transactions transaction) {
            DateFormat dateFormat = DateFormat.getDateInstance();
            String date = dateFormat.format(transaction.getDate());

            mDate.setText(date);
            mType.setText(transaction.getType());
            mTitle.setText(transaction.getTitle());
            mCategory.setText(transaction.getCategory());
        }

        @Override
        public void onClick(View v) {
            List<Receipt> receipts = AccountRegister.get(getActivity()).getReceipts();
            List<Paycheck> paychecks = AccountRegister.get(getActivity()).getPaychecks();
            List<Transactions> transactions = getOrganizedTransactions(receipts, paychecks);

            int position = RVHolder.this.getAdapterPosition();
            String type = mType.getText().toString();
            UUID id = transactions.get(position).getUUID();
            mCallback.onItemSelected(position, type, id);
        }
    }

    private class RVAdapter extends RecyclerView.Adapter<RVHolder> {
        AccountRegister register = AccountRegister.get(getActivity());
        List<Receipt> mReceipts = register.getReceipts();
        List<Paycheck> mPaychecks = register.getPaychecks();
        List<Transactions> mTransactions = getOrganizedTransactions(mReceipts, mPaychecks);

        @NonNull
        @Override
        public RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            return new RVHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RVHolder holder, int position) {
            holder.bind(mTransactions.get(position));
        }

        @Override
        public int getItemCount() {
            Log.i("VIEWRECEIPTS", "mTransactions size is: " + mTransactions.size());
            return mTransactions.size();
        }
    }

    private List<Transactions> getOrganizedTransactions(List<Receipt> receipts, List<Paycheck> paychecks) {
        List<Transactions> organizedList = new ArrayList<>();

        //Combines receipts and paychecks into one list
        organizedList.addAll(receipts);
        organizedList.addAll(paychecks);

        //Sort organizedList by date in reverse order
        Collections.sort(organizedList, Collections.<Transactions>reverseOrder());

        return organizedList;
    }
}
