package com.example.touristapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class LogInActivity extends AppCompatActivity {
    EditText editText1, editText2,editText3;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        editText1 = findViewById(R.id.editTextTextPersonName);
        editText2 = findViewById(R.id.editTextTextPersonName2);
        editText3 = findViewById(R.id.editTextTextPersonName3);
        mAuth = FirebaseAuth.getInstance();
    }
    public void signup(View view){
        mAuth.createUserWithEmailAndPassword(
                editText2.getText().toString(),editText3.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            currentUser = mAuth.getCurrentUser();
                            String url = "https://thumbs.gfycat.com/SharpCookedGiraffe-size_restricted.gif";
                            changeUserProfile(currentUser,editText1.getText().toString(),url);
                        } else {
                            showMessage("Error",task.getException().getMessage());
                        }
                    }
                });
    }
    public void signin(View view){
        mAuth.signInWithEmailAndPassword(editText2.getText().toString(),editText3.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            currentUser = mAuth.getCurrentUser();
                            String uid = currentUser.getUid();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            intent.putExtra("displayName",currentUser.getDisplayName());
                            intent.putExtra("image",currentUser.getPhotoUrl().toString());
                            startActivity(intent);
                        }else {
                            showMessage("Error",task.getException().getMessage());
                        }
                    }
                });
    }
    public void showMessage(String title, String message){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .show();
    }
    private void changeUserProfile(FirebaseUser user,String displayName, String imageUrl){
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayName)
                .setPhotoUri(Uri.parse(imageUrl))
                .build();
        user.updateProfile(profileChangeRequest)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Profile Updated!",Toast.LENGTH_LONG).show();
                    }
                });
    }
    public void skipFornow(View view) { startActivity(new Intent(this, Welcomer.class)); }
}