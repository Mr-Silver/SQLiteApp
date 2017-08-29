package made_in_abyss.sqliteApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper myDB; String first, last, marks;

    EditText editFirst, editLast, editMarks;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DBHelper(this);

        editFirst = (EditText) findViewById(R.id.editText_First);
        editLast  = (EditText) findViewById(R.id.editText_Last) ;
        editMarks = (EditText) findViewById(R.id.editText_Marks);
        btnAdd    = (Button)   findViewById(R.id.button_Add);

        addData();
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
}
