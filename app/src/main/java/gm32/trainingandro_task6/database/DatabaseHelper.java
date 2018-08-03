package gm32.trainingandro_task6.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Money.db";
    public static final String TABLE_INCOME = "Income";
    public static final String TABLE_EXPENSES = "Expense";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "LABEL";
    public static final String COL_3 = "BECOME";

    public static final String TABLE_CREATE_INCOME = "CREATE TABLE " + TABLE_INCOME +
            " ( " +
            COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_2 + " TEXT, " +
            COL_3 + " DOUBLE );";

    public static final String TABLE_CREATE_EXPENSES = "CREATE TABLE " + TABLE_EXPENSES +
            " ( " +
            COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_2 + " TEXT, " +
            COL_3 + " DOUBLE );";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE_INCOME);
        db.execSQL(TABLE_CREATE_EXPENSES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);
        onCreate(db);
    }

    public boolean add_Income(String label, double become) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content_values = new ContentValues();
        content_values.put(COL_2, label);
        content_values.put(COL_3, become);
        long result = db.insert(TABLE_INCOME, null, content_values);
        return result != -1;
    }

    public boolean add_Expenses(String label, double become) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content_values = new ContentValues();
        content_values.put(COL_2, label);
        content_values.put(COL_3, become);
        long result = db.insert(TABLE_EXPENSES, null, content_values);
        return result != -1;
    }

    public Cursor get_Income() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor income = db.rawQuery("SELECT * FROM " + TABLE_INCOME, null);
        return income;
    }

    public Cursor get_Expenses() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor expense = db.rawQuery("SELECT * FROM " + TABLE_EXPENSES, null);
        return expense;
    }
}