package com.epistemik.gsd.views.adapters;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epistemik.gsd.models.InboxItemModel;
import com.epistemik.gsd.R;
import com.epistemik.gsd.models.InboxModel;

import java.util.List;

/**
 * Created by adam on 12/30/14.
 */
public class InboxListAdapter extends RecyclerView.Adapter<InboxListAdapter.ViewHolder> {

    private InboxModel items;
    private RecyclerView mRecyclerView;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int itemPosition = mRecyclerView.getChildPosition(v);
            InboxItemModel inboxItem = items.inboxItems.get(itemPosition);
            // Start View Impulse Activity
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mItemTitle;
        public TextView mItemSubtitle;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mItemTitle = (TextView) itemView.findViewById(R.id.inbox_item_title);
            mItemSubtitle = (TextView) itemView.findViewById(R.id.inbox_item_subtitle);
        }

        @Override
        public void onClick(View view) {
            mItemTitle.setText("Tapped");
        }
    }

    public InboxListAdapter(InboxModel modelData, RecyclerView recyclerView) {
        if (modelData == null) {
            throw new IllegalArgumentException("Inbox item data shouldn't be null");
        }
        this.items = modelData;
        mRecyclerView = recyclerView;
    }

    @Override
    public InboxListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.inbox_item, parent, false);
        v.setOnClickListener(mOnClickListener);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InboxItemModel inboxItem = items.inboxItems.get(position);
        holder.mItemTitle.setText(inboxItem.title);
        holder.mItemSubtitle.setText(inboxItem.subtitle);
    }

    @Override
    public int getItemCount() {
        return items.inboxItems.size();
    }
}
