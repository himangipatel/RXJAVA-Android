package com.rxjavaandroiddemo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.rxjavaandroiddemo.utils.ActivityUtils;
import com.rxjavaandroiddemo.R;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.tvJustFromOperator) TextView tvObservable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
  }

  @OnClick({R.id.tvJustFromOperator, R.id.tvMapFlatMap})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.tvJustFromOperator:
        ActivityUtils.launchActivity(MainActivity.this, JustFromFilterOperatorDemoActivity.class,
            false);
        break;
      case R.id.tvMapFlatMap:
        ActivityUtils.launchActivity(MainActivity.this, MapFlatMapFromCallableOperatorActivity.class,
            false);
        break;
    }
  }
}
