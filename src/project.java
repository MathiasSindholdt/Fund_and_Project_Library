import java.time.LocalDateTime;

public class project extends superProject {
    private String projectPurposeString;
    private String projectOwnerString;
    private String projectTargetAudienceString;
    private long projectBudget = 0; //Handles large numbers
    private LocalDateTime[] projectTimespan = new LocalDateTime[2];
    private String projectActivitiesString;
    

    public String getProjectPurpose(){
        return projectPurposeString;
    }

    public String getProjectOwnerString(){
        return projectOwnerString;
    }

    public String getProjectTargetAudienceString(){
        return projectTargetAudienceString;
    }

    public long getProjectBudget(){
        return projectBudget;
    }

    public LocalDateTime[] getProjectTimeSpan(){
        return projectTimespan;
    }

    public String getProjectActivities(){
        return projectActivitiesString;
    }

    public void setProjectPurpose(String projectPurpose){
        this.projectPurposeString = projectPurpose;
    }

    public void setProjectOwner(String projectOwner){
        this.projectOwnerString = projectOwner;
    }

    public void setProjectTargetAudience(String projectTargetAudience){
        this.projectTargetAudienceString = projectTargetAudience;
    }

    public void setProjectBudget(long newProjectBudget){
        this.projectBudget = newProjectBudget;
    }

    public void setTimeSpan(LocalDateTime from, LocalDateTime to) {
        if (from == null && to != null){
                this.projectTimespan[0] = to.minusDays(1);
            this.projectTimespan[1]  = to;
            if (this.projectTimespan[1].isBefore(LocalDateTime.now().minusDays(1))) {
                throw new IllegalArgumentException("'to' must be after The current day by a day at least");
            }
            System.out.println("TimeSpan set from " + projectTimespan[0] + " to " + projectTimespan[1] + " (default to +1 day)");
        } else if (to == null && from != null){
            this.projectTimespan[0] = from;
            this.projectTimespan[1]  = from.plusDays(1);
            if (this.projectTimespan[0].isBefore(this.projectTimespan[1])) {
                throw new IllegalArgumentException("'to' must be after 'from'");
            }
            System.out.println("TimeSpan set from " + projectTimespan[0] + " to " + projectTimespan[1] + " (default to +1 day)");
        } else if (from == null && to == null){
            this.projectTimespan[0] = LocalDateTime.now();
            this.projectTimespan[1] = this.projectTimespan[0].plusDays(1);
            if (this.projectTimespan[1].isBefore(this.projectTimespan[0])) {
                throw new IllegalArgumentException("'to' must be after 'from'");
            }
            System.out.println("TimeSpan set from " + projectTimespan[0]+ " to " + projectTimespan[1] + " (default to current time and +1 day)");
        } else {
            if (to.isBefore(from)) {
                throw new IllegalArgumentException("'to' must be after 'from'");
            }
            this.projectTimespan[0] = from;
            this.projectTimespan[1]  = to;
            System.out.println("TimeSpan set from " + from + " to " + to);
        }
    }
    //This is an overloaed failover function for timespan, safely setting one if no data present
    public void setTimeSpan() {
        this.projectTimespan[0] = LocalDateTime.now();
        this.projectTimespan[1] = this.projectTimespan[0].plusDays(1);
        if (this.projectTimespan[1].isBefore(this.projectTimespan[0])) {
            throw new IllegalArgumentException("'to' must be after 'from'");
        }
        System.out.println("TimeSpan set from " + projectTimespan[0]+ " to " + projectTimespan[1] + " (default to current time and +1 day)");
    }

    public void setProjectActivities(String newProjectActivities){
        this.projectActivitiesString = newProjectActivities;
    }
    
    public static void main(String[] args) {
        String[] testCatories = {"Byg","Trivsel","cat3"};
  
        superProject superProject = new superProject();
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

        LocalDateTime timeSpantest[] = superProject.getProjectTimeSpan();

        System.out.println("Here is the superProject: " + superProject.getTitle());
        System.out.println("It has the following description: " + superProject.getDescription());
        System.out.println("It was created at: " + superProject.getDateCreated());
        System.out.println("It has the following categoreis: " + superProject.getCategories());
        System.out.println("The main idea is: " + superProject.getProjectPurpose());
        System.out.println("The Project Owner is: " + superProject.getProjectOwnerString());
        System.out.println("The main audience is: " + superProject.getProjectTargetAudienceString());
        System.out.println("The span of the project is from: " + timeSpantest[0] + "and to: " + timeSpantest[1]);
        System.out.println("The following should be completed: " + superProject.getProjectActivities());
    }
}
