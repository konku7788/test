package com.example.vs_application;

/* 사용자 로그인 되어 있을 때 사용할 코드
public class chatFragment extends Fragment {
    private FirebaseDatabase database;
    private DatabaseReference chatsRef;
    private ListView chatListView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> chatList;

    public chatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        database = FirebaseDatabase.getInstance();
        chatsRef = database.getReference("chats");
        chatListView = view.findViewById(R.id.chatListView);
        chatList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, chatList);
        chatListView.setAdapter(adapter);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            loadChatList();
        } else {
            // 사용자가 로그인되어 있지 않은 경우 처리해야함

        }

        loadChatList();

        chatListView.setOnItemClickListener((parent, v, position, id) -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.main_content, new ChatRoomFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return view;
    }

    private void loadChatList() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            chatsRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String chatId = dataSnapshot.getKey();
                    chatList.add(chatId);
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {}

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

                @Override
                public void onCancelled(@NonNull DatabaseError error) {}

            });
        } else {
            // 사용자가 로그인되어 있지 않은 경우 처리해야함
        }


    }
}
*/

//테스트를 위해서 사용자 로그인 부분 제거함.

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class chatFragment extends Fragment {
    private FirebaseDatabase database;
    private DatabaseReference chatsRef;
    private ListView chatListView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> chatList;
    private TextView gotochat;

    public chatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);


        database = FirebaseDatabase.getInstance();
        chatsRef = database.getReference("chats");
        chatListView = view.findViewById(R.id.chatListView);
        chatList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, chatList);
        chatListView.setAdapter(adapter);

        loadChatList();

        chatListView.setOnItemClickListener((parent, v, position, id) -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.main_content, new chatListFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        gotochat= view.findViewById(R.id.makeChatRoom);
        gotochat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content, new chatListFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    private void loadChatList() {
        chatsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String chatId = dataSnapshot.getKey();
                if (chatId != null) {
                    chatList.add(chatId);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

}
