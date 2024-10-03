package com.example.vs_application;

import android.annotation.SuppressLint;
import android.content.Intent;
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
 * Use the {@link boardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class boardFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public boardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment boardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static boardFragment newInstance(String param1, String param2) {
        boardFragment fragment = new boardFragment();
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
        final Button btnboard1,btnboard2,btnreview;
        //FloatingActionButton addbtn;
        // RecyclerView boardrecycler;

        // MainAdpater mAdpater;
        //List<board> mBoardList;



        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_board2, container, false);
        btnboard1 = (Button) view.findViewById(R.id.btnboard1);
        btnboard2 = (Button) view.findViewById(R.id.btnboard2);
        btnreview = (Button) view.findViewById(R.id.btnreview);

        //boardrecycler = (RecyclerView) view.findViewById(R.id.boardRecycler);
        //view.findViewById(R.id.addbtn).setOnClickListener(this);
        allFragment allFragment = new allFragment();


       /* mBoardList = new ArrayList<>();
        mBoardList.add(new board(null,"반갑습니다 여러분",null," android"));
        mBoardList.add(new board(null,"Hello",null," server"));
        mBoardList.add(new board(null,"OK",null," java"));
        mBoardList.add(new board(null,"반갑습니다 여러분",null," php"));
        mBoardList.add(new board(null,"zzzzz",null," python"));

        mAdpater = new MainAdpater(mBoardList);
        boardrecycler.setAdapter(mAdpater);

        replaceFragment(allFragment);*/






       btnboard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                btnboard1.setTextColor(getResources().getColor(R.color.colormain));
                btnboard2.setTextColor(getResources().getColor(R.color.colornoselecte));
                btnreview.setTextColor(getResources().getColor(R.color.colornoselecte));

                replaceFragment(allFragment);



                //btnboard1.setBackgroundColor(getResources().getColor(R.color.colormain));
                //btnboard2.setBackgroundColor(getResources().getColor(R.color.colornoselecte));
                //btnreview.setBackgroundColor(getResources().getColor(R.color.colornoselecte));

              /* FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.board_content, allFragment);
                fragmentTransaction.commit();*/
            }
        });

        btnboard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnboard1.setTextColor(getResources().getColor(R.color.colornoselecte));
                btnboard2.setTextColor(getResources().getColor(R.color.colormain));
                btnreview.setTextColor(getResources().getColor(R.color.colornoselecte));


                replaceFragment(new board_battle_Fragment());




               // btnboard1.setBackgroundColor(getResources().getColor(R.color.colornoselecte));
               // btnboard2.setBackgroundColor(getResources().getColor(R.color.colormain));
               // btnreview.setBackgroundColor(getResources().getColor(R.color.colornoselecte));
               /* FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.board_content,new board_battle_Fragment());
                fragmentTransaction.commit();*/
            }
        });

        btnreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnboard1.setTextColor(getResources().getColor(R.color.colornoselecte));
                btnboard2.setTextColor(getResources().getColor(R.color.colornoselecte));
                btnreview.setTextColor(getResources().getColor(R.color.colormain));


                replaceFragment(new reviewFragment());

               // btnboard1.setBackgroundColor(getResources().getColor(R.color.colornoselecte));
                //btnboard2.setBackgroundColor(getResources().getColor(R.color.colornoselecte));
                //btnreview.setBackgroundColor(getResources().getColor(R.color.colormain));
                /*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.board_content,new reviewFragment());
                fragmentTransaction.commit();*/
            }
        });



        return view;

    }

    private  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.board_content,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) { // 게시팍 작성 부분
        Intent intent = new Intent(getActivity(),addBoardActivity.class); //fragment라서 activity intent와는 다른 방식
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);


    }

//addbtn
   // @Override
    /*public void onClick(View view) {

    }*/
    /*private class MainAdpater extends RecyclerView.Adapter<MainAdpater.MainViewHolder>{


        private final List<board> mBoardList;

        private MainAdpater(List<board> mBoardList) {
            this.mBoardList = mBoardList;
        }


        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
                board data = mBoardList.get(position);
                holder.mTitleTextView.setText(data.getTitle());
                holder.mNameTextView.setText(data.getName());
        }

        @Override
        public int getItemCount() {
            return mBoardList.size();
        }

        class MainViewHolder extends RecyclerView.ViewHolder{
            private TextView mTitleTextView;
            private TextView mNameTextView;

            public MainViewHolder(@NonNull View itemView) {
                super(itemView);

                mTitleTextView = itemView.findViewById(R.id.item_title_text);
                mNameTextView = itemView.findViewById(R.id.item_name_text);
            }
        }
    }*/
}