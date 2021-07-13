package com.example.licenta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


public class Calculator extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CheckBox gender_male ;
    CheckBox gender_female;

    CheckBox goal_fatLoss;
    CheckBox goal_maintainance ;
    CheckBox goal_muscleGain ;

    CheckBox activityLevel_none ;
    CheckBox activityLevel_lightly ;
    CheckBox activityLevel_moderate;
    CheckBox activityLevel_very ;
    CheckBox activityLevel_extra;


    Button btn_calculate;
    EditText weight_ ;
    EditText height_ ;
    EditText age_ ;

    double bmr = 0;
    double kcal = 0;
    public double kcal_final = 0;
    int height;
    int weight;
    int age;

    String _weight;
    String _height;
    String _age;

    String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        final SharedPreferences sharedPreferences = this.getSharedPreferences("profileData", Context.MODE_PRIVATE);





        //region  #-----------------Cod NavBar--------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //endregion #-----------------Cod NavBar--------------

        //region #------------------Asigning UI with Code-------------------
        gender_male = findViewById(R.id.gender_male);
        gender_female = findViewById(R.id.gender_female);

        goal_fatLoss = findViewById(R.id.goal_fatLoss);
        goal_maintainance = findViewById(R.id.goal_maintainance);
        goal_muscleGain = findViewById(R.id.goal_muscleGain);

        activityLevel_none = findViewById(R.id.activityLevel_none);
        activityLevel_lightly = findViewById(R.id.activityLevel_lightly);
        activityLevel_moderate = findViewById(R.id.activityLevel_moderate);
        activityLevel_very = findViewById(R.id.activityLevel_very);
        activityLevel_extra = findViewById(R.id.activityLevel_extra);

        btn_calculate = findViewById(R.id.btn_calculate);

        weight_ =findViewById(R.id.weight);
        height_ =findViewById(R.id.height);
        age_ = findViewById(R.id.age);


        //endregion

        //region #------------------Gender Select----------------------

        gender_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                {
                    gender_female.setChecked(false);
                }
            }
        });

        gender_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    gender_male.setChecked(false);
                }
            }
        });

        //endregion

        //region #------------------Fitness Goal-----------------------

        goal_fatLoss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked) {
                    goal_maintainance.setChecked(false);
                    goal_muscleGain.setChecked(false);
                }
            }
        });

        goal_maintainance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked)
                {
                    goal_fatLoss.setChecked(false);
                    goal_muscleGain.setChecked(false);
                }
            }
        });

        goal_muscleGain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked)
                {
                    goal_fatLoss.setChecked(false);
                    goal_maintainance.setChecked(false);

                }
            }
        });

        //endregion-----------

        //region #------------------Activity Level---------------------

        activityLevel_none.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked)
                {
                    activityLevel_lightly.setChecked(false);
                    activityLevel_moderate.setChecked(false);
                    activityLevel_very.setChecked(false);
                    activityLevel_extra.setChecked(false);
                }
            }
        });

        activityLevel_lightly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked)
                {
                    activityLevel_none.setChecked(false);
                    activityLevel_moderate.setChecked(false);
                    activityLevel_very.setChecked(false);
                    activityLevel_extra.setChecked(false);
                }
            }
        });

        activityLevel_moderate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked)
                {
                    activityLevel_none.setChecked(false);
                    activityLevel_lightly.setChecked(false);
                    activityLevel_very.setChecked(false);
                    activityLevel_extra.setChecked(false);
                }
            }
        });

        activityLevel_very.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked)
                {
                    activityLevel_none.setChecked(false);
                    activityLevel_lightly.setChecked(false);
                    activityLevel_moderate.setChecked(false);
                    activityLevel_extra.setChecked(false);
                }
            }
        });

        activityLevel_extra.setOnCheckedChangeListener((new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked)
                {
                    activityLevel_none.setChecked(false);
                    activityLevel_lightly.setChecked(false);
                    activityLevel_moderate.setChecked(false);
                    activityLevel_very.setChecked(false);
                }
            }
        }));

        //endregion

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String weightTested = weight_.getText().toString();
                String heightTested = height_.getText().toString();
                String ageTested = age_.getText().toString();
                Boolean allgood = true;

                if ((!gender_male.isChecked() && !gender_female.isChecked()))
                {
                    Toast.makeText(Calculator.this, "Select a gender",
                            Toast.LENGTH_LONG).show();
                    allgood = false;
                }


                else if(!goal_fatLoss.isChecked() && !goal_maintainance.isChecked() && !goal_muscleGain.isChecked())
                {
                    Toast.makeText(Calculator.this, "Select a goal",
                            Toast.LENGTH_LONG).show();
                    allgood = false;
                }

                else if(!activityLevel_none.isChecked() && !activityLevel_lightly.isChecked() && !activityLevel_moderate.isChecked() && !activityLevel_very.isChecked() && !activityLevel_extra.isChecked())
                {
                    Toast.makeText(Calculator.this, "Select your activity level",
                            Toast.LENGTH_LONG).show();
                    allgood = false;
                }

                else if(weightTested.equals(""))
                {
                    Toast.makeText(Calculator.this, "Please fill your Weight",
                            Toast.LENGTH_LONG).show();
                    allgood = false;

                }

                else if(heightTested.equals(""))
                {
                    Toast.makeText(Calculator.this, "Please fill your Height",
                            Toast.LENGTH_LONG).show();
                    allgood = false;
                }

                else if(ageTested.equals(""))
                {
                    Toast.makeText(Calculator.this, "Please fill your Age",
                            Toast.LENGTH_LONG).show();
                    allgood = false;
                }


                if(allgood)
                {
                    weight = Integer.parseInt(weight_.getText().toString());
                    height = Integer.parseInt(height_.getText().toString());
                    age = Integer.parseInt(age_.getText().toString());
                    bmr = calculateBMR(weight, height, age);
                    kcal = calculateKcal(bmr);
                    kcal_final = calculateFinalKcal(kcal);
                    String target = verifyTarget();
                    SharedPreferences.Editor editor= sharedPreferences.edit();
                    editor.putString("target",target );
                    editor.commit();
                    saveData();
                    openDialog();
                }

            }


            private void openDialog() {
                PopupCalculator popupCalculator = new PopupCalculator();
                Bundle bundle = new Bundle();
                bundle.putDouble("kcal_final" , kcal_final);
                popupCalculator.setArguments(bundle);
                popupCalculator.show(getSupportFragmentManager(),"Macros");

            }

        });


    }

    private double calculateBMR(int weight, int height, int age)
    {

        double bmr = 0;

        if(gender_male.isChecked())
        {
            bmr = 66 + (13.75*weight)+(5*height)-(6.8*age);
        }

        if(gender_female.isChecked())
        {
            bmr = 655 + (9.6*weight)+(1.8*height)-(4.7*age);
        }

        return bmr;
    }

    private double calculateKcal(double bmr)
    {

        if(activityLevel_none.isChecked())
        {
         bmr *=1.2;
        }

        if(activityLevel_lightly.isChecked())
        {
            bmr*=1.375;
        }

        if(activityLevel_moderate.isChecked())
        {
            bmr*=1.5;
        }

        if(activityLevel_very.isChecked())
        {
          bmr *= 1.65;
        }

        if(activityLevel_extra.isChecked())
        {
            bmr*= 1.8;
        }

        return bmr;
    }

    private double calculateFinalKcal(double bmr)
    {
        if(goal_fatLoss.isChecked())
        {
            bmr-= 450;
        }

        if(goal_maintainance.isChecked())
        {
            bmr = bmr;
        }

        if(goal_muscleGain.isChecked())
        {
            bmr += 450;
        }

        return bmr;
    }

    public String verifyTarget()
    {
        String target = "";
     if (goal_fatLoss.isChecked())
     {
         target = "fatloss";
     }
     if (goal_maintainance.isChecked())
     {
         target = "maintainance";
     }
     if(goal_muscleGain.isChecked())
     {
         target= "muscleGain";
     }
        return  target;
    }

    public void saveData()
    {
        SharedPreferences sharedPreferences = this.getSharedPreferences("profileData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("weight", weight_.getText().toString());
        editor.putString("height", height_.getText().toString());
        editor.putString("age", age_.getText().toString());
        editor.commit();
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

        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
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
}
