import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class GuestFrame extends AbstractFrame {
    // Constructor to set up the GUI
    public GuestFrame() {
        this.userType = "guest";
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

    public static void changeCursor(JButton button) {
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            }
        });
    }

 //KEEP THIS ITS DIFFERENT
    // Center panel for the main view (like before)
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

        // Section for Projekt forslag
        JPanel projectProposalPanel = new JPanel();
        projectProposalPanel.setLayout(new BorderLayout());
        projectProposalPanel.setBackground(new Color(213, 213, 213, 255));

        JLabel projectProposalLabel = new JLabel("Projekt forslag");
        projectProposalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        projectProposalLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align label to the left
        projectProposalLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); // Add space to the left

        JPanel projectProposalButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        projectProposalButtons.setBackground(new Color(213, 213, 213, 255));

        createProbButton = UIButtons.createButton("Opret et nyt projekt forslag");
        createProbButton.setPreferredSize(new Dimension(200, 50));
        createProbButton.addActionListener(this);
        changeProbButton = UIButtons.createButton("Redigér projekt forslag");
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

       
        // Add sections to content panel
        contentPanel.add(projectProposalPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        mainPanel.add(contentPanel, BorderLayout.CENTER);


      

        changeProbButton.addActionListener(e -> {
            editProjectProposal.openEditProjectPropDialog(); // Call the method on the instance
            updateProposalProjectList();
        });
        return mainPanel;
    }

    // Separate view for "Projekter"
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

    //KEEP ITS DIFEFRENT
    // Separate view for "Fonde"
    private JPanel createFundsView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Fonde", SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        // Initialize fund list panel
        fundListPanel = new JPanel();
        fundListPanel.setLayout(new BoxLayout(fundListPanel, BoxLayout.Y_AXIS));

        // Create a scroll pane for the fund list
        JScrollPane scrollPane = new JScrollPane(fundListPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public void updateCategoryPanel() {
        panel2.removeAll();
        System.err.println("Updating category panel...");
        for (String category : main.categories) {
            createTagButton(category);
            System.out.println(category);
        }
    
        panel2.revalidate();
        panel2.repaint();
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

   //KEEP DIFFERENT
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
            cardLayout.show(cardPanel, "Main");
        } else if (e.getSource() == createProbButton) {
            openproposalProjectDialog();
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

