import java.util.ArrayList;
import java.util.List;

public class masterTest {

    private static ArrayList<Boolean> unitTestsSuccess = new ArrayList<>();
    private static ArrayList<Boolean> IntegrationTestsSuccess = new ArrayList<>();
    private static int failedTests = 0;

    public static void main(String[] args) {
        System.out.print("\033[2J \033[H");
        List<testTemplate> unitTests = new ArrayList<>();
        List<testTemplate> IntegrationTests = new ArrayList<>();
        unitTests.add(new settings_test());
        unitTests.add(new Fundclass_test());
        unitTests.add(new test_project());
        unitTests.add(new test_proposalProject());
        IntegrationTests.add(new test_sorting());
        IntegrationTests.add(new PDFGen_test());
        IntegrationTests.add(new Database_test());

        System.out.println("Starting Tests \n" + "Total unitTests: " + unitTests.size());

        for (int i = 0; i < unitTests.size(); i++) {
            Boolean testResult=unitTests.get(i).test();
            unitTestsSuccess.add(testResult);
            System.out.println("completed: (" + (i + 1) + "/" + unitTests.size() + ")");
            if (!testResult) {
                failedTests++;
            }

        }
        System.out.println("Starting Tests \n" + "Total IntegrationTests: " + IntegrationTests.size());

        for (int i = 0; i < IntegrationTests.size(); i++) {
            Boolean testResult=IntegrationTests.get(i).test();
            IntegrationTestsSuccess.add(testResult);
            System.out.println("completed: (" + (i + 1) + "/" + IntegrationTests.size() + ")");
            if (!testResult) {
                failedTests++;
            }

        }

        System.out.println("\033[1m+------------------------------Test Results------------------------------+\033[0m");
        System.out.println(failedTests + " tests failed of " + (IntegrationTests.size() + unitTests.size()) + " tests");
        System.out.println("\033[1m============Unit Test Results============");
        for (int i = 0; i < unitTestsSuccess.size(); i++) {
            if (unitTestsSuccess.get(i)) {
                System.out
                        .println("\033[34;1m" + unitTests.get(i).getName() + "\033[0m" + ": \033[32;1mSuccess\033[0m");
            } else {
                System.out.println("\033[34;1m" + unitTests.get(i).getName() + "\033[0m" + ": \033[31;1mFailed\033[0m");
                System.out.println("    Error: " + unitTests.get(i).getErr());
            }
        }
        System.out.println("\033[1m============Integration Test Results============");
        for (int i = 0; i < IntegrationTestsSuccess.size(); i++) {
            if (IntegrationTestsSuccess.get(i)) {
                System.out.println(
                        "\033[34;1m" + IntegrationTests.get(i).getName() + "\033[0m" + ": \033[32;1mSuccess\033[0m");
            } else {
                System.out.println(
                        "\033[34;1m" + IntegrationTests.get(i).getName() + "\033[0m" + ": \033[31;1mFailed\033[0m");
                System.out.println("    Error: " + IntegrationTests.get(i).getErr());
            }
        }
    }

}
