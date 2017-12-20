package com.example.cuipengyu.satellitemenu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int radius1 = 500;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;
    ImageView iv;
    private List<ImageView> imageViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);
        iv.setOnClickListener(this);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        imageVIewArray();
    }

    private void imageVIewArray() {
        imageViews.add(iv1);
        imageViews.add(iv2);
        imageViews.add(iv3);
        imageViews.add(iv4);
        imageViews.add(iv5);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void closeSectorMenu() {
        int avgAngle = (90 / imageViews.size() - 1);
        for (int i = 0; i < imageViews.size(); i++) {
            int angle = avgAngle * i;
            /*** 此处使用PointF*/
            PointF point = new PointF();
            point.x = (float) Math.cos(angle * (Math.PI / 180)) * radius1;
            /*** 此处y坐标是负的，因为纵坐标向下为正，而我们要在上面显示，可以更改符号看效果*/
            point.y = (float) -Math.sin(angle * (Math.PI / 180)) * radius1;
            ObjectAnimator oax = ObjectAnimator.ofFloat(imageViews.get(i), "translationX", point.x, 0);
            ObjectAnimator oay = ObjectAnimator.ofFloat(imageViews.get(i), "translationY", point.y, 0);
            /*** 此处在平移的基础上加了旋转动画*/
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageViews.get(i), "rotation", 0, 360);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(500);
            /** 使用with连接多个动画*/
            animatorSet.play(oax).with(oay).with(objectAnimator);
            animatorSet.start();
        }

    }

    /**
     * 显示扇形菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void showSectorMenu() {
        int avgAngle = (90 / imageViews.size() - 1);
        for (int i = 0; i < imageViews.size(); i++) {
            int angle = avgAngle * i;
            /*** 此处使用PointF*/
            PointF point = new PointF();
            point.x = (float) Math.cos(angle * (Math.PI / 180)) * radius1;
            /*** 此处y坐标是负的，因为纵坐标向下为正，而我们要在上面显示，大家可以更改符号看效果*/
            point.y = (float) -Math.sin(angle * (Math.PI / 180)) * radius1;
            /**
             * 第一个参数用于指定这个动画要操作的是哪个控件
             * 第二个参数用于指定这个动画要操作这个控件的哪个属性
             * 就是指这个属性值是从哪变到哪
             */
            ObjectAnimator oax = ObjectAnimator.ofFloat(imageViews.get(i), "translationX", 0, point.x);
            ObjectAnimator oay = ObjectAnimator.ofFloat(imageViews.get(i), "translationY", 0, point.y);
            //图标旋转
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageViews.get(i), "rotation", 0, 360);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(500);
            animatorSet.play(oax).with(oay).with(objectAnimator);
            animatorSet.start();
        }
    }

    /**
     * 显示垂直菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void showVerticalMenu() {
        for (int i = 0; i < imageViews.size(); i++) {
            /*** 此处使用PointF*/
            PointF point = new PointF();
            point.x = 0;
            /*** 此处y坐标是负的，因为纵坐标向下为正，而我们要在上面显示，大家可以更改符号看效果*/
            point.y = -(150 * (i + 1));
            ObjectAnimator oax = ObjectAnimator.ofFloat(imageViews.get(i), "translationX", 0, point.x);
            ObjectAnimator oay = ObjectAnimator.ofFloat(imageViews.get(i), "translationY", 0, point.y);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageViews.get(i), "rotation", 0, 360);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(500);
            animatorSet.play(oax).with(oay).with(objectAnimator);
            animatorSet.start();
        }
    }

    /**
     * 关闭垂直菜单
     * 表示注解目标只能够在指定的版本API及以上运行，消除高版本Api在低版本SDK上的报错，
     * 作用上和TargetApi相同，只是在词面上更清楚表达了这是一个建议，而不仅仅是为了消除高版本Api在低版本SDK上的报错。
     * 从官方的表述可以看出更推荐使用RequiresApi替换TargetApi。
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void closeVerticalMenu() {
        for (int i = 0; i < imageViews.size(); i++) {
            /*** 此处使用PointF*/
            PointF point = new PointF();
            point.x = 0;
            /*** 此处y坐标是负的，因为纵坐标向下为正，而我们要在上面显示，大家可以更改符号看效果*/
            point.y = -(150 * (i + 1));
            ObjectAnimator oax = ObjectAnimator.ofFloat(imageViews.get(i), "translationX", 0, point.x);
            ObjectAnimator oay = ObjectAnimator.ofFloat(imageViews.get(i), "translationY", point.y, 0);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageViews.get(i), "rotation", 0, 360);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(500);
            animatorSet.play(oax).with(oay).with(objectAnimator);
            animatorSet.start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv:
                Boolean isShowing = (Boolean) iv.getTag();
                if (null == isShowing || isShowing == false) {
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, "rotation", 0, 45);
                    objectAnimator.setDuration(500);
                    objectAnimator.start();
                    iv.setTag(true);
                    showSectorMenu();
                    /** 打开下面的注释可以看淡垂直显示的菜单效果*/
//                    showVerticalMenu();
                } else {
                    iv.setTag(false);
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, "rotation", 45, 0);
                    objectAnimator.setDuration(500);
                    objectAnimator.start();
                    closeSectorMenu();
                    /** 关闭淡垂直显示的菜单效果*/
//                    closeVerticalMenu();
                }
                break;
            case R.id.iv1:
                Log.e("ss","ss");
                Toast.makeText(this, "你点击了iv1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv2:
                Toast.makeText(this, "你点击了iv2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv3:
                Toast.makeText(this, "你点击了iv3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv4:
                Toast.makeText(this, "你点击了iv4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv5:
                Toast.makeText(this, "你点击了iv5", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
