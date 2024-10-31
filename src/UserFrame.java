import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class UserFrame implements ActionListener {

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    // Buttons
    private JButton createProbButton;
    private JButton changeProbButton;
    private JButton createProjectButton;
    private JButton changeProjectButton;
    private JButton createFundButton;
    private JButton changeFundButton;


    private JButton backButton;
    private JButton projectPropButton;
    private JButton projectButton;
    private JButton fundsButton;
    private JButton archiveButton;

    // List to store project proposals
    private List<ProjectProposal> projectProposals;
    private JPanel projectProposalListPanel;
    private JPanel projectProposalFullPanel;

    private List<Project> projects;
    private JPanel projectListPanel;
    private JPanel projectFullPanel;


    // Constructor to set up the GUI
    public UserFrame() {
        initializeFrame();  // Initialize JFrame

        projectProposals = new ArrayList<>();  // Initialize the project proposals list
        projects = new ArrayList<>(); 

        JPanel panel1 = createTopPanel();  // Top panel
        JPanel panel2 = createSidePanel();  // Left-side panel
        JPanel panel3 = createRightSidePanel();  // Right-side panel

        // Card layout for switching between views
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Adding different views as "cards" to cardPanel
        cardPanel.add(createCenterPanel(), "Main"); // The main view
        cardPanel.add(createProjectProposalView(), "ProjectProposal"); // Project Proposal view
        cardPanel.add(createProjectsView(), "Projects"); // Projects view
        cardPanel.add(createFundsView(), "Funds"); // Funds view
        cardPanel.add(createArchiveView(), "Archive"); // Archive view


        // Adding panels to the frame
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(cardPanel, BorderLayout.CENTER);  // Use cardPanel as the center panel
    }

    private void initializeFrame() {
        frame = new JFrame("Bruger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLayout(new BorderLayout(10, 10));
    }

    private JPanel createTopPanel() {
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(213, 213, 213, 255));
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        backButton = createBackButton();
        panel1.add(backButton);

        JLabel label = new JLabel("Bruger", SwingConstants.LEFT);
        panel1.add(label);

        projectPropButton = createProjectPropButton("Projekt forslag");
        panel1.add(projectPropButton);

        projectButton = createProjectButton("Projekter");
        panel1.add(projectButton);

        fundsButton = createFundsButton("Fonde");
        panel1.add(fundsButton);

        archiveButton = createArchiveButton("Arkiv");
        panel1.add(archiveButton);

        return panel1;
    }

    private JPanel createSidePanel() {
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(213, 213, 213, 255));
        panel2.setPreferredSize(new Dimension(100, 100));
        return panel2;
    }



    // Center panel for the main view (like before)
    private JPanel createCenterPanel() {
        JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel5.setBackground(new Color(213, 213, 213, 255));

        // The button to open the project proposal dialog
        createProbButton = createButton("Lav projekt forslag");
        changeProbButton = createButton("Redigér projekt forslag");
        createProjectButton = createButton("Lav et nyt projekt");
        changeProjectButton = createButton("Redigér et projekt");
        createFundButton = createButton("Lav en ny fond");
        changeFundButton = createButton("Redigér en fond");


        

        panel5.add(createProbButton);
        panel5.add(changeProbButton);
        panel5.add(createProjectButton);
        panel5.add(changeProjectButton);
        panel5.add(createFundButton);
        panel5.add(changeFundButton);




        return panel5;
    }

    // Separate view for "Projekt forslag"
    private JPanel createProjectProposalView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Projekt Forslag", SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        // Panel to display project proposals dynamically
        projectProposalListPanel = new JPanel();
        projectProposalListPanel.setLayout(new BoxLayout(projectProposalListPanel, BoxLayout.Y_AXIS));

        // Scroll pane to handle multiple proposals
        JScrollPane scrollPane = new JScrollPane(projectProposalListPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // Separate view for "Projekter"
    private JPanel createProjectsView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel("Projekter", SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);
        
        JScrollPane scrollPane = new JScrollPane(projectListPanel); // Add project list panel in a scroll pane
        panel.add(scrollPane, BorderLayout.CENTER);
    
        return panel;
    }
    

    // Separate view for "Fonde"
    private JPanel createFundsView() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel("Fonde");
        panel.add(label);
        return panel;
    }
    private JPanel createArchiveView() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel("Arkiv");
        panel.add(label);
        return panel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 50));
        button.addActionListener(this);
        return button;
    }

    private JButton createProjectPropButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 50));
        button.addActionListener(this);
        return button;
    }

    private JButton createProjectButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 50));
        button.addActionListener(this);
        return button;
    }

    private JButton createFundsButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 50));
        button.addActionListener(this);
        return button;
    }

    private JButton createArchiveButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(130, 50));
        button.addActionListener(this);
        return button;
    }

    private JButton createBackButton() {
        ImageIcon originalIcon = new ImageIcon("backArrow.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton button = new JButton();
        button.setPreferredSize(new Dimension(50, 50));
        button.setIcon(resizedIcon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addActionListener(this);
        return button;
    }

    // Show the frame
    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == projectPropButton) {
            cardLayout.show(cardPanel, "ProjectProposal"); // Switch to Project Proposal view
        } else if (e.getSource() == projectButton) {
            openProjectDialog();
            cardLayout.show(cardPanel, "Projects"); // Switch to Projects view
        } else if (e.getSource() == fundsButton) {
            cardLayout.show(cardPanel, "Funds"); // Switch to Funds view
        } else if (e.getSource() == backButton) {
            cardLayout.show(cardPanel, "Main"); // Switch back to the main view
        } else if (e.getSource() == archiveButton) {
            cardLayout.show(cardPanel, "Archive"); 
        } else if (e.getSource() == createProbButton) {
            openProjectProposalDialog(); // Opens a popup for creating a project proposal
        } // Switch back to the main view
    }

    // Restore the project proposal dialog
    private void openProjectProposalDialog() {
        JDialog dialog = new JDialog(frame, "Lav Projekt Forslag", true);
        dialog.setSize(700, 700);
        dialog.setLayout(new GridLayout(11, 2, 10, 10));

        JLabel nameLabel = new JLabel("Titel:");
        JTextField nameField = new JTextField();
        dialog.add(nameLabel);
        dialog.add(nameField);

        JLabel ideaLabel = new JLabel("Idé/Formål:");
        JTextField ideaField = new JTextField();
        dialog.add(ideaLabel);
        dialog.add(ideaField);

        JLabel descriptionLabel = new JLabel("Kort beskrivelse af projektet for at danne en mening:");
        JTextArea descriptionArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        dialog.add(descriptionLabel);
        dialog.add(scrollPane);

        JLabel ideaFromLabel = new JLabel("Ideens oprindelse:");
        JTextField ideaFromField = new JTextField();
        dialog.add(ideaFromLabel);
        dialog.add(ideaFromField);

        JLabel ownerLabel = new JLabel("Ejer af idé/forslaget:");
        JTextField ownerField = new JTextField();
        dialog.add(ownerLabel);
        dialog.add(ownerField);

        JLabel targetLabel = new JLabel("Målgruppe (hvem gavner dette forslag):");
        JTextField targetField = new JTextField();
        dialog.add(targetLabel);
        dialog.add(targetField);

        JLabel budgetLabel = new JLabel("Anslået budget (kr.):");
        JTextField budgetField = new JTextField();
        dialog.add(budgetLabel);
        dialog.add(budgetField);

        JLabel fromDateLabel = new JLabel("Fra dato:");
        SpinnerDateModel fromDateModel = new SpinnerDateModel();
        JSpinner fromDateSpinner = new JSpinner(fromDateModel);
        JSpinner.DateEditor fromDateEditor = new JSpinner.DateEditor(fromDateSpinner, "dd/MM/yyyy");
        fromDateSpinner.setEditor(fromDateEditor);
        dialog.add(fromDateLabel);
        dialog.add(fromDateSpinner);

        JLabel toDateLabel = new JLabel("Til dato:");
        SpinnerDateModel toDateModel = new SpinnerDateModel();
        JSpinner toDateSpinner = new JSpinner(toDateModel);
        JSpinner.DateEditor toDateEditor = new JSpinner.DateEditor(toDateSpinner, "dd/MM/yyyy");
        toDateSpinner.setEditor(toDateEditor);
        dialog.add(toDateLabel);
        dialog.add(toDateSpinner);

        JLabel activitiesLabel = new JLabel("Aktiviteter:");
        JTextField activitiesField = new JTextField();
        dialog.add(activitiesLabel);
        dialog.add(activitiesField);

        JButton submitButton = new JButton("Tilføj");
        submitButton.addActionListener(event -> {
            String name = nameField.getText();
            String idea = ideaField.getText();
            String description = descriptionArea.getText();
            String ideaFrom = ideaFromField.getText();
            String owner = ownerField.getText();
            String target = targetField.getText();
            String budget = budgetField.getText();
            Date fromDate = new Date(((java.util.Date) fromDateSpinner.getValue()).getTime());
            Date toDate = new Date(((java.util.Date) toDateSpinner.getValue()).getTime());
            String activities = activitiesField.getText();

            // Create a new project proposal and add it to the list
            ProjectProposal proposal = new ProjectProposal(name, idea, description, ideaFrom, owner, target, budget, fromDate, toDate, activities);
            projectProposals.add(proposal);

            Project project = new Project(name, idea, description, ideaFrom, owner, target, budget, fromDate, toDate, activities);
            projects.add(project);
            
            
            // Update the project proposal panel
            updateProjectProposalList();

            dialog.dispose();
        });

        dialog.add(new JLabel());
        dialog.add(submitButton);

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    

    private void openProjectDialog(){
        JDialog dialog = new JDialog(frame, "Lav Projekt", true);
        dialog.setSize(700, 700);
        dialog.setLayout(new GridLayout(11, 2, 10, 10));

        JLabel nameLabel = new JLabel("Titel:");
        JTextField nameField = new JTextField();
        dialog.add(nameLabel);
        dialog.add(nameField);

        JLabel ideaLabel = new JLabel("Formål:");
        JTextField ideaField = new JTextField();
        dialog.add(ideaLabel);

        JLabel descriptionLabel = new JLabel("Beskrivelse af projektet:");
        JTextArea descriptionArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        dialog.add(descriptionLabel);
        dialog.add(scrollPane);



    }

    private JPanel createRightSidePanel() {
        JPanel panel3 = new JPanel(new CardLayout());
        panel3.setBackground(new Color(213, 213, 213, 255));
        panel3.setPreferredSize(new Dimension(900, 100));
    
        projectProposalFullPanel = new JPanel();
        projectProposalFullPanel.setLayout(new BoxLayout(projectProposalFullPanel, BoxLayout.Y_AXIS));
        JScrollPane proposalScrollPane = new JScrollPane(projectProposalFullPanel);
    
        projectFullPanel = new JPanel();
        projectFullPanel.setLayout(new BoxLayout(projectFullPanel, BoxLayout.Y_AXIS));
        JScrollPane projectScrollPane = new JScrollPane(projectFullPanel);
    
        panel3.add(proposalScrollPane, "ProjectProposalDetails");
        panel3.add(projectScrollPane, "ProjectDetails");
    
        return panel3;
    }
    

    
    
    // Method to update the list and add mouse listeners
    private void updateProjectProposalList() {
        projectProposalListPanel.removeAll();
    
        for (ProjectProposal proposal : projectProposals) {
            JLabel proposalLabel = new JLabel(proposal.getTitle() + " - " + proposal.getOwner());
    
            proposalLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    showProjectProbDetails(proposal); // Show the selected project's details
                }
            });
    
            projectProposalListPanel.add(proposalLabel);
        }
    
        projectProposalListPanel.revalidate();
        projectProposalListPanel.repaint();
    }

    private void updateProjectList() {
        projectListPanel.removeAll();
        
        for (Project project : projects) {
            JLabel projectLabel = new JLabel(project.getTitle() + " - " + project.getOwner());
            
            projectLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    showProjectDetails(project); // Show the selected project's details
                }
            });
            
            projectListPanel.add(projectLabel);
        }
    
        projectListPanel.revalidate();
        projectListPanel.repaint();
    }
    
    

    class ProjectProposal {
        private String title;
        private String idea;
        private String description;
        private String ideaFrom;
        private String owner;
        private String target;
        private String budget;
        private Date fromDate;
        private Date toDate;
        private String activities;
    
        public ProjectProposal(String title, String idea, String description, String ideaFrom, String owner, String target,
                               String budget, Date fromDate, Date toDate, String activities) {
            this.title = title;
            this.idea = idea;
            this.description = description;
            this.ideaFrom = ideaFrom;
            this.owner = owner;
            this.target = target;
            this.budget = budget;
            this.fromDate = fromDate;
            this.toDate = toDate;
            this.activities = activities;
        }
        
        

   public String getTitle() {
            return title;
        }
    public String getOwner() {
            return owner;
        }
    public String getIdea() {
        return idea;
    }

    public String getDescription() {
        return description;
    }

    public String getIdeaFrom() {
        return ideaFrom;
    }
    public String getTarget() {
        return target;
    }

    public String getBudget() {
        return budget;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public String getActivities() {
        return activities;
    }
}  
    // Method to display the clicked project's details
    private void showProjectProbDetails(ProjectProposal proposal) {
        projectProposalFullPanel.removeAll();
        
        projectProposalFullPanel.add(new JLabel("Titel: " + proposal.getTitle()));
        projectProposalFullPanel.add(new JLabel("Ejer: " + proposal.getOwner()));
        projectProposalFullPanel.add(new JLabel("Idé: " + proposal.getIdea()));
        projectProposalFullPanel.add(new JLabel("Beskrivelse: " + proposal.getDescription()));
        projectProposalFullPanel.add(new JLabel("Ideens oprindelse: " + proposal.getIdeaFrom()));
        projectProposalFullPanel.add(new JLabel("Målgruppe: " + proposal.getTarget()));
        projectProposalFullPanel.add(new JLabel("Budget: " + proposal.getBudget()));
        projectProposalFullPanel.add(new JLabel("Fra Dato: " + proposal.getFromDate().toString()));
        projectProposalFullPanel.add(new JLabel("Til Dato: " + proposal.getToDate().toString()));
        projectProposalFullPanel.add(new JLabel("Aktiviteter: " + proposal.getActivities()));
        
        // Approve button
        JButton approveButton = new JButton("Godkend");
        approveButton.addActionListener(event -> {
            approveProposal(proposal);
            projectProposalFullPanel.getParent().getParent().remove(projectProposalFullPanel); // Close details view
            updateProjectProposalList(); // Update the list to reflect changes
        });
        
        // Reject button
        JButton rejectButton = new JButton("Afvis");
        rejectButton.addActionListener(event -> {
            projectProposalFullPanel.getParent().getParent().remove(projectProposalFullPanel); // Close details view
        });
    
        projectProposalFullPanel.add(approveButton);
        projectProposalFullPanel.add(rejectButton);
    
        projectProposalFullPanel.revalidate();
        projectProposalFullPanel.repaint();
    }
    private void approveProposal(ProjectProposal proposal) {
        // Remove the proposal from the project proposals list
        projectProposals.remove(proposal);
        
        // Create a new Project and add it to the projects list
        Project project = new Project(proposal.getTitle(), proposal.getIdea(), proposal.getDescription(), 
                                      proposal.getIdeaFrom(), proposal.getOwner(), proposal.getTarget(), 
                                      proposal.getBudget(), proposal.getFromDate(), proposal.getToDate(), 
                                      proposal.getActivities());
        projects.add(project);
    
        // Update the project list UI
        updateProjectList();
    }
    
    

    private void showProjectDetails(Project project) {
        projectProposalFullPanel.removeAll();
        
        projectFullPanel.add(new JLabel("Titel: " + project.getTitle()));
        projectFullPanel.add(new JLabel("Ejer: " + project.getOwner()));
        projectFullPanel.add(new JLabel("Idé: " + project.getIdea()));
        projectFullPanel.add(new JLabel("Beskrivelse: " + project.getDescription()));
        projectFullPanel.add(new JLabel("Ideens oprindelse " + project.getIdeaFrom()));
        projectFullPanel.add(new JLabel("Målgruppe: " + project.getTarget()));
        projectFullPanel.add(new JLabel("Budget: " + project.getBudget()));
        projectFullPanel.add(new JLabel("Fra Dato: " + project.getFromDate().toString()));
        projectFullPanel.add(new JLabel("Til Dato: " + project.getToDate().toString()));
        projectFullPanel.add(new JLabel("Aktiviteter: " + project.getActivities()));
    
        projectFullPanel.revalidate();
        projectFullPanel.repaint();
    }
    
    public static void main(String[] args) {
        UserFrame guestFrame = new UserFrame();
        guestFrame.show();
    }
}
// Define Project as a separate class
class Project {
    private String title;
    private String idea;
    private String description;
    private String ideaFrom;
    private String owner;
    private String target;
    private String budget;
    private Date fromDate;
    private Date toDate;
    private String activities;

    public Project(String title, String idea, String description, String ideaFrom, String owner, String target,
                   String budget, Date fromDate, Date toDate, String activities) {
        this.title = title;
        this.idea = idea;
        this.description = description;
        this.ideaFrom = ideaFrom;
        this.owner = owner;
        this.target = target;
        this.budget = budget;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.activities = activities;
    }

    public String getTitle() { return title; }
    public String getOwner() { return owner; }
    public String getIdea() { return idea; }
    public String getDescription() { return description; }
    public String getIdeaFrom() { return ideaFrom; }
    public String getTarget() { return target; }
    public String getBudget() { return budget; }
    public Date getFromDate() { return fromDate; }
    public Date getToDate() { return toDate; }
    public String getActivities() { return activities; }
}
