import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class main {
    static ArrayList<project> projectList = new ArrayList<>();
    static ArrayList<fundClass> fundList = new ArrayList<>();
    boolean onlyOneIsNeeded;
    //Test of Fund constructor vvvv Remove Later
    private static String FundTitle;
    private static String FundDescription;
    private static long FundAmount;
    private static LocalDateTime[] FundDeadline;
    private static String[] FundCategory;
    private static String[] FundCollaborationHistory;
    private static String[] FundContacts;
    private static boolean Collaborated;
    //Test of Proposal to Project constructor vvvv Remove Later
    private static String ProjectTitle;
    private static String ProjectDescription;
    private static String ProjectPurpose;
    private static String ProjectOwner;
    private static String ProjectTargetAudience;
    private static long ProjectBudget;
    private static LocalDateTime ProjectTimeSpanFrom;
    private static LocalDateTime ProjectTimeSpanTo;
    private static String ProjectActivities;
    private static ArrayList<String> ProjectCategories;
    
    
    public static void main(String[] args) {
        Random rand = new Random();
        //runConstructor();  
        //Test of Fund constructor
        FundDescription = "Lorem Ipsum";
        FundCategory = new String[]{"cat1","cat2","cat3"};
        FundCollaborationHistory = new String[]{"P1","P2"};
        FundContacts = new String[]{"lucas","lundse"};
        Collaborated = true;
        for (int i = 1; i < 6; i++){
            FundTitle = "fundTitle" + i;
            int randomNum = rand.nextInt(10)+1;
            FundDeadline = new LocalDateTime[]{LocalDateTime.now().minusDays(randomNum),LocalDateTime.now().plusDays(randomNum+1)};
            FundAmount = i*10000000;
            fundConstructor fund = new fundConstructor(FundTitle, FundDescription, FundAmount, FundDeadline, FundCategory, FundContacts, FundCollaborationHistory, Collaborated);
            fundList.add(fund);
        }
        for(fundClass fund1 : fundList){
            System.out.println(fund1.getTitle());
            System.out.println(fund1.getDescription());
            System.out.println(fund1.getCommonBudget());
            System.out.println(fund1.getDeadlines());
            System.out.println(fund1.getCategories());
            System.out.println(fund1.getContacts());
            System.out.println(fund1.getCollaborationHistory());            
        }
        //Test of Proposal to Project constructor
        ProjectDescription = "Lorem Ipsum";
        ProjectPurpose = "null";
        ProjectOwner = "null";
        ProjectTargetAudience = "null";
        for(int i = 1; i<6; i++){
            int randomNum = rand.nextInt(10)+1;
            ProjectTitle = "testTitle" + i;
            ProjectBudget = randomNum*10000000;
            ProjectTimeSpanFrom = LocalDateTime.now().minusDays(randomNum);
            randomNum = rand.nextInt(10)+1;
            ProjectTimeSpanTo = LocalDateTime.now().plusDays(randomNum);
            ProjectActivities = "null";
            ProjectCategories = new ArrayList<>(Arrays.asList("cat1","cat2","cat3"));   
            proposalProject proposal = new proposalProject(ProjectTitle, ProjectCategories, ProjectDescription, ProjectPurpose, ProjectOwner, ProjectTargetAudience, ProjectBudget, ProjectTimeSpanFrom, ProjectTimeSpanTo, ProjectActivities);
            projectConstructor pConstructor = new projectConstructor();
            projectList.add(pConstructor.proposalToProjectConstructor(proposal, true, fundList));
        }
        for(project proj : projectList){
            System.out.println("project :" + proj.getTitle() + " has the following funds:");
            for(fundClass fund : proj.getDeadlines()){
            System.out.println(fund.getTitle());
        }
            
           
        }
       
    
    }
}