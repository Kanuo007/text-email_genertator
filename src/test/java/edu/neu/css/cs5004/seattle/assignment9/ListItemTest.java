/**
 *
 */
package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author NSR
 *
 */
public class ListItemTest {

  private ListItem l1;
  private ListItem l2;
  private ListItem l3;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.l1 = new ListItem("example");
    this.l2 = new ListItem("example");
    this.l3 = new ListItem("example");
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {}

  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.ListItem#process(java.lang.String)}.
   */
  @Test
  public void testProcess() {
    this.l1.process("1. numberlist 1");
    this.l1.process("1. numberlist 2");
    this.l1.process("    * unorderedlist 1");
    this.l1.process("1. numberlist3");
    Assert.assertEquals(this.l1.getSubList().getSubList().size(), 3);
    Assert.assertEquals(this.l1.getSubList().getClass(), NumberList.class);
    Assert.assertEquals(this.l1.getSubList().getSubList().get(1).getSubList().getClass(),
        UnOrderedList.class);
    Assert.assertEquals(this.l1.getSubList().getSubList().get(1).getSubList().getSubList().size(),
        1);
  }

  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.ListItem#getFormattedContent(edu.neu.css.cs5004.seattle.assignment9.Formatter)}
   * .
   */
  @Test
  public void testGetFormattedContent() {
    this.l1.process("1. numberlist 1");
    this.l1.process("    * unorderedlist 1");
    LinkedList<String> test = new LinkedList<>();
    test.add("<li>");
    test.add("example");
    test.add("<ol>\n");
    test.add("<li>");
    test.add("numberlist 1");
    test.add("<ul>\n");
    test.add("<li>");
    test.add("unorderedlist 1");
    test.add("</li>\n");
    test.add("</ul>\n");
    test.add("</li>\n");
    test.add("</ol>\n");
    test.add("</li>\n");
    LinkedList<String> result = this.l1.getFormattedContent(HTMLFormatter.createHTMLFormatter());
    Assert.assertEquals(result, test);
  }

  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.ListItem#getPattern()}.
   */
  @Test
  public void testGetPattern() {
    Assert.assertEquals(this.l1.getPattern(), null);
  }

  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.ListItem#hashCode()}.
   */
  @Test
  public void testHashCode() {
    this.l1.process("1. 33");
    Assert.assertEquals(this.l2.hashCode(), this.l3.hashCode());
    Assert.assertNotEquals(this.l1.hashCode(), this.l2.hashCode());
  }

  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.ListItem#Equals()}.
   */
  @Test
  public void testEqualsObject() {
    this.l2.process("1. dsf");
    Assert.assertTrue(this.l1.equals(this.l1));
    Assert.assertFalse(this.l1.equals(this.l2));
    Assert.assertFalse(this.l1.equals(new Integer(20)));


    Assert.assertTrue(this.l1.equals(this.l3) && this.l3.equals(this.l1));


    Assert.assertFalse(this.l1.equals(null));
  }


}
