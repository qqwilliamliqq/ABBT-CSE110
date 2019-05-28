package com.example.afs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.Map;

public class Profile extends AppCompatActivity {
    private Button logOutButton;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private FirebaseUser curUser;
    private ImageButton editButton;
    private TextView ageText;
    private TextView heightText;
    private TextView weightText;
    private TextView usernameText;
    private String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        /*
         * remove later
        db = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            curUser = mAuth.getCurrentUser();
        }

        userID = curUser.getUid();

        usernameText = (TextView) findViewById(R.id.Username);
        ageText = (TextView) findViewById(R.id.edit_age);
        heightText = (TextView) findViewById(R.id.edit_height);
        weightText = (TextView) findViewById(R.id.edit_weight);


        db.child("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                System.out.println("Debug message/n" + dataSnapshot.getValue());
                //Map<String, Object> user = (Map<String, Object>) dataSnapshot;

                usernameText.setText((String)dataSnapshot.child("userName").getValue());
                System.out.println("Sava Method: " + dataSnapshot.child("age").getValue().getClass());
                ageText.setText(dataSnapshot.child("age").getValue().toString());
                heightText.setText(dataSnapshot.child("height").getValue().toString());
                weightText.setText(dataSnapshot.child("weight").getValue().toString());
                //usernameText.setText((char)user.get("userName"));
                //ageText.setText((char)user.get("age"));
                //heightText.setText((char)user.get("height"));
                //weightText.setText((char)user.get("weight"));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */

        logOutButton = (Button) findViewById(R.id.logout_button);
        logOutButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Profile.this, login.class);
                startActivity(intent);
            }
        });

        editButton = (ImageButton) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterEditProfile();
            }
        });



        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_workout:
                        Intent intent1 = new Intent(Profile.this, WorkOut.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_dietplan:
                        Intent intent2 = new Intent(Profile.this, DietPlan.class);
                        startActivity(intent2);
                        break;

                    case R.id.nav_calendar:
                        Intent intent3 = new Intent(Profile.this, Calendar.class);
                        startActivity(intent3);
                        break;

                    case R.id.nav_profile:
                        Intent intent4 = new Intent(Profile.this, Profile.class);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });


    }

    private void updateUserInfo() {

    }

    private void enterEditProfile() {
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
