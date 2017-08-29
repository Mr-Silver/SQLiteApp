package made_in_abyss.sqliteApp;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VOID on 8/29/2017.
 * Modified in 8/29/2017.
 *
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "Student.db";
    public static final int Version = 1;

    public static final String Table_NAME  = "Student_Table";   //index #
    public static final String Col_ID      = "ID";              // 0
    public static final String Col_First   = "First";           // 1
    public static final String Col_Last    = "Last";            // 2
    public static final String Col_Marks   = "Marks";           // 3

    public static final String Creat_Table = "CREATE TABLE " + Table_NAME
            + "("
            + Col_ID + " integer primary key autoincrement, "
            + Col_First + " text, "
            + Col_Last + " text, "
            + Col_Marks + " integer"
            + ")";


    public DBHelper (Context context) {
        super(context, DB_NAME, null, Version);
        // SQLiteDatabase db = this.getWritableDatabase(); // just for one time check
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Creat_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_NAME);
        onCreate(db);
    }


    public boolean insertData(String first, String last, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //  values.put(Col_Name, the_value);
        values.put(Col_First, first);
        values.put(Col_Last, last);
        values.put(Col_Marks, marks);

        long result = db.insert(Table_NAME, null, values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_NAME, null);
        return res;
    }


}
