package edu.ccm.tstites.personalexpenditures.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ccm.tstites.personalexpenditures.R;

/**
 * Created by tstites on 4/20/2018.
 */

public class ViewReceiptsFragment extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view, container, false);

        recyclerView = v.findViewById(R.id.viewFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }
}
