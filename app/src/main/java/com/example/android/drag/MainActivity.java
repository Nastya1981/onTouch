package com.example.android.drag;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        _view2.setBackgroundResource(R.drawable.roundrect);
        _view.setBackgroundResource(R.drawable.roundrect);

        _view.setText("TextView!!!!!!!!");
        _view2.setText("find me");

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 50);
        layoutParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.fab_margin);
        layoutParams.topMargin = 50;
        layoutParams.height = 250;
        layoutParams.width = 250;


        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(150, 50);
        layoutParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.fab_margin);
        layoutParams.topMargin = 50;
        layoutParams.height = 250;
        layoutParams.width = 250;

        _view.setLayoutParams(layoutParams);
        _view2.setLayoutParams(layoutParams2);

        _view.setOnTouchListener(this);
        _root.addView(_view2);
        _root.addView(_view);
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
//                _view.setX(event.getX());
//                _view.setY(event.getY());
//

                if((_view.getY()<_view2.getY()&&_view.getY()+10>=_view2.getY())||(_view.getY()>_view2.getY()&&_view.getY()-10<=_view2.getY())){
                    if((_view.getX()<_view2.getX()&&_view.getX()+10>=_view2.getX())||(_view.getX()>_view2.getX()&&_view.getX()-10<=_view2.getX())){
                        Toast.makeText(this,"Catch!",Toast.LENGTH_LONG).show();
                    }
                }

                _view.setText(String.valueOf(_view.getY())+"");
                _view2.setText(String.valueOf(_view2.getY())+" ");//+String.valueOf(_view2.getX())+" \n"+String.valueOf(_view2.getHeight()));
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
//        _view2.setText(str);
        return true;
    }
}
