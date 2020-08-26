package com.example.basicapp.Data;

public class ContentData {
    private int mID;
    private String mContent;
    private String mMoney;

    public ContentData() {
    }

    public ContentData(int mID, String mContent, String mMoney) {
        this.mID = mID;
        this.mContent = mContent;
        this.mMoney = mMoney;
    }

    public ContentData(String mContent, String mMoney) {
        this.mContent = mContent;
        this.mMoney = mMoney;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmMoney() {
        return mMoney;
    }

    public void setmMoney(String mMoney) {
        this.mMoney = mMoney;
    }

}
