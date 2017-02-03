package com.xiren.download;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;

import com.xiren.download.manager.UpdateManager;
import com.xiren.download.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {


    private Button btnVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView(){
        btnVersion = (Button) findViewById(R.id.btn_version);
    }

    private void initEvent(){
        btnVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateManager(MainActivity.this).checkUpdate(false);

            }
        });
    }

}
