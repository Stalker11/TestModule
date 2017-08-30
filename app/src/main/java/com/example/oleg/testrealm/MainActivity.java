package com.example.oleg.testrealm;

import android.content.res.AssetManager;
import android.database.Observable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
private Realm realm;
    private Button button;
    private ImageView imageView;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.image);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        InputStream stream;
        AssetManager man = getAssets();
        try {
             stream = man.open("chs7.jpg");
            realm.beginTransaction();
            ByteArrayOutputStream ss = new ByteArrayOutputStream();
            Bitmap bit = BitmapFactory.decodeStream(stream);
            bit.compress(Bitmap.CompressFormat.PNG, 100, ss);
            byte[] byteArray = ss.toByteArray();
            Image image = realm.createObject(Image.class);
            Channel ch = new Channel();
            ch.setChannelLink("link");
            ch.setChannelName("name");
            RealmList<Channel> channelList = new RealmList<>();
            channelList.add(ch);

            PlayListModel list = realm.createObject(PlayListModel.class);
            list.getChannels().add(ch);
            list.setPlayListName("Second");
            image.setFile(byteArray);
            realm.commitTransaction();
            Log.d(TAG, "onCreate: created");
        } catch (IOException e) {
            e.printStackTrace();
        }
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               realm.beginTransaction();
               RealmResults<Image> in = realm.where(Image.class).findAll();
               for (Image im:in) {
                   Bitmap bit = BitmapFactory.decodeByteArray(im.getFile(),0,im.getFile().length);
                   imageView.setImageBitmap(bit);
                   Log.d(TAG, "onClick: "+im.getFile());
                   button.setText("Was set");
               }
               RealmResults<PlayListModel> res = realm.where(PlayListModel.class).contains("playListName","Second")
                       .findAll();
               Log.d(TAG, "onClick: "+res.size());
               //Log.d(TAG, "onClick: "+res.get(6).getChannels().get(0).getChannelLink());
               realm.commitTransaction();                          }
       });
    }
}
