package com.example.easyteaching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import helpers.HeaderInterceptor;
import model.LoginInfo;
import model.Professeurs;
import repository.AuthenticationRepository;

public class LoginActivity extends AppCompatActivity {

    private AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    private HeaderInterceptor interceptor = HeaderInterceptor.getInstance();
    private SharedPreferences prefs;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        prefs = getSharedPreferences("myapp", MODE_PRIVATE);
        token = prefs.getString("token", null);
        setTokenInterceptor(token);

        /*
        authenticationRepository.login(new LoginInfo("godefroid.laurent@helha.be", "gogogo")).observe(this, new Observer<Professeurs>() {
            @Override
            public void onChanged(Professeurs professeurs) {
                Log.i("Authenticate", professeurs + "");
            }
        });
        */
    }

    public void onSubmitLogin(View view) {
        EditText mail = findViewById(R.id.loginMail);
        EditText pwd = findViewById(R.id.loginPassword);

        callApiToLogin(mail.getText().toString() ,pwd.getText().toString());
    }

    private void callApiToLogin(String mail, String password) {
        Log.i("Login Activity", mail + " " + password);
        authenticationRepository.login(new LoginInfo(mail, password)).observe(this, new Observer<Professeurs>() {
            @Override
            public void onChanged(Professeurs professeurs) {
                Log.i("Login Authenticate", professeurs + "");
                if (professeurs == null){
                    Toast.makeText(getApplicationContext(), "Wrong combination of mail/password", Toast.LENGTH_SHORT).show();
                    return;
                }
                registerUserInPref(professeurs);
                navigateToMainAndFinish();
            }
        });
    }

    private void navigateToMainAndFinish() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void registerUserInPref(Professeurs professeurs) {
        Log.i("MyToken", token);
        Log.i("userID", prefs.getInt("userID", -1) + "");
        if (token == null){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("token", professeurs.getToken());
            editor.putInt("userID", professeurs.getId());
            editor.apply();
            /*
            Toast.makeText(this, "Token : "+ prefs.getString("token", null)+ " UserID"
            + prefs.getInt("userID", -1), Toast.LENGTH_LONG);
            */
        }
    }

    private void setTokenInterceptor(String token){
        interceptor.setToken(token);
    }
}
