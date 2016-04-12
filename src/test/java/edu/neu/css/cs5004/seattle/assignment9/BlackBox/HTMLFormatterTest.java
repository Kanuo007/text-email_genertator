package edu.neu.css.cs5004.seattle.assignment9.BlackBox;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.neu.css.cs5004.seattle.assignment9.BlockQuote;
import edu.neu.css.cs5004.seattle.assignment9.Content;
import edu.neu.css.cs5004.seattle.assignment9.EmptyLine;
import edu.neu.css.cs5004.seattle.assignment9.HTMLFormatter;
import edu.neu.css.cs5004.seattle.assignment9.Header;
import edu.neu.css.cs5004.seattle.assignment9.HorizontalLine;
import edu.neu.css.cs5004.seattle.assignment9.NumberList;
import edu.neu.css.cs5004.seattle.assignment9.Paragraph;
import edu.neu.css.cs5004.seattle.assignment9.Title;
import edu.neu.css.cs5004.seattle.assignment9.UnOrderedList;

public class HTMLFormatterTest {
  private HTMLFormatter h1;
  private Content a1;
  private EmptyLine a2;
  private Header a3;
  private NumberList a4;
  private UnOrderedList a5;
  private HorizontalLine a6;
  private BlockQuote a7;
  private Paragraph a8;
  private Title a9;


  @Before
  public void setUp() throws Exception {
    this.h1 = HTMLFormatter.createHTMLFormatter();
    this.a1 = Content.createContent("AAA");
    this.a2 = EmptyLine.createEmptyLine("");
    this.a3 = Header.createHeader("## *BBB*");
    this.a4 = NumberList.createNumberList();
    this.a5 = UnOrderedList.createUnOrderedList();
    this.a6 = HorizontalLine.createHorizontalLine();
    this.a7 = BlockQuote.createBolckQuote();
    this.a8 = Paragraph.createParagraph("");
    this.a9 = Title.createTitle("EEE");
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testFormatContent() {
    this.a1.process("KKK");
    LinkedList<String> result = this.a1.getFormattedContent(this.h1);
    this.h1.format(this.a1, new LinkedList<>());
    LinkedList<String> test = new LinkedList<>();
    test.add("<!DOCTYPE html>\n<html>\n");
    test.add("<head>\n  <title>");
    test.add("AAA");
    test.add("</title>\n</head>\n<body>\n");
    test.add("<p>");
    test.add("KKK");
    test.add("</p>\n");
    test.add("</body>\n</html>\n");
    Assert.assertEquals(result, test);
  }


  @Test
  public void testFormatParagraph() {
    LinkedList<String> result = this.h1.format(this.a8,
        "You _*can*_ see an example *[Confused](https://media.giphy.com)*,_[Confused](https://media.giphy.com)_AAAAA");
    LinkedList<String> test = new LinkedList<>();
    test.add("<p>");
    test.add(
        "You <em><strong>can</strong></em> see an example <strong><a href=\"https://media.giphy.com\">Confused</a></strong>,<em><a href=\"https://media.giphy.com\">Confused</a></em>AAAAA");
    test.add("</p>\n");
    Assert.assertEquals(result, test);
  }

  @Test
  public void testFormatNumberList() {
    this.a4.process("1. This is the first first level content");
    this.a4.process("1. This is the second first level content");
    this.a4.process("  1. This is the first second level content");
    this.a4.process("    * This is the second *second* level content");
    this.a4.process("      * This is the first third level content");
    this.a4.process("        1. This is the _first_ fifth level content");
    this.a4.process("1. This is the third first level content");

    LinkedList<String> Result = new LinkedList<>();
    Result.add("<li>");
    Result.add("This is the first first level content");
    Result.add("</li>\n");
    Result.add("<li>");
    Result.add("This is the second first level content");
    Result.add("<ol>\n");
    Result.add("<li>");
    Result.add("This is the first second level content");
    Result.add("</li>\n");
    Result.add("<li>");
    Result.add("This is the second second level content");
    Result.add("<ul>\n");
    Result.add("<li>");
    Result.add("This is the first third level content");
    Result.add("<ol>\n");
    Result.add("<ol>\n");
    Result.add("<li>");
    Result.add("This is the first fifth level content");
    Result.add("</li>\n");
    Result.add("</ol>\n");
    Result.add("</ol>\n");
    Result.add("</li>\n");
    Result.add("</ul>\n");
    Result.add("</li>\n");
    Result.add("</ol>\n");
    Result.add("</li>\n");
    Result.add("<li>");
    Result.add("This is the third first level content");
    Result.add("</li>\n");

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
    this.h1.format(this.a4, Result);
    // for (String line : espectedResult) {
    // System.out.println(line);
    // }
    // System.out.println("\n\n\n\n");
    // for (String line : Result) {
    // System.out.println(line);
    // }
    // Assert.assertEquals(espectedResult, Result);
  }


  @Test
  public void testFormatUnOrderedList() {

    this.a5.process("  * aaa");
    this.a5.process("  * bbb");
    this.a5.process("    * ccc");
    this.a5.process("    * ddd");
    LinkedList<String> Pretest = new LinkedList<>();
    Pretest.add("<li>");
    Pretest.add("aaa");
    Pretest.add("</li>\n");
    Pretest.add("<li>");
    Pretest.add("bbb");
    Pretest.add("<ul>\n");
    Pretest.add("<li>");
    Pretest.add("ccc");
    Pretest.add("</li>\n");
    Pretest.add("<li>");
    Pretest.add("ddd");
    Pretest.add("</li>\n");
    Pretest.add("</ul>\n");
    Pretest.add("</li>\n");

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
    test.add("</ul>\n");;

    this.h1.format(this.a5, Pretest);
    Assert.assertEquals(test, Pretest);
  }


  @Test
  public void testFormatHeader() {
    LinkedList<String> result = this.h1.format(this.a3, "*BBB*");
    LinkedList<String> test = new LinkedList<>();
    test.add("<h2>");
    test.add("*BBB*");
    test.add("</h2>");
    Assert.assertEquals(result, test);
  }

  @Test
  public void testFormatTitle() {
    LinkedList<String> result = this.h1.format(this.a9, "FileNaem");
    LinkedList<String> test = new LinkedList<>();
    test.add("<head>\n" + "  <title>");
    test.add("FileNaem");
    test.add("</title>\n</head>\n<body>\n");
    Assert.assertEquals(result, test);
  }

  @Test
  public void testFormatEmptyLine() {

    LinkedList<String> FormattedemptyLine = new LinkedList<>();
    FormattedemptyLine.add("<br/>");
    Assert.assertEquals(this.h1.format(this.a2), FormattedemptyLine);

  }

  @Test
  public void testHorizontalLine() {
    LinkedList<String> HLine = new LinkedList<>();
    HLine.add("<hr>\n");
    Assert.assertEquals(this.h1.format(this.a6), HLine);
  }

  @Test
  public void testFormatBlockQuote() {
    LinkedList<String> Pretest = new LinkedList<>();
    Pretest.add("<p>");
    Pretest.add("What are the resources allocated?");
    Pretest.add("</p>\n");
    Pretest.add("<blockquote>\n");
    Pretest.add("<p>");
    Pretest.add("Are you asking in terms of headcount or machines?");
    Pretest.add("</p>\n");
    Pretest.add("<blockquote>\n");
    Pretest.add("<p>");
    Pretest.add("Headcount");
    Pretest.add("</p>\n");
    Pretest.add("</blockquote>\n");
    Pretest.add("</blockquote>\n");

    LinkedList<String> test = new LinkedList<>();
    test.add("<blockquote>\n");
    test.add("<p>");
    test.add("What are the resources allocated?");
    test.add("</p>\n");
    test.add("<blockquote>\n");
    test.add("<p>");
    test.add("Are you asking in terms of headcount or machines?");
    test.add("</p>\n");
    test.add("<blockquote>\n");
    test.add("<p>");
    test.add("Headcount");
    test.add("</p>\n");
    test.add("</blockquote>\n");
    test.add("</blockquote>\n");
    test.add("</blockquote>\n");


    this.h1.format(this.a7, Pretest);
    Assert.assertEquals(Pretest, test);;
  }

  @Test
  public void testGetOutputName() {
    Assert.assertEquals(this.h1.getOutputName("SS"), "SS");
  }

}
