package com.example.myapplication.data;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.ChatMessage;

@Database(entities = {ChatMessage.class}, version = 1)
public abstract class MessageDatabase extends RoomDatabase {
    public abstract ChatMessageDAO cmDAO();

}
