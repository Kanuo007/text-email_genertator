package edu.neu.css.cs5004.seattle.assignment9;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLFormatter implements Formatter {

  private static final String FORMALFILETYPE = "\\..*";

  private static final String HEADEROPENTAG = "<h";
  private static final String CLOSETAG = ">";
  private static final String HEADERENDTAG = "</h";
  private static final int MAXHEADERLEVEL = 6;
  private static final String ContentBegin = "<!DOCTYPE html>\n<html>\n";
  private static final String ContentEnd = "</body>\n</html>\n";
  private static final String paragraphBegin = "<p>";
  private static final String paragraphEnd = "</p>\n";
  private static final String ListEachBegin = "<li>";
  private static final String ListEachEnd = "</li>\n";
  private static final String NumberListBegin = "<ol>\n";
  private static final String NumberListEnd = "</ol>\n";
  private static final String UnOrderedListBegin = "<ul>\n";
  private static final String UnOrderedListEnd = "</ul>\n";
  private static final String TitleBegin = "<head>\n" + "  <title>";
  private static final String TitleEnd = "</title>\n</head>\n<body>\n";
  private static final String EmptyLineFormat = "<br/>";
  private static final String HorizontalLineFormat = "<hr>\n";
  private static final String OutPutName = ".html";
  private static final String BOLDPATTERN = "\\*[^\\*]+\\*";
  private static final String BOLDOPENTAG = "<strong>";
  private static final String BOLDENDTAG = "</strong>";
  private static final String BLOCKQUOTEBIGIN = "<blockquote>\n";
  private static final String BLOCKQUOTEEND = "</blockquote>\n";
  private static final String ItalicPATTERN = "\\_[^\\_]+\\_";
  private static final String ItalicOPENTAG = "<em>";
  private static final String ItalicEndTAG = "</em>";
  private static final String LinkTextPattern = "\\[[^\\[\\]]+\\]";
  private static final String URLaddressPattern = "\\([^\\(\\])]+\\)";
  private static final String HyperLinkPattern = "\\[[^\\[\\]]+\\]\\([^\\(\\])]+\\)";
  private static final String HyperLinkHtmlPattern = "\\<a [^\\<a \\</a]+\\</a\\>";
  private static final String HyperPosition = "\\<a href\\=\"\\</a\\>";
  private static final String HyperBegin = "<a href=\"";
  private static final String HyperMidEnd = "\">";
  private static final String HyperFinalEnd = "</a>";


  /**
   * Return a new HTMLFormatter
   *
   * @return a new HTMLFormatter
   */
  public static HTMLFormatter createHTMLFormatter() {
    return new HTMLFormatter();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void format(Content content, LinkedList<String> formattedChild) {
    formattedChild.addFirst(HTMLFormatter.ContentBegin);
    formattedChild.addLast(HTMLFormatter.ContentEnd);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LinkedList<String> format(Paragraph paragraph, String text) {
    LinkedList<String> result = new LinkedList<>();
    result.add(HTMLFormatter.paragraphBegin);
    result.add(formatMarkup(text));
    result.add(HTMLFormatter.paragraphEnd);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void format(ListItem listItem, LinkedList<String> resultList) {
    String formattedText = formatMarkup(resultList.getFirst());
    resultList.set(0, formattedText);
    resultList.addFirst(HTMLFormatter.ListEachBegin);
    resultList.addLast(HTMLFormatter.ListEachEnd);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void format(UnOrderedList unorderedList, LinkedList<String> formattedChildern) {
    formattedChildern.addFirst(HTMLFormatter.UnOrderedListBegin);
    formattedChildern.addLast(HTMLFormatter.UnOrderedListEnd);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void format(NumberList numberList, LinkedList<String> formattedChildren) {
    formattedChildren.addFirst(HTMLFormatter.NumberListBegin);
    formattedChildren.addLast(HTMLFormatter.NumberListEnd);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LinkedList<String> format(Header header, String text) {
    int level = Header.getHeaderPosition(header).size();
    if (level > HTMLFormatter.MAXHEADERLEVEL) {
      level = HTMLFormatter.MAXHEADERLEVEL;
    };
    LinkedList<String> result = new LinkedList<>();
    result.add(getHeaderOpenTag(level));
    result.add(text);
    result.add(getHeaderEndTag(level));
    return result;
  }

  /**
   * Given a level number, return the string adding header end tag and close tag on it
   *
   * @param level the integer we need to add tags
   * @return the string adding header end tag and close tag on it
   */
  private String getHeaderEndTag(int level) {
    return HTMLFormatter.HEADERENDTAG + level + HTMLFormatter.CLOSETAG;
  }

  /**
   * Given a level number, return the string adding header open tag on it
   *
   * @param level the integer we need to add tag
   * @return the string adding header open tag on it
   */
  private String getHeaderOpenTag(int level) {
    String string = HTMLFormatter.HEADEROPENTAG + level + HTMLFormatter.CLOSETAG;
    return string;
  }

  /**
   * {@inheritDoc}
   */

  @Override
  public LinkedList<String> format(Title title, String name) {
    LinkedList<String> result = new LinkedList<>();
    result.add(HTMLFormatter.TitleBegin);
    result.add(name);
    result.add(HTMLFormatter.TitleEnd);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LinkedList<String> format(EmptyLine emptyLine) {
    LinkedList<String> FormattedemptyLine = new LinkedList<>();
    FormattedemptyLine.add(HTMLFormatter.EmptyLineFormat);
    return FormattedemptyLine;
  }


  /**
   *
   * {@inheritDoc}
   */
  @Override
  public LinkedList<String> format(HorizontalLine horizontalLine) {
    LinkedList<String> FormattedHorizontalLine = new LinkedList<>();
    FormattedHorizontalLine.add(HTMLFormatter.HorizontalLineFormat);
    return FormattedHorizontalLine;
  }

  /**
   * Given a line, add bold format on the corresponding part.
   *
   * @param line the line need to be add bold format on
   * @return the line after adding bold format on the corresponding part.
   */
  private String formatBold(String line) {
    Pattern boldPattern = Pattern.compile(HTMLFormatter.BOLDPATTERN);
    Matcher matcher = boldPattern.matcher(line);
    if (!matcher.find()) {
      return line;
    } else {
      matcher = boldPattern.matcher(line);
    }
    StringBuilder stringBuilder = new StringBuilder();
    int index = 0;
    while (matcher.find()) {
      stringBuilder.append(line.substring(index, matcher.start()));
      stringBuilder.append(HTMLFormatter.BOLDOPENTAG);
      stringBuilder.append(line.substring(matcher.start() + 1, matcher.end() - 1));
      stringBuilder.append(HTMLFormatter.BOLDENDTAG);
      index = matcher.end();
    }
    stringBuilder.append(line.substring(index));
    return stringBuilder.toString();
  }


  /**
   * Given a LinkedList containing one line, add Italicized format on the corresponding part.
   *
   * @param line the line need to be Italicized.
   * @return the line after adding Italic format on the corresponding part.
   */
  private String formatItalic(String line) {
    Pattern ItalicPattern = Pattern.compile(HTMLFormatter.ItalicPATTERN);
    Matcher matcher = ItalicPattern.matcher(line);

    if (!matcher.find()) {
      return line;
    } else {
      matcher = ItalicPattern.matcher(line);
    }

    StringBuilder stringBuilder = new StringBuilder();
    int index = 0;
    while (matcher.find()) {
      stringBuilder.append(line.substring(index, matcher.start()));
      stringBuilder.append(HTMLFormatter.ItalicOPENTAG);
      stringBuilder.append(line.substring(matcher.start() + 1, matcher.end() - 1));
      stringBuilder.append(HTMLFormatter.ItalicEndTAG);
      index = matcher.end();
    }
    stringBuilder.append(line.substring(index));
    return stringBuilder.toString();
  }

  /**
   * Given a line, add HyperLink format on the corresponding part. The URL link always be completed
   * in one line
   *
   * @param line the line need to add hyper link format.
   * @return the line after adding hyper link format on the corresponding part.
   */
  private String formatHyperLink(String line) {
    Pattern HyperLink = Pattern.compile(HTMLFormatter.HyperLinkPattern);
    Matcher matcher1 = HyperLink.matcher(line);

    Pattern LinkText = Pattern.compile(HTMLFormatter.LinkTextPattern);
    Matcher matcher2 = LinkText.matcher(line);

    Pattern ULRaddress = Pattern.compile(HTMLFormatter.URLaddressPattern);
    Matcher matcher3 = ULRaddress.matcher(line);

    if (!matcher1.find()) {
      return line;
    } else {
      matcher1 = HyperLink.matcher(line);
    }
    StringBuilder str = new StringBuilder();
    int index = 0;
    while ((matcher1.find()) && (matcher2.find()) && (matcher3.find())) {
      str.append(line.substring(index, matcher1.start()));
      str.append(HTMLFormatter.HyperBegin);
      str.append(line.substring(matcher3.start() + 1, matcher3.end() - 1));
      str.append(HTMLFormatter.HyperMidEnd);
      str.append(line.substring(matcher2.start() + 1, matcher2.end() - 1));
      str.append(HTMLFormatter.HyperFinalEnd);
      index = matcher3.end();
    }
    str.append(line.substring(index));
    return str.toString();
  }


  /**
   * Given a line, add style format on the corresponding part.The style includes bold, Italic and
   * Hyperlinks
   *
   * @param line the line need to be add style format on
   * @return the string containing one line after adding style format on the corresponding part.
   */
  private String formatMarkup(String line) {
    LinkedList<String> replaceHyper = new LinkedList<>();
    int index = 0;
    String AfterHyper = formatHyperLink(line);
    Pattern HyperLink = Pattern.compile(HTMLFormatter.HyperLinkHtmlPattern);
    Matcher matcher = HyperLink.matcher(AfterHyper);
    StringBuilder StrWithoutHyper = new StringBuilder();
    while (matcher.find()) {
      replaceHyper.add(AfterHyper.substring(matcher.start(), matcher.end()));
      StrWithoutHyper.append(AfterHyper.substring(index, matcher.start()));
      StrWithoutHyper.append(HTMLFormatter.HyperBegin);
      StrWithoutHyper.append(HTMLFormatter.HyperFinalEnd);
      index = index + matcher.end();
    }
    if (index < AfterHyper.length()) {
      StrWithoutHyper.append(AfterHyper.substring(index, AfterHyper.length()));
    }
    String AfterBoldItalic = this.formatBold(this.formatItalic(StrWithoutHyper.toString()));
    for (String Hyper : replaceHyper) {
      System.out.println(Hyper);
      AfterBoldItalic = AfterBoldItalic.replaceFirst(HTMLFormatter.HyperPosition, Hyper);
    }
    return AfterBoldItalic;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getOutputName(String title) {
    return title.replaceFirst(HTMLFormatter.FORMALFILETYPE, HTMLFormatter.OutPutName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void format(BlockQuote blockQuote, LinkedList<String> formattedChildren) {
    formattedChildren.addFirst(HTMLFormatter.BLOCKQUOTEBIGIN);
    formattedChildren.addLast(HTMLFormatter.BLOCKQUOTEEND);
  }

}
