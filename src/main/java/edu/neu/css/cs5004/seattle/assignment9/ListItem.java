/**
 *
 */
package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * @author NSR
 *
 */
public class ListItem implements TextElement {
  private NestedList subList;
  private String text;

  /**
   * Create a new listItem given a text
   *
   * @param text the text of the listItem
   */
  public ListItem(String text) {
    this.text = text;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void process(String line) {
    if (null != this.subList) {
      this.subList.process(line);
    } else if (Pattern.matches(NumberList.PATTERN, line)) {
      this.subList = NumberList.createNumberList();
      this.subList.process(line);
    } else {
      this.subList = UnOrderedList.createUnOrderedList();
      this.subList.process(line);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LinkedList<String> getFormattedContent(Formatter formatter) {
    LinkedList<String> resultList = new LinkedList<>();
    if (null != this.subList) {
      resultList.addAll(this.subList.getFormattedContent(formatter));
    }
    if (!this.text.equals("")) {
      resultList.addFirst(this.text);
      formatter.format(this, resultList);
    }
    return resultList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPattern() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Return the subList of this listItem
   *
   * @return the subList of this listItem
   */
  public NestedList getSubList() {
    return this.subList;
  }

  /**
   * Set the subList of this listItem to the input subList
   *
   * @param subList set the subList of this listItem to this subList
   */
  public void setSubList(NestedList subList) {
    this.subList = subList;
  }

  /**
   * 
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.subList == null) ? 0 : this.subList.hashCode());
    result = (prime * result) + ((this.text == null) ? 0 : this.text.hashCode());
    return result;
  }

  /**
   * 
   * {@inheritDoc}
   */
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
    ListItem other = (ListItem) obj;
    if (this.subList == null) {
      if (other.subList != null) {
        return false;
      }
    } else if (!this.subList.equals(other.subList)) {
      return false;
    }
    if (this.text == null) {
      if (other.text != null) {
        return false;
      }
    } else if (!this.text.equals(other.text)) {
      return false;
    }
    return true;
  }



}
