package com.example.zachcheu.carboncredit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by DevWork on 10/31/16.
 */

public class CustomView extends View {

    private Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint p1 = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint debugPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int x, y;

    private int speed = 0;
    private int distance = 0;
    private int credits = 0;

    private Typeface typeface;
    private Typeface typeface2;

    private int width, height;

    private int textHeight = 0, textWidth = 0;
    private int textHeight1 = 0, textWidth1 = 0;
    private int textHeight2 = 0, textWidth2 = 0;

    private int speedTextH = 0, speedTextW = 0;
    private int distanceTextH = 0, distanceTextW = 0;
    private int creditsTextH = 0, creditsTextW = 0;

    public static final boolean DEBUG = false;

    public String speedActive = "0 (mph)";
    public String distanceActive = "0 (m)";
    public String creditActive = "0 (c)";



    public CustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final Display display = wm.getDefaultDisplay();
        ViewGroup.LayoutParams layoutParams = this.getLayoutParams();

        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Regular.ttf");
        typeface2 = Typeface.createFromAsset(context.getAssets(), "fonts/Lato-Bold.ttf");

        init();

        height = getResources().getDimensionPixelSize(R.dimen.height);
        width = display.getWidth();

        //getRootView().setY(display.getHeight() - height - launcher.bottomNavigation.getHeight());
        System.out.println("debug: " + launcher.bottomNavigation.getY());
        getRootView().setX(0);
        getRootView().setY(display.getHeight() - height - getResources().getDimensionPixelSize(R.dimen.height1));

      //  getRootView().setX(0);
         //x = display.getWidth();
        //y = display.getHeight();
    }



    private void init(){
        p.setColor(Color.parseColor("#d3d3d3"));
        p.setTextSize(getResources().getDimensionPixelSize(R.dimen.textSize));

        p1.setColor(Color.parseColor("#FFFFFF"));
        p1.setTextSize(getResources().getDimensionPixelSize(R.dimen.textSize2));

        p.setAntiAlias(true);
        p1.setAntiAlias(true);

        p.setTypeface(typeface);
        p1.setTypeface(typeface2);

        Rect f = new Rect();
        String speed = "SPEED";

        p.getTextBounds(speed, 0, speed.length(), f);

        textHeight = f.height();
        textWidth = f.width();



        f = new Rect();
        speed = "DISTANCE";

        p.getTextBounds(speed, 0, speed.length(), f);

        textHeight1 = f.height();
        textWidth1 = f.width();


        f = new Rect();
        speed = "CREDITS";

        p.getTextBounds(speed, 0, speed.length(), f);

        textHeight2 = f.height();
        textWidth2 = f.width();

        debugPaint.setColor(Color.WHITE);
        debugPaint.setStrokeWidth(2.0f);

        Point text_f = calcSize(p1, "0 (mph)");
        Point text_f1 = calcSize(p1, "0 (m)");
        Point text_f2 = calcSize(p1, "0 (c)");

        speedTextH = text_f.y;
        speedTextW = text_f.x;
        distanceTextH = text_f1.y;
        distanceTextW = text_f1.x;
        creditsTextH = text_f2.y;
        creditsTextW = text_f2.x;
    }

    /*
     * Based on font & string, calculate pixels wide and height of text
     */
    public Point calcSize(Paint paint, String text){
        Rect r = new Rect();
        paint.getTextBounds(text, 0, text.length(), r);
        return new Point(r.width(), r.height());
    }




    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.parseColor("#B3000000"));

        canvas.drawText("SPEED", width/6 - textWidth/2 , height/2 + textHeight/2  - height/4, p);
        canvas.drawText("DISTANCE", width/2 - textWidth1/2 , height/2 + textHeight1/2  - height/4, p);
        canvas.drawText("CREDITS", width*5/6 - textWidth2/2, height/2 + textHeight2/2 - height/4, p);

        canvas.drawText(speedActive, width/6 - speedTextW/2, height/2 + speedTextH/2 , p1);
        canvas.drawText(distanceActive, width/2 - distanceTextW/2, height/2 + distanceTextH/2 , p1);
        canvas.drawText(creditActive, width*5/6 - creditsTextW/2, height/2 + creditsTextH/2, p1);

        // Draw grid margins @use for debug
        if(DEBUG) {
            canvas.drawLine(width / 3, height, width / 3, 0, debugPaint);
            canvas.drawLine(width * 2 / 3, height, width * 2 / 3, 0, debugPaint);
            canvas.drawLine(width, height, width, 0, debugPaint);
            canvas.drawLine(0, height / 2, width, height / 2, debugPaint);
        }

    }

    public void forceRefresh(){
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println(widthMeasureSpec + ",  " + heightMeasureSpec);
        setMeasuredDimension(width, height);
    }


}
