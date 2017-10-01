package com.cherepanov.myapplication.db.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cherepanov.myapplication.db.OrganizerBaseHelper;
import com.cherepanov.myapplication.db.RemindCursorWrapper;
import com.cherepanov.myapplication.model.Remind;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Денис on 30.09.2017.
 */

public class RemindTable {

    public static final String NAME = "remind";

    public static final class Cols {
        public static final String UUID = "uuid";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
    }

    public static final String CREATE_TABLE = "create table " + NAME + "(" +
            "_id integer primary key autoincrement, " +
            Cols.UUID + "," +
            Cols.TITLE + "," +
            Cols.DESCRIPTION + ")";

    public static List<Remind> getRemindList(Context c) {
        List<Remind> list = new ArrayList<>();

        RemindCursorWrapper cursor = queryReminds(null, null, c);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                list.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return list;
    }

    public static int clearDB(Context c) {
        SQLiteDatabase dataBase = new OrganizerBaseHelper(c).getWritableDatabase();
        return dataBase.delete(RemindTable.NAME, null, null);
    }

    public static int deleteRemind(Context c, UUID id) {
        SQLiteDatabase dataBase = new OrganizerBaseHelper(c).getWritableDatabase();
        Cursor cursor = dataBase.query(RemindTable.NAME, null, Cols.UUID + " = ?", /*new String[]{id.toString()}*/ new String[]{"a3679777-5b75-4974-9607-87bb9197b6c7"}, null, null, null);
        int k = dataBase.delete(RemindTable.NAME, Cols.UUID + " = ?", new String[]{id.toString()});
        dataBase.close();
        return k;
    }

    public static void addRemind(Remind remind, Context context) {
        ContentValues values = getContentValues(remind);

        SQLiteDatabase dataBase = new OrganizerBaseHelper(context).getWritableDatabase();
        dataBase.insert(RemindTable.NAME, null, values);
    }

    private static ContentValues getContentValues(Remind remind) {
        ContentValues values = new ContentValues();
        values.put(Cols.UUID, remind.getId().toString());
        values.put(Cols.TITLE, remind.getTitle());
        values.put(Cols.DESCRIPTION, remind.getDescription());

        return values;
    }

    private static RemindCursorWrapper queryReminds(String whereClause, String[] whereArgs, Context context) {
        SQLiteDatabase dataBase = new OrganizerBaseHelper(context).getWritableDatabase();
        Cursor cursor = dataBase.query(
                RemindTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new RemindCursorWrapper(cursor);
    }
}
