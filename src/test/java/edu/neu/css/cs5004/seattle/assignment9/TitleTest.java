package edu.neu.css.cs5004.seattle.assignment9;


import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TitleTest {
  private Title t1;

  @Before
  public void setUp() throws Exception {
    this.t1 = Title.createTitle("HTML.txt");
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testCreateTitle() {
    Assert.assertEquals(this.t1.getClass(), Title.class);
  }

  @Test
  public void testProcess() {
    this.t1.process("adfadfa");
    Assert.assertEquals(this.t1.getFileName(), "HTML.txt");
  }

  @Test
  public void testGetStyledContent() {
    HTMLFormatter h1 = HTMLFormatter.createHTMLFormatter();
    LinkedList<String> test = new LinkedList<>();
    test.add("<head>\n" + "  <title>");
    test.add("HTML.txt");
    test.add("</title>\n</head>\n<body>\n");
    LinkedList<String> result = this.t1.getFormattedContent(h1);
    Assert.assertEquals(test, result);
  }

  @Test
  public void testGetContent() {
    Assert.assertEquals(this.t1.getFileName(), "HTML.txt");
  }

  @Test
  public void testGetPattern() {
    Assert.assertEquals(this.t1.getPattern(), TextElement.PATTERN);
  }

}
