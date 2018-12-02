package com.example.think.younews.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.think.younews.IViews.IView;
import com.example.think.younews.Presenter.IssuePresenter;
import com.example.think.younews.R;

public class IssueActivity extends Activity implements View.OnClickListener,IView {
    private ImageView exit;
    private Button send;
    private EditText editText;
    private IssuePresenter presenter;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        init();
        presenter = new IssuePresenter(this);
    }

    private void init() {
        exit = (ImageView) findViewById(R.id.exit);
        send = (Button) findViewById(R.id.btn_Send);
        editText = (EditText) findViewById(R.id.edit);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.exit:
                finish();
                break;
            case R.id.btn_Send:
                sendFunction();
                break;
            default:
                break;
        }
    }

    private void sendFunction() {
        String  editString = editText.getText().toString();
        presenter.send(editString,userName);
    }

    public void showSuccess(){
        Toast.makeText(this,"分享成功",Toast.LENGTH_SHORT).show();
    }
    public void showFail(){
        Toast.makeText(this,"分享失败",Toast.LENGTH_SHORT).show();
    }
}
