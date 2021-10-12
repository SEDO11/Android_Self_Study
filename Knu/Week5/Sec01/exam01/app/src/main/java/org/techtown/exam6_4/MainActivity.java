package org.techtown.exam6_4;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    Chronometer chrono;
//    Button btnStart, btnEnd;
    RadioButton rdoCal, rdoTime;
//    CalendarView calView;
    TimePicker tPicker;
    DatePicker dPicker;
    TextView tvYear, tvMonth, tvDay,tvHour, tvMin;

    int selectYear, selectMonth, selectDay, selectHour, selectMin;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chrono = (Chronometer) findViewById(R.id.chronometer);
//        btnEnd = (Button) findViewById(R.id.btnEnd);
//        btnStart = (Button) findViewById(R.id.btnStart);
        rdoCal = (RadioButton) findViewById(R.id.rdoCal);
        rdoTime = (RadioButton) findViewById(R.id.rdoTime);
//        calView = (CalendarView) findViewById(R.id.calendarView1);
        dPicker = (DatePicker) findViewById(R.id.dPicker);
        tPicker = (TimePicker) findViewById(R.id.tPicker);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvMonth = (TextView) findViewById(R.id.tvMonth);
        tvDay = (TextView) findViewById(R.id.tvDay);
        tvHour = (TextView) findViewById(R.id.tvHour);
        tvMin = (TextView) findViewById(R.id.tvMin);

//        calView.setVisibility(calView.INVISIBLE);
        tPicker.setVisibility(tPicker.INVISIBLE);
        dPicker.setVisibility(dPicker.INVISIBLE);
        rdoCal.setVisibility(rdoCal.INVISIBLE);
        rdoTime.setVisibility(rdoTime.INVISIBLE);

        chrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.RED);
                rdoCal.setVisibility(rdoCal.VISIBLE);
                rdoTime.setVisibility(rdoTime.VISIBLE);
            }
        });

        tvYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.stop();
                chrono.setTextColor(Color.BLUE);

                tvYear.setText(Integer.toString(selectYear));
                tvMonth.setText(Integer.toString(selectMonth));
                tvDay.setText(Integer.toString(selectDay));
                tvHour.setText(Integer.toString(selectHour));
                tvMin.setText(Integer.toString(selectMin));

                tPicker.setVisibility(tPicker.INVISIBLE);
                dPicker.setVisibility(dPicker.INVISIBLE);
                rdoCal.setVisibility(rdoCal.INVISIBLE);
                rdoTime.setVisibility(rdoTime.INVISIBLE);
            }
        });

//        btnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                chrono.setBase(SystemClock.elapsedRealtime());
//                chrono.start();
//                chrono.setTextColor(Color.RED);
//            }
//        });

//        btnEnd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                chrono.stop();
//                chrono.setTextColor(Color.BLUE);
//
//                tvYear.setText(Integer.toString(selectYear));
//                tvMonth.setText(Integer.toString(selectMonth));
//                tvDay.setText(Integer.toString(selectDay));
//                tvHour.setText(Integer.toString(selectHour));
//                tvMin.setText(Integer.toString(selectMin));
//            }
//        });

        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                calView.setVisibility(calView.INVISIBLE);
                tPicker.setVisibility(tPicker.INVISIBLE);
                dPicker.setVisibility(dPicker.VISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                calView.setVisibility(calView.INVISIBLE);
                tPicker.setVisibility(tPicker.VISIBLE);
                dPicker.setVisibility(dPicker.INVISIBLE);
            }
        });

//        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
//                selectYear=i;
//                selectMonth=i1;
//                selectDay=i2;
//            }
//        });

        dPicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                selectYear=i;
                selectMonth=i1;
                selectDay=i2;
            }
        });

        tPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                selectHour=i;
                selectMin=i1;
            }
        });
    }
}