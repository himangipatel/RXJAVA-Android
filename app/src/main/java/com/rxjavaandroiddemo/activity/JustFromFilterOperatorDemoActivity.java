package com.rxjavaandroiddemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.rxjavaandroiddemo.R;
import com.rxjavaandroiddemo.model.ColorModel;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class JustFromFilterOperatorDemoActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_observable_just_demo);
  }

  @Override protected void onResume() {
    super.onResume();
    init();
  }

  private void init() {

    differenceBetweenJustAndFrom();

    loopObservable();

    filterObsrvable();
  }

  private void differenceBetweenJustAndFrom() {
    //Observable.just(someList) will give you 1 emission - a List.
    Observable
        .just(getColorList())
        .subscribe(new Action1<List<String>>() {
          @Override public void call(List<String> strings) {

          }
        });

    // Observable.from(someList) will give you N emissions - each item in the list.
    Observable.from(getColorList())
        .subscribe(new Action1<String>() {
          @Override public void call(String s) {
            System.out.println("differenceBetweenJustAndFrom: " + s);
          }
        });
  }

  private void loopObservable() {
    //  To achieve N emissions using just operator
    Observable
        .just(1, 2, 3, 4, 5)
        .forEach(new Action1<Integer>() {
          @Override
          public void call(Integer item) {
            System.out.println("loopObservable: " + item);
          }
        });

    //Same for-each loop action is also perform like below
    Observable
        .just(1, 2, 3, 4, 5)
        .subscribe(new Observer<Integer>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(Integer integer) {
            System.out.println("loopObservable: " + integer);
          }
        });

    //Same for-each loop action is also perform like below
    Observable
        .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .doOnNext(new Action1<Integer>() {

          @Override

          public void call(Integer integer) {

            if (integer.equals(2)) {

              throw new RuntimeException("I don't like 2");
            }
          }
        })
        .subscribe(new Subscriber<Integer>() {

          @Override

          public void onCompleted() {

            System.out.println("loopObservable" + "Completed Observable.");
          }

          @Override

          public void onError(Throwable throwable) {

            System.err.println("loopObservable Whoops: " + throwable.getMessage());
          }

          @Override

          public void onNext(Integer integer) {

            System.out.println("loopObservable: " + integer);
          }
        });
  }

  private void filterObsrvable() {

    Observable
        .just(1, 2, 3, 4)
        .filter(new Func1<Integer, Boolean>() {
          @Override
          public Boolean call(Integer integer) {
            return integer % 2 == 0;
          }
        }).subscribe(new Observer<Integer>() {
      @Override public void onCompleted() {

      }

      @Override public void onError(Throwable e) {

      }

      @Override public void onNext(Integer integer) {
        System.out.println("filterObsrvable: " + integer);
      }
    });

    Observable
        .just("The", "Dave", "Brubeck", "Quartet", "Time", "Out")
        .take(5)
        .subscribe(new Subscriber<String>() {
          @Override
          public void onCompleted() {
            System.out.println("filterObsrvable Completed Observable.");
          }

          @Override
          public void onError(Throwable throwable) {
            System.err.println("filterObsrvable Whoops: " + throwable.getMessage());
          }

          @Override
          public void onNext(String name) {
            System.out.println("filterObsrvable: " + name);
          }
        });

    Observable
        .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .filter(new Func1<Integer, Boolean>() {
          @Override
          public Boolean call(Integer integer) {
            return integer % 2 == 0;
          }
        })
        .forEach(new Action1<Integer>() {
          @Override
          public void call(Integer item) {
            System.out.println("filterObsrvable: " + item);
          }
        });

    Observable.from(getColorModelList())
        .filter(new Func1<ColorModel, Boolean>() {
          @Override public Boolean call(ColorModel colorModel) {
            return Integer.valueOf(colorModel.getValue()) % 2 == 0;
          }
        })
        .subscribe(new Action1<ColorModel>() {
          @Override public void call(ColorModel colorModel) {
            System.out.println("filterObsrvable: " + colorModel.getColor());
          }
        });
  }

  private static List<String> getColorList() {
    ArrayList<String> colors = new ArrayList<>();
    colors.add("blue");
    colors.add("green");
    colors.add("red");
    colors.add("chartreuse");
    colors.add("Van Dyke Brown");
    return colors;
  }

  private List<ColorModel> getColorModelList() {
    ArrayList<ColorModel> colorModels = new ArrayList<>();
    colorModels.add(new ColorModel("Blue", "1"));
    colorModels.add(new ColorModel("green", "2"));
    colorModels.add(new ColorModel("red", "3"));
    colorModels.add(new ColorModel("chartreuse", "4"));
    colorModels.add(new ColorModel("Van Dyke Brown", "5"));

    return colorModels;
  }
}
