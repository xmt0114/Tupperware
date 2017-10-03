package com.example.xiaomutian.tupperwareproductbrowser.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.xiaomutian.tupperwareproductbrowser.R;

import org.litepal.LitePal;

import java.io.File;
import java.io.IOException;

public class SelectOprationsActivity extends AppCompatActivity {
    private static final String TAG = "SelectOprationsActivity";
    private static final int TAKE_PHOTO = 1;
    private static final int SHOW_PHOTO = 2;

    private int m_nImageIndex = 0;
    private Uri m_imageUri;

    private Button m_bnBrowseMode;
    private Button m_bnPracticeMode;
    private Button m_bnEditMode;
    private Button m_bnTakePhoto;
    private Button m_bnFromAlbum;


    private ImageView m_ivShowPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_oprations_activity);

        // init dabase
        LitePal.getDatabase();

        m_bnBrowseMode = (Button)findViewById(R.id.button_browse);
        m_bnPracticeMode = (Button)findViewById(R.id.button_practice);
        m_bnEditMode = (Button)findViewById(R.id.button_edit);
        m_bnTakePhoto = (Button)findViewById(R.id.button_take_photo);
        m_bnFromAlbum = (Button)findViewById(R.id.button_from_album);

        m_ivShowPhoto = (ImageView)findViewById(R.id.image_show);

        // TODO: 2017/9/4 other button action coded
        m_bnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: take photo");
                // TODO: 2017/9/4 filename
                File fPhotoImage = new File(getExternalCacheDir(), "Tupperware-" + generatePhotoIndex() + ".jpg");
                try {
                    if (fPhotoImage.exists()) {
                        fPhotoImage.delete();
                    }
                    fPhotoImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (Build.VERSION.SDK_INT >= 24) {
                    m_imageUri = FileProvider.getUriForFile(SelectOprationsActivity.this,
                            "com.example.xiaomutian.tupperware.fileprovider", fPhotoImage);
                } else {
                    m_imageUri = Uri.fromFile(fPhotoImage);
                }

                // start capture
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, m_imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    EditActivity.startEditActivityForResult(SelectOprationsActivity.this, SHOW_PHOTO, m_imageUri);
                }
                else {
                    m_imageUri = null;
                }
                break;
            default:
                break;
        }
    }

    private int generatePhotoIndex() {
        return ++m_nImageIndex;
    }
}
