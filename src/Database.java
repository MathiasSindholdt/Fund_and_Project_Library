import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Database {
    private String IP_of_Database = "localhost";

    public void setIPofDatabase(String newIP) {
        this.IP_of_Database = newIP;
    }

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

    public ArrayList<project> getAllProjects() {

        ArrayList<project> projects = new ArrayList<project>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "select * from Fund";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            projects = projectFormatter(rs);
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
                projects = projectFormatter(rs);
                con.close();

            } catch (ClassNotFoundException e2) {

                System.out.println("unable to find mysql connection driver");
            } catch (SQLException e2) {

                System.out.println(e2);
            }

        } catch (SQLException e) {

            System.out.println(e);
        }
        return projects;
    }
    public ArrayList<proposalProject> getAllProjectProposals() {

        ArrayList<proposalProject> proposal = new ArrayList<proposalProject>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "select * from Fund";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            proposal = proposalFormatter(rs);
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
                proposal = proposalFormatter(rs);
                con.close();

            } catch (ClassNotFoundException e2) {

                System.out.println("unable to find mysql connection driver");
            } catch (SQLException e2) {

                System.out.println(e2);
            }

        } catch (SQLException e) {

            System.out.println(e);
        }
        return proposal;
    }

    public void addProjectToDatabase(project pro) {
        try {

            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "insert into project (title, categories, description, dateCreated, projectPurpose, projectOwner, projectTargetAudience, projectBudget, projectTimespan, projetActivities) values (";
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
            ResultSet rs = stmt.executeQuery(query);
            con.close();

        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
    }

    public void addProjectProposalToDatabase(project pro) {
        try {

            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "insert into projectProposal (title, categories, description, dateCreated, projectPurpose, projectOwner, projectTargetAudience, projectBudget, projectTimespan, projetActivities) values (";
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
            ResultSet rs = stmt.executeQuery(query);
            con.close();

        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {

        ArrayList<String> array = new ArrayList<String>();
        array.add("test1");
        array.add("test2");
        array.add("test3");
        array.add("test4");

        String arrayListString = new String();

        // [test1, test2, test3, test4]
        arrayListString = array.toString();
        arrayListString = arrayListString.replace("[", "");
        arrayListString = arrayListString.replace("]", "");

        ArrayList<LocalDateTime> dateArray = new ArrayList<LocalDateTime>();
        dateArray.add(LocalDateTime.now());
        dateArray.add(LocalDateTime.now().plusDays(1));
        String dateArrayString = dateArray.toString();
        dateArrayString = dateArrayString.replace("[", "");
        dateArrayString = dateArrayString.replace("]", "");

        ArrayList<LocalDateTime> dateArray2 = new ArrayList<LocalDateTime>();
        String[] tmpStrArr = dateArrayString.split(",");

        for (String s : tmpStrArr) {

            dateArray2.add(LocalDateTime.parse(s.trim()));
        }

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost/mydb", "toor", "toor");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Fund");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            con.close();
        } catch (Exception e) {

            System.out.println(e);
        }
    }

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

                tmpstr = rs.getString(4);
                tmpstr = tmpstr.replace("[", "");
                tmpstr = tmpstr.replace("]", "");
                tmpstr = tmpstr.trim();
                for (String s : tmpstr.split(",")) {
                    tmp.setDeadlines(LocalDateTime.parse(s)); // will be changed to LocalDateTime
                }
                tmpstr = rs.getString(5);
                tmpstr = tmpstr.replace("[", "");
                tmpstr = tmpstr.replace("]", "");
                tmpstr = tmpstr.trim();
                for (String s : tmpstr.split(",")) {
                    tmp.setContacts(s);
                }
                tmp.setCommonBudget(rs.getLong(6));

                tmpstr = rs.getString(7);
                tmpstr = tmpstr.replace("[", "");
                tmpstr = tmpstr.replace("]", "");
                tmpstr = tmpstr.trim();
                for (String s : tmpstr.split(",")) {
                    tmp.setCollaborationHistory(s);
                }

            }

        } catch (SQLException e2) {

            System.out.println(e2);
        }
        return tmparr;
    }

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

    public void AddFundToDatabase(fundClass fund) {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String query = new String();
            query = "insert into Fund (title, categories, description, dateCreated, deadlines, contacts, commonBudget, collaborationHistory) values (";
            query += fund.getTitle() + ", ";
            query += fund.getCategories().toString() + ", ";
            query += fund.getDescription() + ", ";
            query += fund.getDateCreated().toString() + ", ";
            query += fund.getDeadlines().toString() + ", ";
            query += fund.getContacts().toString() + ", ";
            query += fund.getCommonBudget() + ", ";
            query += fund.getCollaborationHistory().toString() + "); ";
            String Database_host = "jdbc:mariadb://" + this.IP_of_Database.trim() + "/mydb";
            Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            con.close();
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String query = new String();
                query = "insert into Fund (title, categories, description, dateCreated, deadlines, contacts, commonBudget, collaborationHistory) values (";
                query += fund.getTitle() + ", ";
                query += fund.getCategories().toString() + ", ";
                query += fund.getDescription() + ", ";
                query += fund.getDateCreated().toString() + ", ";
                query += fund.getDeadlines().toString() + ", ";
                query += fund.getContacts().toString() + ", ";
                query += fund.getCommonBudget() + ", ";
                query += fund.getCollaborationHistory().toString() + "); ";
                String Database_host = "jdbc:mysql://" + this.IP_of_Database.trim() + "/mydb";
                Connection con = DriverManager.getConnection(Database_host, "toor", "toor");
                Statement stmt = con.createStatement();
                stmt.executeQuery(query);
                con.close();
            } catch (ClassNotFoundException e2) {

                System.out.println("unable to find mysql connection driver");
            } catch (SQLException e2) {

                System.out.println(e);
            }

        } catch (SQLException e) {

            System.out.println(e);
        }
    }

}
