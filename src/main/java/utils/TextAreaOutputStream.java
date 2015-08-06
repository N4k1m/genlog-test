/*
 * Institut Supérieur Industriel Liégeois - Département ingénieurs industriels
 * Copyright 2015 Mawet Xavier. All rights reserved.
 * http://www.nakim.be
 */
package utils;

import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JTextArea;

/**
 *
 * @author Nakim
 */
public class TextAreaOutputStream extends OutputStream
{
    private static final TextAreaOutputStream INSTANCE = new TextAreaOutputStream();
    private static final PrintStream OUT;
    private static JTextArea outWriter;
    private static boolean TAINTED = false;

    static
    {
        OUT = System.out;
        System.setOut(new PrintStream(new TextAreaOutputStream()));
    }

    /**
     * Creates a new instance of TextAreaOutputStream.
     */
    private TextAreaOutputStream()
    {
    }

    /**
     * Gets the output stream.
     */
    public static TextAreaOutputStream getInstance(JTextArea textArea)
    {
        outWriter = textArea;
        return INSTANCE;
    }

    /** Gets the functioning console output.
      * @see java.lang.System.out
      */
    public static PrintStream getOldSystemOut()
    {
        return OUT;
    }

    /**
     * Determines if output has occured.
     */
    public static boolean isTainted()
    {
        return TAINTED;
    }

    /**
     * Write output to the Text Area.
     */
    @Override
    public void write(int param)
    {
        outWriter.setText(outWriter.getText() + (char)param);

        // Auto scroll
        outWriter.setCaretPosition(outWriter.getDocument().getLength());

        TAINTED = true;
    }
}
