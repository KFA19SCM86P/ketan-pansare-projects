package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;

public class MainActivity extends AppCompatActivity
{
    EditText edit1;
    TextView text2,text3,text5,text6;
    RadioButton radio1, radio2;
    String result ="";
    private String MV = "Miles Values:";
    private String KV = "KM Values:";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit1 = findViewById(R.id.editText1);
        text2 = findViewById(R.id.textView2);
        text3 = findViewById(R.id.textView3);
        text5 = findViewById(R.id.textView5);
        text6 = findViewById(R.id.textView6);
        text6.setMovementMethod(new ScrollingMovementMethod());
        radio1 = findViewById(R.id.radioButton1);
        radio2 = findViewById(R.id.radioButton2);

        radio1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Enter Values in Miles", Toast.LENGTH_SHORT).show();
                text2.setText(MV);
                text3.setText(KV);
            }
        });

        radio2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Enter values in KM", Toast.LENGTH_SHORT).show();
                text2.setText(KV);
                text3.setText(MV);
            }
        });

    }

    public void convertButton (View v)
    {
        String value = edit1.getText().toString().trim();

        if (radio1.isChecked())
        {
            if(value.isEmpty())
            {
               Toast.makeText(getApplicationContext(),"'Enter Value!",Toast.LENGTH_SHORT).show();
            }

            else
            {
                double input=Double.parseDouble(value);
                double response;
                response = input * 1.60934;
                String output = String.format("%.1f", response);
                text5.setText(output);
                result = value + " MV ==> " + String.format("%.1f", response) + " KV \n" + result;
                text6.setText(result);
                edit1.setText("");
            }

        }


        if (radio2.isChecked())
        {
            if(value.isEmpty())
            {
                Toast.makeText(getApplicationContext(),"'Enter Value!",Toast.LENGTH_SHORT).show();
            }
            else
                {
                    double input=Double.parseDouble(value);
                    double response;
                response = input * 0.621371;
                String output = String.format("%.1f", response);
                text5.setText(output);
                result = value + " KV ==> " + String.format("%.1f", response) + " MV \n" + result;
                text6.setText(result);
                edit1.setText("");
            }
        }

    }

    public void clearButton(View v)
    {
        text5.setText("");
        text6.setText(" ");
        result = "";
        Toast.makeText(getApplicationContext(), "History Cleared", Toast.LENGTH_SHORT).show();
    }

    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("Text6",text6.getText().toString());
        outState.putString("Text5",text5.getText().toString());
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        text6.setText(savedInstanceState.getString("Text6"));
        text5.setText(savedInstanceState.getString("Text5"));
        result=savedInstanceState.getString("Result");

    }

}
