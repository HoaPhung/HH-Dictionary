package com.example.admin.hh;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText txtNumber;
    TextView txtPercent;
    ProgressBar progressBar;
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addControl() {
        txtNumber=findViewById(R.id.txtNumber);
        txtPercent=findViewById(R.id.txtPercent);
        progressBar=findViewById(R.id.progressBar);
        btnStart=findViewById(R.id.btnStart);
    }

    private void addEvent() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlerRealTime();
            }
        });

    }

    private void handlerRealTime() {
        int number=Integer.parseInt(txtNumber.getText().toString());
        Task task=new Task();
        task.execute(number);
    }

    class Task extends AsyncTask<Integer, Integer, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtPercent.setText("0%");
            progressBar.setProgress(0);
            txtNumber.setEnabled(false);
            btnStart.setEnabled(false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            txtPercent.setText("100%");
            progressBar.setProgress(100);
            txtNumber.setEnabled(true);
            btnStart.setEnabled(true);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int persent=values[0];
            progressBar.setProgress(persent);
            txtPercent.setText(persent+"%");
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            int number=integers[0];
            for(int i=1; i<=number; i++){
                int persent=i*100/number;
                publishProgress(persent);
                SystemClock.sleep(0);
            }
            return null;
        }
    }
}
