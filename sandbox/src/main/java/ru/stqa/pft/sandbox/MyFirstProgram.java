package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String args[]) {
    Point point1 = new Point(7, 10);
    Point point2 = new Point(3, 5);

    System.out.println(distance(point1, point2));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p1.b - p1.a, 2) + Math.pow(p2.b - p2.a, 2));
  }
}

