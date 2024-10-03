package com.example.vs_application;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.vs_application.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragmnet(new homeFragment());

        binding.bottomNavigation.setOnItemSelectedListener(item -> {


            int itemId = item.getItemId();
            if (itemId == R.id.bottom_home) {
                replaceFragmnet(new homeFragment());
            } else if (itemId == R.id.bottom_calender) {
                replaceFragmnet(new calenderFragment());
            } else if (itemId == R.id.bottom_rank) {
                replaceFragmnet(new rankFragment());
            } else if (itemId == R.id.bottom_board) {
                replaceFragmnet(new boardFragment());
            } else if (itemId == R.id.bottom_chat) {
                replaceFragmnet(new chatFragment());
            }
            return true;

        });




    }
    private void replaceFragmnet(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content,fragment);
        fragmentTransaction.commit();
    }




}