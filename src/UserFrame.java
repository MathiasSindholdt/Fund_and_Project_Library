import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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
    
    private JPanel fundListPanel;
    
    // tag button
    private JPanel tagButtonPanel;
    
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
        initializeFrame();  // Initialize JFrame
        //UserFrameErrorHandling ErrorHandling = new UserFrameErrorHandling();
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


        projectProposalListPanel = new JPanel();

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

         // Panel for tag buttons
        tagButtonPanel = new JPanel();
        tagButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(tagButtonPanel, BorderLayout.NORTH);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == projectPropButton) {
            cardLayout.show(cardPanel, "ProjectProposal"); // Switch to Project Proposal view
        } else if (e.getSource() == projectButton) {
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
        else if (e.getSource() == createProjectButton) {
            openProjectDialog();
        }
    }

    // Restore the project proposal dialog
    private void openProjectProposalDialog() {
        JDialog dialog = new JDialog(frame, "Lav Projekt Forslag", true);
        dialog.setSize(700, 700);
        dialog.setLayout(new GridLayout(13, 2, 10, 10));

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
                tagPanel.revalidate();
                tagPanel.repaint();
            }

            //tag button for filtering between the different tags
            JButton tagButton = new JButton(newTag);
            tagButton.addActionListener(tagEvent -> filterProjectProposalsByTag(newTag));
            tagButtonPanel.add(tagButton);
            tagButtonPanel.revalidate();
            tagButtonPanel.repaint();
        });

        JButton submitButton = new JButton("Tilføj");
        submitButton.addActionListener(event -> {
            String name = nameField.getText();
            String idea = ideaField.getText();
            String description = descriptionArea.getText();
            String ideaFrom = ideaFromField.getText();
            String owner = ownerField.getText();
            String target = targetField.getText();
            String budget = budgetField.getText();
            LocalDate fromDate = LocalDate.parse(fromDateSpinner.getValue().toString());
            LocalDate toDate = LocalDate.parse((toDateSpinner.getValue().toString()));
            String activities = activitiesField.getText();
            List<String> selectedTags = new ArrayList<>();
 

            for (Component comp : tagPanel.getComponents()) {
                if (comp instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) comp;
                    if (checkBox.isSelected()) {
                        selectedTags.add(checkBox.getText()); // Add the selected tag to the list
                    }
                }
            }
            

            // Create a new project proposal and add it to the list
            ProjectProposal proposal = new ProjectProposal(name, idea, description, ideaFrom, owner, target, budget, fromDate, toDate, activities, selectedTags);
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
            tagButton.addActionListener(tagEvent -> filterProjectProposalsByTag(newTag));
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

    public static JPanel displayTagError(){
        JOptionPane.showMessageDialog(null, "Katagorien eksisterer allerede");
        return new JPanel();
    }

    public static JPanel displayBudgetError(){
        JOptionPane.showMessageDialog(null, "Budgettet skal være et tal");
        return new JPanel();
    }
    public static JPanel displayDateError(){
        JOptionPane.showMessageDialog(null, "Datoen skal være i formatet dd/MM/yyyy");
        return new JPanel();
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

    

    // method to filther by tag
    private void filterProjectProposalsByTag(String tag) {
        projectProposalListPanel.removeAll();
        
        for (ProjectProposal proposal : projectProposals) {
            if (proposal.getTags().contains(tag)) {
                JLabel proposalLabel = new JLabel(proposal.getTitle() + " - " + proposal.getOwner());
    
                proposalLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        showProjectProbDetails(proposal);
                    }
                });
    
                projectProposalListPanel.add(proposalLabel);
            }
        }
    
        projectProposalListPanel.revalidate();
        projectProposalListPanel.repaint();
    }
    
    // Method to update the list and add mouse listeners
    private void updateProjectProposalList() {
    
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
        projectListPanel = new JPanel();
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
    
    private void showFundDetails(fundClass fund){

    }

    
    

    class ProjectProposal {
        private String title;
        private String idea;
        private String description;
        private String ideaFrom;
        private String owner;
        private String target;
        private String budget;
        private LocalDate fromDate;
        private LocalDate toDate;
        private String activities;
        private List<String> tags;
    
        public ProjectProposal(String title, String idea, String description, String ideaFrom, String owner, String target,
                               String budget, LocalDate fromDate, LocalDate toDate, String activities, List<String> tags) {
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
            this.tags = tags;
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

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public String getActivities() {
        return activities;
    }

    public List<String> getTags() {
        return tags;
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
        projectProposalFullPanel.add(new JLabel("Katagorier: " + proposal.getTags()));
        
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
    private LocalDate fromDate;
    private LocalDate toDate;
    private String activities;

    public Project(String title, String idea, String description, String ideaFrom, String owner, String target,
                   String budget, LocalDate fromDate, LocalDate toDate, String activities) {
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
    public LocalDate getFromDate() { return fromDate; }
    public LocalDate getToDate() { return toDate; }
    public String getActivities() { return activities; }
}