package com.cherepanov.myapplication.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cherepanov.myapplication.receivers.AlarmReceiver;
import com.cherepanov.myapplication.R;

import java.util.Calendar;

/**
 * Created by Денис on 18.09.2017.
 */

public class AlarmClockFragment extends Fragment {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private View view;
    private TextView textView;
    private Button alarmOnBtn;
    private Button alarmOffBtn;

    public static AlarmClockFragment getInstance() {
        Bundle args = new Bundle();
        AlarmClockFragment fragment = new AlarmClockFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.alarm_clock_fragment, container, false);
        alarmTimePicker = (TimePicker) view.findViewById(R.id.timePicker);
        alarmOnBtn = (Button) view.findViewById(R.id.alarm_on_btn);
        alarmOffBtn = (Button) view.findViewById(R.id.alarm_off_btn);
        textView = (TextView)view.findViewById(R.id.time_tv);
        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        setupListener();

        return view;
    }

    private void setupListener() {
        alarmOnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                powerAlarm();
            }
        });
        alarmOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmManager.cancel(pendingIntent);
                setTextTime("Будильник выключен");
            }
        });
    }

    private void powerAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

        int hour = alarmTimePicker.getCurrentHour();
        int minute = alarmTimePicker.getCurrentMinute();

        String minuteStr = String.valueOf(minute);
;
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        if(minute < 10){
            minuteStr = "0" + minuteStr;
        }
        textView.setText("Будильник поставлен на " + hour + ":" + minuteStr);
    }

    private void setTextTime(String s){
        textView.setText(s);
    }

}
