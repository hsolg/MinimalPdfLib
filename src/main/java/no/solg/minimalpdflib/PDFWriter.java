package no.solg.minimalpdflib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Henrik Solgaard
 */
public class PDFWriter {
    private final PDFStream out;
    private final CrossRefTable xref;
    private final ArrayList<PDFObject> objects;
    private final PDFContent content;
    private final int catalogIndex;

    public PDFWriter(String filename) throws IOException {
        out = new PDFStream(filename);
        xref = new CrossRefTable();
        objects = new ArrayList<>();
        content = new PDFContent();
        objects.add(content);
        int contentIndex = objects.size();

        PDFPage page = new PDFPage(contentIndex);
        objects.add(page);
        int pageIndex = objects.size();

        PDFPageTreeNode node = new PDFPageTreeNode(pageIndex);
        objects.add(node);
        int nodeIndex = objects.size();
        page.setParent(nodeIndex);

        PDFCatalog catalog = new PDFCatalog(nodeIndex);
        objects.add(catalog);
        catalogIndex = objects.size();
    }

    public PDFContent getContent() {
        return content;
    }

    public void save() throws IOException {
        out.println("%PDF-1.4");

        // Write objects.
        int n = 0;
        Iterator<PDFObject> it = objects.iterator();
        while (it.hasNext()) {
            PDFObject obj = it.next();
            obj.print(out, ++n);
            xref.add(obj.getOffset(), 0);
        }
        long xrefOffset = out.getPosition();
        xref.print(out);
        out.println("trailer");
        out.println(String.format("<</Size %d/Root %d 0 R>>", xref.size(), catalogIndex));
        out.println("startxref");
        out.println(xrefOffset);
        out.println("%%EOF");
    }
}
