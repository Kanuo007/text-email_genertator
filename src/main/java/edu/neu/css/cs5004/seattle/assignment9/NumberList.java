package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;

public class NumberList extends NestedList {

  static final String PATTERN = "(  )*1\\. .*";
  protected static final String TOPLEVEL = "1\\. .*";
  protected static final int MARKERNUMBER = 3;


  /**
   * Create a new NumberList
   *
   * @return a new NumberList
   */
  public static NumberList createNumberList() {
    return new NumberList();
  }

  /**
   * Create a new NumberList
   *
   */
  private NumberList() {
    super();
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public LinkedList<String> getFormattedContent(Formatter formatter) {
    LinkedList<String> resultList = super.getFormattedContent(formatter);
    formatter.format(this, resultList);
    return resultList;
  }


}
