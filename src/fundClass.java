import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

public class fundClass extends elementFormatting{
    private ArrayList<LocalDateTime> deadlines = new ArrayList<LocalDateTime>();
    private ArrayList<fundContactClass> contacts = new ArrayList<>();
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

    public ArrayList<fundContactClass> getContacts(){
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
        if (newFundWebsite.length() > 1000) {
            throw new validationUtils.WrongDataInputException("The website URL exceeds 1000 characters");
        }
        validationUtils.validateURL(newFundWebsite, "URL");

        this.fundWebsite = newFundWebsite;
    }

    public void setDeadlines(LocalDateTime newDeadline){
        this.deadlines.add(newDeadline);
    }

    public void setContacts(fundContactClass newContact){
        this.contacts.add(newContact);
    }

    public void setBudgetMin(long newBudgetMin, long newBudgetMax){
        if (newBudgetMin < 0){
            throw new validationUtils.WrongDataInputException("The minimum fund budget cannot be negative");
        }
        if (newBudgetMin > newBudgetMax){
            throw new validationUtils.WrongDataInputException("The minimum budget cannot be greater than the maximum budget");
        }
        this.budgetMin = newBudgetMin;
    }


    public void setBudgetMax(long newBudgetMax, long newBudgetMin){
        if (newBudgetMax < 0){
            throw new validationUtils.WrongDataInputException("The maximum fund budget cannot be negative");
        }
        if (newBudgetMin > newBudgetMax){
            throw new validationUtils.WrongDataInputException("The maximum budget cannot be smaller than the minimum budget");
        }
        this.budgetMin = newBudgetMax;
    }

    public void setBudget(long newBudgetMin,long newBudgetMax){
        this.budgetMin = newBudgetMin;
        this.budgetMax = newBudgetMax;
    }

    public void setCollaborationHistory(ArrayList<String> newCollaboration){
        this.collaborationHistory.addAll(newCollaboration);
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
        List<LocalDateTime> fundDeadline,
        ArrayList<String> fundCategory,
        ArrayList<String> fundCollaborationHistory,
        ArrayList<fundContactClass> fundContacts,
        String fundWebsite,
        boolean Collaborated,
        boolean running){
        this.setTitle(fundName);
        this.setDescription(fundDescription);
        this.setBudget(fundAmountFrom,fundAmountTo);
        this.setfundWebsite(fundWebsite);
        for(int i = 0; i < fundDeadline.size() ; i++){
            System.out.println("Deadline XD: " + fundDeadline.get(i));
            this.setDeadlines(fundDeadline.get(i));
        }
        for(int i = 0; i < fundCategory.size() ; i++){
            this.setCategories(fundCategory.get(i));
        }
        for(int i = 0; i < fundContacts.size() ; i++){
            for(int j = 0; j < fundContacts.size(); j++){
                System.out.println("Contact XD: " + fundContacts.get(i).toString());
            }
            this.setContacts(fundContacts.get(i));
        }
        if(Collaborated){
            for(int i = 0; i < fundCollaborationHistory.size() ; i++){
                this.collaborationHistory.add(fundCollaborationHistory.get(i));
            }
        }
        this.setRunning(running);
    }

        //Overloaded
        public fundClass(){}

    // public static void main(String[] args) {
    //     String[] testCatories = {"cat1","cat2","cat3"};
    //     LocalDateTime[] testDeadlines = {LocalDateTime.parse("2024-10-02T12:00:00"),LocalDateTime.parse("2024-12-04T12:00:00")};
    //     String[] testContacts = {"lucas","lundse"};
    //     String[] testCollaborationHistory = {"P1","P2"};

    //     fundClass fund = new fundClass();
    //     fund.setTitle("testTitle2");
    //     fund.setDescription("Lorem Ipsum");
    //     fund.setfundWebsite("ManyMoney.com");
    //     fund.setBudget(100000,80000000);
    //     for (int i = 0; i < testCatories.length ; i++){
    //         fund.setCategories(testCatories[i]);
    //     }
    //     for (int i = 0; i < testDeadlines.length ; i++){
    //         fund.setDeadlines(testDeadlines[i]);
    //     }
    //     for (int i = 0; i < testContacts.length ; i++){
    //         fund.setContacts(testContacts[i]);
    //     }
    //     for (int i = 0; i < testCollaborationHistory.length ; i++){
    //         fund.setCollaborationHistory(testCollaborationHistory[i]);
    //     }
    //     System.out.println("Here is the fund: " + fund.getTitle());
    //     System.out.println("It has the following description: " + fund.getDescription());
    //     System.out.println("It was created at: " + fund.getDateCreated());
    //     System.out.println("It has the following categoreis: " + fund.getCategories());
    //     System.out.println("The deadlines for the fund is: " + fund.getDeadlines());
    //     System.out.println("Contact persons at the fund are: " + fund.getContacts());
    //     System.out.println("The fund has a budget of: " + fund.getBudgetSpan());
    //     System.out.println("We have previously collaborated on: " + fund.getCollaborationHistory());
    //     System.out.println("The fund website can be found at: " + fund.getFundWebsite());
    // }
}
