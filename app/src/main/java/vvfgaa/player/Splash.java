package vvfgaa.player;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    private ImageView circleImageView;
    private ImageView logoImageView;
    private ImageView bgImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        circleImageView = findViewById(R.id.circle);
        logoImageView =findViewById(R.id.logo);
        bgImageView=findViewById(R.id.background);

        starAnimation();



        Integer time = 4000;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                Splash.this.finish();
            }
        }, time);
    }

    private void starAnimation(){
        ObjectAnimator circleAnimator = ObjectAnimator.ofFloat(circleImageView,"scaleX",
                0,3000).setDuration(2000);
        ObjectAnimator circleAnimator2 = ObjectAnimator.ofFloat(circleImageView,"scaleY",
                3000,6000).setDuration(2000);
        circleAnimator.setInterpolator(new AccelerateInterpolator());
        circleAnimator2.setInterpolator(new AccelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator backGroudAnimator=ObjectAnimator.ofFloat(bgImageView,"alpha",
                0,1).setDuration(1500);
        ObjectAnimator logoAnimatorAlpha=ObjectAnimator.ofFloat(logoImageView,"alpha",
                0,1).setDuration(2500);
        ObjectAnimator logoAnimatorSclaeX= ObjectAnimator.ofFloat(logoImageView,"scaleX",
                0,3).setDuration(2500);
        ObjectAnimator logoAnimatorSclaeY= ObjectAnimator.ofFloat(logoImageView,"scaleY",
                0,3).setDuration(2500);


        animatorSet
                .play(circleAnimator)
                .with(circleAnimator2)
                .before(backGroudAnimator)
                .before(logoAnimatorAlpha)
                .before(logoAnimatorSclaeX)
                .before(logoAnimatorSclaeY);

        animatorSet.start();

    }
}
