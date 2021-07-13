package com.example.licenta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PopupWorkout extends AppCompatDialogFragment {

    int muscle_group;
    String muscleSelected;


    TextView title_1 ;
    TextView standard_1;
    TextView recomanded_1;

    ImageView image_1;

    ListView workout_muscle_list ;


    //region -----------------CHEST Workout------------------

    String chest_workout[] ={   "Incline Bench Press",
                                "Flat Bench Press",
                                "Decline Bench Press",
                                "Cables up section",
                                "Cables middle section",
                                "Cables down section"};

    String chest_workout_standard[] = { "Standard : 3 Sets of 10 reps",
                                        "Standard : 3 Sets of 10 reps",
                                        "Standard : 3 Sets of 10 reps",
                                        "Standard : 3 Sets of 10 reps",
                                        "Standard : 3 Sets of 10 reps",
                                        "Standard : 3 Sets of 10 reps"};

    String chest_workout_recommanded_lose[] = { "Recommanded : 3-4 Sets of 12-16 light weight",
                                                "Recommanded : 3-4 Sets of 12-16 light weight",
                                                "Recommanded : 3-4 Sets of 12-16 light weight",
                                                "Recommanded : 3-4 Sets of 12-16 light weight",
                                                "Recommanded : 3-4 Sets of 12-16 light weight",
                                                "Recommanded : 3-4 Sets of 12-16 light weight"};

    String chest_workout_recommanded_maintain[] = { "Recommanded : 3 Sets of 10 medium weight",
                                                    "Recommanded : 3 Sets of 10 medium weight",
                                                    "Recommanded : 3 Sets of 10 medium weight",
                                                    "Recommanded : 3 Sets of 10 medium weight",
                                                    "Recommanded : 3 Sets of 10 medium weight",
                                                    "Recommanded : 3 Sets of 10 medium weight"};

    String chest_workout_recommanded_grow[] = { "Recommanded : 3 Sets of 6-10 heavy weight",
                                                "Recommanded : 3 Sets of 6-10 heavy weight",
                                                "Recommanded : 3 Sets of 6-10 heavy weight",
                                                "Recommanded : 3 Sets of 6-10 heavy weight",
                                                "Recommanded : 3 Sets of 6-10 heavy weight",
                                                "Recommanded : 3 Sets of 6-10 heavy weight"};

    int chest_workout_images[] = {  R.drawable.icon_profile,
                                    R.drawable.icon_profile,
                                    R.drawable.icon_profile,
                                    R.drawable.icon_profile,
                                    R.drawable.icon_profile,
                                    R.drawable.icon_profile};

    //endregion

    //region -----------------SHOULDER Workout---------------

    String shoulder_workout[] ={"Bar rise infront head",
                                "Bar rise back head",
                                "Dumbell lateral rise",
                                "Dumbell front rise",
                                "Bar vertical rise",
                                "Dumbell backwards pull"};

    String shoulder_workout_standard[] = {  "Standard : 3 Sets of 10 reps",
                                            "Standard : 3 Sets of 10 reps",
                                            "Standard : 3 Sets of 10 reps",
                                            "Standard : 3 Sets of 10 reps",
                                            "Standard : 3 Sets of 10 reps",
                                            "Standard : 3 Sets of 10 reps"};

    String shoulder_workout_recommanded_lose[] = {  "Recommanded : 3-4 Sets of 12-16 light weight",
                                                    "Recommanded : 3-4 Sets of 12-16 light weight",
                                                    "Recommanded : 3-4 Sets of 12-16 light weight",
                                                    "Recommanded : 3-4 Sets of 12-16 light weight",
                                                    "Recommanded : 3-4 Sets of 12-16 light weight",
                                                    "Recommanded : 3-4 Sets of 12-16 light weight"};

    String shoulder_workout_recommanded_maintain[] = {  "Recommanded : 3 Sets of 10 medium weight",
                                                        "Recommanded : 3 Sets of 10 medium weight",
                                                        "Recommanded : 3 Sets of 10 medium weight",
                                                        "Recommanded : 3 Sets of 10 medium weight",
                                                        "Recommanded : 3 Sets of 10 medium weight",
                                                        "Recommanded : 3 Sets of 10 medium weight"};

    String shoulder_workout_recommanded_grow[] = {  "Recommanded : 3 Sets of 6-10 heavy weight",
                                                    "Recommanded : 3 Sets of 6-10 heavy weight",
                                                    "Recommanded : 3 Sets of 6-10 heavy weight",
                                                    "Recommanded : 3 Sets of 6-10 heavy weight",
                                                    "Recommanded : 3 Sets of 6-10 heavy weight",
                                                    "Recommanded : 3 Sets of 6-10 heavy weight"};

    int shoulder_workout_images[] = {   R.drawable.icon_profile,
                                        R.drawable.icon_profile,
                                        R.drawable.icon_profile,
                                        R.drawable.icon_profile,
                                        R.drawable.icon_profile,
                                        R.drawable.icon_profile};

    //endregion

    //region -----------------ARMS Workout-------------------

    String arms_workout[] ={"Triceps rope pulls" ,
                            "Triceps V bar push" ,
                            "Dumbell rise above head" ,
                            "Dips" ,
                            "Biceps Dumbell alternatives" ,
                            "Biceps Bar rise" ,
                            "Biceps Hammers"};

    String arms_workout_standard[] = {  "Standard : 3 Sets of 10 reps",
                                        "Standard : 3 Sets of 10 reps",
                                        "Standard : 3 Sets of 10 reps",
                                        "Standard : 3 Sets of 10 reps",
                                        "Standard : 3 Sets of 10 reps",
                                        "Standard : 3 Sets of 10 reps",
                                        "Standard : 3 Sets of 10 reps"};

    String arms_workout_recommanded_lose[] = {  "Recommanded : 3-4 Sets of 12-16 light weight",
                                                "Recommanded : 3-4 Sets of 12-16 light weight",
                                                "Recommanded : 3-4 Sets of 12-16 light weight",
                                                "Recommanded : 3-4 Sets of 12-16 light weight",
                                                "Recommanded : 3-4 Sets of 12-16 light weight",
                                                "Recommanded : 3-4 Sets of 12-16 light weight",
                                                "Recommanded : 3-4 Sets of 12-16 light weight"};

    String arms_workout_recommanded_maintain[] = {  "Recommanded : 3 Sets of 10 medium weight",
                                                    "Recommanded : 3 Sets of 10 medium weight",
                                                    "Recommanded : 3 Sets of 10 medium weight",
                                                    "Recommanded : 3 Sets of 10 medium weight",
                                                    "Recommanded : 3 Sets of 10 medium weight",
                                                    "Recommanded : 3 Sets of 10 medium weight",
                                                    "Recommanded : 3 Sets of 10 medium weight"};

    String arms_workout_recommanded_grow[] = {  "Recommanded : 3 Sets of 6-10 heavy weight",
                                                "Recommanded : 3 Sets of 6-10 heavy weight",
                                                "Recommanded : 3 Sets of 6-10 heavy weight",
                                                "Recommanded : 3 Sets of 6-10 heavy weight",
                                                "Recommanded : 3 Sets of 6-10 heavy weight",
                                                "Recommanded : 3 Sets of 6-10 heavy weight",
                                                "Recommanded : 3 Sets of 6-10 heavy weight"};

    int arms_workout_images[] = {   R.drawable.icon_profile,
                                    R.drawable.icon_profile,
                                    R.drawable.icon_profile,
                                    R.drawable.icon_profile,
                                    R.drawable.icon_profile,
                                    R.drawable.icon_profile,
                                    R.drawable.icon_profile};

        //endregion

    //region -----------------BACK Workout-------------------

    String back_workout[] ={
            "Front Lats Pullups" ,
            "Back Lats Pullups" ,
            "Rows" ,
            "Deadlifts" ,
            "Up Cable Rows"};

    String back_workout_standard[] = {
            "Standard : 3 Sets of 10 reps",
            "Standard : 3 Sets of 10 reps",
            "Standard : 3 Sets of 10 reps",
            "Standard : 3 Sets of 10 reps",
            "Standard : 3 Sets of 10 reps"};

    String back_workout_recommanded_lose[] = {
            "Recommanded : 3-4 Sets of 12-16 light weight",
            "Recommanded : 3-4 Sets of 12-16 light weight",
            "Recommanded : 3-4 Sets of 12-16 light weight",
            "Recommanded : 3-4 Sets of 12-16 light weight",
            "Recommanded : 3-4 Sets of 12-16 light weight"};

    String back_workout_recommanded_maintain[] = {
            "Recommanded : 3 Sets of 10 medium weight",
            "Recommanded : 3 Sets of 10 medium weight",
            "Recommanded : 3 Sets of 10 medium weight",
            "Recommanded : 3 Sets of 10 medium weight",
            "Recommanded : 3 Sets of 10 medium weight"};

    String back_workout_recommanded_grow[] = {
            "Recommanded : 3 Sets of 6-10 heavy weight",
            "Recommanded : 3 Sets of 6-10 heavy weight",
            "Recommanded : 3 Sets of 6-10 heavy weight",
            "Recommanded : 3 Sets of 6-10 heavy weight",
            "Recommanded : 3 Sets of 6-10 heavy weight",};

    int back_workout_images[] = {
            R.drawable.icon_profile,
            R.drawable.icon_profile,
            R.drawable.icon_profile,
            R.drawable.icon_profile,
            R.drawable.icon_profile};

    //endregion

    //region -----------------ABS Workout--------------------

    String abs_workout[] ={
            "Crunches" ,
            "Leg rises" ,
            "Plank" ,
            "Side Crunches" ,
            "Bicycles" ,
            "Mountain Climbers"};

    String abs_workout_standard[] = {
            "Standard : 3 Sets of 25 reps",
            "Standard : 3 Sets of 25 reps",
            "Standard : 3 Sets of 30 sec",
            "Standard : 3 Sets of 30 reps",
            "Standard : 3 Sets of 40 reps",
            "Standard : 3 Sets of 40 reps"};

    String abs_workout_recommanded_lose[] = {
            "Recomanded : 3 Sets of 25 reps",
            "Recomanded : 3 Sets of 25 reps",
            "Recomanded : 3 Sets of 30 sec",
            "Recomanded : 3 Sets of 30 reps",
            "Recomanded : 3 Sets of 40 reps",
            "Recomanded : 3 Sets of 40 reps"};

    String abs_workout_recommanded_maintain[] = {
            "Recomanded : 3 Sets of 15 reps",
            "Recomanded : 3 Sets of 15 reps",
            "Recomanded : 3 Sets of 30 sec",
            "Recomanded : 3 Sets of 20 reps",
            "Recomanded : 3 Sets of 30 reps",
            "Recomanded : 3 Sets of 30 reps"};

    String abs_workout_recommanded_grow[] = {
            "Recommanded : 3 Sets of 10 reps",
            "Recommanded : 3 Sets of 10 reps",
            "Recommanded : 3 Sets of 20 sec",
            "Recommanded : 3 Sets of 10 reps",
            "Recommanded : 3 Sets of 10 reps",
            "Recommanded : 3 Sets of 10 reps"};

    int abs_workout_images[] = {
            R.drawable.icon_profile,
            R.drawable.icon_profile,
            R.drawable.icon_profile,
            R.drawable.icon_profile,
            R.drawable.icon_profile,
            R.drawable.icon_profile};

    //endregion

    //region -----------------LEGS Workout-------------------


    String legs_workout[] ={
            "Leg Extensions" ,
            "Leg Contractions" ,
            "Lungees" ,
            "Squats" ,
            "Inner Tights" ,
            "External Tights",
            "Calves"};

    String legs_workout_standard[] = {
            "Standard : 3 Sets of 10 reps",
            "Standard : 3 Sets of 10 reps",
            "Standard : 3 Sets of 10 reps",
            "Standard : 3 Sets of 10 reps",
            "Standard : 3 Sets of 10 reps",
            "Standard : 3 Sets of 10 reps",
            "Standard : 3 Sets of 10 reps"};

    String legs_workout_recommanded_lose[] = {
            "Recommanded : 3-4 Sets of 12-16 light weight",
            "Recommanded : 3-4 Sets of 12-16 light weight",
            "Recommanded : 3-4 Sets of 12-16 light weight",
            "Recommanded : 3-4 Sets of 12-16 light weight",
            "Recommanded : 3-4 Sets of 12-16 light weight",
            "Recommanded : 3-4 Sets of 12-16 light weight",
            "Recommanded : 3-4 Sets of 12-16 light weight"};

    String legs_workout_recommanded_maintain[] = {
            "Recommanded : 3 Sets of 10 medium weight",
            "Recommanded : 3 Sets of 10 medium weight",
            "Recommanded : 3 Sets of 10 medium weight",
            "Recommanded : 3 Sets of 10 medium weight",
            "Recommanded : 3 Sets of 10 medium weight",
            "Recommanded : 3 Sets of 10 medium weight",
            "Recommanded : 3 Sets of 10 medium weight"};

    String legs_workout_recommanded_grow[] = {
            "Recommanded : 3 Sets of 6-10 heavy weight",
            "Recommanded : 3 Sets of 6-10 heavy weight",
            "Recommanded : 3 Sets of 6-10 heavy weight",
            "Recommanded : 3 Sets of 6-10 heavy weight",
            "Recommanded : 3 Sets of 6-10 heavy weight",
            "Recommanded : 3 Sets of 6-10 heavy weight",
            "Recommanded : 3 Sets of 6-10 heavy weight"};

    int legs_workout_images[] = {
            R.drawable.icon_profile,
            R.drawable.icon_profile,
            R.drawable.icon_profile,
            R.drawable.icon_profile,
            R.drawable.icon_profile,
            R.drawable.icon_profile,
            R.drawable.icon_profile};

    //endregion



    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.workout_selectedworkout_show,null);



        muscle_group= Integer.parseInt(String.valueOf((int)getArguments().getDouble("muscle_group")));



        if(muscle_group == 1)
        {
            muscleSelected = "chest";
        }

        if(muscle_group == 2)
        {
            muscleSelected = "shoulders";
        }

        if(muscle_group == 3)
        {
            muscleSelected = "arms";
        }
        if(muscle_group == 4)
        {
            muscleSelected = "Back";
        }
        if(muscle_group == 5)
        {
            muscleSelected = "abs";
        }
        if(muscle_group == 6)
        {
            muscleSelected = "legs";
        }








        workout_muscle_list = view.findViewById(R.id.workout_muscle_list);
        PopupWorkout.CustomAdapter muscleListAdapter = new PopupWorkout.CustomAdapter();
        workout_muscle_list.setAdapter(muscleListAdapter);




        builder.setView(view).setTitle(muscleSelected).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }

                });


        return builder.create();
    }


    class CustomAdapter extends BaseAdapter {


    @Override
    public int getCount() {

        if(muscle_group == 1)
        {
            return chest_workout.length;
        }
        else if( muscle_group == 2)
        {
            return shoulder_workout.length;
        }
        else if( muscle_group == 3)
        {
            return arms_workout.length;
        }
        else if( muscle_group == 4)
        {
            return back_workout.length;
        }
        else if( muscle_group == 5)
        {
            return abs_workout.length;
        }
        else
            return  legs_workout.length;


    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= getLayoutInflater().inflate(R.layout.custom_workout_listview , null);

        int target = (int) getArguments().getDouble("target");

        title_1 = convertView.findViewById(R.id.workout_layer_title_1);
        image_1 = convertView.findViewById(R.id.workout_layer_image_1);
        standard_1 = convertView.findViewById(R.id.workout_layer_standard_1);
        recomanded_1 = convertView.findViewById(R.id.workout_layer_recomanded_1);


        if(muscle_group == 1)
        {
            title_1.setText(chest_workout[position]);
            image_1.setBackground(getResources().getDrawable(chest_workout_images[position]));
            standard_1.setText(chest_workout_standard[position]);


            if(target == 1)
            {
                recomanded_1.setText(chest_workout_recommanded_lose[position]);
            }

            if (target == 2)
            {
                recomanded_1.setText(chest_workout_recommanded_maintain[position]);
            }
            if (target == 3)
            {
                recomanded_1.setText(chest_workout_recommanded_grow[position]);
            }

        }

        else if (muscle_group == 2)
        {
            title_1.setText(shoulder_workout[position]);
            image_1.setBackground(getResources().getDrawable(shoulder_workout_images[position]));
            standard_1.setText(shoulder_workout_standard[position]);
            recomanded_1.setText(shoulder_workout_recommanded_lose[position]);

            if(target == 1)
            {
                recomanded_1.setText(shoulder_workout_recommanded_lose[position]);
            }

            if (target == 2)
            {
                recomanded_1.setText(shoulder_workout_recommanded_maintain[position]);
            }
            if (target == 3)
            {
                recomanded_1.setText(shoulder_workout_recommanded_grow[position]);
            }
        }
        else if (muscle_group == 3)
        {
            title_1.setText(arms_workout[position]);
            image_1.setBackground(getResources().getDrawable(arms_workout_images[position]));
            standard_1.setText(arms_workout_standard[position]);

            if(target == 1)
            {
                recomanded_1.setText(arms_workout_recommanded_lose[position]);
            }

            if (target == 2)
            {
                recomanded_1.setText(arms_workout_recommanded_maintain[position]);
            }
            if (target == 3)
            {
                recomanded_1.setText(arms_workout_recommanded_grow[position]);
            }

        }
        else if (muscle_group == 4)
        {
            title_1.setText(back_workout[position]);
            image_1.setBackground(getResources().getDrawable(back_workout_images[position]));
            standard_1.setText(back_workout_standard[position]);

            if(target == 1)
            {
                recomanded_1.setText(back_workout_recommanded_lose[position]);
            }

            if (target == 2)
            {
                recomanded_1.setText(back_workout_recommanded_maintain[position]);
            }
            if (target == 3)
            {
                recomanded_1.setText(back_workout_recommanded_grow[position]);
            }

        }
        else if (muscle_group == 5)
        {
            title_1.setText(abs_workout[position]);
            image_1.setBackground(getResources().getDrawable(abs_workout_images[position]));
            standard_1.setText(abs_workout_standard[position]);

            if(target == 1)
            {
                recomanded_1.setText(abs_workout_recommanded_lose[position]);
            }

            if (target == 2)
            {
                recomanded_1.setText(abs_workout_recommanded_maintain[position]);
            }
            if (target == 3)
            {
                recomanded_1.setText(abs_workout_recommanded_grow[position]);
            }
        }
        else if (muscle_group == 6)
        {
            title_1.setText(legs_workout[position]);
            image_1.setBackground(getResources().getDrawable(legs_workout_images[position]));
            standard_1.setText(legs_workout_standard[position]);

            if(target == 1)
            {
                recomanded_1.setText(legs_workout_recommanded_lose[position]);
            }

            if (target == 2)
            {
                recomanded_1.setText(legs_workout_recommanded_maintain[position]);
            }
            if (target == 3)
            {
                recomanded_1.setText(legs_workout_recommanded_grow[position]);
            }
        }

        return convertView;
    }

}

}