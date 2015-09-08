package com.androidyug.nitin.dragdrop;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    ImageView redImageView;
    ImageView greenImageView;
    ImageView whiteImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // view that will get drag
        redImageView = (ImageView) findViewById(R.id.ivRed);
        greenImageView = (ImageView) findViewById(R.id.ivGreen);

        redImageView.setOnTouchListener(new ImageViewTouchListener());
        greenImageView.setOnTouchListener(new ImageViewTouchListener());

        // view to drop onto
        whiteImageView = (ImageView) findViewById(R.id.ivWhite);

        whiteImageView.setOnDragListener(new BoxDragListener());

    }

    // Inner class for touch listener

    private  final class ImageViewTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            // check what motion event has been triggered off
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(LOG_TAG, "Dragging Down");

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

                v.startDrag(data, shadowBuilder, v, 0);

                return true;
            } else {
                return false;
            }
        }
    }


    private class BoxDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.d(LOG_TAG, "drag started");
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d(LOG_TAG, "drag entered");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.d(LOG_TAG, "drag Drag existed");
                    break;
                case DragEvent.ACTION_DROP:
                    Log.d(LOG_TAG, "drag Drop happened");
                    View view = (View) event.getLocalState();
                    ImageView ivType = (ImageView) view;
                    if (ivType.getId() == R.id.ivRed){
                        Toast.makeText(MainActivity.this, "Red is dropped", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Green is dropped", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d(LOG_TAG, "drag ended");
                    break;
                default:
                    break;
            }

            return true;

        }
    }







}
