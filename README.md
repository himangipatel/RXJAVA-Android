# RXJAVA-Android

## Operators

**1. [just()](http://reactivex.io/documentation/operators/just.html)** <br>
  ## Uses <br>
 * The Just operator converts an item into an Observable that emits that item.
 * Observable.just(someList) will give you 1 emission - a List.

   **Example**
```java
  Observable.just(getColorList())
        .subscribe(new Action1<List<String>>() {
          @Override public void call(List<String> strings) { 
             
          } 
        });
```
 **2. [from( )](http://reactivex.io/documentation/operators/from.html)** <br>
   ## Uses <br>
 * Observable.from(someList) will give you N emissions - each item in the list.
 

   **Example**<br>
   
```java
  Observable.from(getColorList())
        .subscribe(new Action1<String>() {
          @Override public void call(String s) {
         /* N emissions is performed using from operator. If there is 4 items in getColorList() 
         then output will be print 4times */
            System.out.println("differenceBetweenJustAndFrom: " + s);
          }
        });
```
  
   **2. [map( )](http://reactivex.io/documentation/operators/map.html)** <br>
   ## Uses <br>
   * takes in one value and outputs another value
 

   **Example**<br>
   - For example There is some JSON input and you want that json to form in POJO class.You can make it using map like below
   
```java
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
        Log.d("Map-operator", colorModel.getColor());
      }
    });
```
