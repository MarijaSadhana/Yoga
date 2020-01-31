package com.example.yoga.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import com.example.yoga.common.Constants;

public class AlarmDialogFragment extends DialogFragment {
    private int timeHour;
    private int timeMinute;
    private Handler handler;

    public AlarmDialogFragment(Handler handler){
        this.handler = handler;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        timeHour = bundle.getInt(Constants.AlarmStrings.HOUR);
        timeMinute = bundle.getInt(Constants.AlarmStrings.MINUTE);
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeHour = hourOfDay;
                timeMinute = minute;
                Bundle b = new Bundle();
                b.putInt(Constants.AlarmStrings.HOUR, timeHour);
                b.putInt(Constants.AlarmStrings.MINUTE, timeMinute);
                Message msg = new Message();
                msg.setData(b);
                handler.sendMessage(msg);
            }
        };
        return new TimePickerDialog(getActivity(), listener, timeHour, timeMinute, false);
    }
}
