package org.artoolkit.ar6.artracking;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class map extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        BitmapFactory.Options myOptions = new BitmapFactory.Options();
        myOptions.inDither = true;
        myOptions.inScaled = false;
        myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important
        myOptions.inPurgeable = true;
        Bitmap img =  BitmapFactory.decodeResource(map.this.getResources(), R.drawable.hermitage, myOptions); //(Bitmap)R.drawable.hermitage;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);

        Bitmap workingBitmap = Bitmap.createBitmap(img);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);


        Canvas canvas = new Canvas(mutableBitmap);
        canvas.drawCircle(440, 55, 7, paint);

        ImageView imgView = (ImageView) findViewById(R.id.map);
        imgView.setAdjustViewBounds(true);
        imgView.setImageBitmap(mutableBitmap);
    }
}
