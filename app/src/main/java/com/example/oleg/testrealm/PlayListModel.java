package com.example.oleg.testrealm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Oleg on 02.08.2017.
 */

public class PlayListModel extends RealmObject {
    private String playListName;
    private RealmList<Channel> channels;

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public RealmList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(RealmList<Channel> channels) {
        this.channels = channels;
    }
}
