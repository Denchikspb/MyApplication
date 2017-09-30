package com.cherepanov.myapplication.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.cherepanov.myapplication.R;
import com.cherepanov.myapplication.db.OrganizerBaseHelper;
import com.cherepanov.myapplication.db.tables.RemindTable;
import com.cherepanov.myapplication.model.Remind;

public class NewRemindActivity extends AppCompatActivity {

    private EditText remindTitle;
    private EditText remindDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_remind);

        remindTitle = (EditText) findViewById(R.id.add_remind_title);
        remindDescription = (EditText) findViewById(R.id.add_remind_description);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_remind, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_save:
                saveResult();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveResult() {
        Remind remind = new Remind(remindTitle.toString(), remindDescription.toString());
        RemindTable.addRemind(remind, getApplicationContext());
    }
}
