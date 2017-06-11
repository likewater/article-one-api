package com.likewater.articleone.models;

import org.parceler.Parcel;

@Parcel
public class RepDetail {
    private String mCongress;
    private String mLastName;
    private String mFirstName;
    private String mTitle;
    private String mState;
    private String mDistrict;
    private String mCurrentParty;
    private String mUrl;
    private String mTwitterAccount;
    private String mFacebookAccount;
    private String mYoutubeAccount;
    private String mPhone;

    public RepDetail(){}

    public RepDetail(String congress, String lastName, String firstName, String title,
                     String state, String district, String currentParty, String url,
                     String twitterAccount, String facebookAccount, String youtubeAccount,
                     String phone){
        this.mCongress = congress;
        this.mLastName = lastName;
        this.mFirstName = firstName;
        this.mTitle = title;
        this.mState = state;
        this.mDistrict = district;
        this.mCurrentParty = currentParty;
        this.mUrl = url;
        this.mTwitterAccount = twitterAccount;
        this.mFacebookAccount = facebookAccount;
        this.mYoutubeAccount = youtubeAccount;
        this.mPhone = phone;
    }

    public String getCongress(){
        return mCongress;
    }

    public String getLastName(){
        return mLastName;
    }

    public String getFirstName(){
        return mFirstName;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getState(){
        return mState;
    }

    public String getDistrict(){
        return mDistrict;
    }

    public String getCurrentParty(){
        return mCurrentParty;
    }

    public String getUrl(){
        return mUrl;
    }

    public String getTwitterAccount(){
        return mTwitterAccount;
    }

    public String getFacebookAccount(){
        return mFacebookAccount;
    }

    public String getYoutubeAccount(){
        return mYoutubeAccount;
    }

    public String getPhone(){
        return mPhone;
    }
}
