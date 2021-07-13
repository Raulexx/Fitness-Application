package com.example.licenta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PopupCreateFood extends AppCompatDialogFragment {


    TextView newFoodName;
    TextView newFoodKcal;
    TextView newFoodProtein;
    TextView newFoodCarbs;
    TextView newFoodFats;

    RatingBar newFoodRating;

    DatabaseHelper Dbnutritie;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_create_food,null);

        newFoodName =  view.findViewById(R.id.newFoodName);
        newFoodKcal = view.findViewById(R.id.newFoodKcal);
        newFoodProtein = view.findViewById(R.id.newFoodProtein);
        newFoodCarbs = view.findViewById(R.id.newFoodCarbs);
        newFoodFats = view.findViewById(R.id.newFoodFats);
        newFoodRating = view.findViewById(R.id.ratingBar);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference nutrition = database.getReference("Nutrition");

        String date  = new SimpleDateFormat("MMM, dd , yyyy" , Locale.getDefault()).format(new Date());

        Dbnutritie = new DatabaseHelper(getContext());


        builder.setView(view).setTitle("Add Food").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })


                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                        if(newFoodName.getText().toString().equals(""))
                        {
                            Toast.makeText(getContext(), "Chose a name ",
                                    Toast.LENGTH_LONG).show();

                        }
                            else
                                {

                                    //region FIREBASE INSERT

                                    DatabaseReference aliment = nutrition.push();
                                    Nutrition obj = new Nutrition();

                                    obj.name = newFoodName.getText().toString();
                                    obj.kcal = newFoodKcal.getText().toString();
                                    obj.protein = newFoodProtein.getText().toString();
                                    obj.carbs = newFoodCarbs.getText().toString();
                                    obj.fats = newFoodFats.getText().toString();

                                    aliment.setValue(obj);

                                    //endregion

                                    //region SQLite INSERT

                                    Dbnutritie.insertDataInFoodItem(newFoodName.getText().toString() , newFoodKcal.getText().toString() , newFoodProtein.getText().toString() , newFoodCarbs.getText().toString(), newFoodFats.getText().toString() ,String.valueOf(newFoodRating.getRating()) );

                                    //endregion



                        }






                    }

                });


        return builder.create();
    }
}


class Nutrition {


    public String name;
    public String kcal;
    public String protein;
    public String carbs;
    public String fats;

}

class RecommendedFood {
    public String ID;
    public String name;
    public String kcal;
    public String protein;
    public String carbs;
    public String fats;
    public String rating;

}