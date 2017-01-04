package br.com.rockbox.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import org.jetbrains.anko.ToastsKt;

import br.com.rockbox.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class CalendarFragment extends Fragment {


    @BindView(R.id.calendarView)
    CalendarView calendarView;

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, view);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                Toast.makeText(getActivity(), "Day: " + day + "MOnth: " + month, Toast.LENGTH_SHORT).show();
            }
        });

        Log.i("TAG", String.valueOf(calendarView.getDate()));
        return view;
    }





}
