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
public class BlockQuoteTest {
  private BlockQuote b1;
  private BlockQuote b2;
  private BlockQuote b3;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.b1 = BlockQuote.createBolckQuote();
    this.b2 = BlockQuote.createBolckQuote();
    this.b3 = BlockQuote.createBolckQuote();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {}

  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.BlockQuote#createBolckQuote()}.
   */
  @Test
  public void testCreateBolckQuote() {
    Assert.assertEquals(this.b1, BlockQuote.createBolckQuote());
  }

  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.BlockQuote#process(java.lang.String)}.
   */
  @Test
  public void testProcess() {
    this.b1.process("> What are the resources allocated?");
    this.b1.process("> > Are you asking in terms of headcount or machines?");
    this.b1.process("> > > Headcount");
    LinkedList<String> test = new LinkedList<>();
    test.add("<blockquote>\n");
    test.add("<p>");
    test.add("What are the resources allocated?");
    test.add("</p>\n");
    test.add("<blockquote>\n");
    test.add("<p>");
    test.add("Are you asking in terms of headcount or machines?");
    test.add("</p>\n");
    test.add("</blockquote>\n");
    test.add("<blockquote>\n");
    test.add("<blockquote>\n");
    test.add("<p>");
    test.add("Headcount");
    test.add("</p>\n");
    test.add("</blockquote>\n");
    test.add("</blockquote>\n");
    test.add("</blockquote>\n");
    Assert.assertEquals(this.b1.getFormattedContent(HTMLFormatter.createHTMLFormatter()), test);
  }

  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.BlockQuote#getFormattedContent(edu.neu.css.cs5004.seattle.assignment9.Formatter)}
   * .
   */
  @Test
  public void testGetFormattedContent() {
    this.b1.process("> What are the resources allocated?");
    this.b1.process("> > Are you asking in terms of headcount or machines?");
    this.b1.process("> > > Headcount");
    LinkedList<String> test = new LinkedList<>();
    test.add("<blockquote>\n");
    test.add("<p>");
    test.add("What are the resources allocated?");
    test.add("</p>\n");
    test.add("<blockquote>\n");
    test.add("<p>");
    test.add("Are you asking in terms of headcount or machines?");
    test.add("</p>\n");
    test.add("</blockquote>\n");
    test.add("<blockquote>\n");
    test.add("<blockquote>\n");
    test.add("<p>");
    test.add("Headcount");
    test.add("</p>\n");
    test.add("</blockquote>\n");
    test.add("</blockquote>\n");
    test.add("</blockquote>\n");
    Assert.assertEquals(this.b1.getFormattedContent(HTMLFormatter.createHTMLFormatter()), test);
  }

  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.BlockQuote#getPattern()}.
   */
  @Test
  public void testGetPattern() {
    Assert.assertEquals(this.b1.getPattern(), BlockQuote.PATTERN);
  }

  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.BlockQuote#hashCode()}.
   */
  @Test
  public void testHashCode() {
    this.b2.process("> 1. 33");
    this.b3.process("> 1. 33");
    Assert.assertNotEquals(this.b1.hashCode(), this.b2.hashCode());
  }

  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.BlockQuote#Equals()}.
   */
  @Test
  public void testEqualsObject() {
    this.b2.process("> dsf");
    Assert.assertTrue(this.b1.equals(this.b1));
    Assert.assertFalse(this.b1.equals(this.b2));
    Assert.assertFalse(this.b1.equals(new Integer(20)));


    Assert.assertTrue(this.b1.equals(this.b3) && this.b3.equals(this.b1));


    Assert.assertFalse(this.b1.equals(null));
  }

}
