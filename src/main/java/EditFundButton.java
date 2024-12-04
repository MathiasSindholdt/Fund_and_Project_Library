import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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

public class EditFundButton {
//    UserFrame UI = new UserFrame();
    private JFrame frame;
    private ArrayList<fundClass> fundList;
    private JPanel tagPanel; // Declare here for broader scope
    public boolean contactEdited = false;
    public boolean collaborationEdited = false;

    public EditFundButton(JFrame frame, ArrayList<fundClass> fundList) {
        this.frame = frame;
        this.fundList = fundList;
    }


public void editFundDialog() {
    JDialog dialog = new JDialog(frame, "Rediger Fond", true);
    dialog.setSize(800, 600);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GroupLayout(mainPanel));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    GroupLayout layout = new GroupLayout(mainPanel);
    mainPanel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    JLabel selectFundLabel = new JLabel("Vælg fond:");
    JComboBox<String> fundSelector = new JComboBox<>();
    for (fundClass fund : fundList) {
        fundSelector.addItem(fund.getTitle());
    }

    // Fond Titel
    JLabel nameLabel = new JLabel("Titel:*");
    JTextField nameField = new JTextField();

    JLabel descriptionLabel = new JLabel("Beskrivelse:*");
    JTextArea descriptionArea = new JTextArea(5, 20);
    JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

    // Fond Beløb Fra og Til
    JLabel amountFromLabel = new JLabel("Beløb fra:*");
    JTextField amountFrom = new JTextField();

    JLabel amountToLabel = new JLabel("Beløb til:*");
    JTextField amountTo = new JTextField();

       // Fond Deadline
    JLabel deadlineLabel = new JLabel("Ansøgningsfrist:");
    SpinnerDateModel deadlineModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
    JSpinner deadlineSpinner = new JSpinner(deadlineModel);
    JSpinner.DateEditor deadlineEditor = new JSpinner.DateEditor(deadlineSpinner, "dd/MM/yyyy");
    deadlineSpinner.setEditor(deadlineEditor);
    deadlineSpinner.setValue(Date.from(Instant.now())); // Set default time to 00:00

    // Tilføjede Deadlines Panel
    JLabel addedDeadlinesLabel = new JLabel("Tilføjede ansøgningsfrister:*");
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
        try {
            LocalDateTime newDeadline = ((java.util.Date) deadlineSpinner.getValue())
                .toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate()
                .atTime(23, 59);
            
            // Check for duplicates
            if (addedDeadlines.stream().anyMatch(d -> d.toLocalDate().equals(newDeadline.toLocalDate()))) {
                JOptionPane.showMessageDialog(dialog, "Fristen er allerede tilføjet.");
                return;
            }
    
            // Add the new deadline
            addedDeadlines.add(newDeadline);
            System.out.println("Added Deadline: " + newDeadline);
    
            // Create a panel to hold the deadline label and the remove button
            JPanel deadlinePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel deadlineLabelItem = new JLabel(newDeadline.format(formatter));
            JButton xButton = new JButton("X");
            
            // Add action listener to the remove button
            xButton.addActionListener(removeEvent -> {
                addedDeadlines.remove(newDeadline); // Remove deadline
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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dialog, "Der opstod en fejl ved tilføjelse af fristen: " + ex.getMessage());
            ex.printStackTrace();
        }
    });
    

    JLabel websiteLabel = new JLabel("Hjemmeside?:");
    JCheckBox websiteCheckBox = new JCheckBox();
    JPanel websitePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel websiteLabel2 = new JLabel("Hjemmeside:");
    JTextField websiteField = new JTextField(30);
    websitePanel.add(websiteLabel2);
    websitePanel.add(websiteField);
    websitePanel.setVisible(false);

    websiteCheckBox.addItemListener(e -> {
        websitePanel.setVisible(websiteCheckBox.isSelected());
        dialog.revalidate();
        dialog.repaint();
    });


    JLabel selectTagLabel = new JLabel("Vælg relevante kategorier:");
    JPanel tagPanel = new JPanel();
    tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
    JScrollPane tagScrollPane = new JScrollPane(tagPanel);
    
    // Populate categories with checkboxes
    fundSelector.addActionListener(e -> {
        String selectedFundTitle = (String) fundSelector.getSelectedItem();
        fundClass selectedFund = fundList.stream()
            .filter(fund -> fund.getTitle().equals(selectedFundTitle))
            .findFirst()
            .orElse(null);
    
        if (selectedFund != null) {
            // Clear the panel before repopulating
            tagPanel.removeAll();
    
            for (String category : main.categories) {
                JCheckBox tagCheckBox = new JCheckBox(category);
    
                // Check if the fund already has this category
                if (selectedFund.getCategories().contains(category)) {
                    tagCheckBox.setSelected(true);
                }
    
                // Add the checkbox to the panel
                tagPanel.add(tagCheckBox);
            }
    
            // Refresh the panel to display updated checkboxes
            tagPanel.revalidate();
            tagPanel.repaint();
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
        contactEdited = true;
        UserFrame.openContactsDialog(dialog);
        System.out.println(UserFrame.tempContact.getContactName());
        System.out.println(UserFrame.tempContact.getContactPhoneNumber());
        System.out.println(UserFrame.tempContact.getContactEmail());

        JButton removeContactButton = new JButton("X");
        JPanel removeContactPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel contactInfo = new JLabel(UserFrame.tempContact.getContactName() + " - " + UserFrame.tempContact.getContactPhoneNumber()
                + " - " + UserFrame.tempContact.getContactEmail());
        removeContactPanel.add(contactInfo);
        removeContactPanel.add(removeContactButton);
        contactsPanel.add(removeContactPanel);
        contactsPanel.revalidate();
        contactsPanel.repaint();
        UserFrame.contacts.add(UserFrame.tempContact);

        final fundContactClass contactToRemove = UserFrame.tempContact;
        removeContactButton.addActionListener(removeEvent -> {
            System.out.println("Removing contact: " + contactToRemove.getContactName());
            UserFrame.contacts.remove(contactToRemove);
            contactsPanel.remove(removeContactPanel);

            contactsPanel.revalidate();
            contactsPanel.repaint();
        });

    });
    
    boolean isCollaborated = true;
    // Previous Collaborations section
    JLabel collaboratedLabel = new JLabel("Tidligere samarbejde?:");
    JCheckBox collaboratedCheckBox = new JCheckBox();
    collaboratedCheckBox.setSelected(isCollaborated);
    JPanel collaborationPanel = new JPanel();
    collaborationPanel.setLayout(new BoxLayout(collaborationPanel, BoxLayout.Y_AXIS));
    JLabel collaborationLabel = new JLabel("Rediger tidligere samarbejdsprojekter:");
    JButton createCollaborationButton = new JButton("Tilføj Projekter");
    JPanel collaborationContentPanel = new JPanel();
    collaborationContentPanel.setLayout(new BoxLayout(collaborationContentPanel, BoxLayout.Y_AXIS));
    JScrollPane collaborationScrollPane = new JScrollPane(collaborationContentPanel);
    collaborationScrollPane.setPreferredSize(new Dimension(200, 100));
/*
    // Populate existing collaborated projects
    for (String project : getSelectedCollaboration()) {
        JCheckBox checkBox = new JCheckBox(project);
        checkBox.setSelected(true);
        collaborationContentPanel.add(checkBox);
    }
*/
    // Populate current projects
    getCurrentCheckboxes.getAllProjects(collaborationContentPanel);

    collaboratedCheckBox.addItemListener(e -> {
        collaborationEdited = true;
        collaborationPanel.setVisible(collaboratedCheckBox.isSelected());
        dialog.revalidate();
        dialog.repaint();
    });

    createCollaborationButton.addActionListener(e -> {
        String newCollaboration = JOptionPane.showInputDialog(dialog, "Skriv nye tidligere samarbejdeprojekter:");
        if (newCollaboration != null && !newCollaboration.trim().isEmpty()) {
            main.userProjectList.add(newCollaboration);
            JCheckBox checkBox = new JCheckBox(newCollaboration);
            checkBox.setSelected(true);
            collaborationContentPanel.add(checkBox);
            collaborationContentPanel.revalidate();
            collaborationContentPanel.repaint();
        }
    });

    // Add collaboration panel components
    JPanel collaborationLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    collaborationLabelPanel.add(collaborationLabel);
    collaborationLabelPanel.add(createCollaborationButton);
    collaborationPanel.add(collaborationLabelPanel);
    collaborationPanel.add(collaborationScrollPane);
    collaborationPanel.setVisible(isCollaborated);

    // Løbende deadline section
    JLabel runningLabel = new JLabel("Løbende ansøgningsfrist:");
    JCheckBox runningCheckBox = new JCheckBox();
    runningCheckBox.addItemListener(e -> {
        boolean isRunning = runningCheckBox.isSelected();
        
        // Hide deadlines and disable the spinner and add button
        deadlineSpinner.setEnabled(!isRunning);
        addDeadlineButton.setEnabled(!isRunning);
        deadlineListPanel.setVisible(!isRunning);
        addedDeadlinesLabel.setVisible(!isRunning);
        
        // Revalidate and repaint for dynamic updates
        dialog.revalidate();
        dialog.repaint();
    });
    



   // Populating fields based on the selected fund
   fundSelector.addActionListener(e -> {
    String selectedFundTitle = (String) fundSelector.getSelectedItem();
    fundClass selectedFund = fundList.stream()
        .filter(fund -> fund.getTitle().equals(selectedFundTitle))
        .findFirst()
        .orElse(null);

    if (selectedFund != null) {
        // Clear existing deadlines in the UI
        deadlineListPanel.removeAll();

        // Set the title, description, and amounts
        nameField.setText(selectedFund.getTitle());
        descriptionArea.setText(selectedFund.getDescription());
        amountFrom.setText(String.valueOf(selectedFund.getBudgetMin()));
        amountTo.setText(String.valueOf(selectedFund.getBudgetMax()));
    
        // Prepopulate the website field if the fund has a website
        if (selectedFund.getFundWebsite() != null && !selectedFund.getFundWebsite().isEmpty()) {
            websiteCheckBox.setSelected(true);
            websitePanel.setVisible(true);
            websiteField.setText(selectedFund.getFundWebsite());
        } else {
            websiteCheckBox.setSelected(false);
            websitePanel.setVisible(false);
            websiteField.setText("");
        }
    
        // Handle deadlines
        addedDeadlines.clear(); // Clear the list of added deadlines
        
        for (LocalDateTime deadline : selectedFund.getDeadlines()) {
            // Add each existing deadline to the list and to the panel
            addedDeadlines.add(deadline);
            // Create a panel for each deadline
            JPanel deadlinePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel deadlineLabelItem = new JLabel(deadline.format(formatter));
            JButton removeButton = new JButton("X");
            removeButton.addActionListener(removeEvent -> {
                // Remove the deadline
                addedDeadlines.remove(deadline);
                deadlineListPanel.remove(deadlinePanel);
                deadlineListPanel.revalidate();
                deadlineListPanel.repaint();
            });

            deadlinePanel.add(deadlineLabelItem);
            deadlinePanel.add(removeButton);
            deadlineListPanel.add(deadlinePanel);
        }

        // Revalidate and repaint to show updated deadlines
        deadlineListPanel.revalidate();
        deadlineListPanel.repaint();
    }
    //Load the selected fund's contacts
    for(int i = 0; i < selectedFund.getContacts().size(); i++){
        JButton removeContactButton = new JButton("X");
        JPanel removeContactPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel contactInfo = new JLabel(selectedFund.getContacts().get(i).getContactName() + " - " + selectedFund.getContacts().get(i).getContactPhoneNumber()
                + " - " + selectedFund.getContacts().get(i).getContactEmail());
        removeContactPanel.add(contactInfo);
        removeContactPanel.add(removeContactButton);
        contactsPanel.add(removeContactPanel);
        contactsPanel.revalidate();
        contactsPanel.repaint();
        final fundContactClass contactToRemove = selectedFund.getContacts().get(i);
        removeContactButton.addActionListener(removeEvent -> {
        System.out.println("Removing contact: " + contactToRemove.getContactName());
        UserFrame.contacts.remove(contactToRemove);
        contactsPanel.remove(removeContactPanel);
        contactsPanel.revalidate();
        contactsPanel.repaint();
    });

    }
});




    // Submit Button to Save Changes
    JButton saveButton = new JButton("Gem Ændringer");
    System.out.println("Gem ændringer");
    saveButton.addActionListener(event -> {
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Er du sikker på, at du vil redigere dette element?",
            "Bekræft redigering",
            JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        String selectedFundTitle = (String) fundSelector.getSelectedItem();
        fundClass selectedFund = fundList.stream()
            .filter(fund -> fund.getTitle().equals(selectedFundTitle))
            .findFirst()
            .orElse(null);

    
    
    
        if (selectedFund != null) {
            boolean hasError = false;
            
           System.out.println(selectedFund);

            // Validate and update fields
            if (!validationUtils.isWithinLowerCharLimit(nameField.getText())) {
                dialog.add(UserFrameErrorHandling.displayTitleError(false));
                hasError = true;
            } else {
                selectedFund.setTitle(nameField.getText().trim());
            }
    
            if (!validationUtils.isWithinUpperCharLimit(descriptionArea.getText())) {
                dialog.add(UserFrameErrorHandling.displayDescriptionError(false));
                hasError = true;
            } else {
                selectedFund.setDescription(descriptionArea.getText().trim());
            }
    
            if (!validationUtils.isNumericInput(amountFrom.getText())) {
                dialog.add(UserFrameErrorHandling.displayAmountFromError());
                hasError = true;
            } else {
                Long tempLong = Long.parseLong(amountFrom.getText().trim());
                selectedFund.setBudgetMin(tempLong);
            }
    
            if (!validationUtils.isNumericInput(amountTo.getText())) {
                dialog.add(UserFrameErrorHandling.displayAmountToError());
                hasError = true;
            } else {
                Long tempLong = Long.parseLong(amountTo.getText().trim());
                selectedFund.setBudgetMax(tempLong);
            }
            // Update the fund deadlines if running is not selected
            if (!runningCheckBox.isSelected()) {
                selectedFund.clearDeadlines(); // Clear all existing deadlines once
                for (LocalDateTime deadline : addedDeadlines) {
                    selectedFund.setDeadlines(deadline); // Append each deadline
                }
            }
            // Update deadlines
            selectedFund.clearDeadlines();
            for(int i = 0; i < addedDeadlines.size() ; i++){
                selectedFund.setDeadlines(addedDeadlines.get(i));
            }

            
            // Update categories
            ArrayList<String> selectedCategories = new ArrayList<>();
            for (Component comp : tagPanel.getComponents()) {
                if (comp instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) comp;
                    if (checkBox.isSelected()) {
                        selectedCategories.add(checkBox.getText());
                    }
                }
            }
            selectedFund.getCategories().clear();
            selectedFund.getCategories().addAll(selectedCategories);

            if(websiteCheckBox.isSelected()){
                if(!validationUtils.isValidUrl(websiteField.getText())){
                    dialog.add(UserFrameErrorHandling.displayWebsiteError());
                    hasError = true;
                    return;
                }else{
                    selectedFund.setfundWebsite(websiteField.getText().trim());
                }
            }else{
                selectedFund.setfundWebsite(""); // If checkbox is unchecked, clear website
            }

            // Update running status
            selectedFund.setRunning(runningCheckBox.isSelected());

            List<String> selectedCollaboration = new ArrayList<>();
            for (Component comp : collaborationContentPanel.getComponents()) {
                if (comp instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) comp;
                    if (checkBox.isSelected()) {
                        selectedCollaboration.add(checkBox.getText());
                    }
                }
            }
            if(runningCheckBox.isSelected()){
                selectedFund.getDeadlines().clear();
                selectedFund.setDeadlines(LocalDateTime.of(3000, 1, 1, 0, 0));
            }

            //Update the selected fund's contacts
            if(contactEdited){
                selectedFund.getContacts().clear();
                selectedFund.getContacts().addAll(UserFrame.contacts);
            }
            //Update the selected fund's contacts
           
            
            // Update the selected fund's collaboration history
            if(collaborationEdited){
                selectedFund.getCollaborationHistory().clear();
                selectedFund.getCollaborationHistory().addAll(selectedCollaboration);
            }
            // If no errors, save the updated fund
            if (!hasError) {
               System.out.println("No errors, updating fund-----------------");
                System.out.println(selectedFund.getTitle());
                System.out.println(selectedFund.getDescription());
                System.out.println(selectedFund.getBudgetMin());
                System.out.println(selectedFund.getBudgetMax());
                System.out.println(selectedFund.getDeadlines());
                System.out.println(selectedFund.getRunning());
                System.out.println(selectedFund.getCategories());
                System.out.println(selectedFund.getCollaborationHistory());
                for (int i = 0; i<selectedFund.getContacts().size();i++) {
                   System.out.println(selectedFund.getContacts().get(i).getContactName() + "-" + selectedFund.getContacts().get(i).getContactPhoneNumber() + "-" + selectedFund.getContacts().get(i).getContactEmail());
                }
                System.out.println(selectedFund.getFundWebsite());
                System.out.println("---------------------");
                //UserFrame.updateFundList();
                //Remove the fund currently being edited from the fundlist
                JOptionPane.showMessageDialog(dialog, "Fonden er blevet opdateret");
                FundCsvWriter.writeCsv("data/funds.csv", fundList);
                dialog.dispose();
            }
        }
    }
    
        });



/* 
    // Contact Person(s) section
    JLabel contactsLabel = new JLabel("Kontakt person(er):");
    JButton createContactsButton = new JButton("Tilføj Kontakt person(er)");
    JPanel contactsPanel = new JPanel();
    contactsPanel.setLayout(new BoxLayout(contactsPanel, BoxLayout.Y_AXIS));
    JScrollPane contactsScrollPane = new JScrollPane(contactsPanel);
    contactsScrollPane.setPreferredSize(new Dimension(200, 100));
    
    createContactsButton.addActionListener(e -> {
        openContactsDialog(dialog);
        // Populate contacts panel with newly added contacts
        for (int i = 0; i < tempContacts.size(); i++) {
            contactsPanel.add(new JLabel(tempContacts.get(i).getContactName() + " - " +
                    tempContacts.get(i).getContactPhoneNumber() + " - " + tempContacts.get(i).getContactEmail()));
        }
        contactsPanel.revalidate();
        contactsPanel.repaint();
    });
    */

    // GroupLayout structure
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(selectFundLabel).addComponent(fundSelector)
        .addComponent(nameLabel).addComponent(nameField)
        .addComponent(descriptionLabel).addComponent(descriptionScrollPane)
        .addComponent(amountFromLabel).addComponent(amountFrom)
        .addComponent(amountToLabel).addComponent(amountTo)
        .addComponent(runningLabel).addComponent(runningCheckBox)
        .addComponent(deadlineLabel)
        .addComponent(deadlineSpinner)
        .addComponent(isDeadLineTimeLabel)
        .addComponent(deadLineTimeCheckBox)
        .addComponent(deadLineTimePanel)
        .addComponent(addedDeadlinesLabel)
        .addComponent(deadlineScrollPane)
        .addComponent(addDeadlineButton)     
        .addComponent(contactsLabel).addComponent(createContactsButton)
        .addComponent(contactsScrollPane)
        //  .addComponent(contactsLabel).addComponent(createContactsButton).addComponent(contactsScrollPane)
        .addComponent(selectTagLabel).addComponent(tagScrollPane)
        .addComponent(websiteLabel).addComponent(websiteCheckBox).addComponent(websitePanel)
        .addComponent(collaboratedLabel).addComponent(collaboratedCheckBox)
        .addComponent(collaborationPanel)
        .addComponent(saveButton)
    );

    layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(selectFundLabel).addComponent(fundSelector)
        .addComponent(nameLabel).addComponent(nameField)
        .addComponent(descriptionLabel).addComponent(descriptionScrollPane)
        .addComponent(amountFromLabel).addComponent(amountFrom)
        .addComponent(amountToLabel).addComponent(amountTo)
        .addComponent(runningLabel).addComponent(runningCheckBox)
        .addComponent(deadlineLabel)
        .addComponent(deadlineSpinner)
        .addComponent(isDeadLineTimeLabel)
        .addComponent(deadLineTimeCheckBox)
        .addComponent(deadLineTimePanel)
        .addComponent(addedDeadlinesLabel)
        .addComponent(deadlineScrollPane)
        .addComponent(addDeadlineButton)  
        .addComponent(contactsLabel).addComponent(createContactsButton)
        .addComponent(contactsScrollPane)  
        .addComponent(selectTagLabel).addComponent(tagScrollPane)
        .addComponent(websiteLabel).addComponent(websiteCheckBox).addComponent(websitePanel)
        .addComponent(collaboratedLabel).addComponent(collaboratedCheckBox)
        .addComponent(collaborationPanel)
        .addComponent(saveButton)
    );

    JScrollPane scrollPane = new JScrollPane(mainPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    dialog.add(scrollPane);

    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);
}


public void editFundDialog(fundClass loadedFund) {
    JDialog dialog = new JDialog(frame, "Rediger Fond", true);
    dialog.setSize(700, 600);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GroupLayout(mainPanel));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    GroupLayout layout = new GroupLayout(mainPanel);
    mainPanel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    // Fond Titel
    JLabel nameLabel = new JLabel("Titel:*");
    JTextField nameField = new JTextField();

    JLabel descriptionLabel = new JLabel("Beskrivelse:*");
    JTextArea descriptionArea = new JTextArea(5, 20);
    JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

    // Fond Beløb Fra og Til
    JLabel amountFromLabel = new JLabel("Beløb fra:*");
    JTextField amountFrom = new JTextField();

    JLabel amountToLabel = new JLabel("Beløb til:*");
    JTextField amountTo = new JTextField();

       // Fond Deadline
    JLabel deadlineLabel = new JLabel("Ansøgningsfrist:");
    SpinnerDateModel deadlineModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
    JSpinner deadlineSpinner = new JSpinner(deadlineModel);
    JSpinner.DateEditor deadlineEditor = new JSpinner.DateEditor(deadlineSpinner, "dd/MM/yyyy");
    deadlineSpinner.setEditor(deadlineEditor);
    deadlineSpinner.setValue(Date.from(Instant.now())); // Set default time to 00:00

    // Tilføjede Deadlines Panel
    JLabel addedDeadlinesLabel = new JLabel("Tilføjede ansøgningsfrister:*");
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
        try {
            LocalDateTime newDeadline = ((java.util.Date) deadlineSpinner.getValue())
                .toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate()
                .atTime(23, 59);
            
            // Check for duplicates
            if (addedDeadlines.stream().anyMatch(d -> d.toLocalDate().equals(newDeadline.toLocalDate()))) {
                JOptionPane.showMessageDialog(dialog, "Fristen er allerede tilføjet.");
                return;
            }
    
            // Add the new deadline
            addedDeadlines.add(newDeadline);
            System.out.println("Added Deadline: " + newDeadline);
    
            // Create a panel to hold the deadline label and the remove button
            JPanel deadlinePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel deadlineLabelItem = new JLabel(newDeadline.format(formatter));
            JButton xButton = new JButton("X");
            
            // Add action listener to the remove button
            xButton.addActionListener(removeEvent -> {
                addedDeadlines.remove(newDeadline); // Remove deadline
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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dialog, "Der opstod en fejl ved tilføjelse af fristen: " + ex.getMessage());
            ex.printStackTrace();
        }
    });
    

    JLabel websiteLabel = new JLabel("Hjemmeside?:");
    JCheckBox websiteCheckBox = new JCheckBox();
    JPanel websitePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel websiteLabel2 = new JLabel("Hjemmeside:");
    JTextField websiteField = new JTextField(30);
    websitePanel.add(websiteLabel2);
    websitePanel.add(websiteField);
    websitePanel.setVisible(false);

    websiteCheckBox.addItemListener(e -> {
        websitePanel.setVisible(websiteCheckBox.isSelected());
        dialog.revalidate();
        dialog.repaint();
    });


    JLabel selectTagLabel = new JLabel("Vælg relevante kategorier:");
    JPanel tagPanel = new JPanel();
    tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
    JScrollPane tagScrollPane = new JScrollPane(tagPanel);
    
    // Clear the panel before repopulating
    tagPanel.removeAll();

    for (String category : main.categories) {
        JCheckBox tagCheckBox = new JCheckBox(category);

        // Check if the fund already has this category
        if (loadedFund.getCategories().contains(category)) {
            tagCheckBox.setSelected(true);
        }
         // Add the checkbox to the panel
        tagPanel.add(tagCheckBox);
    }

    // Refresh the panel to display updated checkboxes
    tagPanel.revalidate();
    tagPanel.repaint();


    // Kontakt person(er)
    JLabel contactsLabel = new JLabel("Kontakt person(er):");
    JButton createContactsButton = new JButton("Tilføj Kontakt person(er)");
    JPanel contactsPanel = new JPanel();
    contactsPanel.setLayout(new BoxLayout(contactsPanel, BoxLayout.Y_AXIS));
    JScrollPane contactsScrollPane = new JScrollPane(contactsPanel);
    contactsScrollPane.setPreferredSize(new Dimension(200, 100));
    createContactsButton.addActionListener(e -> {
        contactEdited = true;
        UserFrame.openContactsDialog(dialog);
        System.out.println(UserFrame.tempContact.getContactName());
        System.out.println(UserFrame.tempContact.getContactPhoneNumber());
        System.out.println(UserFrame.tempContact.getContactEmail());

        JButton removeContactButton = new JButton("X");
        JPanel removeContactPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel contactInfo = new JLabel(UserFrame.tempContact.getContactName() + " - " + UserFrame.tempContact.getContactPhoneNumber()
                + " - " + UserFrame.tempContact.getContactEmail());
        removeContactPanel.add(contactInfo);
        removeContactPanel.add(removeContactButton);
        contactsPanel.add(removeContactPanel);
        contactsPanel.revalidate();
        contactsPanel.repaint();
        UserFrame.contacts.add(UserFrame.tempContact);

        final fundContactClass contactToRemove = UserFrame.tempContact;
        removeContactButton.addActionListener(removeEvent -> {
            System.out.println("Removing contact: " + contactToRemove.getContactName());
            UserFrame.contacts.remove(contactToRemove);
            contactsPanel.remove(removeContactPanel);

            contactsPanel.revalidate();
            contactsPanel.repaint();
        });

    });
    
    boolean isCollaborated = true;
    // Previous Collaborations section
    JLabel collaboratedLabel = new JLabel("Tidligere samarbejde?:");
    JCheckBox collaboratedCheckBox = new JCheckBox();
    collaboratedCheckBox.setSelected(isCollaborated);
    JPanel collaborationPanel = new JPanel();
    collaborationPanel.setLayout(new BoxLayout(collaborationPanel, BoxLayout.Y_AXIS));
    JLabel collaborationLabel = new JLabel("Rediger tidligere samarbejdsprojekter:");
    JButton createCollaborationButton = new JButton("Tilføj Projekter");
    JPanel collaborationContentPanel = new JPanel();
    collaborationContentPanel.setLayout(new BoxLayout(collaborationContentPanel, BoxLayout.Y_AXIS));
    JScrollPane collaborationScrollPane = new JScrollPane(collaborationContentPanel);
    collaborationScrollPane.setPreferredSize(new Dimension(200, 100));

    // Populate current projects
    getCurrentCheckboxes.getAllProjects(collaborationContentPanel);

    collaboratedCheckBox.addItemListener(e -> {
        collaborationEdited = true;
        collaborationPanel.setVisible(collaboratedCheckBox.isSelected());
        dialog.revalidate();
        dialog.repaint();
    });

    createCollaborationButton.addActionListener(e -> {
        String newCollaboration = JOptionPane.showInputDialog(dialog, "Skriv nye tidligere samarbejdeprojekter:");
        if (newCollaboration != null && !newCollaboration.trim().isEmpty()) {
            main.userProjectList.add(newCollaboration);
            JCheckBox checkBox = new JCheckBox(newCollaboration);
            checkBox.setSelected(true);
            collaborationContentPanel.add(checkBox);
            collaborationContentPanel.revalidate();
            collaborationContentPanel.repaint();
        }
    });

    // Add collaboration panel components
    JPanel collaborationLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    collaborationLabelPanel.add(collaborationLabel);
    collaborationLabelPanel.add(createCollaborationButton);
    collaborationPanel.add(collaborationLabelPanel);
    collaborationPanel.add(collaborationScrollPane);
    collaborationPanel.setVisible(isCollaborated);

    // Løbende deadline section
    JLabel runningLabel = new JLabel("Løbende ansøgningsfrist:");
    JCheckBox runningCheckBox = new JCheckBox();
    runningCheckBox.addItemListener(e -> {
        boolean isRunning = runningCheckBox.isSelected();
        
        // Hide deadlines and disable the spinner and add button
        deadlineSpinner.setEnabled(!isRunning);
        addDeadlineButton.setEnabled(!isRunning);
        deadlineListPanel.setVisible(!isRunning);
        addedDeadlinesLabel.setVisible(!isRunning);
        
        // Revalidate and repaint for dynamic updates
        dialog.revalidate();
        dialog.repaint();
    });
    

    nameField.setText(loadedFund.getTitle());
    descriptionArea.setText(loadedFund.getDescription());
    amountFrom.setText(String.valueOf(loadedFund.getBudgetMin()));
    amountTo.setText(String.valueOf(loadedFund.getBudgetMax()));

    if (loadedFund.getFundWebsite() != null && !loadedFund.getFundWebsite().isEmpty()) {
        websiteCheckBox.setSelected(true);
        websitePanel.setVisible(true);
        websiteField.setText(loadedFund.getFundWebsite());
    } else {
        websiteCheckBox.setSelected(false);
        websitePanel.setVisible(false);
        websiteField.setText("");
    }   
    // Handle deadlines
    addedDeadlines.clear(); // Clear the list of added deadlines
    for (LocalDateTime deadline : loadedFund.getDeadlines()) {
        // Add each existing deadline to the list and to the panel
        addedDeadlines.add(deadline);  
        // Create a panel for each deadline
        JPanel deadlinePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel deadlineLabelItem = new JLabel(deadline.format(formatter));
        JButton removeButton = new JButton("X");
        removeButton.addActionListener(removeEvent -> {
            // Remove the deadline
            addedDeadlines.remove(deadline);
            deadlineListPanel.remove(deadlinePanel);
            deadlineListPanel.revalidate();
            deadlineListPanel.repaint();
        });

        deadlinePanel.add(deadlineLabelItem);
        deadlinePanel.add(removeButton);
        deadlineListPanel.add(deadlinePanel);
        }

        // Revalidate and repaint to show updated deadlines
        deadlineListPanel.revalidate();
        deadlineListPanel.repaint();
    
    //Load the selected fund's contacts
    for(int i = 0; i < loadedFund.getContacts().size(); i++){
        JButton removeContactButton = new JButton("X");
        JPanel removeContactPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel contactInfo = new JLabel(loadedFund.getContacts().get(i).getContactName() + " - " + loadedFund.getContacts().get(i).getContactPhoneNumber()
                + " - " + loadedFund.getContacts().get(i).getContactEmail());
        removeContactPanel.add(contactInfo);
        removeContactPanel.add(removeContactButton);
        contactsPanel.add(removeContactPanel);
        contactsPanel.revalidate();
        contactsPanel.repaint();
        final fundContactClass contactToRemove = loadedFund.getContacts().get(i);
        removeContactButton.addActionListener(removeEvent -> {
        System.out.println("Removing contact: " + contactToRemove.getContactName());
        UserFrame.contacts.remove(contactToRemove);
        contactsPanel.remove(removeContactPanel);
        contactsPanel.revalidate();
        contactsPanel.repaint();
    });

    }




    // Submit Button to Save Changes
    JButton saveButton = new JButton("Gem Ændringer");
    System.out.println("Gem ændringer");
    saveButton.addActionListener(event -> {
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Er du sikker på, at du vil redigere dette element?",
            "Bekræft redigering",
            JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        
        boolean hasError = false;
        

        // Validate and update fields
        if (!validationUtils.isWithinLowerCharLimit(nameField.getText())) {
            dialog.add(UserFrameErrorHandling.displayTitleError(false));
            hasError = true;
        } else {
            loadedFund.setTitle(nameField.getText().trim());
        }

        if (!validationUtils.isWithinUpperCharLimit(descriptionArea.getText())) {
            dialog.add(UserFrameErrorHandling.displayDescriptionError(false));
            hasError = true;
        } else {
            loadedFund.setDescription(descriptionArea.getText().trim());
        }

        if (!validationUtils.isNumericInput(amountFrom.getText())) {
            dialog.add(UserFrameErrorHandling.displayAmountFromError());
            hasError = true;
        } else {
            Long tempLong = Long.parseLong(amountFrom.getText().trim());
            loadedFund.setBudgetMin(tempLong);
        }

        if (!validationUtils.isNumericInput(amountTo.getText())) {
            dialog.add(UserFrameErrorHandling.displayAmountToError());
            hasError = true;
        } else {
            Long tempLong = Long.parseLong(amountTo.getText().trim());
            loadedFund.setBudgetMax(tempLong);
        }
        // Update the fund deadlines if running is not selected
        if (!runningCheckBox.isSelected()) {
            loadedFund.clearDeadlines(); // Clear all existing deadlines once
            for (LocalDateTime deadline : addedDeadlines) {
                loadedFund.setDeadlines(deadline); // Append each deadline
            }
        }
        // Update deadlines
        loadedFund.clearDeadlines();
        for(int i = 0; i < addedDeadlines.size() ; i++){
            loadedFund.setDeadlines(addedDeadlines.get(i));
        }

        
        // Update categories
        ArrayList<String> selectedCategories = new ArrayList<>();
        for (Component comp : tagPanel.getComponents()) {
            if (comp instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) comp;
                if (checkBox.isSelected()) {
                    selectedCategories.add(checkBox.getText());
                }
            }
        }
        loadedFund.getCategories().clear();
        loadedFund.getCategories().addAll(selectedCategories);

        if(websiteCheckBox.isSelected()){
            if(!validationUtils.isValidUrl(websiteField.getText())){
                dialog.add(UserFrameErrorHandling.displayWebsiteError());
                hasError = true;
                return;
            }else{
                loadedFund.setfundWebsite(websiteField.getText().trim());
            }
        }else{
            loadedFund.setfundWebsite(""); // If checkbox is unchecked, clear website
        }

        // Update running status
        loadedFund.setRunning(runningCheckBox.isSelected());

        List<String> selectedCollaboration = new ArrayList<>();
        for (Component comp : collaborationContentPanel.getComponents()) {
            if (comp instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) comp;
                if (checkBox.isSelected()) {
                    selectedCollaboration.add(checkBox.getText());
                }
            }
        }
        if(runningCheckBox.isSelected()){
            loadedFund.getDeadlines().clear();
            loadedFund.setDeadlines(LocalDateTime.of(3000, 1, 1, 0, 0));
        }


        if(contactEdited){
            loadedFund.getContacts().clear();
            loadedFund.getContacts().addAll(UserFrame.contacts);
        }
        //Update the selected fund's contacts
       
        
        // Update the selected fund's collaboration history
        if(collaborationEdited){
            loadedFund.getCollaborationHistory().clear();
            loadedFund.getCollaborationHistory().addAll(selectedCollaboration);
        }

        // If no errors, save the updated fund
        if (!hasError) {
            System.out.println("No errors, updating fund-----------------");
            System.out.println(loadedFund.getTitle());
            System.out.println(loadedFund.getDescription());
            System.out.println(loadedFund.getBudgetMin());
            System.out.println(loadedFund.getBudgetMax());
            System.out.println(loadedFund.getDeadlines());
            System.out.println(loadedFund.getRunning());
            System.out.println(loadedFund.getCategories());
            System.out.println(loadedFund.getCollaborationHistory());
            for (int i = 0; i<loadedFund.getContacts().size();i++) {
                System.out.println(loadedFund.getContacts().get(i).getContactName() + "-" + loadedFund.getContacts().get(i).getContactPhoneNumber() + "-" + loadedFund.getContacts().get(i).getContactEmail());
            }
            System.out.println(loadedFund.getFundWebsite());
            System.out.println("---------------------");
            //UserFrame.updateFundList();
            //Remove the fund currently being edited from the fundlist
          ///  FundCsvWriter.writeCsv("data/funds.csv", fundList);
            JOptionPane.showMessageDialog(dialog, "Fonden er blevet opdateret");

            dialog.dispose();
        }
    }
    });



/* 
    // Contact Person(s) section
    JLabel contactsLabel = new JLabel("Kontakt person(er):");
    JButton createContactsButton = new JButton("Tilføj Kontakt person(er)");
    JPanel contactsPanel = new JPanel();
    contactsPanel.setLayout(new BoxLayout(contactsPanel, BoxLayout.Y_AXIS));
    JScrollPane contactsScrollPane = new JScrollPane(contactsPanel);
    contactsScrollPane.setPreferredSize(new Dimension(200, 100));
    
    createContactsButton.addActionListener(e -> {
        openContactsDialog(dialog);
        // Populate contacts panel with newly added contacts
        for (int i = 0; i < tempContacts.size(); i++) {
            contactsPanel.add(new JLabel(tempContacts.get(i).getContactName() + " - " +
                    tempContacts.get(i).getContactPhoneNumber() + " - " + tempContacts.get(i).getContactEmail()));
        }
        contactsPanel.revalidate();
        contactsPanel.repaint();
    });
    */

    // GroupLayout structure
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(nameLabel).addComponent(nameField)
        .addComponent(descriptionLabel).addComponent(descriptionScrollPane)
        .addComponent(amountFromLabel).addComponent(amountFrom)
        .addComponent(amountToLabel).addComponent(amountTo)
        .addComponent(runningLabel).addComponent(runningCheckBox)
        .addComponent(deadlineLabel)
        .addComponent(deadlineSpinner)
        .addComponent(isDeadLineTimeLabel)
        .addComponent(deadLineTimeCheckBox)
        .addComponent(deadLineTimePanel)
        .addComponent(addedDeadlinesLabel)
        .addComponent(deadlineScrollPane)
        .addComponent(addDeadlineButton)     
        .addComponent(contactsLabel).addComponent(createContactsButton)
        .addComponent(contactsScrollPane)
        //  .addComponent(contactsLabel).addComponent(createContactsButton).addComponent(contactsScrollPane)
        .addComponent(selectTagLabel).addComponent(tagScrollPane)
        .addComponent(websiteLabel).addComponent(websiteCheckBox).addComponent(websitePanel)
        .addComponent(collaboratedLabel).addComponent(collaboratedCheckBox)
        .addComponent(collaborationPanel)
        .addComponent(saveButton)
    );

    layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(nameLabel).addComponent(nameField)
        .addComponent(descriptionLabel).addComponent(descriptionScrollPane)
        .addComponent(amountFromLabel).addComponent(amountFrom)
        .addComponent(amountToLabel).addComponent(amountTo)
        .addComponent(runningLabel).addComponent(runningCheckBox)
        .addComponent(deadlineLabel)
        .addComponent(deadlineSpinner)
        .addComponent(isDeadLineTimeLabel)
        .addComponent(deadLineTimeCheckBox)
        .addComponent(deadLineTimePanel)
        .addComponent(addedDeadlinesLabel)
        .addComponent(deadlineScrollPane)
        .addComponent(addDeadlineButton)  
        .addComponent(contactsLabel).addComponent(createContactsButton)
        .addComponent(contactsScrollPane)  
        .addComponent(selectTagLabel).addComponent(tagScrollPane)
        .addComponent(websiteLabel).addComponent(websiteCheckBox).addComponent(websitePanel)
        .addComponent(collaboratedLabel).addComponent(collaboratedCheckBox)
        .addComponent(collaborationPanel)
        .addComponent(saveButton)
    );

    JScrollPane scrollPane = new JScrollPane(mainPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    dialog.add(scrollPane);

    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);
}


}
