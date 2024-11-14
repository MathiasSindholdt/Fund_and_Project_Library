import java.util.ArrayList;
import java.time.LocalDateTime;

public class fundClass extends elementFormatting{
    private ArrayList<LocalDateTime> deadlines = new ArrayList<LocalDateTime>();
    private ArrayList<String> contacts = new ArrayList<String>();
    private long budgetMin = 0; //Handles large numbers
    private long budgetMax = 0; //Handles large numbers
    private ArrayList<String> collaborationHistory= new ArrayList<String>();
    private boolean running;
    private String fundWebsite;

    public String getFundWebsite(){
        return fundWebsite;
    }

    public ArrayList<LocalDateTime> getDeadlines(){
        return deadlines;
    }

    public ArrayList<String> getContacts(){
        return contacts;
    }

    public long getBudgetMin(){
        return budgetMin;
    }

    public long getBudgetMax(){
        return budgetMax;
    }

    public String getBudgetSpan(){
        String tempString = getBudgetMin() + " - " + getBudgetMax();
        return tempString;
    }

    public ArrayList<String> getCollaborationHistory(){
        return collaborationHistory;
    }

    public boolean getRunning(){
        return running;
    }

    public void setfundWebsite(String newFundWebsite){
      
        this.fundWebsite = newFundWebsite;
    }

    public void setDeadlines(LocalDateTime newDeadline){
        this.deadlines.add(newDeadline);
    }

    public void setContacts(String newContact){
        this.contacts.add(newContact);
    }

    public void setBudgetMin(long newBudgetMin){
        this.budgetMin = newBudgetMin;
    }

    public void setBudgetMax(long newBudgetMax){
        
        this.budgetMin = newBudgetMax;
    }

    public void setBudget(long newBudgetMin,long newBudgetMax){
        this.budgetMin = newBudgetMin;
        this.budgetMax = newBudgetMax;
    }

    public void setCollaborationHistory(String newCollaboration){
        this.collaborationHistory.add(newCollaboration);
    }

    public void setRunning(boolean setRunning){
        this.running = setRunning;
    }

    //Constructor
    public fundClass(
        String fundName,
        String fundDescription, 
        long fundAmountFrom,
        long fundAmountTo,
        LocalDateTime[] fundDeadline,
        String[] fundCategory,
        String[] fundCollaborationHistory,
        String[] fundContacts,
        String fundWebsite,
        boolean Collaborated,
        boolean running){
        this.setTitle(fundName);
        this.setDescription(fundDescription);
        this.setBudget(fundAmountFrom,fundAmountTo);
        this.setfundWebsite(fundWebsite);
        for(int i = 0; i < fundDeadline.length ; i++){
            this.setDeadlines(fundDeadline[i]);
        }
        for(int i = 0; i < fundCategory.length ; i++){
            this.setCategories(fundCategory[i]);
        }
        for(int i = 0; i < fundContacts.length ; i++){
            this.setContacts(fundContacts[i]);
        }
        if(Collaborated){
            for(int i = 0; i < fundCollaborationHistory.length ; i++){
                this.setCollaborationHistory(fundCollaborationHistory[i]);
            }
        }
        this.setRunning(running);
    }

        //Overloaded
        public fundClass(){}

    public static void main(String[] args) {
        String[] testCatories = {"cat1","cat2","cat3"};
        LocalDateTime[] testDeadlines = {LocalDateTime.parse("2024-10-02T12:00:00"),LocalDateTime.parse("2024-12-04T12:00:00")};
        String[] testContacts = {"lucas","lundse"};
        String[] testCollaborationHistory = {"P1","P2"};

        fundClass fund = new fundClass();
        fund.setTitle("testTitle2");
        fund.setDescription("Lorem Ipsum");
        fund.setfundWebsite("ManyMoney.com");
        fund.setBudget(100000,80000000);
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
        System.out.println("The fund has a budget of: " + fund.getBudgetSpan());
        System.out.println("We have previously collaborated on: " + fund.getCollaborationHistory());
        System.out.println("The fund website can be found at: " + fund.getFundWebsite());
    }
}
