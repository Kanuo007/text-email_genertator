package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;

public class Title implements TextElement {
  private String fileName;


  /**
   * Create a new Title, with the filename is title
   *
   * @param title the filename
   */
  private Title(String title) {
    this.fileName = title;
  }

  /**
   *
   * @return the filename of title
   */
  public String getFileName() {
    return this.fileName;
  }

  /**
   * Create a new Title, with the filename is title
   *
   * @param title the title filename
   * @return a new Title, with the filename is title
   */
  public static Title createTitle(String title) {
    // TODO Auto-generated method stub
    return new Title(title);
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public void process(String line) {
    return;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LinkedList<String> getFormattedContent(Formatter formatter) {
    return formatter.format(this, this.fileName);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPattern() {
    return TextElement.PATTERN;
  }

}


