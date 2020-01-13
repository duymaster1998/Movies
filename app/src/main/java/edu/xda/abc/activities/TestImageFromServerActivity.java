package edu.xda.abc.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import edu.xda.abc.R;


public class TestImageFromServerActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_rating);

//        imageView = findViewById(R.id.imgTest);
//        String uri = "http://192.168.1.16:3000/movie/slided.jpg";
//        Glide.with(this)
//                .load(uri)
//                .into(imageView);
    }
}
