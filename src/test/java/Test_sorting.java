import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class Test_sorting extends testTemplate {

    public String ErrDump = new String();

    @Test
    public void invalidDataTest() {

        ArrayList<fundClass> funds = new ArrayList<>();
        ArrayList<fundClass> expectedFunds = new ArrayList<>();

        project project = new project();
        project.setCategories("cat1");

        fundClass fund1 = new fundClass();
        fund1.setDateCreated(LocalDateTime.of(2025, 11, 20, 12, 0));
        fund1.setRunning(false);
        fund1.setDeadlines(LocalDateTime.of(2028, 11, 21, 12, 0));
        fund1.setCategories("cat1");
        funds.add(fund1);

        fundClass fund2 = new fundClass();
        fund2.setDateCreated(LocalDateTime.of(2024, 12, 15, 12, 0));
        fund2.setDeadlines(LocalDateTime.of(2023, 11, 21, 12, 0));
        fund2.setDeadlines(LocalDateTime.of(2029, 11, 21, 12, 0));
        fund2.setRunning(false);
        fund2.setCategories("cat1");
        funds.add(fund2);

        fundClass fund3 = new fundClass();
        fund3.setDateCreated(LocalDateTime.of(2026, 9, 5, 12, 0));
        fund3.setDeadlines(LocalDateTime.of(2027, 11, 21, 12, 0));
        fund3.setRunning(false);
        fund3.setCategories("cat1");
        funds.add(fund3);

        expectedFunds.add(fund3);
        expectedFunds.add(fund1);
        expectedFunds.add(fund2);

        sortingFundLists sorter = new sortingFundLists();
        funds = sorter.sortFunds(funds, project, false);

        for (int i = 0; i < funds.size(); i++) {
            assertEquals(funds.get(i).getDeadlines(), expectedFunds.get(i).getDeadlines());
        }

    }

    @Test
    public void validDataTest() {

        ArrayList<fundClass> funds = new ArrayList<>();
        ArrayList<fundClass> expectedFunds = new ArrayList<>();

        project project = new project();
        project.setCategories("cat1");

        fundClass fund1 = new fundClass();
        fund1.setDateCreated(LocalDateTime.of(2025, 11, 20, 12, 0));
        fund1.setRunning(false);
        fund1.setDeadlines(LocalDateTime.of(2028, 11, 21, 12, 0));
        fund1.setCategories("cat1");
        funds.add(fund1);

        fundClass fund2 = new fundClass();
        fund2.setDateCreated(LocalDateTime.of(2024, 12, 15, 12, 0));
        fund2.setDeadlines(LocalDateTime.of(2026, 11, 21, 12, 0));
        fund2.setDeadlines(LocalDateTime.of(2029, 11, 21, 12, 0));
        fund2.setRunning(false);
        fund2.setCategories("cat1");
        funds.add(fund2);

        fundClass fund3 = new fundClass();
        fund3.setDateCreated(LocalDateTime.of(2026, 9, 5, 12, 0));
        fund3.setDeadlines(LocalDateTime.of(2027, 11, 21, 12, 0));
        fund3.setRunning(false);
        fund3.setCategories("cat1");
        funds.add(fund3);

        expectedFunds.add(fund2);
        expectedFunds.add(fund3);
        expectedFunds.add(fund1);

        sortingFundLists sorter = new sortingFundLists();
        funds = sorter.sortFunds(funds, project, false);

        for (int i = 0; i < funds.size(); i++) {
            assertEquals(funds.get(i).getDeadlines(), expectedFunds.get(i).getDeadlines());
        }
    }

    @Test
    public void globalListSorting_fund_Test() {
        ArrayList<fundClass> funds = new ArrayList<>();
        ArrayList<fundClass> expectedFunds = new ArrayList<>();
        fundClass fund1 = new fundClass();
        fund1.setTitle("fund1");
        fund1.setDateCreated(LocalDateTime.of(2025, 11, 20, 12, 0));
        fund1.setRunning(false);
        fund1.setBudget(100, 1000);
        fund1.setDeadlines(LocalDateTime.of(2028, 11, 21, 12, 0));
        fund1.setCategories("cat1");
        funds.add(fund1);

        fundClass fund2 = new fundClass();
        fund2.setTitle("fund2");
        fund2.setBudget(200, 2000);
        fund2.setDateCreated(LocalDateTime.of(2024, 12, 15, 12, 0));
        fund2.setDeadlines(LocalDateTime.of(2023, 11, 21, 12, 0));
        fund2.setDeadlines(LocalDateTime.of(2029, 11, 21, 12, 0));
        fund2.setRunning(false);
        fund2.setCategories("cat1");
        funds.add(fund2);

        fundClass fund3 = new fundClass();
        fund3.setTitle("fund3");
        fund3.setBudget(300, 3000);
        fund3.setDateCreated(LocalDateTime.of(2026, 9, 5, 12, 0));
        fund3.setDeadlines(LocalDateTime.of(2027, 11, 21, 12, 0));
        fund3.setRunning(false);
        fund3.setCategories("cat1");
        funds.add(fund3);

        expectedFunds.addAll(funds);

        globalListSorting sorter = new globalListSorting();
        funds = sorter.compareTitleFund(funds);

        for (int i = 0; i < expectedFunds.size(); i++) {
            assertEquals(expectedFunds.get(i), funds.get(i));
        }

        expectedFunds.clear();
        expectedFunds.add(fund3);
        expectedFunds.add(fund1);
        expectedFunds.add(fund2);
        funds = sorter.sortFundByClosestDeadline(funds);

        for (int i = 0; i < expectedFunds.size(); i++) {
            System.out.println(expectedFunds.get(i).getDeadlines()+" "+ funds.get(i).getDeadlines());
            assertTrue(Objects.equals(expectedFunds.get(i), funds.get(i)));
        }

        expectedFunds.clear();
        expectedFunds.add(fund2);
        expectedFunds.add(fund1);
        expectedFunds.add(fund3);
        funds = sorter.sortFundByFurthestDeadline(funds);

        for (int i = 0; i < expectedFunds.size(); i++) {
                System.out.println(expectedFunds.get(i).getDeadlines()+" "+ funds.get(i).getTitle()+funds.get(i).getDeadlines());
            assertTrue(Objects.equals(expectedFunds.get(i), funds.get(i)));
        }

        expectedFunds.clear();
        expectedFunds.add(fund3);
        expectedFunds.add(fund2);
        expectedFunds.add(fund1);
        funds = sorter.compareBudgetFundIncreasing(funds);

        for (int i = 0; i < expectedFunds.size(); i++) {
            System.out.println(expectedFunds.get(i).getTitle()+" result: "+
                    funds.get(i).getTitle());
            assertTrue(Objects.equals(expectedFunds.get(i), funds.get(i)));
        }

        expectedFunds.clear();
        expectedFunds.add(fund1);
        expectedFunds.add(fund2);
        expectedFunds.add(fund3);
        funds = sorter.compareBudgetFundDecreasing(funds);

        for (int i = 0; i < expectedFunds.size(); i++) {
            assertTrue(Objects.equals(expectedFunds.get(i), funds.get(i)));
        }
    }

    @Test
    public void globalListSorting_project_Test() {
        ArrayList<project> projects = new ArrayList<>();
        ArrayList<project> expectedprojects = new ArrayList<>();
        project project1 = new project();
        project1.setTitle("project1");
        project1.setProjectOwner("owner1");
        project1.setDateCreated(LocalDateTime.of(2025, 11, 20, 12, 0));
        project1.setCategories("cat1");
        projects.add(project1);

        project project2 = new project();
        project2.setTitle("project2");
        project2.setProjectOwner("owner2");
        project2.setDateCreated(LocalDateTime.of(2024, 12, 15, 12, 0));
        project2.setCategories("cat1");
        projects.add(project2);

        project project3 = new project();
        project3.setTitle("project3");
        project3.setProjectOwner("owner3");
        project3.setDateCreated(LocalDateTime.of(2026, 9, 5, 12, 0));
        project3.setCategories("cat1");
        projects.add(project3);

        expectedprojects.addAll(projects);

        globalListSorting sorter = new globalListSorting();
        projects = sorter.compareTitleProject(projects);

        for (int i = 0; i < expectedprojects.size(); i++) {
            assertEquals(expectedprojects.get(i), projects.get(i));
        }

        expectedprojects.clear();
        expectedprojects.add(project1);
        expectedprojects.add(project2);
        expectedprojects.add(project3);
        projects = sorter.compareOwnerProject(projects);

        for (int i = 0; i < expectedprojects.size(); i++) {
            assertTrue(Objects.equals(expectedprojects.get(i), projects.get(i)));
        }

        expectedprojects.clear();
        expectedprojects.add(project3);
        expectedprojects.add(project2);
        expectedprojects.add(project1);
        projects = sorter.compareOwnerReverseProject(projects);

        for (int i = 0; i < expectedprojects.size(); i++) {
            assertTrue(Objects.equals(expectedprojects.get(i), projects.get(i)));
        }

        expectedprojects.clear();
        expectedprojects.add(project3);
        expectedprojects.add(project2);
        expectedprojects.add(project1);
        projects = sorter.compareTitleReverseProject(projects);

        for (int i = 0; i < expectedprojects.size(); i++) {
            System.out.println(expectedprojects.get(i).getTitle()+" result: "+
                    projects.get(i).getTitle());
            assertTrue(Objects.equals(expectedprojects.get(i), projects.get(i)));
        }
    }

    @Test
    public void globalListSorting_proposalProject_Test() {
        ArrayList<proposalProject> proposalProjects = new ArrayList<>();
        ArrayList<proposalProject> expectedproposalProjects = new ArrayList<>();
        proposalProject proposalProject1 = new proposalProject();
        proposalProject1.setTitle("proposalProject1");
        proposalProject1.setProjectOwner("owner1");
        proposalProject1.setDateCreated(LocalDateTime.of(2025, 11, 20, 12, 0));
        proposalProject1.setCategories("cat1");
        proposalProjects.add(proposalProject1);

        proposalProject proposalProject2 = new proposalProject();
        proposalProject2.setTitle("proposalProject2");
        proposalProject2.setProjectOwner("owner2");
        proposalProject2.setDateCreated(LocalDateTime.of(2024, 12, 15, 12, 0));
        proposalProject2.setCategories("cat1");
        proposalProjects.add(proposalProject2);

        proposalProject proposalProject3 = new proposalProject();
        proposalProject3.setTitle("proposalProject3");
        proposalProject3.setProjectOwner("owner3");
        proposalProject3.setDateCreated(LocalDateTime.of(2026, 9, 5, 12, 0));
        proposalProject3.setCategories("cat1");
        proposalProjects.add(proposalProject3);

        expectedproposalProjects.addAll(proposalProjects);

        globalListSorting sorter = new globalListSorting();
        proposalProjects = sorter.compareTitleProposal(proposalProjects);

        for (int i = 0; i < expectedproposalProjects.size(); i++) {
            assertEquals(expectedproposalProjects.get(i), proposalProjects.get(i));
        }

        expectedproposalProjects.clear();
        expectedproposalProjects.add(proposalProject1);
        expectedproposalProjects.add(proposalProject2);
        expectedproposalProjects.add(proposalProject3);
        proposalProjects = sorter.compareOwnerProposal(proposalProjects);

        for (int i = 0; i < expectedproposalProjects.size(); i++) {
            assertTrue(Objects.equals(expectedproposalProjects.get(i), proposalProjects.get(i)));
        }

        expectedproposalProjects.clear();
        expectedproposalProjects.add(proposalProject3);
        expectedproposalProjects.add(proposalProject2);
        expectedproposalProjects.add(proposalProject1);
        proposalProjects = sorter.compareTitleProposalReverse(proposalProjects);

        for (int i = 0; i < expectedproposalProjects.size(); i++) {
            assertTrue(Objects.equals(expectedproposalProjects.get(i), proposalProjects.get(i)));
        }

        expectedproposalProjects.clear();
        expectedproposalProjects.add(proposalProject3);
        expectedproposalProjects.add(proposalProject2);
        expectedproposalProjects.add(proposalProject1);
        proposalProjects = sorter.compareTitleProposalReverse(proposalProjects);

        for (int i = 0; i < expectedproposalProjects.size(); i++) {
            System.out.println(expectedproposalProjects.get(i).getTitle()+" result: "+
                    proposalProjects.get(i).getTitle());
            assertTrue(Objects.equals(expectedproposalProjects.get(i), proposalProjects.get(i)));
        }
    }
}
