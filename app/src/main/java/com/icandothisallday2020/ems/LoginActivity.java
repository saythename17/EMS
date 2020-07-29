package com.icandothisallday2020.ems;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.exception.KakaoException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.kakao.util.helper.Utility.getKeyHash;
import static com.kakao.util.helper.Utility.getPackageInfo;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        String keyHash=getKeyHash(this);
//        Log.i("key",keyHash);

        //Dialog telling users that the email consent is required
        AlertDialog.Builder builder=new AlertDialog.Builder(this,android.R.style.Theme_Material_Dialog);
        builder.setTitle("You must agree to the email collection.");//When you log in, you must check the consent of the email collection items.
        builder.setMessage("\nOtherwise, any information you write will not be saved.\n" +
                "By tapping the login button, you agree to EMS's Privacy Policy.");//"EMS identifies users by email \nand stores the data in the DB.\n"
        //└
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("Check", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


//        String key=getKeyHash(this);
//        Log.i("key", getKeyHash(this));

        //TODO Test Ver. remove under the 2 line TODO
//        Intent intent= new Intent(this,MainActivity.class);
//        startActivity(intent);

        Session.getCurrentSession().addCallback(sessionCallback);
    }


    //Listener try to connect with kakao login server & session work result
    ISessionCallback sessionCallback = new ISessionCallback() {
        @Override
        public void onSessionOpened() {
            Toast.makeText(LoginActivity.this, "Success to Login", Toast.LENGTH_SHORT).show();

            //receive information of user
            requestUserInfo();

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    };


    //receive information of user
    void requestUserInfo() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(MeV2Response result) {
                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                UserAccount account = result.getKakaoAccount();
                if (account == null) return;

                SharedPreferences preferences=getSharedPreferences("Data",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("Login",true);
                editor.putString("Name",account.getProfile().getNickname());
                editor.putString("Email",account.getEmail());
                editor.putString("ProfileUrl",account.getProfile().getProfileImageUrl());
                editor.commit();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Session.getCurrentSession().removeCallback(sessionCallback);
    }



//    public static String getKeyHash(final Context context) {
//        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
//        if (packageInfo == null)
//            return null;
//
//        for (Signature signature : packageInfo.signatures) {
//            try {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
//            } catch (NoSuchAlgorithmException e) {
//                Log.w("TAG", "Unable to get MessageDigest. signature=" + signature, e);
//            }
//        }
//        return null;
//    }
}

