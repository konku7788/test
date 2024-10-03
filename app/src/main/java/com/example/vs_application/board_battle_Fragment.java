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
 * Use the {@link board_battle_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class board_battle_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public board_battle_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment board_battle_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static board_battle_Fragment newInstance(String param1, String param2) {
        board_battle_Fragment fragment = new board_battle_Fragment();
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
        final Button btnspt, btnespt;

        View view = inflater.inflate(R.layout.fragment_board_battle_,container,false);
        btnspt = (Button) view.findViewById(R.id.btnspt);
        btnespt = (Button) view.findViewById(R.id.btnespt);

       sportsFragment sportsFragment = new sportsFragment();
        replaceFragment_battle(sportsFragment);


        btnspt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnspt.setTextColor(getResources().getColor(R.color.white));
                btnspt.setBackgroundColor(getResources().getColor(R.color.colormain));
                btnespt.setTextColor(getResources().getColor(R.color.colornoselecte));
                btnespt.setBackgroundColor(getResources().getColor(R.color.white));

                btnspt.setTextSize(15);
                replaceFragment_battle(sportsFragment);

            }
        });

        btnespt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                btnspt.setTextColor(getResources().getColor(R.color.colornoselecte));
                btnspt.setBackgroundColor(getResources().getColor(R.color.white));

                btnespt.setTextColor(getResources().getColor(R.color.white));
                btnespt.setBackgroundColor(getResources().getColor(R.color.colormain));

                btnespt.setTextSize(15);
                replaceFragment_battle(new esportsFragment());

            }
        });


        return view;
    }
private void replaceFragment_battle(Fragment fragment){
    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.board_sptm,fragment);
    fragmentTransaction.commit();
}

}