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

public class EditProjectButton {
    private JFrame frame;
    private ArrayList<project> projectList;

    public EditProjectButton(JFrame frame, ArrayList<project> projectList) {
        this.frame = frame;
        this.projectList = projectList;
    }

    public void openEditProjectDialog() {
        JDialog dialog = new JDialog(frame, "Redigér Projekt", true);
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
        JLabel projectSelectLabel = new JLabel("Vælg projekt:");
        JComboBox<String> projectSelector = new JComboBox<>();
        for (project proj : projectList) {
            projectSelector.addItem(proj.getTitle());
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
        projectSelector.addActionListener(e -> {
            int selectedIndex = projectSelector.getSelectedIndex();
            if (selectedIndex >= 0) {
                project selectedProject = projectList.get(selectedIndex);
                nameField.setText(selectedProject.getTitle());
                purposeField.setText(selectedProject.getProjectPurpose());
                descriptionArea.setText(selectedProject.getDescription());
                ownerField.setText(selectedProject.getProjectOwner());
                targetField.setText(selectedProject.getProjectTargetAudience());
                budgetField.setText(String.valueOf(selectedProject.getProjectBudget()));

                // Set date spinners
                fromDateSpinner.setValue(Date.from(selectedProject.getProjectTimeSpanFrom().atZone(ZoneId.systemDefault()).toInstant()));
                toDateSpinner.setValue(Date.from(selectedProject.getProjectTimeSpanTo().atZone(ZoneId.systemDefault()).toInstant()));

                activitiesField.setText(selectedProject.getProjectActivities());

                // Update tag selection
                for (Component comp : tagPanel.getComponents()) {
                    if (comp instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) comp;
                        checkBox.setSelected(selectedProject.getCategories().contains(checkBox.getText()));
                    }
                }
            }
        });

        // Submit button
        JButton submitButton = new JButton("Gem Ændringer");
        submitButton.addActionListener(ae -> {
            int confirm = JOptionPane.showConfirmDialog(
                null,
                "Er du sikker på, at du vil redigere dette element?",
                "Bekræft redigering",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean isInvalidLenght = false;
            boolean hasError = false;
            int selectedIndex = projectSelector.getSelectedIndex();
            if (selectedIndex >= 0) {
                try {
                    project selectedProject = projectList.get(selectedIndex);


                    if(!validationUtils.isWithinLowerCharLimit(nameField.getText())){
                        isInvalidLenght = true;
                        UserFrameErrorHandling.displayTitleError(isInvalidLenght);
                        hasError = true;
                    }else{
                        selectedProject.setTitle(nameField.getText());
                    }


                    if(!validationUtils.isWithinLowerCharLimit(purposeField.getText())){
                        isInvalidLenght = true;
                        UserFrameErrorHandling.displayPurposeError(isInvalidLenght);
                        hasError = true;
                    }else{
                        selectedProject.setProjectPurpose(purposeField.getText());
                    }

                    if(!validationUtils.isWithinUpperCharLimit(descriptionArea.getText())){
                        isInvalidLenght = true;
                        UserFrameErrorHandling.displayDescriptionError(isInvalidLenght);
                        hasError = true;
                    }else{
                        selectedProject.setDescription(descriptionArea.getText());
                    }

                    if(!validationUtils.isWithinLowerCharLimit(ownerField.getText())){
                        isInvalidLenght = true;
                        UserFrameErrorHandling.displayOwnerError(isInvalidLenght);
                        hasError = true;
                    }else{
                        selectedProject.setProjectOwner(ownerField.getText());
                    }

                    if(!validationUtils.isWithinLowerCharLimit(targetField.getText())){
                        isInvalidLenght = true;
                        UserFrameErrorHandling.displayTargetAudienceError(isInvalidLenght);
                        hasError = true;
                    }else{
                        selectedProject.setProjectTargetAudience(targetField.getText());
                    }

                    if(!validationUtils.isNumericInput(budgetField.getText())){
                        UserFrameErrorHandling.displayBudgetError();
                        hasError = true;
                    }else{
                        selectedProject.setProjectBudget(Long.parseLong(budgetField.getText()));
                    }


                    if(!validationUtils.isWithinLowerCharLimit(activitiesField.getText())){
                        isInvalidLenght = true;
                        UserFrameErrorHandling.displayActivityError(isInvalidLenght);
                        hasError = true;
                    }else{
                        selectedProject.setProjectActivities(activitiesField.getText());
                    }

                    if(hasError){
                        return;
                    }
                    // Set updated dates
                    LocalDate fromDate = ((Date) fromDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate toDate = ((Date) toDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    selectedProject.setTimeSpan(fromDate.atStartOfDay(), toDate.atStartOfDay());

                    selectedProject.setProjectActivities(activitiesField.getText());

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
                    selectedProject.getCategories().clear();
                    selectedProject.getCategories().addAll(selectedCategories);

                    JOptionPane.showMessageDialog(dialog, "Projekt opdateret!");
                    dialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Fejl ved opdatering af projekt: " + ex.getMessage(), "Fejl", JOptionPane.ERROR_MESSAGE);
                }
            }
            ProjectCsvWriter.writeProjectCsv("data/projects.csv", projectList);
        }
        });

        // Layout definition
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(projectSelectLabel).addComponent(projectSelector)
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
            .addComponent(projectSelectLabel).addComponent(projectSelector)
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



    public void openEditProjectDialog(project project) {
        JDialog dialog = new JDialog(frame, "Redigér Projekt", true);
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


        nameField.setText(project.getTitle());
        purposeField.setText(project.getProjectPurpose());
        descriptionArea.setText(project.getDescription());
        ownerField.setText(project.getProjectOwner());
        targetField.setText(project.getProjectTargetAudience());
        budgetField.setText(String.valueOf(project.getProjectBudget()));


        fromDateSpinner.setValue(Date.from(project.getProjectTimeSpanFrom().atZone(ZoneId.systemDefault()).toInstant()));
        toDateSpinner.setValue(Date.from(project.getProjectTimeSpanTo().atZone(ZoneId.systemDefault()).toInstant()));

        activitiesField.setText(project.getProjectActivities());


        for (Component comp : tagPanel.getComponents()) {
            if (comp instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) comp;
                checkBox.setSelected(project.getCategories().contains(checkBox.getText()));
            }
        }
        
        // Submit button
        JButton submitButton = new JButton("Gem Ændringer");
        submitButton.addActionListener(ae -> {
            int confirm = JOptionPane.showConfirmDialog(
                null,
                "Er du sikker på, at du vil redigere dette element?",
                "Bekræft redigering",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean isInvalidLenght = false;
            boolean hasError = false;
                try {
                    if(!validationUtils.isWithinLowerCharLimit(nameField.getText())){
                        isInvalidLenght = true;
                        UserFrameErrorHandling.displayTitleError(isInvalidLenght);
                        hasError = true;
                    }else{
                        project.setTitle(nameField.getText());
                    }


                    if(!validationUtils.isWithinLowerCharLimit(purposeField.getText())){
                        isInvalidLenght = true;
                        UserFrameErrorHandling.displayPurposeError(isInvalidLenght);
                        hasError = true;
                    }else{
                        project.setProjectPurpose(purposeField.getText());
                    }

                    if(!validationUtils.isWithinUpperCharLimit(descriptionArea.getText())){
                        isInvalidLenght = true;
                        UserFrameErrorHandling.displayDescriptionError(isInvalidLenght);
                        hasError = true;
                    }else{
                        project.setDescription(descriptionArea.getText());
                    }

                    if(!validationUtils.isWithinLowerCharLimit(ownerField.getText())){
                        isInvalidLenght = true;
                        UserFrameErrorHandling.displayOwnerError(isInvalidLenght);
                        hasError = true;
                    }else{
                        project.setProjectOwner(ownerField.getText());
                    }

                    if(!validationUtils.isWithinLowerCharLimit(targetField.getText())){
                        isInvalidLenght = true;
                        UserFrameErrorHandling.displayTargetAudienceError(isInvalidLenght);
                        hasError = true;
                    }else{
                        project.setProjectTargetAudience(targetField.getText());
                    }

                    if(!validationUtils.isNumericInput(budgetField.getText())){
                        UserFrameErrorHandling.displayBudgetError();
                        hasError = true;
                    }else{
                        project.setProjectBudget(Long.parseLong(budgetField.getText()));
                    }


                    if(!validationUtils.isWithinLowerCharLimit(activitiesField.getText())){
                        isInvalidLenght = true;
                        UserFrameErrorHandling.displayActivityError(isInvalidLenght);
                        hasError = true;
                    }else{
                        project.setProjectActivities(activitiesField.getText());
                    }

                    if(hasError){
                        return;
                    }
                    // Set updated dates
                    LocalDate fromDate = ((Date) fromDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate toDate = ((Date) toDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    project.setTimeSpan(fromDate.atStartOfDay(), toDate.atStartOfDay());

                    project.setProjectActivities(activitiesField.getText());

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
                    project.getCategories().clear();
                    project.getCategories().addAll(selectedCategories);

                    JOptionPane.showMessageDialog(dialog, "Projekt opdateret!");
                    dialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Fejl ved opdatering af projekt: " + ex.getMessage(), "Fejl", JOptionPane.ERROR_MESSAGE);
                }
            
            ProjectCsvWriter.writeProjectCsv("data/projects.csv", projectList);
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
