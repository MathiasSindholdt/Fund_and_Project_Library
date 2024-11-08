import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.*;
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
    private JPanel proposalProjectListPanel;
    private JPanel proposalProjectFullPanel;
    
    private JPanel projectListPanel;
    private JPanel projectFullPanel;
    
    
    private ArrayList<fundClass> fundList;
    private JPanel fundListPanel;
    private JPanel fundFullPanel;
    // tag button
    private JPanel tagButtonPanel;

    private JPanel rightSidePanel;
    
    private boolean isInvalidLenght;
    String projectTitle;
    String projectDescription;
    String projectPurpose; 
    String projectOwner;
    String projectTargetAudience;
    Long projectBudget;
    LocalDate fromDate;
    LocalDate toDate;
    LocalDateTime projectFromDate;
    LocalDateTime projectToDate;
    String projectActivities;
    ArrayList<String> selectedCatagories;

    // Constructor to set up the GUI
    public UserFrame() {
        
        fundList = new ArrayList<>();
        initializeFrame();  // Initialize JFrame
        //UserFrameErrorHandling ErrorHandling = new UserFrameErrorHandling();

        JPanel panel1 = createTopPanel();  // Top panel
        JPanel panel2 = createSidePanel();  // Left-side panel
        JPanel panel3 = createRightSidePanel();  // Right-side panel

        // Card layout for switching between views
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Adding different views as "cards" to cardPanel
        cardPanel.add(createCenterPanel(), "Main"); // The main view
        cardPanel.add(createproposalProjectView(), "proposalProjects"); // Project Proposal view
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

    //catagory button
    private JButton CreateCatagoryButton(String text){
            JButton button = new JButton(text);
            button.setPreferredSize(new Dimension(130, 50));
            button.addActionListener(this);
            return button;
    }

    // Show the frame
    public void show() {
        frame.setVisible(true);
    }

    

    
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
            main.proposalList.add(proposal);
            updateproposalProjectList();
    
            // Close the dialog
            dialog.dispose();
        });
    

    dialog.add(new JLabel());
    dialog.add(submitButton);

    dialog.setLocationRelativeTo(frame);
    dialog.setVisible(true);
}

private void updateproposalProjectList() {
    proposalProjectListPanel.removeAll();

    for (proposalProject proposal : main.proposalList) {
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
    for (project project : main.projectList) {
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
    if (main.proposalList == null) {
        System.out.println("Error: proposalProjects list is not initialized.");
        return;
    }

    // Remove the proposal from the proposalProjects list
    if (main.proposalList.remove(proposal)) {
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
        if (main.projectList != null) {
            main.projectList.add(project);
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
    
    JPanel mainPanel = new JPanel();
    dialog.add(mainPanel);
    
    GroupLayout layout = new GroupLayout(mainPanel);
    mainPanel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    
    // Fond Titel
    JLabel nameLabel = new JLabel("Fond Titel:");
    JTextField nameField = new JTextField();

    // Fond Beskrivelse
    JLabel descriptionLabel = new JLabel("Beskrivelse:");
    JTextArea descriptionArea = new JTextArea(3, 20);
    JScrollPane scrollPane = new JScrollPane(descriptionArea);
    
    // Fond Beløb Fra og Til
    JLabel amountFromLabel = new JLabel("Beløb fra:");
    JTextField amountFromField = new JTextField();
    JLabel amountToLabel = new JLabel("Beløb til:");
    JTextField amountToField = new JTextField();

    // Fond Deadline
    JLabel deadlineLabel = new JLabel("Deadline:");
    SpinnerDateModel deadlineModel = new SpinnerDateModel();
    JSpinner deadlineSpinner = new JSpinner(deadlineModel);
    JSpinner.DateEditor deadlineEditor = new JSpinner.DateEditor(deadlineSpinner, "dd/MM/yyyy");
    deadlineSpinner.setEditor(deadlineEditor);

    // Tilføjede Deadlines Panel
    JLabel addedDeadlinesLabel = new JLabel("Tilføjede Deadlines:");
    JPanel deadlineListPanel = new JPanel();
    deadlineListPanel.setLayout(new BoxLayout(deadlineListPanel, BoxLayout.Y_AXIS));
    JScrollPane deadlineScrollPane = new JScrollPane(deadlineListPanel);
    deadlineScrollPane.setPreferredSize(new Dimension(200, 100));

    // Knap til at tilføje en ny deadline
    JButton addDeadlineButton = new JButton("Tilføj Deadline");
    
    // List til opbevaring af tilføjede deadlines
    List<LocalDateTime> addedDeadlines = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    addDeadlineButton.addActionListener(e -> {
        LocalDateTime newDeadline = ((java.util.Date) deadlineSpinner.getValue())
            .toInstant()
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDateTime();
        addedDeadlines.add(newDeadline);

        JLabel deadlineLabelItem = new JLabel(newDeadline.format(formatter));
        deadlineListPanel.add(deadlineLabelItem);
        deadlineListPanel.revalidate();
        deadlineListPanel.repaint();
    });

    // Kategori valg
    JLabel tagLabel = new JLabel("Tilføj Kategori:");
    JButton createTagButton = new JButton("Lav Kategori");
    JPanel tagPanel = new JPanel();
    tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
    JScrollPane tagScrollPane = new JScrollPane(tagPanel);
    tagScrollPane.setPreferredSize(new Dimension(200, 100));

    // Action Listener for Create Tag knap
    createTagButton.addActionListener(e -> {
        String newTag = JOptionPane.showInputDialog(dialog, "Enter new tag:");
        if (newTag != null && !newTag.trim().isEmpty()) {
            JCheckBox tagCheckBox = new JCheckBox(newTag);

            if (main.categories.stream().anyMatch(tag -> tag.equalsIgnoreCase(newTag))) {
                tagPanel.add(UserFrameErrorHandling.displayTagError());
            } else {
                main.addNewCatagory(newTag);
                tagPanel.add(tagCheckBox);
                tagPanel.revalidate();
                tagPanel.repaint();
            }
        }
    });
    
    // Kontakt person(er)
    JLabel contactsLabel = new JLabel("Kontakt person(er):");
    JTextField contactsField = new JTextField();

    // Hjemmeside
    JLabel websiteLabel = new JLabel("Hjemmeside:");
    JTextField websiteField = new JTextField();

    // Tidligere samarbejde
    JLabel collaboratedLabel = new JLabel("Tidligere samarbejde?:");
    JCheckBox collaboratedCheckBox = new JCheckBox();
    JPanel collaborationPanel = new JPanel(new GridLayout(1, 2));
    JLabel collaborationLabel = new JLabel("Tidligere samarbejdsprojekter:");
    JTextField collaborationField = new JTextField();
    collaborationPanel.add(collaborationLabel);
    collaborationPanel.add(collaborationField);
    collaborationPanel.setVisible(false);

    collaboratedCheckBox.addItemListener(e -> {
        collaborationPanel.setVisible(collaboratedCheckBox.isSelected());
        dialog.revalidate();
        dialog.repaint();
    });

    // Løbende deadline
    JLabel runningLabel = new JLabel("Løbende deadline:");
    JCheckBox runningCheckBox = new JCheckBox();
    runningCheckBox.addItemListener(e -> {
        deadlineSpinner.setEnabled(!runningCheckBox.isSelected());
    });

    // Submit knap
    JButton submitButton = new JButton("Tilføj Fond");
    submitButton.addActionListener(event -> {
        try {
            String fundName = nameField.getText().trim();
            String fundDescription = descriptionArea.getText().trim();
            long fundAmountFrom = Long.parseLong(amountFromField.getText().trim());
            long fundAmountTo = Long.parseLong(amountToField.getText().trim());
            LocalDateTime[] fundDeadlines;
            boolean running = runningCheckBox.isSelected();

            if (running) {
                fundDeadlines = new LocalDateTime[]{LocalDateTime.of(1970, 1, 1, 0, 0)};
            } else {
                fundDeadlines = addedDeadlines.toArray(new LocalDateTime[0]);
            }

            List<String> selectedCategories = new ArrayList<>();
            for (Component component : tagPanel.getComponents()) {
                if (component instanceof JCheckBox checkbox && checkbox.isSelected()) {
                    selectedCategories.add(checkbox.getText());
                }
            }
            if(validationUtils.isWithinLowerCharLimit(nameField.getText()) == false){
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayTitleError(isInvalidLenght));
            }else if(validationUtils.isValidInput(nameField.getText()) == false){
                isInvalidLenght = false;
                dialog.add(UserFrameErrorHandling.displayTitleError(isInvalidLenght));
            }else{
                tempTitle = nameField.getText().trim();
            }

            if(validationUtils.isWithinUpperCharLimit(descriptionArea.getText()) == false){
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayDescriptionError(isInvalidLenght));
            }else if(validationUtils.isValidDescription(descriptionArea.getText()) == false){
                isInvalidLenght = false;
                dialog.add(UserFrameErrorHandling.displayDescriptionError(isInvalidLenght));
            }else{
                tempDescription = descriptionArea.getText().trim();
            }

            if(validationUtils.isNumericInput(amountFromField.getText()) == false){
                dialog.add(UserFrameErrorHandling.displayAmountFromError());
            }else{
                tempAmountFrom = Long.parseLong(amountFromField.getText().trim());
            }
            if(validationUtils.isNumericInput(amountToField.getText()) == false){
                dialog.add(UserFrameErrorHandling.displayAmountToError());
            }else{
                tempAmountTo = Long.parseLong(amountToField.getText().trim());
            }



            //DEADLINE ERRORHANDLING


            
            LocalDateTime fundDeadline = ((java.util.Date) deadlineSpinner.getValue())
                .toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime();

            //Category Errorhandling
            // ArrayList<String> selectedCatagories = new ArrayList<>();
            // for(Component comp : tagPanel.getComponents()){
            //     if(comp instanceof JCheckBox){
            //         JCheckBox checkBox = (JCheckBox) comp;
            //         if(checkBox.isSelected()){
            //             selectedCatagories.add(checkBox.getText());
            //         }
            //     }
            // }


            //Collaboration Errorhandling
            //ArrayList<String> collaborationHistory = new ArrayList<>();
            //if(collaboratedCheckBox.isSelected() == true){
                //Check if any archived project has been selected
                //if not throw error
                //else add to collaborationHistory
           // }

           //Contacts Errorhandling
            //ArrayList<String> contacts = new ArrayList<>();
            // check if all contact fields are filled
            //if not throw error
            // check if all contact fields are valid(Name = letters only, phone = numbers only, email = valid email)
            //if not throw error
            //else add all contacts to contacts arraylist
            
            //Website Errorhandling
            if(validationUtils.isValidUrl(websiteField.getText()) == false){
                dialog.add(UserFrameErrorHandling.displayWebsiteError());
            }else{
                tempWebsite = websiteField.getText().trim();
            }



            String[] fundCategory = selectedCategories.toArray(new String[0]);
            String[] fundCollaborationHistory = collaboratedCheckBox.isSelected()
                ? new String[]{collaborationField.getText().trim()}
                : new String[0];
            String[] fundContacts = {contactsField.getText().trim()};
            String fundWebsite = websiteField.getText().trim();
            boolean collaborated = collaboratedCheckBox.isSelected();

            fundClass fund = new fundClass(fundName, fundDescription, fundAmountFrom, fundAmountTo,
                    fundDeadlines, fundCategory, fundCollaborationHistory, fundContacts, fundWebsite,
                    collaborated, running);

            fundList.add(fund);
            updateFundList();
            // Add fund to fund list
            //fundClass fund = new fundClass(fundTitle, fundDescription, fundAmountFrom, fundAmountTo,
                 //   new LocalDateTime[]{fundDeadline}, fundCategory, fundCollaborationHistory, fundContacts, fundWebsite,
                 //   collaborated, running);
            
            //main.fundList.add(fund); // Add to list
            updateFundList(); // Update UI

            dialog.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(dialog, "Indtast venligst gyldige tal for beløb.", "Fejl", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dialog, "Der opstod en fejl: " + e.getMessage(), "Fejl", JOptionPane.ERROR_MESSAGE);
        }
    });

    // GroupLayout struktur
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(nameLabel).addComponent(nameField)
        .addComponent(descriptionLabel).addComponent(scrollPane)
        .addComponent(amountFromLabel).addComponent(amountFromField)
        .addComponent(amountToLabel).addComponent(amountToField)
        .addComponent(deadlineLabel).addComponent(deadlineSpinner)
        .addComponent(addedDeadlinesLabel).addComponent(deadlineScrollPane)
        .addComponent(addDeadlineButton)
        .addComponent(tagLabel).addComponent(createTagButton).addComponent(tagScrollPane)
        .addComponent(contactsLabel).addComponent(contactsField)
        .addComponent(websiteLabel).addComponent(websiteField)
        .addComponent(collaboratedLabel).addComponent(collaboratedCheckBox)
        .addComponent(collaborationPanel)
        .addComponent(runningLabel).addComponent(runningCheckBox)
        .addComponent(submitButton)
    );

    layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(nameLabel).addComponent(nameField)
        .addComponent(descriptionLabel).addComponent(scrollPane)
        .addComponent(amountFromLabel).addComponent(amountFromField)
        .addComponent(amountToLabel).addComponent(amountToField)
        .addComponent(deadlineLabel).addComponent(deadlineSpinner)
        .addComponent(addedDeadlinesLabel).addComponent(deadlineScrollPane)
        .addComponent(addDeadlineButton)
        .addComponent(tagLabel).addComponent(createTagButton).addComponent(tagScrollPane)
        .addComponent(contactsLabel).addComponent(contactsField)
        .addComponent(websiteLabel).addComponent(websiteField)
        .addComponent(collaboratedLabel).addComponent(collaboratedCheckBox)
        .addComponent(collaborationPanel)
        .addComponent(runningLabel).addComponent(runningCheckBox)
        .addComponent(submitButton)
    );

    dialog.setLocationRelativeTo(frame);
    dialog.setVisible(true);
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

 // Method to update the fund list display
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

    private void openProjectDialog(){
        JDialog dialog = new JDialog(frame, "Lav Projekt", true);
        dialog.setSize(700, 700);
        dialog.setLayout(new GridLayout(13, 2, 10, 10));

        JLabel nameLabel = new JLabel("Titel:");
        JTextField nameField = new JTextField();
        dialog.add(nameLabel);
        dialog.add(nameField);
    

        JLabel purposeLabel = new JLabel("Formål:");
        JTextField purposeField = new JTextField();
        dialog.add(purposeLabel);
        dialog.add(purposeField);

        JLabel descriptionLabel = new JLabel("Beskrivelse af projektet:");
        JTextArea descriptionArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        dialog.add(descriptionLabel);
        dialog.add(scrollPane);

        JLabel ownerLabel = new JLabel("Ejer af projektet:");
        JTextField ownerField = new JTextField();
        dialog.add(ownerLabel);
        dialog.add(ownerField);
        
        
        JLabel targetLabel = new JLabel("Målgruppe (hvem gavner dette projekt):");
        JTextField targetField = new JTextField();
        dialog.add(targetLabel);
        dialog.add(targetField);

        JLabel budgetLabel = new JLabel("Anslået budget (kr.):");
        JTextField budgetField = new JTextField();
        if(!validationUtils.isNumericInput(budgetField.getText()) /*&& !validationUtils.budgetLenght()*/){
            dialog.add(budgetLabel);
            dialog.add(budgetField);  
        }

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

        //Ved ik om det her skal stå her, skal det ikke være noget som man laver udenfor projektet etc.
        dialog.add(new JLabel("Create Tag:"));
        JButton createTagButton = new JButton("Create Tag");
        dialog.add(createTagButton);

        dialog.add(new JLabel("Vælg relevante katagorier:"));
        JPanel tagPanel = new JPanel();
        tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
        JScrollPane tagScrollPane = new JScrollPane(tagPanel);
        dialog.add(tagScrollPane);

        createTagButton.addActionListener(e -> {
            String newTag = JOptionPane.showInputDialog(dialog, "Enter new tag:");
            if (newTag != null && !newTag.trim().isEmpty()) {
                JCheckBox tagCheckBox = new JCheckBox(newTag);
                if(main.categories.stream().anyMatch(tag -> tag.equalsIgnoreCase(newTag))){
                    tagPanel.add(UserFrameErrorHandling.displayTagError());
                }else{
                    main.addNewCatagory(newTag);
                    tagPanel.add(tagCheckBox);
                    tagPanel.revalidate();
                    tagPanel.repaint();
                }
            }

            //tag button for filtering between the different tags
            JButton tagButton = new JButton(newTag);
            tagButton.addActionListener(tagEvent -> filterproposalProjectssByTag(newTag));
            tagButtonPanel.add(tagButton);
            tagButtonPanel.revalidate();
            tagButtonPanel.repaint();
        });

     
        JButton submitButton = new JButton("Tilføj");
        submitButton.addActionListener((ActionEvent ae) -> {
            try{
                // Get the values from the input fields
                if(validationUtils.isWithinLowerCharLimit(nameField.getText()) == false){
                    isInvalidLenght = true;
                    dialog.add(UserFrameErrorHandling.displayTitleError(isInvalidLenght));
                }else if(validationUtils.isValidInput(nameField.getText()) == false){
                    isInvalidLenght = false;
                    dialog.add(UserFrameErrorHandling.displayTitleError(isInvalidLenght));
                }else{
                    projectTitle = nameField.getText();
                }
             
                if(validationUtils.isWithinLowerCharLimit(purposeField.getText()) == false){
                    isInvalidLenght = true;
                    dialog.add(UserFrameErrorHandling.displayPurposeError(isInvalidLenght));
                }else if(validationUtils.isValidInput(purposeField.getText()) == false){
                    isInvalidLenght = false;
                    dialog.add(UserFrameErrorHandling.displayPurposeError(isInvalidLenght));
                }else{
                    projectPurpose = purposeField.getText();
                }
                
                if(validationUtils.isWithinUpperCharLimit(descriptionArea.getText()) == false){
                    isInvalidLenght = true;
                    dialog.add(UserFrameErrorHandling.displayDescriptionError(isInvalidLenght));
                }else if(validationUtils.isValidDescription(descriptionArea.getText()) == false){
                    isInvalidLenght = false;
                    dialog.add(UserFrameErrorHandling.displayDescriptionError(isInvalidLenght));
                }else{
                    projectDescription = descriptionArea.getText();
                }

                if(validationUtils.isWithinLowerCharLimit(ownerField.getText()) == false){
                    isInvalidLenght = true;
                    dialog.add(UserFrameErrorHandling.displayOwnerError(isInvalidLenght));
                }else if(validationUtils.isValidInput(ownerField.getText()) == false){
                    isInvalidLenght = false;
                    dialog.add(UserFrameErrorHandling.displayOwnerError(isInvalidLenght));
                }else{
                    projectOwner = ownerField.getText();
                }

                if(validationUtils.isWithinLowerCharLimit(targetField.getText()) == false){
                    isInvalidLenght = true;
                    dialog.add(UserFrameErrorHandling.displayTargetAudienceError(isInvalidLenght));
                }else if(validationUtils.isValidInput(targetField.getText()) == false){
                    isInvalidLenght = false;
                    dialog.add(UserFrameErrorHandling.displayTargetAudienceError(isInvalidLenght));
                }else{
                    projectTargetAudience = targetField.getText();
                }

                if(validationUtils.isNumericInput(budgetField.getText()) == false){
                    dialog.add(UserFrameErrorHandling.displayBudgetError());
                }else{
                    projectBudget = Long.parseLong(budgetField.getText());
                }

                //ERRORHANDLING FOR DATE TYPE SHIT
                /*
                 * if(validDateFormat == false){
                 * invalidDate = true
                 * display invalid format error
                 * }else if(invalid character){
                 * invalidDate = false
                 * display invalid date character error
                 * } else{
                 *  LocalDate fromDate = ((Date) ((fromDateSpinner.getValue()))).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate toDate = ((Date) (toDateSpinner.getValue())).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDateTime projectFromDate = fromDate.atStartOfDay();
                    LocalDateTime projectToDate = toDate.atStartOfDay();
                 * }

                 */

        
                LocalDate fromDate = ((Date) ((fromDateSpinner.getValue()))).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = ((Date) (toDateSpinner.getValue())).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime projectFromDate = fromDate.atStartOfDay();
                LocalDateTime projectToDate = toDate.atStartOfDay();
               
                if(validationUtils.isWithinLowerCharLimit(activitiesField.getText()) == false){
                    isInvalidLenght = true;
                    dialog.add(UserFrameErrorHandling.displayActivityError(isInvalidLenght));
                } else if(validationUtils.isValidInput(activitiesField.getText()) == false){
                    isInvalidLenght = false; 
                    dialog.add(UserFrameErrorHandling.displayActivityError(isInvalidLenght));
                }else{
                    projectActivities = activitiesField.getText();
                }

                ArrayList<String> selectedCatagories = new ArrayList<>();
                for(Component comp : tagPanel.getComponents()){
                    if(comp instanceof JCheckBox){
                        JCheckBox checkBox = (JCheckBox) comp;
                        if(checkBox.isSelected()){
                            selectedCatagories.add(checkBox.getText());
                        }
                    }
                }
                
                System.out.println(projectFromDate);
                System.out.println(projectToDate);
                // Create a new project proposal and add it to the list
    
                project project = new project(projectTitle, selectedCatagories, projectDescription, projectPurpose ,projectOwner, projectTargetAudience, projectBudget, projectFromDate, projectToDate, projectActivities, main.getFundList(), main.getCatagoryBoolean());
                main.projectList.add(project);
                System.out.println("------------");
                System.out.println("adding project");
                for(project proj : main.projectList){
                    System.out.println(proj.getTitle());
                    System.out.println(proj.getCategories());
                    System.out.println(proj.getDescription());
                    System.out.println(proj.getProjectPurpose());
                    System.out.println(proj.getProjectOwner());
                    System.out.println(proj.getProjectTargetAudience());
                    System.out.println(proj.getProjectBudget());
                    System.out.println(proj.getProjectTimeSpanFrom());
                    System.out.println(proj.getProjectTimeSpanTo());
                    System.out.println(proj.getProjectActivities());
                    System.out.println(proj.getClosestDeadlineFunds());
                    System.out.println(proj.getCategories());

                }
                // Update the project proposal panel
                //updateProjectList();
    
                dialog.dispose();
            }catch (Exception e){
                System.err.println("error when adding project");
                e.printStackTrace();
            }
        });

        dialog.add(new JLabel());
        dialog.add(submitButton);

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);

    }

    private JPanel createRightSidePanel() {
        rightSidePanel = new JPanel(new CardLayout());
        rightSidePanel.setBackground(new Color(213, 213, 213, 255));
        rightSidePanel.setPreferredSize(new Dimension(900, 100));
        
        proposalProjectFullPanel = new JPanel();
        proposalProjectFullPanel.setLayout(new BoxLayout(proposalProjectFullPanel, BoxLayout.Y_AXIS));
        JScrollPane proposalScrollPane = new JScrollPane(proposalProjectFullPanel);
        rightSidePanel.add(proposalScrollPane, "proposalProjectsDetails");
        
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

    

    // method to filther by tag
    private void filterproposalProjectssByTag(String tag) {
        proposalProjectListPanel.removeAll();
        
        for (proposalProject proposal : main.proposalList) {
            if (proposal.getCategories().contains(tag)) {
                JLabel proposalLabel = new JLabel(proposal.getTitle() + " - " + proposal.getProjectOwner());
    
                proposalLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        showProjectProbDetails(proposal);
                    }
                });
    
                proposalProjectListPanel.add(proposalLabel);
            }
        }
    
        proposalProjectListPanel.revalidate();
        proposalProjectListPanel.repaint();
    }
    
   
    private void updateRightSidePanel(String tab) {
        CardLayout layout = (CardLayout) rightSidePanel.getLayout();
        System.out.println(tab);
        switch (tab) {
            case "proposalProjects":
                layout.show(rightSidePanel, "proposalProjectsDetails");
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
        System.out.println("Action performed");
        System.out.println(e.getSource());
        if (e.getSource() == projectPropButton) {
            cardLayout.show(cardPanel, "proposalProjects");
            updateRightSidePanel("proposalProjects");
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
        }else if(e.getSource() == createProjectButton){
            openProjectDialog();
            
    }
 
}
}
