package com.example.zachcheu.carboncredit;

import android.app.Activity;

/**
 * Created by ZachCheu on 10/10/16.
 */
public class Settings extends Activity {
   /* ListView listView;
    int item;
    ArrayAdapter<String> adapter;
    String[] SettingsList = {"Transportation","Record Data","Notifications","Sound","Refresh Rate"};
    String[] TransportationList = {"Bus", "Biking", "Prius", "Hummer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView)findViewById(R.id.settingsListView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, SettingsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = (int)parent.getItemIdAtPosition(position);
                if(item == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                    builder.setTitle("Select Transportation");
                    builder.setItems(TransportationList, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Var.TransportInt = which;
                            Toast.makeText(getApplicationContext(), TransportationList[which], Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else if(item == 1){
                    //Log.d("Test"," "+Var.TransportInt);
                }
            }

        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home ) {
            finish();
            return true;
        }
        // other menu select events may be present here

        return super.onOptionsItemSelected(item);
    }*/
}
