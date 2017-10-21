package org.artoolkit.ar6.artracking;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button run = (Button) findViewById(R.id.run);
        run.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                runAR();
            }
        });
        Button map = (Button) findViewById(R.id.map_button);
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                runMap();
            }
        });
    }

    private void runAR() {
        startActivity(new Intent(MainActivity.this, ARTrackingActivity.class));
    }

    private void runMap() {
        startActivity(new Intent(MainActivity.this, map.class));
    }


}
