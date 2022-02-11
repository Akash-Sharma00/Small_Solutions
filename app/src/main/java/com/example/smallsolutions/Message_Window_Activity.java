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
import android.widget.Toast;

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

        Toast.makeText(this,ID,Toast.LENGTH_SHORT).show();

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







//        database = FirebaseDatabase.getInstance();
//     // reference = database.getReference(chatPath).child("chats");
//        reference = database.getReference("users").child("chats");
//
//        send.setOnClickListener(view -> {
//            String Message = message.getText().toString().trim();
//            String time = new SimpleDateFormat("hh:mm  dd-MM-yyyy").format(new Date());
//            ChatMessageLoader sendMessage = new ChatMessageLoader(Message, time, Sender_id);
//            reference.child(Sender_id).child("messages").push().setValue(sendMessage);
//            reference.child(Receiver_id).push().setValue(sendMessage);
//            message.setText("");
//        });
//
////        ref = database.getReference(chatPath).child("chats").child(auth.getUid());
//        ref = database.getReference("users/chats/"+Sender_id+"/messages");
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                messageHolder.clear();
//                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    ChatMessageLoader userChat = dataSnapshot.getValue(ChatMessageLoader.class);
//                    messageHolder.add(userChat);
//                }
//                recycler.setAdapter(new ChatWindowAdapter(messageHolder, Message_Window_Activity.this));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }
}