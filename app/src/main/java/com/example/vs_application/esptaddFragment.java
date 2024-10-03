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
 * Use the {@link esptaddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class esptaddFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public esptaddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment esptaddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static esptaddFragment newInstance(String param1, String param2) {
        esptaddFragment fragment = new esptaddFragment();
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
        FirebaseFirestore mStore = FirebaseFirestore.getInstance();

        RecyclerView recyclerViewval;
        FloatingActionButton addbtnval;
        // FloatingActionButton board_soccer, board_lol, board_basketball, board_val, board_futsal, board_overwatch, board_spt_add, board_espt_add;
        // Animation fabOpen, fabClose, fabRClockwise, fabRAntiClockwise;
        esptaddFragment.MainAdapter mAdapter;
        List<board> mBoardList;

        //boolean isOpen = false;



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_val,container,false);
        recyclerViewval = (RecyclerView) view.findViewById(R.id.board_val_Recycler);
        addbtnval = (FloatingActionButton) view.findViewById(R.id.addbtnval);


        view.findViewById(R.id.addbtnval).setOnClickListener(this);

        mBoardList = new ArrayList<>();
        mStore.collection("e스포츠 기타").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                esptaddFragment.MainAdapter mAdapter;

                for(DocumentChange dc : value.getDocumentChanges()){
                    String id = (String) dc.getDocument().getData().get("id");
                    String title = (String) dc.getDocument().getData().get("title");
                    String contents  = (String) dc.getDocument().getData().get("contents");
                    String name = (String) dc.getDocument().getData().get("name");


                    board  data = new board(id, title, contents, name);
                    //mBoardList.add(new board(null,"val 화면입니다.",null," val"));
                    mBoardList.add(data);
                }
                mAdapter = new esptaddFragment.MainAdapter(mBoardList);
                recyclerViewval.setAdapter(mAdapter);
            }
        });


        addbtnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),addBoardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });

        return view;
    }


//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        RecyclerView recyclerViewesptadd;
//        FloatingActionButton addbtneptadd;
//        MainAdapter mAdapter;
//        List<board> mBoardList;
//
//        View view = inflater.inflate(R.layout.fragment_esptadd,container,false);
//        recyclerViewesptadd = (RecyclerView) view.findViewById(R.id.board_esptadd_Recycler);
//        view.findViewById(R.id.addbtnesptadd).setOnClickListener(this);
//        mBoardList = new ArrayList<>(); // 10번째
//        mBoardList.add(new board(null,"e스포츠 기타 화면입니다.",null," espt"));
//        mBoardList.add(new board(null,"esports",null," board"));
//        mBoardList.add(new board(null,"기타화면입니다,",null," addespt"));
//        mBoardList.add(new board(null,"반갑습니다 여러분",null," php"));
//        mBoardList.add(new board(null,"zzzzz",null," python"));
//
//        mAdapter = new MainAdapter(mBoardList);
//        recyclerViewesptadd.setAdapter(mAdapter);
//
//        // Inflate the layout for this fragment
//        return view;
//    }

    @Override
    public void onClick(View view) {

    }
    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
        private List<board> mBoardList;

        public MainAdapter(List<board> mBoardList) {
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

        class MainViewHolder  extends RecyclerView.ViewHolder{
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