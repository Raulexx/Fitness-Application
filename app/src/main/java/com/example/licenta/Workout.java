package com.example.licenta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Workout extends MainActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TableRow workout_chest;
    TableRow workout_back;
    TableRow workout_arms;
    TableRow workout_shoulders;
    TableRow workout_abs;
    TableRow workout_legs;

    DatabaseHelper DbWorkout;

    FloatingActionButton createWorkout;
    ListView custom_workout_list;

    ArrayList<Workouts> listaMyWorkouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

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

        //endregion  #-----------------Cod NavBar--------------


        //region ---------------UI assignment--------------

        workout_chest = findViewById(R.id.workout_chest);
        workout_back = findViewById(R.id.workout_back);
        workout_arms = findViewById(R.id.workout_arms);
        workout_shoulders = findViewById(R.id.workout_shoulders);
        workout_abs = findViewById(R.id.workout_abs);
        workout_legs= findViewById(R.id.workout_legs);
        createWorkout = findViewById(R.id.workout_createWorkout);

        custom_workout_list = findViewById(R.id.custom_workout_list);

        //endregion


        //region --------------Popup workout pick----------------

        workout_chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkoutPopup(1);

            }
        });

        workout_shoulders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openWorkoutPopup(2);

            }
        });

        workout_arms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openWorkoutPopup(3);

            }
        });

        workout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openWorkoutPopup(4);

            }
        });
        workout_abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openWorkoutPopup(5);

            }
        });
        workout_legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openWorkoutPopup(6);

            }
        });


        //endregion

        listaMyWorkouts = new ArrayList<Workouts>();
        DbWorkout = new DatabaseHelper(this);


        createWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openWorkoutCreatorDialog();
            }
        });


        Cursor rezultat;
        rezultat = DbWorkout.getMyWorkoutsIDandName();
        while (rezultat.moveToNext()) {
            Workouts workoutaux;
            workoutaux= new Workouts();
            workoutaux.ID = rezultat.getString(0);
            workoutaux.Name=rezultat.getString(1);
            listaMyWorkouts.add(workoutaux);
        }




        CustomAdapterMyWorkouts customAdapterMyWorkouts = new CustomAdapterMyWorkouts();
        custom_workout_list.setAdapter(customAdapterMyWorkouts);

            custom_workout_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(Workout.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                }
            });
















    }


    public void openWorkoutPopup(int muscle_group)
    {
        int target = 2;
        SharedPreferences sharedPreferences = getSharedPreferences("profileData", Context.MODE_PRIVATE);
        String aux = sharedPreferences.getString("target",     "N/A");


        if(aux.equals("fatloss"))
        {
            target = 1;
        }
        if(aux.equals("maintainance"))
        {
            target = 2;
        }
        if(aux.equals("muscleGain"))
        {
            target = 3;
        }


        PopupWorkout popupWorkout = new PopupWorkout();
        Bundle bundle = new Bundle();
        bundle.putDouble("muscle_group" , muscle_group);
        bundle.putDouble("target" , target);
        popupWorkout.setArguments(bundle);
        popupWorkout.show(getSupportFragmentManager(),"Workout_layer");

    }

    public void openWorkoutCreatorDialog()
    {
        PopupCreateWorkout popupCreateWorkout = new PopupCreateWorkout();
        popupCreateWorkout.show(getSupportFragmentManager(), "Workout");
    }

    public void openMySelectedWorkoutDialog(int workoutID)
    {
        PopupShowCustomWorkouts popusShowCustomWorkouts = new PopupShowCustomWorkouts();
        Bundle bundle = new Bundle();
        bundle.putDouble("id_workout" , workoutID);
        popusShowCustomWorkouts.setArguments(bundle);
        popusShowCustomWorkouts.show(getSupportFragmentManager() , "id_workout");

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

        }
        else if (id == R.id.calculator)
        {
            Intent intent = new Intent (this, Calculator.class);
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

    public void clickNew(View view) {

    }

    class CustomAdapterMyWorkouts extends BaseAdapter {


        @Override
        public int getCount() {
            return listaMyWorkouts.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent)  {

            convertView= getLayoutInflater().inflate(R.layout.custom_workout_list_single_item , null);

            TextView workout_name = convertView.findViewById(R.id.myworkout_name);
            ImageButton delete = convertView.findViewById(R.id.myworkout_delete_button);
            workout_name.setText(listaMyWorkouts.get(position).Name);


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DbWorkout.deleteDataInMyWorkouts(Integer.parseInt(listaMyWorkouts.get(position).ID));
                    listaMyWorkouts.remove(position);
                    notifyDataSetChanged();
                    recreate();
                }
            });

            workout_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openMySelectedWorkoutDialog(Integer.parseInt(listaMyWorkouts.get(position).ID));
                }
            });



            return convertView;
        }
    }







    public class Workouts
    {
        String ID;
        String Name;
    }
}
