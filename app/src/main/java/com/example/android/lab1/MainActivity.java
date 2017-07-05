package com.example.android.lab1;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;




@SuppressWarnings("WrongConstant")
public class MainActivity extends AppCompatActivity {
    static final String RESULT = "RESULT";
    static final String COLOR = "COLOR";
    static final String IMAGE = "IMAGE";
    static final String WEIGHT = "WEIGHT";
    static final String HEIGHT = "HEIGHT";
    static final String METRIC = "METRIC";
    static final String IMPERIAL = "IMPERIAL";
    static final String VISIBLE = "VISIBILITY";
    static final String VISIBLE1= "VISIBILITY1";
    static final String KG = "KG";
    static final String CM = "CM";
    static final String IN = "INCHES";
    Button mButton;
    Toolbar myToolbar;
    ICountBMI b;
    EditText editText, editText1,editText2;
    ImageView image;
    TextView result,kg,cm,in;
    float w, h, bmi;
    int mColor, res, selectedTest;
    SharedPreferences sPref;
    RadioGroup radio;
    RadioButton metric,imperial;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        mButton = (Button) findViewById(R.id.countBMI);
        editText = (EditText) findViewById(R.id.weight_enter);
        editText1 = (EditText) findViewById(R.id.height_enter);
        editText2 = (EditText) findViewById(R.id.inches_enter);
        res = R.drawable.where;


        result = (TextView) findViewById(R.id.result);
        kg = (TextView) findViewById(R.id.kg);
        cm = (TextView) findViewById(R.id.cm);
        in = (TextView) findViewById(R.id.inches);
        image = (ImageView) findViewById(R.id.image);
        radio = (RadioGroup)findViewById(R.id.radios );
        metric = (RadioButton)findViewById(R.id.metric);
        imperial = (RadioButton)findViewById(R.id.imperial);

        if (savedInstanceState != null) {

            onRestoreInstanceState(savedInstanceState);


        }
        loadText();



        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTest = radio.getCheckedRadioButtonId();
                try {
                    if (selectedTest==R.id.imperial){
                    b = new CountBMIforInPounds();
                    w = Float.parseFloat(editText.getText().toString())+ Float.parseFloat(editText2.getText().toString())/12f;
                    h = Float.parseFloat(editText1.getText().toString());

                        bmi = b.countBMI(h ,w);}
                    else if
                        (selectedTest==R.id.metric){
                            b = new CountBMIforKgM();
                            w = Float.parseFloat(editText.getText().toString());
                            h = Float.parseFloat(editText1.getText().toString());
                            bmi = b.countBMI(h / 100, w);}
                    else {Toast.makeText(getApplicationContext(),"Choose units",Toast.LENGTH_SHORT).show();}



                    if (bmi <= 18.5) {
                        mColor = Color.YELLOW;
                        image.
                                setImageResource(R.drawable.underweight);
                        res = R.drawable.underweight;
                    }
                    if (bmi > 18.5 && bmi < 25.0) {
                        mColor = Color.GREEN;
                        image.setImageResource(R.drawable.normal_weight);
                        res = R.drawable.normal_weight;
                    }
                    if (bmi >= 25.0 && bmi < 30.0) {
                        mColor = Color.parseColor("#FFA500");
                        image.setImageResource(R.drawable.overweight);
                        res = R.drawable.overweight;
                    }
                    if (bmi >= 30.0) {
                        mColor = Color.RED;
                        image.setImageResource(R.drawable.obese);
                        res = R.drawable.obese;
                    }
                    result.setBackgroundColor(mColor);
                    result.setText(new DecimalFormat("##.##").format(bmi));
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(),"Oops, wrong input :)",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();


        switch(view.getId()) {
            case R.id.metric:
                if (checked)
                {editText.setHint("55.5");
                    editText.setText("");
                    editText1.setText("");
                    editText1.setHint("165.7");
                    kg.setText("kg");
                    result.setText("0");
                    result.setBackgroundColor(Color.TRANSPARENT);
                    cm.setText("cm");
                    in.setVisibility(View.INVISIBLE);
                    editText2.setVisibility(View.INVISIBLE);
                }

                break;
            case R.id.imperial:
                if (checked)
                {kg.setText("lbs");
                    cm.setText("ft");
                    result.setText("0");
                    result.setBackgroundColor(Color.TRANSPARENT);
                    editText.setText("");
                    editText1.setText("");
                    editText2.setText("");
                    editText.setHint("120.1");
                    editText1.setHint("5   ");
                    in.setVisibility(View.VISIBLE);
                    editText2.setVisibility(View.VISIBLE);}


                break;
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.author:
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
                return true;
            case R.id.share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "Check it out.My BMI is " + result.getText().toString();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"My BMI");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Shearing Option"));
                return true;
            case R.id.save:
                saveText();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void saveText() {

        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(HEIGHT, editText1.getText().toString());
        ed.commit();
        ed.putString(WEIGHT, editText.getText().toString());
        ed.commit();
        ed.putBoolean(METRIC,metric.isChecked());
        ed.commit();
        ed.putBoolean(IMPERIAL,imperial.isChecked());
        ed.commit();
        ed.commit();
        ed.putString(KG,kg.getText().toString());
        ed.commit();
        ed.putString(CM,cm.getText().toString());
        ed.commit();
        if (imperial.isChecked()==true) {
            ed.putString(IN, editText2.getText().toString());
            ed.commit();
        }



    }

    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedTextH = sPref.getString(HEIGHT, "");
        editText1.setText(savedTextH);
        String savedTextW = sPref.getString(WEIGHT, "");
        editText.setText(savedTextW);
        String weight = sPref.getString(KG,"");
        String height = sPref.getString(CM,"");
        kg.setText(weight);
        cm.setText(height);
        boolean isCheckedIm = sPref.getBoolean(IMPERIAL,false);
        boolean isCheckedMet = sPref.getBoolean(METRIC,false);
        imperial.setChecked(isCheckedIm);
        metric.setChecked(isCheckedMet);
        if (imperial.isChecked()==true) {
            String inches = sPref.getString(IN,"");
            editText2.setText(inches);
            in.setVisibility(View.VISIBLE);
            editText2.setVisibility(View.VISIBLE);
        }


    }


    protected void onSaveInstanceState(Bundle outState) {

        outState.putFloat(RESULT, bmi);
        outState.putInt(COLOR, mColor);
        outState.putInt(IMAGE, res);
        super.onSaveInstanceState(outState);
        outState.putBoolean(METRIC, metric.isChecked());
        outState.putBoolean(IMPERIAL, imperial.isChecked());
        outState.putInt(VISIBLE,in.getVisibility());
        outState.putInt(VISIBLE1,editText2.getVisibility());
        outState.putString(KG,kg.getText().toString());
        outState.putString(CM,cm.getText().toString());


    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        bmi = savedInstanceState.getFloat(RESULT);
        result.setText(new DecimalFormat("##.##").format(bmi));
        mColor = savedInstanceState.getInt(COLOR);
        result.setBackgroundColor(mColor);
        res = savedInstanceState.getInt(IMAGE);
        image.setImageResource(res);
        metric.setChecked(savedInstanceState.getBoolean(METRIC));
        imperial.setChecked(savedInstanceState.getBoolean(IMPERIAL));
        int vis = savedInstanceState.getInt(VISIBLE);
        in.setVisibility(vis);
        editText2.setVisibility(savedInstanceState.getInt(VISIBLE1));
        kg.setText(savedInstanceState.getString(KG));
        cm.setText(savedInstanceState.getString(CM));


    }


}