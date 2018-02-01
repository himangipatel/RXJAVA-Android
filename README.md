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
  
