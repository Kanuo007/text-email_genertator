package edu.neu.css.cs5004.seattle.assignment9.BlackBox;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.neu.css.cs5004.seattle.assignment9.Content;
import edu.neu.css.cs5004.seattle.assignment9.Formatter;
import edu.neu.css.cs5004.seattle.assignment9.HTMLFormatter;
import edu.neu.css.cs5004.seattle.assignment9.Title;

public class ContentTest {
  private Content c1;
  private Title t1;
  private static final String ContentBegin = "<!DOCTYPE html>\n<html>\n";
  private static final String ContentEnd = "</body>\n</html>\n";
  private static final String ContentPattern = ",*";


  @Before
  public void setUp() throws Exception {
    this.c1 = Content.createContent("AAA");
    this.t1 = Title.createTitle("AAA");

  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testGetFormattedContent() {
    Formatter h1 = HTMLFormatter.createHTMLFormatter();

    LinkedList<String> test = new LinkedList<>();
    test.add(ContentTest.ContentBegin);
    test.addAll(this.t1.getFormattedContent(h1));
    test.add(ContentTest.ContentEnd);
    Assert.assertEquals(this.c1.getFormattedContent(h1), test);
  }

  @Test
  public void testProcess() {}

  @Test
  public void testCreateContent() {
    Assert.assertEquals(this.c1.getClass(), Content.class);
  }

  @Test
  public void testGetPattern() {
    Assert.assertEquals(this.c1.getPattern(), ContentTest.ContentPattern);
  }

}
