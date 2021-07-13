package com.example.licenta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Licenta.db";

    public static final String TABLE_NAME_MEALS = "meal_table";
    public static final String mealtype_COL_1 = "ID_meal_table";
    public static final String mealtype_COL_2 = "MEALTYPE";


    public static final String TABLE_NAME_ALIMENT = "fooditem_table";
    public static final String foodItem_COL_1 = "ID_fooditem_table";
    public static final String foodItem_COL_2 = "NAME";
    public static final String foodItem_COL_3 = "KCAL";
    public static final String foodItem_COL_4 = "PROTEIN";
    public static final String foodItem_COL_5 = "CARBS";
    public static final String foodItem_COL_6 = "FATS";
    public static final String foodItem_COL_7 = "RATING";

    public static final String TABLE_NAME_DAILYDIARY =  "diaryfood_table";
    public static final String DAILYFOOD_COL_1 =  "ID_diaryfood_table";
    public static final String DAILYFOOD_COL_2 =  "DATE";
    public static final String DAILYFOOD_COL_3 =  "ID_MEALTYPE";
    public static final String DAILYFOOD_COL_4 =  "ID_ALIMENT";

    public static final String TABLE_NAME_USER_PROGRESS=  "userProgres_table";
    public static final String USERPROGRESS_COL_1 =  "ID_user_progres";
    public static final String USERPROGRESS_COL_2 =  "DATE";
    public static final String USERPROGRESS_COL_3 =  "KG";

    public static final String TABLE_NAME_MYWORKOUT=  "my_workout_table";
    public static final String MYWORKOUT_COL_1 =  "ID_my_workout_table";
    public static final String MYWORKOUT_COL_2 =  "NAME";
    public static final String MYWORKOUT_COL_3 =  "EX1";
    public static final String MYWORKOUT_COL_4 =  "EX2";
    public static final String MYWORKOUT_COL_5 =  "EX3";
    public static final String MYWORKOUT_COL_6 =  "EX4";
    public static final String MYWORKOUT_COL_7 =  "EX5";
    public static final String MYWORKOUT_COL_8 =  "EX6";
    public static final String MYWORKOUT_COL_9 =  "EX7";
    public static final String MYWORKOUT_COL_10 = "EX8";
    public static final String MYWORKOUT_COL_11=  "EX9";
    public static final String MYWORKOUT_COL_12=  "EX10";
    public static final String MYWORKOUT_COL_13 = "EX11";
    public static final String MYWORKOUT_COL_14 = "EX12";
    public static final String MYWORKOUT_COL_15 =  "EX13";
    public static final String MYWORKOUT_COL_16 =  "EX14";
    public static final String MYWORKOUT_COL_17 =  "EX15";
    public static final String MYWORKOUT_COL_18 =  "EX16";
    public static final String MYWORKOUT_COL_19 =  "EX17";
    public static final String MYWORKOUT_COL_20 =  "EX18";
    public static final String MYWORKOUT_COL_21 =  "EX19";
    public static final String MYWORKOUT_COL_22 =  "EX20";

    public static final String TABLE_NAME_EXERCICES=  "exercices_table";
    public static final String EXERCICES_COL_1 =  "ID_exercices_table";
    public static final String EXERCICES_COL_2 =  "EXERCICE";










    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
       final SQLiteDatabase db= this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME_MEALS +" (ID_meal_table INTEGER PRIMARY KEY AUTOINCREMENT, MEALTYPE) " );
        db.execSQL("create table " + TABLE_NAME_ALIMENT +" (ID_fooditem_table INTEGER PRIMARY KEY AUTOINCREMENT, NAME , KCAL, PROTEIN , CARBS , FATS, RATING) " );
        db.execSQL("create table " + TABLE_NAME_DAILYDIARY +" (ID_diaryfood_table INTEGER PRIMARY KEY AUTOINCREMENT, DATE , ID_MEALTYPE, ID_ALIMENT) " );
        db.execSQL("create table " + TABLE_NAME_USER_PROGRESS +" (ID_user_progres INTEGER PRIMARY KEY AUTOINCREMENT, DATE , KG) " );
        db.execSQL("create table " + TABLE_NAME_MYWORKOUT +" (ID_my_workout_table INTEGER PRIMARY KEY AUTOINCREMENT, NAME , EX1,EX2,EX3,EX4,EX5,EX6,EX7,EX8,EX9,EX10,EX11,EX12,EX13,EX14,EX15,EX16,EX17,EX18,EX19,EX20) " );
        db.execSQL("create table " + TABLE_NAME_EXERCICES +" (ID_exercices_table INTEGER PRIMARY KEY AUTOINCREMENT, EXERCICE) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_MEALS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_ALIMENT);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_DAILYDIARY);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_USER_PROGRESS);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_MYWORKOUT);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_EXERCICES);

        onCreate(db);

    }


    public boolean createMealtypeTable(String mealtype){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(mealtype_COL_2 , mealtype);

        long result  = db.insert(TABLE_NAME_MEALS , null , contentValues);
        if(result == -1 )
            return false;
            else
                return true;
    }



    public boolean insertDataInFoodItem (String name, String kcal , String protein , String carbs, String fats, String rating){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(foodItem_COL_2 , name);
        contentValues.put(foodItem_COL_3 , kcal);
        contentValues.put(foodItem_COL_4 , protein);
        contentValues.put(foodItem_COL_5 , carbs);
        contentValues.put(foodItem_COL_6 , fats);
        contentValues.put(foodItem_COL_7 , rating);

        long result = db.insert(TABLE_NAME_ALIMENT , null , contentValues);


        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor selectFooditem(int IDfoodItem){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_ALIMENT + " where ID_fooditem_table = " + IDfoodItem;
        Cursor res = db.rawQuery(query , null);

        return res;
    }


    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_ALIMENT + " order by RATING desc";
        Cursor res = db.rawQuery(query, null);

        return res;
    }

    public  Boolean verifyMealTable(){

        boolean isempty = false;

        SQLiteDatabase db = this.getWritableDatabase();
        String count = "Select count(*) FROM " + TABLE_NAME_MEALS ;
        Cursor cursor = db.rawQuery(count, null);
        cursor.moveToFirst();
        int contor = cursor.getInt(0);

        if(contor>0)
            isempty = false;
        else
            isempty = true;

        return  isempty;
    }


    public boolean insertDataInFoodDiary (String date, String mealtypeID , String foodItemID){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DAILYFOOD_COL_2 , date);
        contentValues.put(DAILYFOOD_COL_3 , mealtypeID);
        contentValues.put(DAILYFOOD_COL_4 , foodItemID);


        long result = db.insert(TABLE_NAME_DAILYDIARY, null , contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor retriveDataFromFoodDiary( String mealtype)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_DAILYDIARY +
                " JOIN meal_table ON id_mealtype = id_meal_table JOIN fooditem_table ON id_aliment = id_fooditem_table WHERE id_mealtype = '"
                + mealtype + "'" ;
        Cursor res = db.rawQuery(query, null);

        return res;
    }

    public void deleteData(int elementSters)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME_DAILYDIARY + " WHERE ID_diaryfood_table = '"+ elementSters + "'");
        db.close();

    }

    public Cursor getLastRow()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_ALIMENT + " ORDER BY ID_fooditem_table DESC LIMIT 1";
        Cursor res = db.rawQuery(query , null);

        return res;
    }

    public boolean insertInExercices(String exercise){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXERCICES_COL_2 , exercise);

        long result = db.insert(TABLE_NAME_EXERCICES , null , contentValues);

        if(result == -1)
            return false;
        else
            return true;


    }

    public  Boolean verifyExercicesTabel(){

        boolean isempty = false;

        SQLiteDatabase db = this.getWritableDatabase();
        String count = "Select count(*) FROM " + TABLE_NAME_EXERCICES ;
        Cursor cursor = db.rawQuery(count, null);
        cursor.moveToFirst();
        int contor = cursor.getInt(0);

        if(contor>0)
            isempty = false;
        else
            isempty = true;

        return  isempty;
    }

    public Cursor getAllExercises(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_EXERCICES + " order by ID_exercices_table asc";
        Cursor res = db.rawQuery(query, null);

        return res;
    }

    public boolean insertIntoMyWorkout(String nume, String ex1, String ex2, String ex3, String ex4, String ex5, String ex6, String ex7, String ex8, String ex9, String ex10, String ex11, String ex12, String ex13, String ex14, String ex15, String ex16, String ex17, String ex18, String ex19, String ex20){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MYWORKOUT_COL_2 ,  nume);
        contentValues.put(MYWORKOUT_COL_3  , ex1);
        contentValues.put(MYWORKOUT_COL_4 , ex2);
        contentValues.put(MYWORKOUT_COL_5  , ex3);
        contentValues.put(MYWORKOUT_COL_6  , ex4);
        contentValues.put(MYWORKOUT_COL_7  , ex5);
        contentValues.put(MYWORKOUT_COL_8  , ex6);
        contentValues.put(MYWORKOUT_COL_9  , ex7);
        contentValues.put(MYWORKOUT_COL_10  , ex8);
        contentValues.put(MYWORKOUT_COL_11  , ex9);
        contentValues.put(MYWORKOUT_COL_12  , ex10);
        contentValues.put(MYWORKOUT_COL_13  , ex11);
        contentValues.put(MYWORKOUT_COL_14  , ex12);
        contentValues.put(MYWORKOUT_COL_15  , ex13);
        contentValues.put(MYWORKOUT_COL_16  , ex14);
        contentValues.put(MYWORKOUT_COL_17  , ex15);
        contentValues.put(MYWORKOUT_COL_18  , ex16);
        contentValues.put(MYWORKOUT_COL_19  , ex17);
        contentValues.put(MYWORKOUT_COL_20  , ex18);
        contentValues.put(MYWORKOUT_COL_21  , ex19);
        contentValues.put(MYWORKOUT_COL_22  , ex20);


        long result = db.insert(TABLE_NAME_MYWORKOUT , null , contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }

    public void deleteDataInMyWorkouts(int elementSters)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME_MYWORKOUT + " WHERE ID_my_workout_table = '"+ elementSters + "'");
        db.close();

    }

    public Cursor getMyWorkoutsIDandName()
        {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_MYWORKOUT + " order by ID_my_workout_table asc";
        Cursor res = db.rawQuery(query, null);

        return res;

    }

    public Cursor getMySpeicificWorkoutAfterID(String idWorkout)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_MYWORKOUT + " WHERE ID_my_workout_table = '" + idWorkout+"'";
        Cursor res = db.rawQuery(query, null);

        return res;

    }

    public Cursor getExerciseByID(String exerciseID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_EXERCICES + " WHERE ID_exercices_table = '" + exerciseID+"'";
        Cursor res = db.rawQuery(query, null);

        return res;

    }

    public boolean insertIntoUserProgress(String date, String kg)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERPROGRESS_COL_2 , date);
        contentValues.put(USERPROGRESS_COL_3 , kg);

        long result = db.insert(TABLE_NAME_USER_PROGRESS , null , contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getUserProgressData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_USER_PROGRESS + " order by ID_user_progres asc";
        Cursor res = db.rawQuery(query, null);

        return res;

    }

    public void deleteInPersonalProgress(int ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME_USER_PROGRESS + " WHERE ID_user_progres = '"+ ID + "'");
        db.close();
    }

    public void deleteAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME_DAILYDIARY);
        db.close();
    }
}
