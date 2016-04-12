
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
public class HeaderTest {

  private Header h1;
  private Header h2;
  private Header h6;
  private Header h7;
  private Formatter f1;


  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.f1 = HTMLFormatter.createHTMLFormatter();
    this.h1 = Header.createHeader("# the first level header");
    this.h2 = Header.createHeader("## the second level header");
    this.h6 = Header.createHeader("###### the sixth level header");
    this.h7 = Header.createHeader("####### the seventh level header");
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {}

  /**
   * NOTE: Because we use a static field to track all the header and every time we call
   * Header.createHeader(), one new header will be inserted in to the header structure, so it is
   * impossible to test all the methods in different test methods since all the test methods will
   * trigger setUp() once, so we decide to test all the methods in one test method.
   */


  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.Header#hashCode()}.
   */
  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.Header#createHeader(java.lang.String)} .
   */

  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.Header#getHeaderPosition(edu.neu.css.cs5004.seattle.assignment9.Header)}
   * .
   */
  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.Header#getContent()}.
   */
  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.Header#getFormattedContent(edu.neu.css.cs5004.seattle.assignment9.Formatter)}
   * .
   */

  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.Header#getPattern()}.
   */
  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.Header#equals(java.lang.Object)}.
   */

  @Test
  public void test() {

    // testCreateHeader testGetHeaderPosition

    Assert.assertEquals(Header.getHeaderPosition(this.h1).size(), 1);
    Assert.assertEquals(Header.getHeaderPosition(this.h2).size(), 2);
    Assert.assertEquals(Header.getHeaderPosition(this.h6).size(), 6);
    Assert.assertEquals(Header.getHeaderPosition(this.h7).size(), 7);

    // testGetContent
    Assert.assertEquals(this.h1.getText(), "the first level header");
    Assert.assertEquals(this.h2.getText(), "the second level header");
    // testGetFormattedContent
    LinkedList<String> test1 = new LinkedList<>();
    test1.add("<h1>");
    test1.add("the first level header");
    test1.add("</h1>");
    LinkedList<String> test2 = new LinkedList<>();
    test2.add("<h2>");
    test2.add("the second level header");
    test2.add("</h2>");
    LinkedList<String> test6 = new LinkedList<>();
    test6.add("<h6>");
    test6.add("the sixth level header");
    test6.add("</h6>");
    LinkedList<String> test7 = new LinkedList<>();
    test7.add("<h6>");
    test7.add("the seventh level header");
    test7.add("</h6>");
    Assert.assertEquals(this.h1.getFormattedContent(this.f1), test1);
    Assert.assertEquals(this.h2.getFormattedContent(this.f1), test2);
    Assert.assertEquals(this.h6.getFormattedContent(this.f1), test6);
    Assert.assertEquals(this.h7.getFormattedContent(this.f1), test7);
    // testGetPattern
    Assert.assertEquals(this.h1.getPattern(), Header.PATTERN);
    // testHashCode
    Assert.assertEquals(this.h1.hashCode(), this.h1.hashCode());
    Assert.assertNotEquals(this.h1.hashCode(), this.h2.hashCode());
    // testEqualsObject
    Header h3 = this.h1;
    Assert.assertTrue(this.h1.equals(this.h1));
    Assert.assertFalse(this.h1.equals(this.h2));
    Assert.assertFalse(this.h1.equals(new Integer(20)));


    Assert.assertTrue(this.h1.equals(h3) && h3.equals(this.h1));


    Assert.assertFalse(this.h1.equals(null));
  }
}

