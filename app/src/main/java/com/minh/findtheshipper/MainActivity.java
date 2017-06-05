package com.minh.findtheshipper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {


    CallbackManager callbackManager;
    ProgressDialog progress;
    @BindView(R.id.toolBar) Toolbar toolbar;
    String []listProfile = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        progress=new ProgressDialog(MainActivity.this);
        progress.setMessage(getResources().getString(R.string.please_wait_facebooklogin));
        progress.setIndeterminate(false);
        progress.setCancelable(false);
        callbackManager = CallbackManager.Factory.create();
        final LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
      /*  if(checkLoginFacebook() == false)
        {*/
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(final LoginResult loginResult) {

                    final GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {

                            try {
                                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                                String email = response.getJSONObject().getString("email");
                                String gender = response.getJSONObject().getString("gender");
                                String name = response.getJSONObject().getString("name");
                                String userID = loginResult.getAccessToken().getUserId();
                                String imageURL = new String("https://graph.facebook.com/" + userID + "/picture?width=200" + "&height=200");
                                listProfile[0] = email;
                                listProfile[1] = gender;
                                listProfile[2] = name;
                                listProfile[3] = imageURL;
                                intent.putExtra("profile",listProfile);
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,email,gender,birthday");
                    request.setParameters(parameters);
                    request.executeAsync();
                }

                @Override
                public void onCancel() {
                    TastyToast.makeText(MainActivity.this,"Not Authentication",TastyToast.LENGTH_LONG,TastyToast.CONFUSING);
                }

                @Override
                public void onError(FacebookException error) {
                    TastyToast.makeText(MainActivity.this,"Something wrong!",TastyToast.LENGTH_LONG,TastyToast.ERROR);

                } });

      /*  }
        else {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        }*/



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    public boolean checkLoginFacebook()
    {
        if(AccessToken.getCurrentAccessToken() != null)
        {
            return true;
        }
        else {
            return false;
        }

    }


}
