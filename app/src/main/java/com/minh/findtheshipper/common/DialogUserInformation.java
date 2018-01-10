package com.minh.findtheshipper.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.helpers.EncodingFirebase;

/**
 * Created by trinh on 12/10/2017.
 *
 */

public class DialogUserInformation extends DialogFragment {
    private String key = "";
    private ImageView imgUserImage;
    private TextView txtUserName;
    private TextView txtGender;
    private TextView txtLocation;
    private TextView txtPhoneNumber;
    private TextView txtOnline;
    private ImageView imgOnline;
    private TextView txtEmail;
    private RatingBar ratingBar;

    @SuppressWarnings("ConstantConditions")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().getAttributes().windowAnimations = R.style.MyDialogAnimation;
        return inflater.inflate(R.layout.dialog_user_information, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        key = bundle.getString("userEmail");
        imgUserImage = view.findViewById(R.id.imgUserImage);
        txtUserName = view.findViewById(R.id.txtUserName);
        txtGender = view.findViewById(R.id.txtGender);
        txtLocation = view.findViewById(R.id.txtLocation);
        txtPhoneNumber = view.findViewById(R.id.txtPhoneNumber);
        txtOnline = view.findViewById(R.id.txtOnline);
        imgOnline = view.findViewById(R.id.imgOnline);
        txtEmail = view.findViewById(R.id.txtEmail);
        ratingBar = view.findViewById(R.id.ratingBar);
        loadUserInformation();
    }

    public void loadUserInformation() {
        final DatabaseReference dataUser = FirebaseDatabase.getInstance().getReference().child("user");
        dataUser.addValueEventListener(new ValueEventListener() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(getActivity() == null){
                    return;
                }
                key = EncodingFirebase.encodeString(key);
                String url = dataSnapshot.child(key).child("Avatar").getValue(String.class);
                String userName = dataSnapshot.child(key).child("Name").getValue(String.class);
                String gender = dataSnapshot.child(key).child("Gender").getValue(String.class);
                boolean isOnline = dataSnapshot.child(key).child("Online").getValue(Boolean.class);
                Double latitude = dataSnapshot.child(key).child("Last Latitude").getValue(Double.class);
                Double longitude = dataSnapshot.child(key).child("Last Longitude").getValue(Double.class);
                String phoneNumber  = dataSnapshot.child(key).child("Phone number").getValue(String.class);
                int rating = dataSnapshot.child(key).child("Rating").getValue(Integer.class);
                String email = EncodingFirebase.decodeString(key);
                if(key != null && url != null && userName != null && gender != null
                        && latitude != null && longitude != null
                        && phoneNumber != null && email != null){

                    Glide.with(getActivity()).load(EncodingFirebase.decodeString(url))
                            .apply(RequestOptions.circleCropTransform()).thumbnail(1.0f).into(imgUserImage);
                    txtUserName.setText(userName);
                    txtEmail.setText(email);
                    txtGender.setText(gender);
                    String online = "Online";
                    String offline = "Offline";
                    if(isOnline){
                        txtOnline.setText(online);
                        imgOnline.setImageResource(R.drawable.ic_status_offline);

                    }else{
                        txtOnline.setText(offline);
                        imgOnline.setImageResource(R.drawable.ic_status_online);
                    }
                    String currentLocation = EncodingFirebase.getCompleteAddressString
                            (getActivity(), latitude, longitude);
                    txtLocation.setText(currentLocation);
                    ratingBar.setMax(5);
                    ratingBar.setNumStars(5);
                    txtPhoneNumber.setText(phoneNumber);
                    ratingBar.setStepSize((float)rating);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    @SuppressWarnings("ConstantConditions")
    @Override
    public void onResume() {
        super.onResume();
        int width = getResources().getDimensionPixelSize(R.dimen.comment_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);
    }
}
