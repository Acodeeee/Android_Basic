package com.android.wrf_mac.activity_deliver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int D_BUNDLE = 1;
    public static final int D_PARCELABLE = 2;
    public static final int D_SERIALIZABLE = 3;
    public static final int D_RESULT = 4;

    public static final int TO_SA_CODE = 100;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        textView = (TextView) findViewById(R.id.tex_view_result);
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        switch (view.getId()){
            case R.id.button1:
                //使用Bundle存入参数
                Bundle bundle = new Bundle();
                bundle.putString("name","W");
                bundle.putInt("age",20);
                //intent.putExtras(bundle);
                intent.putExtra("text", bundle);
                intent.putExtra("id", D_BUNDLE);
                startActivity(intent);
                break;
            case R.id.button2:
                Book book = new Book();
                book.setBookName("第一行代码");
                book.setAuthor("郭霖");
                book.setPrice(59);
                intent.putExtra("book", book);
                intent.putExtra("id", D_PARCELABLE);
                startActivity(intent);
                break;
            case R.id.button3:
                Person person = new Person();
                person.setName("wrf");
                person.setAge(21);
                person.setAddress("Beijing");
                intent.putExtra("person", person);
                intent.putExtra("id", D_SERIALIZABLE);
                startActivity(intent);
                break;
            case R.id.button4:
                intent.putExtra("who", "I`m MainActivity");
                intent.putExtra("id", D_RESULT);
                startActivityForResult(intent, TO_SA_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TO_SA_CODE:
                if (resultCode == SecondActivity.BACK_MA_CODE){
                    textView.setText(data.getStringExtra("who"));
                }

        }
    }
}
