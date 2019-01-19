package com.typicalgeek.shouldi;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String[] affirmative = {"Yes", "Yeah", "Go for it", "Definitely", "Sure",
            "It's probably a horrible idea...but okay", "Yup", "Why not?", "Yeah, whatever", "Aye"};
    private static final String[] negative = {"No", "Nope", "Don't even think about it", "Sure",
            "Definitely not", "It's a horrible idea", "Probably not", "Ummm...no?"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button ask = findViewById(R.id.btnPetitioner);
        final TextView receive = findViewById(R.id.tvOracle);
        final int SUSPENSE = 500;
        float whoa = 0.0f, poof = 1.0f;
        final Animation in = new AlphaAnimation(whoa, poof);
        in.setDuration(SUSPENSE);
        in.setStartOffset(SUSPENSE);
        final Animation out = new AlphaAnimation(poof, whoa);
        out.setDuration(SUSPENSE);
        out.setStartOffset(SUSPENSE);
        ask.setVisibility(View.VISIBLE);
        ask.startAnimation(in);
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ask.setOnClickListener(null);
                ask.startAnimation(out);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ask.setVisibility(View.GONE);
                        if (youThinkSo()) receive.setText(affirmative[pickPoison(true)]);
                        else receive.setText(negative[pickPoison(false)]);
                        receive.setVisibility(View.VISIBLE);
                        receive.startAnimation(in);
                    }
                }, SUSPENSE);
            }
        });
    }

    private int pickPoison(boolean eitherOr) {
        Random r = new Random();
        if (eitherOr) return r.nextInt(affirmative.length);
        else return r.nextInt(negative.length);
    }

    private boolean youThinkSo() {
        return new Random().nextBoolean();
    }
}