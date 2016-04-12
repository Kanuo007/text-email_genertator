package edu.neu.css.cs5004.seattle.assignment9;


import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class UnOrderedListTest {
  private UnOrderedList u1;
  public static final String PATTERN = "(  )*(1\\.|  \\*) .*";

  @Before
  public void setUp() throws Exception {
    this.u1 = UnOrderedList.createUnOrderedList();

  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testGetFormattedContent() {
    HTMLFormatter h = HTMLFormatter.createHTMLFormatter();
    this.u1.process("  * aaa");
    this.u1.process("  * bbb");
    this.u1.process("    * ccc");
    this.u1.process("    * ddd");
    LinkedList<String> test = new LinkedList<>();
    test.add("<ul>\n");
    test.add("<li>");
    test.add("aaa");
    test.add("</li>\n");
    test.add("<li>");
    test.add("bbb");
    test.add("<ul>\n");
    test.add("<li>");
    test.add("ccc");
    test.add("</li>\n");
    test.add("<li>");
    test.add("ddd");
    test.add("</li>\n");
    test.add("</ul>\n");
    test.add("</li>\n");
    test.add("</ul>\n");
    LinkedList<String> result = this.u1.getFormattedContent(h);
    Assert.assertEquals(result, test);
  }

  @Test
  public void testCreateUnOrderedList() {
    Assert.assertEquals(this.u1.getClass(), UnOrderedList.class);
  }


  @Test
  public void testGetPattern() {
    Assert.assertEquals(this.u1.getPattern(), UnOrderedListTest.PATTERN);
  }

}
