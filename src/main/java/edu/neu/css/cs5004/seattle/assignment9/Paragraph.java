package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;

public class Paragraph implements TextElement {

  public static final String PATTERN = "(?![(\\*\\*\\*\\*)(\\>)(\\#+ )((  )*(1\\.|  \\*) )]).+";
  private String text;

  /**
   * Create a new paragraph
   */
  private Paragraph(String line) {
    this.text = line;
  }

  /**
   * Create a new paragraph
   *
   * @param line the line of the paragraph
   * @return a new paragraph
   */
  public static Paragraph createParagraph(String line) {
    return new Paragraph(line);
  }


  /**
   * Return the text of this paragraph
   *
   * @return the text of this paragraph
   */
  public String getText() {
    return this.text;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void process(String line) {
    this.text += line;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LinkedList<String> getFormattedContent(Formatter formatter) {
    LinkedList<String> formattedContent = formatter.format(this, this.text);
    return formattedContent;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPattern() {
    return Paragraph.PATTERN;
  }
}
