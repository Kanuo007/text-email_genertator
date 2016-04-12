/**
 *
 */
package edu.neu.css.cs5004.seattle.assignment9.BlackBox;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.neu.css.cs5004.seattle.assignment9.HTMLFormatter;
import edu.neu.css.cs5004.seattle.assignment9.HorizontalLine;

/**
 * @author NSR
 *
 */
public class HorizontalLineTest {
  private HorizontalLine h1;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    this.h1 = HorizontalLine.createHorizontalLine();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {}

  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.HorizontalLine#process(java.lang.String)}.
   */
  @Test
  public void testProcess() {
    this.h1.process("******");
    LinkedList<String> test = new LinkedList<>();
    test.add("<hr>\n");
    Assert.assertEquals(this.h1.getFormattedContent(new HTMLFormatter()), test);

  }

  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.HorizontalLine#createHorizontalLine()}.
   */
  @Test
  public void testCreateHorizontalLine() {
    LinkedList<String> test = new LinkedList<>();
    test.add("<hr>\n");
    Assert.assertEquals(this.h1.getFormattedContent(new HTMLFormatter()), test);

  }

  /**
   * Test method for
   * {@link edu.neu.css.cs5004.seattle.assignment9.HorizontalLine#getFormattedContent(edu.neu.css.cs5004.seattle.assignment9.Formatter)}
   * .
   */
  @Test
  public void testGetFormattedContent() {
    LinkedList<String> test = new LinkedList<>();
    test.add("<hr>\n");
    Assert.assertEquals(this.h1.getFormattedContent(new HTMLFormatter()), test);
  }

  /**
   * Test method for {@link edu.neu.css.cs5004.seattle.assignment9.HorizontalLine#getPattern()}.
   */
  @Test
  public void testGetPattern() {
    Assert.assertEquals(this.h1.getPattern(), HorizontalLine.PATTERN);
  }

}
