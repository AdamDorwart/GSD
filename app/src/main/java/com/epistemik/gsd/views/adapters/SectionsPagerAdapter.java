package com.epistemik.gsd.views.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epistemik.gsd.R;
import com.epistemik.gsd.models.InboxModel;
import com.epistemik.gsd.views.fragments.InboxFragment;

import java.util.Locale;

/**
 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private InboxModel mInboxModel;

    public SectionsPagerAdapter(FragmentManager fm, Context context, InboxModel inboxModel) {
        super(fm);
        mContext = context;
        mInboxModel = inboxModel;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return InboxFragment.newInstance(mInboxModel);
            case 1:
                return InboxFragment.newInstance(mInboxModel);
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        Resources res = mContext.getResources();
        switch (position) {
            case 0:
                return String.format(res.getString(R.string.inbox_tab_title), mInboxModel.inboxItems.size());
            case 1:
                return res.getString(R.string.project_tab_title).toUpperCase(l);
        }
        return null;
    }
}