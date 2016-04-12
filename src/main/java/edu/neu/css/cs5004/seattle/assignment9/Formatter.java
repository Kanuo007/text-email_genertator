package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;

public interface Formatter {

  /**
   * Given a title and its formatted children, return a string of title content after format it
   *
   * @param title the title to be formatted
   * @param formattedChildren the formatted children of the title
   * @return the list of the formatted title content
   */
  LinkedList<String> format(Title title, String formattedChildren);


  /**
   * Given a content and its formatted children list, add the format of content to the list
   *
   * @param content the content to be formatted
   * @param formattedChildren the formatted children of the content
   */
  void format(Content content, LinkedList<String> formattedChildren);


  /**
   * Given a paragraph, return a string of paragraph content after format it
   *
   * @param paragraph the paragraph to be formatted
   * @param text the text of the paragraph
   * @return the list of the formatted paragraph content
   */
  LinkedList<String> format(Paragraph paragraph, String text);


  /**
   * Given a numberList and its formatted children list, add the format of numberList to the list
   *
   * @param numberList the numberList to be formatted
   * @param formattedChildren the formatted children of the numberList
   */
  void format(NumberList numberList, LinkedList<String> formattedChildren);


  /**
   * Given a unorderedList and its formatted children list, add the format of unorderedList to the
   * list
   *
   * @param unorderedList the unorderedList to be formatted
   * @param formattedChildren the formatted children of the unorderedList
   */
  void format(UnOrderedList unorderedList, LinkedList<String> formattedChildren);

  /**
   * Given a header, return a string list of header content after format it
   *
   * @param header the header to be formatted
   * @param text of the header
   * @return the list of the formatted header content
   */
  LinkedList<String> format(Header header, String text);


  /**
   * Given a emptyLine, return a string list of emptyLine content after format it
   *
   * @param emptyLine the emptyLine to be formatted
   * @return the string list of the formatted emptyLine content
   */
  LinkedList<String> format(EmptyLine emptyLine);

  /**
   * Given a horizontalLine, return a string list of horizontalLine content after format it
   *
   * @param horizontalLine the horizontalLine to be formatted
   * @return the string list of the formatted horizontalLine content
   */
  LinkedList<String> format(HorizontalLine horizontalLine);

  /**
   * Given an input title name, return the output file name
   *
   * @param title the input title name
   * @return the name of the output file
   */
  String getOutputName(String title);

  /**
   * Given a listItem and its formatted children list, add the format of listItem to the list
   *
   * @param listItem the listItem to be formatted
   * @param formattedChildren the formatted children of the listItem
   */
  void format(ListItem listItem, LinkedList<String> formattedChildren);

  /**
   * Given a blockQuote and its formatted children list, add the format of blockQuote to the list
   *
   * @param blockQuote the blockQuote to be formatted
   * @param formattedChildren the formatted children of the blockQuote
   */
  void format(BlockQuote blockQuote, LinkedList<String> formattedChildren);


}
