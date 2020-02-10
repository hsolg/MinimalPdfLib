package no.solg.minimalpdflib;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Henrik Solgaard
 */
public class CrossRefTable {
    private final ArrayList<CrossRef> entries = new ArrayList<>();

    private class CrossRef {
        protected long offset;
        protected int generation;
        protected boolean inUse;

        protected CrossRef(long offset, int generation, boolean inUse) {
            this.offset = offset;
            this.generation = generation;
            this.inUse = inUse;
        }
    }

    public void add(long offset, int generation) {
        entries.add(new CrossRef(offset, generation, true));
    }

    public int size() {
        return entries.size() + 1;
    }

    public int getDictionaryTableIndex() {
        return 0;
    }

    public void print(PDFStream s) {
        s.println("xref");
        s.println(String.format("%d %d", 0, entries.size() + 1));
        s.println(String.format("%010d %05d f ", 0, 65535));
        Iterator<CrossRef> it = entries.iterator();
        while (it.hasNext()) {
            CrossRef cr = it.next();
            char c = (cr.inUse) ? 'n' : 'f';
            s.println(String.format("%010d %05d %c ", cr.offset, cr.generation, c));
        }
    }
}
