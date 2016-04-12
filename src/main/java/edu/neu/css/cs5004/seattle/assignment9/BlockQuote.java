package edu.neu.css.cs5004.seattle.assignment9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockQuote implements TextElement {

  private static final String EMPTY = "";

  private static final String SPACE = " ";

  private static final String REPLACEMENT = BlockQuote.EMPTY;

  private static final String TOPLEVELMARKER = "\\>+ +";

  private static final int LEVELMARK = 1;
  protected static final String PATTERN = "( *\\>)+.*";
  private static final String MARKER = "( *\\>)+";
  protected static final String TOPPATTERN = "\\>[^\\>]*";
  private List<TextElement> childern;

  /**
   * Return a blockQuote
   *
   * @return a blockQuote
   */
  public static BlockQuote createBolckQuote() {
    // TODO Auto-generated method stub
    return new BlockQuote();
  }

  /**
   * Create a blockQuote
   */
  private BlockQuote() {
    super();
    this.childern = new ArrayList<>();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void process(String line) {
    Pattern pattern = Pattern.compile(BlockQuote.MARKER);
    Matcher matcher = pattern.matcher(line);
    matcher.find();
    String regularPattern =
        line.substring(0, matcher.end()).replaceAll(BlockQuote.SPACE, BlockQuote.EMPTY);
    line = line.substring(matcher.end());
    this.processHelper(regularPattern + line);
  }

  /**
   * Given a regulated string line, add it to the blockQuote
   *
   * @param line a regulated string line
   */
  private void processHelper(String line) {
    if (Pattern.matches(BlockQuote.TOPPATTERN, line)) {
      line = getRidOfMark(line);
      this.childern.add(Paragraph.createParagraph(line));
    } else {
      BlockQuote blockQuote = BlockQuote.createBolckQuote();
      this.childern.add(blockQuote);
      blockQuote.processHelper(getRidOfLevelMark(line));
    }
  }

  /**
   * Given a line, return the line after get rid of level mark
   *
   * @param line the line to get rid of level mark
   * @return the line after get rid of level mark
   */
  private String getRidOfLevelMark(String line) {
    return line.substring(BlockQuote.LEVELMARK);
  }

  /**
   * Given a line, return the line after get rid of all the leading mark
   *
   * @param line the line to get rid of all the leading mark
   * @return the line after get rid of all the leading mark
   */
  private String getRidOfMark(String line) {
    // TODO Auto-generated method stub
    return line.replaceFirst(BlockQuote.TOPLEVELMARKER, BlockQuote.REPLACEMENT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LinkedList<String> getFormattedContent(Formatter formatter) {
    LinkedList<String> resultList = new LinkedList<>();
    for (TextElement child : this.childern) {
      resultList.addAll(child.getFormattedContent(formatter));
    }
    formatter.format(this, resultList);
    return resultList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPattern() {
    // TODO Auto-generated method stub
    return BlockQuote.PATTERN;
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.childern == null) ? 0 : this.childern.hashCode());
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
    BlockQuote other = (BlockQuote) obj;
    if (this.childern == null) {
      if (other.childern != null) {
        return false;
      }
    } else if (!this.childern.equals(other.childern)) {
      return false;
    }
    return true;
  }


}
