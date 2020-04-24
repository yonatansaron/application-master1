package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.application.R;

public class MenuAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_manu_acitvity );

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate( R.menu.manu_layout, menu );
        return super.onCreateOptionsMenu( menu );
    }

    public boolean onOptionsIteamSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.itemExit){

        }
        return super.onOptionsItemSelected(item);
    }
}
