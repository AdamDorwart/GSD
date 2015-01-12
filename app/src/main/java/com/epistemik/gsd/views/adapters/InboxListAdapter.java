package com.epistemik.gsd.views.adapters;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epistemik.gsd.models.InboxItemModel;
import com.epistemik.gsd.R;
import com.epistemik.gsd.models.InboxModel;
import com.epistemik.gsd.views.ImpulseActivity;
import com.epistemik.gsd.views.fragments.InboxFragment;

import java.util.List;

/**
 * Created by adam on 12/30/14.
 */
public class InboxListAdapter extends RecyclerView.Adapter<InboxListAdapter.ViewHolder> {

    private InboxModel mItems;
    private RecyclerView mRecyclerView;
    private InboxFragment mInboxFragment;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int itemPosition = mRecyclerView.getChildPosition(v);

            // Start View Impulse Activity
            Intent intent = new Intent(mInboxFragment.getActivity(), ImpulseActivity.class);
            intent.putExtra(ImpulseActivity.STATE_KEY, ImpulseActivity.STATE_VIEW);
            intent.putExtra(ImpulseActivity.ITEM_KEY, itemPosition);
            mInboxFragment.startActivity(intent);
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mItemTitle;
        public TextView mItemSubtitle;
        public ViewHolder(View itemView) {
            super(itemView);
            mItemTitle = (TextView) itemView.findViewById(R.id.inbox_item_title);
            mItemSubtitle = (TextView) itemView.findViewById(R.id.inbox_item_subtitle);
        }
    }

    public InboxListAdapter(InboxModel modelData, RecyclerView recyclerView, InboxFragment inboxFragment) {
        if (modelData == null) {
            throw new IllegalArgumentException("Inbox item data shouldn't be null");
        }
        mItems = modelData;
        mRecyclerView = recyclerView;
        mInboxFragment = inboxFragment;
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
        InboxItemModel inboxItem = mItems.get(position);
        holder.mItemTitle.setText(inboxItem.getTitle());
        holder.mItemSubtitle.setText(inboxItem.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
