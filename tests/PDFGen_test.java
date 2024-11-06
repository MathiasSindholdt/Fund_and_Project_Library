import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class PDFGen_test extends testTemplate {
    public String ErrDump = new String();

    public boolean test() {
        this.TestName = "PDFgen Test";
        if (!test_PDFGen()) {
            this.ErrMessage += " Error in PDFGen test\n";
            this.ErrMessage += ErrDump;

            return false;
        }

        return true;

    }

    public boolean test_PDFGen() {
        boolean passed = true;

        String name = "Example fund";
        String[] tmpstrarr = { "cat1", "cat2", "cat3" };
        ArrayList<String> categories = new ArrayList<String>(Arrays.asList(tmpstrarr));
        String description = "Example description";
        String purpose = "Example purpose";
        String owner = "Example owner";
        String audience = "Example audience";
        long budget = 666666;
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusDays(2);
        String activites = "Example activites";
        ArrayList<fundClass> fundlist = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String tmp = "Fund" + i;
            fundClass fc = new fundClass();
            fc.setTitle(tmp);
            fc.setDescription(
                    "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.");
            long tmpLong = (69 * (10 * i));
            System.out.println(tmpLong);
            // fc.setCommonBudget(tmpLong);
            fc.setCategories("cat1");
            fc.setCategories("cat2");
            fc.setCategories("cat3");
            for (int j = 0; j < 3; j++) {
                fc.setDeadlines(LocalDateTime.now().plusDays(j));
            }
            fc.setContacts("Hans Erik:23435363");
            for (int j = 0; j < 4; j++) {
                fc.setCollaborationHistory("collab " + (j + 1));
            }
            fundlist.add(fc);
        }
        Boolean onlyone = true;

        project project = new project(name, categories, description, purpose, owner, audience, budget, from, to,
                activites,
                fundlist, onlyone);

        PDFGenerator PDFGen = new PDFGenerator();
        PDFGen.GeneratePDF(project, fundlist);
        try {
            int lineNumber = 0;
            Scanner newPDF = new Scanner(new FileReader("report.pdf"));
            Scanner expectedPDF = new Scanner(new FileReader("expectedFiles/expectedReport.pdf"));
            String newStr = new String();
            String expectedStr = new String();
            while (newPDF.hasNextLine() && expectedPDF.hasNextLine()) {
                lineNumber += 1;
                newStr = newPDF.nextLine();
                expectedStr = expectedPDF.nextLine();

                if (!Objects.equals(newStr, expectedStr)) {
                    ErrDump += "Error on line " + lineNumber + ": " + newStr + " != " + expectedStr;
                    passed = false;
                }

            }
            newPDF.close();
            expectedPDF.close();
        } catch (FileNotFoundException e) {
            ErrDump += "Unable to find file:\n" + e.toString();
        }
        return passed;

    }

}
