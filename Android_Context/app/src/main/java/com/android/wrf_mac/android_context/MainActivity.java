package com.android.wrf_mac.android_context;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;

    private boolean button_State = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);
        editText = (EditText) findViewById(R.id.edit_text);
        button = (Button) findViewById(R.id.btn_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button_State) {
                    String str = editText.getText().toString();
                    //将EditText中的数据存储到MyApplication中
                    ((MyApplication) getApplicationContext()).setId(str);
                    textView.setText(str);
                    button_State = false;
                    button.setText("启动SecondActivity");
                }else {
                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                    button_State = true;
                    button.setText("保存");
                }

            }
        });
    }
}
