package com.example.vs_application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link valFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class valFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public valFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment valFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static valFragment newInstance(String param1, String param2) {
        valFragment fragment = new valFragment();
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
        FirebaseFirestore  mStore = FirebaseFirestore.getInstance();

        RecyclerView recyclerViewval;
        FloatingActionButton addbtnval;
       // FloatingActionButton board_soccer, board_lol, board_basketball, board_val, board_futsal, board_overwatch, board_spt_add, board_espt_add;
       // Animation fabOpen, fabClose, fabRClockwise, fabRAntiClockwise;
        MainAdapter mAdapter;
        List<board> mBoardList;

        //boolean isOpen = false;



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_val,container,false);
      recyclerViewval = (RecyclerView) view.findViewById(R.id.board_val_Recycler);
       addbtnval = (FloatingActionButton) view.findViewById(R.id.addbtnval);
/*
       board_soccer = (FloatingActionButton) view.findViewById(R.id.board_soccer);
       board_lol = (FloatingActionButton) view.findViewById(R.id.board_lol);
       board_basketball = (FloatingActionButton) view.findViewById(R.id.board_basketball);
       board_val  = (FloatingActionButton) view.findViewById(R.id.board_val);
       board_futsal = (FloatingActionButton) view.findViewById(R.id.board_futsal);
       board_overwatch = (FloatingActionButton) view.findViewById(R.id.board_overwatch);
       board_spt_add = (FloatingActionButton) view.findViewById(R.id.board_spt_add);
       board_espt_add = (FloatingActionButton) view.findViewById(R.id.board_espt_add);

       fabOpen = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
       fabClose = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_close);
       fabRClockwise = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_clockwise);
       fabRAntiClockwise = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_anticlockwise);*/ // 8개 카테고리 여는 곳






       view.findViewById(R.id.addbtnval).setOnClickListener(this);

        mBoardList = new ArrayList<>();
        mStore.collection("VAL").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                MainAdapter mAdapter;

                for(DocumentChange dc : value.getDocumentChanges()){
                    String id = (String) dc.getDocument().getData().get("id");
                    String title = (String) dc.getDocument().getData().get("title");
                    String contents  = (String) dc.getDocument().getData().get("contents");
                    String name = (String) dc.getDocument().getData().get("name");


                    board  data = new board(id, title, contents, name);
                    //mBoardList.add(new board(null,"val 화면입니다.",null," val"));
                    mBoardList.add(data);
                }
                mAdapter = new MainAdapter(mBoardList);
                recyclerViewval.setAdapter(mAdapter);
            }
        });
       /* mStore.collection("board").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
           @Override
           public void onComplete(@NonNull Task<QuerySnapshot> task) {

            if(task.isSuccessful())     {
                for(DocumentSnapshot document : task.getResult()){
                    String id = (String) document.getData().get("id");
                    String title = (String) document.getData().get("title");
                    String contents  = (String) document.getData().get("contents");
                    String name = (String) document.getData().get("name");

                  //  board  data = new board(id, title, contents, name);
                    //mBoardList.add(new board(null,"val 화면입니다.",null," val"));
                  // mBoardList.add(data);
                }
              //  mAdapter = new MainAdapter(mBoardList);
               // recyclerViewval.setAdapter(mAdapter);

            }
           }
       });*/




       // mAdapter = new MainAdapter(mBoardList);
        //recyclerViewval.setAdapter(mAdapter);



     /* addbtnval.setOnClickListener(new View.OnClickListener() {
          boolean isOpen = false;

          @Override
          public void onClick(View view) {
              if(isOpen) {

                  board_soccer.startAnimation(fabClose);
                  board_lol.startAnimation(fabClose);
                  board_basketball.startAnimation(fabClose);
                  board_val.startAnimation(fabClose);
                  board_futsal.startAnimation(fabClose);
                  board_overwatch.startAnimation(fabClose);
                  board_spt_add.startAnimation(fabClose);
                  board_espt_add.startAnimation(fabClose);

                  addbtnval.startAnimation(fabRClockwise);

                  board_soccer.setClickable(false);
                  board_lol.setClickable(false);
                  board_basketball.setClickable(false);
                  board_val.setClickable(false);
                  board_futsal.setClickable(false);
                  board_overwatch.setClickable(false);
                  board_spt_add.setClickable(false);
                  board_espt_add.setClickable(false);

                  isOpen = false;
              }
              else{

                  board_soccer.startAnimation(fabOpen);
                  board_lol.startAnimation(fabOpen);
                  board_basketball.startAnimation(fabOpen);
                  board_val.startAnimation(fabOpen);
                  board_futsal.startAnimation(fabOpen);
                  board_overwatch.startAnimation(fabOpen);
                  board_spt_add.startAnimation(fabOpen);
                  board_espt_add.startAnimation(fabOpen);

                  addbtnval.startAnimation(fabRAntiClockwise);

                  board_soccer.setClickable(true);
                  board_lol.setClickable(true);
                  board_basketball.setClickable(true);
                  board_val.setClickable(true);
                  board_futsal.setClickable(true);
                  board_overwatch.setClickable(true);
                  board_spt_add.setClickable(true);
                  board_espt_add.setClickable(true);

                  isOpen = true;

              }
          }
      });*/ // 카테고리 8개

      addbtnval.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(getActivity(),addBoardActivity.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
              startActivity(intent);


             /* board_soccer.startAnimation(fabClose);
              board_lol.startAnimation(fabClose);
              board_basketball.startAnimation(fabClose);
              board_val.startAnimation(fabClose);
              board_futsal.startAnimation(fabClose);
              board_overwatch.startAnimation(fabClose);
              board_spt_add.startAnimation(fabClose);
              board_espt_add.startAnimation(fabClose);

              addbtnval.startAnimation(fabRClockwise);

              board_soccer.setClickable(false);
              board_lol.setClickable(false);
              board_basketball.setClickable(false);
              board_val.setClickable(false);
              board_futsal.setClickable(false);
              board_overwatch.setClickable(false);
              board_spt_add.setClickable(false);
              board_espt_add.setClickable(false);*/





          }
      });
     /* board_lol.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });
      board_basketball.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });
      board_val.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });
      board_futsal.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });
      board_overwatch.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });
      board_spt_add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });
      board_espt_add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });*/ // 카테고리 8개




        return view;
    }



    @Override
    public void onClick(View view) {
        /*Intent intent = new Intent(getActivity(),addBoardActivity.class); //fragment라서 activity intent와는 다른 방식
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);*/


    }
    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
        private List<board> mBordList;

        public MainAdapter(List<board> mBordList){
            this.mBordList = mBordList;
        }
        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            board data = mBordList.get(position);
            holder.mTitleTextView.setText(data.getTitle());
            holder.mNameTextView.setText(data.getName());
        }

        @Override
        public int getItemCount() {
            return mBordList.size();
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
    }
}