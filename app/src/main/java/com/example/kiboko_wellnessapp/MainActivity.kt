package com.example.kiboko_wellnessapp

import android.content.Intent
import android.media.tv.AdRequest
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

//locating the button with their ID

class MainActivity : AppCompatActivity() {
   //create a private variable to hold interstitial ad
    private var mInterstitialAd : InterstitialAd? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Initialize Admob ads
        MobileAds.initialize(this)
         val Adview = findViewById<AdView>(R.id.adView)

        //request ad from Admob
        val adRequest = com.google.android.gms.ads.AdRequest.Builder().build()

        //show the ad
        Adview.loadAd(adRequest)

        //Show interstitial ad
        loadInterstitialAd()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val healthy_tips = findViewById<Button>(R.id.healthy_tips)
        val nutrion_advice = findViewById<Button>(R.id.nutrition_advice)
        val medication = findViewById<Button>(R.id.meditation)
        val hydration_alert = findViewById<Button>(R.id.hydration_alert)
        val start_activity = findViewById<Button>(R.id.start_activity)
        val daily_motivation = findViewById<Button>(R.id.daily_motivation)
        val check_progress = findViewById<Button>(R.id.check_progress)
        val weekly_goals = findViewById<Button>(R.id.weekly_goals)


        healthy_tips.setOnClickListener {
            val intent = Intent(applicationContext, Healthy_Recipes::class.java)
            startActivity(intent)
        }
        nutrion_advice.setOnClickListener {
            val intent = Intent(applicationContext, Nutrition_Advice::class.java)
            startActivity(intent)
        }
        medication.setOnClickListener {
            val intent = Intent(applicationContext, Medication::class.java)
            startActivity(intent)
        }
        hydration_alert.setOnClickListener {
            val intent = Intent(applicationContext, Hydration_Alert::class.java)
            startActivity(intent)
        }
        start_activity.setOnClickListener {
            val intent = Intent(applicationContext, Start_Exercise::class.java)
            startActivity(intent)
            showInterstitialAd()
        }
        daily_motivation.setOnClickListener {
            val intent = Intent(applicationContext, Daily_Motivation::class.java)
            startActivity(intent)
            showInterstitialAd()
        }
        check_progress.setOnClickListener {
            val intent = Intent(applicationContext, Check_Progress::class.java)
            startActivity(intent)
        }
        weekly_goals.setOnClickListener {
            val intent = Intent(applicationContext, Weekly_Goals::class.java)
            startActivity(intent)
        }

    }
    //load the interstitial Ad
    fun loadInterstitialAd(){
        //request Ad from Admob
        val adRequest = com.google.android.gms.ads.AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback(){
                //load the ad and store it inside a variable
                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd = ad
                }
                //set the variable to null if ad fails to load,i.e  in the absence of a network
                override fun onAdFailedToLoad(error: LoadAdError) {
                    mInterstitialAd = null
                }
            }

        )
    }
    //show  the ad
    fun showInterstitialAd(){
        if (mInterstitialAd != null){
            mInterstitialAd?.show(this)
        }
    }
}



