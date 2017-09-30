package com.cherepanov.myapplication.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.cherepanov.myapplication.db.tables.RemindTable;
import com.cherepanov.myapplication.model.Remind;

/**
 * Created by Денис on 01.10.2017.
 */

public class RemindCursorWrapper extends CursorWrapper {
    public RemindCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Remind getCrime() {
        String uuidString = getString(getColumnIndex(RemindTable.Cols.UUID));
        String title = getString(getColumnIndex(RemindTable.Cols.TITLE));
        String desc = getString(getColumnIndex(RemindTable.Cols.DESCRIPTION));

        Remind remind = new Remind(title, desc);

        return remind;
    }
}
