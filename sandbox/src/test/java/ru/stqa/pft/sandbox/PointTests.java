package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by User on 9/19/2016.
 */
public class PointTests {

  @Test
  public void negativeNumbers(){
    Point firstPoint = new Point(-8,-10);
    Point secondPoint = new Point(-3,-5);
    Assert.assertEquals(firstPoint.distance(secondPoint),7.0710678118654752);
  }

  @Test
  public void zeroInPoint(){
    Point firstPoint = new Point(0,-10);
    Point secondPoint = new Point(-3,0);
    Assert.assertEquals(firstPoint.distance(secondPoint),10.44030650891055);
  }

  @Test
  public void testPoint(){
    Point firstPoint = new Point(7,10);
    Point secondPoint = new Point(3,5);
    Assert.assertEquals(firstPoint.distance(secondPoint),6.4031242374328485);
  }
}
