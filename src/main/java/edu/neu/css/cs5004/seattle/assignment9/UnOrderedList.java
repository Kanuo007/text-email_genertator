package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;

public class UnOrderedList extends NestedList {
  public static final String PATTERN = "(  )+\\* .*";
  protected static final String TOPLEVEL = "  \\* .*";
  protected static final int MARKERNUMBER = 4;

  /**
   * Create a new UnorderedList
   *
   * @return a new UnorderedList
   */
  public static UnOrderedList createUnOrderedList() {
    return new UnOrderedList();
  }

  /**
   * Create a new UnorderedList
   *
   * @return a new UnorderedList
   */
  protected UnOrderedList() {
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
