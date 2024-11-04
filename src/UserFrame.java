import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
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
    private List<proposalProject> proposalProjects;
    private JPanel proposalProjectListPanel;
    private JPanel proposalProjectFullPanel;

    private ArrayList<project> projects;
    private JPanel projectListPanel;
    private JPanel projectFullPanel;

    private JPanel tagButtonPanel;


    private ArrayList<fundClass> fundList;
    private JPanel fundListPanel;
    private JPanel fundFullPanel;

    private JPanel rightSidePanel;
    
    List<JCheckBox> tagCheckBoxes = new ArrayList<>(); // Stores checkboxes for dynamic tags


    
    // Constructor to set up the GUI
    public UserFrame() {
        initializeFrame();  // Initialize JFrame

        proposalProjects = new ArrayList<>();  // Initialize the project proposals list
        projects = new ArrayList<>(); 

        JPanel panel1 = createTopPanel();  // Top panel
        JPanel panel2 = createSidePanel();  // Left-side panel
        JPanel panel3 = createRightSidePanel();  // Right-side panel
        

        // Card layout for switching between views
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Adding different views as "cards" to cardPanel
        cardPanel.add(createCenterPanel(), "Main"); // The main view
        cardPanel.add(createproposalProjectView(), "proposalProject"); // Project Proposal view
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
    private JPanel createproposalProjectView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Projekt Forslag", SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        // Panel to display project proposals dynamically
        proposalProjectListPanel = new JPanel();
        proposalProjectListPanel.setLayout(new BoxLayout(proposalProjectListPanel, BoxLayout.Y_AXIS));

        // Scroll pane to handle multiple proposals
        JScrollPane scrollPane = new JScrollPane(proposalProjectListPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // Separate view for "Projekter"
    private JPanel createProjectsView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Projekter", SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);
    
        // Initialize the project list panel
        projectListPanel = new JPanel();
        projectListPanel.setLayout(new BoxLayout(projectListPanel, BoxLayout.Y_AXIS)); // Ensure layout is set
    
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
        
        // Initialize fund list panel
        fundListPanel = new JPanel();
        fundListPanel.setLayout(new BoxLayout(fundListPanel, BoxLayout.Y_AXIS));
        
        // Create a scroll pane for the fund list
        JScrollPane fundScrollPane = new JScrollPane(fundListPanel);
        panel.add(fundScrollPane);
        
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

  // Inde i UserFrame klassen

// Metode til at opdatere højrepanelet baseret på aktivt faneblad
private void updateRightSidePanel(String tab) {
    CardLayout layout = (CardLayout) rightSidePanel.getLayout();
    switch (tab) {
        case "proposalProject":
            layout.show(rightSidePanel, "proposalProjectDetails");
            break;
        case "Projects":
            layout.show(rightSidePanel, "ProjectDetails");
            break;
        case "Funds":
            layout.show(rightSidePanel, "FundDetails");
            break;
        case "Archive":
            layout.show(rightSidePanel, "ArchiveDetails");
            break;
        default:
            layout.show(rightSidePanel, "Default"); // Sikrer standardvisning
            break;
    }
}

@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == projectPropButton) {
        cardLayout.show(cardPanel, "proposalProject");
        updateRightSidePanel("proposalProject");
    } else if (e.getSource() == projectButton) {
        cardLayout.show(cardPanel, "Projects");
        updateRightSidePanel("Projects");
    } else if (e.getSource() == fundsButton) {
        cardLayout.show(cardPanel, "Funds");
        updateRightSidePanel("Funds");
    } else if (e.getSource() == archiveButton) {
        cardLayout.show(cardPanel, "Archive");
        updateRightSidePanel("Archive");
    } else if (e.getSource() == backButton) {
        cardLayout.show(cardPanel, "Main");
    } else if (e.getSource() == createProbButton) {
        openproposalProjectDialog();
    } else if (e.getSource() == createFundButton) {
        openFundDialog(); // New method to open fund dialog
    }
}

// createRightSidePanel-metoden med tilhørende layout og indhold
private JPanel createRightSidePanel() {
    rightSidePanel = new JPanel(new CardLayout());
    rightSidePanel.setBackground(new Color(213, 213, 213, 255));
    rightSidePanel.setPreferredSize(new Dimension(900, 100));
    
    proposalProjectFullPanel = new JPanel();
    proposalProjectFullPanel.setLayout(new BoxLayout(proposalProjectFullPanel, BoxLayout.Y_AXIS));
    JScrollPane proposalScrollPane = new JScrollPane(proposalProjectFullPanel);
    rightSidePanel.add(proposalScrollPane, "proposalProjectDetails");
    
    projectFullPanel = new JPanel();
    projectFullPanel.setLayout(new BoxLayout(projectFullPanel, BoxLayout.Y_AXIS));
    JScrollPane projectScrollPane = new JScrollPane(projectFullPanel);
    rightSidePanel.add(projectScrollPane, "ProjectDetails");
    
    JPanel fundPanel = new JPanel();
    fundPanel.add(new JLabel("Fond Detaljer"));
    rightSidePanel.add(fundPanel, "FundDetails");
    
    JPanel archivePanel = new JPanel();
    archivePanel.add(new JLabel("Arkiv Detaljer"));
    rightSidePanel.add(archivePanel, "ArchiveDetails");
    
    return rightSidePanel;
}

    // Restore the project proposal dialog
    private void openproposalProjectDialog() {
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
            LocalDateTime fromDate = LocalDateTime.parse((fromDateSpinner.getValue()).toString());
            LocalDateTime toDate = LocalDateTime.parse(( toDateSpinner.getValue()).toString());
            String activities = activitiesField.getText();

            // Create a new project proposal and add it to the list
            proposalProject proposal = new proposalProject();
            proposalProjects.add(proposal);
            
            // Update the project proposal panel
            updateproposalProjectList();

            dialog.dispose();
        });

        dialog.add(new JLabel());
        dialog.add(submitButton);

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
    
    
    // Method to update the list and add mouse listeners
    private void updateproposalProjectList() {
        proposalProjectListPanel.removeAll();
    
        for (proposalProject proposal : proposalProjects) {
            JLabel proposalLabel = new JLabel(proposal.getTitle() + " - " + proposal.getProjectOwner());
    
            proposalLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    showProjectProbDetails(proposal); // Show the selected project's details
                }
            });
    
            proposalProjectListPanel.add(proposalLabel);
        }
    
        proposalProjectListPanel.revalidate();
        proposalProjectListPanel.repaint();
    }

    private void updateProjectList() {
            // Ryd panelet før opdatering
            projectListPanel.removeAll();
        
            // Tilføj labels for hvert projekt
            for (project project : projects) {
                JLabel projectLabel = new JLabel(project.getTitle() + " - " + project.getProjectOwner());
        
                // Brug en endelig variabel for at undgå problemer med closures
                final project currentProject = project;
                
                projectLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        showProjectDetails(currentProject); // Vis detaljer for det valgte projekt
                    }
                });
        
                projectListPanel.add(projectLabel);
            }
        
            // Opdater visningen
            projectListPanel.revalidate();
            projectListPanel.repaint();
        }
        
    
     
        

  
    // Method to display the clicked project's details
    private void showProjectProbDetails(proposalProject proposal) {
        proposalProjectFullPanel.removeAll();
    
        // Vis detaljer om projektforslaget
        proposalProjectFullPanel.add(new JLabel("Titel: " + proposal.getTitle()));
        proposalProjectFullPanel.add(new JLabel("Ejer: " + proposal.getProjectOwner()));
        proposalProjectFullPanel.add(new JLabel("Idé: " + proposal.getProjectPurpose()));
        proposalProjectFullPanel.add(new JLabel("Beskrivelse: " + proposal.getDescription()));
        proposalProjectFullPanel.add(new JLabel("Målgruppe: " + proposal.getProjectTargetAudience()));
        proposalProjectFullPanel.add(new JLabel("Budget: " + proposal.getProjectBudget()));
        proposalProjectFullPanel.add(new JLabel("Fra Dato: " + proposal.getProjectTimeSpanFrom().toString()));
        proposalProjectFullPanel.add(new JLabel("Til Dato: " + proposal.getProjectTimeSpanTo().toString()));
        proposalProjectFullPanel.add(new JLabel("Aktiviteter: " + proposal.getProjectActivities()));
    
        // Godkend knap
        JButton approveButton = new JButton("Godkend");
        approveButton.addActionListener(event -> {
            approveProposal(proposal);
            proposalProjectFullPanel.getParent().getParent().remove(proposalProjectFullPanel); // Luk detaljer
            updateproposalProjectList(); // Opdater listen
        });
    
        // Afvis knap
        JButton rejectButton = new JButton("Afvis");
        rejectButton.addActionListener(event -> {
            proposalProjectFullPanel.getParent().getParent().remove(proposalProjectFullPanel); // Luk detaljer
        });
    
        proposalProjectFullPanel.add(approveButton);
        proposalProjectFullPanel.add(rejectButton);
    
        proposalProjectFullPanel.revalidate();
        proposalProjectFullPanel.repaint();
    }
    
    private void approveProposal(proposalProject proposal) {
        System.out.println("Approving proposal: " + proposal.getTitle());
    
        // Check if proposalProjects list is initialized
        if (proposalProjects == null) {
            System.out.println("Error: proposalProjects list is not initialized.");
            return;
        }
    
        // Remove the proposal from the proposalProjects list
        if (proposalProjects.remove(proposal)) {
            System.out.println("Proposal removed: " + proposal.getTitle());
        } else {
            System.out.println("Proposal not found in proposalProjects list: " + proposal.getTitle());
        }
    
        // Create a new projectAbstract instance using proposal details
        project project = new project();
    
        // Set project details based on proposal details
        try {
            project.setTitle(proposal.getTitle());
            project.setProjectPurpose(proposal.getProjectPurpose());
            project.setDescription(proposal.getDescription());
            project.setProjectOwner(proposal.getProjectOwner());
            project.setProjectTargetAudience(proposal.getProjectTargetAudience());
            project.setProjectBudget(proposal.getProjectBudget());
            project.setTimeSpan(proposal.getProjectTimeSpanFrom(), proposal.getProjectTimeSpanTo());
            project.setProjectActivities(proposal.getProjectActivities()); // Make sure proposal.getActivities() returns a valid string or list
    
            // Add the new project to the projects list
            if (projects != null) {
                projects.add(project);
                System.out.println("New project added: " + project.getTitle());
            } else {
                System.out.println("Error: projects list is not initialized.");
            }
    
        } catch (NullPointerException e) {
            System.out.println("Error: One of the proposal properties is null - " + e.getMessage());
        }
    
        // Update the UI to reflect the new project list
        updateProjectList();
    }
    
    
    


    private void showProjectDetails(project project) {
        projectFullPanel.removeAll();
        
        projectFullPanel.add(new JLabel("Titel: " + project.getTitle()));
        projectFullPanel.add(new JLabel("Ejer: " + project.getProjectOwner()));
        projectFullPanel.add(new JLabel("Idé: " + project.getProjectPurpose()));
        projectFullPanel.add(new JLabel("Beskrivelse: " + project.getDescription()));
        projectFullPanel.add(new JLabel("Målgruppe: " + project.getProjectTargetAudience()));
        projectFullPanel.add(new JLabel("Budget: " + project.getProjectBudget()));
        projectFullPanel.add(new JLabel("Fra Dato: " + project.getProjectTimeSpanFrom().toString()));
        projectFullPanel.add(new JLabel("Til Dato: " + project.getProjectTimeSpanTo().toString()));
        projectFullPanel.add(new JLabel("Aktiviteter: " + project.getProjectActivities()));
    
        projectFullPanel.revalidate();
        projectFullPanel.repaint();
    }
    

    private void openFundDialog() {
        JDialog dialog = new JDialog(frame, "Lav en ny fond", true);
        dialog.setSize(700, 600);
        dialog.setLayout(new GridLayout(11, 2, 10, 10));
    
        JLabel nameLabel = new JLabel("Fondens navn:");
        JTextField nameField = new JTextField();
        dialog.add(nameLabel);
        dialog.add(nameField);
    
        JLabel descriptionLabel = new JLabel("Beskrivelse af foden:");
        JTextField descriptionField = new JTextField();
        dialog.add(descriptionLabel);
        dialog.add(descriptionField);
    
        JLabel amountLabel = new JLabel("Fra beløb (kr.):");
        JTextField amountField = new JTextField();
        dialog.add(amountLabel);
        dialog.add(amountField);
    
        JLabel toAmountLabel = new JLabel("Til beløb (kr.):");
        JTextField toAmountField = new JTextField();
        dialog.add(toAmountLabel);
        dialog.add(toAmountField);
    
        JLabel deadlineLabel = new JLabel("Deadline:");
        SpinnerDateModel deadlineModel = new SpinnerDateModel();
        JSpinner deadlineSpinner = new JSpinner(deadlineModel);
        JSpinner.DateEditor deadlineEditor = new JSpinner.DateEditor(deadlineSpinner, "dd/MM/yyyy");
        deadlineSpinner.setEditor(deadlineEditor);
        dialog.add(deadlineLabel);
        dialog.add(deadlineSpinner);
    
        dialog.add(new JLabel("Create Tag:"));
        JButton createTagButton = new JButton("Create Tag");
        dialog.add(createTagButton);
    
        dialog.add(new JLabel("Choose Tags:"));
        JPanel tagPanel = new JPanel();
        tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
        JScrollPane tagScrollPane = new JScrollPane(tagPanel);
        dialog.add(tagScrollPane);
        
        createTagButton.addActionListener(e -> {
            String newTag = JOptionPane.showInputDialog(dialog, "Enter new tag:");
            if (newTag != null && !newTag.trim().isEmpty()) {
                JCheckBox tagCheckBox = new JCheckBox(newTag);
                tagPanel.add(tagCheckBox);
                tagCheckBoxes.add(tagCheckBox); // Store the checkbox for later access
                tagPanel.revalidate();
                tagPanel.repaint();
            }
        });
        
    
        // Checkbox for previous collaboration
        JCheckBox collaborationCheckbox = new JCheckBox("Har I arbejdet sammen før?");
        dialog.add(collaborationCheckbox);
    
        JLabel previousCollabLabel = new JLabel("Tidligere samarbejdspartnere:");
        JTextField previousCollabField = new JTextField();
        previousCollabLabel.setVisible(false);
        previousCollabField.setVisible(false);
    
        dialog.add(previousCollabLabel);
        dialog.add(previousCollabField);
    
        // Show/Hide the previous collaborations field when checkbox is selected/deselected
        collaborationCheckbox.addActionListener(e -> {
            boolean selected = collaborationCheckbox.isSelected();
            previousCollabLabel.setVisible(selected);
            previousCollabField.setVisible(selected);
        });
    

        JButton submitButton = new JButton("Tilføj"); // Create and initialize the button
        submitButton.addActionListener(event -> {
            try {
                String name = nameField.getText().trim();
                String description = descriptionField.getText().trim();
                
                // Parsing amount values safely
                long amount = Long.parseLong(amountField.getText().trim());
                long toAmount = Long.parseLong(toAmountField.getText().trim());
                
                LocalDateTime deadline = LocalDateTime.parse((deadlineSpinner.getValue()).toString());
                String previousCollab = previousCollabField.isVisible() ? previousCollabField.getText().trim() : "";
                
                // Create a new fund and add it to the fund list
                fundClass newFund = new fundClass();
                fundList.add(newFund);
        
                // Update the fund list panel with the new fund
                updateFundList();
                
                dialog.dispose(); // Close the dialog after successfully adding the fund
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Indtast venligst gyldige beløb i numerisk format.", "Input Fejl", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(dialog, "Der opstod en fejl. Prøv igen.", "Fejl", JOptionPane.ERROR_MESSAGE);
            }
        });
        

dialog.add(new JLabel()); // Spacer
dialog.add(submitButton); // Add the initialized button to the dialog

        
    
        dialog.add(new JLabel()); // Spacer
        dialog.add(submitButton);
    
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
    
    
    // Method to update the fund list display
    private void updateFundList() {
        fundListPanel.removeAll();
    
        for (fundClass fund : main.fundList) {
            JLabel fundLabel = new JLabel(fund.getTitle() + " - " + fund.getBudgetMax());
    
            // Add a listener to view fund details when clicked
            fundLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    showFundDetails(fund);
                }
            });
    
            fundListPanel.add(fundLabel);
        }
    
        fundListPanel.revalidate();  // Refreshes the layout
        fundListPanel.repaint();     // Repaints the UI
    }
    
    
    private void showFundDetails(fundClass fund) {
    }
    
    public static void main(String[] args) {
        UserFrame frame = new UserFrame();
        frame.show();
    }
    


}

