package com.example.lazyguy.exercisebonus;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {
    Price price = new Price();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        final EditText etUSD = (EditText) findViewById(R.id.etUSD);
        final EditText etVND = (EditText) findViewById(R.id.etVND);

        final Spinner spinner = setItemForSpinner();

        Button btnChange = (Button) findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currencyCode = spinner.getSelectedItem().toString();
                double money = Double.parseDouble(etUSD.getText().toString());
                double priceUnit = price.getPrice(currencyCode);
                int result = (int)(money*priceUnit);
                etVND.setText(String.valueOf(result));
            }
        });
    }

    private Spinner setItemForSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return spinner;
    }

}
