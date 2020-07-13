package com.example.android.newsapp;

public class News {
    String mTitle;
    String mDate;
    String mSection;
    String mUrl;
    String mAuthor ;

    public News(String mTitle, String mDate, String mSection,String mUrl,String mAuthor) {
        this.mTitle = mTitle;
        this.mDate = mDate;
        this.mSection = mSection;
        this.mUrl = mUrl;
        this.mAuthor = mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmSection() {
        return mSection;
    }

    public String getmUrl() { return mUrl; }

    public String getmAuthor() { return mAuthor; }
}
