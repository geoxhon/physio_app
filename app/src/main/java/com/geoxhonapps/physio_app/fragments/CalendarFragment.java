package com.geoxhonapps.physio_app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.geoxhonapps.physio_app.ContextFlowUtilities;
import com.geoxhonapps.physio_app.R;
import com.geoxhonapps.physio_app.RestUtilities.AAppointment;
import com.geoxhonapps.physio_app.RestUtilities.ADoctorUser;
import com.geoxhonapps.physio_app.StaticFunctionUtilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class ListOfDates extends ArrayAdapter<AAppointment>{

    public ListOfDates(Context context, ArrayList<AAppointment> items){
        super(context,0,items);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.r6_list,parent,false);
        }
        AAppointment appoint = getItem(position);
        ((TextView)convertView.findViewById(R.id.appointmentIdText)).setText("#"+appoint.getAppointmentId());
        ((TextView)convertView.findViewById(R.id.patientName)).setText(appoint.getAssociatedUser().getDisplayName());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        ((TextView)convertView.findViewById(R.id.appointmentTime)).setText(timeFormat.format(appoint.getAppointmentDate()));
        return convertView;
    }
}

class DateButtonAdapter extends ArrayAdapter<Calendar>{
    ArrayList<AAppointment> AppointmentsList = new ArrayList<AAppointment>();

    public DateButtonAdapter(@NonNull Context context, @NonNull List<Calendar> objects) {
        super(context, 0, objects);
    }

   public View getView(int position, View convertView, ViewGroup parent, View parentView){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.r6_date, parent, false);
        }
        Calendar currentDate = getItem(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        Button dateButton = convertView.findViewById(R.id.ButtonDate);
        dateButton.setText(dateFormat.format(currentDate.getTime()) +" "+ dayFormat.format(currentDate.getTime()));
        AppointmentsList =((ADoctorUser) StaticFunctionUtilities.getUser()).getAppointments(false);
        ArrayList<AAppointment> SpecificDayApp = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        for(int i=0; i<AppointmentsList.size();i++){
            if((format.format(AppointmentsList.get(i).getAppointmentDate().getTime())).equals(format.format(currentDate.getTime()))){
                SpecificDayApp.add(AppointmentsList.get(i));
            }
        }
        for(int i = 0; i<SpecificDayApp.size(); i++){
            for(int j = SpecificDayApp.size()-1; j>i; j--){
                if(SpecificDayApp.get(j).getAppointmentDate().getTime() < SpecificDayApp.get(j-1).getAppointmentDate().getTime()) {
                    AAppointment temp = SpecificDayApp.get(j);
                    SpecificDayApp.set(j, SpecificDayApp.get(j-1));
                    SpecificDayApp.set(j - 1, temp);
                }
            }
        }
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListOfDates adapter = new ListOfDates(ContextFlowUtilities.getCurrentView(),SpecificDayApp);
                LinearLayout dateLayout = parentView.findViewById(R.id.dateListLayout);
                dateLayout.removeAllViews();
                for(int i = 0; i< adapter.getCount();i++){
                    View newview = adapter.getView(i, null, dateLayout);
                    dateLayout.addView(newview);
                }
            }
        });
        return convertView;
    }
}


public class CalendarFragment extends Fragment {
    private Calendar calendar;
    private TextView titleLabel;
    private int year = 2023;
    private int month = 5;
    private int date = 6;


    private View rootView;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.r6, container, false);
        // Initialize the calendar
        calendar = Calendar.getInstance();

        // Set the first day of the week as Monday
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        ArrayList<Calendar> dates = new ArrayList<Calendar>();
        for (int i = 0; i < 14; i++) {
            Calendar newCalendar = Calendar.getInstance();
            newCalendar.setTimeInMillis(calendar.getTimeInMillis() + 60 * 60 * 24 * i * 1000);
            dates.add(newCalendar);
        }


        DateButtonAdapter adapter = new DateButtonAdapter(ContextFlowUtilities.getCurrentView(), dates);
        LinearLayout dateLayout = rootView.findViewById(R.id.dateButtonsLayout);
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, null, dateLayout, rootView);
            dateLayout.addView(view);
        }
        // Get references to UI components
        titleLabel = rootView.findViewById(R.id.MonthText);
        Button previousButton = rootView.findViewById(R.id.PreviousDate);
        Button nextButton = rootView.findViewById(R.id.NextDate);
        // Update the title
        updateTitle();
        // Set click listeners for buttons
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = date - 7;
                calendar.set(year, month, date);
                updateTitle();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = date + 7;
                calendar.set(year, month, date);
                updateTitle();
            }
        });
        return rootView;
    }

    private void updateTitle() {
    /*
        for(int i=0;i<7; i++){
            calendar.set(year,month,date);
            Button dateButton;
            int dayofweek = calendar.get(Calendar.DAY_OF_WEEK);


            switch (dayofweek) {
                case Calendar.SUNDAY:


                    dateButton.setText(""+date);

                    break;
                case Calendar.MONDAY:
                    dateButton= rootView.findViewById(R.id.Monday);
                    dateButton.setText(""+date);
                    break;
                case Calendar.TUESDAY:
                    dateButton= rootView.findViewById(R.id.Tuesday);
                    dateButton.setText(""+date);

                    break;
                case Calendar.WEDNESDAY:
                    dateButton= rootView.findViewById(R.id.Wednesday);
                    dateButton.setText(""+date+"Wednesday");

                    break;
                case Calendar.THURSDAY:
                    dateButton= rootView.findViewById(R.id.Thursday);
                    dateButton.setText(""+date);

                    break;
                case Calendar.FRIDAY:
                    dateButton= rootView.findViewById(R.id.Friday);
                    dateButton.setText(""+date);

                    break;
                case Calendar.SATURDAY:
                    dateButton= rootView.findViewById(R.id.Saturday);
                    dateButton.setText(""+date);

                    break;
                default:
                    ContextFlowUtilities.presentAlert("Αποτυχία","Κάτι πηγε λάθος");
                    break;

            }
            if(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)>date) {
                date = date + 1;
            }else{
                month= month+1;
                date=1;
            }


        }*/
    }
}




