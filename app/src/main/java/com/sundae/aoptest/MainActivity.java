package com.sundae.aoptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.sundae.aoplib.annotation.CheckNet;
import com.sundae.aoplib.annotation.DebugTrack;
import com.sundae.aoplib.annotation.InterceptBefore;

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

    @CheckNet
    @DebugTrack
    @InterceptBefore(methodName = "callback")
    private void test()
    {
        Toast.makeText(MainActivity.this , "TEST 有网哟" , Toast.LENGTH_SHORT).show();
    }

    private boolean callback()
    {
        Toast.makeText(this , "CALLBACKMETHOD" , Toast.LENGTH_SHORT).show();
        return true;
    }

}
