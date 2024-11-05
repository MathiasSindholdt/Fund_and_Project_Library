import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Database {
    private String IP_of_Database = "localhost";

    /*
     * setIPofDatabase() - sets the host of the database
     * 
     * @arg String - containing the ip of the host
     */
    public void setHostofDatabase(String newIP) {
        this.IP_of_Database = newIP;
    }

    /*
     * projectFormatter() - returns a list of formatted projects
     * 
     * @arg ResultSet
     * Return: ArrayList of projects that have been formatted
     * 
     * This function formats a list of projects from a resultset recived from
     * the database.
     */
    private ArrayList<project> projectFormatter(ResultSet rs) {
        ArrayList<project> tmparr = new ArrayList<project>();
        String tmpstr = new String();
        try {
            while (rs.next()) {
                project tmp = new project();
                tmp.setTitle(rs.getString(1));
                tmpstr = rs.getString(2);
                tmpstr = tmpstr.replace("[", "");
                tmpstr = tmpstr.replace("]", "");
                tmpstr = tmpstr.trim();
                for (String s : tmpstr.split(",")) {
                    tmp.setCategories(s);
                }
                tmp.setDescription(rs.getString(3));
                tmp.setDateCreated(LocalDateTime.parse(rs.getString(4)));
                tmp.setProjectPurpose(rs.getString(5));
                tmp.setProjectOwner(rs.getString(6));
                tmp.setProjectTargetAudience(rs.getString(7));
                tmp.setProjectBudget(rs.getLong(8));

                tmpstr = rs.getString(9);
                tmpstr = tmpstr.replace("[", "");
                tmpstr = tmpstr.replace("]", "");
                tmpstr = tmpstr.trim();
                ArrayList<LocalDateTime> tmpLDTarr = new ArrayList<LocalDateTime>();
                for (String s : tmpstr.split(",")) {
                    tmpLDTarr.add(LocalDateTime.parse(s.trim()));
                }
                tmp.setTimeSpan(tmpLDTarr.get(0), tmpLDTarr.get(1));
                tmp.setProjectActivities(rs.getString(10));

                tmparr.add(tmp);

            }

        } catch (SQLException e2) {

            System.out.println(e2);
        }
        return tmparr;
    }

    /*
     * fundFormatter() - returns a list of formatted funds
     * 
     * @arg ResultSet
     * Return: ArrayList of funds that have been formatted
     * 
     * This function formats a list of funds from a resultset reccived from
     * the database.
     */
    private ArrayList<fundClass> fundFormatter(ResultSet rs) {
        ArrayList<fundClass> tmparr = new ArrayList<fundClass>();
        String tmpstr = new String();
        try {
            while (rs.next()) {
                fundClass tmp = new fundClass();
                tmp.setTitle(rs.getString(1));
                tmpstr = rs.getString(2);
                tmpstr = tmpstr.replace("[", "");
                tmpstr = tmpstr.replace("]", "");
                tmpstr = tmpstr.trim();
                for (String s : tmpstr.split(",")) {
                    tmp.setCategories(s);
                }
                tmp.setDescription(rs.getString(3));
                tmp.setDateCreated(LocalDateTime.parse(rs.getString(4)));

                tmpstr = rs.getString(5);
                tmpstr = tmpstr.replace("[", "");
                tmpstr = tmpstr.replace("]", "");
                tmpstr = tmpstr.trim();
                for (String s : tmpstr.split(",")) {
                    tmp.setDeadlines(LocalDateTime.parse(s));
                }
                tmpstr = rs.getString(6);
                tmpstr = tmpstr.replace("[", "");
                tmpstr = tmpstr.replace("]", "");
                tmpstr = tmpstr.trim();
                for (String s : tmpstr.split(",")) {
                    tmp.setContacts(s);
                }
                tmpstr = rs.getString(7);
                String[] tmpstrArr = tmpstr.split(" - ");
                tmp.setBudget(Long.parseLong(tmpstrArr[0]), Long.parseLong(tmpstrArr[0]));

                tmpstr = rs.getString(8);
                tmpstr = tmpstr.replace("[", "");
                tmpstr = tmpstr.replace("]", "");
                tmpstr = tmpstr.trim();
                for (String s : tmpstr.split(",")) {
                    tmp.setCollaborationHistory(s);
                }
                tmp.setRunning(rs.getBoolean(9));
                tmp.setfundWebsite(rs.getString(10));

            }

        } catch (SQLException e2) {

            System.out.println(e2);
        }
        return tmparr;
    }

    /*
     * proposalProject() - returns list of formatted projectProposals
     * 
     * @arg ResultSet
     * Return: ArrayList of proposalProjects extracted from resultset
     *
     * This function creates a list of properly formatted proposalProjects from
     * a resultset recived from the database.
     */
    private ArrayList<proposalProject> proposalFormatter(ResultSet rs) {
        ArrayList<proposalProject> tmparr = new ArrayList<proposalProject>();
        String tmpstr = new String();
        try {
            while (rs.next()) {
                proposalProject tmp = new proposalProject();
                tmp.setTitle(rs.getString(1));
                tmpstr = rs.getString(2);
                tmpstr = tmpstr.replace("[", "");
                tmpstr = tmpstr.replace("]", "");
                tmpstr = tmpstr.trim();
                for (String s : tmpstr.split(",")) {
                    tmp.setCategories(s);
                }
                tmp.setDescription(rs.getString(3));
                tmp.setDateCreated(LocalDateTime.parse(rs.getString(4)));
                tmp.setProjectPurpose(rs.getString(5));
                tmp.setProjectOwner(rs.getString(6));
                tmp.setProjectTargetAudience(rs.getString(7));
                tmp.setProjectBudget(rs.getLong(8));

                tmpstr = rs.getString(9);
                tmpstr = tmpstr.replace("[", "");
                tmpstr = tmpstr.replace("]", "");
                tmpstr = tmpstr.trim();
                ArrayList<LocalDateTime> tmpLDTarr = new ArrayList<LocalDateTime>();
                for (String s : tmpstr.split(",")) {
                    tmpLDTarr.add(LocalDateTime.parse(s.trim()));
                }
                tmp.setTimeSpan(tmpLDTarr.get(0), tmpLDTarr.get(1));
                tmp.setProjectActivities(rs.getString(10));
                tmparr.add(tmp);
            }

        } catch (SQLException e2) {

            System.out.println(e2);
        }
        return tmparr;
    }

    /*
     * getAllFunds() - gets all funds from database
     * Return: ArrayList of fundClass
     *
     * This function queries the database for all funds and creates a list of
     * funds.
     */
    public ArrayList<fundClass> getAllFunds() {

        ArrayList<fundClass> funds = new ArrayList<fundClass>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "select * from Fund";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            funds = fundFormatter(rs);
            con.close();
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String query = new String();
                query = "select * from Funds";
                String Database_host = "jdbc:mysql://" + this.IP_of_Database.trim() + "/mydb";
                Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                funds = fundFormatter(rs);
                con.close();

            } catch (ClassNotFoundException e2) {

                System.out.println("unable to find mysql connection driver");
            } catch (SQLException e2) {

                System.out.println(e2);
            }

        } catch (SQLException e) {

            System.out.println(e);
        }
        return funds;

    }

    /*
     * getAllProjects() - gets list of all projects from database
     * Return: ArrayList of projects
     * 
     * This function queries the database to get a list of all projects.
     */
    public ArrayList<project> getAllProjects() {

        ArrayList<project> projects = new ArrayList<project>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "select * from project";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            projects = projectFormatter(rs);
            con.close();
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {

            System.out.println(e);
        }
        return projects;
    }

    /*
     * getAllProjectProposals() - returns list of all projectProposals in database
     *
     * Return: ArrayList of projectProposals
     *
     * This function queries the database for all projectProposals and returns
     * a list of them.
     */
    public ArrayList<proposalProject> getAllProjectProposals() {

        ArrayList<proposalProject> proposal = new ArrayList<proposalProject>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "select * from projectProposals";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            proposal = proposalFormatter(rs);
            con.close();
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {

            System.out.println(e);
        }
        return proposal;
    }

        
    /*
     * getAllArchivedFunds() - gets all archived funds from database
     * Return: ArrayList of fundClass
     *
     * This function queries the database for all funds and creates a list of
     * funds.
     */
    public ArrayList<fundClass> getAllArchivedFunds() {

        ArrayList<fundClass> funds = new ArrayList<fundClass>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "select * from ArchivedFund";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            funds = fundFormatter(rs);
            con.close();
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String query = new String();
                query = "select * from Funds";
                String Database_host = "jdbc:mysql://" + this.IP_of_Database.trim() + "/mydb";
                Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                funds = fundFormatter(rs);
                con.close();

            } catch (ClassNotFoundException e2) {

                System.out.println("unable to find mysql connection driver");
            } catch (SQLException e2) {

                System.out.println(e2);
            }

        } catch (SQLException e) {

            System.out.println(e);
        }
        return funds;

    }

    /*
     * getAllArchivedProjects() - gets list of all archived projects from database
     * Return: ArrayList of projects
     * 
     * This function queries the database to get a list of all projects.
     */
    public ArrayList<project> getAllArchivedProjects() {

        ArrayList<project> projects = new ArrayList<project>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "select * from ArchivedProject";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            projects = projectFormatter(rs);
            con.close();
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {

            System.out.println(e);
        }
        return projects;
    }

    /*
     * getAllArchivedProjectProposals() - returns list of all archived projectProposals in database
     *
     * Return: ArrayList of projectProposals
     *
     * This function queries the database for all archived projectProposals and returns
     * a list of them.
     */
    public ArrayList<proposalProject> getAllArchivedProjectProposals() {

        ArrayList<proposalProject> proposal = new ArrayList<proposalProject>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "select * from ArchivedProposal";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            proposal = proposalFormatter(rs);
            con.close();
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {

            System.out.println(e);
        }
        return proposal;
    }

    /*
     * AddFundToDatabase() - adds fund to database
     * 
     * @arg fundClass
     *
     * This function takes a fundClass and adds it to a new row of the
     * Fund table in the database.
     */
    public void AddFundToDatabase(fundClass fund) {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "insert into Fund (title, categories, description, dateCreated,"
                    + " deadlines, contacts, budgetSpan, collaborationHistory, running) values (";
            query += fund.getTitle() + ", ";
            query += fund.getCategories().toString() + ", ";
            query += fund.getDescription() + ", ";
            query += fund.getDateCreated().toString() + ", ";
            query += fund.getDeadlines().toString() + ", ";
            query += fund.getContacts().toString() + ", ";
            query += fund.getBudgetSpan() + ", ";
            query += fund.getCollaborationHistory().toString();
            query += fund.getRunning() + ", ";
            query += fund.getFundWebsite() +"); ";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            con.close();
        } catch (ClassNotFoundException e) {

        } catch (SQLException e) {

            System.out.println(e);
        }
    }

    /*
     * addProjectProposalToDatabase() - adds projectProposal to database
     * 
     * @arg projectProposal
     *
     * This function takes a projectProposal and adds it to a new row of the
     * projectProposal table in the database.
     */
    public void addProjectProposalToDatabase(project pro) {
        try {

            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "insert into projectProposal (title, categories, description, dateCreated, projectPurpose, "
                    + "projectOwner, projectTargetAudience, projectBudget, projectTimespan, projetActivities) values (";
            query += pro.getTitle() + ", ";
            query += pro.getCategories().toString() + ", ";
            query += pro.getDescription() + ", ";
            query += pro.getDateCreated().toString() + ", ";
            query += pro.getProjectPurpose().toString() + ", ";
            query += pro.getProjectOwner().toString() + ", ";
            query += pro.getProjectTargetAudience().toString() + ", ";
            query += pro.getProjectBudget() + ", ";
            ArrayList<LocalDateTime> tmparr = new ArrayList<LocalDateTime>();
            tmparr.add(pro.getProjectTimeSpanTo());
            tmparr.add(pro.getProjectTimeSpanFrom());
            query += tmparr.toString() + ", ";
            query += pro.getProjectActivities() + ");";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            con.close();

        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
    }

    /*
     * addProjectToDatabase() - add a project to database
     * 
     * @arg project
     * 
     * This function takes a project and adds a new row to the project table
     * of the database containing the contents of the project.
     */
    public void addProjectToDatabase(project pro) {
        try {

            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "insert into project (title, categories, description, dateCreated, projectPurpose, projectOwner,"
                    + " projectTargetAudience, projectBudget, projectTimespan, projetActivities) values (";
            query += pro.getTitle() + ", ";
            query += pro.getCategories().toString() + ", ";
            query += pro.getDescription() + ", ";
            query += pro.getDateCreated().toString() + ", ";
            query += pro.getProjectPurpose().toString() + ", ";
            query += pro.getProjectOwner().toString() + ", ";
            query += pro.getProjectTargetAudience().toString() + ", ";
            query += pro.getProjectBudget() + ", ";
            ArrayList<LocalDateTime> tmparr = new ArrayList<LocalDateTime>();
            tmparr.add(pro.getProjectTimeSpanTo());
            tmparr.add(pro.getProjectTimeSpanFrom());
            query += tmparr.toString() + ", ";
            query += pro.getProjectActivities() + ");";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            con.close();

        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
    }

    /*
     * AddArchivedFundToDatabase() - adds archived fund to database
     * 
     * @arg fundClass
     *
     * This function takes a fundClass and adds it to a new row of the
     * ArchivedFund table in the database.
     */
    public void AddArchivedFundToDatabase(fundClass fund) {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "insert into ArchivedFund (title, categories, description, dateCreated,"
                    + " deadlines, contacts, budgetSpan, collaborationHistory, running) values (";
            query += fund.getTitle() + ", ";
            query += fund.getCategories().toString() + ", ";
            query += fund.getDescription() + ", ";
            query += fund.getDateCreated().toString() + ", ";
            query += fund.getDeadlines().toString() + ", ";
            query += fund.getContacts().toString() + ", ";
            query += fund.getBudgetSpan() + ", ";
            query += fund.getCollaborationHistory().toString();
            query += fund.getRunning() + "); ";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            con.close();
        } catch (ClassNotFoundException e) {

        } catch (SQLException e) {

            System.out.println(e);
        }
    }

    /*
     * addArchviedProjectProposalToDatabase() - adds archived projectProposal to database
     * 
     * @arg projectProposal
     *
     * This function takes a projectProposal and adds it to a new row of the
     * ArchivedProjectProposal table in the database.
     */
    public void addArchivedProjectProposalToDatabase(project pro) {
        try {

            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "insert into ArchivedProposal (title, categories, description, dateCreated, projectPurpose, "
                    + "projectOwner, projectTargetAudience, projectBudget, projectTimespan, projetActivities) values (";
            query += pro.getTitle() + ", ";
            query += pro.getCategories().toString() + ", ";
            query += pro.getDescription() + ", ";
            query += pro.getDateCreated().toString() + ", ";
            query += pro.getProjectPurpose().toString() + ", ";
            query += pro.getProjectOwner().toString() + ", ";
            query += pro.getProjectTargetAudience().toString() + ", ";
            query += pro.getProjectBudget() + ", ";
            ArrayList<LocalDateTime> tmparr = new ArrayList<LocalDateTime>();
            tmparr.add(pro.getProjectTimeSpanTo());
            tmparr.add(pro.getProjectTimeSpanFrom());
            query += tmparr.toString() + ", ";
            query += pro.getProjectActivities() + ");";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            con.close();

        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
    }

    /*
     * addArchivedProjectToDatabase() - add a archived project to database
     * 
     * @arg project
     * 
     * This function takes a project and adds a new row to the archived project table
     * of the database containing the contents of the project.
     */
    public void addArchivedProjectToDatabase(project pro) {
        try {

            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "insert into ArchivedProject (title, categories, description, dateCreated, projectPurpose, projectOwner,"
                    + " projectTargetAudience, projectBudget, projectTimespan, projetActivities) values (";
            query += pro.getTitle() + ", ";
            query += pro.getCategories().toString() + ", ";
            query += pro.getDescription() + ", ";
            query += pro.getDateCreated().toString() + ", ";
            query += pro.getProjectPurpose().toString() + ", ";
            query += pro.getProjectOwner().toString() + ", ";
            query += pro.getProjectTargetAudience().toString() + ", ";
            query += pro.getProjectBudget() + ", ";
            ArrayList<LocalDateTime> tmparr = new ArrayList<LocalDateTime>();
            tmparr.add(pro.getProjectTimeSpanTo());
            tmparr.add(pro.getProjectTimeSpanFrom());
            query += tmparr.toString() + ", ";
            query += pro.getProjectActivities() + ");";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            con.close();

        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
    }

    /*
     * updateProposal() - updates proposalProject in database
     * 
     * @arg proposalProject
     *
     * This function updates the contents of a row in the proposalProject
     * table in the database to match the given proposalProject. The row is
     * determined by matching the title of the proposalProject.
     *
     * NOTE: if two projectProposals have the same title then the wrong one
     * may be overwritten.
     */
    public void updateProposal(proposalProject pp) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "update Fund set ";
            query += "title = '" + pp.getTitle() + "' ";
            query += "categories = '" + pp.getCategories().toString() + "' ";
            query += "description = '" + pp.getDescription() + "' ";
            query += "dateCreated = '" + pp.getDateCreated().toString() + "' ";
            query += "deadlines = '" + pp.getDeadlines().toString() + "' ";
            query += "projectPurpose = '" + pp.getProjectPurpose() + "' ";
            query += "projectOwner = '" + pp.getProjectOwner() + "' ";
            query += "projectTargetAudience = '" + pp.getProjectTargetAudience() + "'";
            query += "projectBudget = '" + pp.getProjectBudget() + "' ";
            query += "projectTimespan = '" + "[" + pp.getProjectTimeSpanFrom().toString() + ", "
                    + pp.getProjectTimeSpanFrom().toString() + "]" + "' ";
            query += "projetActivities = '" + pp.getProjectActivities() + "' ";
            query += " where title = " + pp.getTitle() + ";";

            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            con.close();
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
    }

    /*
     * updateProject() - updates a row in the database to contain updated project
     * 
     * @arg project
     *
     * This function creates & executes a query that updates the contents of a
     * row in the project table where the title matches the title of the
     * given project.
     *
     * NOTE: if two projects have the same title then the wrong one
     * may be overwritten.
     */
    public void updateProject(project pro) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "update Fund set ";
            query += "title = '" + pro.getTitle() + "' ";
            query += "categories = '" + pro.getCategories().toString() + "' ";
            query += "description = '" + pro.getDescription() + "' ";
            query += "dateCreated = '" + pro.getDateCreated().toString() + "' ";
            query += "deadlines = '" + pro.getDeadlines().toString() + "' ";
            query += "projectPurpose = '" + pro.getProjectPurpose() + "' ";
            query += "projectOwner = '" + pro.getProjectOwner() + "' ";
            query += "projectTargetAudience = '" + pro.getProjectTargetAudience() + "'";
            query += "projectBudget = '" + pro.getProjectBudget() + "' ";
            query += "projectTimespan = '" + "[" + pro.getProjectTimeSpanFrom().toString() + ", "
                    + pro.getProjectTimeSpanFrom().toString() + "]" + "' ";
            query += "projetActivities = '" + pro.getProjectActivities() + "' ";
            query += " where title = " + pro.getTitle() + ";";

            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            con.close();
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
    }

    /*
     * updateFund() - updates fund in database
     * 
     * @arg fundClass
     *
     * This function updates the contents of a row in the Fund table in the
     * database to match the given proposalProject. The row is determined
     * by matching the title of the fund.
     *
     * NOTE: if two funds have the same title then the wrong one
     * may be overwritten.
     */
    public void updateFund(fundClass fc) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "update Fund set ";
            query += "title = '" + fc.getTitle() + "' ";
            query += "categories = '" + fc.getCategories().toString() + "' ";
            query += "description = '" + fc.getDescription() + "' ";
            query += "dateCreated = '" + fc.getDateCreated().toString() + "' ";
            query += "deadlines = '" + fc.getDeadlines().toString() + "' ";
            query += "contacts = '" + fc.getContacts() + "' ";
            query += "budgetSpan = '" + fc.getBudgetSpan() + "' ";
            query += "collaborationHistory = '" + fc.getCollaborationHistory() + "'";
            query += "running = '" + fc.getRunning() + "' ";
            query += "website = '" + fc.getFundWebsite() + "' ";
            query += " where title = " + fc.getTitle() + ";";

            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            con.close();
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
    }
}
