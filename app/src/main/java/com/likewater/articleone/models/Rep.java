package com.likewater.articleone.models;

import org.parceler.Parcel;

@Parcel
public class Rep {

    private String name;
    private String role;
    private String party;
    private String apiUri;
    //String index;
    private String pushId;

    public Rep() {}

    public Rep(String name, String role, String party, String apiUri){
        this.name = name;
        this.role = role;
        this.party = party;
        this.apiUri = apiUri;
        //this.index = "not_specified";
    }

    public String getName(){
        return name;
    }

    public String getRole(){
        return role;
    }

    public String getParty(){
        return party;
    }

    public String getApiUri(){
        return apiUri;
    }

    //public String getIndex() {return index; }

//    public void setIndex(String index) {
//        this.index = index;
//    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

}
