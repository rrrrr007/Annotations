package com.example.ch.annotationsmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ch.annotationsmodel.custom.AnnotateUtils;
import com.example.ch.annotationsmodel.custom.InJect;
import com.example.ch.annotationsmodel.nomal.Colors;

public class MainActivity extends AppCompatActivity {
    @InJect(id = R.id.tv)
    private TextView tv;
    private Colors colors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnnotateUtils.findViewById(this);
        colors = new Colors();
        colors.testThread(MainActivity.this);
    }

    @InJect(id = R.id.tv)
    public void onClick(){
        colors.setCurrent(1);
        colors.setSource(R.id.tv);
        colors.setName("1234567");
        tv.setText(colors.getName());
    }
}
