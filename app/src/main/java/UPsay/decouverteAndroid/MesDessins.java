package UPsay.decouverteAndroid;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Paint;
import android.graphics.Color;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MesDessins extends View {
    float xText, yText;
    float taille = 0;
    float x1, x2, y1, y2;

    public MesDessins(Context context, AttributeSet attrs) {
        super(context, attrs);
        Toast.makeText(context, "Il faut saisir une heure", Toast.LENGTH_SHORT).show();
        setXYText(600,600);
        timerHandler.postDelayed(updateTimerThread, 10);

        OnTouchListener onTouchListener = new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float dx, dy;
                String direction;
                switch(event.getAction()) {
                    case(MotionEvent.ACTION_DOWN):
                        x1 = event.getX();
                        y1 = event.getY();
                        xText = x1;
                        yText = y1;
                        Log.i("pacman", "appuyé");
                        break;

                    case(MotionEvent.ACTION_UP): {
                        x2 = event.getX();
                        y2 = event.getY();
                        dx = x2 - x1;
                        dy = y2 - y1;
                        if (Math.abs(dx) > Math.abs(dy)) {
                            if (dx > 0)
                                direction = "right";
                            else
                                direction = "left";
                        } else {
                            if (dy > 0)
                                direction = "down";
                            else
                                direction = "up";
                        }
                        Log.i("pacman", "laché " + direction);
                        Log.i("pacman", "dx = " + dx + "; dy = " + dy);
                        break;
                    }
                }
                invalidate();
                return true;
            }
        };
        setOnTouchListener(onTouchListener);
    }

    public void setXYText (float x, float y){
        xText = x;
        yText = y;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        /*définir la couleur de l'objet de dessin*/
        p.setColor(Color.BLACK);
        /*définir son style en remplissage*/
        p.setStyle(android.graphics.Paint.Style.FILL);
        /*dessiner un rectangle qui occupe la totalité du View*/
        canvas.drawRect(0,0,getWidth(),getHeight(), p);
        /*définir une autre couleur pour dessiner un texte*/
        p.setColor(Color.GREEN);
        /*définir la taille du texte*/
        p.setTextSize(taille);
        /*définir le centre du texte comme étant son origine*/
        p.setTextAlign(android.graphics.Paint.Align.CENTER);
        /*dessiner le texte en positionnant son origine au centre du View*/
        String texte = "Bonjour MONDE";
        canvas.drawText(texte, xText, yText, p);
    }

    Handler timerHandler = new Handler();

    Runnable updateTimerThread = new Runnable() {
        public void run() {
            timerHandler.postDelayed(this, 100);
            if (taille < 100) {
                taille = taille + 2;
            }
            invalidate();     // appel de onDraw pour redessiner
        }
    };
}