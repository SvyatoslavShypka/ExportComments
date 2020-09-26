package Test_16_RegexExportComments;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ExportComments {
    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.err.println("Use it in this way: java ExportComments infile outfile");
            return;
        }

        Pattern p;
        try
        {
            // Next pattern defines few-line comments,
            // that are placed in one line (e.g., /* one line comments */)
            // and one line comments (e.g., // some line).
            // Comments could be placed in any place of the line.
//new line
 /* not works? */
            p = Pattern.compile(".*/\\*.*\\*/|.*//.*$");
        }
        catch (PatternSyntaxException pse)
        {
            System.err.printf("Syntax error in regular expression: %s%n", pse.getMessage());
            System.err.printf("Error description: %s%n", pse.getDescription());
            System.err.printf("Position of error: %s%n", pse.getIndex());
            System.err.printf("Wrong pattern: %s%n", pse.getPattern());
            return;
        }

        try (FileReader fr = new FileReader(args[0]); // something
             BufferedReader br = new BufferedReader(fr);
             FileWriter fw = new FileWriter(args[1]);
             BufferedWriter bw = new BufferedWriter(fw))
        {
            Matcher m = p.matcher("");
            String line;
            while ((line = br.readLine()) != null)
            {
                m.reset(line);
                if (m.matches()) /* It should match whole line */
                {
                    bw.write(line); /* second comment   */
                    bw.newLine();
                }
            }
        }
        catch (IOException ioe)
        {
            System.err.println(ioe.getMessage());
            return;
        }
    }
}
