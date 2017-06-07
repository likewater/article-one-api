package com.likewater.articleone;

public class Rep {
    private String mFirstName;
    private String mLastName;
    private String mState;
    private String mParty;

    public Rep(String firstName, String lastName, String state, String party){
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mState = state;
        this.mParty = party;
    }

    public String getFirstName(){
        return mFirstName;
    }

    public String getLastName(){
        return mLastName;
    }

    public String getState(){
        return mState;
    }

    public String getParty(){
        return mParty;
    }
//Example json object
//    {
//        "id": "A000360",
//            "api_uri":"https://api.propublica.org/congress/v1/members/A000360.json",
//            "first_name": "Lamar",
//            "middle_name": "",
//            "last_name": "Alexander",
//            "party": "R",
//            "leadership_role": "",
//            "twitter_account": "SenAlexander",
//            "facebook_account": "senatorlamaralexander",
//            "youtube_account": "lamaralexander",
//            "govtrack_id": "300002",
//            "cspan_id": "5",
//            "votesmart_id": "15691",
//            "icpsr_id": "40304",
//            "crp_id": "N00009888",
//            "google_entity_id": "/m/01rbs3",
//            "url": "https://www.alexander.senate.gov/public/index.cfm/home",
//            "rss_url": "https://www.alexander.senate.gov/public/?a=RSS.Feed",
//            "contact_form": "http://www.alexander.senate.gov/public/index.cfm?p=Email",
//            "domain": "",
//            "in_office": "true",
//            "dw_nominate": "0.323",
//            "ideal_point": "",
//            "seniority": "15",
//            "next_election": "2020",
//            "total_votes": "139",
//            "missed_votes": "4",
//            "total_present": "0",
//            "ocd_id": "ocd-division/country:us/state:tn",
//            "office": "455 Dirksen Senate Office Building",
//            "phone": "202-224-4944",
//            "fax": "202-228-3398",
//            "state": "TN",
//            "senate_class": "2",
//            "state_rank": "senior",
//            "lis_id": "S289"
//            ,"missed_votes_pct": "2.88",
//            "votes_with_party_pct": "99.26"
//    },
//
}
