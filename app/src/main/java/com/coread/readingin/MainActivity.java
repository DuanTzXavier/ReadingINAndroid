package com.coread.readingin;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    int Duration = 400;

    float x = 0;
    int ty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager();
        getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

        initView();
    }

    private void initView() {
        final ImageView originView = (ImageView) findViewById(R.id.image_cicici);
        originView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RelativeLayout rootView = new RelativeLayout(getBaseContext());
                rootView.setBackgroundColor(Color.WHITE);


                int[] location = new int[2];
                originView.getLocationOnScreen(location);
                ((ViewGroup)getWindow().getDecorView()).addView(rootView);
                final ImageView fullImage = new ImageView(getBaseContext());

                fullImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                fullImage.setImageResource(R.drawable.tsssttt);

                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(originView.getWidth(), originView.getHeight());
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                layoutParams.topMargin = location[1];
                rootView.addView(fullImage, layoutParams);



                fullImage.post(new Runnable() {
                    @Override
                    public void run() {
                        final int fh = originView.getMeasuredHeight();
                        final int fw = originView.getMeasuredWidth();
                        final int th = MainActivity.this.getWindowManager().getDefaultDisplay().getHeight();
                        final int tw = MainActivity.this.getWindowManager().getDefaultDisplay().getWidth();

                        int[] location = new int[2];
                        originView.getLocationOnScreen(location);



                        AnimationSet set = new AnimationSet(true);
                        set.addAnimation(getAR());
                        float w = (float)tw/(float)fw;
                        float h = (float)th/(float)fh;
                        x = w < h ? w : h;

                        ty = (th - fh)/2 - location[1];
                        set.addAnimation(getScale(x + 1));
                        set.addAnimation(getTrans(ty));
                        set.setFillAfter(true);
                        fullImage.startAnimation(set);
                    }
                });
                rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AnimationSet set = new AnimationSet(true);
                        set.addAnimation(getARK());
                        set.addAnimation(getScaleK(x + 1));
                        set.addAnimation(getTransK(ty));
                        set.setFillAfter(true);
                        fullImage.startAnimation(set);
                        set.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                ((ViewGroup)getWindow().getDecorView()).removeView(rootView);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    }
                });
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    public Animation getAR(){
        RotateAnimation rotate  = new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(Duration);//设置动画持续周期
        return rotate;
    }

    public Animation getTrans(int toY){
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,toY);
//起始x轴，最终x轴，起始y轴，最终y轴
        translateAnimation.setDuration(Duration);

        return translateAnimation;
    }

    public Animation getScale(float scale){
        ScaleAnimation scaleAnimation = new ScaleAnimation(1,scale,1,scale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);//x轴0倍，x轴1倍，y轴0倍，y轴1倍
        scaleAnimation.setDuration(Duration);
        return scaleAnimation;
    }

    public Animation getARK(){
        RotateAnimation rotate  = new RotateAnimation(90f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(Duration);//设置动画持续周期
        return rotate;
    }

    public Animation getTransK(int toY){
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,toY,0);
//起始x轴，最终x轴，起始y轴，最终y轴
        translateAnimation.setDuration(Duration);

        return translateAnimation;
    }

    public Animation getScaleK(float scale){
        ScaleAnimation scaleAnimation = new ScaleAnimation(scale,1,scale,1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);//x轴0倍，x轴1倍，y轴0倍，y轴1倍
        scaleAnimation.setDuration(Duration);
        return scaleAnimation;
    }
}
