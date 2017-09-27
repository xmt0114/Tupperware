package com.example.xiaomutian.tupperwareproductbrowser;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class EditActivity extends AppCompatActivity {
    private static final String TAG = "EditActivity";
    private ImageView m_ivPicture;
    private Button m_btCommit;
    private Button m_btCancel;

    private EditText m_etName;
    private EditText m_etPrice;
    private EditText m_etDescription;

    private static final String IMAGE_URI = "image_uri";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        m_ivPicture = (ImageView)findViewById(R.id.image_browse);

        Intent intent = getIntent();
        Uri imageUri = (Uri) intent.getParcelableExtra(IMAGE_URI);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
            m_ivPicture.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        m_etName = (EditText)findViewById(R.id.edit_product_name);
        m_etPrice = (EditText)findViewById(R.id.edit_product_price);
        m_etDescription = (EditText)findViewById(R.id.edit_product_des);

        m_btCommit = (Button)findViewById(R.id.button_commit);
        m_btCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        m_btCancel = (Button)findViewById(R.id.button_cancel);
        m_btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public static boolean startEditActivity(Context context, Uri imageUri) {
        if (context == null || imageUri == null) {
            Log.w(TAG, "startEditActivity: parameter error");
            return false;
        }
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra(IMAGE_URI, imageUri);
        context.startActivity(intent);

        return true;
    }

    public static boolean startEditActivityForResult(Context context, int requestCode, Uri imageUri) {
        if (context == null || imageUri == null) {
            Log.w(TAG, "startEditActivity: parameter error");
            return false;
        }

        if (context instanceof SelectOprationsActivity) {
            SelectOprationsActivity myselfActivity = (SelectOprationsActivity)context;
            Intent intent = new Intent(myselfActivity, EditActivity.class);
            intent.putExtra(IMAGE_URI, imageUri);
            myselfActivity.startActivityForResult(intent, requestCode);
        } else {
            Log.w(TAG, "startEditActivity: parameter error 2" );
            return false;
        }

        return true;
    }
}
