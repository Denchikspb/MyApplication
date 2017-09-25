package com.cherepanov.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cherepanov.myapplication.R;

/**
 * Created by Денис on 21.09.2017.
 */

public class StopwatchFragment extends Fragment {

    private View view;
    private TextView timeTV;
    private Button startBtn;
    private Button stopBtn;
    private Button resetBtn;

    private int seconds;
    private boolean isRunning;

    public static StopwatchFragment getInstance() {
        Bundle args = new Bundle();
        StopwatchFragment fragment = new StopwatchFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stopwatch_fragment, container, false);

        startBtn = (Button) view.findViewById(R.id.start_btn);
        stopBtn = (Button) view.findViewById(R.id.stop_btn);
        resetBtn = (Button) view.findViewById(R.id.reset_btn);

        setupListener();
        runTimer();
        return view;
    }

    private void setupListener() {
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStartTimer();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });
    }

    private void onStartTimer() {
        isRunning = true;
    }

    private void stopTimer() {
        isRunning = false;
    }

    private void resetTimer() {
        isRunning = false;
        seconds = 0;
    }

    private void runTimer() {
        final TextView timeView = (TextView) view.findViewById(R.id.start_time_tv);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d",
                        hours, minutes, secs);
                timeView.setText(time);
                if (isRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

}
