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
    private JFrame frame;
    private ArrayList<fundClass> fundList;
    private JPanel tagPanel; // Declare here for broader scope

    public EditFundButton(JFrame frame, ArrayList<fundClass> fundList) {
        this.frame = frame;
        this.fundList = fundList;
    }


public void editFundDialog() {
    JDialog dialog = new JDialog(frame, "Rediger Fond", true);
    dialog.setSize(700, 600);

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
    JLabel nameLabel = new JLabel("Titel:");
    JTextField nameField = new JTextField();

    JLabel descriptionLabel = new JLabel("Beskrivelse:");
    JTextArea descriptionArea = new JTextArea(5, 20);
    JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

    // Fond Beløb Fra og Til
    JLabel amountFromLabel = new JLabel("Beløb fra:");
    JTextField amountFrom = new JTextField();

    JLabel amountToLabel = new JLabel("Beløb til:");
    JTextField amountTo = new JTextField();

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
            .atTime(0, 0); // Set default time to 00:00
    }
    addedDeadlines.add(newDeadline);

        // Create a panel to hold the deadline label and the remove button
        JPanel deadlinePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel deadlineLabelItem = new JLabel(newDeadline.format(formatter));
        JButton xButton = new JButton("X");
        
        
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
    

    // Previous Collaborations section
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

    createCollaborationButton.addActionListener(e -> {
        String newCollaboration = JOptionPane.showInputDialog(dialog, "Skriv nye tidligere samarbejdeprojekter:");
        if (newCollaboration != null && !newCollaboration.trim().isEmpty()) {
            main.userProjectList.add(newCollaboration);
            collaborationContentPanel.add(new JCheckBox(newCollaboration));
            collaborationContentPanel.revalidate();
            collaborationContentPanel.repaint();
        }
    });

    // Løbende deadline section
    JLabel runningLabel = new JLabel("Løbende ansøgningsfrist:");
    JCheckBox runningCheckBox = new JCheckBox();
    runningCheckBox.addItemListener(e -> {
        deadlineSpinner.setEnabled(!runningCheckBox.isSelected());
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
    
        // Handle deadlines
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
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
});


    // Submit Button to Save Changes
    JButton saveButton = new JButton("Gem Ændringer");
    System.out.println("Gem ændringer");
    saveButton.addActionListener(event -> {
        String selectedFundTitle = (String) fundSelector.getSelectedItem();
        fundClass selectedFund = fundList.stream()
            .filter(fund -> fund.getTitle().equals(selectedFundTitle))
            .findFirst()
            .orElse(null);

    
    
    
        if (selectedFund != null) {
            boolean hasError = false;
            
           System.out.println(selectedFund);

            // Validate and update fields
            if (!validationUtils.isValidInput(nameField.getText())) {
                dialog.add(UserFrameErrorHandling.displayTitleError(false));
                hasError = true;
            } else {
                selectedFund.setTitle(nameField.getText().trim());
            }
    
            if (!validationUtils.isValidDescription(descriptionArea.getText())) {
                dialog.add(UserFrameErrorHandling.displayDescriptionError(false));
                hasError = true;
            } else {
                selectedFund.setDescription(descriptionArea.getText().trim());
            }
    
            if (!validationUtils.isNumericInput(amountFrom.getText())) {
                dialog.add(UserFrameErrorHandling.displayAmountFromError());
                hasError = true;
            } else {
                selectedFund.setBudgetMin(Long.parseLong(amountFrom.getText().trim()));
            }
    
            if (!validationUtils.isNumericInput(amountTo.getText())) {
                dialog.add(UserFrameErrorHandling.displayAmountToError());
                hasError = true;
            } else {
                selectedFund.setBudgetMax(Long.parseLong(amountTo.getText().trim()), 0);
            }
    
            // Update deadlines
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

            if(websiteCheckBox.isSelected() == true){
                if(validationUtils.isValidUrl(websiteField.getText()) == false){
                    dialog.add(UserFrameErrorHandling.displayWebsiteError());
                    //hasError = true;
                }else{
                    selectedFund.setfundWebsite(websiteField.getText().trim());
                }
            }else{
                selectedFund.setfundWebsite(""); // If checkbox is unchecked, clear website
            }

            // Update running status
            selectedFund.setRunning(runningCheckBox.isSelected());
    
            // If no errors, save the updated fund
            if (!hasError) {
                System.out.println("No errors-----------------");
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
                //Remove the fund currently being edited from the fundlist
                FundCsvWriter.writeCsv("data/funds.csv", fundList);
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
        .addComponent(selectFundLabel).addComponent(fundSelector)
        .addComponent(nameLabel).addComponent(nameField)
        .addComponent(descriptionLabel).addComponent(descriptionScrollPane)
        .addComponent(amountFromLabel).addComponent(amountFrom)
        .addComponent(amountToLabel).addComponent(amountTo)
        .addComponent(deadlineLabel)
        .addComponent(deadlineSpinner)
        .addComponent(isDeadLineTimeLabel)
        .addComponent(deadLineTimeCheckBox)
        .addComponent(deadLineTimePanel)
        .addComponent(addedDeadlinesLabel)
        .addComponent(deadlineScrollPane)
        .addComponent(addDeadlineButton)     
        //  .addComponent(contactsLabel).addComponent(createContactsButton).addComponent(contactsScrollPane)
        .addComponent(selectTagLabel).addComponent(tagScrollPane)
        .addComponent(websiteLabel).addComponent(websiteCheckBox).addComponent(websitePanel)
        .addComponent(collaboratedLabel).addComponent(collaboratedCheckBox).addComponent(collaborationPanel)
        .addComponent(runningLabel).addComponent(runningCheckBox)
        .addComponent(saveButton)
    );

    layout.setVerticalGroup(layout.createSequentialGroup()
        .addComponent(selectFundLabel).addComponent(fundSelector)
        .addComponent(nameLabel).addComponent(nameField)
        .addComponent(descriptionLabel).addComponent(descriptionScrollPane)
        .addComponent(amountFromLabel).addComponent(amountFrom)
        .addComponent(amountToLabel).addComponent(amountTo)
        .addComponent(deadlineLabel)
        .addComponent(deadlineSpinner)
        .addComponent(isDeadLineTimeLabel)
        .addComponent(deadLineTimeCheckBox)
        .addComponent(deadLineTimePanel)
        .addComponent(addedDeadlinesLabel)
        .addComponent(deadlineScrollPane)
        .addComponent(addDeadlineButton)      
        //  .addComponent(contactsLabel).addComponent(createContactsButton).addComponent(contactsScrollPane)
        .addComponent(selectTagLabel).addComponent(tagScrollPane)
        .addComponent(websiteLabel).addComponent(websiteCheckBox).addComponent(websitePanel)
        .addComponent(collaboratedLabel).addComponent(collaboratedCheckBox).addComponent(collaborationPanel)
        .addComponent(runningLabel).addComponent(runningCheckBox)
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
