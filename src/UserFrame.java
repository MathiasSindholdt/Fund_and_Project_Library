import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
    
    
    // tag button

    private JPanel tagButtonPanel;


    private ArrayList<fundClass> fundList;
    private JPanel fundListPanel;
    private JPanel fundFullPanel;

    private JPanel rightSidePanel;
    
    List<JCheckBox> tagCheckBoxes = new ArrayList<>(); // Stores checkboxes for dynamic tags


    
    // Constructor to set up the GUI
    public UserFrame() {
        
        fundList = new ArrayList<>();
        initializeFrame();  // Initialize JFrame

        proposalProjects = new ArrayList<>();  // Initialize the project proposals list
        projects = new ArrayList<>(); 

        JPanel panel1 = createTopPanel();  // Top panel
        JPanel panel2 = createSidePanel();  // Left-side panel
        JPanel panel3 = createRightSidePanel();  // Right-side panel
        
        fundList = new ArrayList<>();

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
    
    
        // Initialize the project list panel
        projectListPanel = new JPanel();
        projectListPanel.setLayout(new BoxLayout(projectListPanel, BoxLayout.Y_AXIS)); // Ensure layout is set
    
        JScrollPane scrollPane = new JScrollPane(projectListPanel); // Add project list panel in a scroll pane
        panel.add(scrollPane, BorderLayout.CENTER);


        return panel;
    }


    private JPanel createFundsView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Fonde", SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);
    
        
        // Initialize fund list panel
        fundListPanel = new JPanel();
        fundListPanel.setLayout(new BoxLayout(fundListPanel, BoxLayout.Y_AXIS));
        
        // Create a scroll pane for the fund list
        JScrollPane scrollPane = new JScrollPane(fundListPanel);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Initialize fund list panel
        fundListPanel = new JPanel();
        fundListPanel.setLayout(new BoxLayout(fundListPanel, BoxLayout.Y_AXIS));
        
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
    }else if (e.getSource() == createFundButton) {
        openFundDialog();
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
    
    fundFullPanel = new JPanel();
    fundFullPanel.setLayout(new BoxLayout(fundFullPanel, BoxLayout.Y_AXIS));
    JScrollPane fundScrollPane = new JScrollPane(fundFullPanel);
    rightSidePanel.add(fundScrollPane, "FundDetails");
    
    JPanel archivePanel = new JPanel();
    archivePanel.add(new JLabel("Arkiv Detaljer"));
    rightSidePanel.add(archivePanel, "ArchiveDetails");
    
    return rightSidePanel;
}

    // Restore the project proposal dialog
    private void openproposalProjectDialog() {
        JDialog dialog = new JDialog(frame, "Lav Projekt Forslag", true);
        dialog.setSize(700, 700);
        dialog.setLayout(new GridLayout(12, 2, 10, 10));
    
        JLabel nameLabel = new JLabel("Titel:");
        JTextField nameField = new JTextField();
        dialog.add(nameLabel);
        dialog.add(nameField);
    
        JLabel ideaLabel = new JLabel("Idé/Formål:");
        JTextField ideaField = new JTextField();
        dialog.add(ideaLabel);
        dialog.add(ideaField);
    
        JLabel descriptionLabel = new JLabel("Kort beskrivelse af projektet:");
        JTextArea descriptionArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        dialog.add(descriptionLabel);
        dialog.add(scrollPane);
    
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
    
        // "Create Tag" button
        dialog.add(new JLabel("Opret Kategori:"));
        JButton createTagButton = new JButton("Opret Kategori");
        dialog.add(createTagButton);
    
        // Tag selection panel (scrollable)
        dialog.add(new JLabel("Vælg relevante kategorier:"));
        JPanel tagPanel = new JPanel();
        tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
        JScrollPane tagScrollPane = new JScrollPane(tagPanel);
        dialog.add(tagScrollPane);
    
        // Populate tagPanel with existing categories as checkboxes
        for (String category : main.categories) {
            JCheckBox tagCheckBox = new JCheckBox(category);
            tagPanel.add(tagCheckBox);
        }
    
        // Action Listener for Create Tag button
        createTagButton.addActionListener(e -> {
            String newTag = JOptionPane.showInputDialog(dialog, "Indtast ny kategori:");
            if (newTag != null && !newTag.trim().isEmpty()) {
                JCheckBox tagCheckBox = new JCheckBox(newTag);
    
                // Check for duplicate category
                if (main.categories.stream().anyMatch(tag -> tag.equalsIgnoreCase(newTag))) {
                    tagPanel.add(UserFrameErrorHandling.displayTagError());
                } else {
                    main.addNewCatagory(newTag); // Add to main category list
                    tagPanel.add(tagCheckBox); // Add checkbox for new tag
                    tagPanel.revalidate();
                    tagPanel.repaint();
                }
            }
        });
    
        JButton submitButton = new JButton("Tilføj");
        submitButton.addActionListener(event -> {
            String name = nameField.getText();
            String idea = ideaField.getText();
            String description = descriptionArea.getText();
            String owner = ownerField.getText();
            String target = targetField.getText();
            long budget;
            LocalDateTime fromDate;
            LocalDateTime toDate;
            String activities = activitiesField.getText();
    
            try {
                // Parse the budget
                budget = Long.parseLong(budgetField.getText());
    
                // Get Date from JSpinner
                Date fromDateValue = (Date) fromDateSpinner.getValue();
                Date toDateValue = (Date) toDateSpinner.getValue();
    
                // Convert Dates to LocalDateTime
                fromDate = fromDateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                toDate = toDateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    
                // Validate date range
                if (fromDate.isAfter(toDate)) {
                    JOptionPane.showMessageDialog(dialog, "Fra dato skal være før Til dato.", "Ugyldig datointerval", JOptionPane.ERROR_MESSAGE);
                    return;
                }
    
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Budget skal være et gyldigt nummer.", "Ugyldig indtastning", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Fejl ved parsing af datoer. Kontroller venligst dine indtastninger.", "Ugyldig dato", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
                return;
            }
    
            // Collect selected categories
            ArrayList<String> selectedCategories = new ArrayList<>();
            for (Component component : tagPanel.getComponents()) {
                if (component instanceof JCheckBox checkbox && checkbox.isSelected()) {
                    selectedCategories.add(checkbox.getText());
                }
            }
    
            // Create the proposal project instance with categories
            proposalProject proposal = new proposalProject(
                name,
                selectedCategories,  // Add selected categories to proposal
                description,
                idea,
                owner,
                target,
                budget,
                fromDate,
                toDate,
                activities
            );
    
            // Add the proposal to the list and update UI
            proposalProjects.add(proposal);
            updateproposalProjectList();
    
            // Close the dialog
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
    
        // Display project proposal details
        proposalProjectFullPanel.add(new JLabel("Titel: " + proposal.getTitle()));
        proposalProjectFullPanel.add(new JLabel("Ejer: " + proposal.getProjectOwner()));
        proposalProjectFullPanel.add(new JLabel("Idé: " + proposal.getProjectPurpose()));
        proposalProjectFullPanel.add(new JLabel("Beskrivelse: " + proposal.getDescription()));
        proposalProjectFullPanel.add(new JLabel("Målgruppe: " + proposal.getProjectTargetAudience()));
        proposalProjectFullPanel.add(new JLabel("Budget: " + proposal.getProjectBudget()));
    
        // Display date range
        proposalProjectFullPanel.add(new JLabel("Fra Dato: " + proposal.getProjectTimeSpanFrom().toString()));
        proposalProjectFullPanel.add(new JLabel("Til Dato: " + proposal.getProjectTimeSpanTo().toString()));
    
        // Display project activities
        proposalProjectFullPanel.add(new JLabel("Aktiviteter: " + proposal.getProjectActivities()));
    
        // Display categories as a concatenated string
        String categories = String.join(", ", proposal.getCategories());
        proposalProjectFullPanel.add(new JLabel("Kategorier: " + categories));
    
        JButton approveButton = new JButton("Godkend");
        approveButton.addActionListener(event -> {
            approveProposal(proposal); // Approve the proposal and convert it to a project
            proposalProjectFullPanel.getParent().getParent().remove(proposalProjectFullPanel); // Close details
            updateproposalProjectList(); // Refresh proposal list
            proposalProjectFullPanel.removeAll();
            proposalProjectFullPanel.repaint();
            proposalProjectFullPanel.revalidate();

        });
        

        // Reject button
        JButton rejectButton = new JButton("Afvis");
        rejectButton.addActionListener(event -> {
            proposalProjectFullPanel.getParent().getParent().remove(proposalProjectFullPanel); // Close details
        });
    
        // Add buttons to the panel
        proposalProjectFullPanel.add(approveButton);
        proposalProjectFullPanel.add(rejectButton);
    
        // Refresh the panel to reflect the changes
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
    
        // Create a new project instance using proposal details
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
            project.setProjectActivities(proposal.getProjectActivities());
    
            // Set categories from the proposal, ensuring the project has the same categories
            for (String category : proposal.getCategories()) {
                project.setCategories(category); // Assuming setCategories handles adding categories to the project
            }
    
            // Add the new project to the projects list if initialized
            if (projects != null) {
                projects.add(project);
                System.out.println("New project added: " + project.getTitle());
            } else {
                System.out.println("Error: projects list is not initialized.");
            }
    
        } catch (NullPointerException e) {
            System.out.println("Error: One of the proposal properties is null - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Unexpected issue while approving proposal - " + e.getMessage());
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
        JDialog dialog = new JDialog(frame, "Lav En Fond", true);
        dialog.setSize(700, 700);
        dialog.setLayout(new GridLayout(13, 2, 10, 10));
    
        // Fond Titel
        JLabel nameLabel = new JLabel("Fond Titel:");
        JTextField nameField = new JTextField();
        dialog.add(nameLabel);
        dialog.add(nameField);
    
        // Fond Beskrivelse
        JLabel descriptionLabel = new JLabel("Beskrivelse:");
        JTextArea descriptionArea = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        dialog.add(descriptionLabel);
        dialog.add(scrollPane);
    
        // Fond Beløb Fra
        JLabel amountFromLabel = new JLabel("Beløb fra:");
        JTextField amountFromField = new JTextField();
        dialog.add(amountFromLabel);
        dialog.add(amountFromField);
    
        // Fond Beløb Til
        JLabel amountToLabel = new JLabel("Beløb til:");
        JTextField amountToField = new JTextField();
        dialog.add(amountToLabel);
        dialog.add(amountToField);
    
        // Fond Deadline
        JLabel deadlineLabel = new JLabel("Deadline:");
        SpinnerDateModel deadlineModel = new SpinnerDateModel();
        JSpinner deadlineSpinner = new JSpinner(deadlineModel);
        JSpinner.DateEditor deadlineEditor = new JSpinner.DateEditor(deadlineSpinner, "dd/MM/yyyy");
        deadlineSpinner.setEditor(deadlineEditor);
        dialog.add(deadlineLabel);
        dialog.add(deadlineSpinner);
    
        // "Create Tag" knap
        dialog.add(new JLabel("Create Tag:"));
        JButton createTagButton = new JButton("Create Tag");
        dialog.add(createTagButton);
    
        // Tag udvalgspanel (scrollbar og dynamisk tilføjede checkboxes)
        dialog.add(new JLabel("Vælg relevante kategorier:"));
        JPanel tagPanel = new JPanel();
        tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
        JScrollPane tagScrollPane = new JScrollPane(tagPanel);
        dialog.add(tagScrollPane);
    
        // Action Listener for Create Tag knap
        createTagButton.addActionListener(e -> {
            String newTag = JOptionPane.showInputDialog(dialog, "Enter new tag:");
            if (newTag != null && !newTag.trim().isEmpty()) {
                JCheckBox tagCheckBox = new JCheckBox(newTag);
    
                // Check om tag allerede findes
                if (main.categories.stream().anyMatch(tag -> tag.equalsIgnoreCase(newTag))) {
                    tagPanel.add(UserFrameErrorHandling.displayTagError());
                } else {
                    main.addNewCatagory(newTag); // Tilføj til hovedkategori liste
                    tagPanel.add(tagCheckBox); // Tilføj checkbox for nyt tag
                    tagPanel.revalidate();
                    tagPanel.repaint();
                }
            }
        });
    
        // Fond Kontaktpersoner
        JLabel contactsLabel = new JLabel("Kontakt person(er):");
        JTextField contactsField = new JTextField();
        dialog.add(contactsLabel);
        dialog.add(contactsField);
    
        // Fond Hjemmeside
        JLabel websiteLabel = new JLabel("Hjemmeside:");
        JTextField websiteField = new JTextField();
        dialog.add(websiteLabel);
        dialog.add(websiteField);
    
        // Tidligere Samarbejde (checkbox)
        JLabel collaboratedLabel = new JLabel("Tidligere samarbejde?:");
        JCheckBox collaboratedCheckBox = new JCheckBox();
        dialog.add(collaboratedLabel);
        dialog.add(collaboratedCheckBox);
    
        // Tidligere samarbejdsprojekter (skjult som udgangspunkt)
        JPanel collaborationPanel = new JPanel(new GridLayout(1, 2));
        JLabel collaborationLabel = new JLabel("Tidligere samarbejdsprojekter:");
        JTextField collaborationField = new JTextField();
        collaborationPanel.add(collaborationLabel);
        collaborationPanel.add(collaborationField);
        collaborationPanel.setVisible(false);
    
        // Vis samarbejdsfelt hvis checkbox er valgt
        collaboratedCheckBox.addItemListener(e -> {
            collaborationPanel.setVisible(collaboratedCheckBox.isSelected());
            dialog.revalidate();
            dialog.repaint();
        });
    
        dialog.add(collaborationPanel);
    
        // Løbende deadline (checkbox) og deaktivering af deadline-spinner
        JLabel runningLabel = new JLabel("Løbende deadline:");
        JCheckBox runningCheckBox = new JCheckBox();
        dialog.add(runningLabel);
        dialog.add(runningCheckBox);
    
        // Deaktiver deadline-spinner hvis "Løbende deadline" er valgt
        runningCheckBox.addItemListener(e -> {
            deadlineSpinner.setEnabled(!runningCheckBox.isSelected());
        });
    
        // Submit knap
        JButton submitButton = new JButton("Tilføj Fond");
        submitButton.addActionListener(event -> {
            try {
                // Indhent værdier og opret ny fond-instans
                String fundName = nameField.getText().trim();
                String fundDescription = descriptionArea.getText().trim();
                long fundAmountFrom = Long.parseLong(amountFromField.getText().trim());
                long fundAmountTo = Long.parseLong(amountToField.getText().trim());
    
                LocalDateTime fundDeadline = ((java.util.Date) deadlineSpinner.getValue())
                    .toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
    
                // Indsamling af valgte kategorier fra checkboxes
                List<String> selectedCategories = new ArrayList<>();
                for (Component component : tagPanel.getComponents()) {
                    if (component instanceof JCheckBox checkbox && checkbox.isSelected()) {
                        selectedCategories.add(checkbox.getText());
                    }
                }
    
                String[] fundCategory = selectedCategories.toArray(new String[0]);
                String[] fundCollaborationHistory = collaboratedCheckBox.isSelected()
                    ? new String[]{collaborationField.getText().trim()}
                    : new String[0];
                String[] fundContacts = {contactsField.getText().trim()};
                String fundWebsite = websiteField.getText().trim();
                boolean collaborated = collaboratedCheckBox.isSelected();
                boolean running = runningCheckBox.isSelected();
    
                // Opret og tilføj ny fond til listen
                fundClass fund = new fundClass(fundName, fundDescription, fundAmountFrom, fundAmountTo,
                        new LocalDateTime[]{fundDeadline}, fundCategory, fundCollaborationHistory, fundContacts, fundWebsite,
                        collaborated, running);
    
                fundList.add(fund);
                updateFundList(); // Opdater brugergrænsefladen
    
                dialog.dispose();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(dialog, "Indtast venligst gyldige tal for beløb.", "Fejl", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(dialog, "Der opstod en fejl: " + e.getMessage(), "Fejl", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        dialog.add(new JLabel());
        dialog.add(submitButton);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
    
    
    private void updateFundList() {
        fundListPanel.removeAll();  // Clear existing funds
    
        for (fundClass fund : fundList) {
            JLabel fundLabel = new JLabel(fund.getTitle() + " - " + fund.getCategories());
            fundLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    showFundDetails(fund);  // Display details on click
                }
            });
            fundListPanel.add(fundLabel);
        }
        
        fundListPanel.revalidate();
        fundListPanel.repaint();
    }

    private void showFundDetails(fundClass fund) {
        fundFullPanel.removeAll();
    
        fundFullPanel.add(new JLabel("Navn: " + fund.getTitle()));
        fundFullPanel.add(new JLabel("Beskrivelse: " + fund.getDescription()));
        fundFullPanel.add(new JLabel("Beløb Fra: " + fund.getBudgetMin()));
        fundFullPanel.add(new JLabel("Beløb Til: " + fund.getBudgetMax()));
        fundFullPanel.add(new JLabel("Deadline: " + fund.getDeadlines()));
        fundFullPanel.add(new JLabel("Løbende: " + fund.getRunning()));
        fundFullPanel.add(new JLabel("Kategori: " + fund.getCategories()));
        fundFullPanel.add(new JLabel("Tidligere samarbejde: " + String.join(", ", fund.getCollaborationHistory())));
        fundFullPanel.add(new JLabel("Kontaktperson(er): " + fund.getContacts()));
        fundFullPanel.add(new JLabel("Hjemmeside: " + fund.getFundWebsite()));
    
        fundFullPanel.revalidate();
        fundFullPanel.repaint();
    }
    public static void main(String[] args) {
        UserFrame frame = new UserFrame();
        frame.show();
    }
}