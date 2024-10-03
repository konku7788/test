package com.example.vs_application;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class chatMsgFragment extends Fragment implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    EditText content_et;
    Button send_bt;

    RecyclerView rv;
    chatAdapter mAdapter;

    String chatroom = "";

    List<chatMessage> msgList = new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    public chatMsgFragment() {
    }


    @SuppressWarnings("unused")
    public static chatMsgFragment newInstance() {
        chatMsgFragment fragment = new chatMsgFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_msg, container, false);

        content_et = view.findViewById(R.id.content_et);
        send_bt = view.findViewById(R.id.send_iv);

        rv = view.findViewById(R.id.rv);
        send_bt.setOnClickListener(this);


        chatroom = getArguments().getString("chatroom");
        mAdapter = new chatAdapter(msgList);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(mAdapter);

        // Firebase Database 초기화
        myRef = database.getReference(chatroom);

        // Firebase Database Listener
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                // Database 의 정보를 ChatMsgVO 객체에 담음
                chatMessage chatMsgVO = dataSnapshot.getValue(chatMessage.class);
                msgList.add(chatMsgVO);

                // 채팅 메시지 배열에 담고 RecyclerView 다시 그리기
                mAdapter = new chatAdapter(msgList);
                rv.setAdapter(mAdapter);
                rv.scrollToPosition(msgList.size()-1);
                Log.d(TAG, msgList.size()+"");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        Log.d(TAG, "chatroom = "+chatroom);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_iv) {
            if (content_et.getText().toString().trim().length() >= 1) {

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // Firebase Authentication에서 현재 사용자 가져오기
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {

                } else {
                    Toast.makeText(getActivity(), "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
                    String userId = "testUser";

                    // Database에 저장할 객체 만들기
                    chatMessage msgVO = new chatMessage(userId, df.format(new Date()), content_et.getText().toString().trim());

                    // 해당 DB 에 값 저장시키기
                    myRef.push().setValue(msgVO);

                    // 입력 필드 초기화
                    content_et.setText("");
                }
            } else {
                Toast.makeText(getActivity(), "메시지를 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


