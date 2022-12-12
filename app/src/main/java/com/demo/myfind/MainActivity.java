package com.demo.myfind;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv1_1,iv2_1,iv3_1,iv4_1,iv5_1,iv6_1,iv7_1,iv8_1;
    ImageView iv1_2,iv2_2,iv3_2,iv4_2,iv5_2,iv6_2,iv7_2,iv8_2;
    ImageView iv1_3,iv2_3,iv3_3,iv4_3,iv5_3,iv6_3,iv7_3,iv8_3;
    ImageView iv1_4,iv2_4,iv3_4,iv4_4,iv5_4,iv6_4,iv7_4,iv8_4;
    ImageView iv1_5,iv2_5,iv3_5,iv4_5,iv5_5,iv6_5,iv7_5,iv8_5;
    ImageView[] imageViews={
            iv1_1,iv2_1,iv3_1,iv4_1,iv5_1,iv6_1,iv7_1,iv8_1,
            iv1_2,iv2_2,iv3_2,iv4_2,iv5_2,iv6_2,iv7_2,iv8_2,
            iv1_3,iv2_3,iv3_3,iv4_3,iv5_3,iv6_3,iv7_3,iv8_3,
            iv1_4,iv2_4,iv3_4,iv4_4,iv5_4,iv6_4,iv7_4,iv8_4,
            iv1_5,iv2_5,iv3_5,iv4_5,iv5_5,iv6_5,iv7_5,iv8_5
    };

    int[] ids={
            R.id.iv1_1,R.id.iv2_1,R.id.iv3_1,R.id.iv4_1,R.id.iv5_1,R.id.iv6_1,R.id.iv7_1,R.id.iv8_1,
            R.id.iv1_2,R.id.iv2_2,R.id.iv3_2,R.id.iv4_2,R.id.iv5_2,R.id.iv6_2,R.id.iv7_2,R.id.iv8_2,
            R.id.iv1_3,R.id.iv2_3,R.id.iv3_3,R.id.iv4_3,R.id.iv5_3,R.id.iv6_3,R.id.iv7_3,R.id.iv8_3,
            R.id.iv1_4,R.id.iv2_4,R.id.iv3_4,R.id.iv4_4,R.id.iv5_4,R.id.iv6_4,R.id.iv7_4,R.id.iv8_4,
            R.id.iv1_5,R.id.iv2_5,R.id.iv3_5,R.id.iv4_5,R.id.iv5_5,R.id.iv6_5,R.id.iv7_5,R.id.iv8_5
    };

    int[] bgs={
            R.drawable.fish1_94, R.drawable.fish2_94, R.drawable.fish3_94, R.drawable.fish4_94,
            R.drawable.fish5_94, R.drawable.fish6_94, R.drawable.fish7_94, R.drawable.fish8_94,
            R.drawable.fish9_94, R.drawable.fish10_94, R.drawable.fish11_94, R.drawable.fish12_94,
            R.drawable.fish13_94, R.drawable.fish14_94, R.drawable.fish15_94, R.drawable.fish16_94,
            R.drawable.fish17_94, R.drawable.fish18_94, R.drawable.fish19_94, R.drawable.fish20_94,
            R.drawable.fish1_94, R.drawable.fish2_94, R.drawable.fish3_94, R.drawable.fish4_94,
            R.drawable.fish5_94, R.drawable.fish6_94, R.drawable.fish7_94, R.drawable.fish8_94,
            R.drawable.fish9_94, R.drawable.fish10_94, R.drawable.fish11_94, R.drawable.fish12_94,
            R.drawable.fish13_94, R.drawable.fish14_94, R.drawable.fish15_94, R.drawable.fish16_94,
            R.drawable.fish17_94, R.drawable.fish18_94, R.drawable.fish19_94, R.drawable.fish20_94
    };
    List<Integer> arrIds = new ArrayList<>();
    List<Integer> doneIndexs = new ArrayList<>();
    Context mContext;
    private Handler handler = new Handler();
    boolean isStart=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        for (int i=0;i<40;i++) {
            imageViews[i] = findViewById(ids[i]);
            imageViews[i].setOnClickListener(this);
            arrIds.add(ids[i]);
        }
        randomBgs(bgs);

    }

    private void randomBgs(int[] a) {
        Random r = new Random();
        for (int i = 0; i < a.length-1; i++) {
            int random = (int)(Math.random()*(a.length-i));
            int last = a[a.length-1-i];
            a[a.length-1-i] = a[random];
            a[random] = last;
        }
    }
   /* private void setBackground(int x, int y) {
        int pos=y*8+x;
        imageViews[pos].setBackground(getDrawable(bgs[pos]));
    }
    public void onClick1_1(View view) {
        x=0;y=0;
        setBackground(x,y);
    }*/

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View v) {
        int index=arrIds.indexOf(v.getId());
        if (index<0) return;
        if (doneIndexs.contains(index)){
            Toast.makeText(mContext,"Aleady opened！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isStart){
            Toast.makeText(mContext,"please press start！",Toast.LENGTH_SHORT).show();
            return;
        }

        doneIndexs.add(index);
        v.setBackground(getDrawable(bgs[index]));
        if (doneIndexs.size()%2==0){

            handler.postDelayed(runnable, 2000);
        }


    }



    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (doneIndexs.size()==0) return;
            int cur=doneIndexs.get(doneIndexs.size()-1);
            int last=doneIndexs.get(doneIndexs.size()-2);
            if(bgs[cur]!=bgs[last]){
                Toast.makeText(mContext,"Not the same, matching failed",Toast.LENGTH_SHORT).show();
                imageViews[cur].setBackground(getDrawable(R.drawable.tileback_94));
                imageViews[last].setBackground(getDrawable(R.drawable.tileback_94));
                doneIndexs.remove(doneIndexs.size()-1);
                doneIndexs.remove(doneIndexs.size()-1);
            }else{
                Toast.makeText(mContext,"Matching successfully！",Toast.LENGTH_SHORT).show();

            }
        }
    };

    public void onClickStart(View view) {
        isStart=true;
    }

    public void onClickEnd(View view) {
        isStart=false;
        for (int i=0;i<40;i++) {
            imageViews[i].setBackground(getDrawable(R.drawable.tileback_94));
        }
        doneIndexs.clear();
    }
}

