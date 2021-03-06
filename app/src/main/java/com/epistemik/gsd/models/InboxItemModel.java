package com.epistemik.gsd.models;

/**
 * Created by adam on 12/30/14.
 */
public class InboxItemModel {
    public static final int SUBTITLE_LENGTH = 40;

    public String mTitle;
    private String mSubtitle;
    public String mDetail;
    public int mPosition;
    public long mId;

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDetail(String detail) {
        mDetail = detail;
        if (detail.length() > SUBTITLE_LENGTH) {
            mSubtitle = detail.substring(0, SUBTITLE_LENGTH - 3) + "...";
        } else {
            mSubtitle = detail;
        }
    }

    public void setID(long id) {
        mId = id;
    }

    public void setPosition(int position) { mPosition = position;}

    public String getTitle() {
        return mTitle;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public long getID() {
        return mId;
    }

    public int getPosition() { return mPosition; }

    public String getDetail() {
        return mDetail;
    }
}
