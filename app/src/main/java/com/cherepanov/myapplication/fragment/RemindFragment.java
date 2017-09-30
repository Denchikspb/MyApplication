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

import com.cherepanov.myapplication.R;
import com.cherepanov.myapplication.activity.MainActivity;
import com.cherepanov.myapplication.activity.NewRemindActivity;
import com.cherepanov.myapplication.adapter.RemindAdapter;
import com.cherepanov.myapplication.db.tables.RemindTable;
import com.cherepanov.myapplication.model.Remind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 22.09.2017.
 */

public class RemindFragment extends Fragment {

    private View view;
    private Context context;
    private FloatingActionButton addRemindFABtn;

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
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RemindAdapter(createMockListRemind()));
        addRemindFABtn = (FloatingActionButton) view.findViewById(R.id.add_remind_fab);
        addRemindFABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewRemindActivity.class));
            }
        });
        return view;
    }

    private List<Remind> createMockListRemind() {
        List list = RemindTable.getRemindList(getActivity());
//        String description = "Какое то описание заметки и пускай оно будет длинным";
//        list.add(new Remind("Купить хлеб", description));
//        list.add(new Remind("Сделать презентацию", description));

        return list;
    }
}
