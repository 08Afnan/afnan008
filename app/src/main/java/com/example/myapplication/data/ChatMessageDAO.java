package com.example.myapplication.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.ChatMessage;

import java.util.List;

@Dao
public interface ChatMessageDAO {

    @Insert
    public long insertMessage(ChatMessage m);

    @Query("Select * from ChatMessage")// SQL Statement
    public List<ChatMessage> getAllMessages();

    @Delete
    void deleteMessage(ChatMessage m);

}
