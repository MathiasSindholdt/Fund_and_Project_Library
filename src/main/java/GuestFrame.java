import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
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

import org.checkerframework.checker.units.qual.C;

import jdk.jfr.Category;

public class GuestFrame extends JFrame implements ActionListener {

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    JPanel panel2 = new JPanel();

    // Buttons
    private JButton createProbButton;
    private JButton changeProbButton;

    private JButton menuButton;
    private JButton logoutButton;
    private JButton backButton;
    private JButton xButton;
    private JButton xButtonHover;
    private JButton projectPropButton;
    private JButton projectButton;
    private JButton fundsButton;
    private JButton archiveButton;

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
    public static fundContactClass tempContact;
    public ArrayList<fundContactClass> tempContacts = new ArrayList<>();
    private ArrayList<fundContactClass> removeContactArray = new ArrayList<>();
    private ArrayList<fundContactClass> contacts = new ArrayList<>();
    public ArrayList<proposalProject> sortedProposalList = main.proposalList;
    public ArrayList<project> sortedProjectList = main.projectList;
    public ArrayList<fundClass> sortedFundList = main.fundList;

    private List<JToggleButton> tagButton;
    private ArrayList<String> selectedTags = new ArrayList<>();

    globalListSorting sorter = new globalListSorting();
    UIButtons UIButtons = new UIButtons();
    EditProjectProposal editProjectProposal = new EditProjectProposal(this, main.proposalList);


    int[] clickCounts = { 0, 0, 0, 0, 0 }; // Initialize the clickCounts array

    // Constructor to set up the GUI
    public GuestFrame() {
        initializeFrame(); // Initialize JFrame
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
        menuButton = UIButtons.createMenuButton();
        menuButton.addActionListener(this);
        menuButton.setPreferredSize(new Dimension(125, 50)); // Set preferred size
        leftPanel.add(menuButton);
        changeCursor(menuButton);

        projectPropButton = UIButtons.createProjectPropButton("Projekt forslag");
        projectPropButton.setPreferredSize(new Dimension(150, 50)); 
        projectPropButton.addActionListener(this);
        panel1.add(projectPropButton);
        leftPanel.add(projectPropButton);
        changeCursor(projectPropButton);

        projectButton = UIButtons.createProjectButton("Projekter");
        projectButton.addActionListener(this);
        panel1.add(projectButton);
        leftPanel.add(projectButton);
        changeCursor(projectButton);

        fundsButton = UIButtons.createFundsButton("Fonde");
        fundsButton.addActionListener(this);
        panel1.add(fundsButton);
        leftPanel.add(fundsButton);
        changeCursor(fundsButton);

        archiveButton = UIButtons.createArchiveButton("Arkiv");
        archiveButton.addActionListener(this);
        panel1.add(archiveButton);
        leftPanel.add(archiveButton);
        changeCursor(archiveButton);
        panel1.add(leftPanel, BorderLayout.WEST);

        setupToggleBehavior(projectPropButton);
        setupToggleBehavior(projectButton);
        setupToggleBehavior(fundsButton);
        setupToggleBehavior(archiveButton);

        logoutButton = UIButtons.createLogutButton();
        logoutButton.addActionListener(this);
        logoutButton.setPreferredSize(new Dimension(150, 50)); // Set preferred size
        panel1.add(logoutButton, BorderLayout.EAST);
        changeCursor(logoutButton);

        return panel1;
    }

//done
    private void setupToggleBehavior(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(213, 213, 213, 255)); // Default color
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    
        button.addActionListener(e -> {
            resetButtonStates(); // Reset all buttons
            button.setBackground(new Color(169, 169, 169)); // Set selected button to darker color
        });
    
        changeCursor(button); // Set cursor to hand
    }   

    //ONLY IN GUEST

    private void resetButtonStates() {
        projectPropButton.setBackground(new Color(213, 213, 213, 255));
        projectButton.setBackground(new Color(213, 213, 213, 255));
        fundsButton.setBackground(new Color(213, 213, 213, 255));
        archiveButton.setBackground(new Color(213, 213, 213, 255));
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

    private JPanel createSidePanel() {
        // Create the main panel
        panel2.setBackground(new Color(213, 213, 213, 255));
        panel2.setPreferredSize(new Dimension(150, 100));
        // panel2.setLayout(new BorderLayout()); // Use BorderLayout for better
        // organization

        return panel2;
    }

    private void filterByTag() {
        updateBeforeFilter();

        ArrayList<fundClass> fundList = new ArrayList<>();
        ArrayList<project> projectList = new ArrayList<>();
        ArrayList<proposalProject> proposalList = new ArrayList<>();

        for (String s : selectedTags) {
            // Loop through proposals and add only those matching the tag
            for (proposalProject proposal : sortedProposalList) {
                if (proposal.getCategories().contains(s) && !proposalList.contains(proposal)) {
                    proposalList.add(proposal);
                }
            }
            for (project project : sortedProjectList) {
                if (project.getCategories().contains(s) && !projectList.contains(project)) {
                    projectList.add(project);
                }
            }
            for (fundClass fund : sortedFundList) {
                if (fund.getCategories().contains(s) && !fundList.contains(fund)) {
                    fundList.add(fund);
                }
            }
        }

        if (selectedTags.isEmpty()) {
            updateFundList();
            updateProposalProjectList();
            updateProjectList();
        } else {
            updateFundList(fundList);
            updateProposalProjectList(proposalList);
            updateProjectList(projectList);
        }

    }

    private int updateBeforeFilter() {

        if (clickCounts[0] % 3 == 1) {
            sortedProposalList = sorter.compareTitleProposal(sortedProposalList);
            sortedFundList = sorter.compareTitleFund(sortedFundList);
            sortedProjectList = sorter.compareTitleProject(sortedProjectList);
            return 0;

        } else if (clickCounts[0] % 3 == 2) {
            sortedProposalList = sorter.compareTitleProposalReverse(sortedProposalList);
            sortedFundList = sorter.compareTitleReverseFund(sortedFundList);
            sortedProjectList = sorter.compareTitleReverseProject(sortedProjectList);
            return 0;

        } else {
            sortedProjectList = sorter.sortByClosestDateProject(sortedProjectList);
            sortedFundList = sorter.sortFundByClosestDeadline(sortedFundList);
            sortedProposalList = sorter.sortByClosestDateProposal(sortedProposalList);
        }

        if (clickCounts[1] % 3 == 1) {
            sortedProposalList = sorter.compareOwnerProposal(sortedProposalList);
            sortedProjectList = sorter.compareOwnerProject(sortedProjectList);
            return 0;

        } else if (clickCounts[1] % 3 == 2) {
            sortedProposalList = sorter.compareReverseOwnerProposal(sortedProposalList);
            sortedProjectList = sorter.compareOwnerReverseProject(sortedProjectList);
            return 0;

        } else {
            sortedProjectList = sorter.sortByClosestDateProject(sortedProjectList);
            sortedFundList = sorter.sortFundByClosestDeadline(sortedFundList);
            sortedProposalList = sorter.sortByClosestDateProposal(sortedProposalList);
        }

        if (clickCounts[2] % 3 == 1) {
            sortedFundList = sorter.sortFundByClosestDeadline(sortedFundList);
            return 0;

        } else if (clickCounts[2] % 3 == 2) {
            sortedFundList = sorter.sortFundByFurthestDeadline(sortedFundList);
            return 0;

        } else {
            sortedFundList = sorter.sortFundByClosestDeadline(sortedFundList);
        }

        if (clickCounts[3] % 3 == 1) {
            sortedProjectList = sorter.sortByClosestDateProject(sortedProjectList);
            sortedProposalList = sorter.sortByClosestDateProposal(sortedProposalList);
            return 0;

        } else if (clickCounts[3] % 3 == 2) {
            sortedProjectList = sorter.sortByFurthestDateProject(sortedProjectList);
            sortedProposalList = sorter.sortByFurthestDateProposal(sortedProposalList);
            System.out.println("--- Here is our flag ---");
            return 0;

        } else {
            sortedProjectList = sorter.sortByClosestDateProject(sortedProjectList);
            sortedProposalList = sorter.sortByClosestDateProposal(sortedProposalList);
        }

        if (clickCounts[4] % 3 == 1) {
            sortedFundList = sorter.compareBudgetFundDecreasing(sortedFundList);
            return 0;

        } else if (clickCounts[4] % 3 == 2) {
            sortedFundList = sorter.compareBudgetFundIncreasing(sortedFundList);
            return 0;

        } else {
            sortedProjectList = sorter.sortByClosestDateProject(sortedProjectList);
            sortedFundList = sorter.sortFundByClosestDeadline(sortedFundList);
            sortedProposalList = sorter.sortByClosestDateProposal(sortedProposalList);
        }
        return 1;
    }

    // Refresh the panel to show the filtered list

    private void createTagButton(String newTag) {
        // Truncate tag name if it's longer than 17 characters
        String displayTag = newTag.length() > 17 ? newTag.substring(0, 16) + "..." : newTag;
    
        // Create the button with the display name
        JToggleButton tagButtonInstance = new JToggleButton(displayTag);
        tagButtonInstance.setPreferredSize(new Dimension(120, 25));
        // Add action listener for button selection
        tagButtonInstance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tagButtonInstance.isSelected()) {
                    selectedTags.add(newTag); // Use full tag in the list
                } else {
                    selectedTags.remove(newTag); // Use full tag in the list
                }
                System.out.println("<<<<<<<<" + selectedTags.toString());
                filterByTag();
            }
        });
    
        panel2.add(tagButtonInstance); // Add the button to the panel
        panel2.revalidate(); // Update the layout
        panel2.repaint(); // Re-render the panel
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

    // Separate view for "Projekt forslag"
    private JPanel createproposalProjectView() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        

        // Panel to display project proposals dynamically
        proposalProjectListPanel = new JPanel();
        proposalProjectListPanel.setLayout(new BoxLayout(proposalProjectListPanel, BoxLayout.Y_AXIS));


        JButton addProposal = new JButton("Tilføj et nyt projekt forslag");
        addProposal.addActionListener(e -> {
            openproposalProjectDialog();
            updateProposalProjectList();
        });
        JButton changeProposal = new JButton("Rediger et projekt forslag");
        changeProposal.addActionListener(e -> {
            editProjectProposal.openEditProjectPropDialog();
            updateProjectList();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0)); // Set GridLayout with 1 row, 2 columns, and 10px horizontal gap
        buttonPanel.add(addProposal);
        buttonPanel.add(changeProposal);
        panel.add(buttonPanel, BorderLayout.NORTH);

        // Scroll pane to handle multiple proposals
        JScrollPane scrollPane = new JScrollPane(proposalProjectListPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    //KEEP ITS DIFEFRENT
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

    private JPanel createArchiveView() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        // Title label
        JLabel label = new JLabel("Arkiv");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setFont(new Font("Arial", Font.BOLD, 18));

        panel.add(label);

        // Buttons for "Proposals," "Projects," and "Funds"
        JButton proposalsButton = new JButton("Afviste projektforslag");
        JButton projectsButton = new JButton("Arkiveret projekter");
        JButton fundsButton = new JButton("Arkiveret fonde");

        // Set button sizes (optional, adjust as needed)
        Dimension buttonSize = new Dimension(150, 50);
        proposalsButton.setPreferredSize(buttonSize);
        projectsButton.setPreferredSize(buttonSize);
        fundsButton.setPreferredSize(buttonSize);

        // Add action listeners to each button to display respective archives
        proposalsButton.addActionListener(e -> displayArchiveList("proposalProjectsDetails", main.deniedProposalList));
        projectsButton.addActionListener(e -> displayArchiveList("ProjectDetails", main.archiveProjectList));
        fundsButton.addActionListener(e -> displayArchiveList("FundDetails", main.archiveFundList));

        // Layout buttons in a single row
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        panel.add(proposalsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        panel.add(projectsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        panel.add(fundsButton);

        return panel;
    }

//KEEP IS DIFEFRNET

    // Creates the right-side panel with different views for each archive type

    // Method to update the right-side panel based on selected archive type

    private <T> void displayItemDetails(T item) {
        // Create a new panel or dialog to show item details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        // Display details based on the type of item
        if (item instanceof project) {
            project proj = (project) item;
            detailsPanel.add(new JLabel("Titel: " + proj.getTitle()));
            detailsPanel.add(new JLabel("Beskrivelse: " + proj.getDescription()));
            detailsPanel.add(new JLabel("Kategorier: " + proj.getCategories()));
            detailsPanel.add(new JLabel("Ide/Formål: " + proj.getProjectPurpose()));
            detailsPanel.add(new JLabel("Ejer: " + proj.getProjectOwner()));
            detailsPanel.add(new JLabel("Målgruppe: " + proj.getProjectTargetAudience()));
            detailsPanel.add(new JLabel("Aktiviter: " + proj.getProjectActivities()));
            detailsPanel.add(new JLabel("\n"));

            detailsPanel.add(new JLabel("Dato fra: " + proj.getProjectTimeSpanFrom()));
            detailsPanel.add(new JLabel("Dato til: " + proj.getProjectTimeSpanTo()));
            detailsPanel.add(new JLabel("\n"));

            detailsPanel.add(new JLabel("Dato lavet: " + proj.getDateCreated()));

        } else if (item instanceof proposalProject) {
            proposalProject proposal = (proposalProject) item;
            detailsPanel.add(new JLabel("Titel: " + proposal.getTitle()));
            detailsPanel.add(new JLabel("Beskrivelse: " + proposal.getDescription()));
            detailsPanel.add(new JLabel("Kategorier: " + proposal.getCategories()));
            detailsPanel.add(new JLabel("Ide/Formål: " + proposal.getProjectPurpose()));
            detailsPanel.add(new JLabel("Ejer: " + proposal.getProjectOwner()));
            detailsPanel.add(new JLabel("Målgruppe: " + proposal.getProjectTargetAudience()));
            detailsPanel.add(new JLabel("Aktiviter: " + proposal.getProjectActivities()));
            detailsPanel.add(new JLabel("\n"));

            detailsPanel.add(new JLabel("Dato fra: " + proposal.getProjectTimeSpanFrom()));
            detailsPanel.add(new JLabel("Dato til: " + proposal.getProjectTimeSpanTo()));
            detailsPanel.add(new JLabel("\n"));

            detailsPanel.add(new JLabel("Dato lavet: " + proposal.getDateCreated()));

        } else if (item instanceof fundClass) {
            fundClass fund = (fundClass) item;
            detailsPanel.add(new JLabel("Titel: " + fund.getTitle()));
            detailsPanel.add(new JLabel("Beskrivelse: " + fund.getDescription()));
            detailsPanel.add(new JLabel("Kategorier: " + fund.getCategories()));
            if (fund.getDeadlines().get(0) == LocalDateTime.of(3000, 1, 1, 0, 0)) {
                detailsPanel.add(new JLabel("Ansøgningsfrist(er): Løbende"));
            } else {
                detailsPanel.add(new JLabel("Ansøgningsfrist(er): " + fund.getDeadlines()));
            }
            detailsPanel.add(new JLabel("Kontakter: " + fund.getContacts()));
            detailsPanel.add(new JLabel("Budget span: " + fund.getBudgetSpan()));
            detailsPanel.add(new JLabel("Tidligere samarbejde: " + fund.getCollaborationHistory()));
            detailsPanel.add(new JLabel("Hjemmeside: " + fund.getFundWebsite()));
            detailsPanel.add(new JLabel("\n"));
            detailsPanel.add(new JLabel("Dato lavet: " + fund.getDateCreated()));

        } else {
            detailsPanel.add(new JLabel("Details: " + item.toString()));
        }

        // Show details in a dialog box
        JOptionPane.showMessageDialog(null, detailsPanel, "Item Details", JOptionPane.INFORMATION_MESSAGE);
    }

    //KEEP Sets the specific frame
    // Show the frame
    public void show() {
        main.initializeLists();
        updateProposalProjectList();
        updateProjectList();
        updateFundList();
        frame.setVisible(true);
        updateCategoryPanel();
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

        JLabel nameLabel = new JLabel("Titel:*");
        JTextField nameField = new JTextField();

        JLabel ideaLabel = new JLabel("Idé/Formål:*");
        JTextField ideaField = new JTextField();

        JLabel descriptionLabel = new JLabel("Kort beskrivelse af projektet:*");
        JTextArea descriptionArea = new JTextArea(5, 20);
        JScrollPane desciptionScrollPane = new JScrollPane(descriptionArea);

        JLabel ownerLabel = new JLabel("Ejer af idé/forslaget:*");
        JTextField ownerField = new JTextField();

        JLabel targetLabel = new JLabel("Målgruppe (hvem gavner dette forslag):*");
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
                    updateCategoryPanel(); // Call your desired method

                    // Add ItemListener to the checkbox

                    tagPanel.revalidate();
                    tagPanel.repaint();
                }
            }
        });

        JButton submitButton = new JButton("Tilføj");
        submitButton.addActionListener(event -> {
            System.out.println("Submit button clicked");

            boolean hasError = false; // Flag to check if any validation error occurs

            // Proposal Error Handling

            // Title Error Handling
            if (!validationUtils.isWithinLowerCharLimit(nameField.getText())) {
                isInvalidLenght = true;
                UserFrameErrorHandling.displayTitleError(isInvalidLenght);
                System.out.println("Title error: Length is invalid");
                hasError = true;
            } else {
                tempTitle = nameField.getText();
                System.out.println("Title set: " + tempTitle);
            }

            // Ideas Error Handling
            if (!validationUtils.isWithinLowerCharLimit(ideaField.getText())) {
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayIdeaError(isInvalidLenght));
                System.out.println("Idea error: Length is invalid");
                hasError = true;
            } else {
                tempIdea = ideaField.getText();
                System.out.println("Idea set: " + tempIdea);
            }

            // Description Error Handling
            if (!validationUtils.isWithinUpperCharLimit(descriptionArea.getText())) {
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayDescriptionError(isInvalidLenght));
                System.out.println("Description error: Length is invalid");
                hasError = true;
            } else {
                tempDescription = descriptionArea.getText();
                System.out.println("Description set: " + tempDescription);
            }

            // Owner Error Handling
            if (!validationUtils.isWithinLowerCharLimit(ownerField.getText())) {
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayOwnerError(isInvalidLenght));
                System.out.println("Owner error: Length is invalid");
                hasError = true;
            } else {
                tempOwner = ownerField.getText();
                System.out.println("Owner set: " + tempOwner);
            }

            // Target Audience Error Handling
            if (!validationUtils.isWithinLowerCharLimit(targetField.getText())) {
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayTargetAudienceError(isInvalidLenght));
                System.out.println("Target Audience error: Length is invalid");
                hasError = true;
            } else {
                tempTargetAudience = targetField.getText();
                System.out.println("Target Audience set: " + tempTargetAudience);
            }

            // Budget Error Handling
            String trimmedBudget = budgetField.getText().trim().replace(".", "").replace(",", "");
            if (!validationUtils.isNumericInput(trimmedBudget)) {
                dialog.add(UserFrameErrorHandling.displayBudgetError());
                System.out.println("Budget error: Invalid input");
                hasError = true;
            } else {
                tempBudget = Long.parseLong(trimmedBudget);
                System.out.println("Budget set: " + tempBudget);
            }

            // From Date Error Handling
            // To Date Error Handling
            // If From Date is after To Date
            if (((Date) fromDateSpinner.getValue()).after((Date) toDateSpinner.getValue())) {
                dialog.add(UserFrameErrorHandling.displayDateSpinnerError());
                System.out.println("Date error: From date is after To date");
                hasError = true;
            } else {
                tempFromDate = ((Date) fromDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate();
                tempToDate = ((Date) toDateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                tempFromDateLDT = tempFromDate.atStartOfDay();
                tempToDateLDT = tempToDate.atStartOfDay();
                System.out.println("Dates set: From " + tempFromDateLDT + " To " + tempToDateLDT);
            }

            // Activities Error Handling
            if (!validationUtils.isWithinUpperCharLimit(activitiesField.getText())) {
                isInvalidLenght = true;
                dialog.add(UserFrameErrorHandling.displayActivityError(isInvalidLenght));
                System.out.println("Activities error: Length is invalid");
                hasError = true;
            } else {
                tempActivities = activitiesField.getText();
                System.out.println("Activities set: " + tempActivities);
            }

            // Categories Error Handling
            // If no categories are selected
            ArrayList<String> selectedCatagories = new ArrayList<>();
            for (Component comp : tagPanel.getComponents()) {
                if (comp instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) comp;
                    if (checkBox.isSelected()) {
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
                    tempActivities);

            // Add the proposal to the list and update UI
            main.proposalList.add(proposal);
            updateProposalProjectList();
            write();
            System.out.println("Proposal added to list and UI updated");
            JOptionPane.showMessageDialog(dialog, "Projektforslaget er blevet tilføjet", "Projektforslag tilføjet", JOptionPane.INFORMATION_MESSAGE);

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
                .addComponent(submitButton));

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
                .addComponent(submitButton));

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    private void updateProposalProjectList(ArrayList<proposalProject> smallerList) {
        System.out.println("Updating proposal project list");
        proposalProjectListPanel.removeAll();
        JButton proposalTitleSortButton = UIButtons.sortingButtons("title", clickCounts);
        JButton proposalOwnerSortButton = UIButtons.sortingButtons("owner", clickCounts);
        JButton proposalDateButton = UIButtons.sortingButtons("date", clickCounts);
        JButton catagoriesButton = UIButtons.createListCatagoryButton("Kategorier");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0))); // Add space between buttons
        buttonPanel.add(proposalTitleSortButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(proposalOwnerSortButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(proposalDateButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(catagoriesButton);
        proposalProjectListPanel.add(buttonPanel);

        proposalTitleSortButton.addActionListener(e -> {
            System.out.println("Title button clicked");
            clickCounts[0]++;
            clickCounts[1] = 0;
            clickCounts[2] = 0;
            clickCounts[3] = 0;

            JButton newButton = UIButtons.sortingButtons("title", clickCounts);
            proposalTitleSortButton.setText(newButton.getText());
            proposalTitleSortButton.setIcon(newButton.getIcon());

            proposalOwnerSortButton.setText("Ejer");
            proposalOwnerSortButton.setIcon(null);
            proposalDateButton.setText("Oprettelsesdato");
            proposalDateButton.setIcon(null);
            updateProposalProjectList(smallerList);
            filterByTag();
            proposalProjectListPanel.revalidate();
            proposalProjectListPanel.repaint();
        });

        proposalOwnerSortButton.addActionListener(e -> {
            System.out.println("Owner button clicked");
            clickCounts[0] = 0;
            clickCounts[1]++;
            clickCounts[2] = 0;
            clickCounts[3] = 0;

            JButton newButton = UIButtons.sortingButtons("owner", clickCounts);
            proposalOwnerSortButton.setText(newButton.getText());
            proposalOwnerSortButton.setIcon(newButton.getIcon());

            proposalTitleSortButton.setText("Titel");
            proposalTitleSortButton.setIcon(null);
            proposalDateButton.setText("Oprettelsesdato");
            proposalDateButton.setIcon(null);
            filterByTag();
            proposalProjectListPanel.revalidate();
            proposalProjectListPanel.repaint();
        });

        proposalDateButton.addActionListener(e -> {
            System.out.println("deadline button clicked");
            clickCounts[0] = 0;
            clickCounts[1] = 0;
            clickCounts[2] = 0;
            clickCounts[3]++;

            JButton newButton = UIButtons.sortingButtons("date", clickCounts);
            proposalDateButton.setText(newButton.getText());
            proposalDateButton.setIcon(newButton.getIcon());

            proposalTitleSortButton.setText("Titel");
            proposalTitleSortButton.setIcon(null);
            proposalOwnerSortButton.setText("Ejer");
            proposalOwnerSortButton.setIcon(null);

            filterByTag();
            proposalProjectListPanel.revalidate();
            proposalProjectListPanel.repaint();
        });
        // proposalProjectListPanel.add(buttonPanel);
        // Add labels for each proposal project
        for (proposalProject proposal : smallerList) {
            JLabel proposalLabel;

            // Check if the categories list is empty and set a default value
            String categoriesDisplay = proposal.getCategories().isEmpty() ? "No Categories"
                    : proposal.getCategories().toString();

            if (proposal.getTitle().length() < 20) {
                proposalLabel = new JLabel(
                        String.format("%-30s %-30s %-30s %-30s",
                                proposal.getTitle(),
                                proposal.getProjectOwner(),
                                proposal.getDateCreated().toString().split("T")[0],
                                categoriesDisplay));
            } else {
                proposalLabel = new JLabel(
                        String.format("%-30s %-30s %-30s %-30s",
                                proposal.getTitle().substring(0, 17) + "...",
                                proposal.getProjectOwner(),
                                proposal.getDateCreated().toString().split("T")[0],
                                categoriesDisplay));
            }
            JButton proposalButton = UIButtons.createNewListButton(proposalLabel, false);
            proposalButton.addActionListener(e ->  {
                showProjectProbDetails(proposal);
                resetAllListButtons(0); // Reset all buttons to default color
                projectButton.setBackground(new Color(150, 150, 150));
            });    
            proposalProjectListPanel.add(proposalButton);
            proposalProjectListPanel.add(Box.createHorizontalGlue());
            proposalProjectListPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Add space to the left of the
                                                                                     // buttons
        }
        // Update the view
        proposalProjectListPanel.revalidate();
        proposalProjectListPanel.repaint();
    }

    private void resetAllListButtons(int List){
        //If List == 0, reset proposalProjectListPanel buttons
        //If List == 1, reset projectListPanel buttons
        //If List == 2, reset fundListPanel buttons

        if(List == 0){
            for (Component comp : proposalProjectListPanel.getComponents()) {
                if (comp instanceof JButton) {
                    JButton button = (JButton) comp;
                    button.setFont(new Font("SansSerif", Font.PLAIN, 14)); // Set font
                    button.setPreferredSize(new Dimension(300, 30));
                    button.setFocusPainted(false); // Remove focus border
                    button.setBackground(new Color(245, 245, 245)); // Set background color
                    button.setForeground(Color.DARK_GRAY); // Set text color
                    button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Set border
                    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor                 
                    }
            }
        } else if(List == 1){
            for (Component comp : projectListPanel.getComponents()) {
                if (comp instanceof JButton) {
                    JButton button = (JButton) comp;
                    button.setFont(new Font("SansSerif", Font.PLAIN, 14)); // Set font
                    button.setPreferredSize(new Dimension(300, 30));
                    button.setFocusPainted(false); // Remove focus border
                    button.setBackground(new Color(245, 245, 245)); // Set background color
                    button.setForeground(Color.DARK_GRAY); // Set text color
                    button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Set border
                    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor                
                    }
            }
        } else if(List == 2){
            for (Component comp : fundListPanel.getComponents()) {
                if (comp instanceof JButton) {
                    JButton button = (JButton) comp;
                    button.setFont(new Font("SansSerif", Font.PLAIN, 14)); // Set font
                    button.setPreferredSize(new Dimension(300, 30));
                    button.setFocusPainted(false); // Remove focus border
                    button.setBackground(new Color(245, 245, 245)); // Set background color
                    button.setForeground(Color.DARK_GRAY); // Set text color
                    button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Set border
                    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor  
                    }
            }
        }

    }

    private void updateProjectList(ArrayList<project> smallerList) { // SORT HERE
        // Ryd panelet før opdatering

        System.out.println("Updating project list");
        projectListPanel.removeAll();
        JButton projectTitleSortButton = UIButtons.sortingButtons("title", clickCounts);
        JButton projectOwnerSortButton = UIButtons.sortingButtons("owner", clickCounts);
        JButton projectDeadlineSortButton = UIButtons.sortingButtons("date", clickCounts);
        JButton catagoriesButton = UIButtons.createListCatagoryButton("Kategorier");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0))); // Add space between buttons
        buttonPanel.add(projectTitleSortButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(projectOwnerSortButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(projectDeadlineSortButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(catagoriesButton);
        projectListPanel.add(buttonPanel);
        projectTitleSortButton.addActionListener(e -> {
            System.out.println("Title button clicked");
            clickCounts[0]++;
            clickCounts[1] = 0;
            clickCounts[2] = 0;
            clickCounts[3] = 0;

            JButton newButton = UIButtons.sortingButtons("title", clickCounts);
            projectTitleSortButton.setText(newButton.getText());
            projectTitleSortButton.setIcon(newButton.getIcon());

            projectOwnerSortButton.setText("Ejer");
            projectOwnerSortButton.setIcon(null);
            projectDeadlineSortButton.setText("Deadline");
            projectDeadlineSortButton.setIcon(null);
            filterByTag();
            projectListPanel.revalidate();
            projectListPanel.repaint();
        });

        projectOwnerSortButton.addActionListener(e -> {
            System.out.println("Owner button clicked");
            clickCounts[0] = 0;
            clickCounts[1]++;
            clickCounts[2] = 0;
            clickCounts[3] = 0;

            JButton newButton = UIButtons.sortingButtons("owner", clickCounts);
            projectOwnerSortButton.setText(newButton.getText());
            projectOwnerSortButton.setIcon(newButton.getIcon());

            projectTitleSortButton.setText("Titel");
            projectTitleSortButton.setIcon(null);
            projectDeadlineSortButton.setText("Deadline");
            projectDeadlineSortButton.setIcon(null);

            filterByTag();
            projectListPanel.revalidate();
            projectListPanel.repaint();
        });

        projectDeadlineSortButton.addActionListener(e -> {
            System.out.println("deadline button clicked");
            clickCounts[0] = 0;
            clickCounts[1] = 0;
            clickCounts[2]++;
            clickCounts[3] = 0;

            JButton newButton = UIButtons.sortingButtons("date", clickCounts);
            projectDeadlineSortButton.setText(newButton.getText());
            projectDeadlineSortButton.setIcon(newButton.getIcon());

            projectTitleSortButton.setText("Titel");
            projectTitleSortButton.setIcon(null);
            projectOwnerSortButton.setText("Ejer");
            projectOwnerSortButton.setIcon(null);

            filterByTag();
            projectListPanel.revalidate();
            projectListPanel.repaint();
        });

        // Add labels for each project
        for (project project : smallerList) {
            if (project.getFunds().isEmpty()) {
                compareProjectCatsWithFundCats comparer = new compareProjectCatsWithFundCats();
                project.setFundList(comparer.compareCategoriesWithFund(true, main.fundList, project));
            }

            JLabel projectLabel;
            String categoriesDisplay = project.getCategories().isEmpty() ? "No Categories"
                    : project.getCategories().toString();

            if (project.getFunds().isEmpty()) {
                if (project.getTitle().length() < 20) {
                    projectLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s %-30s",
                                    project.getTitle(),
                                    project.getProjectOwner(),
                                    project.getDateCreated().toString().split("T")[0],
                                    "No Deadlines",
                                    categoriesDisplay));
                } else {
                    projectLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s %-30s",
                                    project.getTitle().substring(0, 17) + "...",
                                    project.getProjectOwner(),
                                    project.getDateCreated().toString().split("T")[0],
                                    "No Deadlines",
                                    categoriesDisplay));
                }
            } else {
                if (project.getTitle().length() < 20) {
                    projectLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s %-30s",
                                    project.getTitle(),
                                    project.getProjectOwner(),
                                    project.getDateCreated().toString().split("T")[0],
                                    project.getFunds().get(0).getDeadlines().toString(),
                                    categoriesDisplay));
                } else {
                    projectLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s %-30s",
                                    project.getTitle().substring(0, 17) + "...",
                                    project.getProjectOwner(),
                                    project.getDateCreated().toString().split("T")[0],
                                    project.getFunds().get(0).getDeadlines().toString(),
                                    categoriesDisplay));
                }
            }
            JButton projectButton = UIButtons.createNewListButton(projectLabel, false);
            projectButton.addActionListener(e -> {
                showProjectDetails(project);
                resetAllListButtons(1); // Reset all buttons to default color
                projectButton.setBackground(new Color(150, 150, 150));
            });
            projectListPanel.add(projectButton);
            projectListPanel.add(Box.createHorizontalGlue());
            projectListPanel.add(Box.createRigidArea(new Dimension(30, 0))); // Add space to the left of the buttons
        }

        // Update the view
        projectListPanel.revalidate();
        projectListPanel.repaint();
    }

    // SORT HERE
    private void updateProposalProjectList() {
        System.out.println("Updating proposal project list");
        proposalProjectListPanel.removeAll();
        JButton proposalTitleSortButton = UIButtons.sortingButtons("title", clickCounts);
        JButton proposalOwnerSortButton = UIButtons.sortingButtons("owner", clickCounts);
        JButton proposalDateButton = UIButtons.sortingButtons("date", clickCounts);
        JButton catagoriesButton = UIButtons.createListCatagoryButton("Kategorier");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0))); // Add space between buttons
        buttonPanel.add(proposalTitleSortButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(proposalOwnerSortButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(proposalDateButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(catagoriesButton);
        proposalProjectListPanel.add(buttonPanel);

        // proposalProjectListPanel.add(buttonPanel);
        proposalTitleSortButton.addActionListener(e -> {
            System.out.println("Title button clicked");
            clickCounts[0]++;
            clickCounts[1] = 0;
            clickCounts[2] = 0;
            clickCounts[3] = 0;

            JButton newButton = UIButtons.sortingButtons("title", clickCounts);
            proposalTitleSortButton.setText(newButton.getText());
            proposalTitleSortButton.setIcon(newButton.getIcon());

            proposalOwnerSortButton.setText("Ejer");
            proposalOwnerSortButton.setIcon(null);
            proposalDateButton.setText("Oprettelsesdato");
            proposalDateButton.setIcon(null);
            filterByTag();
            proposalProjectListPanel.revalidate();
            proposalProjectListPanel.repaint();
        });

        proposalOwnerSortButton.addActionListener(e -> {
            System.out.println("Owner button clicked");
            clickCounts[0] = 0;
            clickCounts[1]++;
            clickCounts[2] = 0;
            clickCounts[3] = 0;

            JButton newButton = UIButtons.sortingButtons("owner", clickCounts);
            proposalOwnerSortButton.setText(newButton.getText());
            proposalOwnerSortButton.setIcon(newButton.getIcon());

            proposalTitleSortButton.setText("Titel");
            proposalTitleSortButton.setIcon(null);
            proposalDateButton.setText("Oprettelsesdato");
            proposalDateButton.setIcon(null);

            filterByTag();
            proposalProjectListPanel.revalidate();
            proposalProjectListPanel.repaint();
        });
        proposalDateButton.addActionListener(e -> {
            System.out.println("deadline button clicked");
            clickCounts[0] = 0;
            clickCounts[1] = 0;
            clickCounts[2] = 0;
            clickCounts[3]++;

            JButton newButton = UIButtons.sortingButtons("date", clickCounts);
            proposalDateButton.setText(newButton.getText());
            proposalDateButton.setIcon(newButton.getIcon());

            proposalTitleSortButton.setText("Titel");
            proposalTitleSortButton.setIcon(null);
            proposalOwnerSortButton.setText("Ejer");
            proposalOwnerSortButton.setIcon(null);

            filterByTag();
            proposalProjectListPanel.revalidate();
            proposalProjectListPanel.repaint();
        });

        for (proposalProject proposal : sortedProposalList) {
            JLabel proposalLabel;

            // Check if the categories list is empty and set a default value
            String categoriesDisplay = proposal.getCategories().isEmpty() ? "No Categories"
                    : proposal.getCategories().toString();

            if (proposal.getTitle().length() < 20) {
                proposalLabel = new JLabel(
                        String.format("%-30s %-30s %-30s %-30s",
                                proposal.getTitle(),
                                proposal.getProjectOwner(),
                                proposal.getDateCreated().toString().split("T")[0],
                                categoriesDisplay));
            } else {
                proposalLabel = new JLabel(
                        String.format("%-30s %-30s %-30s %-30s",
                                proposal.getTitle().substring(0, 17) + "...",
                                proposal.getProjectOwner(),
                                proposal.getDateCreated().toString().split("T")[0],
                                categoriesDisplay));
            }

            JButton proposalButton = UIButtons.createNewListButton(proposalLabel, false);
            proposalButton.addActionListener(e -> {
                showProjectProbDetails(proposal);
                resetAllListButtons(0); // Reset all buttons to default color
                proposalButton.setBackground(new Color(150, 150, 150));
            });
            proposalProjectListPanel.add(proposalButton);
            proposalProjectListPanel.add(Box.createHorizontalGlue());
            proposalProjectListPanel.add(Box.createRigidArea(new Dimension(30, 0))); // Add space to the left of the buttons
        }
        // Update the view
        proposalProjectListPanel.revalidate();
        proposalProjectListPanel.repaint();
    }

    private void updateProjectList() { // SORT HERE
        // Ryd panelet før opdatering
        System.out.println("Updating project list");
        projectListPanel.removeAll();
        JButton projectTitleSortButton = UIButtons.sortingButtons("title", clickCounts);
        JButton projectOwnerSortButton = UIButtons.sortingButtons("owner", clickCounts);
        JButton projectDeadlineSortButton = UIButtons.sortingButtons("date", clickCounts);
        JButton catagoriesButton = UIButtons.createListCatagoryButton("Kategorier");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0))); // Add space between buttons
        buttonPanel.add(projectTitleSortButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(projectOwnerSortButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(projectDeadlineSortButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(catagoriesButton);
        projectListPanel.add(buttonPanel);
        projectTitleSortButton.addActionListener(e -> {
            System.out.println("Title button clicked");
            clickCounts[0]++;
            clickCounts[1] = 0;
            clickCounts[2] = 0;
            clickCounts[3] = 0;

            JButton newButton = UIButtons.sortingButtons("title", clickCounts);
            projectTitleSortButton.setText(newButton.getText());
            projectTitleSortButton.setIcon(newButton.getIcon());

            projectOwnerSortButton.setText("Ejer");
            projectOwnerSortButton.setIcon(null);
            projectDeadlineSortButton.setText("Deadline");
            projectDeadlineSortButton.setIcon(null);
            filterByTag();
            projectListPanel.revalidate();
            projectListPanel.repaint();
        });
        projectOwnerSortButton.addActionListener(e -> {
            System.out.println("Owner button clicked");
            clickCounts[0] = 0;
            clickCounts[1]++;
            clickCounts[2] = 0;
            clickCounts[3] = 0;

            JButton newButton = UIButtons.sortingButtons("owner", clickCounts);
            projectOwnerSortButton.setText(newButton.getText());
            projectOwnerSortButton.setIcon(newButton.getIcon());

            projectTitleSortButton.setText("Titel");
            projectTitleSortButton.setIcon(null);
            projectDeadlineSortButton.setText("Deadline");
            projectDeadlineSortButton.setIcon(null);
            filterByTag();

            projectListPanel.revalidate();
            projectListPanel.repaint();
        });

        projectDeadlineSortButton.addActionListener(e -> {
            System.out.println("deadline button clicked");
            clickCounts[0] = 0;
            clickCounts[1] = 0;
            clickCounts[2] = 0;
            clickCounts[3]++;
            clickCounts[4] = 0;

            JButton newButton = UIButtons.sortingButtons("date", clickCounts);
            projectDeadlineSortButton.setText(newButton.getText());
            projectDeadlineSortButton.setIcon(newButton.getIcon());

            projectTitleSortButton.setText("Titel");
            projectTitleSortButton.setIcon(null);
            projectOwnerSortButton.setText("Ejer");
            projectOwnerSortButton.setIcon(null);

            filterByTag();
            projectListPanel.revalidate();
            projectListPanel.repaint();
        });

        // Add labels for each project
        for (project project : sortedProjectList) {
            if (project.getFunds().isEmpty()) {
                compareProjectCatsWithFundCats comparer = new compareProjectCatsWithFundCats();
                project.setFundList(comparer.compareCategoriesWithFund(true, main.fundList, project));
            }

            JLabel projectLabel;
            String categoriesDisplay = project.getCategories().isEmpty() ? "No Categories"
                    : project.getCategories().toString();

            if (project.getFund() == null) {

                if (project.getTitle().length() < 20) {
                    projectLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s %-30s",
                                    project.getTitle(),
                                    project.getProjectOwner(),
                                    project.getDateCreated().toString().split("T")[0],
                                    "Ingen Beviling",
                                    categoriesDisplay));
                } else {
                    projectLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s %-30s",
                                    project.getTitle().substring(0, 17) + "...",
                                    project.getProjectOwner(),
                                    project.getDateCreated().toString().split("T")[0],
                                    "Ingen Beviling",
                                    categoriesDisplay));
                }
            } else {
                String deadline;
                if (fundQSort.allDeadlinesPassed(project.getFund())) {
                    deadline = "Alle frister Overskredet";
                } else {
                    if ((project.getFund().getDeadlines().toString()).contains("3000")) {
                        deadline = "Løbende Fond";
                    } else {
                        for (LocalDateTime dL : project.getFund().getDeadlines()) {
                            if (dL.isBefore(LocalDateTime.now())) {
                                project.getFund().getDeadlines().remove(dL);
                            }
                        }
                        deadline = project.getFund().getDeadlines().get(0).toString().replace("T", " ").replace("[", "")
                                .replace("]", "");
                    }
                }
                if (project.getTitle().length() < 20) {
                    projectLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s %-30s",
                                    project.getTitle(),
                                    project.getProjectOwner(),
                                    project.getDateCreated().toString().split("T")[0],
                                    deadline,
                                    categoriesDisplay));
                } else {
                    projectLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s %-30s",
                                    project.getTitle().substring(0, 17) + "...",
                                    project.getProjectOwner(),
                                    project.getDateCreated().toString().split("T")[0],
                                    deadline,
                                    categoriesDisplay));
                }
            }

            JButton projectButton = UIButtons.createNewListButton(projectLabel, false);
            projectButton.addActionListener(e -> {
                showProjectDetails(project);
                resetAllListButtons(1); // Reset all buttons to default color
                projectButton.setBackground(new Color(150, 150, 150));
            });
            projectListPanel.add(projectButton);
            projectListPanel.add(Box.createHorizontalGlue());
            projectListPanel.add(Box.createRigidArea(new Dimension(30, 0))); // Add space to the left of the buttons
        }

        // Update the view
        projectListPanel.revalidate();
        projectListPanel.repaint();
    }

    private void showProjectProbDetails(proposalProject proposal) {
        proposalProjectFullPanel.removeAll();

        // Display project proposal details
        proposalProjectFullPanel.add(new JLabel("Titel: " + proposal.getTitle()));
        proposalProjectFullPanel.add(new JLabel("\n"));
        proposalProjectFullPanel.add(new JLabel("Ejer: " + proposal.getProjectOwner()));
        proposalProjectFullPanel.add(new JLabel("\n"));

        proposalProjectFullPanel.add(new JLabel("Idé: " + proposal.getProjectPurpose()));
        proposalProjectFullPanel.add(new JLabel("\n"));

        proposalProjectFullPanel.add(new JLabel("Beskrivelse: "));

        insertWrappedText(proposal.getDescription(), proposalProjectFullPanel);
        proposalProjectFullPanel.add(new JLabel("\n"));

        proposalProjectFullPanel.add(new JLabel("Målgruppe: " + proposal.getProjectTargetAudience()));
        proposalProjectFullPanel.add(new JLabel("\n"));

        proposalProjectFullPanel.add(new JLabel("Budget: " + proposal.getProjectBudget()));
        proposalProjectFullPanel.add(new JLabel("\n"));

        // Display date range
        proposalProjectFullPanel.add(new JLabel("Fra Dato: " + proposal.getProjectTimeSpanFrom().toString()));
        proposalProjectFullPanel.add(new JLabel("Til Dato: " + proposal.getProjectTimeSpanTo().toString()));
        proposalProjectFullPanel.add(new JLabel("\n"));

        // Display project activities
        proposalProjectFullPanel.add(new JLabel("Aktiviteter: " + proposal.getProjectActivities()));
        proposalProjectFullPanel.add(new JLabel("\n"));

        // Display categories as a concatenated string
        String categories = String.join(", ", proposal.getCategories());
        proposalProjectFullPanel.add(new JLabel("Kategorier: " + categories));
        proposalProjectFullPanel.add(new JLabel("\n"));

        JCheckBox onlyOneNeededBox = new JCheckBox("Kun en kategori kræves for match", true);
        proposalProjectFullPanel.add(new JLabel("\n"));
        proposalProjectFullPanel.add(new JLabel("\n"));
        proposalProjectFullPanel.add(onlyOneNeededBox);

        JPanel matchingFundsPanel = new JPanel();
        matchingFundsPanel.setLayout(new BoxLayout(matchingFundsPanel, BoxLayout.Y_AXIS));
        proposalProjectFullPanel.add(matchingFundsPanel);



        Runnable updateMatchingFunds = () -> {
            matchingFundsPanel.removeAll();
            project tempProject = new project();
            tempProject.setTitle(proposal.getTitle());
            tempProject.setProjectPurpose(proposal.getProjectPurpose());
            tempProject.setDescription(proposal.getDescription());
            tempProject.setProjectOwner(proposal.getProjectOwner());
            tempProject.setProjectTargetAudience(proposal.getProjectTargetAudience());
            tempProject.setProjectBudget(proposal.getProjectBudget());
            tempProject.setTimeSpan(proposal.getProjectTimeSpanFrom(), proposal.getProjectTimeSpanTo());
            tempProject.setProjectActivities(proposal.getProjectActivities());
            for(String category : proposal.getCategories()) {
                tempProject.setCategories(category);
            }

             compareProjectCatsWithFundCats comparer = new compareProjectCatsWithFundCats();
            boolean onlyOneNeeded = onlyOneNeededBox.isSelected();
            ArrayList<fundClass> matchingFunds = comparer.compareCategoriesWithFund(onlyOneNeeded, main.fundList,
                    tempProject);

            if (!matchingFunds.isEmpty()) {
                matchingFundsPanel.add(new JLabel("Fonde med matchende kategorier:"));
                proposalProjectFullPanel.add(new JLabel("\n"));
                JPanel centerPanel = new JPanel();
                centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
                for (fundClass fund : matchingFunds) {
                    JButton recommendButton = UIButtons.createNewListButton(new JLabel(fund.getTitle()), true);
                    recommendButton.addActionListener(e -> showFundDetailsDialog(fund, tempProject));
                    centerPanel.add(recommendButton);
                    centerPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add space between buttons
                    // Add space to the left of the buttons
                    // centerPanel.add(Box.createRigidArea(new Dimension(50, 0)));
                }
                matchingFundsPanel.add(centerPanel);
            } else {
                matchingFundsPanel.add(new JLabel("Ingen fonde matcher nogle kategorier"));
                proposalProjectFullPanel.add(new JLabel("\n"));
            }

            matchingFundsPanel.revalidate();
            matchingFundsPanel.repaint();
        };

        onlyOneNeededBox.addActionListener(e -> updateMatchingFunds.run());

        updateMatchingFunds.run();
        // Add buttons to the panel

        // Refresh the panel to reflect the changes
        proposalProjectFullPanel.revalidate();
        proposalProjectFullPanel.repaint();
    }

    private void insertWrappedText(String text, JPanel panel) {
        String newText = new String();
        List<String> strings = new ArrayList<String>();
        int index = 0;
        while (index < text.length()) {
            strings.add(text.substring(index,
                    Math.min(index + 60, text.length())));
            index += Math.min(index + 60, text.length());
        }

        for (int i = 0; i < strings.size(); i++) {
            if (i + 1 < strings.size()) {
                if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                        && Character.isLetter(strings.get(i + 1).charAt(0))) {
                    newText += strings.get(i).trim() + "-";
                    newText += "\n";
                } else if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                        && !Character.isLetter(strings.get(i + 1).charAt(0))) {
                    newText += strings.get(i).trim() + strings.get(i + 1).charAt(0);
                    strings.set(i + 1, strings.get(i + 1).substring(1));
                    newText += "\n";
                } else {
                    newText += strings.get(i).trim();
                    newText += "\n";
                }
            } else {
                newText += strings.get(i).trim();
                newText += "\n";
            }
        }
        for (String s : newText.split("\n")) {
            panel.add(new JLabel(s));
        }
    }

    private void showProjectDetails(project project) {
        projectFullPanel.removeAll();

        // Display project details
        projectFullPanel.add(new JLabel("Titel: " + project.getTitle()));
        projectFullPanel.add(new JLabel("\n"));

        projectFullPanel.add(new JLabel("Ejer: " + project.getProjectOwner()));
        projectFullPanel.add(new JLabel("\n"));

        projectFullPanel.add(new JLabel("Idé: " + project.getProjectPurpose()));
        projectFullPanel.add(new JLabel("\n"));

        projectFullPanel.add(new JLabel("Beskrivelse: "));
        insertWrappedText(project.getDescription(), projectFullPanel);
        projectFullPanel.add(new JLabel("\n"));

        projectFullPanel.add(new JLabel("Målgruppe: " + project.getProjectTargetAudience()));
        projectFullPanel.add(new JLabel("\n"));

        projectFullPanel.add(new JLabel("Budget: " + project.getProjectBudget()));
        projectFullPanel.add(new JLabel("\n"));

        projectFullPanel.add(new JLabel("Fra Dato: " + project.getProjectTimeSpanFrom().toString()));
        projectFullPanel.add(new JLabel("Til Dato: " + project.getProjectTimeSpanTo().toString()));
        projectFullPanel.add(new JLabel("\n"));

        projectFullPanel.add(new JLabel("Aktiviteter: " + project.getProjectActivities()));
        projectFullPanel.add(new JLabel("\n"));

        projectFullPanel.add(new JLabel("Kategori: " + project.getCategories()));
        if (project.getFund() != null) {
            projectFullPanel.add(new JLabel("Bevillieget fund: " + project.getFund().getTitle()));
        }
        projectFullPanel.add(new JLabel("\n"));

        // Checkbox to toggle "only one category needed" logic
        JCheckBox onlyOneNeededCheckBox = new JCheckBox("Kun en kategori kræves for match", true); // Default to true
        projectFullPanel.add(new JLabel("\n"));
        projectFullPanel.add(new JLabel("\n"));
        projectFullPanel.add(onlyOneNeededCheckBox);

        // Panel for matching funds
        JPanel matchingFundsPanel = new JPanel();
        matchingFundsPanel.setLayout(new BoxLayout(matchingFundsPanel, BoxLayout.Y_AXIS));
        projectFullPanel.add(matchingFundsPanel);

        // Method to update the matching funds list based on checkbox state
        Runnable updateMatchingFunds = () -> {
            matchingFundsPanel.removeAll();

            compareProjectCatsWithFundCats comparer = new compareProjectCatsWithFundCats();
            boolean onlyOneNeeded = onlyOneNeededCheckBox.isSelected();
            ArrayList<fundClass> matchingFunds = comparer.compareCategoriesWithFund(onlyOneNeeded, main.fundList,
                    project);

            if (!matchingFunds.isEmpty()) {
                matchingFundsPanel.add(new JLabel("Fonde med matchende kategorier:"));
                projectFullPanel.add(new JLabel("\n"));
                JPanel centerPanel = new JPanel();
                centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
                for (fundClass fund : matchingFunds) {
                    JButton recommendButton = UIButtons.createNewListButton(new JLabel(fund.getTitle()), true);
                    recommendButton.addActionListener(e -> showFundDetailsDialog(fund, project));
                    centerPanel.add(recommendButton);
                    centerPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add space between buttons
                    // Add space to the left of the buttons
                    // centerPanel.add(Box.createRigidArea(new Dimension(50, 0)));
                }
                matchingFundsPanel.add(centerPanel);
            } else {
                matchingFundsPanel.add(new JLabel("Ingen fonde matcher nogle kategorier"));
                projectFullPanel.add(new JLabel("\n"));
            }

            matchingFundsPanel.revalidate();
            matchingFundsPanel.repaint();
        };

        // Add listener to update the list when checkbox state changes
        onlyOneNeededCheckBox.addActionListener(e -> updateMatchingFunds.run());

        // Initial update for matching funds
        updateMatchingFunds.run();

        // Archive button to archive the project

        projectFullPanel.revalidate();
        projectFullPanel.repaint();
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

    private void showFundDetails(fundClass fund) {
        tempContacts = fund.getContacts();
        fundFullPanel.removeAll();
        fundFullPanel.setLayout(new BoxLayout(fundFullPanel, BoxLayout.Y_AXIS));

        // Titel
        fundFullPanel.add(new JLabel("Titel:"));
        fundFullPanel.add(new JLabel(fund.getTitle() + "\n"));
        fundFullPanel.add(new JLabel("\n"));

        // Beskrivelse
        fundFullPanel.add(new JLabel("Beskrivelse:" + "\n"));
        insertWrappedText(fund.getDescription(), fundFullPanel);
        fundFullPanel.add(new JLabel("\n"));

        // Budget
        fundFullPanel.add(new JLabel("Beløb Fra: " + fund.getBudgetMin()));
        fundFullPanel.add(new JLabel("\n"));
        fundFullPanel.add(new JLabel("Beløb Til: " + fund.getBudgetMax()));
        fundFullPanel.add(new JLabel("\n"));

        // Ansøgningsfrist
        fundFullPanel.add(new JLabel("Ansøgningsfrist:"));
        List<String> formattedDeadlines = new ArrayList<>();
        for (LocalDateTime deadline : fund.getDeadlines()) {
            if (deadline.getYear() == 3000) {
                formattedDeadlines.add("Løbende Ansøgningsfrist");
            } else {
                formattedDeadlines.add(deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            }
        }
        insertWrappedText(String.join(", ", formattedDeadlines), fundFullPanel);
        fundFullPanel.add(new JLabel("\n"));

        // Kategori
        fundFullPanel.add(new JLabel("Kategori:"));
        insertWrappedText(String.join(", ", fund.getCategories()), fundFullPanel);
        fundFullPanel.add(new JLabel("\n"));

        // Tidligere samarbejde
        fundFullPanel.add(new JLabel("Tidligere samarbejde:"));
        insertWrappedText(String.join(", ", fund.getCollaborationHistory()), fundFullPanel);
        fundFullPanel.add(new JLabel("\n"));

        // Kontaktperson(er)
        fundFullPanel.add(new JLabel("Kontaktperson(er):"));
        if (!tempContacts.isEmpty()) {
            for (fundContactClass contact : tempContacts) {
                fundFullPanel.add(new JLabel(
                        contact.getContactName() + " - " +
                                contact.getContactPhoneNumber() + " - " +
                                contact.getContactEmail()));
            }
        } else {
            fundFullPanel.add(new JLabel("Ingen kontaktpersoner tilgængelige."));
        }
        fundFullPanel.add(new JLabel("\n"));

        // Hjemmeside
        fundFullPanel.add(new JLabel("Hjemmeside:"));
        fundFullPanel.add(new JLabel(fund.getFundWebsite()));
        fundFullPanel.add(new JLabel("\n"));

        // Refresh panel
        fundFullPanel.revalidate();
        fundFullPanel.repaint();
    }

    /*
     * formatNumber() - shortens number to be at most 4 chars
     * 
     * @arg string
     * Return: string
     *
     * This function takes a number in string form and converts it into a
     * shortened version that contains the 3 first numbers and a letter
     * representing the magnitude of the number i.e. 823746 would become
     * 823k and 876238576 would become 876m
     */
    private String formatNumber(String number) {

        String newnumber = number.substring(0, number.length() % 3);
        if (number.length() % 3 != 0) {
            switch (((number.length() - number.length() % 3) / 3)) {
                case 0:
                    newnumber += " ";
                    break;
                case 1:
                    newnumber += "k";
                    break;
                case 2:
                    newnumber += "m";
                    break;
                case 3:
                    newnumber += "b";
                    break;
                case 4:
                    newnumber += "t";
                    break;
                case 5:
                    newnumber += "q";
                    break;

            }
        } else if (number.length() % 3 == 0) {
            newnumber = number.substring(0, (number.length() % 3) + 3);
            switch (((number.length() - number.length() % 3) / 3) - 1) {
                case 0:
                    newnumber += " ";
                    break;
                case 1:
                    newnumber += "k";
                    break;
                case 2:
                    newnumber += "m";
                    break;
                case 3:
                    newnumber += "b";
                    break;
                case 4:
                    newnumber += "t";
                    break;
                case 5:
                    newnumber += "q";
                    break;

            }
        }
        return newnumber;

    }

    private void updateFundList(ArrayList<fundClass> smallerList) {
        fundListPanel.removeAll();
        JButton fundTitleButton = UIButtons.sortingButtons("title", clickCounts);
        JButton fundBudgetButton = UIButtons.sortingButtons("budget", clickCounts);
        JButton catagoriesButton = UIButtons.createListCatagoryButton("Kategorier");
        JButton deadlineButton = UIButtons.sortingButtons("deadline", clickCounts);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0))); // Add space between buttons
        buttonPanel.add(fundTitleButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(fundBudgetButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(catagoriesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(deadlineButton);
        fundListPanel.add(buttonPanel);
        fundTitleButton.addActionListener(e -> {
            System.out.println("Title button clicked");
            clickCounts[0]++;
            clickCounts[1] = 0;
            clickCounts[2] = 0;
            clickCounts[3] = 0;
            clickCounts[4] = 0;

            JButton newButton = UIButtons.sortingButtons("title", clickCounts);
            fundTitleButton.setText(newButton.getText());
            fundTitleButton.setIcon(newButton.getIcon());

            fundBudgetButton.setText("Budget");
            fundBudgetButton.setIcon(null);
            deadlineButton.setText("Deadline");

            deadlineButton.setIcon(null);
            filterByTag();
            fundListPanel.revalidate();
            fundListPanel.repaint();
        });

        fundBudgetButton.addActionListener(e -> {
            System.out.println("Owner button clicked");
            clickCounts[0] = 0;
            clickCounts[1] = 0;
            clickCounts[2] = 0;
            clickCounts[3] = 0;
            clickCounts[4]++;

            JButton newButton = UIButtons.sortingButtons("budget", clickCounts);
            fundBudgetButton.setText(newButton.getText());
            fundBudgetButton.setIcon(newButton.getIcon());

            fundTitleButton.setText("Titel");
            fundTitleButton.setIcon(null);
            deadlineButton.setText("Deadline");
            deadlineButton.setIcon(null);

            filterByTag();
            projectListPanel.revalidate();
            projectListPanel.repaint();
        });

        deadlineButton.addActionListener(e -> {
            System.out.println("deadline button clicked");
            clickCounts[0] = 0;
            clickCounts[1] = 0;
            clickCounts[2]++;
            clickCounts[3] = 0;
            clickCounts[4] = 0;

            JButton newButton = UIButtons.sortingButtons("deadline", clickCounts);
            deadlineButton.setText(newButton.getText());
            deadlineButton.setIcon(newButton.getIcon());

            fundTitleButton.setText("Titel");
            fundTitleButton.setIcon(null);
            fundBudgetButton.setText("Budget");
            fundBudgetButton.setIcon(null);
            filterByTag();

            fundListPanel.revalidate();
            fundListPanel.repaint();
        });

        for (fundClass fund : smallerList) {
            JLabel fundLabel;

            // Determine the deadline display
            String deadlineDisplay;
            if (fund.getDeadlines().contains(LocalDateTime.of(3000, 1, 1, 0, 0))) {
                deadlineDisplay = "[Løbende Ansøgningsfrist]";
            } else {
                if (fundQSort.allDeadlinesPassed(fund)) {
                    deadlineDisplay = "Alle frister Overskredet";
                } else {
                    for (LocalDateTime dL : fund.getDeadlines()) {
                        if (dL.isBefore(LocalDateTime.now())) {
                            fund.getDeadlines().remove(dL);
                        }
                    }
                    deadlineDisplay = fund.getDeadlines().get(0).toString().replace("T", " ").replace("[", "")
                            .replace("]", "");
                }
            }

            if (fund.getTitle().length() < 20) {
                if (fund.getCategories().toString().length() < 20) {
                    fundLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s",
                                    fund.getTitle(),
                                    formatNumber(fund.getBudgetMin() + "") + " - "
                                            + formatNumber(fund.getBudgetMax() + ""),
                                    fund.getCategories().toString(),
                                    deadlineDisplay));
                } else {
                    fundLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s",
                                    fund.getTitle(),
                                    formatNumber(fund.getBudgetMin() + "") + " - "
                                            + formatNumber(fund.getBudgetMax() + ""),
                                    fund.getCategories().toString().substring(0, 17) + "...",
                                    deadlineDisplay));
                }
            } else {
                if (fund.getCategories().toString().length() < 20) {
                    fundLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s",
                                    fund.getTitle().substring(0, 17) + "...",
                                    formatNumber(fund.getBudgetMin() + "") + " - "
                                            + formatNumber(fund.getBudgetMax() + ""),
                                    fund.getCategories().toString(),
                                    deadlineDisplay));
                } else {
                    fundLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s",
                                    fund.getTitle().substring(0, 17) + "...",
                                    formatNumber(fund.getBudgetMin() + "") + " - "
                                            + formatNumber(fund.getBudgetMax() + ""),
                                    fund.getCategories().toString().substring(0, 17),
                                    deadlineDisplay));
                }
            }

            JButton fundButton = UIButtons.createNewListButton(fundLabel, false);
            fundButton.addActionListener(e -> {
                showFundDetails(fund);
                resetAllListButtons(2); // Reset all buttons to default color
                fundButton.setBackground(new Color(150, 150, 150));
            });
            fundListPanel.add(fundButton);
        }
        fundListPanel.revalidate();
        fundListPanel.repaint();
    }

    // Method to update the fund list display SORT HERE
    private void updateFundList() {
        fundListPanel.removeAll(); // Clear existing funds
        // JLabel infoLabel = new JLabel(
        // String.format("%-30s %-30s %-30s %-30s",
        // "Title",
        // "Budget",
        // "Kategori(er)",
        // "Ansøgningsfrist"));
        // fundListPanel.add(infoLabel);
        fundListPanel.removeAll();
        JButton fundTitleButton = UIButtons.sortingButtons("title", clickCounts);
        JButton fundBudgetButton = UIButtons.sortingButtons("budget", clickCounts);
        JButton catagoriesButton = UIButtons.createListCatagoryButton("Kategorier");
        JButton deadlineButton = UIButtons.sortingButtons("deadline", clickCounts);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonPanel.add(Box.createRigidArea(new Dimension(30, 0))); // Add space between buttons
        buttonPanel.add(fundTitleButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(fundBudgetButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(catagoriesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(60, 0))); // Add space between buttons
        buttonPanel.add(deadlineButton);
        fundListPanel.add(buttonPanel);

        fundTitleButton.addActionListener(e -> {
            System.out.println("Title button clicked");
            clickCounts[0]++;
            clickCounts[1] = 0;
            clickCounts[2] = 0;
            clickCounts[3] = 0;
            clickCounts[4] = 0;

            JButton newButton = UIButtons.sortingButtons("title", clickCounts);
            fundTitleButton.setText(newButton.getText());
            fundTitleButton.setIcon(newButton.getIcon());

            fundBudgetButton.setText("Budget");
            fundBudgetButton.setIcon(null);
            deadlineButton.setText("Deadline");

            deadlineButton.setIcon(null);
            filterByTag();
            fundListPanel.revalidate();
            fundListPanel.repaint();
        });

        fundBudgetButton.addActionListener(e -> {
            System.out.println("Owner button clicked");
            clickCounts[0] = 0;
            clickCounts[1] = 0;
            clickCounts[2] = 0;
            clickCounts[3] = 0;
            clickCounts[4]++;

            JButton newButton = UIButtons.sortingButtons("budget", clickCounts);
            fundBudgetButton.setText(newButton.getText());
            fundBudgetButton.setIcon(newButton.getIcon());

            fundTitleButton.setText("Titel");
            fundTitleButton.setIcon(null);
            deadlineButton.setText("Deadline");
            deadlineButton.setIcon(null);

            filterByTag();
            projectListPanel.revalidate();
            projectListPanel.repaint();
        });

        deadlineButton.addActionListener(e -> {
            System.out.println("deadline button clicked");
            clickCounts[0] = 0;
            clickCounts[1] = 0;
            clickCounts[2]++;
            clickCounts[3] = 0;
            clickCounts[4] = 0;

            JButton newButton = UIButtons.sortingButtons("deadline", clickCounts);
            deadlineButton.setText(newButton.getText());
            deadlineButton.setIcon(newButton.getIcon());

            fundTitleButton.setText("Titel");
            fundTitleButton.setIcon(null);
            fundBudgetButton.setText("Budget");
            fundBudgetButton.setIcon(null);
            filterByTag();

            fundListPanel.revalidate();
            fundListPanel.repaint();
        });
        System.out.println(">>>>>>>>> " + (clickCounts[2] % 3));
        // if (clickCounts[2] % 3 == 1) {
        // sortedFundList = sorter.sortFundByClosestDeadline(sortedFundList);

        // } else if (clickCounts[2] % 3 == 2) {
        // sortedFundList = sorter.sortByFurthestDateFund(sortedFundList);

        // } else {
        // sortedFundList = main.fundList;
        // }

        for (fundClass fund : sortedFundList) {
            JLabel fundLabel;

            // Determine the deadline display
            String deadlineDisplay;
            if (fund.getDeadlines().contains(LocalDateTime.of(3000, 1, 1, 0, 0))) {
                deadlineDisplay = "[Løbende Ansøgningsfrist]";
            } else {
                if (fundQSort.allDeadlinesPassed(fund)) {
                    deadlineDisplay = "Alle frister Overskredet";
                } else {
                    for (LocalDateTime dL : fund.getDeadlines()) {
                        if (dL.isBefore(LocalDateTime.now())) {
                            fund.getDeadlines().remove(dL);
                        }
                    }
                    deadlineDisplay = fund.getDeadlines().get(0).toString().replace("T", " ").replace("[", "")
                            .replace("]", "");
                }
            }

            if (fund.getTitle().length() < 20) {
                if (fund.getCategories().toString().length() < 20) {
                    fundLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s",
                                    fund.getTitle(),
                                    formatNumber(fund.getBudgetMin() + "") + " - "
                                            + formatNumber(fund.getBudgetMax() + ""),
                                    fund.getCategories().toString(),
                                    deadlineDisplay));
                } else {
                    fundLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s",
                                    fund.getTitle(),
                                    formatNumber(fund.getBudgetMin() + "") + " - "
                                            + formatNumber(fund.getBudgetMax() + ""),
                                    fund.getCategories().toString().substring(0, 17) + "...",
                                    deadlineDisplay));
                }
            } else {
                if (fund.getCategories().toString().length() < 20) {
                    fundLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s",
                                    fund.getTitle().substring(0, 17) + "...",
                                    formatNumber(fund.getBudgetMin() + "") + " - "
                                            + formatNumber(fund.getBudgetMax() + ""),
                                    fund.getCategories().toString(),
                                    deadlineDisplay));
                } else {
                    fundLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s",
                                    fund.getTitle().substring(0, 17) + "...",
                                    formatNumber(fund.getBudgetMin() + "") + " - "
                                            + formatNumber(fund.getBudgetMax() + ""),
                                    fund.getCategories().toString().substring(0, 17),
                                    deadlineDisplay));
                }
            }

            JButton fundButton = UIButtons.createNewListButton(fundLabel, false);
            fundButton.addActionListener(e -> {
                showFundDetails(fund);
                resetAllListButtons(2); // Reset all buttons to default color
                fundButton.setBackground(new Color(150, 150, 150));
            });
            fundListPanel.add(fundButton);
            fundListPanel.add(Box.createHorizontalGlue());
            fundListPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Add space to the left of the buttons
        }

        fundListPanel.revalidate();
        fundListPanel.repaint();
    }


    private JPanel createRightSidePanel() {
        JPanel rightSidePanel = new JPanel(new CardLayout());
        rightSidePanel.setBackground(new Color(213, 213, 213, 255));
        rightSidePanel.setPreferredSize(new Dimension(500, 100));

        proposalProjectFullPanel = new JPanel();
        proposalProjectFullPanel.setLayout(new BoxLayout(proposalProjectFullPanel, BoxLayout.Y_AXIS));
        JScrollPane proposalScrollPane = new JScrollPane(proposalProjectFullPanel);
        rightSidePanel.add(proposalScrollPane, "proposalProjectsDetails");

        projectFullPanel = new JPanel();
        projectFullPanel.setLayout(new BoxLayout(projectFullPanel, BoxLayout.Y_AXIS));
        JScrollPane projectScrollPane = new JScrollPane(projectFullPanel);
        rightSidePanel.add(projectScrollPane, "ProjectDetails");

        fundFullPanel = new JPanel();
        fundFullPanel.setLayout(new BoxLayout(fundFullPanel, BoxLayout.Y_AXIS));
        JScrollPane fundScrollPane = new JScrollPane(fundFullPanel);
        rightSidePanel.add(fundScrollPane, "FundDetails");

        JPanel archivePanel = new JPanel();
        archivePanel.setLayout(new BoxLayout(archivePanel, BoxLayout.Y_AXIS));
        JScrollPane archiveScrollPane = new JScrollPane(archivePanel);
        rightSidePanel.add(archiveScrollPane, "ArchiveDetails");

        return rightSidePanel;
    }

    private void updateRightSidePanel(String tab) {
        CardLayout layout = (CardLayout) rightSidePanel.getLayout();
        System.out.println(tab);
        switch (tab) {
            case "proposalProjects":
                layout.show(rightSidePanel, "proposalProjectsDetails");
                break;
            case "Projects":
                layout.show(rightSidePanel, "ProjectDetails");
                break;
            case "Funds":
                layout.show(rightSidePanel, "FundDetails");
                break;
            case "Archive":
                layout.show(rightSidePanel, "ArchiveDetails");
                break;
            default:
                layout.show(rightSidePanel, "Default"); // Sikrer standardvisning
                break;
        }
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

    private <T> void displayArchiveList(String cardName, List<T> archiveList) {
        JPanel targetPanel;

        // Determine which panel to update based on card name
        switch (cardName) {
            case "proposalProjectsDetails":
                targetPanel = proposalProjectFullPanel;
                break;
            case "ProjectDetails":
                targetPanel = projectFullPanel;
                break;
            case "FundDetails":
                targetPanel = fundFullPanel;
                break;
            default:
                throw new IllegalArgumentException("Invalid card name");
        }

        // Clear the panel and populate it with clickable items from the archive list
        targetPanel.removeAll();
        for (int i = 0; i < archiveList.size() && archiveList.size() != 0; i++) {
            String displayText;

            // Get the title of each item
            if (archiveList.get(i) instanceof project) {
                displayText = ((project) archiveList.get(i)).getTitle();
            } else if (archiveList.get(i) instanceof proposalProject) {
                displayText = ((proposalProject) archiveList.get(i)).getTitle();
            } else if (archiveList.get(i) instanceof fundClass) {
                displayText = ((fundClass) archiveList.get(i)).getTitle();
            } else {
                displayText = archiveList.get(i).toString();
            }

            // Create a JButton for each item to make it clickable
            JButton itemButton = new JButton(displayText);
            T item = archiveList.get(i);
            // Add an ActionListener to display item details when clicked
            itemButton.addActionListener(e -> displayItemDetails(item));

            targetPanel.add(itemButton);
            targetPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacer between items
        }

        // Refresh the panel and switch to the selected archive view
        targetPanel.revalidate();
        targetPanel.repaint();
        CardLayout cardLayout = (CardLayout) rightSidePanel.getLayout();
        cardLayout.show(rightSidePanel, cardName);
    }

    private void showFundDetailsDialog(fundClass fund, project project) {
        // Create the dialog box
        JDialog fundDialog = new JDialog(frame, fund.getTitle(), true);
        fundDialog.setLayout(new GridLayout(0, 1));

        // Add fund details to the dialog box
        fundDialog.add(new JLabel("Titel: " + fund.getTitle()));
        String description = new String();
        List<String> strings = new ArrayList<String>();
        int index = 0;
        while (index < fund.getDescription().length()) {
            strings.add(fund.getDescription().substring(index,
                    Math.min(index + 60, fund.getDescription().length())));
            index += Math.min(index + 60, fund.getDescription().length());
        }

        for (int i = 0; i < strings.size(); i++) {
            if (i + 1 < strings.size()) {
                if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                        && Character.isLetter(strings.get(i + 1).charAt(0))) {
                    description += strings.get(i).trim() + "-";
                    description += "\n";
                } else if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                        && !Character.isLetter(strings.get(i + 1).charAt(0))) {
                    description += strings.get(i).trim() + strings.get(i + 1).charAt(0);
                    strings.set(i + 1, strings.get(i + 1).substring(1));
                    description += "\n";
                } else {
                    description += strings.get(i).trim();
                    description += "\n";
                }
            } else {
                description += strings.get(i).trim();
                description += "\n";
            }
        }
        description += "\n";
        fundDialog.add(new JLabel("Beskrivelse: "));
        for (String s : description.split("\n")) {
            fundDialog.add(new JLabel(s));
        }

        fundDialog.add(new JLabel("Kategorier: " + fund.getCategories()));
        fundDialog.add(new JLabel("Ansøgningsfrist: " + fund.getDeadlines().toString()));
        for (int i = 0; i < tempContacts.size(); i++) {
            fundDialog.add(new JLabel("Kontaktpersoner: " + tempContacts.get(i).getContactName() + " - "
                    + tempContacts.get(i).getContactPhoneNumber() + " - " + tempContacts.get(i).getContactEmail()));
        }
        fundDialog.add(new JLabel("Budget: " + fund.getBudgetSpan()));
        fundDialog.add(new JLabel("Tidligere projekter: " + fund.getCollaborationHistory()));
        fundDialog.add(new JLabel("Hjemmeside: " + fund.getFundWebsite()));
        fundDialog.add(new JLabel("\n"));
        fundDialog.add(new JLabel("Dato tilføjet: " + fund.getDateCreated()));

        // Button to close the dialog
        JButton closeButton = new JButton("Luk");
        closeButton.addActionListener(e -> fundDialog.dispose());
        fundDialog.add(closeButton);

        fundDialog.pack();
        fundDialog.setLocationRelativeTo(projectFullPanel); // Center dialog relative to main panel
        fundDialog.setVisible(true);
    }

    public static void write() {
        CsvStringWriter.writeStringCSV("data/categories.csv", main.categories);
        ProposalsCsvWriter.writeProposalCsv("data/proposals.csv", main.proposalList);
    }

}

