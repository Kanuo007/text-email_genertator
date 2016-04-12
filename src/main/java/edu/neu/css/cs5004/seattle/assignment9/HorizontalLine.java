package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;

public class HorizontalLine implements TextElement {
  public static final String PATTERN = "\\*\\*\\*\\*+";

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public void process(String line) {}

  /**
   * Create a new HorizontalLine
   *
   * @return a new HorizontalLine
   */
  public static HorizontalLine createHorizontalLine() {
    return new HorizontalLine();
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public LinkedList<String> getFormattedContent(Formatter formatter) {
    return formatter.format(this);
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public String getPattern() {
    return HorizontalLine.PATTERN;
  }
}
