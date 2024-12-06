import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PDFGenerator {

    /*
     * GeneratePDF() - Generates a pdf
     *
     * @arg1 project
     * 
     * @arg2 list of fundClass
     *
     * This function generates a pdf based on a project and a list of related
     * funds. The first page consists of informmation about the project.
     * The second page contains an index of related funds.
     * Then each of the related funds get their own pages that describe them.
     */
    public void GeneratePDF(project project, List<fundClass> fundClasseList) {
        int pageTop = 750;
        int textOffset = 0;

        TextStreamObject textStreamObject = new TextStreamObject("F1", 20, 30, pageTop, project.getTitle());
        textOffset += 20;
        if (project.getSensitive()) {
            textStreamObject.add("F1", 12, 30, pageTop - textOffset, "Dette projekt indeholder følsom information");
            textOffset += 16;
        }

        textStreamObject.add("F1", 11, 30, pageTop - textOffset, "Project Ejer: " + project.getProjectOwner());
        textOffset += 14;
        textStreamObject.add("F1", 11, 30 + 400, pageTop - textOffset + 28,
                "Dato: " + project.getDateCreated().toString().split("T")[0]);
        String budget = new String();

        budget = formatNumber(project.getProjectBudget() + "");

        textStreamObject.add("F1", 11, 30 + 400, pageTop - textOffset + 14, "Budget: " + budget);
        textStreamObject.add("F1", 11, 30, pageTop - textOffset, "Kategorier: ");
        List<String> categories = project.getCategories();
        textOffset += 14;
        for (int i = 0; i < categories.size(); i += 2) {
            String line = categories.get(i);
            if (i + 1 < categories.size()) {
            line += ", " + categories.get(i + 1);
            }
            textStreamObject.add("F1", 11, 30, pageTop - textOffset, line);
            textOffset += 14;
        }

        textOffset += 14;
        // textStreamObject.add("F1", 11, 30 + 400, pageTop - textOffset + 28,
        //         "Dato: " + project.getDateCreated().toString().split("T")[0]);
        // String budget = new String();

        // budget = formatNumber(project.getProjectBudget() + "");

        // textStreamObject.add("F1", 11, 30 + 400, pageTop - textOffset + 14, "Budget: " + budget);
        // textOffset += 20;

        textStreamObject.add("F1", 16, 30, pageTop - textOffset, "Beskrivelse");
        textOffset += 16;
        String text = project.getDescription();
        List<String> strings = new ArrayList<String>();
        int index = 0;
        // Split the project description text into chunks of 50 characters
        while (index < text.length()) {
            strings.add(text.substring(index, Math.min(index + 100, text.length())));
            index += 100;
        }

        for (int i = 0; i < strings.size(); i++) {
            if (i + 1 < strings.size()) {
            if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                && Character.isLetter(strings.get(i + 1).charAt(0))) {
                textStreamObject.add("F1", 11, 30, pageTop - textOffset, strings.get(i).trim() + "-");
                textOffset += 14;
            } else if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                && !Character.isLetter(strings.get(i + 1).charAt(0))) {
                textStreamObject.add("F1", 11, 30, pageTop - textOffset,
                    strings.get(i).trim() + strings.get(i + 1).charAt(0));
                strings.set(i + 1, strings.get(i + 1).substring(1));
                textOffset += 14;
            } else {
                textStreamObject.add("F1", 11, 30, pageTop - textOffset, strings.get(i).trim());
                textOffset += 14;
            }
            } else {
            textStreamObject.add("F1", 11, 30, pageTop - textOffset, strings.get(i).trim());
            textOffset += 14;
            }
        }
        textOffset += 10;
        textStreamObject.add("F1", 16, 30, pageTop - textOffset, "Formål");
        textOffset += 16;

        strings.clear();
        text = project.getProjectPurpose();
        index = 0;
        while (index < text.length()) {
            strings.add(text.substring(index, Math.min(index + 80, text.length())));
            index += 80;
        }

        for (int i = 0; i < strings.size(); i++) {

            if (i + 1 < strings.size()) {
                if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                        && Character.isLetter(strings.get(i + 1).charAt(0))) {
                    textStreamObject.add("F1", 11, 30, pageTop - textOffset, strings.get(i).trim() + "-");
                    textOffset += 14;
                } else if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                        && !Character.isLetter(strings.get(i + 1).charAt(0))) {
                    textStreamObject.add("F1", 11, 30, pageTop - textOffset,
                            strings.get(i).trim() + strings.get(i + 1).charAt(0));
                    strings.set(i + 1, strings.get(i + 1).substring(1));
                    textOffset += 14;
                } else {
                    textStreamObject.add("F1", 11, 30, pageTop - textOffset, strings.get(i).trim());
                    textOffset += 14;
                }
            } else {
                textStreamObject.add("F1", 11, 30, pageTop - textOffset, strings.get(i).trim());
                textOffset += 14;
            }
        }
        textOffset += 10;

        if(project.getFund() != null){
            textStreamObject.add("F1", 16, 30, pageTop - textOffset, "Bevilling:");
            textOffset += 16;
        

        strings.clear();
        text = project.getFund().getTitle();
        index = 0;
        while (index < text.length()) {
            strings.add(text.substring(index, Math.min(index + 80, text.length())));
            index += 80;
        }

        for (int i = 0; i < strings.size(); i++) {
            if (i + 1 < strings.size()) {
                if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                        && Character.isLetter(strings.get(i + 1).charAt(0))) {
                    textStreamObject.add("F1", 11, 30, pageTop - textOffset, strings.get(i).trim() + "-");
                    textOffset += 14;
                } else if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                        && !Character.isLetter(strings.get(i + 1).charAt(0))) {
                    textStreamObject.add("F1", 11, 30, pageTop - textOffset,
                            strings.get(i).trim() + strings.get(i + 1).charAt(0));
                    strings.set(i + 1, strings.get(i + 1).substring(1));
                    textOffset += 14;
                } else {
                    textStreamObject.add("F1", 11, 30, pageTop - textOffset, strings.get(i).trim());
                    textOffset += 14;
                }
            } else {
                textStreamObject.add("F1", 11, 30, pageTop - textOffset, strings.get(i).trim());
                textOffset += 14;
            }
        }
        textOffset += 10;
    }


        PageObject page1 = new PageObject();
        page1.addAttribute("Resources", new FontObject("F1", "Times-Roman"));
        page1.addContent(textStreamObject);

        TextStreamObject textStreamObject2 = new TextStreamObject("F1", 20, 30, pageTop, "Suggested Funds");
        textOffset = 20;

        for (int i = 0; i < fundClasseList.size(); i++) {
            if (fundClasseList.get(i).getTitle().length() < 55) {
                textStreamObject2.add("F1", 16, 30, pageTop - textOffset,
                        "   " + (i + 1) + ". " + fundClasseList.get(i).getTitle());
                textOffset += 18;
            } else {

                textStreamObject2.add("F1", 16, 30, pageTop - textOffset,
                        "   " + (i + 1) + ". " + fundClasseList.get(i).getTitle().substring(0, 52) + "...");
                textOffset += 18;
            }
        }
        

        PageObject page2 = new PageObject();
        page2.addAttribute("Resources", new FontObject("F1", "Times-Roman"));
        page2.addContent(textStreamObject2);
        PageCollectionObject pageCollectionObject = new PageCollectionObject();
        ArrayList<PageObject> fundPages = createFundPages(fundClasseList);
        pageCollectionObject.addPages(page1, page2);
        for (PageObject fp : fundPages) {
            pageCollectionObject.addPage(fp);
        }
        CatalogObject catalogObject = new CatalogObject(pageCollectionObject);

        PDF pdf = new PDF(catalogObject);

        try {
            FileWriter fileWriter;
            String home = System.getProperty("user.home");
            System.out.println(System.getProperty("os.name"));
            if (System.getProperty("os.name").contains("Windows")){
                fileWriter = new FileWriter(home + "\\Downloads\\" + project.getTitle() + " report.pdf");
                fileWriter.write(pdf.build());
                fileWriter.close();
            }else if (System.getProperty("os.name").contains("Linux")){
                fileWriter = new FileWriter(home + "/Downloads/" + project.getTitle() + " report.pdf");
                fileWriter.write(pdf.build());
                fileWriter.close();
            }else{
                    System.err.println("unable to determine os");
            }
        } catch (IOException e) {
            try{
                FileWriter fileWriter;
                String home = System.getProperty("user.home");
                System.out.println(System.getProperty("os.name"));
                if (System.getProperty("os.name").contains("Linux")){
                    fileWriter = new FileWriter(home + "/Hentet/" + project.getTitle() + " report.pdf");
                    fileWriter.write(pdf.build());
                    fileWriter.close();
                }
            }catch (IOException e2){
                System.out.println(e2);
                System.out.println("was unable to write to PDF");
            }
            System.out.println(e);
            System.out.println("was unable to write to PDF");
        }

    }

    /*
     * formatNumber() - shortens number to be at most 4 chars
     * 
     * @arg string
     * Return: string
     *
     * This function takes a number in string form and converts it into a
     * shortened version that contains the 3 first numbers and a letter
     * representing the magnitude of the number i.e. 823746 would become
     * 823k and 876238576 would become 876m
     */
    private String formatNumber(String number) {

        String newnumber = number.substring(0, number.length() % 3);
        if (number.length() % 3 != 0) {
            switch (((number.length() - number.length() % 3) / 3)) {
                case 0:
                    newnumber += " ";
                    break;
                case 1:
                    newnumber += "k";
                    break;
                case 2:
                    newnumber += "m";
                    break;
                case 3:
                    newnumber += "b";
                    break;
                case 4:
                    newnumber += "t";
                    break;
                case 5:
                    newnumber += "q";
                    break;

            }
        } else if (number.length() % 3 == 0) {
            newnumber = number.substring(0, (number.length() % 3) + 3);
            switch (((number.length() - number.length() % 3) / 3) - 1) {
                case 0:
                    newnumber += " ";
                    break;
                case 1:
                    newnumber += "k";
                    break;
                case 2:
                    newnumber += "m";
                    break;
                case 3:
                    newnumber += "b";
                    break;
                case 4:
                    newnumber += "t";
                    break;
                case 5:
                    newnumber += "q";
                    break;

            }
        }
        return newnumber;

    }

    /*
     * createFundPages() - creates list of pages for funds
     *
     * @arg list of funds
     * Return: list of pageObject
     *
     * This function creates a list of pages from a list of funds.
     */
    private ArrayList<PageObject> createFundPages(List<fundClass> funds) {
        ArrayList<PageObject> pages = new ArrayList<PageObject>();
        int pageTop = 750;
        for (fundClass fc : funds) {
            TextStreamObject textStreamObject = new TextStreamObject("F1", 20, 30, pageTop, "");
            int textOffset = 0;
            List<String> strings = new ArrayList<String>();
            String text = fc.getTitle();
            int index = 0;
            while (index < text.length()) {
                strings.add(text.substring(index, Math.min(index + 50, text.length())));
                index += 50;
            }

            for (int i = 0; i < strings.size(); i++) {

                if (i + 1 < strings.size()) {
                    if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                            && Character.isLetter(strings.get(i + 1).charAt(0))) {
                        textStreamObject.add("F1", 20, 30, pageTop - textOffset, strings.get(i).trim() + "-");
                        textOffset += 20;
                    } else if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                            && !Character.isLetter(strings.get(i + 1).charAt(0))) {
                        textStreamObject.add("F1", 20, 30, pageTop - textOffset,
                                strings.get(i).trim() + strings.get(i + 1).charAt(0));
                        strings.set(i + 1, strings.get(i + 1).substring(1));
                        textOffset += 20;
                    } else {
                        textStreamObject.add("F1", 20, 30, pageTop - textOffset, strings.get(i).trim());
                        textOffset += 20;
                    }
                } else {
                    textStreamObject.add("F1", 20, 30, pageTop - textOffset, strings.get(i).trim());
                    textOffset += 20;
                }
            }
            textOffset += 20;

            if (fc.getCategories().toString().length() < 20) {
                textStreamObject.add("F1", 11, 30, pageTop - textOffset,
                        "Kategorier: " + fc.getCategories().toString().replace("[", " ").replace("]", " "));
            } else {
                textStreamObject.add("F1", 11, 30, pageTop - textOffset,
                        "Kategorier: "
                                + fc.getCategories().toString().replace("[", " ").replace("]", " ").substring(0, 17)
                                + "...");
            }

            textOffset += 14;
            String budget = new String();

            budget = formatNumber(fc.getBudgetMin() + "") + " - " + formatNumber(fc.getBudgetMax() + "");

            textStreamObject.add("F1", 11, 30 + 400, pageTop - textOffset + 14, "Budget: " + budget);
            ArrayList<String> Deadlines = new ArrayList<String>();
            for (LocalDateTime date : fc.getDeadlines()) {
                Deadlines.add(date.toString().split("T")[0]);
            }
            if (!Deadlines.toString().contains("3000")) {
                textStreamObject.add("F1", 11, 30, pageTop - textOffset,
                        "Deadlines: " + Deadlines.toString().replace("[", " ").replace("]", " "));
                textOffset += 20;
                textOffset += 14;
            } else {
                textStreamObject.add("F1", 11, 30, pageTop - textOffset,
                        "Deadlines: " + "løbene");
                textOffset += 20;
                textOffset += 14;

            }
            textStreamObject.add("F1", 16, 30, pageTop - textOffset, "Beskrivelse");
            textOffset += 18;
            text = fc.getDescription();
            System.out.println("\033[31m" + text + "\n text length: " + text.length() + "\033[0m");
            index = 0;
            while (index < text.length()) {
                strings.add(text.substring(index, Math.min(index + 100, text.length())));
                index += Math.min(index + 100, text.length());
            }

            for (int i = 0; i < strings.size(); i++) {

                if (i + 1 < strings.size()) {
                    if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                            && Character.isLetter(strings.get(i + 1).charAt(0))) {
                        textStreamObject.add("F1", 11, 30, pageTop - textOffset, strings.get(i).trim() + "-");
                        textOffset += 14;
                    } else if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                            && !Character.isLetter(strings.get(i + 1).charAt(0))) {
                        textStreamObject.add("F1", 11, 30, pageTop - textOffset,
                                strings.get(i).trim() + strings.get(i + 1).charAt(0));
                        strings.set(i + 1, strings.get(i + 1).substring(1));
                        textOffset += 14;
                    } else {
                        textStreamObject.add("F1", 11, 30, pageTop - textOffset, strings.get(i).trim());
                        textOffset += 14;
                    }
                } else {
                    textStreamObject.add("F1", 11, 30, pageTop - textOffset, strings.get(i).trim());
                    textOffset += 14;
                }

            }
            textOffset += 18;
            textStreamObject.add("F1", 16, 30, pageTop - textOffset, "Kontaktperson(er)");
            textOffset += 18;
            for (fundContactClass fcc : fc.getContacts()) {
                textStreamObject.add("F1", 11, 30, pageTop - textOffset,
                        fcc.getContactName() + " - " + fcc.getContactEmail() +
                                " - " + fcc.getContactPhoneNumber());
                textOffset += 14;
            }
            textOffset += 18;
            textStreamObject.add("F1", 16, 30, pageTop - textOffset, "Samarbejds historik");
            textOffset += 18;
            if (fc.getCollaborationHistory().size() < 4) {
                for (String s : fc.getCollaborationHistory()) {
                    textStreamObject.add("F1", 11, 30, pageTop - textOffset, s);
                    textOffset += 14;
                }
            } else {
                ArrayList<String> collabs = fc.getCollaborationHistory();
                for (int j = 0; j < 4; j++) {
                    textStreamObject.add("F1", 11, 30, pageTop - textOffset, collabs.get(j));
                    textOffset += 14;
                }
            }
            textOffset += 18;

            PageObject page = new PageObject();
            page.addAttribute("Resources", new FontObject("F1", "Times-Roman"));
            page.addContent(textStreamObject);
            pages.add(page);
        }
        return pages;

    }
}

/*
 * Representation of entire PDF file.
 *
 */
class PDF {

    private CatalogObject catalogObject;

    private int objectCount = 0;

    public PDF(CatalogObject catalogObject) {
        this.catalogObject = catalogObject;
    }

    /*
     * Build() - builds the PDF
     *
     * Return: string that contains the contents of the PDF
     */
    public String build() {
        populateObjectNumbers();
        StringBuilder pdf = new StringBuilder();
        pdf.append("%PDF-1.6\n\n");

        pdf.append(catalogObject.build());
        pdf.append(catalogObject.getPages().build());

        for (PageObject page : catalogObject.getPages().getPages()) {
            pdf.append(page.build());
            if (page.getContent() != null) {
                pdf.append(page.getContent().build());
            }
        }

        pdf.append("trailer\n  << /Root " + catalogObject.getReference().getObjectNumber() + " "
                + catalogObject.getReference().getGeneration() + " R" + "\n   /Size " + (objectCount + 1)
                + "\n  >>\n"
                + "%%EOF");

        return pdf.toString();
    }

    /*
     * populateObjectNumbers() - populates object numbers
     */
    private void populateObjectNumbers() {
        catalogObject.setObjectNumber(++objectCount);
        catalogObject.getPages().setObjectNumber(++objectCount);

        for (PageObject page : catalogObject.getPages().getPages()) {
            page.setObjectNumber(++objectCount);

            if (page.getContent() != null) {
                page.getContent().setObjectNumber(++objectCount);

            }
        }
    }

}

/*
 * Representation of reference to any PDF object.
 *
 */
class PDFObjectReference {
    private int objectNumber;

    private int generation = 0; // Hardcode as it remains same always

    int getObjectNumber() {
        return objectNumber;
    }

    int getGeneration() {
        return generation;
    }

    void setObjectNumber(int objectNumber) {
        this.objectNumber = objectNumber;
    }

}

/**
 * Abstract Representation of PDF objects. All objects in PDF must extend this.
 *
 */
abstract class PDFObject {

    private PDFObjectReference reference = new PDFObjectReference();

    private Map<String, Object> attributes = new HashMap<>();

    public PDFObject(String type) {
        super();
        this.attributes.put("Type", type);
    }

    public void addAttribute(String key, Object value) {
        this.attributes.put(key, value);
    }

    public abstract void addSpecificAttributes();

    /*
     * build() - builds the pdfObject
     *
     * Return: string containing the built pdfObject.
     */
    public String build() {

        addSpecificAttributes();

        StringBuilder pdfObject = new StringBuilder();
        pdfObject.append(reference.getObjectNumber()).append(" ").append(reference.getGeneration())
                .append(" obj\n  ")
                .append(buildObject()).append("\nendobj\n\n");

        return pdfObject.toString();
    }

    public StringBuilder buildObject() {
        StringBuilder pdfObject = new StringBuilder();
        pdfObject.append("<< \n");

        for (String key : attributes.keySet()) {

            Object value = attributes.get(key);
            if (value instanceof String) {
                pdfObject.append("\n     /").append(key).append(" ")
                        .append(((String) value).contains("[") ? "" : "/")
                        .append(value);
            } else if (value instanceof Integer) {
                pdfObject.append("\n     /").append(key).append(" ").append(value);
            } else if (value instanceof PDFObject) {
                pdfObject.append("\n     /").append(key).append(" \n").append(((PDFObject) value).buildObject());
            } else if (value instanceof PDFObjectReference[]) {

                pdfObject.append("\n     /").append(key).append(" [");
                for (PDFObjectReference ref : (PDFObjectReference[]) value) {
                    pdfObject.append(ref.getObjectNumber() + " " + ref.getGeneration() + " R ");
                }
                pdfObject.append("]");
            } else if (value instanceof PDFObjectReference) {
                pdfObject.append("\n     /").append(key).append(" ")
                        .append(((PDFObjectReference) value).getObjectNumber() + " "
                                + ((PDFObjectReference) value).getGeneration() + " R ");
            }
        }
        pdfObject.append("  >>");

        return pdfObject;
    }

    public void setObjectNumber(int objectNumber) {
        this.reference.setObjectNumber(objectNumber);
    }

    PDFObjectReference getReference() {
        return reference;
    }

}

/*
 * Representation of catalog object
 *
 */
class CatalogObject extends PDFObject {

    private PageCollectionObject pages;

    public CatalogObject(PageCollectionObject pageCollectionObject) {
        super("Catalog");
        this.pages = pageCollectionObject;
    }

    @Override
    public void addSpecificAttributes() {
        addAttribute("Pages", pages.getReference());
    }

    PageCollectionObject getPages() {
        return pages;
    }

}

/*
 * Representation of page object.
 *
 */
class PageObject extends PDFObject {

    private StreamObject content;

    public PageObject() {
        super("Page");
    }

    public void addContent(StreamObject streamObject) {
        content = streamObject;
    }

    @Override
    public void addSpecificAttributes() {
        addAttribute("Contents", content.getReference());
    }

    StreamObject getContent() {
        return content;
    }

}

/*
 * Representation of pages object
 *
 */
class PageCollectionObject extends PDFObject {

    private List<PageObject> pages = new ArrayList<>();

    public PageCollectionObject() {
        super("Pages");
    }

    public void addPages(PageObject... pageObjects) {
        for (PageObject pageObject : pageObjects) {
            addPage(pageObject);
        }
    }

    public void addPage(PageObject pageObject) {
        this.pages.add(pageObject);
        pageObject.addAttribute("Parent", getReference());
    }

    @Override
    public void addSpecificAttributes() {
        addAttribute("Count", Integer.valueOf(pages.size()));
        PDFObjectReference[] refArr = new PDFObjectReference[pages.size()];
        for (int i = 0; i < pages.size(); i++) {
            refArr[i] = pages.get(i).getReference();
        }
        addAttribute("Kids", refArr);
    }

    List<PageObject> getPages() {
        return pages;
    }

}

/*
 * Representation of font object
 *
 */
class FontObject extends PDFObject {

    public FontObject(String fontAliasName, String fontName) {
        super(null);

        PDFObject fontDef = new PDFObject("Font") {
            @Override
            public void addSpecificAttributes() {
                addAttribute("Subtype", "Type1");
                addAttribute("BaseFont", fontName);
                addAttribute("Encoding", "WinAnsiEncoding");
            }
        };
        fontDef.addSpecificAttributes();

        PDFObject fontAlias = new PDFObject(null) {
            @Override
            public void addSpecificAttributes() {
                addAttribute(fontAliasName, fontDef);
            }
        };
        fontAlias.addSpecificAttributes();

        addAttribute("Font", fontAlias);
    }

    @Override
    public void addSpecificAttributes() {

    }

}

/*
 * Abstract Representation of stream object
 *
 */
abstract class StreamObject extends PDFObject {

    public StreamObject() {
        super(null);
    }

    public abstract String buildStream();

    public void addSpecificAttributes() {
        addAttribute("Length", Integer.valueOf(100));
    }

    @Override
    public StringBuilder buildObject() {
        StringBuilder sb = super.buildObject();
        sb.append("\nstream").append(buildStream()).append("\nendstream");
        return sb;
    }

}

/*
 * Representation of text stream object
 *
 */
class TextStreamObject extends StreamObject {

    private static final String BEGIN_TEXT = "BT";
    private static final String END_TEXT = "ET";
    private static final String TEXT_FONT = "Tf";
    private static final String TEXT_OFFSET = "Td";
    private static final String SHOW_TEXT = "Tj";

    private List<String> texts = new ArrayList<>();

    public TextStreamObject(String fontAlias, int fontSize, int xPos, int yPos, String text) {
        add(fontAlias, fontSize, xPos, yPos, text);

    }

    public void add(String fontAlias, int fontSize, int xPos, int yPos, String text) {
        this.texts.add(" \n " + BEGIN_TEXT + " \n  /" + fontAlias + " " + fontSize + " " + TEXT_FONT + " \n " + xPos
                + " " + yPos + " " + TEXT_OFFSET + "\n (" + text + ") " + SHOW_TEXT + "\n" + END_TEXT + "\n");
    }

    @Override
    public String buildStream() {
        return texts.stream().collect(Collectors.joining());
    }

    public void clear() {
        texts.clear();
    }
}

/*
 * Representation of graphics stream object
 *
 */
class GraphicStreamObject extends StreamObject {

    private static final String MOVE_POINTER = "m";
    private static final String LINE = "l";
    private static final String LINE_WIDTH = "w";
    private static final String RECTANGLE = "re";
    private static final String FILL = "f";
    private static final String BEZIER_CURVE = "c";
    private static final String BORDER_COLOR = "rg";
    private static final String FILL_COLOR = "RG";
    private static final String STROKE = "S";
    private static final String CLOSE_FILL_STROKE = "b";

    private List<String> graphics = new ArrayList<>();

    public void addLine(int xFrom, int yFrom, int xTo, int yTo) {
        this.graphics.add(
                "\n " + xFrom + " " + yFrom + " " + MOVE_POINTER + " " + xTo + " " + yTo + " " + LINE + " "
                        + STROKE);
    }

    public void addRectangle(int a, int b, int c, int d) {
        this.graphics.add("\n " + a + " " + b + " " + c + " " + d + " " + RECTANGLE + " " + STROKE);
    }

    public void addFilledRectangle(int a, int b, int c, int d, String color) {
        this.graphics.add("\n" + color);
        this.graphics.add("\n " + a + " " + b + " " + c + " " + d + " " + RECTANGLE + " " + FILL + " " + STROKE);
    }

    public void addBezierCurve(int movex, int movey, int a, int b, int c, int d, int e, int f, String borderColor,
            int borderWidth, String fillColor) {
        this.graphics.add("\n" + borderWidth + " " + LINE_WIDTH);
        this.graphics.add("\n" + fillColor + " " + FILL_COLOR);
        this.graphics.add("\n" + borderColor + " " + BORDER_COLOR);
        this.graphics.add("\n" + movex + " " + movey + " " + MOVE_POINTER);
        this.graphics.add("\n " + a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + BEZIER_CURVE + " \n "
                + CLOSE_FILL_STROKE);
    }

    @Override
    public String buildStream() {
        return graphics.stream().collect(Collectors.joining());
    }

}