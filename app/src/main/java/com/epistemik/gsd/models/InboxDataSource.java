package com.epistemik.gsd.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 1/8/15.
 */
public class InboxDataSource implements BaseColumns {
    public static final String TABLE_NAME = "inbox";
    public static final String COLUMN_POS_ID = "position";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DETAIL = "detail";
    private String[] allColumns = { COLUMN_POS_ID, COLUMN_TITLE, COLUMN_DETAIL};

    private SQLiteDatabase mDb;
    private DbHelper mDbHelper;

    public InboxDataSource(Context context) {
        mDbHelper = new DbHelper(context);
    }

    public void open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public void createInboxItem(InboxItemModel item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_POS_ID, item.getPosition());
        values.put(COLUMN_TITLE, item.getTitle());
        values.put(COLUMN_DETAIL, item.getDetail());
        mDb.insert(TABLE_NAME, null, values);
    }

    public void deleteInboxItem(InboxItemModel item) {

    }

    public InboxItemModel getInboxItem(int position) {
        Cursor cursor = mDb.query(TABLE_NAME, allColumns, COLUMN_POS_ID + " = " + position, null, null, null, null);
        cursor.moveToFirst();
        InboxItemModel item = cursorToItem(cursor);
        cursor.close();
        return item;

    }

    public List<InboxItemModel> getAllInboxItems() {
        List<InboxItemModel> items = new ArrayList<InboxItemModel>();
        Cursor cursor = mDb.query(TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            items.add( cursorToItem(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    public void updateInboxItem(InboxItemModel item) {
        
    }

    private InboxItemModel cursorToItem(Cursor cursor) {
        InboxItemModel item = new InboxItemModel();
        item.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        item.setDetail(cursor.getString(cursor.getColumnIndex(COLUMN_DETAIL)));
        item.setPosition(cursor.getInt(cursor.getColumnIndex(COLUMN_POS_ID)));
        return item;
    }
}
