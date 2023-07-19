package com.example.myapplication.data;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.ChatMessage;

import java.util.ArrayList;

public class ChatRoomViewModel extends ViewModel {

    public ArrayList<ChatMessage> messages = new ArrayList<>();
}
