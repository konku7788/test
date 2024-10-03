package com.example.vs_application;

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
 * Use the {@link sptaddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sptaddFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public sptaddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sptaddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static sptaddFragment newInstance(String param1, String param2) {
        sptaddFragment fragment = new sptaddFragment();
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

        FirebaseFirestore mStore = FirebaseFirestore.getInstance();

        RecyclerView recyclerViewsoccer;
        FloatingActionButton addbtnsoccer;
        sptaddFragment.MainAdapter mAdapter;
        List<board> mBoardList;


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_soccer, container, false);
        recyclerViewsoccer = (RecyclerView) view.findViewById(R.id.board_soccer_Recycler);
        addbtnsoccer = (FloatingActionButton) view.findViewById(R.id.addbtnsoccer);
        view.findViewById(R.id.addbtnsoccer).setOnClickListener(this);

        mBoardList = new ArrayList<>();

        mStore.collection("스포츠 기타").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                sptaddFragment.MainAdapter mAdapter;

                for (DocumentChange dc : value.getDocumentChanges()) {
                    String id = (String) dc.getDocument().getData().get("id");
                    String title = (String) dc.getDocument().getData().get("title");
                    String contents = (String) dc.getDocument().getData().get("contents");
                    String name = (String) dc.getDocument().getData().get("name");

                    board data = new board(id, title, contents, name);

                    mBoardList.add(data);
                }
                mAdapter = new sptaddFragment.MainAdapter(mBoardList);
                recyclerViewsoccer.setAdapter(mAdapter);
            }
        });
        return view;
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        RecyclerView recyclerViewsptadd;
//        FloatingActionButton addbtnsptadd;
//        MainAdapter mAdapter;
//        List<board> mBoardList;
//
//        View view = inflater.inflate(R.layout.fragment_sptadd,container,false);
//        recyclerViewsptadd = (RecyclerView) view.findViewById(R.id.board_sptadd_Recycler);
//        view.findViewById(R.id.addbtnsptadd).setOnClickListener(this);
//
//        mBoardList = new ArrayList<>(); // 10번째
//        mBoardList.add(new board(null,"스포츠 기타화면입니다.",null," sptadd"));
//        mBoardList.add(new board(null,"sportsadd",null," board"));
//        mBoardList.add(new board(null,"기타",null,"sptadd"));
//        mBoardList.add(new board(null,"반갑습니다 여러분",null," php"));
//        mBoardList.add(new board(null,"zzzzz",null," python"));
//
//        mAdapter = new MainAdapter(mBoardList); // 11번째
//        recyclerViewsptadd.setAdapter(mAdapter); // 마지막
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