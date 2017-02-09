package com.example.hn.imagesearchforgiphy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.hn.imagesearchforgiphy.view.MainActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_activity);

        ImageView giphyView = (ImageView) findViewById(R.id.giphyView);

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(giphyView);
        Glide.with(this)
                .load(R.mipmap.logo_buildtext_white_forever)
                .into(imageViewTarget);

        /**
         * stay a while
         * */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, 3000);
    }
}
