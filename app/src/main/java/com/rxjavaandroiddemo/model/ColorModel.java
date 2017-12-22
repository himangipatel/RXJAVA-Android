package com.rxjavaandroiddemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Himangi Patel on 20/12/17.
 */

public class ColorModel {

  @SerializedName("color")
  private String color;
  @SerializedName("value")
  private String value;

  public ColorModel(String color, String value) {
    this.color = color;
    this.value = value;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
