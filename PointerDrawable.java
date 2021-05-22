package com.example.ar_final;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.PaintDrawable;
import android.os.*;
import android.net.*;

import android.content.Context;
import android.annotation.SuppressLint;


import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.*;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.*;
import android.graphics.drawable.*;
import com.google.ar.core.Frame;
import com.google.ar.core.*;

import java.lang.ref.WeakReference;
import java.util.List;
import android.widget.FrameLayout;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.graphics.drawable.Drawable;

public class PointerDrawable extends Drawable
{
    private final Paint paint = new Paint();
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        float cx = canvas.getWidth()/2;
        float cy = canvas.getHeight()/2;
        if (enabled) {
            paint.setColor(Color.GREEN);
            canvas.drawCircle(cx, cy, 10, paint);
        } else {
            paint.setColor(Color.GRAY);
            canvas.drawText("X", cx, cy, paint);
        }
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
