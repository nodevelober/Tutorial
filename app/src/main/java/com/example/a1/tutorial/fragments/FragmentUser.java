package com.example.a1.tutorial.fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1.tutorial.DbHelper;
import com.example.a1.tutorial.R;


public class FragmentUser extends android.app.Fragment  {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Button button;
    TextView textView;
    EditText editText;

    DbHelper dbHelper;
    SQLiteDatabase db;

    private OnFragmentInteractionListener mListener;

    public FragmentUser() {
    }


    public static FragmentUser newInstance(String param1, String param2) {
        FragmentUser fragment = new FragmentUser();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user,
                container, false);
        button = (Button) view.findViewById(R.id.buttonOk);
        editText = (EditText) view.findViewById(R.id.editTextUser);
        textView = (TextView) view.findViewById(R.id.tvPoints);
        Bundle bundle = getArguments();
        final int Points = bundle.getInt("Points");
        String str = "Правильных ответов: " + Integer.toString(Points);
        textView.setText(str);


       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(view.getContext(),
                            "Заполните поля!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    String tmp = editText.getText().toString();

                    dbHelper = new DbHelper(view.getContext());
                    db = dbHelper.getWritableDatabase();
                    db.execSQL("INSERT INTO " + DbHelper.TABLE_USERS +" ( "+ DbHelper.KEY_NAME + " ) VALUES ('"+ tmp + "');");
                   // Cursor num = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_USERS, null);
                   // num.moveToLast();
                   // int UsId = num.getInt(num.getColumnIndexOrThrow(DbHelper.KEY_ID));
                   // db.execSQL("INSERT INTO " + DbHelper.TABLE_STAT + " ( " + DbHelper.KEY_UserID + "," + DbHelper.KEY_POINTS + " ) VALUES (" + UsId + "," + Points + ");");

                   // num.close();
                    db.close();

                    getFragmentManager().popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

   /* @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.buttonOk:
                if (editText.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(v.getContext(),
                            "Заполните поля!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Bundle bundle = getArguments();
                    final int Points = bundle.getInt("Points");
                    String tmp = editText.getText().toString();
                    dbHelper = new DbHelper(v.getContext());
                    db = dbHelper.getWritableDatabase();
                    db.execSQL("INSERT INTO " + DbHelper.TABLE_USERS +" ( "+ DbHelper.KEY_NAME + " ) VALUES ('"+ tmp + "');");
                    Cursor num = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_USERS, null);
                    num.moveToLast();
                    int UsId = num.getInt(num.getColumnIndexOrThrow(DbHelper.KEY_ID));
                    db.execSQL("INSERT INTO " + DbHelper.TABLE_STAT + " ( " + DbHelper.KEY_UserID + "," + DbHelper.KEY_POINTS + " ) VALUES (" + UsId + "," + Points + ");");

                    num.close();
                    db.close();
                }
                break;
        }
    }*/


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
