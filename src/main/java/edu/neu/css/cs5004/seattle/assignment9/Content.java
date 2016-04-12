package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;
import java.util.regex.Pattern;

public class Content implements TextElement {
  private LinkedList<TextElement> elements;
  private TextElement curElement;
  private String curPattern;
  private static final String ContentPattern = ",*";

  /**
   * Create a new Content. The first element of the content list is a Title named title
   *
   * @param title the title name. We need to add the title into the content.
   */
  private Content(String title) {
    this.elements = new LinkedList<>();
    this.elements.add(Title.createTitle(title));
  }



  /**
   * Get the elements of the content
   *
   * @return the elements of the content
   */
  public LinkedList<TextElement> getElements() {
    return this.elements;
  }



  /**
   * Get the current element of the content
   *
   * @return the current element of the content
   */
  public TextElement getCurElement() {
    return this.curElement;
  }



  /**
   * Given a line, find corresponding type and process it, and it to this content.
   *
   * @param line the line we need to process.
   */
  private void chooseChildToProcess(String line) {
    if ((this.curElement != null) && Pattern.matches(this.curElement.getPattern(), line)) {

      if ((Pattern.matches(NumberList.PATTERN, this.curPattern)
          && Pattern.matches(UnOrderedList.TOPLEVEL, line))
          || (Pattern.matches(UnOrderedList.PATTERN, this.curPattern)
              && Pattern.matches(NumberList.TOPLEVEL, line))) {
        NestedList nestedList = NestedList.createList(line);
        this.elements.add(nestedList);
        this.curElement = nestedList;
        this.curPattern = line;
      } else {
        this.curElement.process(line);
      }
    } else {
      if (Pattern.matches(Header.PATTERN, line)) {
        Header newHeader = Header.createHeader(line);
        this.elements.add(newHeader);
        this.curElement = null;
        this.curPattern = line;
      } else if (Pattern.matches(NestedList.PATTERN, line)) {
        NestedList nestedList = NestedList.createList(line);
        this.elements.add(nestedList);
        this.curElement = nestedList;
        this.curPattern = line;
      } else if (Pattern.matches(EmptyLine.PATTERN, line)) {
        this.elements.add(EmptyLine.createEmptyLine(line));
        this.curElement = null;
        this.curPattern = line;
      } else if (Pattern.matches(HorizontalLine.PATTERN, line)) {
        this.elements.add(HorizontalLine.createHorizontalLine());
        this.curElement = null;
        this.curPattern = line;
      } else if (Pattern.matches(BlockQuote.PATTERN, line)) {
        BlockQuote blockQuote = BlockQuote.createBolckQuote();
        blockQuote.process(line);
        this.elements.add(blockQuote);
        this.curElement = blockQuote;
        this.curPattern = line;
      } else {
        Paragraph paragraph = Paragraph.createParagraph(line);
        this.elements.add(paragraph);
        this.curElement = paragraph;
        this.curPattern = line;
      }
    }
  }


  /**
   * {@inheritDoc}
   */

  @Override
  public LinkedList<String> getFormattedContent(Formatter formatter) {
    LinkedList<String> resultList = new LinkedList<>();
    for (TextElement textElement : this.elements) {
      resultList.addAll(textElement.getFormattedContent(formatter));
    }
    formatter.format(this, resultList);
    return resultList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void process(String line) {
    chooseChildToProcess(line);
  }

  /**
   * create a new content. The first element of the content list is a title with string name
   *
   * @param name the title content
   * @return a new content. The first element of the content list is a title with string name
   */
  public static Content createContent(String name) {
    // TODO Auto-generated method stub
    return new Content(name);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPattern() {
    return Content.ContentPattern;
  }
}
