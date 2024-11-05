import java.util.ArrayList;
import java.util.List;

public class masterTest {

    private static ArrayList<Boolean> testSuccess = new ArrayList<>();

    public static void main(String[] args) {
        System.out.print("\033[2J \033[H");
        List<testTemplate> tests = new ArrayList<>();
        tests.add(new settings_test());
        tests.add(new Fundclass_test());
        tests.add(new test_project());
        tests.add(new test_proposalProject());

        System.out.println("Starting Tests \n" + "Total tests: " + tests.size());

        for (int i = 0; i < tests.size(); i++) {
            testSuccess.add(tests.get(i).test());
            System.out.println("completed: (" + (i + 1) + "/" + tests.size() + ")");

        }

        System.out.println("\033[1m======Test Results======");
        for (int i = 0; i < testSuccess.size(); i++) {
            if (testSuccess.get(i)) {
                System.out.println("\033[34;1m"+tests.get(i).getName()+"\033[0m" + ": \033[32;1mSuccess\033[0m");
            } else {
                System.out.println("\033[34;1m"+tests.get(i).getName()+"\033[0m" + ": \033[31;1mFailed\033[0m");
                System.out.println("    Error: " + tests.get(i).getErr());
            }
        }
    }

}
