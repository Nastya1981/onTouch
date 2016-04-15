package com.example.android.drag;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    TextView _view;
    TextView _view2;
    ViewGroup _root;
    private int _xDelta;
    private int _yDelta;
    int x;
    int y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _root = (ViewGroup)findViewById(R.id.root);

        _view = new TextView(this);
        _view2 = new TextView(this);
        _view.setBackgroundResource(this
                .getResources()
                .getIdentifier("roundrect", "drawable", this
                        .getPackageName()));
        _view2.setBackgroundResource(this
                .getResources()
                .getIdentifier("roundrect", "drawable", this
                        .getPackageName()));
        _view.setText("TextView!!!!!!!!");
        _view2.setText("find me");

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 50);
        layoutParams.leftMargin = 50;
        layoutParams.topMargin = 50;
        layoutParams.bottomMargin = -250;
        layoutParams.rightMargin = -250;
        _view.setLayoutParams(layoutParams);

        _view.setOnTouchListener(this);
        _root.addView(_view);
        _root.addView(_view2);
    }
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        String str = "";
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                _view.setX(event.getX());
                _view.setY(event.getY());
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                view.setLayoutParams(layoutParams);
                break;
        }
        _root.invalidate();
        _view2.setText(str);
        return true;
    }
}
