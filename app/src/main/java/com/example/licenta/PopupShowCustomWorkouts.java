package com.example.licenta;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


@SuppressLint("ValidFragment")
class PopupShowCustomWorkouts extends AppCompatDialogFragment {

    ListView showTheWorkout;
    ArrayList<Exercise> listaExercitii;
    DatabaseHelper DbDisplay;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_show_custom_workouts, null);

        DbDisplay = new DatabaseHelper(getContext());
        listaExercitii = new ArrayList<Exercise>();

        showTheWorkout = view.findViewById(R.id.ShowCustomWorkoutListview);
        String IDworkout= String.valueOf((int)getArguments().getDouble("id_workout"));

        Cursor rezultat;
        rezultat = DbDisplay.getMySpeicificWorkoutAfterID(IDworkout);
        while (rezultat.moveToNext()) {
            InregistrareWorkout auxiliar;
            auxiliar= new InregistrareWorkout();
            auxiliar.ID = rezultat.getString(0);
            auxiliar.Name = rezultat.getString(1);

            ArrayList<String>indexExercitii = new ArrayList<String>();
            for(int i = 2 ; i<=21 ; i++){
                indexExercitii.add(rezultat.getString(i));
            }

            Cursor rezultatExercitiu;


            for (String item: indexExercitii)
            {
                rezultatExercitiu = DbDisplay.getExerciseByID(item);
                while(rezultatExercitiu.moveToNext())
                {
                    Exercise aux = new Exercise();
                    aux.ID = rezultatExercitiu.getString(0);
                    aux.Nume = rezultatExercitiu.getString(1);
                    listaExercitii.add(aux);
                }

            }
        }

                CustomAdapterShowMyWorkout customAdapterShowMyWorkout = new CustomAdapterShowMyWorkout();
                showTheWorkout.setAdapter(customAdapterShowMyWorkout);














        builder.setView(view).setTitle("Workout").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })

                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        getActivity().recreate();


                    }

                });


        return builder.create();


    }

    class CustomAdapterShowMyWorkout extends BaseAdapter {


        @Override
        public int getCount() {
            return listaExercitii.size();
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

            convertView= getLayoutInflater().inflate(R.layout.popup_show_custom_workout_single_item , null);

            TextView workout_name = convertView.findViewById(R.id.name_exercise_from_custom_workout);
            workout_name.setText(listaExercitii.get(position).Nume);



            return convertView;
        }
    }


    public class InregistrareWorkout
    {
        String ID;
        String Name;
        String ex1;
        String ex2;
        String ex3;
        String ex4;
        String ex5;
        String ex6;
        String ex7;
        String ex8;
        String ex9;
        String ex10;
        String ex11;
        String ex12;
        String ex13;
        String ex14;
        String ex15;
        String ex16;
        String ex17;
        String ex18;
        String ex19;
        String ex20;
    }

    public class Exercise
    {
        String ID;
        String Nume;
    }



}