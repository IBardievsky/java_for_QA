package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String args[]) {
    Point firtsPoint = new Point(7, 10);
    Point secondPoint = new Point(3, 5);

    System.out.println("Координаты точки а = " + firtsPoint.a + "," + firtsPoint.b + " координаты точки b = " + secondPoint.a + "," + secondPoint.b);
    System.out.println("Расстояние между точками a и b = " + firtsPoint.distance(secondPoint));  }


}

