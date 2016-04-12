
package edu.neu.css.cs5004.seattle.assignment9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProcessorTest {
  BufferedReader result;
  BufferedReader comparsion;
  List<String> a1;
  List<String> a2;



  @Before
  public void setUp() {

  }

  @After
  public void tearDown() throws Exception {
    try {
      Files.delete((new File("test.html").toPath()));
    } catch (IOException e) {

    }
  }

  @Test
  public void testMain() {
    String[] argu = {"test.txt"};
    Processor.main(argu);
    this.a1 = new ArrayList<>();
    this.a2 = new ArrayList<>();
    try {
      this.result = new BufferedReader(new FileReader("test.html"));
      String line1;
      while ((line1 = this.result.readLine()) != null) {
        this.a1.add(line1);
      }
      this.comparsion = new BufferedReader(new FileReader("test2.html"));
      String line2;
      while ((line2 = this.comparsion.readLine()) != null) {
        this.a2.add(line2);
      }
    } catch (IOException e) {
    }
    Assert.assertEquals(this.a1, this.a2);
  }

  @Test(expected = RuntimeException.class)
  public void testException1() {
    Processor.main(null);
  }


  @Test(expected = RuntimeException.class)
  public void testException2() {
    String[] a = {};
    Processor.main(a);
  }

  @Test(expected = RuntimeException.class)
  public void testException3() {
    String[] a = {"asdfad"};
    Processor.main(a);
    Processor.main(null);
  }
}
