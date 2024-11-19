import java.time.LocalDateTime;
import java.util.ArrayList;

public class main {


    static ArrayList<String> categories = new ArrayList<>();
    static ArrayList<String> userProjectList = new ArrayList<>();
    static ArrayList<project> projectList = new ArrayList<>();
    static ArrayList<fundClass> fundList = new ArrayList<>();
    static ArrayList<proposalProject> proposalList = new ArrayList<>();
    static ArrayList<proposalProject> deniedProposalList = new ArrayList<>();
    static ArrayList<project> archiveProjectList = new ArrayList<>();
    static ArrayList<fundClass> archiveFundList = new ArrayList<>();

    public static void initializeLists() {
        categories.addAll(CsvStringReader.readStringCsv("data/categories.csv"));
        userProjectList.addAll(CsvStringReader.readStringCsv("data/nonSystemProjects.csv"));
        projectList.addAll(ProjectCsvReader.readProjectCsv("data/projects.csv"));
        fundList.addAll(FundCsvReader.readFundCsv("data/funds.csv"));
        proposalList.addAll(ProposalCsvReader.readProposalCsv("data/proposals.csv"));
        deniedProposalList.addAll(ProposalCsvReader.readProposalCsv("data/deniedProposals.csv"));
        archiveProjectList.addAll(ProjectCsvReader.readProjectCsv("data/projectsArchive.csv"));
        archiveFundList.addAll(FundCsvReader.readFundCsv("data/fundsArchive.csv"));
    }

    static boolean onlyOneIsNeeded = true;
    
    public static boolean getCatagoryBoolean(){
        return onlyOneIsNeeded;
    }

    public ArrayList<project> getProjectList(){
        return projectList;
    }
    public static ArrayList<fundClass> getFundList(){
        return fundList;
    }
    
    public ArrayList<proposalProject> getArchiveProposalList(){
        return deniedProposalList;
    }

    public ArrayList<project> getArchiveProjectList(){
        return archiveProjectList;
    }

    public ArrayList<fundClass> getArchiveFundList(){
        return archiveFundList;
    }

    public void setProjectList(ArrayList<project> projectList){
        main.projectList = projectList;
    }
    public void setFundList(ArrayList<fundClass> fundList){
        main.fundList = fundList;
    }
    public static void addNewCatagory(String category){
        System.out.println("adding category");
            categories.add(category);
    }
    public static void main(String[] args) {
        Frontpage front = new Frontpage();
        front.show();
    }
}
