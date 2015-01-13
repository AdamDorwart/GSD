package com.epistemik.gsd.views;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.epistemik.gsd.R;
import com.epistemik.gsd.models.InboxItemModel;
import com.epistemik.gsd.models.InboxModel;

public class ImpulseActivity extends ActionBarActivity {
    public final static String STATE_KEY = "com.epistemik.gsd.impulse.state";
    public final static int STATE_NEW = 0;
    public final static int STATE_EDIT = 1;
    public final static int STATE_VIEW = 2;
    public final static String ITEM_KEY = "com.epistemik.gsd.impulse.item";

    private int mItemPosition;
    private InboxModel mInboxModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mInboxModel == null) {
            mInboxModel = new InboxModel(getApplicationContext());
        }

        int state = getIntent().getIntExtra(STATE_KEY, 0);
        switch(state) {
            case STATE_NEW:
                setContentView(R.layout.activity_edit_impulse);
                break;
            case STATE_EDIT:
                setContentView(R.layout.activity_edit_impulse);
                mItemPosition = getIntent().getIntExtra(ITEM_KEY, 0);
                // Set Text
                EditText itemTitleEdit = (EditText) findViewById(R.id.edit_impulse_title);
                EditText itemDetailEdit = (EditText) findViewById(R.id.edit_impulse_detail);

                InboxItemModel item = mInboxModel.get(mItemPosition);
                itemTitleEdit.setText(item.getTitle());
                itemDetailEdit.setText(item.getDetail());

                break;
            case STATE_VIEW:
                setContentView(R.layout.activity_view_impulse);
                mItemPosition = getIntent().getIntExtra(ITEM_KEY, 0);
                // Set text
                TextView itemTitleView = (TextView) findViewById(R.id.view_impulse_title);
                TextView itemDetailView = (TextView) findViewById(R.id.view_impulse_detail);

                InboxItemModel itemView = mInboxModel.get(mItemPosition);
                itemTitleView.setText(itemView.getTitle());
                itemDetailView.setText(itemView.getDetail());

                break;
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        int state = getIntent().getIntExtra(STATE_KEY, 0);
        switch (state) {
            case STATE_NEW:
            case STATE_EDIT:
                getMenuInflater().inflate(R.menu.menu_edit_impulse, menu);
                return true;
            case STATE_VIEW:
                getMenuInflater().inflate(R.menu.menu_view_impulse, menu);
                return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mInboxModel == null) {
            mInboxModel = new InboxModel(getApplicationContext());
        }
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_done:
                // Save item
                //TODO Need to differentiate between New and Update task
                InboxItemModel newItem = new InboxItemModel();

                EditText itemTitle = (EditText) findViewById(R.id.edit_impulse_title);
                EditText itemDetail = (EditText) findViewById(R.id.edit_impulse_detail);

                newItem.setTitle(itemTitle.getText().toString());
                newItem.setDetail(itemDetail.getText().toString());
                newItem.setPosition(mInboxModel.size());

                mInboxModel.create(newItem);
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_edit:
                Intent intent = new Intent(this, ImpulseActivity.class);
                intent.putExtra(ImpulseActivity.STATE_KEY, ImpulseActivity.STATE_EDIT);
                intent.putExtra(ImpulseActivity.ITEM_KEY, mItemPosition);
                startActivity(intent);
                return true;
            case R.id.action_delete:
                // Delete item
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
