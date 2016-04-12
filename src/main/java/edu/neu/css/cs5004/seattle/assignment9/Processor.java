package edu.neu.css.cs5004.seattle.assignment9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Processor {

  protected static final String LINE_END_MARK = "\n";
  private static final String FAIL_TO_GENERATE_OUTPUT_FILE = "Fail to generate output file.";
  private static final String FAIL_TO_READ = "Fail to read ";
  private static final String CANNOT_BE_FOUND = " cannot be found";
  private static final String NO_FILE_PROVIDED = "No file provided.";
  private TextElement content;
  private Formatter formatter;

  /**
   * Given a txt file name as first element of arguments, return a new html file with same name and
   * reorganized the content to html format
   *
   * @param args the first element is the input file name
   */
  public static void main(String[] args) {
    Processor processor = new Processor();
    File inputFile = processor.validateInput(args);
    processor.content = Content.createContent(inputFile.getName());
    processor.formatter = new HTMLFormatter();
    processor.processFile(inputFile);
    processor.outputFile(inputFile);
  }

  /**
   * Given a string array, validate whether have at least one element in it
   *
   * @param args the input string array
   * @exception throw runtime exception if no element in array
   */
  private File validateInput(String[] args) {
    if ((args == null) || (args.length < 1)) {
      throw new RuntimeException(Processor.NO_FILE_PROVIDED);
    } else {
      return new File(args[0]);
    }
  }

  /**
   * Given a input file, process the content of it
   *
   * @param inputFile the file to be processed
   * @exception throw runtime exception if no file found or have error reading file
   */
  private void processFile(File inputFile) {
    BufferedReader bufferedReader = null;
    try {
      bufferedReader = new BufferedReader(new FileReader(inputFile));
      String Line;
      while ((Line = bufferedReader.readLine()) != null) {
        this.content.process(Line);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(inputFile + Processor.CANNOT_BE_FOUND);
    } catch (IOException e) {
      throw new RuntimeException(Processor.FAIL_TO_READ + inputFile);
    } finally {
      if (bufferedReader != null) {
        try {
          bufferedReader.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          throw new RuntimeException(Processor.FAIL_TO_READ + inputFile);
        }
      }
    }
  }



  /**
   * Given a input file, generate a new html file in the same directory with its content format into
   * html format
   *
   * @param inputFile the input file
   * @exception throw runtime exception when having error writing file
   */
  private void outputFile(File inputFile) {
    BufferedWriter writer = null;
    try {
      File outputFile = new File(this.formatter.getOutputName(inputFile.getName()));
      writer = new BufferedWriter(new FileWriter(outputFile));
      for (String line : this.content.getFormattedContent(this.formatter)) {
        writer.write(line);
      }
    } catch (IOException e) {
      throw new RuntimeException(Processor.FAIL_TO_GENERATE_OUTPUT_FILE);
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          throw new RuntimeException(Processor.FAIL_TO_GENERATE_OUTPUT_FILE);
        }
      }
    }
  }
}
