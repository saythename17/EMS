package com.icandothisallday2020.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

        String key=getKeyHash(this);
        Log.i("key", getKeyHash(this));
    //    Session.getCurrentSession().addCallback(sessionCallback);
    }


    //Listener try to connect with kakao login server & session work result
    ISessionCallback sessionCallback = new ISessionCallback() {
        @Override
        public void onSessionOpened() {
            Toast.makeText(LoginActivity.this, "Success to connect Login Session", Toast.LENGTH_SHORT).show();

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
            }

            @Override
            public void onSuccess(MeV2Response result) {
                UserAccount account = result.getKakaoAccount();
                if (account == null) return;

                G.userName = account.getProfile().getNickname();
                G.userEmail = account.getEmail();
                G.userThumbnail = account.getProfile().getThumbnailImageUrl();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Session.getCurrentSession().removeCallback(sessionCallback);
    }



    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w("TAG", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }
}

