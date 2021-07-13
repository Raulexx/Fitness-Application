package com.example.licenta;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PersonalProgress extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper DbProgress;
    FloatingActionButton add_data ;
    TextView date;
    EditText kgUpdate;
    ArrayList<Update> listaInregistrariUpdate;
    ListView listaIntrari;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_progres);

        add_data = findViewById(R.id.add_personal_update_button);
        date = findViewById(R.id.date_display);
        kgUpdate = findViewById(R.id.kgUpdate);
        DbProgress = new DatabaseHelper(this);
        listaIntrari = findViewById(R.id.lista_cu_intrari);

        final String actualDate=new SimpleDateFormat("MMM, dd , yyyy" , Locale.getDefault()).format(new Date());

        closeKeyboard();

        date.setText(actualDate);

        listaInregistrariUpdate =new ArrayList<Update>();

        //region ------------NAVBAR CODE

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //endregion



        Cursor rezultat;
        rezultat = DbProgress.getUserProgressData();

            while (rezultat.moveToNext()) {
                Update intrare;
                intrare = new Update();
                intrare.ID = rezultat.getString(0);
                intrare.Date = rezultat.getString(1);
                intrare.Kg = rezultat.getString(2);
                listaInregistrariUpdate.add(intrare);
            }



        CustomAdapterIntrari customAdapterIntrari = new CustomAdapterIntrari();
        listaIntrari.setAdapter(customAdapterIntrari);








        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbProgress.insertIntoUserProgress(actualDate , kgUpdate.getText().toString());
                Toast.makeText(PersonalProgress.this, "Update succeded!!", Toast.LENGTH_SHORT).show();
                closeKeyboard();
                kgUpdate.setText("");
                recreate();
            }
        });










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

    @SuppressWarnings("StatementWithEmptyBody")
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


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void closeKeyboard()
    {
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }


    class CustomAdapterIntrari extends BaseAdapter {


        @Override
        public int getCount() {
            return listaInregistrariUpdate.size();
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

            convertView= getLayoutInflater().inflate(R.layout.personal_progres_listview_single_item , null);

            TextView date = convertView.findViewById(R.id.single_item_date);
            TextView kg = convertView.findViewById(R.id.single_item_kg);
            ImageView delete_button = convertView.findViewById(R.id.deleteButton);



            date.setText(listaInregistrariUpdate.get(position).Date);
            kg.setText(listaInregistrariUpdate.get(position).Kg);

            delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DbProgress.deleteInPersonalProgress(Integer.parseInt(listaInregistrariUpdate.get(position).ID));
                    listaInregistrariUpdate.remove(position);
                    notifyDataSetChanged();

                }
            });


            return convertView;
        }
    }

    public class Update
    {
        String ID;
        String Date;
        String Kg;
    }




}
