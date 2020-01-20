package com.example.mydraganddeaw;

import android.graphics.PointF;

public class Box {
    private PointF mOrigin;
    private PointF mCurrent;

    public PointF getmPointerOrigin() {
        return mPointerOrigin;
    }

    private PointF mPointerOrigin;
    private float  angle;


    public Box(PointF origin) {
        mOrigin = origin;
        mCurrent = origin;
    }

    public void setPointerOrigin(PointF pointerOrigin) {
        mPointerOrigin = pointerOrigin;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }



    public PointF getmOrigin() {
        return mOrigin;
    }

    public PointF getmCurrent() {
        return mCurrent;
    }

    public void setmCurrent(PointF mCurrent) {
        this.mCurrent = mCurrent;
    }
}
