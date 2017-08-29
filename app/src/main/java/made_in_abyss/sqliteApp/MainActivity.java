package made_in_abyss.sqliteApp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper myDB;

    EditText editFirst, editLast, editMarks;
    Button btnAdd, btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DBHelper(this);

        editFirst   = (EditText) findViewById(R.id.editText_First);
        editLast    = (EditText) findViewById(R.id.editText_Last) ;
        editMarks   = (EditText) findViewById(R.id.editText_Marks);
        btnAdd      = (Button)   findViewById(R.id.button_Add);
        btnViewAll  = (Button)   findViewById(R.id.button_View);

        addData();
        viewAll();
    }

    protected void addData(){

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean check = myDB.insertData(editFirst.getText().toString(),
                                                editLast.getText().toString() ,
                                                editMarks.getText().toString() );
                if(check == true)
                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Data Not Inserted", Toast.LENGTH_LONG).show();
            }
        });

    }

    protected void viewAll(){

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.getAllData();

                if(res.getCount() == 0) {
                    // show error message. thier is no result
                    showMessage("Error", "No data found.");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID : " + res.getString(0)+"\n");
                    buffer.append("First Name : " + res.getString(1)+"\n");
                    buffer.append("Last Name : " + res.getString(2)+"\n");
                    buffer.append("Marks : " + res.getString(3)+"\n\n");
                }

                // show all data
                showMessage("Data", buffer.toString());

            }
        });

    }

    public  void showMessage(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
