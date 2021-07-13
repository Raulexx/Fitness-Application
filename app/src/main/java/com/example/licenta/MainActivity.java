package com.example.licenta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    ImageView profil_photo ;
    TextView profil_name;
    EditText profil_weight;
    TextView profil_height;
    TextView profil_age;
    TextView profil_kcal;
    TextView profil_protein;
    TextView profil_carbs;
    TextView profil_fats;
    TextView navbar_name;

    FloatingActionButton profil_settings;
    FloatingActionButton profil_settings_ok;


    Boolean settings_activated = false;
    boolean verificareDBMeals;


    private static final int PICK_IMAGE = 100;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        final DatabaseHelper db = new DatabaseHelper(this);
        final String date  = new SimpleDateFormat("MMM, dd , yyyy" , Locale.getDefault()).format(new Date());


        //region verificare tabel sa fie populat

        verificareDBMeals  =db.verifyMealTable();
        if(verificareDBMeals) {
            db.createMealtypeTable("BreakFast");
            db.createMealtypeTable("Lunch");
            db.createMealtypeTable("Dinner");
        }

        //endregion

        //region  -----------------Cod NavBar--------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //endregion  #-----------------Cod NavBar--------------

        //region ------------------Asigning UI with Code-------------------

        profil_photo = findViewById(R.id.profil_photo);

        profil_name = findViewById(R.id.profil_name);
        profil_weight = findViewById(R.id.profil_weight);
        profil_height = findViewById(R.id.profil_height);
        profil_age = findViewById(R.id.profil_age);
        profil_kcal = findViewById(R.id.profil_kcal);
        profil_protein = findViewById(R.id.profil_protein);
        profil_carbs = findViewById(R.id.profil_carbs);
        profil_fats = findViewById(R.id.profil_fats);

        profil_settings = findViewById(R.id.btn_edit_profil);
        profil_settings_ok = findViewById(R.id.btn_edit_profil_ok);

        navbar_name= findViewById(R.id.navbar_name);
        //endregion

        //region ----------------------------settings button--------------------------------


        profil_settings.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {

                profil_settings.setVisibility(View.GONE);
                profil_settings_ok.setVisibility(View.VISIBLE);

                profil_name.setFocusableInTouchMode(true);
                profil_name.setEnabled(true);

                profil_weight.setFocusableInTouchMode(true);
                profil_weight.setEnabled(true);

                profil_height.setFocusableInTouchMode(true);
                profil_height.setEnabled(true);

                profil_age.setFocusableInTouchMode(true);
                profil_age.setEnabled(true);

                profil_kcal.setFocusableInTouchMode(true);
                profil_kcal.setEnabled(true);

                profil_protein.setFocusableInTouchMode(true);
                profil_protein.setEnabled(true);

                profil_carbs.setFocusableInTouchMode(true);
                profil_carbs.setEnabled(true);

                profil_fats.setFocusableInTouchMode(true);
                profil_fats.setEnabled(true);

                settings_activated = true;

            }
        });

        //endregion

        //region ----------------------------setari OK-----------------------------
        profil_settings_ok.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {

                profil_settings.setVisibility(View.VISIBLE);
                profil_settings_ok.setVisibility(View.GONE);

                profil_name.setFocusableInTouchMode(false);
                profil_name.setEnabled(false);

                profil_weight.setFocusableInTouchMode(false);
                profil_weight.setEnabled(false);

                profil_height.setFocusableInTouchMode(false);
                profil_height.setEnabled(false);

                profil_age.setFocusableInTouchMode(false);
                profil_age.setEnabled(false);

                profil_kcal.setFocusableInTouchMode(false);
                profil_kcal.setEnabled(false);

                profil_protein.setFocusableInTouchMode(false);
                profil_protein.setEnabled(false);

                profil_carbs.setFocusableInTouchMode(false);
                profil_carbs.setEnabled(false);

                profil_fats.setFocusableInTouchMode(false);
                profil_fats.setEnabled(false);

                settings_activated = false;
                closeKeyboard();
                saveData();







            }


        });

        //endregion

        //region -----------------poza profil---------------------

            profil_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(settings_activated) {
                        openGallery();
                    }
                    else{}
                }
            });

            //endregion



    }





        private void saveData()
        {
            SharedPreferences sharedPreferences = getSharedPreferences("profileData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putString("name", profil_name.getText().toString());
            editor.putString("weight", profil_weight.getText().toString());
            editor.putString("height", profil_height.getText().toString());
            editor.putString("age", profil_age.getText().toString());
            editor.putString("kcal", profil_kcal.getText().toString());
            editor.putString("protein", profil_protein.getText().toString());
            editor.putString("carbs", profil_carbs.getText().toString());
            editor.putString("fats", profil_fats.getText().toString());

            editor.commit();

            Toast.makeText(this, "Data saved", Toast.LENGTH_LONG);

        }

    private void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("profileData", Context.MODE_PRIVATE);
        profil_name.setText(sharedPreferences.getString("name",     "Enter Name"));
        profil_weight.setText(sharedPreferences.getString("weight", "0"));
        profil_height.setText(sharedPreferences.getString("height", "0"));
        profil_age.setText(sharedPreferences.getString("age",       "0"));
        profil_kcal.setText(sharedPreferences.getString("kcal",     "0"));
        profil_protein.setText(sharedPreferences.getString("protein", "0"));
        profil_carbs.setText(sharedPreferences.getString("carbs",   "0"));
        profil_fats.setText(sharedPreferences.getString("fats",     "0"));
    }

   private void openGallery()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
}


    public void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }



    }


     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode , resultCode , data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            profil_photo.setImageURI(imageUri);
        }
}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);


        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            // Handle the camera action
        }
        else if (id == R.id.nutrition)
        {
            Intent intent = new Intent(this,Nutritie.class);
            startActivity(intent);
        }
        else if (id == R.id.workout)
        {
            Intent intent = new Intent(this,Workout.class);
            startActivity(intent);
        }
        else if (id == R.id.calculator)
        {
           Intent intent = new Intent(this, Calculator.class);
           startActivity(intent);

        }
        else if (id == R.id.personalTracker)
        {
            Intent intent = new Intent(this, PersonalProgress.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();

      loadData();

    }




}

