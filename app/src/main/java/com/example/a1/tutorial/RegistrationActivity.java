package com.example.a1.tutorial;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText editLogin;
    EditText editPass1;
    EditText editPass2;

    Button btnRegistration;

    DbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editLogin = (EditText) findViewById(R.id.editLogin);
        editPass1 = (EditText) findViewById(R.id.editPass1);
        editPass2 = (EditText) findViewById(R.id.editPass2);
        btnRegistration = (Button) findViewById(R.id.btnRegistration);
        setTitle("Регистрация");

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editLogin.getText().toString().equals("") || editPass1.getText().toString().equals("") || editPass2.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Заполните пожалуйста все поля", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (editPass1.getText().toString().equals(editPass2.getText().toString())) {
                        dbHelper = new DbHelper(getApplicationContext());
                        db = dbHelper.getReadableDatabase();
                        String str = editLogin.getText().toString();
                        Cursor c = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_USERS + " WHERE " + DbHelper.KEY_NAME + " = ?", new String[]{str});
                        c.moveToFirst();
                        db.close();
                        c.close();

                        if (c.getCount() != 0) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Пользователь с таким именем уже существует", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            db = dbHelper.getWritableDatabase();
                            String name = editLogin.getText().toString();
                            String pas = editPass1.getText().toString();
                            db.execSQL("INSERT INTO " + DbHelper.TABLE_USERS + " (" + DbHelper.KEY_NAME + "," + DbHelper.KEY_PASS + ") VALUES ('" + name + "','" + pas + "');");
                            db.close();
                            Toast toast = Toast.makeText(getApplicationContext(), "Вы успешно зарегистрированы", Toast.LENGTH_SHORT);
                            toast.show();
                            finish();
                        }
                    } else {
                        Toast toast = Toast.makeText(v.getContext(), "Пароли должны совпдать", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            }
        });



    }
}
