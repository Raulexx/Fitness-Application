package com.example.licenta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class PopupCreateWorkout extends AppCompatDialogFragment {


    ListView listaExercitii;
    ListView listaMyWorkout_UI;
    ArrayList<Exercitii> listaCuExercitii;
    ArrayList<Exercitii> listaMyWorkout;
    DatabaseHelper DbWorkout;

    EditText nume_workout;


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.workout_create_workout, null);

        listaExercitii = view.findViewById(R.id.exercices_list);
        listaMyWorkout_UI = view.findViewById(R.id.myworkout_list);

        listaCuExercitii  = new ArrayList<Exercitii>();
        listaMyWorkout = new ArrayList<Exercitii>();

        nume_workout = view.findViewById(R.id.nume_workout);

        DbWorkout = new DatabaseHelper(getContext());



        boolean verificareExercitii = DbWorkout.verifyExercicesTabel();
        if(verificareExercitii)
        {
            DbWorkout.insertInExercices("Incline bench press");
            DbWorkout.insertInExercices("Decline bench press");
            DbWorkout.insertInExercices("Bench press");
            DbWorkout.insertInExercices("Lower Cable pull");
            DbWorkout.insertInExercices("Middle Cable pull");
            DbWorkout.insertInExercices("High Cable pull");
            DbWorkout.insertInExercices("High Lats pull front");
            DbWorkout.insertInExercices("High Lats pull back");
            DbWorkout.insertInExercices("Rows");
            DbWorkout.insertInExercices("Deadlifts");
            DbWorkout.insertInExercices("Back arm pull down");
            DbWorkout.insertInExercices("Front rises (Dumbell/Disk)");
            DbWorkout.insertInExercices("Lateral rises(Dumbell/cable)");
            DbWorkout.insertInExercices("Back risees (Dumbell)");
            DbWorkout.insertInExercices("Vertical bar rise");
            DbWorkout.insertInExercices("Bar front rise");
            DbWorkout.insertInExercices("Bar back rise");
            DbWorkout.insertInExercices("Dumbell alternatives");
            DbWorkout.insertInExercices("Dumbell hammers");
            DbWorkout.insertInExercices("Bar rise");
            DbWorkout.insertInExercices("Cables pull (Bicesps)");
            DbWorkout.insertInExercices("Dips");
            DbWorkout.insertInExercices("Cable single arm pushdown");
            DbWorkout.insertInExercices("Triceps pushdown");
            DbWorkout.insertInExercices("Skullcrusher");
            DbWorkout.insertInExercices("Closegrip benchpress");
            DbWorkout.insertInExercices("Squats");
            DbWorkout.insertInExercices("Leg press");
            DbWorkout.insertInExercices("Lungees");
            DbWorkout.insertInExercices("Leg Extensions");
            DbWorkout.insertInExercices("Lying curls");
            DbWorkout.insertInExercices("Calfs");
            DbWorkout.insertInExercices("Sit ups");
            DbWorkout.insertInExercices("Abs leg rise");
            DbWorkout.insertInExercices("Jack knifes");
            DbWorkout.insertInExercices("Leg pull ins");
            DbWorkout.insertInExercices("Toe touches");
            DbWorkout.insertInExercices("Crunches");
            DbWorkout.insertInExercices("Reverse crunches");
        }



        Cursor rezultat;
        rezultat = DbWorkout.getAllExercises();


            while (rezultat.moveToNext()) {
                Exercitii exercitiu;
                exercitiu = new Exercitii();
                exercitiu.ID = rezultat.getString(0);
                exercitiu.Name=rezultat.getString(1);
                listaCuExercitii.add(exercitiu);
            }


      final CustomAdapterMyWorkout customAdapterMyWorkout = new CustomAdapterMyWorkout();

        CustomAdapterExercise customAdapterExercise = new CustomAdapterExercise();
        listaExercitii.setAdapter(customAdapterExercise);

        listaExercitii.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if(listaMyWorkout.size()<20) {
                    listaMyWorkout.add(listaCuExercitii.get(position));
                    listaMyWorkout_UI.setAdapter(customAdapterMyWorkout);
                }
                else
                    Toast.makeText(getContext(),"Maximum number of exercises reached!",Toast.LENGTH_LONG).show();

            }
        });











        builder.setView(view).setTitle("Add exercices to Custom Workout").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(nume_workout.getText().toString().equals(""))
                        {
                            Toast.makeText(getContext(),"Please add a name",Toast.LENGTH_LONG).show();
                        }
                        else {
                            int nrExercitii = listaMyWorkout.size();
                            switch (nrExercitii)
                            {
                                case 0:  DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(),"", "", "", "", "", "", "", "", "", "","", "", "","", "", "", "", "", "", "");
                                            break;
                                case 1:  DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, "", "", "", "", "", "", "", "", "","", "", "","", "", "", "", "", "", "");
                                    break;
                                case 2:  DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, "", "", "", "", "", "", "", "","", "", "","", "", "", "", "", "", "");
                                    break;
                                case 3:  DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, "", "", "", "", "", "", "","", "", "","", "", "", "", "", "", "");
                                    break;
                                case 4:  DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, "", "", "", "", "", "","", "", "","", "", "", "", "", "", "");
                                    break;
                                case 5:  DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, "", "", "", "", "","", "", "","", "", "", "", "", "", "");
                                    break;
                                case 6:  DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, "", "", "", "","", "", "","", "", "", "", "", "", "");
                                    break;
                                case 7:  DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, "", "", "","", "", "","", "", "", "", "", "", "");
                                    break;
                                case 8:  DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, listaMyWorkout.get(7).ID, "", "","", "", "","", "", "", "", "", "", "");
                                    break;
                                case 9:  DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, listaMyWorkout.get(7).ID, listaMyWorkout.get(8).ID, "","", "", "","", "", "", "", "", "", "");
                                    break;
                                case 10: DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, listaMyWorkout.get(7).ID, listaMyWorkout.get(8).ID, listaMyWorkout.get(9).ID,"", "", "","", "", "", "", "", "", "");
                                    break;
                                case 11: DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, listaMyWorkout.get(7).ID, listaMyWorkout.get(8).ID, listaMyWorkout.get(9).ID, listaMyWorkout.get(10).ID, "", "","", "", "", "", "", "", "");
                                    break;
                                case 12: DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, listaMyWorkout.get(7).ID, listaMyWorkout.get(8).ID, listaMyWorkout.get(9).ID, listaMyWorkout.get(10).ID, listaMyWorkout.get(11).ID, "","", "", "", "", "", "", "");
                                    break;
                                case 13: DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, listaMyWorkout.get(7).ID, listaMyWorkout.get(8).ID, listaMyWorkout.get(9).ID, listaMyWorkout.get(10).ID, listaMyWorkout.get(11).ID, listaMyWorkout.get(12).ID,"", "", "", "", "", "", "");
                                    break;
                                case 14: DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, listaMyWorkout.get(7).ID, listaMyWorkout.get(8).ID, listaMyWorkout.get(9).ID, listaMyWorkout.get(10).ID, listaMyWorkout.get(11).ID, listaMyWorkout.get(12).ID, listaMyWorkout.get(13).ID, "", "", "", "", "", "");
                                    break;
                                case 15: DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, listaMyWorkout.get(7).ID, listaMyWorkout.get(8).ID, listaMyWorkout.get(9).ID, listaMyWorkout.get(10).ID, listaMyWorkout.get(11).ID, listaMyWorkout.get(12).ID, listaMyWorkout.get(13).ID, listaMyWorkout.get(14).ID, "", "", "", "", "");
                                    break;
                                case 16: DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, listaMyWorkout.get(7).ID, listaMyWorkout.get(8).ID, listaMyWorkout.get(9).ID, listaMyWorkout.get(10).ID, listaMyWorkout.get(11).ID, listaMyWorkout.get(12).ID, listaMyWorkout.get(13).ID, listaMyWorkout.get(14).ID, listaMyWorkout.get(15).ID, "", "", "", "");
                                    break;
                                case 17: DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, listaMyWorkout.get(7).ID, listaMyWorkout.get(8).ID, listaMyWorkout.get(9).ID, listaMyWorkout.get(10).ID, listaMyWorkout.get(11).ID, listaMyWorkout.get(12).ID, listaMyWorkout.get(13).ID, listaMyWorkout.get(14).ID, listaMyWorkout.get(15).ID, listaMyWorkout.get(16).ID, "", "", "");
                                    break;
                                case 18: DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, listaMyWorkout.get(7).ID, listaMyWorkout.get(8).ID, listaMyWorkout.get(9).ID, listaMyWorkout.get(10).ID, listaMyWorkout.get(11).ID, listaMyWorkout.get(12).ID, listaMyWorkout.get(13).ID, listaMyWorkout.get(14).ID, listaMyWorkout.get(15).ID, listaMyWorkout.get(16).ID, listaMyWorkout.get(17).ID, "", "");
                                    break;
                                case 19: DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, listaMyWorkout.get(7).ID, listaMyWorkout.get(8).ID, listaMyWorkout.get(9).ID, listaMyWorkout.get(10).ID, listaMyWorkout.get(11).ID, listaMyWorkout.get(12).ID, listaMyWorkout.get(13).ID, listaMyWorkout.get(14).ID, listaMyWorkout.get(15).ID, listaMyWorkout.get(16).ID, listaMyWorkout.get(17).ID, listaMyWorkout.get(18).ID, "");
                                    break;
                                case 20: DbWorkout.insertIntoMyWorkout(nume_workout.getText().toString(), listaMyWorkout.get(0).ID, listaMyWorkout.get(1).ID, listaMyWorkout.get(2).ID, listaMyWorkout.get(3).ID, listaMyWorkout.get(4).ID, listaMyWorkout.get(5).ID, listaMyWorkout.get(6).ID, listaMyWorkout.get(7).ID, listaMyWorkout.get(8).ID, listaMyWorkout.get(9).ID, listaMyWorkout.get(10).ID, listaMyWorkout.get(11).ID, listaMyWorkout.get(12).ID, listaMyWorkout.get(13).ID, listaMyWorkout.get(14).ID, listaMyWorkout.get(15).ID, listaMyWorkout.get(16).ID, listaMyWorkout.get(17).ID, listaMyWorkout.get(18).ID, listaMyWorkout.get(19).ID);
                                    break;
                            }
                                    getActivity().recreate();

                        }

                    }

                });


        return builder.create();


    }


    class CustomAdapterExercise extends BaseAdapter {


        @Override
        public int getCount() {
            return listaCuExercitii.size();
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

            convertView= getLayoutInflater().inflate(R.layout.exercices_list_single_item , null);

            TextView workout_name = convertView.findViewById(R.id.exercice_name);
            workout_name.setText(listaCuExercitii.get(position).Name);

            return convertView;
        }
    }


    class CustomAdapterMyWorkout extends BaseAdapter {


        @Override
        public int getCount() {
            return listaMyWorkout.size();
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

            convertView= getLayoutInflater().inflate(R.layout.myworkout_lis_single_item , null);

            TextView myWorkout_name = convertView.findViewById(R.id.myworkout_name);
            ImageButton delete = convertView.findViewById(R.id.myworkout_delete_button);


            myWorkout_name.setText(listaMyWorkout.get(position).Name);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listaMyWorkout.remove(position);
                    notifyDataSetChanged();
                }
            });



            notifyDataSetChanged();


            return convertView;
        }
    }


    public class Exercitii
    {
        String ID;
        String Name;
    }



}
