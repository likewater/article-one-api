package com.likewater.articleone.models;

public class Rep {

    private String mName;
    private String mRole;
    private String mParty;
    private String mApiUri;

    public Rep(String name, String role, String party, String apiUri){
        this.mName = name;
        this.mRole = role;
        this.mParty = party;
        this.mApiUri = apiUri;
    }

    public String getName(){
        return mName;
    }

    public String getRole(){
        return mRole;
    }

    public String getParty(){
        return mParty;
    }

    public String getApiUri(){
        return mApiUri;
    }

}
