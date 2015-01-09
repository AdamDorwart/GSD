package com.epistemik.gsd.views;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.epistemik.gsd.R;

public class ImpulseActivity extends ActionBarActivity {
    public final static String STATE_KEY = "com.epistemik.gsd.impulse.state";
    public final static int STATE_NEW = 0;
    public final static int STATE_EDIT = 1;
    public final static int STATE_VIEW = 2;
    public final static String ITEM_KEY = "com.epistemik.gsd.impulse.item";

    private int mItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int state = getIntent().getIntExtra(STATE_KEY, 0);
        switch(state) {
            case STATE_NEW:
                setContentView(R.layout.activity_edit_impulse);
                break;
            case STATE_EDIT:
                setContentView(R.layout.activity_edit_impulse);
                mItemPosition = getIntent().getIntExtra(ITEM_KEY, 0);
                // Set text
                break;
            case STATE_VIEW:
                setContentView(R.layout.activity_view_impulse);
                mItemPosition = getIntent().getIntExtra(ITEM_KEY, 0);
                // Set text
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
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_done:
                // Save item
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
