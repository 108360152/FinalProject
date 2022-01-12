package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class check extends AppCompatActivity {
    private Button btn_idd;
    private EditText ed_id;
    private SQLiteDatabase dbrw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        btn_idd=findViewById(R.id.button4);
        ed_id=findViewById(R.id.editTextTextPersonName5);

        Toast.makeText(check.this, "歡迎使用疫苗預約系統"
                , Toast.LENGTH_SHORT).show();

        btn_idd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(check.this, "歡迎使用疫苗預約系統"
                        , Toast.LENGTH_SHORT).show();
                Cursor c;
                if(ed_id.length()<1)
                    c = dbrw.rawQuery("SELECT * FROM myTable",null);
                else
                    c = dbrw.rawQuery("SELECT * FROM myTable WHERE nhi " +
                            "LIKE '"+ed_id.getText().toString()+"'",null);
                c.moveToFirst();
                try {
                    if (ed_id.getText().toString().equals(c.getString(0))) {
                        Toast.makeText(check.this, "歡迎使用疫苗預約系統"
                                , Toast.LENGTH_SHORT).show();
                        Toast.makeText(check.this, c.getString(8)
                                , Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(check.this,"請先使用意願登記系統登錄資料"
                            ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(check.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}