package org.artoolkit.ar6.artracking;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import static android.R.attr.path;

public class map extends Activity {
    private FloatingActionButton left;
    private FloatingActionButton right;

    private void drawCircle(int x, int y, Canvas toDraw) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#64DD17"));



        toDraw.drawCircle(x, y, 13, paint);

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
                point2_draw.x = x - 4;
                point2_draw.y = y + 6;

                point3_draw.x = x + 4;
                point3_draw.y = y + 6;
                break;
            case 1:
                point2_draw.x = x - 6;
                point2_draw.y = y - 4;

                point3_draw.x = x - 6;
                point3_draw.y = y + 4;
                break;
            case 2:
                point2_draw.x = x - 4;
                point2_draw.y = y - 6;

                point3_draw.x = x + 4;
                point3_draw.y = y - 6;
                break;
            case 3:
                point2_draw.x = x + 6;
                point2_draw.y = y - 4;

                point3_draw.x = x + 6;
                point3_draw.y = y + 4;
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
        Intent intent = getIntent();
        int hall = intent.getIntExtra("hall", -1);
        setContentView(R.layout.activity_map);

//        setContentView(new ScrollableImage(this));
//
//        BitmapFactory.Options myOptions = new BitmapFactory.Options();
//        myOptions.inDither = true;
//        myOptions.inScaled = false;
//        myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important
//        myOptions.inPurgeable = true;
        Bitmap img =  BitmapFactory.decodeResource(map.this.getResources(), R.drawable.hermitage2);

        Bitmap workingBitmap = Bitmap.createBitmap(img);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Canvas toDraw = new Canvas(mutableBitmap);
        if (hall == 1) {
            drawCircle(400, 105, toDraw);
            drawCircle(540, 177, toDraw);

            drawTriangle(500, 177, 3, toDraw);
            drawTriangle(480, 177, 3, toDraw);
            drawTriangle(460, 177, 3, toDraw);
            drawTriangle(440, 177, 3, toDraw);
            drawTriangle(420, 177, 3, toDraw);
            drawTriangle(400, 177, 0, toDraw);
            drawTriangle(400, 157, 0, toDraw);
            drawTriangle(400, 137, 0, toDraw);
        }

        if (hall == 2) {
            drawCircle(400, 105, toDraw);
            drawCircle(160, 105, toDraw);

            drawTriangle(180, 105, 1, toDraw);
            drawTriangle(200, 105, 1, toDraw);
            drawTriangle(220, 105, 1, toDraw);
            drawTriangle(240, 105, 1, toDraw);
            drawTriangle(260, 105, 1, toDraw);
            drawTriangle(280, 105, 1, toDraw);
            drawTriangle(300, 105, 1, toDraw);
            drawTriangle(320, 105, 1, toDraw);
            drawTriangle(340, 105, 1, toDraw);
            drawTriangle(360, 105, 1, toDraw);

        }

        //Canvas toDraw = new Canvas(mutableBitmap);

        ImageView imgView = (ImageView) findViewById(R.id.map);
        imgView.setImageBitmap(img);
        imgView.setAdjustViewBounds(true);
        imgView.setImageBitmap(mutableBitmap);

        left = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(map.this, ARTrackingActivity.class);
                intent.putExtra("AR", 2); // TODO
                startActivity(intent);
            }
        });


        right = (FloatingActionButton) findViewById(R.id.floatingActionButtonRight);
        right.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(map.this, ARTrackingActivity.class);
                intent.putExtra("AR", 1);  // TODO
                startActivity(intent);
            }
        });
    }
}
