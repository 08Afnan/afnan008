package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.data.ChatRoomViewModel;
import com.example.myapplication.databinding.ActivityChatRoomBinding;
import com.example.myapplication.databinding.ReceiveMessageBinding;
import com.example.myapplication.databinding.SentMessageBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatRoom extends AppCompatActivity {


    ChatRoomViewModel chatModel ;
    ActivityChatRoomBinding binding;
    ArrayList<ChatMessage> messages =null;
    private RecyclerView.Adapter myAdapter;
    boolean position;

    SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
    String currentDateandTime = sdf.format(new Date());




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages;


        binding.sendButton.setOnClickListener(click ->{
            position = true;
            messages.add(new ChatMessage
                    (binding.textInput.getText().toString(),currentDateandTime,true));
            myAdapter.notifyDataSetChanged();


            binding.textInput.setText("");
        });


        binding.receiveButton.setOnClickListener( click ->{
            position = false;
            messages.add(new ChatMessage(binding.textInput.getText().toString(),currentDateandTime,false));
            myAdapter.notifyDataSetChanged();
            // clear the previous text:
            binding.textInput.setText("");
        });















        binding.recycleView.setAdapter(myAdapter= new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override

            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if(viewType == 0) {
                    SentMessageBinding binding1 = SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding1.getRoot());
                }
                ReceiveMessageBinding binding1 = ReceiveMessageBinding.inflate(getLayoutInflater(),parent, false);
                return new MyRowHolder(binding1.getRoot() );

            }




            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                holder.messageText.setText("");
                holder.timeText.setText("");

                ChatMessage obj = messages.get(position);
                holder.messageText.setText(obj.message);
                holder.timeText.setText(currentDateandTime);
            }


            @Override
            public int getItemViewType(int position) {
                if (messages.get(position).isSentButton==true){
                    return 0;
                }
                else{
                    return 1;
                }
            }

            @Override
            public int getItemCount() {
                // Return the total number of items in the RecyclerView
                return messages.size();
            }



        });

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

    }

    private class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            timeText = itemView.findViewById((R.id.timeText));
        }
    }


    //dont touch
}