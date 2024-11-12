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
    
        // Title label
        JLabel label = new JLabel("Arkiv");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(label);
    
        // Buttons for "Proposals," "Projects," and "Funds"
        JButton proposalsButton = new JButton("Arkiveret projektforslag");
        JButton projectsButton = new JButton("Arkiveret projekter");
        JButton fundsButton = new JButton("Arkiveret fonde");
    
        // Set button sizes (optional, adjust as needed)
        Dimension buttonSize = new Dimension(150, 50);
        proposalsButton.setPreferredSize(buttonSize);
        projectsButton.setPreferredSize(buttonSize);
        fundsButton.setPreferredSize(buttonSize);
    
        // Add action listeners to each button to display respective archives
        proposalsButton.addActionListener(e -> displayArchiveList("proposalProjectsDetails", main.archiveProposalList));
        projectsButton.addActionListener(e -> displayArchiveList("ProjectDetails", main.archiveProjectList));
        fundsButton.addActionListener(e -> displayArchiveList("FundDetails", main.archiveFundList));
    
        // Layout buttons in a single row
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));  // Spacer
        panel.add(proposalsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));  // Spacer
        panel.add(projectsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));  // Spacer
        panel.add(fundsButton);
    
        return panel;
    }

    // Creates the right-side panel with different views for each archive type

    // Method to update the right-side panel based on selected archive type
    private <T> void displayArchiveList(String cardName, List<T> archiveList) {
        JPanel targetPanel;
    
        // Determine which panel to update based on card name
        switch (cardName) {
            case "proposalProjectsDetails":
                targetPanel = proposalProjectFullPanel;
                break;
            case "ProjectDetails":
                targetPanel = projectFullPanel;
                break;
            case "FundDetails":
                targetPanel = fundFullPanel;
                break;
            default:
                throw new IllegalArgumentException("Invalid card name");
        }
    
        // Clear the panel and populate it with clickable items from the archive list
        targetPanel.removeAll();
        for (T item : archiveList) {
            String displayText;
            
            // Get the title of each item
            if (item instanceof project) {
                displayText = ((project) item).getTitle();
            } else if (item instanceof proposalProject) {
                displayText = ((proposalProject) item).getTitle();
            } else if (item instanceof fundClass) {
                displayText = ((fundClass) item).getTitle();
            } else {
                displayText = item.toString();
            }
    
            // Create a JButton for each item to make it clickable
            JButton itemButton = new JButton(displayText);
            
            // Add an ActionListener to display item details when clicked
            itemButton.addActionListener(e -> displayItemDetails(item));
            
            targetPanel.add(itemButton);
            targetPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer between items
        }
        
        // Refresh the panel and switch to the selected archive view
        targetPanel.revalidate();
        targetPanel.repaint();
        CardLayout cardLayout = (CardLayout) rightSidePanel.getLayout();
        cardLayout.show(rightSidePanel, cardName);
    }
    
    
    private <T> void displayItemDetails(T item) {
        // Create a new panel or dialog to show item details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
    
        // Display details based on the type of item
        if (item instanceof project) {
            project proj = (project) item;
            detailsPanel.add(new JLabel("Titel: " + proj.getTitle()));
            detailsPanel.add(new JLabel("Beskrivelse: " + proj.getDescription()));
            detailsPanel.add(new JLabel("Kategorier: " + proj.getCategories()));
            detailsPanel.add(new JLabel("Ide/Formål: " + proj.getProjectPurpose()));
            detailsPanel.add(new JLabel("Ejer: " + proj.getProjectOwner()));
            detailsPanel.add(new JLabel("Målgruppe: " + proj.getProjectTargetAudience()));
            detailsPanel.add(new JLabel("Aktiviter: " + proj.getProjectActivities()));
            detailsPanel.add(new JLabel("\n"));

            detailsPanel.add(new JLabel("Dato fra: " + proj.getProjectTimeSpanFrom()));
            detailsPanel.add(new JLabel("Dato til: " + proj.getProjectTimeSpanTo()));
            detailsPanel.add(new JLabel("\n"));

            detailsPanel.add(new JLabel("Dato lavet: " + proj.getDateCreated()));





<<<<<<< HEAD
=======
            // Add more fields as needed
>>>>>>> fc8f243 (Created archive button functionality with funds,)
        } else if (item instanceof proposalProject) {
            proposalProject proposal = (proposalProject) item;
            detailsPanel.add(new JLabel("Titel: " + proposal.getTitle()));
            detailsPanel.add(new JLabel("Beskrivelse: " + proposal.getDescription()));
            detailsPanel.add(new JLabel("Kategorier: " + proposal.getCategories()));
            detailsPanel.add(new JLabel("Ide/Formål: " + proposal.getProjectPurpose()));
            detailsPanel.add(new JLabel("Ejer: " + proposal.getProjectOwner()));
            detailsPanel.add(new JLabel("Målgruppe: " + proposal.getProjectTargetAudience()));
            detailsPanel.add(new JLabel("Aktiviter: " + proposal.getProjectActivities()));
            detailsPanel.add(new JLabel("\n"));

            detailsPanel.add(new JLabel("Dato fra: " + proposal.getProjectTimeSpanFrom()));
            detailsPanel.add(new JLabel("Dato til: " + proposal.getProjectTimeSpanTo()));
            detailsPanel.add(new JLabel("\n"));

            detailsPanel.add(new JLabel("Dato lavet: " + proposal.getDateCreated()));




<<<<<<< HEAD
=======
            // Add more fields as needed
>>>>>>> fc8f243 (Created archive button functionality with funds,)
        } else if (item instanceof fundClass) {
            fundClass fund = (fundClass) item;
            detailsPanel.add(new JLabel("Titel: " + fund.getTitle()));
            detailsPanel.add(new JLabel("Beskrivelse: " + fund.getDescription()));
            detailsPanel.add(new JLabel("Kategorier: " + fund.getCategories()));
            detailsPanel.add(new JLabel("Deadline(s): " + fund.getDeadlines()));
            detailsPanel.add(new JLabel("Kontakter: " + fund.getContacts()));
            detailsPanel.add(new JLabel("Budget span: " + fund.getBudgetSpan()));
            detailsPanel.add(new JLabel("Tidligere samarbejde: " + fund.getCollaborationHistory()));
            detailsPanel.add(new JLabel("Hjemmeside: " + fund.getFundWebsite()));
            detailsPanel.add(new JLabel("\n"));
            detailsPanel.add(new JLabel("Dato lavet: " + fund.getDateCreated()));






        } else {
            detailsPanel.add(new JLabel("Details: " + item.toString()));
        }
    
        // Show details in a dialog box
        JOptionPane.showMessageDialog(null, detailsPanel, "Item Details", JOptionPane.INFORMATION_MESSAGE);
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
    
        JPanel mainPanel = new JPanel();
        dialog.add(mainPanel);
    
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
    
        JLabel nameLabel = new JLabel("Titel:");
        JTextField nameField = new JTextField();
    
        JLabel ideaLabel = new JLabel("Idé/Formål:");
        JTextField ideaField = new JTextField();
    
        JLabel descriptionLabel = new JLabel("Kort beskrivelse af projektet:");
        JTextArea descriptionArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
    
        JLabel ownerLabel = new JLabel("Ejer af idé/forslaget:");
        JTextField ownerField = new JTextField();
    
        JLabel targetLabel = new JLabel("Målgruppe (hvem gavner dette forslag):");
        JTextField targetField = new JTextField();
    
        JLabel budgetLabel = new JLabel("Anslået budget (kr.):");
        JTextField budgetField = new JTextField();
    
        JLabel fromDateLabel = new JLabel("Fra dato:");
        SpinnerDateModel fromDateModel = new SpinnerDateModel();
        JSpinner fromDateSpinner = new JSpinner(fromDateModel);
        JSpinner.DateEditor fromDateEditor = new JSpinner.DateEditor(fromDateSpinner, "dd/MM/yyyy");
        fromDateSpinner.setEditor(fromDateEditor);
    
        JLabel toDateLabel = new JLabel("Til dato:");
        SpinnerDateModel toDateModel = new SpinnerDateModel();
        JSpinner toDateSpinner = new JSpinner(toDateModel);
        JSpinner.DateEditor toDateEditor = new JSpinner.DateEditor(toDateSpinner, "dd/MM/yyyy");
        toDateSpinner.setEditor(toDateEditor);
    
        JLabel activitiesLabel = new JLabel("Aktiviteter:");
        JTextField activitiesField = new JTextField();
    
        // "Create Tag" label and button
        JLabel createTagLabel = new JLabel("Opret Kategori:");
        JButton createTagButton = new JButton("Opret Kategori");
    
        // Tag selection panel (scrollable)
        JLabel selectTagLabel = new JLabel("Vælg relevante kategorier:");
        JPanel tagPanel = new JPanel();
        tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
        JScrollPane tagScrollPane = new JScrollPane(tagPanel);
    
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
    
        // Define layout structure
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(nameLabel).addComponent(nameField)
            .addComponent(ideaLabel).addComponent(ideaField)
            .addComponent(descriptionLabel).addComponent(scrollPane)
            .addComponent(ownerLabel).addComponent(ownerField)
            .addComponent(targetLabel).addComponent(targetField)
            .addComponent(budgetLabel).addComponent(budgetField)
            .addComponent(fromDateLabel).addComponent(fromDateSpinner)
            .addComponent(toDateLabel).addComponent(toDateSpinner)
            .addComponent(activitiesLabel).addComponent(activitiesField)
            .addComponent(createTagLabel).addComponent(createTagButton)
            .addComponent(selectTagLabel).addComponent(tagScrollPane)
            .addComponent(submitButton)
        );
    
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(nameLabel).addComponent(nameField)
            .addComponent(ideaLabel).addComponent(ideaField)
            .addComponent(descriptionLabel).addComponent(scrollPane)
            .addComponent(ownerLabel).addComponent(ownerField)
            .addComponent(targetLabel).addComponent(targetField)
            .addComponent(budgetLabel).addComponent(budgetField)
            .addComponent(fromDateLabel).addComponent(fromDateSpinner)
            .addComponent(toDateLabel).addComponent(toDateSpinner)
            .addComponent(activitiesLabel).addComponent(activitiesField)
            .addComponent(createTagLabel).addComponent(createTagButton)
            .addComponent(selectTagLabel).addComponent(tagScrollPane)
            .addComponent(submitButton)
        );
    
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
   
    JButton archiveButton = new JButton("Arkivér");
    Dimension buttonSize = new Dimension(150, 50); 
    archiveButton.setPreferredSize(buttonSize);
    
    archiveButton.addActionListener(e -> {
        // Archive the project
        archive.archiveProposal(proposal);

        // Call update methods after archiving
        updateproposalProjectList();
        proposalProjectFullPanel.removeAll(); 
        proposalProjectFullPanel.revalidate();
        proposalProjectFullPanel.repaint();
    });
    
    proposalProjectFullPanel.add(archiveButton);
 
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

    JButton archiveButton = new JButton("Arkivér");
    Dimension buttonSize = new Dimension(150, 50); 
    archiveButton.setPreferredSize(buttonSize);
    
    archiveButton.addActionListener(e -> {
        // Archive the project
        archive.archiveProject(project);

        // Call update methods after archiving
        updateProjectList();
        projectFullPanel.removeAll(); 
        projectFullPanel.revalidate();
        projectFullPanel.repaint();
    });
    
    projectFullPanel.add(archiveButton);
    
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

    JButton archiveButton = new JButton("Arkivér");
    Dimension buttonSize = new Dimension(150, 50); 
    archiveButton.setPreferredSize(buttonSize);
    
    archiveButton.addActionListener(e -> {
<<<<<<< HEAD
=======
        // Archive the project
>>>>>>> fc8f243 (Created archive button functionality with funds,)
        archive.archiveFund(fund);

        // Call update methods after archiving
        updateFundList();
        fundFullPanel.removeAll(); 
        fundFullPanel.revalidate();
        fundFullPanel.repaint();
      
    });
    
    fundFullPanel.add(archiveButton);

    fundFullPanel.revalidate();
    fundFullPanel.repaint();

    fundListPanel.revalidate();
    fundListPanel.repaint();
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

private void openProjectDialog() {
    JDialog dialog = new JDialog(frame, "Lav Projekt", true);
    dialog.setSize(700, 700);

    JPanel mainPanel = new JPanel();
    dialog.add(mainPanel);

    GroupLayout layout = new GroupLayout(mainPanel);
    mainPanel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    // Components
    JLabel nameLabel = new JLabel("Titel:");
    JTextField nameField = new JTextField();

    JLabel purposeLabel = new JLabel("Formål:");
    JTextField purposeField = new JTextField();

    JLabel descriptionLabel = new JLabel("Beskrivelse af projektet:");
    JTextArea descriptionArea = new JTextArea(5, 20);
    JScrollPane scrollPane = new JScrollPane(descriptionArea);

    JLabel ownerLabel = new JLabel("Ejer af projektet:");
    JTextField ownerField = new JTextField();

    JLabel targetLabel = new JLabel("Målgruppe (hvem gavner dette projekt):");
    JTextField targetField = new JTextField();

    JLabel budgetLabel = new JLabel("Anslået budget (kr.):");
    JTextField budgetField = new JTextField();

    JLabel fromDateLabel = new JLabel("Fra dato:");
    SpinnerDateModel fromDateModel = new SpinnerDateModel();
    JSpinner fromDateSpinner = new JSpinner(fromDateModel);
    JSpinner.DateEditor fromDateEditor = new JSpinner.DateEditor(fromDateSpinner, "dd/MM/yyyy");
    fromDateSpinner.setEditor(fromDateEditor);

    JLabel toDateLabel = new JLabel("Til dato:");
    SpinnerDateModel toDateModel = new SpinnerDateModel();
    JSpinner toDateSpinner = new JSpinner(toDateModel);
    JSpinner.DateEditor toDateEditor = new JSpinner.DateEditor(toDateSpinner, "dd/MM/yyyy");
    toDateSpinner.setEditor(toDateEditor);

    JLabel activitiesLabel = new JLabel("Aktiviteter:");
    JTextField activitiesField = new JTextField();

    // Tag creation and selection
    JLabel createTagLabel = new JLabel("Create Tag:");
    JButton createTagButton = new JButton("Create Tag");

    JLabel selectTagLabel = new JLabel("Vælg relevante kategorier:");
    JPanel tagPanel = new JPanel();
    tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
    JScrollPane tagScrollPane = new JScrollPane(tagPanel);

    for (String category : main.categories) {
        JCheckBox tagCheckBox = new JCheckBox(category);
        tagPanel.add(tagCheckBox);
    }

    // Event for create tag button
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

    JButton submitButton = new JButton("Tilføj");
    submitButton.addActionListener(ae -> {
        try {
            // Implement input validation and logic here similar to original code
            // Collect and validate all the inputs
            // Collect selected categories
            ArrayList<String> selectedCategories = new ArrayList<>();
            for (Component comp : tagPanel.getComponents()) {
                if (comp instanceof JCheckBox checkBox && checkBox.isSelected()) {
                    selectedCategories.add(checkBox.getText());
                }
            }

            // Create project and add it to list
            project project = new project(
                nameField.getText(),
                selectedCategories,
                descriptionArea.getText(),
                purposeField.getText(),
                ownerField.getText(),
                targetField.getText(),
                Long.parseLong(budgetField.getText()),
                ((Date) fromDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                ((Date) toDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                activitiesField.getText(),
                main.getFundList(),
                main.getCatagoryBoolean()
            );
            main.projectList.add(project);
            dialog.dispose();
        } catch (Exception e) {
            System.err.println("Error when adding project");
            e.printStackTrace();
        }
    });

    // Layout definition
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(nameLabel).addComponent(nameField)
        .addComponent(purposeLabel).addComponent(purposeField)
        .addComponent(descriptionLabel).addComponent(scrollPane)
        .addComponent(ownerLabel).addComponent(ownerField)
        .addComponent(targetLabel).addComponent(targetField)
        .addComponent(budgetLabel).addComponent(budgetField)
        .addComponent(fromDateLabel).addComponent(fromDateSpinner)
        .addComponent(toDateLabel).addComponent(toDateSpinner)
        .addComponent(activitiesLabel).addComponent(activitiesField)
        .addComponent(createTagLabel).addComponent(createTagButton)
        .addComponent(selectTagLabel).addComponent(tagScrollPane)
        .addComponent(submitButton)
    );

    layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(nameLabel).addComponent(nameField)
        .addComponent(purposeLabel).addComponent(purposeField)
        .addComponent(descriptionLabel).addComponent(scrollPane)
        .addComponent(ownerLabel).addComponent(ownerField)
        .addComponent(targetLabel).addComponent(targetField)
        .addComponent(budgetLabel).addComponent(budgetField)
        .addComponent(fromDateLabel).addComponent(fromDateSpinner)
        .addComponent(toDateLabel).addComponent(toDateSpinner)
        .addComponent(activitiesLabel).addComponent(activitiesField)
        .addComponent(createTagLabel).addComponent(createTagButton)
        .addComponent(selectTagLabel).addComponent(tagScrollPane)
        .addComponent(submitButton)
    );

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
        archivePanel.setLayout(new BoxLayout(archivePanel, BoxLayout.Y_AXIS));
        JScrollPane archiveScrollPane = new JScrollPane(archivePanel);
        rightSidePanel.add(archiveScrollPane, "Arkiv");
        
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
