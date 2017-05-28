package com.daniel.weighttracker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.daniel.weighttracker.LogHelper.Log;
import static com.daniel.weighttracker.MainActivity.weights;

/**
 * Created by Daniel on 5/27/2017.
 */

public class WeightActivity extends AppCompatActivity
{
    ImageView imageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap image;
    TextView weightView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_activity);

        imageView = (ImageView ) findViewById(R.id.imageView5);
        weightView = ( TextView ) findViewById(R.id.editText2);

        if( weights == null )
        {

        }
        else {
            imageView.setImageBitmap( weights.getImage());
            weightView.setText(weights.getWeight());
        }

    }

    public void saveWeightObject(View view)
    {

        if ( imageView == null ) return;

        String weight = weightView.getEditableText().toString();

        image = (( BitmapDrawable ) imageView.getDrawable() ).getBitmap();


        weights = new WeightRecord(image, weight);

        Toast.makeText(this, "Weight Recorded", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);




        Log(weight);

    }

    public void takePicture(View view)
    {

        Log("Take picture clicked");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Bitmap imageBitmap = data.getParcelableExtra("data");

            if(imageBitmap  == null )
            {
                Log( "bitmap is null");
            }
            else
            {
                Log("bitmap is Not null");
                Log( imageView == null  ? "Null image view" : "not null iamgeView");

            }
            imageView.setImageBitmap(imageBitmap);

        }

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
