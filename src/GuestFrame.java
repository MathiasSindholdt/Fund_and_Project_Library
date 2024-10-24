import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GuestFrame implements ActionListener {

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    // Buttons
    private JButton createProbButton;
    private JButton changeProbButton;
    private JButton backButton;
    private JButton projectPropButton;
    private JButton projectButton;
    private JButton fundsButton;

    // List to store project proposals
    private List<ProjectProposal> projectProposals;
    private JPanel projectProposalListPanel;

    // Constructor to set up the GUI
    public GuestFrame() {
        initializeFrame();  // Initialize JFrame

        projectProposals = new ArrayList<>();  // Initialize the project proposals list

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
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        backButton = createBackButton();
        panel1.add(backButton);

        JLabel label = new JLabel("Gæst", SwingConstants.LEFT);
        panel1.add(label);

        projectPropButton = createProjectPropButton("Projekt forslag");
        panel1.add(projectPropButton);

        projectButton = createProjectButton("Projekter");
        panel1.add(projectButton);

        fundsButton = createFundsButton("Fonde");
        panel1.add(fundsButton);

        return panel1;
    }

    private JPanel createSidePanel() {
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(213, 213, 213, 255));
        panel2.setPreferredSize(new Dimension(100, 100));
        return panel2;
    }

    private JPanel createRightSidePanel() {
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel3.setBackground(new Color(213, 213, 213, 255));
        panel3.setPreferredSize(new Dimension(900, 100));
        return panel3;
    }

    // Center panel for the main view (like before)
    private JPanel createCenterPanel() {
        JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel5.setBackground(new Color(213, 213, 213, 255));

        // The button to open the project proposal dialog
        createProbButton = createButton("Lav projekt forslag");
        changeProbButton = createButton("Ændre projekt forslag");

        panel5.add(createProbButton);
        panel5.add(changeProbButton);

        return panel5;
    }

    // Separate view for "Projekt forslag"
    private JPanel createProjectProposalView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

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
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel("Projekter");
        panel.add(label);
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
        } else if (e.getSource() == createProbButton) {
            openProjectProposalDialog(); // Opens a popup for creating a project proposal
        }
    }

    // Restore the project proposal dialog
    private void openProjectProposalDialog() {
        JDialog dialog = new JDialog(frame, "Lav Projekt Forslag", true);
        dialog.setSize(700, 700);
        dialog.setLayout(new GridLayout(11, 2, 10, 10));

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
        JSpinner.DateEditor fromDateEditor = new JSpinner.DateEditor(fromDateSpinner, "MM/dd/yyyy");
        fromDateSpinner.setEditor(fromDateEditor);
        dialog.add(fromDateLabel);
        dialog.add(fromDateSpinner);

        JLabel toDateLabel = new JLabel("Til dato:");
        SpinnerDateModel toDateModel = new SpinnerDateModel();
        JSpinner toDateSpinner = new JSpinner(toDateModel);
        JSpinner.DateEditor toDateEditor = new JSpinner.DateEditor(toDateSpinner, "MM/dd/yyyy");
        toDateSpinner.setEditor(toDateEditor);
        dialog.add(toDateLabel);
        dialog.add(toDateSpinner);

        JLabel activitiesLabel = new JLabel("Aktiviteter:");
        JTextField activitiesField = new JTextField();
        dialog.add(activitiesLabel);
        dialog.add(activitiesField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(event -> {
            String name = nameField.getText();
            String idea = ideaField.getText();
            String description = descriptionArea.getText();
            String ideaFrom = ideaFromField.getText();
            String owner = ownerField.getText();
            String target = targetField.getText();
            String budget = budgetField.getText();
            Date fromDate = new Date(((java.util.Date) fromDateSpinner.getValue()).getTime());
            Date toDate = new Date(((java.util.Date) toDateSpinner.getValue()).getTime());
            String activities = activitiesField.getText();

            // Create a new project proposal and add it to the list
            ProjectProposal proposal = new ProjectProposal(name, idea, description, ideaFrom, owner, target, budget, fromDate, toDate, activities);
            projectProposals.add(proposal);

            // Update the project proposal panel
            updateProjectProposalList();

            dialog.dispose();
        });

        dialog.add(new JLabel());
        dialog.add(submitButton);

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    // Update the project proposal list in the "ProjectProposal" panel
    private void updateProjectProposalList() {
        projectProposalListPanel.removeAll(); // Clear the current list

        // Loop through the project proposals and display them
        for (ProjectProposal proposal : projectProposals) {
            JLabel proposalLabel = new JLabel(proposal.getTitle() + " - " + proposal.getOwner());
            projectProposalListPanel.add(proposalLabel);
        }

        projectProposalListPanel.revalidate(); // Refresh the panel to display new components
        projectProposalListPanel.repaint();
    }

    public static void main(String[] args) {
        GuestFrame guestFrame = new GuestFrame();
        guestFrame.show();
    }
}

// Class to represent a project proposal
class ProjectProposal {
    private String title;
    private String idea;
    private String description;
    private String ideaFrom;
    private String owner;
    private String target;
    private String budget;
    private Date fromDate;
    private Date toDate;
    private String activities;

    public ProjectProposal(String title, String idea, String description, String ideaFrom, String owner, String target,
                           String budget, Date fromDate, Date toDate, String activities) {
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

    public String getTitle() {
        return title;
    }

    public String getOwner() {
        return owner;
    }

    // Add getters for other fields if needed
}
