package com.example.oleg.testrealm;

import android.widget.ImageView;

import java.io.File;
import java.io.InputStream;

import io.realm.RealmObject;

/**
 * Created by Oleg on 09.07.2017.
 */

public class Image extends RealmObject {
    private byte[] file;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
