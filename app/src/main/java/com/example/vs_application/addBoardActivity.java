package com.example.vs_application;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addBoardActivity extends AppCompatActivity {

    private FirebaseFirestore mstore = FirebaseFirestore.getInstance();
    private EditText titleEditText;
    private  EditText contentEditText;
    private Button tagbtn;
    private Button conbtn;

    private String id;

    private Spinner sptspinner;
    private Spinner esptspinner;
    private Button sptselecetedbtn, esptselectedbtn;

    int spbtn , espbtn;
    String spinnertext;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_board);

        spbtn =1;

        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);
        //tagbtn = findViewById(R.id.tagbtn);
        conbtn = findViewById(R.id.cobtn);

        sptspinner  = findViewById(R.id.spt_spinner);
        esptspinner = findViewById(R.id.espt_spinner);
        sptselecetedbtn = findViewById(R.id.spt_selected_btn);
        esptselectedbtn = findViewById(R.id.espt_selected_btn);

        esptselectedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esptselectedbtn.setTextColor(getResources().getColor(R.color.white));
                esptselectedbtn.setBackgroundColor(getResources().getColor(R.color.colormain));


                sptselecetedbtn.setTextColor(getResources().getColor(R.color.white));
                sptselecetedbtn.setBackgroundColor(getResources().getColor(R.color.colornoselecte));
                sptspinner.setVisibility(View.INVISIBLE);
                esptspinner.setVisibility(View.VISIBLE);

                espbtn = 1;
                spbtn = 0;

                esptspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });


        sptselecetedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sptselecetedbtn.setTextColor(getResources().getColor(R.color.white));
                sptselecetedbtn.setBackgroundColor(getResources().getColor(R.color.colormain));


                esptselectedbtn.setTextColor(getResources().getColor(R.color.white));
                esptselectedbtn.setBackgroundColor(getResources().getColor(R.color.colornoselecte));

                esptspinner.setVisibility(View.INVISIBLE);
                sptspinner.setVisibility(View.VISIBLE);

                spbtn = 1;
                espbtn = 0;

                sptspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
        });









        conbtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if (spbtn > espbtn){
                    spinnertext = sptspinner.getSelectedItem().toString();
                } else if (spbtn < espbtn) {
                    spinnertext = esptspinner.getSelectedItem().toString();

                }






                id = mstore .collection(spinnertext).document().getId();
                // sboard 를 board로 바꾸면 val게시판으로 가능하다
                //이걸 스피너를 활용해서 변수로해서 그 id를 받으면 가능할거같다.



                Map<String, String> post = new HashMap<>();
                post.put("id",id);
                post.put("title",titleEditText.getText().toString());
                post.put("content",contentEditText.getText().toString());

                mstore.collection(spinnertext).document(id).set(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(addBoardActivity.this,"업로드 성공",Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addBoardActivity.this,"업로드 실패",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }

}