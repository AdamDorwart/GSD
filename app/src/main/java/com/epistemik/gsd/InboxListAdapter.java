package com.epistemik.gsd;

import android.provider.Telephony;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by adam on 12/30/14.
 */
public class InboxListAdapter extends RecyclerView.Adapter<InboxListAdapter.ViewHolder> {

    private List<InboxItemModel> items;

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

    public InboxListAdapter(List<InboxItemModel> modelData) {
        if (modelData == null) {
            throw new IllegalArgumentException("Inbox item data shouldn't be null");
        }
        this.items = modelData;
    }

    @Override
    public InboxListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.inbox_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InboxItemModel inboxItem = items.get(position);
        holder.mItemTitle.setText(inboxItem.title);
        holder.mItemSubtitle.setText(inboxItem.subtitle);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
