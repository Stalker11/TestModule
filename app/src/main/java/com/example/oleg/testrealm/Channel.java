package com.example.oleg.testrealm;

import io.realm.RealmObject;

/**
 * Created by Oleg on 02.08.2017.
 */

public class Channel extends RealmObject{
    private String channelName;
    private String channelLink;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelLink() {
        return channelLink;
    }

    public void setChannelLink(String channelLink) {
        this.channelLink = channelLink;
    }
}
