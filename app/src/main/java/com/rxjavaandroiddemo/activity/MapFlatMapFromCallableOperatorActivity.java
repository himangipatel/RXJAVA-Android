package com.rxjavaandroiddemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.rxjavaandroiddemo.R;
import com.rxjavaandroiddemo.model.ColorModel;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MapFlatMapFromCallableOperatorActivity extends AppCompatActivity {

  @BindView(R.id.image) ImageView image;
  @BindView(R.id.progressBar) ProgressBar progressBar;
  private String JSON = "{\"color\":\"red\",\"value\":\"#f00\"}";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_flat_map_operator);
    ButterKnife.bind(this);
    fromCallableExample();

    mapDemo();

    flatmapDemo();
  }

  private void flatmapDemo() {
    Observable.just(10)
        .flatMap(new Func1<Integer, Observable<String>>() {
          @Override public Observable<String> call(Integer integer) {
            return Observable.just(String.valueOf(integer), String.valueOf(integer + 1),
                String.valueOf(integer + 2));
          }
        })
        .subscribe(new Observer<String>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(String s) {
            Log.d("Flat-map-operator", s);
          }
        });
  }

  //map transform one event to another
  private void mapDemo() {
    Observable.just(JSON)
        .map(new Func1<String, ColorModel>() {
          @Override public ColorModel call(String s) {
            return new Gson().fromJson(s, ColorModel.class);
          }
        }).subscribe(new Observer<ColorModel>() {
      @Override public void onCompleted() {

      }

      @Override public void onError(Throwable e) {

      }

      @Override public void onNext(ColorModel colorModel) {
        Log.d("Flat-map-operator", colorModel.getColor());
      }
    });

    Single.just("4").map(new Func1<String, Integer>() {
      @Override
      public Integer call(String integer) {
        return Integer.valueOf(integer);
      }
    }).subscribe(new SingleSubscriber<Integer>() {
      @Override
      public void onSuccess(Integer value) {
        Log.d("Map-Operator", String.valueOf(value));
      }

      @Override
      public void onError(Throwable error) {

      }
    });
  }

  public Bitmap getBitmapFromURL(String src) {
    try {
      URL url = new URL(src);
      HttpURLConnection connection = (HttpURLConnection) url
          .openConnection();
      connection.setDoInput(true);
      connection.connect();
      InputStream input = connection.getInputStream();
      Bitmap myBitmap = BitmapFactory.decodeStream(input);
      return myBitmap;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private void fromCallableExample() {
    progressBar.setVisibility(View.VISIBLE);
    Observable.fromCallable(new Callable<Bitmap>() {
      @Override public Bitmap call() throws Exception {
        return getBitmapFromURL("http://www.flyingstart.uk.com/wp-content/uploads/2014/12/Merry-Christmas-pictures-free.jpg");
      }
    }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Bitmap>() {
          @Override public void onCompleted() {
            progressBar.setVisibility(View.GONE);
          }

          @Override public void onError(Throwable e) {
            Log.e("FromCallable", e.getMessage());
          }

          @Override public void onNext(Bitmap bitmap) {
            image.setImageBitmap(bitmap);
          }
        });
  }
}
