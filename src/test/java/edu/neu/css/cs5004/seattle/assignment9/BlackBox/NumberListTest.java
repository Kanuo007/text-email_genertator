/**
 *
 */
package edu.neu.css.cs5004.seattle.assignment9.BlackBox;


import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.neu.css.cs5004.seattle.assignment9.Formatter;
import edu.neu.css.cs5004.seattle.assignment9.HTMLFormatter;
import edu.neu.css.cs5004.seattle.assignment9.ListItem;
import edu.neu.css.cs5004.seattle.assignment9.NestedList;
import edu.neu.css.cs5004.seattle.assignment9.NumberList;

/**
 * @author NSR
 *
 */
public class NumberListTest {
  private NumberList n1;
  private NumberList n2;
  private NumberList n3;

  private Formatter f1;


  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.f1 = HTMLFormatter.createHTMLFormatter();
    this.n1 = NumberList.createNumberList();
    this.n2 = NumberList.createNumberList();
    this.n3 = NumberList.createNumberList();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {}

  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.NumberList#getFormattedContent(edu.neu.css.cs5004.seattle.assignment9.Formatter)}
   * .
   */
  @Test
  public void testGetFormattedContent() {
    this.n1.process("1. This is the first first level content");
    this.n1.process("1. This is the second first level content");
    this.n1.process("  1. This is the first second level content");
    this.n1.process("    * This is the second second level content");
    this.n1.process("      * This is the first third level content");
    this.n1.process("        1. This is the first fifth level content");
    this.n1.process("1. This is the third first level content");
    LinkedList<String> espectedResult = new LinkedList<>();
    espectedResult.add("<ol>\n");
    espectedResult.add("<li>");
    espectedResult.add("This is the first first level content");
    espectedResult.add("</li>\n");
    espectedResult.add("<li>");
    espectedResult.add("This is the second first level content");
    espectedResult.add("<ol>\n");
    espectedResult.add("<li>");
    espectedResult.add("This is the first second level content");
    espectedResult.add("</li>\n");
    espectedResult.add("<li>");
    espectedResult.add("This is the second second level content");
    espectedResult.add("<ul>\n");
    espectedResult.add("<li>");
    espectedResult.add("This is the first third level content");
    espectedResult.add("<ol>\n");
    espectedResult.add("<ol>\n");
    espectedResult.add("<li>");
    espectedResult.add("This is the first fifth level content");
    espectedResult.add("</li>\n");
    espectedResult.add("</ol>\n");
    espectedResult.add("</ol>\n");
    espectedResult.add("</li>\n");
    espectedResult.add("</ul>\n");
    espectedResult.add("</li>\n");
    espectedResult.add("</ol>\n");
    espectedResult.add("</li>\n");
    espectedResult.add("<li>");
    espectedResult.add("This is the third first level content");
    espectedResult.add("</li>\n");
    espectedResult.add("</ol>\n");
    Assert.assertEquals(this.n1.getFormattedContent(this.f1), espectedResult);
  }

  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.NumberList#createNumberList()}.
   */
  @Test
  public void testCreateNumberList() {
    Assert.assertEquals(NumberList.createNumberList(), this.n1);
  }

  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.NestedList#hashCode()}.
   */
  @Test
  public void testHashCode() {
    this.n2.process("1. ddd");
    Assert.assertEquals(this.n1.hashCode(), this.n1.hashCode());
    Assert.assertNotEquals(this.n1.hashCode(), this.n2.hashCode());
  }

  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.NestedList#createList(java.lang.String)}.
   */
  @Test
  public void testCreateList() {
    this.n1.process("1. level one list");
    Assert.assertEquals(NestedList.createList("1. level one list"), this.n1);
  }

  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.NestedList#process(java.lang.String)}.
   */
  @Test
  public void testProcess() {
    this.n1.process("1. level one list");
    Assert.assertTrue(this.n1.getSubList().contains(new ListItem("level one list")));
    this.n1.process("  1. level one list");
    this.n1.getFormattedContent(this.f1);
    LinkedList<String> espectedResult = new LinkedList<>();
    espectedResult.add("<ol>\n");
    espectedResult.add("<li>");
    espectedResult.add("This is the first first level content");
    espectedResult.add("</li>\n");
    espectedResult.add("<li>");
    espectedResult.add("This is the second first level content");
    espectedResult.add("<ol>\n");
  }

  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.NestedList#getPattern()}.
   */
  @Test
  public void testGetPattern() {
    Assert.assertEquals(this.n1.getPattern(), NestedList.PATTERN);
  }

  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.NestedList#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObject() {
    this.n2.process("1. dsf");
    Assert.assertTrue(this.n1.equals(this.n1));
    Assert.assertFalse(this.n1.equals(this.n2));
    Assert.assertFalse(this.n1.equals(new Integer(20)));


    Assert.assertTrue(this.n1.equals(this.n3) && this.n3.equals(this.n1));


    Assert.assertFalse(this.n1.equals(null));
  }

}
