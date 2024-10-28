import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GuestFrame implements ActionListener {

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JButton createProbButton, changeProbButton, backButton, projectPropButton, projectButton, fundsButton;
    private List<ProjectProposal> projectProposals;
    private JPanel projectProposalListPanel, projectProposalFullPanel;

    public GuestFrame() {
        initializeFrame();
        projectProposals = new ArrayList<>();
        JPanel panel1 = createTopPanel();
        JPanel panel2 = createSidePanel();
        JPanel panel3 = createRightSidePanel();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createCenterPanel(), "Main");
        cardPanel.add(createProjectProposalView(), "ProjectProposal");
        cardPanel.add(createProjectsView(), "Projects");
        cardPanel.add(createFundsView(), "Funds");

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(cardPanel, BorderLayout.CENTER);
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

        projectPropButton = createButton("Projekt forslag");
        projectButton = createButton("Projekter");
        fundsButton = createButton("Fonde");

        panel1.add(projectPropButton);
        panel1.add(projectButton);
        panel1.add(fundsButton);

        return panel1;
    }

    private JPanel createSidePanel() {
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(213, 213, 213, 255));
        panel2.setPreferredSize(new Dimension(100, 100));
        return panel2;
    }

    private JPanel createCenterPanel() {
        JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel5.setBackground(new Color(213, 213, 213, 255));

        createProbButton = createButton("Lav projekt forslag");
        changeProbButton = createButton("Ændre projekt forslag");

        panel5.add(createProbButton);
        panel5.add(changeProbButton);

        return panel5;
    }

    private JPanel createProjectProposalView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Projekt Forslag", SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        projectProposalListPanel = new JPanel();
        projectProposalListPanel.setLayout(new BoxLayout(projectProposalListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(projectProposalListPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createProjectsView() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel("Projekter");
        panel.add(label);
        return panel;
    }

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

    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == projectPropButton) {
            cardLayout.show(cardPanel, "ProjectProposal");
        } else if (e.getSource() == projectButton) {
            cardLayout.show(cardPanel, "Projects");
        } else if (e.getSource() == fundsButton) {
            cardLayout.show(cardPanel, "Funds");
        } else if (e.getSource() == backButton) {
            cardLayout.show(cardPanel, "Main");
        } else if (e.getSource() == createProbButton) {
            openProjectProposalDialog();
        }
    }

    private void openProjectProposalDialog() {
        JDialog dialog = new JDialog(frame, "Lav Projekt Forslag", true);
        dialog.setSize(700, 700);
        dialog.setLayout(new GridLayout(12, 2, 10, 10));

        dialog.add(new JLabel("Titel:"));
        JTextField nameField = new JTextField();
        dialog.add(nameField);

        dialog.add(new JLabel("Idé/Formål:"));
        JTextField ideaField = new JTextField();
        dialog.add(ideaField);

        dialog.add(new JLabel("Kort beskrivelse af projektet for at danne en mening:"));
        JTextArea descriptionArea = new JTextArea(5, 20);
        dialog.add(new JScrollPane(descriptionArea));

        dialog.add(new JLabel("Ejer af idé/forslaget:"));
        JTextField ownerField = new JTextField();
        dialog.add(ownerField);

        dialog.add(new JLabel("Målgruppe:"));
        JTextField targetField = new JTextField();
        dialog.add(targetField);

        dialog.add(new JLabel("Anslået budget (kr.):"));
        JTextField budgetField = new JTextField();
        dialog.add(budgetField);

        dialog.add(new JLabel("Aktiviteter:"));
        JTextField activitiesField = new JTextField();
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
        });

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(event -> {
            List<String> selectedTags = new ArrayList<>();
            for (Component component : tagPanel.getComponents()) {
                if (component instanceof JCheckBox checkBox && checkBox.isSelected()) {
                    selectedTags.add(checkBox.getText());
                }
            }
            dialog.dispose();
        });

        dialog.add(new JLabel());
        dialog.add(submitButton);

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    private JPanel createRightSidePanel() {
        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBackground(new Color(213, 213, 213, 255));
        panel3.setPreferredSize(new Dimension(900, 100));

        projectProposalFullPanel = new JPanel();
        projectProposalFullPanel.setLayout(new BoxLayout(projectProposalFullPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(projectProposalFullPanel);
        panel3.add(scrollPane, BorderLayout.CENTER);

        return panel3;
    }

    public static void main(String[] args) {
        GuestFrame guestFrame = new GuestFrame();
        guestFrame.show();
    }
}
