package org.artoolkit.ar6.artracking;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import static android.R.attr.path;

public class map extends Activity {

    private void drawCircle(int x, int y, Canvas toDraw) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#64DD17"));



        toDraw.drawCircle(x, y, 7, paint);

    }

    private void drawTriangle(int x, int y, int dir, Canvas toDraw) { //0 - up, 1 - right, 2 - down, 3 - left



        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setStrokeWidth(2);
        paint.setColor(Color.parseColor("#64DD17"));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        Point point1_draw = new Point(x, y);
        Point point2_draw = new Point();
        Point point3_draw = new Point();
        switch (dir) {
            case 0:
                point2_draw.x = x - 2;
                point2_draw.y = y + 3;

                point3_draw.x = x + 2;
                point3_draw.y = y + 3;
                break;
            case 1:
                point2_draw.x = x - 3;
                point2_draw.y = y - 2;

                point3_draw.x = x - 3;
                point3_draw.y = y + 2;
                break;
            case 2:
                point2_draw.x = x - 2;
                point2_draw.y = y - 3;

                point3_draw.x = x + 2;
                point3_draw.y = y - 3;
                break;
            case 3:
                point2_draw.x = x + 3;
                point2_draw.y = y - 2;

                point3_draw.x = x + 3;
                point3_draw.y = y + 2;
                break;

        }

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(point1_draw.x,point1_draw.y);
        path.lineTo(point2_draw.x,point2_draw.y);
        path.lineTo(point3_draw.x,point3_draw.y);
        path.lineTo(point1_draw.x,point1_draw.y);
        path.close();


        toDraw.drawPath(path, paint);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setContentView(new ScrollableImage(this));

        BitmapFactory.Options myOptions = new BitmapFactory.Options();
        myOptions.inDither = true;
        myOptions.inScaled = false;
        myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important
        myOptions.inPurgeable = true;
        Bitmap img =  BitmapFactory.decodeResource(map.this.getResources(), R.drawable.hermitage, myOptions);

        Bitmap workingBitmap = Bitmap.createBitmap(img);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x * 5;
        int height = size.y;

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(mutableBitmap, width, height, true);

        //Canvas toDraw = new Canvas(mutableBitmap);
        Canvas toDraw = new Canvas(scaledBitmap);
        drawCircle(440, 55, toDraw);

        drawTriangle(400, 55, 0, toDraw);
        drawTriangle(380, 55, 1, toDraw);
        drawTriangle(360, 55, 2, toDraw);
        drawTriangle(340, 55, 3, toDraw);

        ImageView imgView = (ImageView) findViewById(R.id.map);
        imgView.setAdjustViewBounds(true);
        imgView.setImageBitmap(scaledBitmap);

    }
}
