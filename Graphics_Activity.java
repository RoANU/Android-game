package com.example.asus.indus_train;

/**
 * Created by asus on 8/14/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;


public class Graphics_Activity extends View implements View.OnTouchListener{
    Bitmap graph,touch;
    float vc=0;
    float ac=0;
    char yy='d';
    char xx='f';
    float x=0;
    int m=2,n=2;
    MediaPlayer md;
    public Graphics_Activity(Context context) {
        super(context);
        graph= BitmapFactory.decodeResource(getResources(),R.drawable.icon2);
        touch=BitmapFactory.decodeResource(getResources(),R.drawable.icon);
        md=MediaPlayer.create(context,R.raw.alimba);
        md.start();
        setOnTouchListener(this);
        setOnCompletionListener();

    }
    public void setOnCompletionListener(final MediaPlayer.OnCompletionListener pOnCompletionListener) {

        md.reset();
    }

    @Override
    public boolean isLayoutDirectionResolved() {
        return super.isLayoutDirectionResolved();
    }

    public boolean onTouch(View view, MotionEvent motionevent) {
        Log.d("ontouch","called");
        x=motionevent.getX();
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);

        canvas.drawBitmap(graph,ac,vc,null);
        canvas.drawBitmap(touch,x-(touch.getWidth()/2),canvas.getHeight()-(touch.getHeight()),null);
        if(yy=='d')
        {
           if(vc<canvas.getHeight()-graph.getHeight())
               vc+=m;
            else
           {
               yy='u';
               vc-=m;
           }
        }
        else {
            if(vc>0)
            {
                vc-=m;
            }else{
                yy='d';
                vc+=m;
            }

        }
        if(xx=='f')
        {
            if(ac<canvas.getWidth()-graph.getWidth())
                ac+=n;
            else
            {
                xx='b';
                ac-=n;
            }
        }
        else {
            if(ac>0)
            {
                ac-=n;
            }else{
                xx='f';
                ac+=n;
            }

        }
        float q=canvas.getHeight()-touch.getHeight();
        q=q-graph.getHeight();
        Log.d("q",Integer.toString((int)q));
        Log.d("v",Integer.toString((int)vc));
        if((ac>=x-(touch.getWidth()/2)-graph.getWidth()+1 && ac<x+(touch.getWidth()/2+graph.getWidth()-1) ) && (vc<=q && vc>=q-m-1)) {
            if (xx == 'f') {
                if (ac < canvas.getWidth()-graph.getWidth())
                    ac += n;
                else {
                    xx = 'b';
                    ac -= n;
                }
            } else {
                if (ac > 0) {
                    ac -= n;
                } else {
                    xx = 'f';
                    ac += n;
                }

            }
            if (yy == 'd') {

                yy = 'u';
                vc -= m;

            }
        }
        Rect middleRect=new Rect();
        middleRect.set(0,canvas.getHeight()/2,canvas.getWidth(),canvas.getHeight()/2+1);
        Paint c=new Paint();
        c.setColor(Color.RED);
        canvas.drawRect(middleRect,c);
        invalidate();

    }

    /*public class surfx extends SurfaceView
    {
        public surfx(Context context) {
            super(context);
        }
    }*/

}
