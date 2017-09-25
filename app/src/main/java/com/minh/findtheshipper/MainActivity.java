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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minh.findtheshipper.Shop.HandleMapsActivity;
import com.minh.findtheshipper.helpers.EncodingFireBase;
import com.minh.findtheshipper.models.CurrentUser;
import com.minh.findtheshipper.models.UserTemp;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends FragmentActivity {


    CallbackManager callbackManager;
    ProgressDialog progress;
    @BindView(R.id.toolBar) Toolbar toolbar;
    String []listProfile = new String[4];
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Realm.init(this);
        initRealm();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        progress=new ProgressDialog(MainActivity.this);
        progress.setMessage(getResources().getString(R.string.please_wait_facebooklogin));
        progress.setIndeterminate(false);
        progress.setCancelable(false);
        callbackManager = CallbackManager.Factory.create();
        final LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        if(!checkLoginFacebook())
        {
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(final LoginResult loginResult) {

                        final GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                try {
                                    EncodingFireBase encodingFireBase = new EncodingFireBase();
                                    Intent intent = new Intent(MainActivity.this, HandleMapsActivity.class);
                                    final String email = response.getJSONObject().getString("email");
                                    final String gender = response.getJSONObject().getString("gender");
                                    final String name = response.getJSONObject().getString("name");
                                    String userID = loginResult.getAccessToken().getUserId();
                                    final String imageURL = "https://graph.facebook.com/" + userID + "/picture?width=200" + "&height=200";
                                    UserTemp userTemp = new UserTemp();
                                    userTemp.setAvatar(imageURL);
                                    userTemp.setEmail(email);
                                    userTemp.setName(name);
                                    userTemp.setGender(gender);
                                    /*
                                     * Have to save current user into database for all class can access to current user login Facebook.
                                     * */
                                    realm.executeTransaction(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            CurrentUser currentUser = realm.createObject(CurrentUser.class,email);
                                            currentUser.setGender(gender);
                                            currentUser.setName(name);
                                            currentUser.setAvatar(imageURL);
                                            realm.insertOrUpdate(currentUser);
                                        }
                                    });
                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");
                                    mDatabase.child(encodingFireBase.encodeString(email)).child("Name").setValue(encodingFireBase.encodeString(name));
                                    mDatabase.child(encodingFireBase.encodeString(email)).child("Gender").setValue(gender);
                                    mDatabase.child(encodingFireBase.encodeString(email)).child("Avatar").setValue(encodingFireBase.encodeString(imageURL));

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

        }
       else {

            Intent intent = new Intent(MainActivity.this, HandleMapsActivity.class);
            startActivity(intent);
        }
    }

    public void initRealm()
    {
        realm = null;
        realm = Realm.getDefaultInstance();
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
