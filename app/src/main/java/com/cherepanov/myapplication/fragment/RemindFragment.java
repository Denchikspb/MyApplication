package com.cherepanov.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cherepanov.myapplication.R;
import com.cherepanov.myapplication.activity.NewRemindActivity;
import com.cherepanov.myapplication.adapter.RemindAdapter;
import com.cherepanov.myapplication.db.tables.RemindTable;
import com.cherepanov.myapplication.model.Remind;

import java.util.List;

/**
 * Created by Денис on 22.09.2017.
 */

public class RemindFragment extends Fragment {

    private static final int CREATE_REMIND = 1;

    private View view;
    private Context context;
    private FloatingActionButton addRemindFABtn;

    private RemindAdapter adapter;
    private RecyclerView recyclerView;

    public static RemindFragment getInstance(Context context) {
        Bundle args = new Bundle();
        RemindFragment fragment = new RemindFragment();
        fragment.setArguments(args);
        fragment.context = context;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.remind_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateUI();
        adapter.setOnDeleteRemindListener(new RemindAdapter.OnDeleteRemindListener() {
            @Override
            public void onDelete() {
                updateUI();
            }
        });
        addRemindFABtn = (FloatingActionButton) view.findViewById(R.id.add_remind_fab);
        addRemindFABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), NewRemindActivity.class), CREATE_REMIND);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CREATE_REMIND){
            updateUI();
        }
    }

    private void updateUI() {
        if (adapter == null) {
            adapter = new RemindAdapter(getRemindFromDb());
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setRemindList(getRemindFromDb());
            adapter.notifyDataSetChanged();
        }
    }

    private List<Remind> getRemindFromDb() {
        return (List) RemindTable.getRemindList(getActivity());
    }
}
