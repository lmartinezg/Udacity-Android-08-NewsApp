package com.example.android.newsapp;

public class NewsItem {

    // Suggestion:
    // These class fields should be final to final immutability.
    final private String mId;
    final private String mSectionName;
    final private String mWebPublicationDate;
    final private String mWebTitle;
    final private String mWebUrl;

    public NewsItem(String id, String sectionName, String
            webPublicationDate, String webTitle, String webUrl){

        this.mId = id;
        this.mSectionName = sectionName;
        this.mWebPublicationDate = webPublicationDate;
        this.mWebTitle = webTitle;
        this.mWebUrl = webUrl;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getWebPublicationDate() {
        return mWebPublicationDate;
    }

    public String getWebTitle() {
        return mWebTitle;
    }

    public String getWebUrl() {
        return mWebUrl;
    }
}
