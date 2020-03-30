package com.sadhana.yoga.activity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.sadhana.yoga.R;
import com.sadhana.yoga.fragments.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class SetAlarm extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        mTextView = findViewById(R.id.textSetTheAlarm);

        ImageView imageView = findViewById(R.id.imgTimepicker);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        Button buttonCancelAlarm = findViewById(R.id.button_cancel);
        buttonCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }

            @SuppressLint("SetTextI18n")
            private void cancelAlarm() {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getBaseContext(), AlertReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);
                if (alarmManager != null) {
                    alarmManager.cancel(pendingIntent);
                }
                mTextView.setText("Алармот е деактивиран");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);
    }

    private void startAlarm(Calendar c) {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
    }

    private void updateTimeText(Calendar c) {

        String timeText = "Време за јога: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        mTextView.setText(timeText);
    }
}
