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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class PopupAddFood extends AppCompatDialogFragment {

    ListView standard_meal_list;
    ListView recommended_meal_list;


    ArrayList<Nutrition> foodListNutritionObject;
    ArrayList<RecommendedFood> foodListRecommandedList;


    DatabaseHelper Dbnutritie;

    final String date  = new SimpleDateFormat("MMM, dd , yyyy" , Locale.getDefault()).format(new Date());
    String mealSelected;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_add_food, null);


        recommended_meal_list = view.findViewById(R.id.recommended_meal_list);
        standard_meal_list = view.findViewById(R.id.standard_meal_list);

        foodListNutritionObject = new ArrayList<Nutrition>();
        foodListRecommandedList = new ArrayList<RecommendedFood>();

        mealSelected = getArguments().getString("meal");


//region --------------FIREBASE REQUEST DATA

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("Nutrition");


// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){

                    Nutrition post = ds.getValue(Nutrition.class);
                    foodListNutritionObject.add(post);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



//endregion


        //region --------------FIREBASE Adapter

        CustomAdapterMealList foodListAdapter = new CustomAdapterMealList();
        standard_meal_list.setAdapter(foodListAdapter);

        standard_meal_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();

                Dbnutritie = new DatabaseHelper(getContext());
                Cursor alimentSelectat;

                Nutrition auxiliar = foodListNutritionObject.get(position);
                Dbnutritie.insertDataInFoodItem(auxiliar.name, auxiliar.kcal, auxiliar.protein, auxiliar.carbs, auxiliar.fats, "0");
                alimentSelectat = Dbnutritie.getLastRow();

                while (alimentSelectat.moveToNext()) {
                    RecommendedFood AlimentSelectatAuxiliar;
                    AlimentSelectatAuxiliar = new RecommendedFood();
                    AlimentSelectatAuxiliar.ID = alimentSelectat.getString(0);
                    AlimentSelectatAuxiliar.name = alimentSelectat.getString(1);
                    AlimentSelectatAuxiliar.kcal = alimentSelectat.getString(2);
                    AlimentSelectatAuxiliar.protein = alimentSelectat.getString(3);
                    AlimentSelectatAuxiliar.carbs = alimentSelectat.getString(4);
                    AlimentSelectatAuxiliar.fats = alimentSelectat.getString(5);
                    AlimentSelectatAuxiliar.rating = alimentSelectat.getString(6);

                    if (mealSelected.equals("breakfast")) {
                        Dbnutritie.insertDataInFoodDiary(date, String.valueOf(1), String.valueOf(AlimentSelectatAuxiliar.ID));
                    }
                    if (mealSelected.equals("lunch")) {
                        Dbnutritie.insertDataInFoodDiary(date, String.valueOf(2), String.valueOf(AlimentSelectatAuxiliar.ID));
                    }
                    if (mealSelected.equals("dinner")) {
                        Dbnutritie.insertDataInFoodDiary(date, String.valueOf(3), String.valueOf(AlimentSelectatAuxiliar.ID));
                    }


                }
            }
        });


            //endregion




        //region SQLITE REQUEST DATA
        
        Dbnutritie = new DatabaseHelper(getContext());
        Cursor rezultat;
        rezultat = Dbnutritie.getAllData();

        if(rezultat.getCount()== 0)
        {
           // Toast.makeText(getContext(),"No data to show",Toast.LENGTH_LONG).show();
        }
        else {
            while (rezultat.moveToNext()) {
                RecommendedFood aliment;
                aliment = new RecommendedFood();
                aliment.ID = rezultat.getString(0);
                aliment.name = rezultat.getString(1);
                aliment.kcal = rezultat.getString(2);
                aliment.protein = rezultat.getString(3);
                aliment.carbs = rezultat.getString(4);
                aliment.fats = rezultat.getString(5);
                aliment.rating = rezultat.getString(6);
                foodListRecommandedList.add(aliment);
            }
        }


        //endregion

        //region ---------SQL ADAPTER

        CustomAdapterRecommendedMeal recommendedfoodListAdapter = new CustomAdapterRecommendedMeal();
        recommended_meal_list.setAdapter(recommendedfoodListAdapter);

        recommended_meal_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();

                Dbnutritie = new DatabaseHelper(getContext());
                Cursor alimentSelectat;

                RecommendedFood auxiliar = foodListRecommandedList.get(position);

                alimentSelectat = Dbnutritie.selectFooditem(Integer.parseInt(auxiliar.ID));


                    while (alimentSelectat.moveToNext()) {
                        RecommendedFood AlimentSelectatAuxiliar;
                        AlimentSelectatAuxiliar = new RecommendedFood();
                        AlimentSelectatAuxiliar.ID = alimentSelectat.getString(0);
                        AlimentSelectatAuxiliar.name = alimentSelectat.getString(1);
                        AlimentSelectatAuxiliar.kcal = alimentSelectat.getString(2);
                        AlimentSelectatAuxiliar.protein = alimentSelectat.getString(3);
                        AlimentSelectatAuxiliar.carbs = alimentSelectat.getString(4);
                        AlimentSelectatAuxiliar.fats = alimentSelectat.getString(5);
                        AlimentSelectatAuxiliar.rating = alimentSelectat.getString(6);

                        if(mealSelected.equals("breakfast")) {
                            Dbnutritie.insertDataInFoodDiary(date, String.valueOf(1), String.valueOf(AlimentSelectatAuxiliar.ID));
                        }
                        if(mealSelected.equals("lunch")){
                            Dbnutritie.insertDataInFoodDiary(date, String.valueOf(2), String.valueOf(AlimentSelectatAuxiliar.ID));
                        }
                        if(mealSelected.equals("dinner")){
                            Dbnutritie.insertDataInFoodDiary(date, String.valueOf(3), String.valueOf(AlimentSelectatAuxiliar.ID));
                        }
                }


            }
        });

        //endregion





        builder.setView(view).setTitle("Add Food").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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


   class CustomAdapterMealList extends BaseAdapter {


        @Override
        public int getCount() {
            return foodListNutritionObject.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView= getLayoutInflater().inflate(R.layout.nutrition_food_list_all , null);

            TextView food_name = convertView.findViewById(R.id.nutrition_foodList_all_name);
            TextView food_kcal = convertView.findViewById(R.id.nutrition_foodList_all_kcal);
            TextView food_protein = convertView.findViewById(R.id.nutrition_foodList_all_protein);
            TextView food_carbs = convertView.findViewById(R.id.nutrition_foodList_all_carbs);
            TextView food_fats = convertView.findViewById(R.id.nutrition_foodList_all_fat);




            food_name.setText((foodListNutritionObject.get(position).name).toString());
            food_kcal.setText((foodListNutritionObject.get(position).kcal).toString()+ " Kcal");
            food_protein.setText((foodListNutritionObject.get(position).protein).toString()+ " P");
            food_carbs.setText((foodListNutritionObject.get(position).carbs).toString()+ " C");
            food_fats.setText((foodListNutritionObject.get(position).fats).toString()+ " F");

            return convertView;
        }
    }

    class CustomAdapterRecommendedMeal extends BaseAdapter {


        @Override
        public int getCount() {
            return foodListRecommandedList.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.nutrition_food_list_all, null);

            TextView food_name = convertView.findViewById(R.id.nutrition_foodList_all_name);
            TextView food_kcal = convertView.findViewById(R.id.nutrition_foodList_all_kcal);
            TextView food_protein = convertView.findViewById(R.id.nutrition_foodList_all_protein);
            TextView food_carbs = convertView.findViewById(R.id.nutrition_foodList_all_carbs);
            TextView food_fats = convertView.findViewById(R.id.nutrition_foodList_all_fat);


            food_name.setText((foodListRecommandedList.get(position).name).toString());
            food_kcal.setText((foodListRecommandedList.get(position).kcal).toString() + " Kcal");
            food_protein.setText((foodListRecommandedList.get(position).protein).toString() + " P");
            food_carbs.setText((foodListRecommandedList.get(position).carbs).toString() + " C");
            food_fats.setText((foodListRecommandedList.get(position).fats).toString() + " F");

            notifyDataSetChanged();

            return convertView;
        }
    }









}
