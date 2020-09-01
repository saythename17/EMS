package com.icandothisallday2020.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class LoginEMSActivity extends AppCompatActivity {
    TextInputEditText name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_e_m_s);
        name=findViewById(R.id.et_name);
        email=findViewById(R.id.et_email);
    }

    public void emsLogin(View view) {
        SharedPreferences preferences=getSharedPreferences("Data",MODE_PRIVATE);

        //TODO check sing in
        //TODO save the userData to sharedPreferences
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("Login",true);
        editor.putString("Name",name.getText().toString());
        editor.putString("Email",email.getText().toString());
        editor.putString("ProfileUrl", "https://cdn.icon-icons.com/icons2/2442/PNG/512/favorite_profile_user_icon_148627.png");//default profile image
        editor.commit();

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
