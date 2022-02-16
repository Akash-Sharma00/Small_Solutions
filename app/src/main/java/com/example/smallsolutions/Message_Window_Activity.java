package com.example.smallsolutions;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class Message_Window_Activity extends AppCompatActivity {



    CircleImageView Image;
    RecyclerView recycler;
    EditText message;
    TextView receiverName;
    ImageView send;
    ArrayList<ChatMessageLoader>messageHolder;
    ChatWindowAdapter chatAdapter;
    FirebaseDatabase database;
    DatabaseReference reference, ref;
    FirebaseAuth auth;
    String Sender_id, Receiver_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_window);


        Intent intent = getIntent();
        String NAME = intent.getStringExtra("Name");
        String IMAGE = intent.getStringExtra("ProfilePhoto");
        String ID = intent.getStringExtra("uid");


        auth = FirebaseAuth.getInstance();
        Receiver_id = intent.getStringExtra("Sender_id");
        Sender_id = auth.getUid();


        Image = findViewById(R.id.windowImg);
        message = findViewById(R.id.messageText);
        send = findViewById(R.id.sendButton);
        receiverName = findViewById(R.id.windowName);

        receiverName.setText(NAME);
        Picasso.get().load(IMAGE).into(Image);



        messageHolder = new ArrayList<>();
        recycler = findViewById(R.id.message_window);
        LinearLayoutManager  linearLayoutManager =  new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recycler.setLayoutManager(linearLayoutManager);
        chatAdapter = new ChatWindowAdapter(messageHolder, this);
        recycler.setHasFixedSize(true);


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users/message");
        send.setOnClickListener(view -> {
            String Message = message.getText().toString().trim();
            String time = new SimpleDateFormat("hh:mm  dd-MM-yyyy").format(new Date());

            if (Message.isEmpty()){return;}

            ChatMessageLoader sendMessage = new ChatMessageLoader(Message, time, Sender_id);
            if (!Message.equals("")){
                reference.child(auth.getUid()).child(ID).push().setValue(sendMessage);

                reference.child(ID).child(auth.getUid()).push().setValue(sendMessage);

            reference = database.getReference("users/chats");
            reference.child(auth.getUid()).child(ID).child("lastMessage").setValue(Message);
            reference.child(auth.getUid()).child(ID).child("lastTime").setValue(time);
            reference.child(ID).child(auth.getUid()).child("lastMessage").setValue(Message);
            reference.child(ID).child(auth.getUid()).child("lastTime").setValue(time);

            message.setText("");
        }
        });
        

        ref = database.getReference("users/message/"+auth.getUid());
        ref.child(ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageHolder.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ChatMessageLoader userChat = dataSnapshot.getValue(ChatMessageLoader.class);
                    messageHolder.add(userChat);

                }
                recycler.setAdapter(new ChatWindowAdapter(messageHolder, Message_Window_Activity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Message_Window_Activity.this, ChatActivity.class));
    }
}