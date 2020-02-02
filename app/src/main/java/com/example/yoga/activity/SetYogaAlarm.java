package com.example.yoga.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yoga.R;
import com.example.yoga.common.Constants;
import com.example.yoga.fragments.AlarmDialogFragment;

import java.util.Calendar;

public class SetYogaAlarm extends AppCompatActivity {

    private static int timeHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private static int timeMinute = Calendar.getInstance().get(Calendar.MINUTE);
    TextView textView1;
    private static TextView textView2;
    public static TextView getTextView2() {
        return textView2;
    }
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        textView1 = (TextView)findViewById(R.id.msg1);
        textView1.setText(timeHour + ":" + timeMinute);
        textView2 = (TextView)findViewById(R.id.msg2);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(SetYogaAlarm.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(SetYogaAlarm.this, 0, myIntent, 0);

        View.OnClickListener listener1 = new View.OnClickListener() {
            public void onClick(View view) {
                textView2.setText("");
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.AlarmStrings.HOUR, timeHour);
                bundle.putInt(Constants.AlarmStrings.MINUTE, timeMinute);
                AlarmDialogFragment fragment = new AlarmDialogFragment(new MyHandler());
                fragment.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(fragment, Constants.AlarmStrings.TIME_PICKER);
                transaction.commit();
            }
        };

        Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(listener1);
        View.OnClickListener listener2 = new View.OnClickListener() {
            public void onClick(View view) {
                textView2.setText("");
                cancelAlarm();
            }
        };
        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(listener2);
    }
    class MyHandler extends Handler {
        @Override
        public void handleMessage (Message msg){
            Bundle bundle = msg.getData();
            timeHour = bundle.getInt(Constants.AlarmStrings.HOUR);
            timeMinute = bundle.getInt(Constants.AlarmStrings.MINUTE);
            textView1.setText(timeHour + ":" + timeMinute);
            setAlarm();
        }
    }
    private void setAlarm(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timeHour);
        calendar.set(Calendar.MINUTE, timeMinute);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
    private void cancelAlarm() {
        if (alarmManager!= null) {
            alarmManager.cancel(pendingIntent);
        }
    }
}
