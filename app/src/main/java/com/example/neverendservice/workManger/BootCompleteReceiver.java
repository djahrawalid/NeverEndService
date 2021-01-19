package com.example.neverendservice.workManger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompleteReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent ) {
        MyWorker.enqueueSelf();
    }
}
