package com.example.touristapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    TextView aboutText,welcomeUser;
    Button openMap,openLocalHotel,openComments;
    private Button mButtonSpeak;
    private TextToSpeech mTTS;
    private SeekBar mSeekBarPitch;
    private SeekBar mSeekBarSpeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openLocalHotel = findViewById(R.id.localHotelsButton);
        openMap = findViewById(R.id.googleMapButton);
        openComments = findViewById(R.id.commentsButton);
        openMap = findViewById(R.id.googleMapButton);
        mButtonSpeak = findViewById(R.id.button_speak);
        welcomeUser = findViewById(R.id.welcomerText);

        String welcomeText = getIntent().getStringExtra("keyname");
        welcomeUser.setText("Welcome " + welcomeText);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        aboutText = findViewById(R.id.aboutText);
        mSeekBarPitch = findViewById(R.id.seek_bar_pitch);
        mSeekBarSpeed = findViewById(R.id.seek_bar_speed);

        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });


        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);

    }
    private void speak() {
        String text = aboutText.getText().toString();
        float pitch = (float) mSeekBarPitch.getProgress() / 50;
        if (pitch < 0.1) pitch = 0.1f;
        float speed = (float) mSeekBarSpeed.getProgress() / 50;
        if (speed < 0.1) speed = 0.1f;
        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
    public void logInButton(View view ) {startActivity(new Intent(this, LogInActivity.class));}
    public void openWelcomer(View view ) {startActivity(new Intent(this, Welcomer.class));}
    public void openLocalHotels(View view) { startActivity(new Intent(this, LocalHotelsActivity.class)); }
    public void openComments(View view) { startActivity(new Intent(this, CommentsActivity.class)); }
    public void openMap(View view) { startActivity(new Intent(this, MapsActivity.class)); }

}