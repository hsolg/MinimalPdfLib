package no.solg.minimalpdflib;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;

/**
 *
 * @author Henrik Solgaard
 */
public class PDFStream {
    private final FileChannel ch;
    private final PrintStream s;

    public PDFStream(String filename) throws IOException {
        FileOutputStream fs = new FileOutputStream(filename);
        ch = fs.getChannel();
        // We probably have to set autoflush to true to get correct values from ch.position().
        s = new PrintStream(fs, true /* autoflush */, "ISO-8859-1");
    }

    public long getPosition() throws IOException {
        return ch.position();
    }

    public void println() {
        s.println();
    }

    public void println(String str) {
        s.println(str);
    }

    public void println(int n) {
        s.println(n);
    }

    public void println(long n) {
        s.println(n);
    }

    public void write(byte b[]) throws IOException {
        s.write(b);
    }
}
