package com.example.a1.tutorial.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1.tutorial.CustomAdapter;
import com.example.a1.tutorial.DbHelper;
import com.example.a1.tutorial.R;

import org.w3c.dom.Text;


public class FragmentResult extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public static final String TAG = "myLog";
    private OnFragmentInteractionListener mListener;

    TextView listView;

    DbHelper dbHelper;
    SQLiteDatabase db;

    String[] informations = new String[10];

    public FragmentResult() {
    }

    public static FragmentResult newInstance(String param1, String param2) {
        FragmentResult fragment = new FragmentResult();
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
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        listView = (TextView) view.findViewById(R.id.listRes);

        Log.d(TAG, "Список");
        dbHelper = new DbHelper(this.getActivity());
        db = dbHelper.getReadableDatabase();

        int i = 0;
        Cursor c = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_STAT + " , " + DbHelper.TABLE_USERS + "  WHERE " + DbHelper.TABLE_STAT + "." + DbHelper.KEY_UserID + " = "+ DbHelper.TABLE_USERS + "." + DbHelper.KEY_ID, null);
        String info = "";
        if(c!=null&&c. moveToFirst()){
            do{

                int points = c.getInt(c.getColumnIndexOrThrow (DbHelper.KEY_POINTS));
                String name  = c.getString(c.getColumnIndexOrThrow (DbHelper.KEY_NAME)) ;


                info = info + name + " - Правильных ответов: " + Integer.toString(points) +" из 4" + "\n\n";
                informations[i] = info;
                Log.d(TAG, info);
                i++;
            }while(c.moveToNext());
        }
       // CustomAdapter adapter = new CustomAdapter(view.getContext(), informations);
        listView.setText(info);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, informations);
        //    listView.setAdapter(adapter);

        db.close();
        c.close();
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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
