package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Header implements TextElement {


  private static final String LEVELDIVIDER = ".";
  private static final int MARKER_LENGTH = 1;
  static final String PATTERN = "\\#+ .*";
  private static final String TOPLEVEL = "\\# .*";
  private static final int TOPMARKER_LENGTH = 2;
  private static Header root;
  private LinkedList<Header> headers;
  private int id;
  private static int ID = 0;
  private String text;

  /**
   * Create a new header and process the line
   *
   * @param line the line we need to process
   * @return a new header
   */
  public static Header createHeader(String line) {
    if (Header.root == null) {
      Header.root = new Header();
    }
    Header.root.process(line);
    return Header.root.getLastHeader();
  }

  /**
   * Given a header, return the position of this header in the header structure as a number list
   *
   * @param header to determine the position of it
   * @return a number list that is the position of this header in the header structure, return null
   *         if header is not in this structure
   *
   */
  public static List<Integer> getHeaderPosition(Header header) {
    LinkedList<Header> path = new LinkedList<>();
    LinkedList<Integer> pathI = new LinkedList<>();
    Header.findHeaderPath(Header.root, header, path);
    for (int i = 0; i < (path.size() - 1); i++) {
      pathI.add(path.get(i).getHeaders().indexOf(path.get(i + 1)) + 1);
    }
    return pathI;
  }

  /**
   * Given a current header, a target header, and a path, return true if the target header is
   * current header or its descendants, false otherwise
   *
   * @param curHead the current header in the header structure
   * @param head the target header
   * @param path the path from root to the curHead
   * @return true if the target header is current header or its descendants, false otherwise
   */
  private static boolean findHeaderPath(Header curHead, Header head, LinkedList<Header> path) {
    if (curHead.equals(head)) {
      path.add(curHead);
      return true;
    } else {
      path.add(curHead);
      for (Header next : curHead.getHeaders()) {
        if (Header.findHeaderPath(next, head, path)) {
          return true;
        }
      }
      path.removeLast();
      return false;
    }

  }

  /**
   * Return the last header added to the header structure
   *
   * @return the last header added to the header structure
   */
  private Header getLastHeader() {
    Header lastHeader = this.getCurrentHeader();
    while (!lastHeader.getHeaders().isEmpty()) {
      lastHeader = lastHeader.getCurrentHeader();
    }
    return lastHeader;
  }

  /**
   * Given a string of content, create a new header with the content
   *
   * @param content the string content of the header
   */
  private Header(String content) {
    this.text = content;
    this.id = Header.ID++;
    this.headers = new LinkedList<>();
  }

  /**
   * Create a new header with empty string
   */
  public Header() {
    this("");
  }

  /**
   * Return the current child header of this header
   *
   * @return the current child header of this header
   */
  public Header getCurrentHeader() {
    if (getHeaders().isEmpty()) {
      getHeaders().add(new Header());
    }
    return getHeaders().getLast();
  }

  /**
   * Return the child headers of this header
   *
   * @return the child headers of this header
   */
  public LinkedList<Header> getHeaders() {
    return this.headers;
  }

  /**
   * Set the headers as the input headers
   *
   * @param headers the headers to be
   */
  public void setHeaders(LinkedList<Header> headers) {
    this.headers = headers;
  }

  /**
   * Return the content of this header
   *
   * @return the content of this header
   */
  public String getText() {
    return this.text;
  }

  /**
   * Set the content as the input string
   *
   * @param content the content to be
   */
  public void setText(String content) {
    this.text = content;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void process(String line) {
    if (Pattern.matches(Header.TOPLEVEL, line)) {
      this.headers.add(new Header(deleteLevelTopLevelMarker(line)));
    } else {
      getCurrentHeader().process(deleteLevelMarker(line));
    }
  }

  /**
   * Return the last sub header
   *
   * @param line the line to add marker
   * @return the content of this header
   */
  private String deleteLevelTopLevelMarker(String line) {
    // TODO Auto-generated method stub
    return line.substring(Header.TOPMARKER_LENGTH);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LinkedList<String> getFormattedContent(Formatter formatter) {
    return formatter.format(this, this.text);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPattern() {
    // TODO Auto-generated method stub
    return Header.PATTERN;
  }

  /**
   * Return a new line after delete the front substring that is one single marker
   *
   * @param line the line to delete substring
   * @return a new line after delete the front substring that is one single marker
   */
  private String deleteLevelMarker(String line) {
    return line.substring(Header.MARKER_LENGTH);
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    toStringSubHeader("", sb);
    sb.append(Processor.LINE_END_MARK);
    return sb.toString();
  }

  /**
   * Given a prefix string, return the string representation of this header with prefix
   *
   * @param prefix the prefix string to add into the string representation of this header
   * @return the string representation of this header with prefix
   */
  private String toStringWithPrifix(String prefix) {
    StringBuilder sb = new StringBuilder();
    sb.append(prefix);
    sb.append(this.text);
    sb.append(Processor.LINE_END_MARK);
    toStringSubHeader(prefix, sb);
    return sb.toString();
  }

  /**
   * Given a stringBuilder and a prefix, add string representation of sub headers to the
   * stringBuilder
   *
   * @param prefix the prefix to add to string
   * @param sb the stringBuilder to add string representation of sub headers to
   */
  private void toStringSubHeader(String prefix, StringBuilder sb) {
    for (Header header : this.headers) {
      String subPrefix = prefix + (this.headers.indexOf(header) + 1) + Header.LEVELDIVIDER;
      sb.append(header.toStringWithPrifix(subPrefix));
    }
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.text == null) ? 0 : this.text.hashCode());
    result = (prime * result) + ((this.headers == null) ? 0 : this.headers.hashCode());
    result = (prime * result) + this.id;
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
    Header other = (Header) obj;
    if (this.text == null) {
      if (other.text != null) {
        return false;
      }
    } else if (!this.text.equals(other.text)) {
      return false;
    }
    if (this.headers == null) {
      if (other.headers != null) {
        return false;
      }
    } else if (!this.headers.equals(other.headers)) {
      return false;
    }
    if (this.id != other.id) {
      return false;
    }
    return true;
  }


}
