package com.example.mydraganddeaw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";
    private Box mCurrentBox;
    private List<Box> mBoxen = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    public BoxDrawingView(Context context) {
        super(context);
    }

    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Paint the boxes a nice semitransparent red (ARGB)
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);
        // Paint the background off-white
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // Fill the background
        canvas.drawPaint(mBackgroundPaint);
        for (Box box : mBoxen) {
            float left = Math.min(box.getmOrigin().x, box.getmCurrent().x);
            float right = Math.max(box.getmOrigin().x, box.getmCurrent().x);
            float top = Math.min(box.getmOrigin().y, box.getmCurrent().y);
            float bottom = Math.max(box.getmOrigin().y, box.getmCurrent().y);

            float angle = box.getAngle();
            float px = (box.getmOrigin().x+box.getmCurrent().x)/2;
            float py = (box.getmOrigin().y+box.getmCurrent().y)/2;
            canvas.save();
            canvas.rotate(angle, px, py);
            canvas.drawRect(left, top, right, bottom, mBoxPaint);
            canvas.restore();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        PointF current=new PointF(event.getX(), event.getY());
//        String action = "";

        PointF touchPoint  = null;
        PointF touchPoint2 = null;
        for (int i=0;i<event.getPointerCount();i++) {
            if(event.getPointerId(i)==0)
                touchPoint = new PointF(event.getX(i), event.getY(i));
            if(event.getPointerId(i)==1)
                touchPoint2 = new PointF(event.getX(i), event.getY(i));
        }


        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mCurrentBox = new Box(touchPoint);
                mBoxen.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mCurrentBox.setPointerOrigin(touchPoint2);
                break;
            case MotionEvent.ACTION_MOVE:
                if(touchPoint  != null )
                    mCurrentBox.setmCurrent(touchPoint);
                if(touchPoint2 != null ) {
                    PointF boxOrigin     = mCurrentBox.getmOrigin();
                    PointF pointerOrigin = mCurrentBox.getmPointerOrigin();
                    float angle2 = (float) Math.atan2(touchPoint2.y   - boxOrigin.y, touchPoint2.x   - boxOrigin.x);
                    float angle1 = (float) Math.atan2(pointerOrigin.y - boxOrigin.y, pointerOrigin.x - boxOrigin.x);
                    float calculatedAngle = (float) Math.toDegrees(angle2 - angle1);
                    if (calculatedAngle < 0) calculatedAngle += 360;
                    mCurrentBox.setAngle(calculatedAngle);
                    Log.d(TAG, "Set Box Angle " + calculatedAngle);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "Finger UP Box Set");
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "Action Cancel Box Set");
                mCurrentBox = null;
                break;
        }

        return true;



//        switch (event.getAction())
//        {
//            case MotionEvent.ACTION_DOWN:
//            action= "ACTION_DOWN";
//                mCurrentBox = new Box(current);
//                mBoxen.add(mCurrentBox);
//            break;
//            case MotionEvent.ACTION_MOVE:
//                action = "ACTION_MOVE";
//                if (mCurrentBox != null) {
//                    mCurrentBox.setmCurrent(current);
//                    invalidate();
//                }
//
//                break;
//            case MotionEvent.ACTION_UP:
//                action = "ACTION_UP";
//                mCurrentBox=null;
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                action = "ACTION_CANCEL";
//                mCurrentBox=null;
//                break;
//        }
//        Log.i(TAG, action + " at x=" + current.x +
//                ", y=" + current.y);
//        return true;

    }

//    @Override
//    public Parcelable onSaveInstanceState() {
//        Log.d(TAG,"Saving Instance State");
//        Parcelable superState = super.onSaveInstanceState();
//        SavedState ss = new SavedState(superState);
//        ss.mBoxList = mBoxen;
//
//        return ss;
//    }
//
//    @Override
//    public void onRestoreInstanceState(Parcelable state) {
//
//        SavedState ss = (SavedState) state;
//        super.onRestoreInstanceState(ss.getSuperState());
//        Log.d(TAG,"Restoring Instance State");
//        mBoxen = ss.mBoxList;
//    }
//
//    private static class SavedState extends BaseSavedState {
//        private List<Box> mBoxList ;
//
//        public SavedState(Parcelable superState) {
//            super(superState);
//            Log.d(TAG,"Saving parcelable");
//        }
//    }
}
