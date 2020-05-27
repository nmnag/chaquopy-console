package com.chaquo.python.console;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chaquo.python.utils.*;

public class MainActivity extends PythonConsoleActivity {

    public static String textView;
    Button btn_fp;
    Intent myfileintent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_console);
        btn_fp= (Button) findViewById(R.id.btn_fp);

        btn_fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myfileintent =new Intent(Intent.ACTION_GET_CONTENT);
                myfileintent.setType("*/*");
                startActivityForResult(myfileintent,10);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK) {
                    String path = data.getData().getPath();
                    textView = path;

                }
                break;
        }

    }

    @Override protected Class<? extends Task> getTaskClass() {
        return Task.class;
    }

    public static class Task extends PythonConsoleActivity.Task  {

        public Task(Application app) { super(app);}

        @Override public void run() {
            py.getModule("main").callAttr("main", textView );
        }
    }
}
