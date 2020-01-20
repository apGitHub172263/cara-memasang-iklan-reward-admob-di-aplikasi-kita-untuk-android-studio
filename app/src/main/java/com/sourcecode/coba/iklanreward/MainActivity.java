package com.sourcecode.coba.iklanreward;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;


public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private RewardedVideoAd mRewardedVideoAd;
    Button get_Reward;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713"); // id admob
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
        get_Reward = (Button) findViewById(R.id.get_reward);
        get_Reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
            }
        });

    }
    private void loadRewardedVideoAd() {
        //id RewardedVideo
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }
    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(this, "Iklan Gagal Dimuat " + reward.getType() + "  amount: " +
                reward.getAmount(), Toast.LENGTH_SHORT).show();
        // isini akan di eksekusi saat Iklan Gagal Dimuat
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
       // isini akan di eksekusi saat Pengguna Meniggalkan Aplikasi/Membuka Aplikasi Lain
        Toast.makeText(getApplicationContext(),"Iklan Meninggalkan Aplikasi", Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        //disini akan di eksekusi saat Iklan Gagal Dimuat
        Toast.makeText(this, "Iklan Gagal Dimuat", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        //isini akan di eksekusi saat Iklan Selesai Dimuat
        Toast.makeText(this, "Iklan Berhasil Dimuat", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        //disini akan di eksekusi saat Pengguna Mengklik Iklan
        Toast.makeText(this, "Iklan sedang dibuka", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {

        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

  //  @Override
   // public void onRewardedVideoCompleted() {
  //      Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
  //  }
    @Override
    public void onRewardedVideoAdClosed() {
        //Metode ini berjalan saat Iklan Ditutup
        loadRewardedVideoAd();
    }
    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }





}

