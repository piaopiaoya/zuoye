package com.example.text_dibu;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.text_dibu.fragment.ClassFragment;
import com.example.text_dibu.fragment.GouFragment;
import com.example.text_dibu.fragment.HomeFragment;
import com.example.text_dibu.fragment.MyFragment;
import com.example.text_dibu.fragment.ZhuanFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout fl;
    private RadioGroup rg;
    private RadioButton rbHome;
    private RadioButton rbZhuanti;
    private RadioButton rbFeilei;
    private RadioButton rbGouwuche;
    private RadioButton rbMy;
    private HomeFragment homeFragment;
    private ZhuanFragment zhuanFragment;
    private GouFragment gouFragment;
    private MyFragment myFragment;
    private ClassFragment classFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        homeFragment = new HomeFragment();
        zhuanFragment = new ZhuanFragment();
        classFragment = new ClassFragment();
        gouFragment = new GouFragment();
        myFragment = new MyFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl,homeFragment)
                .add(R.id.fl,zhuanFragment)
                .add(R.id.fl,classFragment)
                .add(R.id.fl,gouFragment)
                .add(R.id.fl,myFragment)
                .show(homeFragment)
                .hide(zhuanFragment)
                .hide(classFragment)
                .hide(gouFragment)
                .hide(myFragment)
                .commit();

    }

    private void initView() {
        fl = (FrameLayout) findViewById(R.id.fl);
        rg = (RadioGroup) findViewById(R.id.rg);
        rbHome = (RadioButton) findViewById(R.id.rb_home);
        rbZhuanti = (RadioButton) findViewById(R.id.rb_zhuanti);
        rbFeilei = (RadioButton) findViewById(R.id.rb_feilei);
        rbGouwuche = (RadioButton) findViewById(R.id.rb_gouwuche);
        rbMy = (RadioButton) findViewById(R.id.rb_my);

        rbHome.setOnClickListener(this);
        rbZhuanti.setOnClickListener(this);
        rbFeilei.setOnClickListener(this);
        rbGouwuche.setOnClickListener(this);
        rbMy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rb_home:
                getSupportFragmentManager().beginTransaction()
                        .show(homeFragment)
                        .hide(zhuanFragment)
                        .hide(classFragment)
                        .hide(gouFragment)
                        .hide(myFragment)
                        .commit();
                break;
            case R.id.rb_zhuanti:
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .show(zhuanFragment)
                        .hide(classFragment)
                        .hide(gouFragment)
                        .hide(myFragment)
                        .commit();
                break;
            case R.id.rb_feilei:
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .hide(zhuanFragment)
                        .show(classFragment)
                        .hide(gouFragment)
                        .hide(myFragment)
                        .commit();
                break;
            case R.id.rb_gouwuche:
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .hide(zhuanFragment)
                        .hide(classFragment)
                        .show(gouFragment)
                        .hide(myFragment)
                        .commit();
                break;
            case R.id.rb_my:
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .hide(zhuanFragment)
                        .hide(classFragment)
                        .hide(gouFragment)
                        .show(myFragment)
                        .commit();
                break;
        }
    }
}