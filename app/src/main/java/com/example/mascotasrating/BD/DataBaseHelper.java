package com.example.mascotasrating.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DataBaseHelper extends SQLiteOpenHelper {

    Context context;
   public DataBaseHelper(Context context)
   {
       super(context,BD.DATABASE_NAME,null,BD.DATABASE_VERSION);
       this.context = context;
   }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       try {
           sqLiteDatabase.execSQL(BD.CREATE_MASCOTA);
       }catch (Exception e)
       {
           Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
       }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL(BD.DROP_MASCOTA);
       onCreate(sqLiteDatabase);
    }
}
