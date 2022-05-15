package com.example.instant_messanger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.instant_messanger.message_adapter.MyAdapater;
import com.example.instant_messanger.messages_data.Messages;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;


public class chat extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;
    private ArrayList<Messages> messages;
    MyAdapater myAdapater;
    RecyclerView recyclerView;
    TextView textField;
    Button btnSend;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        messages = new ArrayList<Messages>();
        recyclerView = findViewById(R.id.recyclerView);
        textField = findViewById(R.id.textField);
        btnSend = findViewById(R.id.btnSend);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Messages message = new Messages(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail(), textField.getText().toString());
                firebaseFirestore.collection("messages").add(message);
                textField.setText("");


            }
        });


        Toolbar toolbar = findViewById(R.id.btnLogout);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
            }
        });
        //listening to real time data
        getData();
        myAdapater = new MyAdapater(messages);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messages.clear();
        recyclerView.setAdapter(myAdapater);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getData() {
        firebaseFirestore.collection("messages").addSnapshotListener((snapshots, error) -> {
            if (error != null) {
                System.out.println("Field");
            }
            assert snapshots != null;
            for (DocumentSnapshot doc : snapshots) {
                if (doc.get("email") != null && doc.get("message") != null) {
                    System.out.println(doc.getData());
                    Messages message = new Messages(doc.getString("email"), doc.getString("message"));
                    messages.add(message);
                }
                myAdapater.notifyDataSetChanged();

            }

        });
    }
}