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
        JButton submitButton = new JButton("Gem");
        submitButton.addActionListener(ae -> {
            int selectedIndex = projectSelector.getSelectedIndex();
            if (selectedIndex >= 0) {
                try {
                    project selectedProject = projectList.get(selectedIndex);

                    // Update project properties
                    selectedProject.setTitle(nameField.getText());
                    selectedProject.setProjectPurpose(purposeField.getText());
                    selectedProject.setDescription(descriptionArea.getText());
                    selectedProject.setProjectOwner(ownerField.getText());
                    selectedProject.setProjectTargetAudience(targetField.getText());
                    selectedProject.setProjectBudget(Long.parseLong(budgetField.getText()));

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
}
