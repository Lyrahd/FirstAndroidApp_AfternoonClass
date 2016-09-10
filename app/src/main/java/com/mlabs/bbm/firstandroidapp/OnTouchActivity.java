package com.mlabs.bbm.firstandroidapp;


        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.Toast;

public class OnTouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_touch_activity);

        ImageView image = (ImageView) findViewById(R.id.imageView2);

        image.setOnTouchListener(new View.OnTouchListener() {
            float initX = 0, finalX = 0, initY = 0, finalY = 0;
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent){

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        initX = motionEvent.getX();
                        initY = motionEvent.getY();
                        Toast.makeText(getApplicationContext(),""+String.format("ACTION_DOWN - x:%.2f, y:%.2f",initX,initY),Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        finalX = motionEvent.getX();
                        finalY = motionEvent.getY();
                        actionDisplay(initX, finalX, initY, finalY);
                        return true;
                }
                return false;
            }

        });

    }
    public void actionDisplay(float initX, float finalX, float initY, float finalY){
        String prompt = "";

        if (initX<finalX){
            prompt = String.format("SWIPED LEFT to RIGHT");
        }else if(initX>finalX){
            prompt = String.format("SWIPED RIGHT to LEFT");
        }
        Toast.makeText(getApplicationContext(),""+prompt,Toast.LENGTH_SHORT).show();

        if(initY<finalY){
            prompt = String.format("SWIPED UP to DOWN");
        }else if(initY>finalY){
            prompt = String.format("SWIPED DOWN to UP");
        }

        Toast.makeText(getApplicationContext(),""+prompt,Toast.LENGTH_SHORT).show();
    }
}

