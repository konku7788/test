package com.example.vs_application;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link esportsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class esportsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public esportsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment esportsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static esportsFragment newInstance(String param1, String param2) {
        esportsFragment fragment = new esportsFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Button lol,val,overwatch,esptadd;
        View view = inflater.inflate(R.layout.fragment_esports,container,false);


        lol = (Button) view.findViewById(R.id.lol);
         val = (Button) view.findViewById(R.id.val);
         overwatch = (Button) view.findViewById(R.id.Overwatch);
         esptadd = (Button) view.findViewById(R.id.esptadd);

         lolFragment lolFragment = new lolFragment();
         replaceFragment_esports(lolFragment);

         lol.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 lol.setTextColor(getResources().getColor(R.color.white));
                 lol.setBackgroundColor(getResources().getColor(R.color.colormain));

                 val.setTextColor(getResources().getColor(R.color.colornoselecte));
                 val.setBackgroundColor(getResources().getColor(R.color.white));
                 overwatch.setTextColor(getResources().getColor(R.color.colornoselecte));
                 overwatch.setBackgroundColor(getResources().getColor(R.color.white));
                 esptadd.setTextColor(getResources().getColor(R.color.colornoselecte));
                 esptadd.setBackgroundColor(getResources().getColor(R.color.white));

                 replaceFragment_esports(lolFragment);

             }
         });

         val.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 val.setTextColor(getResources().getColor(R.color.white));
                 val.setBackgroundColor(getResources().getColor(R.color.colormain));

                 lol.setTextColor(getResources().getColor(R.color.colornoselecte));
                 lol.setBackgroundColor(getResources().getColor(R.color.white));
                 overwatch.setTextColor(getResources().getColor(R.color.colornoselecte));
                 overwatch.setBackgroundColor(getResources().getColor(R.color.white));
                 esptadd.setTextColor(getResources().getColor(R.color.colornoselecte));
                 esptadd.setBackgroundColor(getResources().getColor(R.color.white));

                 replaceFragment_esports(new valFragment());
             }
         });

         overwatch.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 overwatch.setTextColor(getResources().getColor(R.color.white));
                 overwatch.setBackgroundColor(getResources().getColor(R.color.colormain));

                 lol.setTextColor(getResources().getColor(R.color.colornoselecte));
                 lol.setBackgroundColor(getResources().getColor(R.color.white));
                 val.setTextColor(getResources().getColor(R.color.colornoselecte));
                 val.setBackgroundColor(getResources().getColor(R.color.white));
                 esptadd.setTextColor(getResources().getColor(R.color.colornoselecte));
                 esptadd.setBackgroundColor(getResources().getColor(R.color.white));

                 replaceFragment_esports(new overwatchFragment());
             }
         });

         esptadd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 esptadd.setTextColor(getResources().getColor(R.color.white));
                 esptadd.setBackgroundColor(getResources().getColor(R.color.colormain));

                 lol.setTextColor(getResources().getColor(R.color.colornoselecte));
                 lol.setBackgroundColor(getResources().getColor(R.color.white));
                 val.setTextColor(getResources().getColor(R.color.colornoselecte));
                 val.setBackgroundColor(getResources().getColor(R.color.white));
                 overwatch.setTextColor(getResources().getColor(R.color.colornoselecte));
                 overwatch.setBackgroundColor(getResources().getColor(R.color.white));

                 replaceFragment_esports(new esptaddFragment());
             }
         });










        return view;
    }

    private void replaceFragment_esports(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.espt_content,fragment);
        fragmentTransaction.commit();
    }
}