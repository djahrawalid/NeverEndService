package com.example.neverendservice.jobSchedule;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.neverendservice.R;

import static com.example.neverendservice.jobSchedule.App.CHANNEL_ID;

public class ExampleJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    private boolean jobCancelled = false;
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        doBackgroundWork(params);
        return true;
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                Log.d(TAG, "run: " + i);
                if (jobCancelled) {
                    return;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.d(TAG, "Job finished");
            jobFinished(params, false);
        }).start();
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        showNotification();
        return super.onUnbind(intent);
    }

    private void showNotification() {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        RemoteViews collapsedView = new RemoteViews(getPackageName(),
                R.layout.notification_collapsed);
        Intent clickIntent = new Intent(this, MainActivity.class);
        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(this,
                0, clickIntent, 0);
        collapsedView.setTextViewText(R.id.text_view_collapsed_1, "Hello World!");
        collapsedView.setOnClickPendingIntent(R.id.text_view_collapsed_1,clickPendingIntent);//
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setCustomContentView(collapsedView)
                .build();
        notificationManager.notify(1, notification);
    }

}