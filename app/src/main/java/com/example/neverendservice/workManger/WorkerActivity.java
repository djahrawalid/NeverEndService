package com.example.neverendservice.workManger;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.neverendservice.R;

public class WorkerActivity extends AppCompatActivity {
    public static final String MESSAGE_STATUS = "WorkerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        MyWorker.enqueueSelf();
    }
}