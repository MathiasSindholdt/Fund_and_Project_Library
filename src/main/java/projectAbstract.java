import java.time.LocalDateTime;
import java.util.ArrayList;

public class projectAbstract extends elementFormatting {
    private String projectPurpose;
    private String projectOwner;
    private String projectTargetAudience;
    private ArrayList<fundClass> fundList = new ArrayList<fundClass>();
    private long projectBudget = 0; //Handles large numbers
    private LocalDateTime[] projectTimespan = new LocalDateTime[2];
    private String projectActivities;
    

    public String getProjectPurpose(){
        return projectPurpose;
    }

    public void setFundList(ArrayList<fundClass> fundList){
        this.fundList = fundList;
    }

    public ArrayList<fundClass> getFunds(){
        return fundList;
    }

    public String getProjectOwner(){
        return projectOwner;
    }

    public String getProjectTargetAudience(){
        return projectTargetAudience;
    }

    public long getProjectBudget(){
        System.out.println("Budget+++++++++++++++: " + projectBudget);
        return projectBudget;
    }

    public LocalDateTime getProjectTimeSpanFrom(){
        return projectTimespan[0];
    }

    public LocalDateTime getProjectTimeSpanTo(){
        return projectTimespan[1];
    }


    public String getProjectActivities(){
        return projectActivities;
    }

 
    public void setProjectPurpose(String newProjectPurpose){
        this.projectPurpose = newProjectPurpose;
    }

    public void setProjectOwner(String newProjectOwner){
        System.out.println("Owner+++++++++++++++: " + newProjectOwner);
        this.projectOwner = newProjectOwner;
    }

    public void setProjectTargetAudience(String newProjectTargetAudience){
        this.projectTargetAudience = newProjectTargetAudience;
    }

    public void setProjectBudget(long newProjectBudget){
        this.projectBudget = newProjectBudget;
    }

    public void setTimeSpan(LocalDateTime from, LocalDateTime to) {
        if (from == null && to != null){
                this.projectTimespan[0] = LocalDateTime.now();
            this.projectTimespan[1]  = to;
            if (this.projectTimespan[1].isBefore(this.projectTimespan[1])) {
                throw new validationUtils.WrongDataInputException("'to' must be after The current day by at least a day");
            }
            System.out.println("TimeSpan set from " + projectTimespan[0] + " to " + projectTimespan[1] + " (default to +1 day)");
        } else if (to == null && from != null){
            this.projectTimespan[0] = from;
            this.projectTimespan[1]  = from.plusDays(1);
            if (this.projectTimespan[0].isBefore(this.projectTimespan[1])) {
                throw new validationUtils.WrongDataInputException("'to' must be after 'from'");
            }
            System.out.println("TimeSpan set from " + projectTimespan[0] + " to " + projectTimespan[1] + " (default to +1 day)");
        } else if (from == null && to == null){
            this.projectTimespan[0] = LocalDateTime.now();
            this.projectTimespan[1] = this.projectTimespan[0].plusDays(1);
            if (this.projectTimespan[1].isBefore(this.projectTimespan[0])) {
                throw new validationUtils.WrongDataInputException("'to' must be after 'from'");
            }
            System.out.println("TimeSpan set from " + projectTimespan[0]+ " to " + projectTimespan[1] + " (default to current time and +1 day)");
        } else {
            if (to.isBefore(from)) {
                throw new validationUtils.WrongDataInputException("'to' must be after 'from'");
            }
            this.projectTimespan[0] = from;
            this.projectTimespan[1]  = to;
            System.out.println("TimeSpan set from " + from + " to " + to);
        }
    }

    public void setProjectActivities(String newProjectActivities){
        this.projectActivities = newProjectActivities;
    }
    
    public static void main(String[] args) {
        String[] testCatories = {"Byg","Trivsel","cat3"};
  
        projectAbstract superProject = new projectAbstract();
        superProject.setTitle("testProject");
        superProject.setDescription("Lorem Ipsum");
        superProject.setProjectBudget(80000000);
        for (int i = 0; i < testCatories.length ; i++){
            superProject.setCategories(testCatories[i]);
        }
        superProject.setTimeSpan(LocalDateTime.now(), LocalDateTime.now().plusDays(3));
        //superProject.setTimeSpan(); //This also works
        superProject.setProjectPurpose("Here is the main idea");
        superProject.setProjectOwner("Lucas");
        superProject.setProjectTargetAudience("Techcollege med mere");
        superProject.setProjectActivities("Her er noget vi skal have gjort");

        LocalDateTime timeSpantest[] = {superProject.getProjectTimeSpanFrom(),superProject.getProjectTimeSpanTo()};

        System.out.println("Here is the superProject: " + superProject.getTitle());
        System.out.println("It has the following description: " + superProject.getDescription());
        System.out.println("It was created at: " + superProject.getDateCreated());
        System.out.println("It has the following categoreis: " + superProject.getCategories());
        System.out.println("The main idea is: " + superProject.getProjectPurpose());
        System.out.println("The Project Owner is: " + superProject.getProjectOwner());
        System.out.println("The main audience is: " + superProject.getProjectTargetAudience());
        System.out.println("The span of the project is from: " + timeSpantest[0] + "and to: " + timeSpantest[1]);
        System.out.println("The following should be completed: " + superProject.getProjectActivities());
    }
}
