package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class register extends AppCompatActivity {

    private EditText ed_nhi, ed_ic, ed_name, ed_phone,set_nhi;
    private Button btn_register;
    private CheckBox cb_bnt, cb_mvc, cb_mdn;
    private Spinner  spinner;
    private String place,zero;
    private int v[] = new int[3];

    private SQLiteDatabase dbrw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ed_nhi = findViewById(R.id.ed_nhi);
        ed_ic = findViewById(R.id.ed_ic);
        ed_name = findViewById(R.id.ed_name);
        ed_phone = findViewById(R.id.ed_phone);
        btn_register = findViewById(R.id.btn_register);
        cb_bnt = findViewById(R.id.cb_bnt);
        cb_mvc = findViewById(R.id.cb_mvc);
        cb_mdn = findViewById(R.id.cb_mdn);
        zero="0";

        Toast.makeText(register.this, "歡迎使用意願登記系統"
                , Toast.LENGTH_SHORT).show();

        String[] dataArray = new String[]{"臺北","新北"};

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,dataArray);

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                place = dataArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cb_bnt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    v[0] = 1;
                }
                else{
                    v[0] = 0;
                }
            }
        });

        cb_mdn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    v[1] = 1;
                }
                else{
                    v[1] = 0;
                }
            }
        });

        cb_mvc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    v[2] = 1;
                }
                else{
                    v[2] = 0;
                }
            }
        });

        dbrw = new MyDBHelper(this).getWritableDatabase();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判斷是否沒有填入書名或價格
                if(ed_nhi.length()<1||ed_ic.length()<1||ed_name.length()<1||ed_phone.length()<1)
                    Toast.makeText(register.this,"欄位請勿留空",
                            Toast.LENGTH_SHORT).show();
                else if(ed_nhi.length()!=16){
                    Toast.makeText(register.this,"健保卡卡號錯誤",
                            Toast.LENGTH_SHORT).show();
                }
                else if(ed_ic.length()!=10){
                    Toast.makeText(register.this,"身份證錯誤",
                            Toast.LENGTH_SHORT).show();
                }
                else if(ed_phone.length()!=10){
                    Toast.makeText(register.this,"手機號碼錯誤",
                            Toast.LENGTH_SHORT).show();
                }
                else if(v[0]==0&&v[1]==0&&v[2]==0){
                    Toast.makeText(register.this,"請選擇疫苗",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        dbrw.execSQL("INSERT INTO myTable(nhi,ic,name,phone,place,bnt,mdn,mvc,vac)" +
                                "VALUES(?,?,?,?,?,?,?,?,?)",new Object[]{ed_nhi.getText().toString(),
                                ed_ic.getText().toString(),ed_name.getText().toString(),
                                ed_phone.getText().toString(), place, v[0], v[1], v[2],zero});
                        Toast.makeText(register.this,
                                "登記成功",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(register.this,MainActivity.class);
                        startActivity(intent);
                    }
                    catch (Exception e){
                        Toast.makeText(register.this,"登記失敗:"+
                                e.toString(),Toast.LENGTH_LONG).show();
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