package com.example.ar_final;

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
import android.graphics.drawable.*;
import com.google.ar.core.Frame;
import com.google.ar.core.*;

import java.lang.ref.WeakReference;
import java.util.List;
import android.widget.FrameLayout;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;


public class ModelLoader {

    private final WeakReference<MainActivity> owner;
    private static final String TAG = "ModelLoader";

    ModelLoader(WeakReference<MainActivity> owner) {
        this.owner = owner;
    }

    void loadModel(Anchor anchor, Uri uri) {
        if (owner.get() == null) {
            Log.d(TAG, "Activity is null.  Cannot load model.");
            return;
        }
        ModelRenderable.builder()
                .setSource(owner.get(), uri)
                .build()
                .handle((renderable, throwable) -> {
                    MainActivity activity = owner.get();
                    if (activity == null) {
                        return null;
                    } else if (throwable != null) {
                        activity.onException(throwable);
                    } else {
                        activity.addNodeToScene(anchor, renderable);
                    }
                    return null;
                });

        return;
    }
}
