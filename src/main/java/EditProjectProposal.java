import java.awt.Component;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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

public class EditProjectProposal {
    private JFrame frame;
    private ArrayList<proposalProject> proposalList;

    public EditProjectProposal(JFrame frame, ArrayList<proposalProject> proposalList) {
        this.frame = frame;
        this.proposalList = proposalList;
    }

    public void openEditProjectPropDialog() {
        JDialog dialog = new JDialog(frame, "Redigér Projekt Forslag", true);
        dialog.setSize(700, 700);

        // Main panel with layout
        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        dialog.add(scrollPane);

        // Project selection dropdown
        JLabel projectPropSelectLabel = new JLabel("Vælg projekt forslag:");
        JComboBox<String> projectPropSelecter = new JComboBox<>();
        for (proposalProject prop : proposalList) {
            projectPropSelecter.addItem(prop.getTitle());
        }

        // Fields for editing
        JLabel nameLabel = new JLabel("Titel:*");
        JTextField nameField = new JTextField();

        JLabel purposeLabel = new JLabel("Formål:*");
        JTextField purposeField = new JTextField();

        JLabel descriptionLabel = new JLabel("Beskrivelse:*");
        JTextArea descriptionArea = new JTextArea(5, 20);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        JLabel ownerLabel = new JLabel("Ejer:*");
        JTextField ownerField = new JTextField();

        JLabel targetLabel = new JLabel("Målgruppe:*");
        JTextField targetField = new JTextField();

        JLabel budgetLabel = new JLabel("Budget:*");
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

        JLabel selectTagLabel = new JLabel("Vælg relevante kategorier:");
        JPanel tagPanel = new JPanel();
        tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
        JScrollPane tagScrollPane = new JScrollPane(tagPanel);

        // Pre-fill tags with checkboxes
        for (String category : main.categories) {
            JCheckBox tagCheckBox = new JCheckBox(category);
            tagPanel.add(tagCheckBox);
        }

        // Populate fields based on selected project
        projectPropSelecter.addActionListener(e -> {
            int selectedIndex = projectPropSelecter.getSelectedIndex();
            if (selectedIndex >= 0) {
                proposalProject selectedPropProject = proposalList.get(selectedIndex);
                nameField.setText(selectedPropProject.getTitle());
                purposeField.setText(selectedPropProject.getProjectPurpose());
                descriptionArea.setText(selectedPropProject.getDescription());
                ownerField.setText(selectedPropProject.getProjectOwner());
                targetField.setText(selectedPropProject.getProjectTargetAudience());
                budgetField.setText(String.valueOf(selectedPropProject.getProjectBudget()));

                // Set date spinners
                fromDateSpinner.setValue(Date.from(selectedPropProject.getProjectTimeSpanFrom().atZone(ZoneId.systemDefault()).toInstant()));
                toDateSpinner.setValue(Date.from(selectedPropProject.getProjectTimeSpanTo().atZone(ZoneId.systemDefault()).toInstant()));

                activitiesField.setText(selectedPropProject.getProjectActivities());

                // Update tag selection
                for (Component comp : tagPanel.getComponents()) {
                    if (comp instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) comp;
                        checkBox.setSelected(selectedPropProject.getCategories().contains(checkBox.getText()));
                    }
                }
            }
        });

        // Submit button
        JButton submitButton = new JButton("Gem");
        submitButton.addActionListener(ae -> {
            int selectedIndex = projectPropSelecter.getSelectedIndex();
            boolean isInvalidLenght = false;
            boolean hasError = false;

            if (selectedIndex >= 0) {
                try {
                    proposalProject selectedPropProject = proposalList.get(selectedIndex);


                    if(!validationUtils.isWithinLowerCharLimit(nameField.getText())){
                        isInvalidLenght = true;
                        hasError = true;
                        UserFrameErrorHandling.displayTitleError(isInvalidLenght);
                    }else{
                        selectedPropProject.setTitle(nameField.getText());
                    }

                    if(!validationUtils.isWithinLowerCharLimit(purposeField.getText())){
                        isInvalidLenght = true;
                        hasError = true;
                        UserFrameErrorHandling.displayPurposeError(isInvalidLenght);
                    }else{
                        selectedPropProject.setProjectPurpose(purposeField.getText());
                    }

                    if(!validationUtils.isWithinUpperCharLimit(descriptionArea.getText())){
                        isInvalidLenght = true;
                        hasError = true;
                        UserFrameErrorHandling.displayDescriptionError(isInvalidLenght);
                    }else{
                        selectedPropProject.setDescription(descriptionArea.getText());
                    }

                    if(!validationUtils.isWithinLowerCharLimit(ownerField.getText())){
                        isInvalidLenght = true;
                        hasError = true;
                        UserFrameErrorHandling.displayOwnerError(isInvalidLenght);
                    }else{
                        selectedPropProject.setProjectOwner(ownerField.getText());
                    }

                    if(!validationUtils.isWithinLowerCharLimit(targetField.getText())){
                        isInvalidLenght = true;
                        hasError = true;
                        UserFrameErrorHandling.displayTargetAudienceError(isInvalidLenght);
                    }else{
                        selectedPropProject.setProjectTargetAudience(targetField.getText());
                    }   

                    if(!validationUtils.isWithinUpperCharLimit(activitiesField.getText())){
                        isInvalidLenght = true;
                        hasError = true;
                        UserFrameErrorHandling.displayActivityError(isInvalidLenght);
                    }else{
                        selectedPropProject.setProjectActivities(activitiesField.getText());
                    }

                    if(!validationUtils.isNumericInput(budgetField.getText())){
                        hasError = true;
                        UserFrameErrorHandling.displayBudgetError();
                    }else{
                        selectedPropProject.setProjectBudget(Long.parseLong(budgetField.getText()));
                    }

                    if(hasError){
                        return;
                    }
                    // Set updated dates
                    LocalDate fromDate = ((Date) fromDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate toDate = ((Date) toDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    selectedPropProject.setTimeSpan(fromDate.atStartOfDay(), toDate.atStartOfDay());

                    selectedPropProject.setProjectActivities(activitiesField.getText());

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
                    selectedPropProject.getCategories().clear();
                    selectedPropProject.getCategories().addAll(selectedCategories);

                    JOptionPane.showMessageDialog(dialog, "Projekt forslag opdateret!");
                    dialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Fejl ved opdatering af projekt forslag: " + ex.getMessage(), "Fejl", JOptionPane.ERROR_MESSAGE);
                }
            }
            ProposalsCsvWriter.writeProposalCsv("data/proposals.csv", proposalList);

        });

        // Layout definition
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(projectPropSelectLabel).addComponent(projectPropSelecter)
            .addComponent(nameLabel).addComponent(nameField)
            .addComponent(purposeLabel).addComponent(purposeField)
            .addComponent(descriptionLabel).addComponent(descriptionScrollPane)
            .addComponent(ownerLabel).addComponent(ownerField)
            .addComponent(targetLabel).addComponent(targetField)
            .addComponent(budgetLabel).addComponent(budgetField)
            .addComponent(fromDateLabel).addComponent(fromDateSpinner)
            .addComponent(toDateLabel).addComponent(toDateSpinner)
            .addComponent(activitiesLabel).addComponent(activitiesField)
            .addComponent(selectTagLabel).addComponent(tagScrollPane)
            .addComponent(submitButton)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(projectPropSelectLabel).addComponent(projectPropSelecter)
            .addComponent(nameLabel).addComponent(nameField)
            .addComponent(purposeLabel).addComponent(purposeField)
            .addComponent(descriptionLabel).addComponent(descriptionScrollPane)
            .addComponent(ownerLabel).addComponent(ownerField)
            .addComponent(targetLabel).addComponent(targetField)
            .addComponent(budgetLabel).addComponent(budgetField)
            .addComponent(fromDateLabel).addComponent(fromDateSpinner)
            .addComponent(toDateLabel).addComponent(toDateSpinner)
            .addComponent(activitiesLabel).addComponent(activitiesField)
            .addComponent(selectTagLabel).addComponent(tagScrollPane)
            .addComponent(submitButton)
        );

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }


public void openEditProjectPropDialog(proposalProject loadselectedPropProject) {
    JDialog dialog = new JDialog(frame, "Redigér Projekt Forslag", true);
    dialog.setSize(700, 700);

    // Main panel with layout
    JPanel mainPanel = new JPanel();
    GroupLayout layout = new GroupLayout(mainPanel);
    mainPanel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    JScrollPane scrollPane = new JScrollPane(mainPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    dialog.add(scrollPane);

    // Fields for editing
    JLabel nameLabel = new JLabel("Titel:*");
    JTextField nameField = new JTextField();

    JLabel purposeLabel = new JLabel("Formål:*");
    JTextField purposeField = new JTextField();

    JLabel descriptionLabel = new JLabel("Beskrivelse:*");
    JTextArea descriptionArea = new JTextArea(5, 20);
    JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

    JLabel ownerLabel = new JLabel("Ejer:*");
    JTextField ownerField = new JTextField();

    JLabel targetLabel = new JLabel("Målgruppe:*");
    JTextField targetField = new JTextField();

    JLabel budgetLabel = new JLabel("Budget:*");
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

    JLabel selectTagLabel = new JLabel("Vælg relevante kategorier:");
    JPanel tagPanel = new JPanel();
    tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.Y_AXIS));
    JScrollPane tagScrollPane = new JScrollPane(tagPanel);

    // Pre-fill tags with checkboxes
    for (String category : main.categories) {
        JCheckBox tagCheckBox = new JCheckBox(category);
        tagPanel.add(tagCheckBox);
    }
    
    //Populate the default value on the current project
    nameField.setText(loadselectedPropProject.getTitle());
    purposeField.setText(loadselectedPropProject.getProjectPurpose());
    descriptionArea.setText(loadselectedPropProject.getDescription());
    ownerField.setText(loadselectedPropProject.getProjectOwner());
    targetField.setText(loadselectedPropProject.getProjectTargetAudience());
    budgetField.setText(String.valueOf(loadselectedPropProject.getProjectBudget()));
    fromDateSpinner.setValue(Date.from(loadselectedPropProject.getProjectTimeSpanFrom().atZone(ZoneId.systemDefault()).toInstant()));
    toDateSpinner.setValue(Date.from(loadselectedPropProject.getProjectTimeSpanTo().atZone(ZoneId.systemDefault()).toInstant()));
    activitiesField.setText(loadselectedPropProject.getProjectActivities());

    // Update tag selection
    for (Component comp : tagPanel.getComponents()) {
        if (comp instanceof JCheckBox) {
            JCheckBox checkBox = (JCheckBox) comp;
            checkBox.setSelected(loadselectedPropProject.getCategories().contains(checkBox.getText()));
        }
    }

    // Submit button
    JButton submitButton = new JButton("Gem");
    submitButton.addActionListener(ae -> {
        boolean isInvalidLenght = false;
        boolean hasError = false;
            try {
                if(!validationUtils.isWithinLowerCharLimit(nameField.getText())){
                    isInvalidLenght = true;
                    hasError = true;
                    UserFrameErrorHandling.displayTitleError(isInvalidLenght);
                }else{
                    loadselectedPropProject.setTitle(nameField.getText());
                }

                if(!validationUtils.isWithinLowerCharLimit(purposeField.getText())){
                    isInvalidLenght = true;
                    hasError = true;
                    UserFrameErrorHandling.displayPurposeError(isInvalidLenght);
                }else{
                    loadselectedPropProject.setProjectPurpose(purposeField.getText());
                }

                if(!validationUtils.isWithinUpperCharLimit(descriptionArea.getText())){
                    isInvalidLenght = true;
                    hasError = true;
                    UserFrameErrorHandling.displayDescriptionError(isInvalidLenght);
                }else{
                    loadselectedPropProject.setDescription(descriptionArea.getText());
                }

                if(!validationUtils.isWithinLowerCharLimit(ownerField.getText())){
                    isInvalidLenght = true;
                    hasError = true;
                    UserFrameErrorHandling.displayOwnerError(isInvalidLenght);
                }else{
                    loadselectedPropProject.setProjectOwner(ownerField.getText());
                }

                if(!validationUtils.isWithinLowerCharLimit(targetField.getText())){
                    isInvalidLenght = true;
                    hasError = true;
                    UserFrameErrorHandling.displayTargetAudienceError(isInvalidLenght);
                }else{
                    loadselectedPropProject.setProjectTargetAudience(targetField.getText());
                }   

                if(!validationUtils.isWithinUpperCharLimit(activitiesField.getText())){
                    isInvalidLenght = true;
                    hasError = true;
                    UserFrameErrorHandling.displayActivityError(isInvalidLenght);
                }else{
                    loadselectedPropProject.setProjectActivities(activitiesField.getText());
                }

                if(!validationUtils.isNumericInput(budgetField.getText())){
                    hasError = true;
                    UserFrameErrorHandling.displayBudgetError();
                }else{
                    loadselectedPropProject.setProjectBudget(Long.parseLong(budgetField.getText()));
                }

                if(hasError){
                    return;
                }
                // Set updated dates
                LocalDate fromDate = ((Date) fromDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = ((Date) toDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                loadselectedPropProject.setTimeSpan(fromDate.atStartOfDay(), toDate.atStartOfDay());

                loadselectedPropProject.setProjectActivities(activitiesField.getText());

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
                loadselectedPropProject.getCategories().clear();
                loadselectedPropProject.getCategories().addAll(selectedCategories);

                JOptionPane.showMessageDialog(dialog, "Projekt forslag opdateret!");
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Fejl ved opdatering af projekt forslag: " + ex.getMessage(), "Fejl", JOptionPane.ERROR_MESSAGE);
            }
        ProposalsCsvWriter.writeProposalCsv("data/proposals.csv", proposalList);

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
        .addComponent(selectTagLabel).addComponent(tagScrollPane)
        .addComponent(submitButton)
    );

    dialog.setLocationRelativeTo(frame);
    dialog.setVisible(true);
}
}

