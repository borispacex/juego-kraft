package com.example.kraft;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
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

    // sensor
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int contador = 0;
    boolean estado = false;

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

        // sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor == null) finish();

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                System.out.println("valor de giro " + x);

                if (x < -4 && contador==0) {
                    contador++;
                } else if (x > 4 && contador==1) {
                    contador++;
                }
                if (contador == 2 && !estado) {
                    //
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
                            estado = true;
                        }
                        if (d1 == 1 && d2 == 1) {
                            t1.setText("PIERDE");
                            estado = true;
                        }
                        valorAnt = suma;
                        t2.setText(valorAnt+"");
                    }
                    if (nrolanzamiento > 1) {
                        if (valorAnt==suma) {
                            t1.setText("GANA");
                            btn1.setEnabled(false);
                            estado = true;
                        }
                    }
                    //
                    sound();
                    contador = 0;
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        start();
    }
    public void sound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sonido);
        mediaPlayer.start();
    }

    public void start() {
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void stop() {
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }

    @Override
    protected void onResume() {
        start();
        super.onResume();
    }

    public void lanzamiento(View view) {
        sound();

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
                estado = true;
            }
            if (d1 == 1 && d2 == 1) {
                t1.setText("PIERDE");
                estado = true;
            }
            valorAnt = suma;
            t2.setText(valorAnt+"");
        }
        if (nrolanzamiento > 1) {
            if (valorAnt==suma) {
                t1.setText("GANA");
                btn1.setEnabled(false);
                estado = true;
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
        img1.setImageResource(R.drawable.juegodados);
        img2.setImageResource(R.drawable.juegodados);
        btn1.setEnabled(true);
        estado = false;

    }
}
