package com.example.zachcheu.carboncredit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends Activity {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().hide();
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.social_floating_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.floating_settings);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.floating_logs);
        final SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(this);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyPreferencesActivity.class);
                startActivity(intent);
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Var.RefreshRate = setting.getInt("prefRefresh", 0) + 1;
                Var.Noti = setting.getBoolean("prefNoti", true);
                Var.Sound = setting.getBoolean("prefSound", true);
                Var.Trans = Integer.parseInt(setting.getString("prefTrans", "0"));
                Log.d("Trans", " " + Var.Trans);
                Log.d("Rate", " " + Var.RefreshRate);
                Log.d("Noti", " " + Var.Noti);
                Log.d("Sound", " " + Var.Sound);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
