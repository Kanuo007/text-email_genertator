package edu.neu.css.cs5004.seattle.assignment9.BlackBox;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.neu.css.cs5004.seattle.assignment9.EmptyLine;
import edu.neu.css.cs5004.seattle.assignment9.HTMLFormatter;

public class EmptyLineTest {
  private EmptyLine e1;
  public static final String PATTERN = "";

  @Before
  public void setUp() throws Exception {
    this.e1 = EmptyLine.createEmptyLine("");
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testGetStyledContent() {
    HTMLFormatter h1 = HTMLFormatter.createHTMLFormatter();
    LinkedList<String> result = new LinkedList<>();
    result.addFirst("<br/>");
    Assert.assertEquals(this.e1.getFormattedContent(h1), result);
  }

  @Test
  public void testCreateEmptyLine() {
    Assert.assertEquals(this.e1.getClass(), EmptyLine.class);
  }

  @Test
  public void testProcess() {
    this.e1.process("");
    Assert.assertEquals(this.e1.getText(), "\n");
  }

  @Test
  public void testGetContent() {
    Assert.assertEquals(this.e1.getClass(), EmptyLine.class);
  }

  @Test
  public void testGetPattern() {
    Assert.assertEquals(this.e1.getPattern(), EmptyLine.PATTERN);
  }

}
