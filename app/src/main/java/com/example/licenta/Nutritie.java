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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Nutritie extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView remaining_kcal;
    TextView remaining_protein;
    TextView remaining_carbs;
    TextView remaining_fats;

    ListView breakfast;
    ListView lunch;
    ListView dinner;



    ArrayList<FoodItem> listaAlimenteBreakfast;
    ArrayList<FoodItem> listaAlimenteLunch;
    ArrayList<FoodItem> listaAlimenteDinner;

    FloatingActionButton addFood;

    FoodItem spanac = new FoodItem();
    FoodItem rahat = new FoodItem();

    FoodItem rahat0 = new FoodItem();
    FoodItem rahat11 = new FoodItem();

     static DatabaseHelper DBnutritie;

    TextView addFoodBreakfast;
    TextView addFoodLunch;
    TextView addFoodDinner;

    int suma_kcal_remaining;
    int suma_protein_remaining;
    int suma_carbs_remaining;
    int suma_fat_remaining;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritie);

       DBnutritie = new DatabaseHelper(this);

        listaAlimenteBreakfast =  new ArrayList<FoodItem>();
        listaAlimenteLunch = new ArrayList<FoodItem>();
        listaAlimenteDinner = new ArrayList<FoodItem>();

        final String actualDate=new SimpleDateFormat("MMM, dd , yyyy" , Locale.getDefault()).format(new Date());


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


        //region --------------------Assignation to UI-------------------

        remaining_kcal = findViewById(R.id.remaining_Kcal);
        remaining_protein = findViewById(R.id.remaining_Protein);
        remaining_carbs = findViewById(R.id.remaining_Carbs);
        remaining_fats = findViewById(R.id.remaining_Fats);

        addFood = findViewById(R.id.nutrition_add_food);

        addFoodBreakfast = findViewById(R.id.nutrition_addFood_breakfast);
        addFoodLunch = findViewById(R.id.nutrition_addFood_lunch);
        addFoodDinner = findViewById(R.id.nutrition_addFood_dinner);

        //endregion


        loadData();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(calendar.getTime());











        breakfast = findViewById(R.id.listview_breakfast);
        CustomAdapterBreakfast breakfastadapter = new CustomAdapterBreakfast();
        breakfast.setAdapter(breakfastadapter);


        lunch = findViewById(R.id.listview_lunch);
        CustomAdapterLunch lunchadapter = new CustomAdapterLunch();
        lunch.setAdapter(lunchadapter);

        dinner = findViewById(R.id.listview_dinner);
        CustomAdapterDinner dineradapter = new CustomAdapterDinner();
        dinner.setAdapter(dineradapter);



        DBnutritie = new DatabaseHelper(this);
        Cursor rezultat;
        rezultat = DBnutritie.retriveDataFromFoodDiary(String.valueOf(1));
        {
            while (rezultat.moveToNext()) {
                FoodItem aliment;
                aliment = new FoodItem();

                aliment.food_item_date= rezultat.getString(1);

                if(!aliment.food_item_date.equals(actualDate))
                {
                    DBnutritie.deleteAllData();
                }

                aliment.food_item_ID = rezultat.getString(0);

                aliment.food_item_name = rezultat.getString(7);
                aliment.food_item_kcal = rezultat.getString(8);
                aliment.food_item_protein = rezultat.getString(9);
                aliment.food_item_carbs = rezultat.getString(10);
                aliment.food_item_fats = rezultat.getString(11);
                listaAlimenteBreakfast.add(aliment);


            }
        }

        rezultat=DBnutritie.retriveDataFromFoodDiary(String.valueOf(2));
        {
            while (rezultat.moveToNext()) {
                FoodItem aliment;
                aliment = new FoodItem();
                aliment.food_item_ID = rezultat.getString(0);
                aliment.food_item_name = rezultat.getString(7);
                aliment.food_item_kcal = rezultat.getString(8);
                aliment.food_item_protein = rezultat.getString(9);
                aliment.food_item_carbs = rezultat.getString(10);
                aliment.food_item_fats = rezultat.getString(11);
                listaAlimenteLunch.add(aliment);
            }

        }

        rezultat=DBnutritie.retriveDataFromFoodDiary(String.valueOf(3));
        {
            while (rezultat.moveToNext()) {
                FoodItem aliment;
                aliment = new FoodItem();
                aliment.food_item_ID = rezultat.getString(0);
                aliment.food_item_name = rezultat.getString(7);
                aliment.food_item_kcal = rezultat.getString(8);
                aliment.food_item_protein = rezultat.getString(9);
                aliment.food_item_carbs = rezultat.getString(10);
                aliment.food_item_fats = rezultat.getString(11);
                listaAlimenteDinner.add(aliment);
            }

        }


        SharedPreferences sharedPreferences = getSharedPreferences("profileData", Context.MODE_PRIVATE);
        int remaining_kcal_aux = Integer.parseInt(sharedPreferences.getString("kcal",     "2000"));
        int remaining_protein_aux = Integer.parseInt(sharedPreferences.getString("protein",     "100"));
        int remaining_carbs_aux = Integer.parseInt(sharedPreferences.getString("carbs",     "100"));
        int remaining_fats_aux = Integer.parseInt(sharedPreferences.getString("fats",     "100"));



        for(FoodItem item: listaAlimenteBreakfast)
        {
            suma_kcal_remaining+=Integer.parseInt(item.food_item_kcal);
            suma_protein_remaining+=Integer.parseInt(item.food_item_protein);
            suma_carbs_remaining +=Integer.parseInt(item.food_item_carbs);
            suma_fat_remaining += Integer.parseInt(item.food_item_fats);
        }

        for(FoodItem item: listaAlimenteLunch)
        {
            suma_kcal_remaining+=Integer.parseInt(item.food_item_kcal);
            suma_protein_remaining+=Integer.parseInt(item.food_item_protein);
            suma_carbs_remaining +=Integer.parseInt(item.food_item_carbs);
            suma_fat_remaining += Integer.parseInt(item.food_item_fats);
        }

        for(FoodItem item: listaAlimenteDinner)
        {
            suma_kcal_remaining+=Integer.parseInt(item.food_item_kcal);
            suma_protein_remaining+=Integer.parseInt(item.food_item_protein);
            suma_carbs_remaining +=Integer.parseInt(item.food_item_carbs);
            suma_fat_remaining += Integer.parseInt(item.food_item_fats);
        }




        remaining_kcal.setText(String.valueOf(remaining_kcal_aux-suma_kcal_remaining));
        remaining_protein.setText(String.valueOf(remaining_protein_aux-suma_protein_remaining));
        remaining_carbs.setText(String.valueOf(remaining_carbs_aux-suma_carbs_remaining));
        remaining_fats.setText(String.valueOf(remaining_fats_aux-suma_fat_remaining));








        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createFoodDialog();

            }
        });


        addFoodBreakfast.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                addFoodDialog("breakfast");

            }
        });

        addFoodLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addFoodDialog("lunch");

            }
        });

        addFoodDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addFoodDialog("dinner");
            }
        });



    }


    public void createFoodDialog(){

        PopupCreateFood popupObject = new PopupCreateFood();
        Bundle bundle = new Bundle();
        popupObject.setArguments(bundle);
        popupObject.show(getSupportFragmentManager(),"Macros");


    }

    public void addFoodDialog(String meal){

        PopupAddFood popupObject = new PopupAddFood();
        Bundle bundle = new Bundle();
        bundle.putString("meal" , meal);
        popupObject.setArguments(bundle);
        popupObject.show(getSupportFragmentManager(),"meal");


    }

    private void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("profileData", Context.MODE_PRIVATE);

        remaining_kcal.setText(sharedPreferences.getString("kcal",     "2000"));
        remaining_protein.setText(sharedPreferences.getString("protein", "100"));
        remaining_carbs.setText(sharedPreferences.getString("carbs",   "100"));
        remaining_fats.setText(sharedPreferences.getString("fats",     "100"));
    }




    class CustomAdapterBreakfast extends BaseAdapter{


        @Override
        public int getCount() {
            return listaAlimenteBreakfast.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView= getLayoutInflater().inflate(R.layout.custom_nutrition_layer , null);

            TextView food_name = convertView.findViewById(R.id.food_name);
            TextView food_kcal = convertView.findViewById(R.id.food_kcal);
            ImageView delete_button = convertView.findViewById(R.id.delete_image);



            food_name.setText(listaAlimenteBreakfast.get(position).food_item_name);
            food_kcal.setText(listaAlimenteBreakfast.get(position).food_item_kcal);

            delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DBnutritie.deleteData(Integer.parseInt(listaAlimenteBreakfast.get(position).food_item_ID));
                    listaAlimenteBreakfast.remove(position);
                    notifyDataSetChanged();
                    recreate();

                }
            });


            return convertView;
        }
    }

    class CustomAdapterLunch extends BaseAdapter{

        @Override
        public int getCount() {
            return listaAlimenteLunch.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView= getLayoutInflater().inflate(R.layout.custom_nutrition_layer , null);

            TextView food_name = convertView.findViewById(R.id.food_name);
            TextView food_kcal = convertView.findViewById(R.id.food_kcal);
            ImageView delete_button = convertView.findViewById(R.id.delete_image);

            food_name.setText(listaAlimenteLunch.get(position).food_item_name);
            food_kcal.setText(listaAlimenteLunch.get(position).food_item_kcal);

            delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DBnutritie.deleteData(Integer.parseInt(listaAlimenteLunch.get(position).food_item_ID));
                    listaAlimenteLunch.remove(position);
                    notifyDataSetChanged();
                    recreate();
                }
            });


            return convertView;
        }
    }

    class CustomAdapterDinner extends BaseAdapter{


        @Override
        public int getCount() {
            return listaAlimenteDinner.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView= getLayoutInflater().inflate(R.layout.custom_nutrition_layer , null);

            TextView food_name = convertView.findViewById(R.id.food_name);
            TextView food_kcal = convertView.findViewById(R.id.food_kcal);
            ImageView delete_button = convertView.findViewById(R.id.delete_image);




            food_name.setText(listaAlimenteDinner.get(position).food_item_name);
            food_kcal.setText(listaAlimenteDinner.get(position).food_item_kcal);

            delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DBnutritie.deleteData(Integer.parseInt(listaAlimenteDinner.get(position).food_item_ID));
                    listaAlimenteDinner.remove(position);
                    notifyDataSetChanged();
                    recreate();
                }
            });


            return convertView;
        }
    }

    public class FoodItem {

        private String food_item_date ;
        private String food_item_name ;
        private String food_item_kcal ;
        private String food_item_protein;
        private String food_item_carbs;
        private String food_item_fats;
        private String food_item_ID;


        public String getFood_item_date() {
            return food_item_date;
        }

        public String getFood_item_name() {
            return food_item_name;
        }

        public void setFood_item_name(String food_item_name) {
            this.food_item_name = food_item_name;
        }

        public String getFood_item_kcal() {
            return food_item_kcal;
        }

        public void setFood_item_kcal(String food_item_kcal) {
            this.food_item_kcal = food_item_kcal;
        }

        public String getFood_item_protein() {
            return food_item_protein;
        }

        public void setFood_item_protein(String food_item_protein) {
            this.food_item_protein = food_item_protein;
        }

        public String getFood_item_carbs() {
            return food_item_carbs;
        }

        public void setFood_item_carbs(String food_item_carbs) {
            this.food_item_carbs = food_item_carbs;
        }

        public String getFood_item_fats() {
            return food_item_fats;
        }

        public void setFood_item_fats(String food_item_fats) {
            this.food_item_fats = food_item_fats;
        }

        public String getFood_item_ID() {
            return food_item_ID;
        }

        public void setFood_item_ID(String food_item_ID) {
            this.food_item_ID = food_item_ID;
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
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nutrition)
        {

        }
        else if (id == R.id.workout)
        {
            Intent intent = new Intent(this,Workout.class);
            startActivity(intent);
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
}

