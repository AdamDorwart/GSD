package com.epistemik.gsd.models;

import android.content.Context;
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

    }

    public void deleteInboxItem(InboxItemModel item) {

    }

    public InboxItemModel getInboxItem(int position) {
        return new InboxItemModel();
    }

    public List<InboxItemModel> getAllInboxItems() {
        return new ArrayList<InboxItemModel>();
    }

    public void updateInboxItem(InboxItemModel item) {

    }
}
