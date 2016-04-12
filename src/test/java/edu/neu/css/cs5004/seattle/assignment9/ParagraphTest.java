package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParagraphTest {
  private Paragraph p0;
  private Paragraph p1;


  @Before
  public void setUp() throws Exception {
    this.p0 = Paragraph.createParagraph("");
    this.p1 = Paragraph.createParagraph("AAA,BBB, THISDFD DFDF SFS\n");
    this.p1.process(" CCC, DDSG,ASDFA AFD!!//@//@\n");
    this.p1.process(", jhgjg\n\n");
    this.p1.process(" ASDF, DDSG,\\$8798ASDFA AFD!!\n");
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testCreateParagraph() {
    Paragraph test = Paragraph.createParagraph("a");
    Assert.assertEquals(test.getClass(), Paragraph.class);
    Assert.assertEquals(test.getText(), "a");
  }

  @Test
  public void testProcess() {
    Assert.assertEquals(this.p0.getText(), "");
    Assert.assertEquals(this.p1.getText(), "AAA,BBB, THISDFD DFDF SFS\n"
        + " CCC, DDSG,ASDFA AFD!!//@//@\n" + ", jhgjg\n\n" + " ASDF, DDSG,\\$8798ASDFA AFD!!\n");
  }

  @Test
  public void testGetStyledContent() {
    HTMLFormatter h1 = HTMLFormatter.createHTMLFormatter();
    LinkedList<String> test1 = new LinkedList<>();
    test1.add("<p>");
    test1.add("AAA,BBB, THISDFD DFDF SFS\n" + " CCC, DDSG,ASDFA AFD!!//@//@\n" + ", jhgjg\n\n"
        + " ASDF, DDSG,\\$8798ASDFA AFD!!\n");
    test1.add("</p>\n");
    LinkedList<String> test2 = new LinkedList<>();
    test2.add("<p>");
    test2.add("");
    test2.add("</p>\n");
    Assert.assertEquals(this.p1.getFormattedContent(h1), test1);
    Assert.assertEquals(this.p0.getFormattedContent(h1), test2);
  }

  @Test
  public void testGetPattern() {
    Assert.assertEquals(this.p1.getPattern(), Paragraph.PATTERN);
  }

}
