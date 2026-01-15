package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        cityList = findViewById(R.id.city_list);
        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }


    public void add_city_click(View view) {
        EditText inputcity = findViewById(R.id.input_city);
        Button confirmButton = findViewById(R.id.button4);

        inputcity.setText("");
        inputcity.setVisibility(View.VISIBLE);
        confirmButton.setVisibility(View.VISIBLE);
        inputcity.requestFocus();
    }

    public void confirm_city_click(View view) {
        EditText inputcity = findViewById(R.id.input_city);
        String city = inputcity.getText().toString().trim();
        Button confirmButton = findViewById(R.id.button4);
        dataList.add(city);
        cityAdapter.notifyDataSetChanged();
        inputcity.setVisibility(View.GONE);
        confirmButton.setVisibility(View.GONE);
    }

    public void Delete_city_click(View view) {
        if (selectedIndex==-1){
            Toast.makeText(this, "Make Selection", Toast.LENGTH_SHORT).show();
            return;
        }
        dataList.remove(selectedIndex);
        cityAdapter.notifyDataSetChanged();
        selectedIndex = -1;
    }
}