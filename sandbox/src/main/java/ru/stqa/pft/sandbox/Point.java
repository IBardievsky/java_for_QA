package ru.stqa.pft.sandbox;

/**
 * Created by User on 9/12/2016.
 */
public class Point {
  double a;
  double b;

  public Point(double p1, double p2) {
    this.a = p1;
    this.b = p2;
  }

//  public double distance() {
//    return Math.pow(this.b - this.a, 2);
//  }

  public  double distance(Point p2) {
    return Math.sqrt(Math.pow(this.a - p2.a, 2) + Math.pow(this.b - p2.b, 2));
  }

}
