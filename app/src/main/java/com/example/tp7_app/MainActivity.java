package com.example.tp7_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText module;
    EditText acronyme;
    EditText vh;
    EditText departement;
    Button enregistrer;
    ListView listview;

    ArrayList<String> listModules;
    ArrayAdapter<String> adapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        module = (EditText)findViewById(R.id.module_id);
        acronyme = (EditText)findViewById(R.id.acronyme_id);
        vh = (EditText)findViewById(R.id.vh_id);
        departement = (EditText)findViewById(R.id.departement_id);
        enregistrer = (Button)findViewById(R.id.btn_id);
        listview = (ListView)findViewById(R.id.lv_id);

        listModules = new ArrayList<String>();

        databaseHelper = new DatabaseHelper(MainActivity.this);
        listModules = databaseHelper.getAllext();
        adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,listModules);
        listview.setAdapter(adapter);


        enregistrer.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {

                String get_module = module.getText().toString();
                String get_acronyme = acronyme.getText().toString();
                String get_vh = vh.getText().toString();
                String get_departement = departement.getText().toString();

                if (!get_module.isEmpty() && !get_acronyme.isEmpty() && !get_vh.isEmpty() && !get_departement.isEmpty()){
                    if (databaseHelper.addText(get_module, get_acronyme, get_vh, get_departement)){
                        module.setText("");
                        acronyme.setText("");
                        vh.setText("");
                        departement.setText("");
                        Toast.makeText(MainActivity.this, "DATA INSERTED..", Toast.LENGTH_SHORT).show();
                        listModules.clear();
                    }
                }
                listModules.addAll(databaseHelper.getAllext());
                adapter.notifyDataSetChanged();
                listview.invalidateViews();
                listview.refreshDrawableState();
            }
        });



    }
}