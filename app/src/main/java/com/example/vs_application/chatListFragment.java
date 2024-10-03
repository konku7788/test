package com.example.vs_application;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link chatListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class chatListFragment extends Fragment implements View.OnClickListener {
    public chatListFragment() {
    }

    private static final String TAG = "chatListFragment";
    EditText chatroom_et;
    Button enter_btn;

    public static chatListFragment newInstance(String param1, String param2) {
        chatListFragment fragment = new chatListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_room, container, false);
        chatroom_et = view.findViewById(R.id.chatroom_et);
        enter_btn = view.findViewById(R.id.enter_btn);
        enter_btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.enter_btn) {
            if(chatroom_et.getText().toString().trim().length() > 0){
                Bundle argu = new Bundle();
                argu.putString("chatroom", chatroom_et.getText().toString());

                chatMsgFragment chatMsgFragment = new chatMsgFragment();
                chatMsgFragment.setArguments(argu);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content, chatMsgFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            } else {
                Toast.makeText(getActivity(), "채팅방 이름을 입력하세요", Toast.LENGTH_SHORT).show();
            }
        }
    }
}