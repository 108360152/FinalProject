package com.example.finalproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.Toast;


public class book extends AppCompatActivity {
    private EditText ed_id;
    private Button btn_id,btn_book;
    private Spinner spin_vac,spin_place,spin_time;
    private SQLiteDatabase dbrw;
    private String time,vac,district;
    private Integer vac_int=0,place_int=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        ed_id=findViewById(R.id.editTextPhone);
        btn_id=findViewById(R.id.button5);
        btn_book=findViewById(R.id.button6);
        spin_place=findViewById(R.id.spinner3);
        spin_time=findViewById(R.id.spinner4);
        spin_vac = findViewById(R.id.spinner2);

        dbrw = new MyDBHelper(this).getWritableDatabase();

        String zero=Integer.toString(0);
        String one=Integer.toString(1);


        String[] vac0 = new String[]{""};
        ArrayAdapter<String> vacAdapter0 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, vac0);


        String[] vac1 = new String[]{"高端"};
        ArrayAdapter<String> vacAdapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, vac1);

        String[] vac2 = new String[]{"莫德納"};
        ArrayAdapter<String> vacAdapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, vac2);

        String[] vac3 = new String[]{"莫德納","高端"};
        ArrayAdapter<String> vacAdapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, vac3);

        String[] vac4 = new String[]{"BNT"};
        ArrayAdapter<String> vacAdapter4 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, vac4);

        String[] vac5 = new String[]{"BNT","高端"};
        ArrayAdapter<String> vacAdapter5 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, vac5);

        String[] vac6 = new String[]{"BNT","莫德納"};
        ArrayAdapter<String> vacAdapter6 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, vac6);

        String[] vac7 = new String[]{"BNT","莫德納","高端"};
        ArrayAdapter<String> vacAdapter7= new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, vac7);

        spin_vac.setAdapter(vacAdapter0);

        spin_vac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (vac_int==0){
                        vac=vac0[i];
                    }
                    else if (vac_int==1){
                        vac=vac1[i];
                    }
                    else if (vac_int==2){
                        vac=vac2[i];
                    }
                    else if (vac_int==3){
                        vac=vac3[i];
                    }
                    else if (vac_int==4){
                        vac=vac4[i];
                    }
                    else if (vac_int==5){
                        vac=vac5[i];
                    }
                    else if (vac_int==6){
                        vac=vac6[i];
                    }
                    else if (vac_int==7){
                        vac=vac7[i];
                    }
                }catch (Exception e){
                    Toast.makeText(book.this, "error"
                            , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        String[] place0= new String[]{""};
        ArrayAdapter<String> placeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,place0);

        String[] place1= new String[]{"大安區","中正區"};
        ArrayAdapter<String> placeAdapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,place1);

        String[] place2= new String[]{"板橋區","永和區"};
        ArrayAdapter<String> placeAdapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,place2);

        spin_place.setAdapter(placeAdapter);
        spin_place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (place_int == 0)
                        district = place0[i];
                    else if (place_int == 1)
                        district = place1[i];
                    else if (place_int == 2)
                        district = place2[i];
                }catch (Exception e){
                    Toast.makeText(book.this, "error"
                            , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] time0= new String[]{""};

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,time0);

        String[] time1 = new String[]{"09:00~10:00","10:00~11:00","11:00~12:00","14:00~15:00",
                "15:00~16:00","16:00~17:00"};
        ArrayAdapter<String> timeAdapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, time1);

        spin_time=findViewById(R.id.spinner4);
        spin_time.setAdapter(timeAdapter);
        spin_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                time=time1[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dbrw = new MyDBHelper(this).getWritableDatabase();


        btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c;
                if(ed_id.length()<1)
                    c = dbrw.rawQuery("SELECT * FROM myTable",null);
                else
                    c = dbrw.rawQuery("SELECT * FROM myTable WHERE nhi " +
                            "LIKE '"+ed_id.getText().toString()+"'",null);
                c.moveToFirst();
                try {
                    if (ed_id.getText().toString().equals(c.getString(0))) {
                        Toast.makeText(book.this, "歡迎使用疫苗預約系統"
                                , Toast.LENGTH_SHORT).show();
                        /*Toast.makeText(book.this, c.getString(8)
                                , Toast.LENGTH_SHORT).show();*/
                        spin_time.setAdapter(timeAdapter1);
                        if(c.getString(5).equals(zero)&&c.getString(6).equals(zero)&&c.getString(7).equals(one))
                        {
                            spin_vac.setAdapter(vacAdapter1);
                            vac_int=1;
                        }
                        else if(c.getString(5).equals(zero)&&c.getString(6).equals(one)&&c.getString(7).equals(zero))
                        {
                            spin_vac.setAdapter(vacAdapter2);
                            vac_int=2;
                        }
                        else if(c.getString(5).equals(zero)&&c.getString(6).equals(one)&&c.getString(7).equals(one))
                        {
                            spin_vac.setAdapter(vacAdapter3);
                            vac_int=3;
                        }
                        else if(c.getString(5).equals(one)&&c.getString(6).equals(zero)&&c.getString(7).equals(zero))
                        {
                            spin_vac.setAdapter(vacAdapter4);
                            vac_int=4;
                        }
                        else if(c.getString(5).equals(one)&&c.getString(6).equals(zero)&&c.getString(7).equals(one))
                        {
                            spin_vac.setAdapter(vacAdapter5);
                            vac_int=5;
                        }
                        else if(c.getString(5).equals(one)&&c.getString(6).equals(one)&&c.getString(7).equals(zero))
                        {
                            spin_vac.setAdapter(vacAdapter6);
                            vac_int=6;
                        }
                        else if(c.getString(5).equals(one)&&c.getString(6).equals(one)&&c.getString(7).equals(one))
                        {
                            spin_vac.setAdapter(vacAdapter7);
                            vac_int=7;
                        }

                        if(c.getString(4).equals("臺北"))
                        {
                            spin_place.setAdapter(placeAdapter1);
                            place_int=1;
                        }
                        else if(c.getString(4).equals("新北"))
                        {
                            spin_place.setAdapter(placeAdapter2);
                            place_int=2;
                        }


                    }
                }catch (Exception e){
                    Toast.makeText(book.this,"請先使用意願登記系統登錄資料"
                            ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(book.this,MainActivity.class);
                    startActivity(intent);
                }
                //關閉Cursor
                c.close();
            }
        });

        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_id.length() < 1)
                    Toast.makeText(book.this, "欄位請勿留空",
                            Toast.LENGTH_SHORT).show();
                else {
                    try {
                        /*dbrw.execSQL("UPDATE myTable SET vac="+
                                        vac+" WHERE nhi LIKE '"+ ed_id.getText().toString()+"'");*/
                        /*dbrw.execSQL("UPDATE myTable SET district="+
                                district+" WHERE nhi LIKE '"+ ed_id.getText().toString()+"'");
                        dbrw.execSQL("UPDATE myTable SET time="+
                                time+" WHERE nhi LIKE '"+ ed_id.getText().toString()+"'");*/
                        Toast.makeText(book.this, "登記成功\n"+"您預約的疫苗為： "+vac + "\n接種地點為： "+district+
                                "\n接種時間為："+time,
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(book.this,MainActivity.class);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(book.this, "登記失敗:" +
                                e.toString(), Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        dbrw.close();
    }
    
}

