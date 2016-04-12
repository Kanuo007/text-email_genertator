package edu.neu.css.cs5004.seattle.assignment9;


import java.util.LinkedList;

public interface TextElement {
  String PATTERN = null;

  /**
   * Given a string line, add it to the textElement
   *
   * @param line the line to add it to the textElement
   */
  void process(String line);

  /**
   * Return the content after apply the formatter to it
   *
   * @param formatter the formatter to apply to the testElement
   * @return the content list after apply the formatter to it
   */
  LinkedList<String> getFormattedContent(Formatter formatter);

  /**
   * Get the pattern of the textElement
   *
   * @return the pattern of the textElement
   */
  String getPattern();

}
