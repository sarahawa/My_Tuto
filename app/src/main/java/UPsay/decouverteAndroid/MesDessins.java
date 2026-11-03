package UPsay.decouverteAndroid;


import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Paint;
import android.graphics.Color;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MesDessins extends View {
    float xText, yText;

    public MesDessins(Context context, AttributeSet attrs) {
        super(context, attrs);
        Toast.makeText(context, "Il faut saisir une heure", Toast.LENGTH_SHORT).show();
        setXYText(600,600);
        timerHandler.postDelayed(updateTimerThread, 10);

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
        p.setTextSize(100);
        /*définir le centre du texte comme étant son origine*/
        p.setTextAlign(android.graphics.Paint.Align.CENTER);
        /*dessiner le texte en positionnant son origine au centre du View*/
        String texte = "Bonjour MONDE";
        canvas.drawText(texte, xText, yText, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        xText = event.getX();
        yText = event.getY();
        invalidate();
        return false;
    }

    Handler timerHandler = new Handler();

    Runnable updateTimerThread = new Runnable() {
        public void run() {
            timerHandler.postDelayed(this, 100);
            invalidate();     // appel de onDraw pour redessiner
        }
    };


}
