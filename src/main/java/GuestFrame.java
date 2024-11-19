import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.Cursor;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

public class GuestFrame extends JFrame implements ActionListener {

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    // Buttons  
    private JButton createProbButton;

    private JButton menuButton;
    private JButton logoutButton;
    private JButton projectPropButton;
    
    // List to store project proposals
    private JPanel proposalProjectListPanel;
    private JPanel proposalProjectFullPanel;
    
    private JPanel projectListPanel;
    private JPanel projectFullPanel;
    
    
    private JPanel fundListPanel;
    private JPanel fundFullPanel;
    // tag button
    private JPanel tagButtonPanel;

    private JPanel rightSidePanel;
    
    private boolean isInvalidLenght;
    String tempTitle;
    String tempDescription;
    String tempPurpose; 
    String tempOwner;
    String tempTargetAudience;
    String tempIdea;
    Long tempBudget;
    LocalDate tempFromDate;
    LocalDate tempToDate;
    LocalDateTime tempFromDateLDT;
    LocalDateTime tempToDateLDT;
    String tempActivities;
    ArrayList<String> selectedCatagories;
    ArrayList<String> selectedCollabortion = new ArrayList<>();
    boolean isCollaborated;
    String tempWebsite;
    Long tempAmountFrom;
    Long tempAmountTo;
    // Constructor to set up the GUI
    public GuestFrame() {
        initializeFrame();  // Initialize JFrame
        //UserFrameErrorHandling ErrorHandling = new UserFrameErrorHandling();
        JPanel panel1 = createTopPanel();  // Top panel
        JPanel panel2 = createSidePanel();  // Left-side panel
        rightSidePanel = createRightSidePanel();  // Right-side panel
        JPanel panel3 = rightSidePanel;

        // Card layout for switching between views
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Adding different views as "cards" to cardPanel
        cardPanel.add(createCenterPanel(), "Main"); // The main view
        cardPanel.add(createproposalProjectView(), "proposalProjects"); // Project Proposal view


        // Adding panels to the frame
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(cardPanel, BorderLayout.CENTER);  // Use cardPanel as the center panel
        
    }


    private void initializeFrame() {
        frame = new JFrame("Gæst");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLayout(new BorderLayout(10, 10));
    }

    private JPanel createTopPanel() {
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(213, 213, 213, 255));
        panel1.setLayout(new BorderLayout());
    
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftPanel.setOpaque(false); // Make it transparent to show panel1 background
    
        menuButton = createMenuButton();
        menuButton.setPreferredSize(new Dimension(100, 50)); // Set preferred size
        leftPanel.add(menuButton);
    
        menuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            }
    
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            }
        });
    
        projectPropButton = createProjectPropButton("Projekt forslag");
        projectPropButton.setPreferredSize(new Dimension(150, 50)); // Set preferred size
        leftPanel.add(projectPropButton);
    
        projectPropButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        projectPropButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            }
    
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            }
        });
    
        panel1.add(leftPanel, BorderLayout.WEST);
    
        logoutButton = createLogutButton();
        logoutButton.setPreferredSize(new Dimension(100, 50)); // Set preferred size
        panel1.add(logoutButton, BorderLayout.EAST);
    
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            }
    
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            }
        });
    
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

        panel5.add(createProbButton);

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

    // Separate view for "ProjekterMenu"
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
    

    // Creates the right-side panel with different views for each archive type

    // Method to update the right-side panel based on selected archive type

    

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

    private JButton createMenuButton() {
        ImageIcon originalIcon = new ImageIcon("img/Menu.png");
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

    private JButton createLogutButton(){
        ImageIcon originalIcon = new ImageIcon("img/Logout.png");
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
        System.out.println("Opening proposal project dialog...");
    
        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
    
        // Wrap the main panel in a scroll pane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        dialog.add(scrollPane);
    
        JLabel nameLabel = new JLabel("Titel:");
        JTextField nameField = new JTextField();
    
        JLabel ideaLabel = new JLabel("Idé/Formål:");
        JTextField ideaField = new JTextField();
    
        JLabel descriptionLabel = new JLabel("Kort beskrivelse af projektet:");
        JTextArea descriptionArea = new JTextArea(5, 20);
        JScrollPane desciptionScrollPane = new JScrollPane(descriptionArea);
    
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
            }
        );
    
        JButton submitButton = new JButton("Tilføj");
        submitButton.addActionListener(event -> {
            System.out.println("Submit button clicked");

            boolean hasError = false; // Flag to check if any validation error occurs

            //Proposal Error Handling

            //Title Error Handling
            if(!validationUtils.isWithinLowerCharLimit(nameField.getText())){
                isInvalidLenght = true;
                UserFrameErrorHandling.displayTitleError(isInvalidLenght);
                System.out.println("Title error: Length is invalid");
                hasError = true;
            }else if(!validationUtils.isValidInput(nameField.getText())){
                isInvalidLenght = false;
                dialog.add(UserFrameErrorHandling.displayTitleError(isInvalidLenght));
                System.out.println("Title error: Invalid input");
                hasError = true;
            }else{
                tempTitle = nameField.getText();
                System.out.println("Title set: " + tempTitle);
            }

            //Ideas Error Handling
            if(!validationUtils.isWithinLowerCharLimit(ideaField.getText())){
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayIdeaError(isInvalidLenght));
                System.out.println("Idea error: Length is invalid");
                hasError = true;
            }else if(!validationUtils.isValidInput(ideaField.getText())){
                isInvalidLenght = false;
                dialog.add(UserFrameErrorHandling.displayIdeaError(isInvalidLenght));
                System.out.println("Idea error: Invalid input");
                hasError = true;
            }else{
                tempIdea = ideaField.getText();
                System.out.println("Idea set: " + tempIdea);
            }

            //Description Error Handling
            if(!validationUtils.isWithinUpperCharLimit(descriptionArea.getText())){
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayDescriptionError(isInvalidLenght));
                System.out.println("Description error: Length is invalid");
                hasError = true;
            }else if(!validationUtils.isValidDescription(descriptionArea.getText())){
                isInvalidLenght = false;
                dialog.add(UserFrameErrorHandling.displayDescriptionError(isInvalidLenght));
                System.out.println("Description error: Invalid input");
                hasError = true;
            }else{
                tempDescription = descriptionArea.getText();
                System.out.println("Description set: " + tempDescription);
            }

            //Owner Error Handling
            if(!validationUtils.isWithinLowerCharLimit(ownerField.getText())){
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayOwnerError(isInvalidLenght));
                System.out.println("Owner error: Length is invalid");
                hasError = true;
            }else if(!validationUtils.isValidInput(ownerField.getText())){
                isInvalidLenght = false;
                dialog.add(UserFrameErrorHandling.displayOwnerError(isInvalidLenght));
                System.out.println("Owner error: Invalid input");
                hasError = true;
            }else{
                tempOwner = ownerField.getText();
                System.out.println("Owner set: " + tempOwner);
            }

            //Target Audience Error Handling
            if(!validationUtils.isWithinLowerCharLimit(targetField.getText())){
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayTargetAudienceError(isInvalidLenght));
                System.out.println("Target Audience error: Length is invalid");
                hasError = true;
            }else if(!validationUtils.isValidInput(targetField.getText())){
                isInvalidLenght = false;
                dialog.add(UserFrameErrorHandling.displayTargetAudienceError(isInvalidLenght));
                System.out.println("Target Audience error: Invalid input");
                hasError = true;
            }else{
                tempTargetAudience = targetField.getText();
                System.out.println("Target Audience set: " + tempTargetAudience);
            }

            //Budget Error Handling
            if(!validationUtils.isNumericInput(budgetField.getText())){
                dialog.add(UserFrameErrorHandling.displayBudgetError());
                System.out.println("Budget error: Invalid input");
                hasError = true;
            }else{
                tempBudget = Long.parseLong(budgetField.getText());
                System.out.println("Budget set: " + tempBudget);
            }

            //From Date Error Handling
            //To Date Error Handling
            //If From Date is after To Date
            if(((Date) fromDateSpinner.getValue()).after((Date) toDateSpinner.getValue())){
                dialog.add(UserFrameErrorHandling.displayDateSpinnerError());
                System.out.println("Date error: From date is after To date");
                hasError = true;
            }else{
                tempFromDate = ((Date) fromDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                tempToDate = ((Date) toDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                tempFromDateLDT = tempFromDate.atStartOfDay();
                tempToDateLDT = tempToDate.atStartOfDay();
                System.out.println("Dates set: From " + tempFromDateLDT + " To " + tempToDateLDT);
            }

            //Activities Error Handling
            if(!validationUtils.isWithinUpperCharLimit(activitiesField.getText())){
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayActivityError(isInvalidLenght));
                System.out.println("Activities error: Length is invalid");
                hasError = true;
            }else if(!validationUtils.isValidInput(activitiesField.getText())){
                isInvalidLenght = false;
                dialog.add(UserFrameErrorHandling.displayActivityError(isInvalidLenght));
                System.out.println("Activities error: Invalid input");
                hasError = true;
            }else{
                tempActivities = activitiesField.getText();
                System.out.println("Activities set: " + tempActivities);
            }

            //Categories Error Handling
            //If no categories are selected
            ArrayList<String> selectedCatagories = new ArrayList<>();
            for(Component comp : tagPanel.getComponents()){
                if(comp instanceof JCheckBox){
                    JCheckBox checkBox = (JCheckBox) comp;
                    if(checkBox.isSelected()){
                        selectedCatagories.add(checkBox.getText());
                    }
                }
            }
            System.out.println("Selected categories: " + selectedCatagories);

            if (hasError) {
                return;
            }

            // Create the proposal project instance with categories
            proposalProject proposal = new proposalProject(
                tempTitle,
                selectedCatagories,
                tempDescription,
                tempIdea,
                tempOwner,
                tempTargetAudience,
                tempBudget,
                tempFromDateLDT,
                tempToDateLDT,
                tempActivities
            );
            

            // Add the proposal to the list and update UI
            main.proposalList.add(proposal);
            updateproposalProjectList();
            System.out.println("Proposal added to list and UI updated");

            // Close the dialog
            dialog.dispose();
            System.out.println("Dialog closed");
        });

        // Define layout structure
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(nameLabel).addComponent(nameField)
            .addComponent(ideaLabel).addComponent(ideaField)
            .addComponent(descriptionLabel).addComponent(desciptionScrollPane)
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
            .addComponent(descriptionLabel).addComponent(desciptionScrollPane)
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


//SORT HERE
    private void updateproposalProjectList() {
        System.out.println("Updating proposal project list");
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
     // Refresh the panel to reflect the changes
     proposalProjectFullPanel.revalidate();
     proposalProjectFullPanel.repaint();
 }


    private JPanel createRightSidePanel() {
        JPanel rightSidePanel = new JPanel(new CardLayout());
        rightSidePanel.setBackground(new Color(213, 213, 213, 255));
        rightSidePanel.setPreferredSize(new Dimension(900, 100));
        
        proposalProjectFullPanel = new JPanel();
        proposalProjectFullPanel.setLayout(new BoxLayout(proposalProjectFullPanel, BoxLayout.Y_AXIS));
        JScrollPane proposalScrollPane = new JScrollPane(proposalProjectFullPanel);
        rightSidePanel.add(proposalScrollPane, "proposalProjectsDetails");
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
        } else if (e.getSource() == menuButton) {
            cardLayout.show(cardPanel, "Main");
        } else if (e.getSource() == createProbButton) {
            openproposalProjectDialog();
        }else if (e.getSource() == logoutButton) {
            Frontpage frontpage = new Frontpage();
            frontpage.show();
            frame.dispose();
        }
 

    }

}
