package com.example.ar_final;

import android.content.Intent;
import android.graphics.Bitmap;
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
import com.google.ar.sceneform.ArSceneView;
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

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.widget.FrameLayout;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;
import com.google.ar.sceneform.ux.ArFragment;
import java.io.*;
import android.content.*;

public class MainActivity extends AppCompatActivity {

    private ArFragment fragment;
    private PointerDrawable pointer = new PointerDrawable();
    private boolean isTracking;
    private boolean isHitting;
    private ModelLoader modelLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        fragment = (ArFragment)
                getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);

        fragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
            fragment.onUpdate(frameTime);
            onUpdate();
        });

        modelLoader = new ModelLoader(new WeakReference<>(this));

        initializeGallery();


    }

    private void initializeGallery() {
        LinearLayout gallery = findViewById(R.id.gallery_layout);


        ImageView Base_table = new ImageView(this);
        Base_table.setImageResource(R.drawable.basetable);
        Base_table.setContentDescription("Base_table");
        Base_table.setOnClickListener(view ->{addObject(Uri.parse("Base_table.sfb"));});
        gallery.addView(Base_table);

        ImageView Book_shelf = new ImageView(this);
        Book_shelf.setImageResource(R.drawable.book_shelf);
        Book_shelf.setContentDescription("Book_shelf");
        Book_shelf.setOnClickListener(view ->{addObject(Uri.parse("Book_shelf.sfb"));});
        gallery.addView(Book_shelf);


        /*ImageView Chair1 = new ImageView(this);
        Chair1.setImageResource(R.drawable.chair1);
        Chair1.setContentDescription("igloo");
        Chair1.setOnClickListener(view ->{addObject(Uri.parse("Chair1.sfb"));});
        gallery.addView(Chair1);

         */




        ImageView Chair2 = new ImageView(this);
        Chair2.setImageResource(R.drawable.chair2);
        Chair2.setContentDescription("Chair2");
        Chair2.setOnClickListener(view ->{addObject(Uri.parse("Chair2.sfb"));});
        gallery.addView(Chair2);

        ImageView cupboard = new ImageView(this);
        cupboard.setImageResource(R.drawable.cupboard);
        cupboard.setContentDescription("cupboard");
        cupboard.setOnClickListener(view ->{addObject(Uri.parse("cupboard.sfb"));});
        gallery.addView(cupboard);

        ImageView night_stand = new ImageView(this);
        night_stand.setImageResource(R.drawable.night_stand);
        night_stand.setContentDescription("night_stand");
        night_stand.setOnClickListener(view ->{addObject(Uri.parse("night_stand.sfb"));});
        gallery.addView(night_stand);

        ImageView Chair3 = new ImageView(this);
        Chair3.setImageResource(R.drawable.chair3);
        Chair3.setContentDescription("Chair3");
        Chair3.setOnClickListener(view ->{addObject(Uri.parse("Chair3.sfb"));});
        gallery.addView(Chair3);



        ImageView Couch = new ImageView(this);
        Couch.setImageResource(R.drawable.couch);
        Couch.setContentDescription("Couch");
        Couch.setOnClickListener(view ->{addObject(Uri.parse("Couch.sfb"));});
        gallery.addView(Couch);

        ImageView bed = new ImageView(this);
        bed.setImageResource(R.drawable.bed);
        bed.setContentDescription("bed");
        bed.setOnClickListener(view ->{addObject(Uri.parse("bed.sfb"));});
        gallery.addView(bed);


        ImageView Couch2 = new ImageView(this);
        Couch2.setImageResource(R.drawable.couch2);
        Couch2.setContentDescription("Couch2");
        Couch2.setOnClickListener(view ->{addObject(Uri.parse("Couch2.sfb"));});
        gallery.addView(Couch2);



        ImageView Drawer = new ImageView(this);
        Drawer.setImageResource(R.drawable.drawer);
        Drawer.setContentDescription("Drawer");
        Drawer.setOnClickListener(view ->{addObject(Uri.parse("Drawer.sfb"));});
        gallery.addView(Drawer);

        ImageView Chair_cabin = new ImageView(this);
        Chair_cabin.setImageResource(R.drawable.chair);
        Chair_cabin.setContentDescription("Chair_cabin");
        Chair_cabin.setOnClickListener(view ->{addObject(Uri.parse("Chair_cabin.sfb"));});
        gallery.addView(Chair_cabin);




        ImageView Old_chair = new ImageView(this);
        Old_chair.setImageResource(R.drawable.old_chair);
        Old_chair.setContentDescription("Old_chair");
        Old_chair.setOnClickListener(view ->{addObject(Uri.parse("Old_chair.sfb"));});
        gallery.addView(Old_chair);




    }


    private void addObject(Uri model) {
        Frame frame = fragment.getArSceneView().getArFrame();
        android.graphics.Point pt = getScreenCenter();
        List<HitResult> hits;
        if (frame != null) {
            hits = frame.hitTest(pt.x, pt.y);
            for (HitResult hit : hits) {
                Trackable trackable = hit.getTrackable();
                if (trackable instanceof Plane &&
                        ((Plane) trackable).isPoseInPolygon(hit.getHitPose())) {
                    modelLoader.loadModel(hit.createAnchor(), model);
                    break;

                }
            }
        }
    }
    private void onUpdate() {
        boolean trackingChanged = updateTracking();
        View contentView = findViewById(android.R.id.content);
        if (trackingChanged) {
            if (isTracking) {
                contentView.getOverlay().add(pointer);
            } else {
                contentView.getOverlay().remove(pointer);
            }
            contentView.invalidate();
        }

        if (isTracking) {
            boolean hitTestChanged = updateHitTest();
            if (hitTestChanged) {
                pointer.setEnabled(isHitting);
                contentView.invalidate();
            }
        }
    }


    private boolean updateTracking() {
        Frame frame = fragment.getArSceneView().getArFrame();
        boolean wasTracking = isTracking;
        isTracking = frame != null &&
                frame.getCamera().getTrackingState() == TrackingState.TRACKING;
        return isTracking != wasTracking;
    }

    private boolean updateHitTest() {
        Frame frame = fragment.getArSceneView().getArFrame();
        android.graphics.Point pt = getScreenCenter();
        List<HitResult> hits;
        boolean wasHitting = isHitting;
        isHitting = false;
        if (frame != null) {
            hits = frame.hitTest(pt.x, pt.y);
            for (HitResult hit : hits) {
                Trackable trackable = hit.getTrackable();
                if (trackable instanceof Plane &&
                        ((Plane) trackable).isPoseInPolygon(hit.getHitPose())) {
                    isHitting = true;
                    break;
                }
            }
        }
        return wasHitting != isHitting;
    }


    private android.graphics.Point getScreenCenter() {
        View vw = findViewById(android.R.id.content);
        return new android.graphics.Point(vw.getWidth()/2, vw.getHeight()/2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addNodeToScene(Anchor anchor, ModelRenderable renderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(fragment.getTransformationSystem());
        node.setRenderable(renderable);
        node.setParent(anchorNode);
        fragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
    }

    public void onException(Throwable throwable){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(throwable.getMessage())
                .setTitle("Codelab error!");
        AlertDialog dialog = builder.create();
        dialog.show();
        return;
    }




}
