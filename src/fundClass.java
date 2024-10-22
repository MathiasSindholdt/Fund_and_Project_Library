import java.time.LocalDateTime;
import java.util.ArrayList;

public class fundClass extends elementFormatting{
    private ArrayList<LocalDateTime> deadlines = new ArrayList<LocalDateTime>();
    private ArrayList<String> contacts = new ArrayList<String>();
    private long commonBudget = 0; //Handles large numbers
    private ArrayList<String> collaborationHistory= new ArrayList<String>();

    public ArrayList<LocalDateTime> getDeadlines(){
        return deadlines;
    }

    public ArrayList<String> getContacts(){
        return contacts;
    }

    public long getCommonBudget(){
        return commonBudget;
    }

    public ArrayList<String> getCollaborationHistory(){
        return collaborationHistory;
    }

    public void setDeadlines(LocalDateTime newDeadline){
        this.deadlines.add(newDeadline);
    }

    public void setContacts(String newContact){
        this.contacts.add(newContact);
    }

    public void setCommonBudget(long newBudget){
        this.commonBudget = newBudget;
    }

    public void setCollaborationHistory(String newCollaboration){
        this.collaborationHistory.add(newCollaboration);
    }

    public static void main(String[] args) {
        String[] testCatories = {"cat1","cat2","cat3"};
        LocalDateTime[] testDeadlines = {LocalDateTime.parse("2024-10-02T12:00:00"),LocalDateTime.parse("2024-12-04T12:00:00")};
        String[] testContacts = {"lucas","lundse"};
        String[] testCollaborationHistory = {"P1","P2"};

        fundClass fund = new fundClass();
        fund.setTitle("testTitle2");
        fund.setDescription("Lorem Ipsum");
        fund.setCommonBudget(80000000);
        for (int i = 0; i < testCatories.length ; i++){
            fund.setCategories(testCatories[i]);
        }
        for (int i = 0; i < testDeadlines.length ; i++){
            fund.setDeadlines(testDeadlines[i]);
        }
        for (int i = 0; i < testContacts.length ; i++){
            fund.setContacts(testContacts[i]);
        }
        for (int i = 0; i < testCollaborationHistory.length ; i++){
            fund.setCollaborationHistory(testCollaborationHistory[i]);
        }
        System.out.println("Here is the fund: " + fund.getTitle());
        System.out.println("It has the following description: " + fund.getDescription());
        System.out.println("It was created at: " + fund.getDateCreated());
        System.out.println("It has the following categoreis: " + fund.getCategories());
        System.out.println("The deadlines for the fund is: " + fund.getDeadlines());
        System.out.println("Contact persons at the fund are: " + fund.getContacts());
        System.out.println("The fund has a budget of: " + fund.getCommonBudget());
        System.out.println("We have previously collaborated on: " + fund.getCollaborationHistory());
    }
}