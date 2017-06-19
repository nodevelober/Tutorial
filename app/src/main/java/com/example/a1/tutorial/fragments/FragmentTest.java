package com.example.a1.tutorial.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1.tutorial.DbHelper;
import com.example.a1.tutorial.R;


public class FragmentTest extends android.app.Fragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    FragmentResult ftheory;

    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;

    Dialog dialog;
    Button btnOk;
    EditText editText;

    int radioFlag = 0;

    TextView textView;
    Button btnNext;
    int Points = 0;

    String[] answers = new String[10];
    int[] RightAnswer = new int[4];

    FragmentDiscription fdiscription;
    FragmentUser fuser;
    String nameUser;
    DbHelper dbHelper;
    SQLiteDatabase db;
    private int count = 1;
    private OnFragmentInteractionListener mListener;



    public FragmentTest() {
    }


    public static FragmentTest newInstance(String param1, String param2) {
        FragmentTest fragment = new FragmentTest();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_test,
                container, false);
        Points = 0;
        Bundle bundle = getArguments();
        nameUser = bundle.getString("login");

        for (int i=0;i<5; i++)
            answers[i] = "";
        textView = (TextView) view.findViewById(R.id.tvQuestion);
        radioGroup = (RadioGroup) view.findViewById(R.id.RadioGr);
        radioButton1 = (RadioButton) view.findViewById(R.id.RadioBtn1);
        radioButton2 = (RadioButton) view.findViewById(R.id.RadioBtn2);
        radioButton3 = (RadioButton) view.findViewById(R.id.RadioBtn3);
        radioButton4 = (RadioButton) view.findViewById(R.id.RadioBtn4);
        btnNext = (Button) view.findViewById(R.id.btnNext);

        count = 1;
        btnNext.setText("Следующий вопрос");

        dbHelper = new DbHelper(view.getContext());
        db = dbHelper.getReadableDatabase();

        String str  = String.valueOf(count);
        String name = "";
        Cursor c = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_QUESTION + " WHERE " + DbHelper.KEY_ID + " = ?;", new String[] {str});
        if (c.moveToFirst()) {
            name = c.getString(c.getColumnIndexOrThrow(DbHelper.KEY_QUESTION));
        }
        textView.setText(name);

        int i = 0;
        c = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_ANSWERS + " WHERE " + DbHelper.KEY_NUM_QUESTION + " = ?;", new String[] {str});
        if (c.moveToFirst()) {
            do {
                String answer = c.getString(c.getColumnIndexOrThrow(DbHelper.KEY_ANSWER));
                answers[i] = answer;
                i++;

            }while (c.moveToNext());
        }

        i = 0;
        Cursor c1 = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_ANSWERS, null);
        if (c1.moveToFirst()) {
            do {
                int RAnswer = c1.getInt(c.getColumnIndexOrThrow(DbHelper.KEY_FLAG));
                if (RAnswer != 0 ) {
                    RightAnswer[i] = RAnswer;
                    i++;
                }

            }while (c1.moveToNext());
        }

        radioButton1.setText(answers[0]);
        radioButton2.setText(answers[1]);
        radioButton3.setText(answers[2]);
        radioButton4.setText(answers[3]);
        c1.close();
        c.close();
        db.close();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prvcount = count;
                switch (prvcount){
                    case 1:
                        if (radioFlag == RightAnswer[prvcount - 1]) Points++;
                        break;
                    case 2:
                        if (radioFlag == RightAnswer[prvcount - 1]) Points++;
                        break;
                    case 3:
                        if (radioFlag == RightAnswer[prvcount - 1]) Points++;
                        break;
                    case 4:
                        if (radioFlag == RightAnswer[prvcount - 1]) Points++;
                        break;
                }
                if (count >= 3) btnNext.setText("Завершить тестирование");
                count++;
                if ((radioButton1.isChecked()) || (radioButton2.isChecked()) || (radioButton3.isChecked()) || (radioButton4.isChecked())) {
                    if (count < 5) {
                        dbHelper = new DbHelper(v.getContext());
                        db = dbHelper.getReadableDatabase();
                        String str = String.valueOf(count);
                        String name = "";
                        Cursor c = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_QUESTION + " WHERE " + DbHelper.KEY_ID + " = ?;", new String[]{str});
                        if (c.moveToFirst()) {
                            name = c.getString(c.getColumnIndexOrThrow(DbHelper.KEY_QUESTION));
                        }
                        textView.setText(name);

                        int i = 0;
                        c = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_ANSWERS + " WHERE " + DbHelper.KEY_NUM_QUESTION + " = ?;", new String[]{str});
                        if (c.moveToFirst()) {
                            do {
                                String answer = c.getString(c.getColumnIndexOrThrow(DbHelper.KEY_ANSWER));
                                answers[i] = answer;
                                i++;
                            } while (c.moveToNext());
                        }

                        radioButton1.setText(answers[0]);
                        radioButton2.setText(answers[1]);
                        radioButton3.setText(answers[2]);
                        radioButton4.setText(answers[3]);
                        radioGroup.clearCheck();

                        c.close();
                        db.close();
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("Тестирование закончено!")
                                .setMessage("Правильных ответов на вопросы: " + Points + " из 4")
                              //  .setIcon(R.drawable.ic_android_cat)
                                .setCancelable(false)
                                .setNegativeButton("ОК",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                        dbHelper = new DbHelper(view.getContext());
                        db = dbHelper.getWritableDatabase();
                        //db.execSQL("INSERT INTO " + DbHelper.TABLE_USERS +" ( "+ DbHelper.KEY_NAME + " ) VALUES ('"+ nameUser + "');");
                         Cursor num = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_USERS, null);
                         num.moveToLast();
                         int UsId = num.getInt(num.getColumnIndexOrThrow(DbHelper.KEY_ID));
                         db.execSQL("INSERT INTO " + DbHelper.TABLE_STAT + " ( " + DbHelper.KEY_UserID + "," + DbHelper.KEY_POINTS + " ) VALUES (" + UsId + "," + Points + ");");

                         num.close();
                         db.close();

                         ftheory = new FragmentResult();
                         FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                         ftrans.replace(R.id.content_main, ftheory);
                         ftrans.commit();
                        radioGroup.clearCheck();
                       // getFragmentManager().popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);



                        /*fuser = new FragmentUser();
                        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                        ftrans.replace(R.id.content_main, fuser);
                        Bundle bundle = new Bundle();
                        bundle.putInt("Points", Points);
                        fuser.setArguments(bundle);
                        ftrans.addToBackStack("fuser");
                        ftrans.commit();*/
                    }
                } else {
                    Toast toast = Toast.makeText(view.getContext(),
                            "Сначала вы должны ответить на вопрос!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.RadioBtn1:
                        radioFlag = 1;
                        break;
                    case R.id.RadioBtn2:
                        radioFlag = 2;
                        break;
                    case R.id.RadioBtn3:
                        radioFlag = 3;
                        break;
                    case R.id.RadioBtn4:
                        radioFlag = 4;
                        break;
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

    @Override
    public void onClick(View v) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
