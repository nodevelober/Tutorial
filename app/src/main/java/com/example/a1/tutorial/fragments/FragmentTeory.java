package com.example.a1.tutorial.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a1.tutorial.DbHelper;
import com.example.a1.tutorial.R;


public class FragmentTeory extends android.app.Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ListView listView;

    private DbHelper dbHelper;
    private SQLiteDatabase db;

    FragmentDiscription fdiscription;

    String[] informations = new String[10];

    public FragmentTeory() {
    }


    public static FragmentTeory newInstance(String param1, String param2) {
        FragmentTeory fragment = new FragmentTeory();
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

        View view = inflater.inflate(R.layout.fragment_teory,
                container, false);

        for (int i=0;i<10; i++)
            informations[i] = "";

        dbHelper = new DbHelper(view.getContext());
        db = dbHelper.getReadableDatabase();

        int i = 0;
        Cursor c = db.query(dbHelper.TABLE_THEORY, null, null, null, null, null, null);
        if(c!=null&&c. moveToFirst()){
            do{

                long id = c.getLong(c.getColumnIndexOrThrow (DbHelper.KEY_ID));
                String name  = c.getString(c.getColumnIndexOrThrow (DbHelper.KEY_INFO));
                informations[i] = name;
                i++;
            }while(c.moveToNext());
        }

        listView = (ListView) view.findViewById(R.id.TeoryList);
      //  CustomAdapter adapter = new CustomAdapter(view.getContext(), informations);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, informations);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Bundle bundle = new Bundle();
                 bundle.putInt("position", position);
                 fdiscription = new FragmentDiscription();
                 fdiscription.setArguments(bundle);
                 FragmentTransaction ftrans =  getFragmentManager().beginTransaction();
                 ftrans.replace(R.id.content_main, fdiscription);
                 ftrans.commit();

             }
         });
        c.close();
        db.close();
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
