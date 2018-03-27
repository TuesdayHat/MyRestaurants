package com.epicodus.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.myrestaurants.Constants;
import com.epicodus.myrestaurants.R;

import butterknife.ButterKnife;
import butterknife.BindView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;

    @BindView(R.id.findRestaurantsButton) Button mFindRestaurantsButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mFindRestaurantsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v == mFindRestaurantsButton){
            String location = mLocationEditText.getText().toString();
//            if(!(location).equals("")){
//                addToSharedPreferences(location);
//            }
            saveLocationToFirebase(location);
            Intent intent = new Intent(MainActivity.this, RestaurantsListActivity.class);
//            intent.putExtra("location", location);
            startActivity(intent);
        }
    }

//    private void addToSharedPreferences(String location){
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }

    private void saveLocationToFirebase(String location){
        mSearchedLocationReference.push().setValue(location); //if push() isn't there, will replace data each time
    }
}
