package com.example.licenta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PopupCalculator extends AppCompatDialogFragment {

 //region  -----------------------UI connect-----------------------

    TextView kcalFinal;
    String afisareKcalFinal;

    TextView proteinaIntake;
    TextView carbohidratiIntake;
    TextView grasimiIntake;

    String afisareProteina;
    String afisareCarbohidrati;
    String afisareGrasimi;

    int proteina;
    int carbohidrati;
    int grasimi;

    //endregion

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_rezultat_macronutrienti,null);

       final DatabaseHelper db = new DatabaseHelper(getContext());
       final String date  = new SimpleDateFormat("MMM, dd , yyyy" , Locale.getDefault()).format(new Date());

        kcalFinal = view.findViewById(R.id.kcalCalculated);
        proteinaIntake = view.findViewById(R.id.proteinIntake);
        carbohidratiIntake = view.findViewById(R.id.carbsIntake);
        grasimiIntake = view.findViewById(R.id.fatsIntake);

         afisareKcalFinal= String.valueOf((int)getArguments().getDouble("kcal_final"));

         proteina = calculatorProteina(Integer.parseInt(afisareKcalFinal));
         carbohidrati= calculatorCarbohidrat(Integer.parseInt(afisareKcalFinal));
         grasimi = calculatorGrasime(Integer.parseInt(afisareKcalFinal));


        kcalFinal.setText(afisareKcalFinal);
        proteinaIntake.setText(String.valueOf(proteina));
        carbohidratiIntake.setText(String.valueOf(carbohidrati));
        grasimiIntake.setText(String.valueOf(grasimi));




        builder.setView(view).setTitle("Result").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
       .setPositiveButton("Set my Macros", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {

            saveData();

               Intent intent = new Intent(getContext(),MainActivity.class );
               getActivity().startActivity(intent);

           }

       });


        return builder.create();
    }

    private int calculatorProteina(int kcal)
    {
        int gramProteina = 0;

        // cod in cazul in care ma gandesc sa fac procentele in functie de procentele alese

        gramProteina = (((kcal/4)*30)/100);

        return gramProteina;
    }

    private int calculatorCarbohidrat(int kcal)
    {
        double gramCarbohidrat=0 ;
        gramCarbohidrat = (((kcal/4)*40)/100);
        return (int) gramCarbohidrat;
    }

    private int calculatorGrasime(int kcal)
    {
        int gramGrasime=0 ;
        gramGrasime = (((kcal/9)*30)/100);
        return gramGrasime;
    }

    private void saveData()
    {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("profileData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("kcal", kcalFinal.getText().toString());
        editor.putString("protein", proteinaIntake.getText().toString());
        editor.putString("carbs", carbohidratiIntake.getText().toString());
        editor.putString("fats", grasimiIntake.getText().toString());

        editor.commit();

        Toast.makeText(getContext(), "Data saved", Toast.LENGTH_LONG);

    }




}



