package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;

public class EmptyLine implements TextElement {
  public static final String PATTERN = "";
  public String text;

  /**
   * Create a new emptyLine given a line
   *
   * @param line the line of the emptyLine
   */
  private EmptyLine(String line) {
    this.text = line;
  }

  /**
   * Create a new emptyLine given a line
   * 
   * @param line the line of the emptyLine
   * @return the new emptyLine
   */
  public static EmptyLine createEmptyLine(String line) {
    return new EmptyLine(line);
  }


  /**
   * Return the text of this emptyLine
   * 
   * @return the text of this emptyLine
   */
  public String getText() {
    return this.text;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void process(String line) {
    this.text += "\n" + line;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public LinkedList<String> getFormattedContent(Formatter formatter) {
    return formatter.format(this);
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public String getPattern() {
    return EmptyLine.PATTERN;
  }


}
