package com.example.touristapp;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    protected static final int RESULT_SPEECH=121;


    public FirebaseDatabase database;
    public DatabaseReference myRef,myRef2;
    TextView textView;
    TextView usersComment;
    Button speak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Comments");
        myRef2 = database.getReference("All Comments");
        textView = findViewById(R.id.textView);
        usersComment = findViewById(R.id.usersComment);
        speak = findViewById(R.id.speak);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"en-US");
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    usersComment.setText("");
                }catch (ActivityNotFoundException e){
                    Toast.makeText(getApplicationContext(),"Your device doesn't support speech", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                textView.setText(textView.getText().toString()+"\n"+snapshot.getValue(String.class));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void goBackButton (View view ) {startActivity(new Intent(this, MainActivity.class));}
    public void write(View view){
        EditText usersName = findViewById(R.id.usersName);
        EditText usersComment = findViewById(R.id.usersComment);
        myRef.setValue(usersName.getText().toString()+": "+usersComment.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RESULT_SPEECH:
                if (resultCode == RESULT_OK && data !=null){
                    ArrayList<String> text =data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    usersComment.setText(text.get(0));
                }
                break;
        }
    }
}