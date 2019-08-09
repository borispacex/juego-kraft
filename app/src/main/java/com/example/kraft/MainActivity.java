package com.example.kraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText pd1;
    EditText pd2;
    TextView t1;
    TextView t2;
    TextView t3;
    ImageView img1;
    ImageView img2;
    Button btn1;
    Button btn2;

    int suma = 0;
    int nrolanzamiento = 0;
    int valorAnt = 0;

    int imagenes[] = {
            R.drawable.and_uno,
            R.drawable.and_dos,
            R.drawable.and_tres,
            R.drawable.and_cuatro,
            R.drawable.and_cinco,
            R.drawable.and_seis
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pd1 = (EditText) findViewById(R.id.editText1);
        pd2 = (EditText) findViewById(R.id.editText2);
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        img1 = (ImageView) findViewById(R.id.imageView1);
        img2 = (ImageView) findViewById(R.id.imageView2);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
    }

    public void lanzamiento(View view) {
        int d1 = (int) (Math.random()*6)+1;
        int d2 = (int) (Math.random()*6)+1;

        img1.setImageResource(imagenes[d1-1]);
        img2.setImageResource(imagenes[d2-1]);

        pd1.setText(d1+"");
        pd2.setText(d2+"");
        suma = d1 + d2;
        t3.setText(suma+"");

        nrolanzamiento++;
        if (nrolanzamiento == 1) {
            if(suma == 7 || suma == 11) {
                t1.setText("GANA");
                btn1.setEnabled(false);
            }
            if (d1 == 1 && d2 == 1) {
                t1.setText("PIERDE");
            }
            valorAnt = suma;
            t2.setText(valorAnt+"");
        }
        if (nrolanzamiento > 1) {
            if (valorAnt==suma) {
                t1.setText("GANA");
                btn1.setEnabled(false);
            }
        }



    }

    public void inicializacion(View view) {
        pd1.setText("");
        pd2.setText("");
        t1.setText("");
        t2.setText("0");
        t3.setText("0");
        nrolanzamiento = 0;
        suma = 0;
        valorAnt = 0;
        img1.setImageResource(0);
        img2.setImageResource(0);
        btn1.setEnabled(true);
    }
}
