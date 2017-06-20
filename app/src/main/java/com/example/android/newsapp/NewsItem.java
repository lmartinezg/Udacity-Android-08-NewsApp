package com.example.android.newsapp;

public class NewsItem {

    private String mId;
    private String mSectionName;
    private String mWebPublicationDate;
    private String mWebTitle;
    private String mWebUrl;

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
