package no.solg.minimalpdflib;

import java.io.IOException;

/**
 *
 * @author Henrik Solgaard
 */
public abstract class PDFObject {
    private final int generation = 0;
    private long offset = 0;

    public long getOffset() {
        return offset;
    }

    public void print(PDFStream s, int n) throws IOException {
        offset = s.getPosition();
        s.println(String.format("%d %d obj", n, generation));
        printObject(s);
        s.println("endobj");
    }

    public abstract void printObject(PDFStream s) throws IOException;
}
