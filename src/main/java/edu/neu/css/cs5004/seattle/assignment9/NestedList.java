package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;
import java.util.regex.Pattern;

public abstract class NestedList implements TextElement {

  public static final String PATTERN = "(  )*(1\\.|  \\*) .*";
  protected static final int LEVELDOWN = 2;
  protected LinkedList<ListItem> listItems;

  /**
   * Return a new NestedList with and add the line to the structure
   *
   * @param line the line to add to the structure
   * @return a new NestedList with adding line in the sublist
   */
  public static NestedList createList(String line) {
    if (Pattern.matches(NumberList.PATTERN, line)) {
      NumberList numberList = NumberList.createNumberList();
      numberList.process(line);
      return numberList;
    } else {
      UnOrderedList unOrderedList = UnOrderedList.createUnOrderedList();
      unOrderedList.process(line);
      return unOrderedList;
    }
  }


  /**
   * Create a new NestedList
   *
   */
  protected NestedList() {
    super();
    this.listItems = new LinkedList<>();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void process(String line) {
    if (Pattern.matches(NumberList.TOPLEVEL, line)) {
      this.listItems.add(new ListItem(line.substring(NumberList.MARKERNUMBER)));
    } else if (Pattern.matches(UnOrderedList.TOPLEVEL, line)) {
      this.listItems.add(new ListItem(line.substring(UnOrderedList.MARKERNUMBER)));
    } else {
      getCurList().process(line.substring(NestedList.LEVELDOWN));
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public LinkedList<String> getFormattedContent(Formatter formatter) {
    // TODO Auto-generated method stub
    LinkedList<String> resultList = new LinkedList<>();
    for (ListItem item : this.listItems) {
      resultList.addAll(item.getFormattedContent(formatter));
    }
    return resultList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPattern() {
    // TODO Auto-generated method stub
    return NestedList.PATTERN;
  }

  /**
   * Return the listItems of this number list
   *
   * @return the listItems of this number list
   */
  public LinkedList<ListItem> getSubList() {
    return this.listItems;
  }


  /**
   * Set the listItems as the input listItems
   *
   * @param subList listItems the sublist to be
   */
  public void setSubList(LinkedList<ListItem> subList) {
    this.listItems = subList;
  }

  /**
   * Return the last list in the sublist of this nestedList
   *
   * @return the last list in the sublist of this nestedList
   */
  private ListItem getCurList() {
    if (this.listItems.isEmpty()) {
      this.listItems.add(new ListItem(""));
    }
    return this.listItems.getLast();
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.listItems == null) ? 0 : this.listItems.hashCode());
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    NestedList other = (NestedList) obj;
    if (this.listItems == null) {
      if (other.listItems != null) {
        return false;
      }
    } else if (!this.listItems.equals(other.listItems)) {
      return false;
    }
    return true;
  }



}
