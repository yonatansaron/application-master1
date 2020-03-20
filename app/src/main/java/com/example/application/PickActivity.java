package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PickActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    Button mapbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);
        mapbutton = findViewById(R.id.mapbutton);
        mapbutton.setOnClickListener(this);

        Spinner spinner = findViewById(R.id.kmspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kilometers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Uri ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),ringtone);
    }


    @Override
    public void onClick(View v) {

        if(v == mapbutton)
        {
            Intent i = new Intent(getBaseContext(),Activity_map.class);
            startActivity(i);
        }

    }
    //            n.play();
    //        Uri note = RingtoneManager.getDefaultUri(RingtoneManager.Type_NOTIFICATION);
    //        Ringtone n = RingtoneManager.getRingtone(getApplicationContext(),note);

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
