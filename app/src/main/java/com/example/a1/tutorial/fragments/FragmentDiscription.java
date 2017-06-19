package com.example.a1.tutorial.fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.IntegerRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1.tutorial.DbHelper;
import com.example.a1.tutorial.R;


public class FragmentDiscription extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Button btnBack;

    private OnFragmentInteractionListener mListener;

    TextView tv;

    FragmentTeory ftheory;

    ImageView imageView;
    Cursor cursor;
    int num;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public FragmentDiscription() {
    }


    public static FragmentDiscription newInstance(String param1, String param2) {
        FragmentDiscription fragment = new FragmentDiscription();
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
        View v = inflater.inflate(R.layout.fragment_discription, container, false);

        btnBack = (Button) v.findViewById(R.id.backBtn);
        ftheory = new FragmentTeory();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
             num = bundle.getInt("position");
        }

        imageView = (ImageView) v.findViewById(R.id.imageDiscription);

        dbHelper = new DbHelper(v.getContext());
        db = dbHelper.getReadableDatabase();

        String str ="";
        String nums = Integer.toString(num + 1 );
        cursor = db.rawQuery("SELECT * FROM Discriptions WHERE _id = ?", new String[] {nums});
        if (cursor.moveToFirst()) {
            str = cursor.getString(cursor.getColumnIndexOrThrow("Discription"));
        }
        switch (num){
            case 0:
                imageView.setImageResource(R.drawable.ram11);
            break;
            case 1:
                imageView.setImageResource(R.drawable.ram2);
            break;
            case 2:
                imageView.setImageResource(R.drawable.post);
            break;
            case 3:
                imageView.setImageResource(R.drawable.ssd);
            break;
            case 4:
                imageView.setImageResource(R.drawable.cd);
                break;
        }
        tv = (TextView) v.findViewById(R.id.discription);
        tv.setText(str);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                ftrans.replace(R.id.content_main,ftheory);
                ftrans.commit();

            }
        });
        return v;
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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
