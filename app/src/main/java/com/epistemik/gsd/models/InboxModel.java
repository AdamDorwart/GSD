package com.epistemik.gsd.models;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by adam on 1/2/15.
 */
public class InboxModel {
    private List<InboxItemModel> mInboxItems;
    private InboxDataSource mDataSource;

    public InboxModel(Context context) {
        mDataSource = new InboxDataSource(context);
        try {
            mDataSource.open();
        } catch (SQLException e) {
            // Bad stuff
        }
        mInboxItems = mDataSource.getAllInboxItems();
    }

    public InboxItemModel get(int position) {
        return mInboxItems.get(position);
    }

    public boolean update(int position, InboxItemModel item) {

    }

    public boolean create(InboxItemModel item) {
        mInboxItems.add(item);
        mDataSource.createInboxItem(item);
    }
}
