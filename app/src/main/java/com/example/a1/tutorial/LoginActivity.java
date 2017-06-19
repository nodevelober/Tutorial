package com.example.a1.tutorial;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editUser;
    EditText editPass;
    Button btnLog;
    Button btnReg;

    DbHelper dbHelper;
    SQLiteDatabase db;
    String user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Авторизация");
        editUser = (EditText) findViewById(R.id.editUser);
        editPass = (EditText) findViewById(R.id.editPass);
        btnLog = (Button) findViewById(R.id.btnLog);
        btnReg = (Button) findViewById(R.id.btnReg);
        editUser.setHint("Введите логин");
        editPass.setHint("Введите пароль");
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editUser.getText().toString().equals("") || editPass.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Заполните поля", Toast.LENGTH_SHORT);
                    toast.show();
                }else
                {
                    dbHelper = new DbHelper(getApplicationContext());
                    db = dbHelper.getReadableDatabase();

                    String str = editUser.getText().toString();
                    Cursor c = db.rawQuery("SELECT * FROM "+ DbHelper.TABLE_USERS + " WHERE " + DbHelper.KEY_NAME + " = ?",new String[] {str});
                    if (c.moveToFirst()) {
                    String pas = c.getString(c.getColumnIndexOrThrow(DbHelper.KEY_PASS));
                    int count = c.getCount();
                    if (count == 1 && editPass.getText().toString().equals(pas)) {
                        Intent intent = new Intent();
                        intent.putExtra("login", str);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Неверно введено имя пользователя или пароль", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),"Такого пользователя не существует", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    db.close();
                    c.close();
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                editUser.setText("");
                editPass.setText("");
            }
        });

    }

    @Override
    public void onBackPressed() {
       // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       // if (drawer.isDrawerOpen(GravityCompat.START)) {
       //     drawer.closeDrawer(GravityCompat.START);
       // }
        //else {
        //     super.onBackPressed();
        // }
    }

}
