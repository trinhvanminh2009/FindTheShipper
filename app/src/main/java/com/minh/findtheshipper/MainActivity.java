package com.minh.findtheshipper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;

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
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.models.RealmObject.CurrentUser;
import com.minh.findtheshipper.models.RealmObject.NotificationData;
import com.minh.findtheshipper.models.RealmObject.User;
import com.minh.findtheshipper.models.UserTemp;
import com.onesignal.OneSignal;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends FragmentActivity {


    CallbackManager callbackManager;
    ProgressDialog progress;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    String[] listProfile = new String[4];
    private Realm realm;
    public static String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Realm.init(this);
        initRealm();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        if (getNotificationData()!= null) {
            Log.e("notification", "Notification created");
        } else {
            createNotificationRealm();
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        progress = new ProgressDialog(MainActivity.this);
        progress.setMessage(getResources().getString(R.string.please_wait_facebooklogin));
        progress.setIndeterminate(false);
        progress.setCancelable(false);
        callbackManager = CallbackManager.Factory.create();
        final LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.minh.findtheshipper", PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        if (!checkLoginFacebook()) {
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(final LoginResult loginResult) {

                    final GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {

                            try {
                                Intent intent = new Intent(MainActivity.this, HandleMapsActivity.class);
                                email = response.getJSONObject().getString("email");
                                if (email != null) {
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
                                            CurrentUser currentUser = realm.createObject(CurrentUser.class, email);
                                            currentUser.setGender(gender);
                                            currentUser.setName(name);
                                            currentUser.setAvatar(imageURL);
                                            realm.insertOrUpdate(currentUser);
                                        }
                                    });
                                    addUser();

                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");
                                    mDatabase.child(EncodingFirebase.encodeString(email)).child("Name").setValue(EncodingFirebase.encodeString(name));
                                    mDatabase.child(EncodingFirebase.encodeString(email)).child("Gender").setValue(gender);
                                    mDatabase.child(EncodingFirebase.encodeString(email)).child("Avatar").setValue(EncodingFirebase.encodeString(imageURL));
                                    OneSignal.sendTag("email", email);
                                    startActivity(intent);
                                }

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
                    TastyToast.makeText(MainActivity.this, "Not Authentication", TastyToast.LENGTH_LONG, TastyToast.CONFUSING);
                }

                @Override
                public void onError(FacebookException error) {
                    TastyToast.makeText(MainActivity.this, "Something wrong!", TastyToast.LENGTH_LONG, TastyToast.ERROR);

                }
            });

        } else {
            realm = Realm.getDefaultInstance();
            if (getCurrentUser().getEmail() != null) {
                email = getCurrentUser().getEmail();
                OneSignal.sendTag("email", email);
                Intent intent = new Intent(MainActivity.this, HandleMapsActivity.class);
                startActivity(intent);
            } else {
                finish();
                startActivity(getIntent());
                Log.e("Null", "Email of current user is null");
            }

        }
    }

    public void initRealm() {
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public boolean checkLoginFacebook() {
        if (AccessToken.getCurrentAccessToken() != null) {
            return true;
        } else {
            return false;
        }
    }

    private User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        return realm.where(User.class).equalTo("email", currentUser.getEmail()).findFirst();
    }

    private void createNotificationRealm() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                NotificationData notificationData = realm.createObject(NotificationData.class, "nt0");
                notificationData.setTotalNotification(0);
                notificationData.setNumberUnread(0);
                realm.insertOrUpdate(notificationData);
            }
        });
    }

    private NotificationData getNotificationData() {
        return realm.where(NotificationData.class).findFirst();
    }

    public void addUser() {
        final CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class, currentUser.getEmail());
                user.setPhoneNumber("01655713623");
                user.setFullName(currentUser.getName());
                user.setAvatar(R.drawable.ic_your_profile);
                realm.insertOrUpdate(user);
            }
        });
    }
}
