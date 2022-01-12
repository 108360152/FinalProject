package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class check extends AppCompatActivity {
    private Button btn_idd,btn_back;
    private EditText ed_idd;
    private SQLiteDatabase dbrw;
    private TextView tv_ic,tv_name,tv_phone,tv_vac,tv_district,tv_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        btn_idd=findViewById(R.id.button4);
        btn_back=findViewById(R.id.button7);
        ed_idd=findViewById(R.id.editTextTextPersonName5);
        tv_ic=(TextView) findViewById(R.id.textView20);
        tv_name=(TextView)findViewById(R.id.textView22);
        tv_phone=(TextView)findViewById(R.id.textView24);
        tv_vac=(TextView)findViewById(R.id.textView26);
        tv_district=(TextView)findViewById(R.id.textView28);
        tv_time=(TextView)findViewById(R.id.textView30);


        dbrw = new MyDBHelper(this).getWritableDatabase();

        Toast.makeText(check.this, "歡迎使用資格查詢系統"
                , Toast.LENGTH_SHORT).show();

        btn_idd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c;
                if(ed_idd.length()<1)
                    c = dbrw.rawQuery("SELECT * FROM myTable",null);
                else
                    c = dbrw.rawQuery("SELECT * FROM myTable WHERE nhi " +
                            "LIKE '"+ed_idd.getText().toString()+"'",null);
                c.moveToFirst();
                try {
                    if (ed_idd.getText().toString().equals(c.getString(0))&&!c.getString(8).equals("0")) {
                        tv_ic.setText(c.getString(1));
                        tv_name.setText(c.getString(2));
                        tv_phone.setText(c.getString(3));
                        tv_vac.setText(c.getString(8));
                        tv_district.setText(c.getString(9));
                        tv_time.setText(c.getString(10));
                    }
                    else{
                        Toast.makeText(check.this,"請先使用預約接種系統登錄資料"
                                ,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(check.this,MainActivity.class);
                        startActivity(intent);
                    }
                }catch (Exception e){
                    Toast.makeText(check.this,"請先使用意願登記系統登錄資料"
                            ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(check.this,MainActivity.class);
                    startActivity(intent);
                }
                //關閉Cursor
                c.close();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(check.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //關閉資料庫
        dbrw.close();
    }

}