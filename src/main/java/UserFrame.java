import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

public class UserFrame extends AbstractFrame{
    public UserFrame() {
        this.userType = "user";
        this.initializeFrame(); // Initialize JFrame
        // UserFrameErrorHandling ErrorHandling = new UserFrameErrorHandling();

        JPanel panel1 = createTopPanel(); // Top panel
        // Create a top panel to hold the label
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setPreferredSize(new Dimension(100, 30)); // Set height for the top panel
        JLabel label = new JLabel("Kategorier");
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
        topPanel.add(label);

        // Add the top panel to the main panel
        panel2.add(topPanel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane();

        panel2.add(scrollPane, BorderLayout.CENTER);

        JPanel panel2 = createSidePanel(); // Left-side panel
        rightSidePanel = createRightSidePanel(); // Right-side panel
        JPanel panel3 = rightSidePanel;
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
        frame.add(cardPanel, BorderLayout.CENTER); // Use cardPanel as the center panel

    }

    //KEEP THIS ITS DIFFERENT
    public JPanel createCenterPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(213, 213, 213, 255));

        // Panel for the welcome label
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(new Color(213, 213, 213, 255));

        JLabel welcomeLabel = new JLabel("Velkommen");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 

        welcomePanel.add(Box.createVerticalGlue());
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 20))); // 

        mainPanel.add(welcomePanel, BorderLayout.NORTH);

        // Panel for the sections and buttons
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(213, 213, 213, 255));

        // Section for Projektforslag
        JPanel projectProposalPanel = new JPanel();
        projectProposalPanel.setLayout(new BorderLayout());
        projectProposalPanel.setBackground(new Color(213, 213, 213, 255));

        JLabel projectProposalLabel = new JLabel("Projektforslag");
        projectProposalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        projectProposalLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align label to the left
        projectProposalLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); // Add space to the left

        JPanel projectProposalButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        projectProposalButtons.setBackground(new Color(213, 213, 213, 255));

        createProbButton = UIButtons.createButton("Opret et nyt Projektforslag");
        createProbButton.setPreferredSize(new Dimension(200, 50));
        createProbButton.addActionListener(this);
        changeProbButton = UIButtons.createButton("Redigér Projektforslag");
        changeProbButton.setPreferredSize(new Dimension(175, 50));
        changeProbButton.addActionListener(this);

        projectProposalButtons.add(createProbButton);
        projectProposalButtons.add(changeProbButton);
        projectProposalPanel.add(projectProposalLabel, BorderLayout.NORTH);
        JPanel verticalSpacePanel = new JPanel();
        verticalSpacePanel.setLayout(new BoxLayout(verticalSpacePanel, BoxLayout.Y_AXIS));
        verticalSpacePanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical space
        verticalSpacePanel.setBackground(new Color(213, 213, 213, 255));
        verticalSpacePanel.add(projectProposalButtons);

        projectProposalPanel.add(verticalSpacePanel, BorderLayout.CENTER);

        // Section for Projekt
        JPanel projectPanel = new JPanel();
        projectPanel.setLayout(new BorderLayout());
        projectPanel.setBackground(new Color(213, 213, 213, 255));

        JLabel projectLabel = new JLabel("Projekt");
        projectLabel.setFont(new Font("Arial", Font.BOLD, 20));
        projectLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align label to the left
        projectLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); // Add space to the left

        JPanel projectButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        projectButtons.setBackground(new Color(213, 213, 213, 255));

        createProjectButton = UIButtons.createButton("Opret et nyt projekt");
        createProjectButton.setPreferredSize(new Dimension(160, 50));
        createProjectButton.addActionListener(this);
        changeProjectButton = UIButtons.createButton("Redigér et projekt");
        changeProjectButton.addActionListener(this);

        projectButtons.add(createProjectButton);
        projectButtons.add(changeProjectButton);

        projectPanel.add(projectLabel, BorderLayout.NORTH);
        JPanel verticalSpacePanel2 = new JPanel();
        verticalSpacePanel2.setLayout(new BoxLayout(verticalSpacePanel2, BoxLayout.Y_AXIS));
        verticalSpacePanel2.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical space
        verticalSpacePanel2.setBackground(new Color(213, 213, 213, 255));
        verticalSpacePanel2.add(projectButtons);

        projectPanel.add(verticalSpacePanel2, BorderLayout.CENTER);

        // Section for Fond
        JPanel fundPanel = new JPanel();
        fundPanel.setLayout(new BorderLayout());
        fundPanel.setBackground(new Color(213, 213, 213, 255));

        JLabel fundLabel = new JLabel("Fond");
        fundLabel.setFont(new Font("Arial", Font.BOLD, 20));
        fundLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align label to the left
        fundLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); // Add space to the left


        JPanel fundButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        fundButtons.setBackground(new Color(213, 213, 213, 255));

        createFundButton = UIButtons.createButton("Tilføj en ny fond");
        createFundButton.addActionListener(this);
        changeFundButton = UIButtons.createButton("Redigér en fond");
        changeFundButton.addActionListener(this);

        fundButtons.add(createFundButton);
        fundButtons.add(changeFundButton);

        fundPanel.add(fundLabel, BorderLayout.NORTH);
        JPanel verticalSpacePanel3 = new JPanel();
        verticalSpacePanel3.setLayout(new BoxLayout(verticalSpacePanel3, BoxLayout.Y_AXIS));
        verticalSpacePanel3.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical space
        verticalSpacePanel3.setBackground(new Color(213, 213, 213, 255));
        verticalSpacePanel3.add(fundButtons);

        fundPanel.add(verticalSpacePanel3, BorderLayout.CENTER);


        //section for catagories
        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new BorderLayout());
        categoriesPanel.setBackground(new Color(213, 213, 213, 255));

        JLabel categoriesLabel = new JLabel("Kategorier");
        categoriesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        categoriesLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align label to the left
        categoriesLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); // Add space to the left

        JPanel categoriesButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        categoriesButtons.setBackground(new Color(213, 213, 213, 255));

        createCategoriesButton = UIButtons.createButton("Opret en ny kategori");
        createCategoriesButton.setPreferredSize(new Dimension(160, 50));
        createCategoriesButton.addActionListener(this);
        changeCategoriesButton = UIButtons.createButton("Redigér kategori listen");
        changeCategoriesButton.addActionListener(this);
       
        categoriesButtons.add(createCategoriesButton);
        categoriesButtons.add(changeCategoriesButton);

        categoriesPanel.add(categoriesLabel, BorderLayout.NORTH);
        JPanel verticalSpacePanel4 = new JPanel();
        verticalSpacePanel4.setLayout(new BoxLayout(verticalSpacePanel4, BoxLayout.Y_AXIS));
        verticalSpacePanel4.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical space
        verticalSpacePanel4.setBackground(new Color(213, 213, 213, 255));
        verticalSpacePanel4.add(categoriesButtons);

        categoriesPanel.add(verticalSpacePanel4, BorderLayout.CENTER);


        // Add sections to content panel
        contentPanel.add(projectProposalPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(projectPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(fundPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(categoriesPanel);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        //ActionListeners for edit buttons

        changeFundButton.addActionListener(e -> {
            editFundButton.editFundDialog(); // Call the method on the instance
            updateFundList();

        });

        changeProbButton.addActionListener(e -> {
            editProjectProposal.openEditProjectPropDialog(); // Call the method on the instance
            this.updateProposalProjectList();
        });

        changeProjectButton.addActionListener(e -> {
            editProjectButton.openEditProjectDialog();
            updateProjectList();
        });

        changeCategoriesButton.addActionListener(e -> {
            openEditCategoriesDialog();
            updateCategoryPanel();
        });

        return mainPanel;
    }

    //KEEP ITS DIFEFRENT
    // Separate view for "Projekter"
    private JPanel createProjectsView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

       

        // Initialize the project list panel
        projectListPanel = new JPanel();
        projectListPanel.setLayout(new BoxLayout(projectListPanel, BoxLayout.Y_AXIS)); // Ensure layout is set


        JButton addProject = new JButton("Tilføj et nyt projekt");
        addProject.addActionListener(e -> {
            openProjectDialog();
            updateProjectList();
        });
        JButton changeProject = new JButton("Rediger et projekt");
        changeProject.addActionListener(e -> {
            editProjectButton.openEditProjectDialog();
            updateProjectList();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonPanel.add(addProject);
        buttonPanel.add(changeProject);
        panel.add(buttonPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(projectListPanel); // Add project list panel in a scroll pane
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    //KEEP ITS DIFEFRENT
    // Separate view for "Fonde"
    private JPanel createFundsView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);


        // Initialize fund list panel
        fundListPanel = new JPanel();
        fundListPanel.setLayout(new BoxLayout(fundListPanel, BoxLayout.Y_AXIS));

        JButton addFund = new JButton("Tilføj en ny fond");
        addFund.addActionListener(e -> {
            openFundDialog();
            updateFundList();
        });
        JButton changeFund = new JButton("Rediger en fond");
        changeFund.addActionListener(e -> {
            editFundButton.editFundDialog();
            updateFundList();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0)); // Set GridLayout with 1 row, 2 columns, and 10px horizontal gap
        buttonPanel.add(addFund);
        buttonPanel.add(changeFund);
        panel.add(buttonPanel, BorderLayout.NORTH);

        // Create a scroll pane for the fund list
        JScrollPane scrollPane = new JScrollPane(fundListPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
    
//KEEP DIFFERNET
    public void openCategoriesDialog(){
        JDialog dialog = new JDialog(UserFrame.frame, "Tilføj nye Kategorier");
        dialog.setSize(500, 500);
        dialog.setLayout(new BorderLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new BoxLayout(categoriesPanel, BoxLayout.Y_AXIS));
        
        JLabel title = new JLabel("Tilføj en ny kategori");
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setBounds(50, 10, 200, 30);

        JButton addCategoryButton = new JButton("Tilføj kategori");
        addCategoryButton.setPreferredSize(new Dimension(150, 50));
        addCategoryButton.setBounds(50, 250, 150, 50);
        addCategoryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        for(String category : main.categories){
            JLabel categoryLabel = new JLabel(category);
            categoriesPanel.add(categoryLabel);
            categoriesPanel.add(Box.createVerticalStrut(5));
        }
        addCategoryButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String category = JOptionPane.showInputDialog(dialog, "Indtast den nye kategori");
                JOptionPane.showMessageDialog(dialog, "Kategori: "+ category + " er blevet tilføjet");
                if(category == null || category.isEmpty()){
                    return;
                }

                if(main.categories.contains(category)){
                    UserFrameErrorHandling.displayTagError();
                    return;
                }else{
                    main.categories.add(category);
                    JLabel categoryLabel = new JLabel(category);
                    categoriesPanel.add(categoryLabel);
                    categoriesPanel.add(Box.createVerticalStrut(5));
                    updateCategoryPanel();
                }
                
                dialog.revalidate();
                dialog.repaint();
            }
        });
        
        
        JScrollPane scrollPane = new JScrollPane(categoriesPanel);
        scrollPane.setBounds(50, 50, 400, 200);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        
        dialog.add(title, BorderLayout.NORTH);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(addCategoryButton, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(UserFrame.frame);
        dialog.setVisible(true);
    }
//KEEP DIFFERNET

    public void openEditCategoriesDialog(){
        JDialog dialog = new JDialog(UserFrame.frame, "Rediger Kategori listen");
        dialog.setSize(500, 500);
        dialog.setLayout(new BorderLayout());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel categoriesPanel = new JPanel();
        
        
        for(String category : main.categories){
            JButton removeButton = new JButton("X");
            JButton editButton = new JButton("🖉");
            JPanel editRemovePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel categoryLabel = new JLabel(category);
            editRemovePanel.add(categoryLabel);
            editRemovePanel.add(editButton);
            editRemovePanel.add(removeButton);
            editRemovePanel.add(Box.createVerticalStrut(5));
            categoriesPanel.add(editRemovePanel);
            categoriesPanel.revalidate();
            categoriesPanel.repaint();

            removeButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int response = JOptionPane.showConfirmDialog(dialog, "Er du sikker på, at du vil fjerne denne kategori?", "Bekræft fjernelse", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        categoriesPanel.repaint();
                        System.out.println("Removing category: " + category);
                        main.categories.remove(categoryLabel.getText());
                        categoriesPanel.remove(editRemovePanel);
                        categoriesPanel.revalidate();
                        categoriesPanel.repaint();
                        updateCategoryPanel();
                        writeAll(); // Update in csv
                    }
                }
            });

            editButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    String newCategory = JOptionPane.showInputDialog(dialog, "Indtast den nye kategori titel");
                    if(newCategory == null || newCategory.isEmpty()){
                        return;
                    }

                    if(main.categories.contains(newCategory)){
                        UserFrameErrorHandling.displayTagError();
                        return;
                    }else{
                        categoryLabel.setText(newCategory);
                        int index = main.categories.indexOf(category);
                        if (index != -1) {
                            main.categories.set(index, newCategory);
                        }
                        updateCategoryPanel();
                        writeAll();
                    }
                };
            });
        }

        categoriesPanel.setLayout(new BoxLayout(categoriesPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(categoriesPanel);
        scrollPane.setBounds(50, 50, 400, 200);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        
       
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.setLocationRelativeTo(UserFrame.frame);
        dialog.setVisible(true);
    }

    //KEEP DIFFERENT
    private void openFundDialog() {
        JDialog dialog = new JDialog(frame, "Tilføj En Fond", true);
        dialog.setSize(700, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GroupLayout(mainPanel));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Fond Titel
        JLabel nameLabel = new JLabel("Fond Titel:*");
        JTextField nameField = new JTextField();

        // Fond Beskrivelse
        JLabel descriptionLabel = new JLabel("Beskrivelse:*");
        JTextArea descriptionArea = new JTextArea(7, 20);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionArea.setLineWrap(true);

        // Fond Beløb Fra og Til
        JLabel amountFromLabel = new JLabel("Beløb fra:*");
        JTextField amountFromField = new JTextField();
        JLabel amountToLabel = new JLabel("Beløb til:*");
        JTextField amountToField = new JTextField();

        // Fond Deadline
        JLabel deadlineLabel = new JLabel("Ansøgningsfrist:");
        SpinnerDateModel deadlineModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner deadlineSpinner = new JSpinner(deadlineModel);
        JSpinner.DateEditor deadlineEditor = new JSpinner.DateEditor(deadlineSpinner, "dd/MM/yyyy");
        deadlineSpinner.setEditor(deadlineEditor);
        deadlineSpinner.setValue(Date.from(Instant.now())); // Set default time to 00:00

        // Tilføjede Deadlines Panel
        JLabel addedDeadlinesLabel = new JLabel("Tilføjede ansøgningsfrister:");
        JPanel deadlineListPanel = new JPanel();
        deadlineListPanel.setLayout(new BoxLayout(deadlineListPanel, BoxLayout.Y_AXIS));
        JScrollPane deadlineScrollPane = new JScrollPane(deadlineListPanel);
        deadlineScrollPane.setPreferredSize(new Dimension(200, 100));
        JLabel isDeadLineTimeLabel = new JLabel("Er ansøgningsfrist på et bestemt tidspunkt?:");
        JCheckBox deadLineTimeCheckBox = new JCheckBox();
        JPanel deadLineTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel deadLineTimeLabel = new JLabel("Tidspunkt:");
        JTextField deadLineTimeFieldHour = new JTextField(2);
        JLabel deadLineTimeLabelColon = new JLabel(":");
        JTextField deadLineTimeFieldMinute = new JTextField(2);

        deadLineTimePanel.add(deadLineTimeLabel);
        deadLineTimePanel.add(deadLineTimeFieldHour);
        deadLineTimePanel.add(deadLineTimeLabelColon);
        deadLineTimePanel.add(deadLineTimeFieldMinute);
        deadLineTimePanel.setVisible(false);

        deadLineTimeCheckBox.addItemListener(e -> {
            deadLineTimePanel.setVisible(deadLineTimeCheckBox.isSelected());
            dialog.revalidate();
            dialog.repaint();
        });

        // Knap til at tilføje en ny deadline
        JButton addDeadlineButton = new JButton("Tilføj ansøgningsfrist");

        // List til opbevaring af tilføjede deadlines
        List<LocalDateTime> addedDeadlines = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        addDeadlineButton.addActionListener(e -> {
            LocalDateTime newDeadline;
            if (deadLineTimeCheckBox.isSelected()) {
                if (!validationUtils.isValidTime(deadLineTimeFieldHour.getText(), true)) {
                    dialog.add(UserFrameErrorHandling.displayTimeError());
                    return;
                }
                if (!validationUtils.isValidTime(deadLineTimeFieldMinute.getText(), false)) {
                    dialog.add(UserFrameErrorHandling.displayTimeError());
                    return;
                }
                newDeadline = ((java.util.Date) deadlineSpinner.getValue())
                        .toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDateTime();
                newDeadline = newDeadline.withHour(Integer.parseInt(deadLineTimeFieldHour.getText()));
                newDeadline = newDeadline.withMinute(Integer.parseInt(deadLineTimeFieldMinute.getText()));
            } else {
                newDeadline = ((java.util.Date) deadlineSpinner.getValue())
                        .toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate()
                        .atTime(23, 59); // Set default time to 00:00
            }
            addedDeadlines.add(newDeadline);

            // Create a panel to hold the deadline label and the remove button
            JPanel deadlinePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel deadlineLabelItem = new JLabel(newDeadline.format(formatter));

            JButton xButton = new JButton("X");
            xButton.addActionListener(this);

            // Add action listener to the remove button
            final LocalDateTime deadlineToRemove = newDeadline;
            xButton.addActionListener(removeEvent -> {
                addedDeadlines.remove(deadlineToRemove);
                deadlineListPanel.remove(deadlinePanel);
                deadlineListPanel.revalidate();
                deadlineListPanel.repaint();
            });

            // Add components to the deadline panel
            deadlinePanel.add(deadlineLabelItem);
            deadlinePanel.add(xButton);

            // Add the deadline panel to the main panel
            deadlineListPanel.add(deadlinePanel);
            deadlineListPanel.revalidate();
            deadlineListPanel.repaint();
        });

        // Kategori valg
        JLabel tagLabel = new JLabel("Tilføj Kategori:");
        JButton createTagButton = new JButton("Opret Kategori");
        JPanel tagPanel = new JPanel();
        tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
        JScrollPane tagScrollPane = new JScrollPane(tagPanel);
        tagScrollPane.setPreferredSize(new Dimension(200, 100));
        getCurrentCheckboxes.getAllCurrentCatagories(tagPanel);

        // Action Listener for Create Tag knap
        createTagButton.addActionListener(e -> {
            String newTag = JOptionPane.showInputDialog(dialog, "Indtast Ny Kategori:");
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
        JButton createContactsButton = new JButton("Tilføj Kontakt person(er)");
        JPanel contactsPanel = new JPanel();
        contactsPanel.setLayout(new BoxLayout(contactsPanel, BoxLayout.Y_AXIS));
        JScrollPane contactsScrollPane = new JScrollPane(contactsPanel);
        contactsScrollPane.setPreferredSize(new Dimension(200, 100));

        createContactsButton.addActionListener(e -> {
            openContactsDialog(dialog);
            System.out.println(tempContact.getContactName());
            System.out.println(tempContact.getContactPhoneNumber());
            System.out.println(tempContact.getContactEmail());

            JButton removeContactButton = new JButton("X");
            JPanel removeContactPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel contactInfo = new JLabel(tempContact.getContactName() + " - " + tempContact.getContactPhoneNumber()
                    + " - " + tempContact.getContactEmail());
            removeContactPanel.add(contactInfo);
            removeContactPanel.add(removeContactButton);
            contactsPanel.add(removeContactPanel);
            contactsPanel.revalidate();
            contactsPanel.repaint();
            contacts.add(tempContact);

            final fundContactClass contactToRemove = tempContact;
            removeContactButton.addActionListener(removeEvent -> {
                System.out.println("Removing contact: " + contactToRemove.getContactName());
                contacts.remove(contactToRemove);
                contactsPanel.remove(removeContactPanel);

                contactsPanel.revalidate();
                contactsPanel.repaint();
            });

        });
        // Hjemmeside
        JLabel websiteLabel = new JLabel("Hjemmeside?:");
        JCheckBox websiteCheckBox = new JCheckBox();
        JPanel websitePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        new GridLayout(1, 2);
        JLabel websiteLabel2 = new JLabel("Hjemmeside:");
        JTextField websiteField = new JTextField(30);
        websitePanel.add(websiteLabel2);
        websitePanel.add(websiteField);
        websitePanel.setVisible(false);

        websiteCheckBox.addItemListener(e2 -> {
            websitePanel.setVisible(websiteCheckBox.isSelected());
            if(websiteCheckBox.isSelected()){
                noUrl = false;
            }else{
                noUrl = true;
            }
            dialog.revalidate();
            dialog.repaint();
        });
        // Tidligere samarbejde
        JLabel collaboratedLabel = new JLabel("Tidligere samarbejde?:");
        JCheckBox collaboratedCheckBox = new JCheckBox();
        JPanel collaborationPanel = new JPanel();
        collaborationPanel.setLayout(new BoxLayout(collaborationPanel, BoxLayout.Y_AXIS));
        JLabel collaborationLabel = new JLabel("Tilføj tidligere samarbejdsprojekter:");
        JButton createCollaborationButton = new JButton("Tilføj Projekter");
        JPanel collaborationContentPanel = new JPanel();
        collaborationContentPanel.setLayout(new BoxLayout(collaborationContentPanel, BoxLayout.Y_AXIS));
        JScrollPane collaborationScrollPane = new JScrollPane(collaborationContentPanel);
        collaborationScrollPane.setPreferredSize(new Dimension(200, 100));

        JPanel collaborationLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        collaborationLabelPanel.add(collaborationLabel);
        collaborationLabelPanel.add(createCollaborationButton);
        collaborationPanel.add(collaborationLabelPanel);
        collaborationPanel.add(collaborationScrollPane);
        collaborationPanel.setVisible(false);

        collaboratedCheckBox.addItemListener(e -> {
            getCurrentCheckboxes.getAllProjects(collaborationContentPanel);
            collaborationPanel.setVisible(collaboratedCheckBox.isSelected());
            dialog.revalidate();
            dialog.repaint();

        });
        createCollaborationButton.addActionListener(e -> {
            String newCollaboration = JOptionPane.showInputDialog(dialog,
                    "Skriv nye tidligere samarbejdeprojekter:");
            if (newCollaboration != null && !newCollaboration.trim().isEmpty()) {
                main.userProjectList.add(newCollaboration); // insert stringwrite for user project list
                collaborationContentPanel.add(new JCheckBox(newCollaboration));
                collaborationContentPanel.revalidate();
                collaborationContentPanel.repaint();
            }
        });

        // Add the collaboration panel to the main panel
        mainPanel.add(collaborationPanel, BorderLayout.WEST);

        // Løbende deadline
        JLabel runningLabel = new JLabel("Løbende ansøgningsfrist:");
        JCheckBox runningCheckBox = new JCheckBox();
        runningCheckBox.addItemListener(e -> {
            deadlineSpinner.setEnabled(!runningCheckBox.isSelected());
            addDeadlineButton.setEnabled(!runningCheckBox.isSelected());
            deadLineTimeCheckBox.setEnabled(!runningCheckBox.isSelected());
            // Save all the current deadlines and remove them from the UI when checked
            if (runningCheckBox.isSelected()) {
                deadlineListPanel.removeAll();
            } else {
                // Restore the deadlines when unchecked
                for (LocalDateTime deadline : addedDeadlines) {
                    JLabel deadlineLabelItem = new JLabel(deadline.format(formatter));
                    deadlineListPanel.add(deadlineLabelItem);
                }
            }
            deadlineListPanel.revalidate();
            deadlineListPanel.repaint();
        });

        // Submit knap
        JButton submitButton = new JButton("Tilføj Fond");
        submitButton.addActionListener(event -> {
            boolean running = runningCheckBox.isSelected();
            boolean hasError = false;

            // FundTitle errorhandling
            if (!validationUtils.isWithinLowerCharLimit(nameField.getText())) {
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayTitleError(isInvalidLenght));
                hasError = true;
            } else {
                tempTitle = nameField.getText().trim();
            }

            // FundDescription errorhandling
            if (!validationUtils.isWithinUpperCharLimit(descriptionArea.getText())) {
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayDescriptionError(isInvalidLenght));
                hasError = true;
            }  else {
                tempDescription = descriptionArea.getText().trim();
            }

            // Money errorhandling
            String trimmedAmountTo = amountToField.getText().trim().replace(".", "").replace(",", "");
            String trimmedAmountFrom = amountFromField.getText().trim().replace(".", "").replace(",", "");
            System.out.println(trimmedAmountFrom);
            System.out.println(trimmedAmountTo);
            if (!validationUtils.isNumericInput(trimmedAmountFrom)) {
                dialog.add(UserFrameErrorHandling.displayAmountFromError());
                hasError = true;
            } else {
                tempAmountFrom = Long.parseLong(trimmedAmountFrom);
            }

            if (!validationUtils.isNumericInput(trimmedAmountTo)) {
                dialog.add(UserFrameErrorHandling.displayAmountToError());
                hasError = true;
            } else {
                tempAmountTo = Long.parseLong(trimmedAmountTo);
            }

            // Category Errorhandling
            ArrayList<String> selectedCatagories = new ArrayList<>();
            for (Component comp : tagPanel.getComponents()) {
                if (comp instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) comp;
                    if (checkBox.isSelected()) {
                        selectedCatagories.add(checkBox.getText());
                    }
                }
            }

            // Website Errorhandling
            if (!validationUtils.isValidUrl(websiteField.getText())) {
                dialog.add(UserFrameErrorHandling.displayWebsiteError());
                hasError = true;
            } else {
                tempWebsite = websiteField.getText().trim();
            }

            if (collaboratedCheckBox.isSelected() == true) {
                isCollaborated = true;
                for (Component comp : collaborationContentPanel.getComponents()) {
                    if (comp instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) comp;
                        if (checkBox.isSelected()) {
                            selectedCollabortion.add(checkBox.getText());
                        }
                    }
                }
            }

            if (running) {
                addedDeadlines.clear();
                addedDeadlines.add(LocalDateTime.of(3000, 1, 1, 0, 0));
            }

            if (!hasError) {
                JOptionPane.showMessageDialog(dialog, "Fonden er blevet tilføjet", "Fonden tilføjet", JOptionPane.INFORMATION_MESSAGE);

                fundClass fund = new fundClass(tempTitle, tempDescription, tempAmountFrom, tempAmountTo,
                        addedDeadlines, selectedCatagories, selectedCollabortion, contacts, tempWebsite,
                        isCollaborated, running, noUrl);
                contacts.clear();
                main.fundList.add(fund);
                updateFundList();

                writeAll();

                dialog.dispose();
            }
        });

        // GroupLayout struktur
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameLabel).addComponent(nameField)
                .addComponent(descriptionLabel).addComponent(descriptionScrollPane)
                .addComponent(amountFromLabel).addComponent(amountFromField)
                .addComponent(amountToLabel).addComponent(amountToField)
                .addComponent(runningLabel).addComponent(runningCheckBox)
                .addComponent(deadlineLabel).addComponent(deadlineSpinner)
                .addComponent(isDeadLineTimeLabel)
                .addComponent(deadLineTimeCheckBox).addComponent(deadLineTimePanel)
                .addComponent(addDeadlineButton)
                .addComponent(addedDeadlinesLabel).addComponent(deadlineScrollPane)
                .addComponent(tagLabel).addComponent(createTagButton).addComponent(tagScrollPane)
                .addComponent(contactsLabel).addComponent(createContactsButton)
                .addComponent(contactsScrollPane)
                .addComponent(websiteLabel).addComponent(websiteCheckBox).addComponent(websitePanel)
                .addComponent(collaboratedLabel).addComponent(collaboratedCheckBox)
                .addComponent(collaborationPanel)
                .addComponent(submitButton));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(nameLabel).addComponent(nameField)
                .addComponent(descriptionLabel).addComponent(descriptionScrollPane)
                .addComponent(amountFromLabel).addComponent(amountFromField)
                .addComponent(amountToLabel).addComponent(amountToField)
                .addComponent(runningLabel).addComponent(runningCheckBox)
                .addComponent(deadlineLabel).addComponent(deadlineSpinner)
                .addComponent(isDeadLineTimeLabel)
                .addComponent(deadLineTimeCheckBox).addComponent(deadLineTimePanel)
                .addComponent(addDeadlineButton)
                .addComponent(addedDeadlinesLabel).addComponent(deadlineScrollPane)
                .addComponent(tagLabel).addComponent(createTagButton).addComponent(tagScrollPane)
                .addComponent(contactsLabel).addComponent(createContactsButton)
                .addComponent(contactsScrollPane)
                .addComponent(websiteLabel).addComponent(websiteCheckBox).addComponent(websitePanel)
                .addComponent(collaboratedLabel).addComponent(collaboratedCheckBox)
                .addComponent(collaborationPanel)
                .addComponent(submitButton));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Optional: Smooth scrolling

        // Add the scrollPane to the dialog
        dialog.add(scrollPane);

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    public JButton createXButton() {
        ImageIcon originalIcon = new ImageIcon("img/X_button.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JButton button = new JButton();
        button.setPreferredSize(new Dimension(20, 20));
        button.setIcon(resizedIcon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addActionListener(this);
        return button;
    }

    public static void openContactsDialog(JDialog dialog) {
        JDialog contactDialog = new JDialog(dialog, "Tilføj Kontakt Person", true);
        contactDialog.setSize(300, 200);
        JPanel mainPanel = new JPanel();
        contactDialog.add(mainPanel);

        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel("Navn:");
        JTextField nameField = new JTextField();

        JLabel phoneLabel = new JLabel("Telefon:");
        JTextField phoneField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JButton submitButton = new JButton("Tilføj");
        submitButton.addActionListener(event -> {
            String contactName = "";
            contactName = nameField.getText();
            String contactPhone = "";
            contactPhone = phoneField.getText();
            String contatctEmail = "";
            contatctEmail = emailField.getText();

            // Function to replace empty fields with "N/A"
            if (contactName.isEmpty()) {
                contactName = "N/A";
            }
            if (contactPhone.isEmpty()) {
                contactPhone = "N/A";
            }
            if (contatctEmail.isEmpty()) {
                contatctEmail = "N/A";
            }

            // Validate phone number
            // Trimming of phone number to remove "+landcode and spaces"
            if (contactPhone != "N/A") {
                String trimmedContactPhone = contactPhone.trim().replaceAll("\\s+", "").replaceAll("^\\+\\d{2}", "");
                System.out.println(trimmedContactPhone);
                if (!validationUtils.isValidPhoneNumber(trimmedContactPhone)) {
                    UserFrameErrorHandling.displayPhoneError();
                    return;
                }
            }
            if (contatctEmail != "N/A") {
                if (!validationUtils.validateEmailInput(contatctEmail)) {
                    UserFrameErrorHandling.displayEmailError();
                    return;
                }
            }
            if(contactName != "N/A"){
                if(!validationUtils.isValidInput(contactName)){
                    UserFrameErrorHandling.displayNameError(validationUtils.isWithinLowerCharLimit(contactName));
                    return;
                }
            }

            tempContact = new fundContactClass(contactName, contactPhone, contatctEmail);
            contactDialog.dispose();

        });

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameLabel).addComponent(nameField)
                .addComponent(phoneLabel).addComponent(phoneField)
                .addComponent(emailLabel).addComponent(emailField)
                .addComponent(submitButton));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(nameLabel).addComponent(nameField)
                .addComponent(phoneLabel).addComponent(phoneField)
                .addComponent(emailLabel).addComponent(emailField)
                .addComponent(submitButton));

        contactDialog.setLocationRelativeTo(dialog);
        contactDialog.setVisible(true);
    }

 //KEEP DIFFERENT
    public void openProjectDialog() {
        JDialog dialog = new JDialog(frame, "Opret Projekt", true);
        dialog.setSize(700, 700);

        // Main panel with layout
        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Wrap the main panel in a scroll pane

        // Components
        JLabel nameLabel = new JLabel("Titel:*");
        JTextField nameField = new JTextField();

        JLabel purposeLabel = new JLabel("Formål:*");
        JTextField purposeField = new JTextField();

        JLabel descriptionLabel = new JLabel("Beskrivelse af projektet:*");
        JTextArea descriptionArea = new JTextArea(5, 20);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        JLabel ownerLabel = new JLabel("Ejer af projektet:*");
        JTextField ownerField = new JTextField();

        JLabel targetLabel = new JLabel("Målgruppe (hvem gavner dette projekt):*");
        JTextField targetField = new JTextField();

        JLabel budgetLabel = new JLabel("Anslået budget (kr.):*");
        JTextField budgetField = new JTextField();

        JLabel fromDateLabel = new JLabel("Fra dato:*");
        SpinnerDateModel fromDateModel = new SpinnerDateModel();
        JSpinner fromDateSpinner = new JSpinner(fromDateModel);
        JSpinner.DateEditor fromDateEditor = new JSpinner.DateEditor(fromDateSpinner, "dd/MM/yyyy");
        fromDateSpinner.setEditor(fromDateEditor);

        JLabel toDateLabel = new JLabel("Til dato:*");
        SpinnerDateModel toDateModel = new SpinnerDateModel();
        JSpinner toDateSpinner = new JSpinner(toDateModel);
        JSpinner.DateEditor toDateEditor = new JSpinner.DateEditor(toDateSpinner, "dd/MM/yyyy");
        toDateSpinner.setEditor(toDateEditor);

        JLabel activitiesLabel = new JLabel("Aktiviteter:*");
        JTextField activitiesField = new JTextField();

        // Tag creation and selection
        JLabel createTagLabel = new JLabel("Opret kategori :");
        JButton createTagButton = new JButton("Opret kategori");

        JLabel selectTagLabel = new JLabel("Vælg relevante kategorier:");
        JPanel tagPanel = new JPanel();
        tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
        JScrollPane tagScrollPane = new JScrollPane(tagPanel);

        createTagButton.addActionListener(e -> {
            String newTag = JOptionPane.showInputDialog(dialog, "Skriv en ny kategori:");
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

        for (String category : main.categories) {
            JCheckBox tagCheckBox = new JCheckBox(category);
            tagPanel.add(tagCheckBox);
        }

        JButton submitButton = new JButton("Tilføj");
        submitButton.addActionListener((ActionEvent ae) -> {
            try {
                // Get the values from the input fields
                if (!validationUtils.isWithinLowerCharLimit(nameField.getText())) {
                    isInvalidLenght = true;
                    dialog.add(UserFrameErrorHandling.displayTitleError(isInvalidLenght));
                } else {
                    tempTitle = nameField.getText();
                }

                if (!validationUtils.isWithinLowerCharLimit(purposeField.getText())) {
                    isInvalidLenght = true;
                    dialog.add(UserFrameErrorHandling.displayPurposeError(isInvalidLenght));
                } else {
                    tempPurpose = purposeField.getText();
                }

                if (!validationUtils.isWithinUpperCharLimit(descriptionArea.getText())) {
                    isInvalidLenght = true;
                    dialog.add(UserFrameErrorHandling.displayDescriptionError(isInvalidLenght));
                } else {
                    tempDescription = descriptionArea.getText();
                }

                if (!validationUtils.isWithinLowerCharLimit(ownerField.getText())) {
                    isInvalidLenght = true;
                    dialog.add(UserFrameErrorHandling.displayOwnerError(isInvalidLenght));
                } else {
                    tempOwner = ownerField.getText();
                }

                if (!validationUtils.isWithinLowerCharLimit(targetField.getText())) {
                    isInvalidLenght = true;
                    dialog.add(UserFrameErrorHandling.displayTargetAudienceError(isInvalidLenght));
                } else {
                    tempTargetAudience = targetField.getText();
                }

                String trimmedBudget = budgetField.getText().trim().replace(".", "").replace(",", "");
                if (!validationUtils.isNumericInput(trimmedBudget)) {
                    dialog.add(UserFrameErrorHandling.displayBudgetError());
                } else {
                    tempBudget = Long.parseLong(trimmedBudget);
                }

                LocalDate fromDate = ((Date) ((fromDateSpinner.getValue()))).toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate();
                LocalDate toDate = ((Date) (toDateSpinner.getValue())).toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate();
                LocalDateTime projectFromDate = fromDate.atStartOfDay();
                LocalDateTime projectToDate = toDate.atStartOfDay();

                if (!validationUtils.isWithinLowerCharLimit(activitiesField.getText())) {
                    isInvalidLenght = true;
                    dialog.add(UserFrameErrorHandling.displayActivityError(isInvalidLenght));
                } else {
                    tempActivities = activitiesField.getText();

                }

                ArrayList<String> selectedCatagories = new ArrayList<>();
                for (Component comp : tagPanel.getComponents()) {
                    if (comp instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) comp;
                        if (checkBox.isSelected()) {
                            selectedCatagories.add(checkBox.getText());
                        }
                    }
                }

                System.out.println(projectFromDate);
                System.out.println(projectToDate);
                // Create a new project proposal and add it to the list

                project project = new project(tempTitle, selectedCatagories, tempDescription, tempPurpose, tempOwner,
                        tempTargetAudience, tempBudget, projectFromDate, projectToDate, tempActivities,
                        main.getFundList(), main.getCatagoryBoolean());
                main.projectList.add(project);

                System.out.println("------------");
                System.out.println("adding project");
                for (project proj : main.projectList) {
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
                JOptionPane.showMessageDialog(dialog, "Projektet er blevet tilføjet", "Projekt tilføjet", JOptionPane.INFORMATION_MESSAGE);
                updateProjectList();
                writeAll();
                dialog.dispose();
            } catch (Exception e) {
                System.err.println("Rip openProjectDialog died");
            }
        });

        // Layout definition
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                .addComponent(nameLabel).addComponent(nameField)
                .addComponent(purposeLabel).addComponent(purposeField)
                .addComponent(descriptionLabel).addComponent(descriptionScrollPane)
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
                .addComponent(descriptionLabel).addComponent(descriptionScrollPane)
                .addComponent(ownerLabel).addComponent(ownerField)
                .addComponent(targetLabel).addComponent(targetField)
                .addComponent(budgetLabel).addComponent(budgetField)
                .addComponent(fromDateLabel).addComponent(fromDateSpinner)
                .addComponent(toDateLabel).addComponent(toDateSpinner)
                .addComponent(activitiesLabel).addComponent(activitiesField)
                .addComponent(createTagLabel).addComponent(createTagButton)
                .addComponent(selectTagLabel).addComponent(tagScrollPane)
                .addComponent(submitButton));

        JScrollPane FramescrollPane = new JScrollPane(mainPanel);
        FramescrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        FramescrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        FramescrollPane.getVerticalScrollBar().setUnitIncrement(16); // Optional: Smooth scrolling

        // Add the scrollPane to the dialog
        dialog.add(FramescrollPane);

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
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
        } else if (e.getSource() == menuButton) {
            resetButtonStates();
            cardLayout.show(cardPanel, "Main");
        } else if (e.getSource() == createProbButton) {
            openproposalProjectDialog();
        } else if (e.getSource() == createFundButton) {
            openFundDialog();
        } else if (e.getSource() == createProjectButton) {
            openProjectDialog();
        } else if(e.getSource() == createCategoriesButton){
            openCategoriesDialog();
        } else if (e.getSource() == logoutButton) {
            int confirmation = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil logge ud?", "Log ud",
                    JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
            Frontpage frontpage = new Frontpage();
            main.fundList.removeAll(sortedFundList);
            main.projectList.removeAll(sortedProjectList);
            main.proposalList.removeAll(sortedProposalList);
            main.clearCategories();
            main.clearArchive();
            

            frontpage.show();
            frame.dispose();
            }
        }

    }

}
