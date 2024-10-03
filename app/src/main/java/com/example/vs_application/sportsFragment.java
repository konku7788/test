package com.example.vs_application;

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
 * Use the {@link sportsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sportsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public sportsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sportsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static sportsFragment newInstance(String param1, String param2) {
        sportsFragment fragment = new sportsFragment();
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

        final Button soccer,futsal,basketball,sptadd;
        View view = inflater.inflate(R.layout.fragment_sports,container,false);
        soccer = (Button) view.findViewById(R.id.soccer);
        futsal = (Button) view.findViewById(R.id.futsal);
        basketball = (Button) view.findViewById(R.id.basketball);
        sptadd = (Button) view.findViewById(R.id.spt_add);


        soccerFragment soccerFragment = new soccerFragment();
        replaceFragment_sports(soccerFragment);

        soccer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soccer.setTextColor(getResources().getColor(R.color.white));
                soccer.setBackgroundColor(getResources().getColor(R.color.colormain));
                soccer.setTextSize(15);

                futsal.setTextColor(getResources().getColor(R.color.colornoselecte));
                futsal.setBackgroundColor(getResources().getColor(R.color.white));
                basketball.setTextColor(getResources().getColor(R.color.colornoselecte));
                basketball.setBackgroundColor(getResources().getColor(R.color.white));
                sptadd.setTextColor(getResources().getColor(R.color.colornoselecte));
                sptadd.setBackgroundColor(getResources().getColor(R.color.white));

                replaceFragment_sports(soccerFragment);

            }
        });

        futsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                futsal.setTextColor(getResources().getColor(R.color.white));
                futsal.setBackgroundColor(getResources().getColor(R.color.colormain));
                futsal.setTextSize(15);


                soccer.setTextColor(getResources().getColor(R.color.colornoselecte));
                soccer.setBackgroundColor(getResources().getColor(R.color.white));
                basketball.setTextColor(getResources().getColor(R.color.colornoselecte));
                basketball.setBackgroundColor(getResources().getColor(R.color.white));
                sptadd.setTextColor(getResources().getColor(R.color.colornoselecte));
                sptadd.setBackgroundColor(getResources().getColor(R.color.white));

                replaceFragment_sports(new futsalFragment());
            }
        });

        basketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basketball.setTextColor(getResources().getColor(R.color.white));
                basketball.setBackgroundColor(getResources().getColor(R.color.colormain));
                basketball.setTextSize(15);

                soccer.setTextColor(getResources().getColor(R.color.colornoselecte));
                soccer.setBackgroundColor(getResources().getColor(R.color.white));
                futsal.setTextColor(getResources().getColor(R.color.colornoselecte));
                futsal.setBackgroundColor(getResources().getColor(R.color.white));
                sptadd.setTextColor(getResources().getColor(R.color.colornoselecte));
                sptadd.setBackgroundColor(getResources().getColor(R.color.white));

                replaceFragment_sports(new basketballFragment());

            }
        });

        sptadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sptadd.setTextColor(getResources().getColor(R.color.white));
                sptadd.setBackgroundColor(getResources().getColor(R.color.colormain));
                sptadd.setTextSize(15);

                soccer.setTextColor(getResources().getColor(R.color.colornoselecte));
                soccer.setBackgroundColor(getResources().getColor(R.color.white));
                futsal.setTextColor(getResources().getColor(R.color.colornoselecte));
                futsal.setBackgroundColor(getResources().getColor(R.color.white));
                basketball.setTextColor(getResources().getColor(R.color.colornoselecte));
                basketball.setBackgroundColor(getResources().getColor(R.color.white));

                replaceFragment_sports(new sptaddFragment());
            }
        });


        return view;
    }

    private void replaceFragment_sports(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.spt_content,fragment);
        fragmentTransaction.commit();

    }
}