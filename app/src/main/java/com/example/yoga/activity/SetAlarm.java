package com.example.yoga.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.yoga.R;

import java.util.Calendar;

public class SetAlarm extends AppCompatActivity {

    TimePicker timePicker;
    Button startAlarmBtn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        startAlarmBtn = findViewById(R.id.btn_startAlarm);
        timePicker = findViewById(R.id.timePicker);
        textView = findViewById(R.id.txtAlarmSet);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)

    public void startAlarm(View view) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                timePicker.getHour(),
                timePicker.getMinute(),
                0
        );
        setAlarm(calendar.getTimeInMillis());
    }

    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmBroadcastManager.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0, intent,0);
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        Toast.makeText(getApplicationContext(),"Алармот е активиран", Toast.LENGTH_SHORT).show();
    }
}
