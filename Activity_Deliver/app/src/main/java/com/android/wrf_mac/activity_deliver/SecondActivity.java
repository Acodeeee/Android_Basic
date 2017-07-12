package com.android.wrf_mac.activity_deliver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final int BACK_MA_CODE = 100;


    TextView textView;
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.text_view_sec);
        back = (Button) findViewById(R.id.button_back);

        receiveFormMA();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("who", "I`m SecondActivity");
                setResult(BACK_MA_CODE, intent);
                finish();
            }
        });

    }


    private void receiveFormMA(){
        //取到Intent,再取Bundle,再取存入的值
        Intent intent = getIntent();
//      //bundle = intent.getExtras();
        Log.d("SecondActivity", intent.getIntExtra("id", 0) + "");

        switch (intent.getIntExtra("id",0)){
            case MainActivity.D_BUNDLE:
                Bundle bundle = intent.getBundleExtra("text");
                String name = bundle.getString("name");
                int age = bundle.getInt("age");
                String des = bundle.getString("des","No describe");//若没有值，给定一个默认值
                textView.setText("Name is " + name + ",age is " + age + ",describe is " + des);
                break;

            case MainActivity.D_PARCELABLE:
                Book book = intent.getParcelableExtra("book");
                textView.setText("书名：" + book.getBookName() + "， 作者：" + book.getAuthor() +
                        " ,价格： " + book.getPrice());
                break;

            case MainActivity.D_SERIALIZABLE:
                Person person = (Person) intent.getSerializableExtra("person");
                textView.setText("Name is " + person.getName() + ",age is " + person.getAge() +
                        ",address is " + person.getAddress());
                break;

            case MainActivity.D_RESULT:
                textView.setText(intent.getStringExtra("who"));
                break;

            default:
                textView.setText("没有接收到数据");
        }
    }
}
