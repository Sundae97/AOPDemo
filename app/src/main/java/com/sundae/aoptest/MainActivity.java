package com.sundae.aoptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.sundae.aoplib.annotation.CheckNet;
import com.sundae.aoplib.annotation.DebugTrack;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });
    }

    @CheckNet(callbackMethodName = "callback")
    @DebugTrack
    private void test()
    {
        Toast.makeText(MainActivity.this , "TEST 有网哟" , Toast.LENGTH_SHORT).show();
    }

    private boolean callback()
    {
        Toast.makeText(this , "checkNet" , Toast.LENGTH_SHORT);
        return true;
    }

}
