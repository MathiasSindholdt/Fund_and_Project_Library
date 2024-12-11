import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
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
import javax.swing.JToggleButton;
import javax.swing.SpinnerDateModel;

public abstract class AbstractFrame extends JFrame implements ActionListener{
    
    public static JFrame frame;
    public JPanel cardPanel;
    public CardLayout cardLayout;
    public static JPanel panel2 = new JPanel();
    public PDFGenerator PDFGen = new PDFGenerator();

    // Buttons
    public JButton createProbButton;
    public JButton changeProbButton;
    public JButton createProjectButton;
    public JButton changeProjectButton;
    public JButton createFundButton;
    public JButton changeFundButton;
    public JButton createCategoriesButton;
    public JButton changeCategoriesButton;


    public JButton menuButton;
    public JButton logoutButton;
    public JButton backButton;
    public JButton xButton;
    public JButton xButtonHover;
    public JButton projectPropButton;
    public JButton projectButton;
    public JButton fundsButton;
    public JButton archiveButton;
    public JButton deleteButton;

    // List to store project proposals
    public JPanel proposalProjectListPanel;
    public JPanel proposalProjectFullPanel;

    public JPanel projectListPanel;
    public JPanel projectFullPanel;

    public JPanel fundListPanel;
    public JPanel fundFullPanel;
    // tag button
    public JPanel tagButtonPanel;

    public JPanel rightSidePanel;

    public boolean isInvalidLenght;
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
    public boolean noUrl;
    public static fundContactClass tempContact;
    public ArrayList<fundContactClass> tempContacts = new ArrayList<>();
    public ArrayList<fundContactClass> removeContactArray = new ArrayList<>();
    public static ArrayList<fundContactClass> contacts = new ArrayList<>();
    public ArrayList<proposalProject> sortedProposalList = main.proposalList;
    public ArrayList<project> sortedProjectList = main.projectList;
    public ArrayList<fundClass> sortedFundList = main.fundList;
    public String userType = "Guest";
    
    public List<JToggleButton> tagButton;
    public ArrayList<String> selectedTags = new ArrayList<>();
    
    globalListSorting sorter = new globalListSorting();
    UIButtons UIButtons = new UIButtons();
    EditProjectButton editProjectButton = new EditProjectButton(this, main.projectList);
    EditFundButton editFundButton = new EditFundButton(this, main.fundList);
    EditProjectProposal editProjectProposal = new EditProjectProposal(this, main.proposalList);

    int[] clickCounts = { 0, 0, 0, 0, 0 }; // Initialize the clickCounts array
    public static String clickableURL;
    // Constructor to set up the GUI

    public void initializeFrame() {
        frame = new JFrame(userType);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLayout(new BorderLayout(10, 10));
    }

    public JPanel createTopPanel() {
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

    public void setupToggleBehavior(JButton button) {
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

    public JPanel createSidePanel() {
        // Create the main panel
        panel2.setBackground(new Color(213, 213, 213, 255));
        panel2.setPreferredSize(new Dimension(200, 100));
        // panel2.setLayout(new BorderLayout()); // Use BorderLayout for better
        // organization

        return panel2;
    }


    public void filterByTag() {
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

    public int updateBeforeFilter() {
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

    public void createTagButton(String newTag) {
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

    public void resetButtonStates() {
        projectPropButton.setBackground(new Color(213, 213, 213, 255));
        projectButton.setBackground(new Color(213, 213, 213, 255));
        fundsButton.setBackground(new Color(213, 213, 213, 255));
        archiveButton.setBackground(new Color(213, 213, 213, 255));
    }

    public JPanel createproposalProjectView() {
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

    public JPanel createArchiveView() {
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

    // Creates the right-side panel with different views for each archive type

    // Method to update the right-side panel based on selected archive type

    public <T> void displayItemDetails(T item) {
        // Create a new panel or dialog to show item details
        JDialog detailsDialog = new JDialog(frame, "Item Details", true);
        JPanel detailsPanel = new JPanel();
        detailsDialog.add(detailsPanel);
        detailsDialog.setSize(400, 300);
        detailsDialog.setLocationRelativeTo(frame);
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        // Display details based on the type of item
        if (item instanceof project) {
            project proj = (project) item;
            detailsDialog.setTitle(proj.getTitle());
            detailsPanel.add(new JLabel("Titel: " + proj.getTitle()));
            detailsPanel.add(new JLabel("Beskrivelse: " + proj.getDescription()));
            detailsPanel.add(new JLabel("Kategorier: " + proj.getCategories()));
            detailsPanel.add(new JLabel("Ide/Formål: " + proj.getProjectPurpose()));
            detailsPanel.add(new JLabel("Ejer: " + proj.getProjectOwner()));
            detailsPanel.add(new JLabel("Målgruppe: " + proj.getProjectTargetAudience()));
            detailsPanel.add(new JLabel("Aktiviter: " + proj.getProjectActivities()));
            detailsPanel.add(new JLabel("\n"));

            detailsPanel.add(new JLabel("Dato fra: " + proj.getProjectTimeSpanFrom().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            detailsPanel.add(new JLabel("Dato til: " + proj.getProjectTimeSpanTo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            detailsPanel.add(new JLabel("\n"));
            

            detailsPanel.add(new JLabel("Dato lavet: " + proj.getDateCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        } else if (item instanceof proposalProject) {
            proposalProject proposal = (proposalProject) item;
            detailsDialog.setTitle(proposal.getTitle());
            detailsPanel.add(new JLabel("Titel: " + proposal.getTitle()));
            detailsPanel.add(new JLabel("Beskrivelse: " + proposal.getDescription()));
            detailsPanel.add(new JLabel("Kategorier: " + proposal.getCategories()));
            detailsPanel.add(new JLabel("Ide/Formål: " + proposal.getProjectPurpose()));
            detailsPanel.add(new JLabel("Ejer: " + proposal.getProjectOwner()));
            detailsPanel.add(new JLabel("Målgruppe: " + proposal.getProjectTargetAudience()));
            detailsPanel.add(new JLabel("Aktiviter: " + proposal.getProjectActivities()));
            detailsPanel.add(new JLabel("\n"));

            detailsPanel.add(new JLabel("Dato fra: " + proposal.getProjectTimeSpanFrom().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            detailsPanel.add(new JLabel("Dato til: " + proposal.getProjectTimeSpanTo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            detailsPanel.add(new JLabel("\n"));

            detailsPanel.add(new JLabel("Dato lavet: " + proposal.getDateCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        } else if (item instanceof fundClass) {
            fundClass fund = (fundClass) item;
            detailsDialog.setTitle(fund.getTitle());
            detailsPanel.add(new JLabel("Titel: " + fund.getTitle()));
            detailsPanel.add(new JLabel("Beskrivelse: " + fund.getDescription()));
            detailsPanel.add(new JLabel("Kategorier: " + fund.getCategories()));
            if (!fund.getDeadlines().isEmpty() && fund.getDeadlines().get(0).equals(LocalDateTime.of(3000, 1, 1, 0, 0))) {
                detailsPanel.add(new JLabel("Ansøgningsfrist(er): Løbende"));
            } else {
                detailsPanel.add(new JLabel("Ansøgningsfrist(er): " + fund.getDeadlines()));
            }
            detailsPanel.add(new JLabel("Kontakter: " + fund.getContacts()));
            detailsPanel.add(new JLabel("Budget span: " + fund.getBudgetSpan()));
            detailsPanel.add(new JLabel("Tidligere samarbejde: " + fund.getCollaborationHistory()));
            detailsPanel.add(new JLabel("Hjemmeside: " + fund.getFundWebsite()));
            detailsPanel.add(new JLabel("\n"));
            detailsPanel.add(new JLabel("Dato lavet: " + fund.getDateCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        } else {
            detailsPanel.add(new JLabel("Details: " + item.toString()));
        }

        JButton deleteButton = new JButton("Slet");
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Er du sikker på, at du vil slette dette element?",
                    "Bekræft Sletning",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (item instanceof project) {
                    main.archiveProjectList.remove((project) item);
                    displayArchiveList("ProjectDetails", main.archiveProjectList);
                    ProjectCsvWriter.writeProjectCsv("data/projectsArchive.csv", main.archiveProjectList);
                } else if (item instanceof proposalProject) {
                    main.deniedProposalList.remove((proposalProject) item);
                    displayArchiveList("proposalProjectsDetails", main.deniedProposalList);
                    ProposalsCsvWriter.writeProposalCsv("data/deniedProposals.csv", main.deniedProposalList);
                } else if (item instanceof fundClass) {
                    main.archiveFundList.remove((fundClass) item);
                    displayArchiveList("FundDetails", main.archiveFundList);
                    FundCsvWriter.writeCsv("data/fundsArchive.csv", main.archiveFundList);
                }
                System.out.println("Deleting item: " + item);
                System.out.println(main.deniedProposalList);

                JOptionPane.showMessageDialog(
                        null,
                        "Elementet er blevet slettet.",
                        "Sletning fuldført",
                        JOptionPane.INFORMATION_MESSAGE);
                        
                detailsDialog.dispose();
            }
        });

        JButton returnButton = new JButton("Send tilbage");

        returnButton.addActionListener(e -> {
            if (item instanceof project) {
                main.archiveProjectList.remove((project) item);
                main.projectList.add((project) item);
                writeAll();
                displayArchiveList("ProjectDetails", main.archiveProjectList);
                updateProjectList();
            } else if (item instanceof proposalProject) {
                main.deniedProposalList.remove((proposalProject) item);
                main.proposalList.add((proposalProject) item);
                writeAll();
                displayArchiveList("proposalProjectsDetails", main.deniedProposalList);
                updateProposalProjectList();  
            } else if (item instanceof fundClass) {
                main.archiveFundList.remove((fundClass) item);
                main.fundList.add((fundClass) item);
                writeAll();
                displayArchiveList("FundDetails", main.archiveFundList);
                updateFundList();
            }
            JOptionPane.showMessageDialog(
                    null,
                    "Elementet er blevet sendt tilbage.",
                    "Sendt tilbage",
                    JOptionPane.INFORMATION_MESSAGE);
            detailsDialog.dispose();
        });

        detailsPanel.add(Box.createVerticalStrut(10)); // Add spacing before the button
        detailsPanel.add(deleteButton);
        detailsPanel.add(Box.createVerticalStrut(10)); // Add spacing before the button
        detailsPanel.add(returnButton);

        detailsDialog.setVisible(true);
    }

    //This might create issues
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

    public void openproposalProjectDialog() {

        JDialog dialog = new JDialog(frame, "Opret Projektforslag", true);
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
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

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

            if (selectedCatagories.isEmpty()) {
                JPanel noCategories = UserFrameErrorHandling.displayNoCategory("projektForslag");
                if (noCategories != null) {
                    dialog.add(noCategories);
                }else{
                    return;
                }
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
            writeAll();
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

    public void updateProposalProjectList(ArrayList<proposalProject> smallerList) {
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
            clickCounts[4] = 0;



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

    public void resetAllListButtons(int List){
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

    public void updateProposalProjectList() {
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
            System.out.println("date button clicked");
            clickCounts[0] = 0;
            clickCounts[1] = 0;
            clickCounts[2] = 0;
            clickCounts[3]++;
            clickCounts[4] = 0;

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
                                proposal.getDateCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                categoriesDisplay));
            } else {
                proposalLabel = new JLabel(
                        String.format("%-30s %-30s %-30s %-30s",
                                proposal.getTitle().substring(0, 17) + "...",
                                proposal.getProjectOwner(),
                                proposal.getDateCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
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

    public void updateProjectList() { // SORT HERE
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
            System.out.println("date button clicked");
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
            if(project.getSensitive() && userType == "guest"){
                System.out.println("Sensitive project dected for proj" + project.getTitle());
                continue;
            }
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
                                    project.getDateCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                    "Ingen Beviling",
                                    categoriesDisplay));
                } else {
                    projectLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s %-30s",
                                    project.getTitle().substring(0, 17) + "...",
                                    project.getProjectOwner(),
                                    project.getDateCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
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
                                    project.getDateCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                    deadline,
                                    categoriesDisplay));
                } else {
                    projectLabel = new JLabel(
                            String.format("%-30s %-30s %-30s %-30s %-30s",
                                    project.getTitle().substring(0, 17) + "...",
                                    project.getProjectOwner(),
                                    project.getDateCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
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

    public void updateProjectList(ArrayList<project> smallerList) { // SORT HERE
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
        System.out.println("--------------------" + userType + "2");

        for (project project : smallerList) {
            if(project.getSensitive() && userType == "guest"){
                System.out.println("Sensitive project detected");
                continue;
            }
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

    public void showProjectProbDetails(proposalProject proposal) {
        proposalProjectFullPanel.removeAll();

        // Display project proposal details
        proposalProjectFullPanel.add(new JLabel("Titel: " + proposal.getTitle()));
        proposalProjectFullPanel.add(new JLabel("\n"));
        proposalProjectFullPanel.add(new JLabel("Ejer: " + proposal.getProjectOwner()));
        proposalProjectFullPanel.add(new JLabel("\n"));

        proposalProjectFullPanel.add(new JLabel("Idé: " + proposal.getProjectPurpose()));
        proposalProjectFullPanel.add(new JLabel("\n"));
        if(proposal.getDescription().length() > 560){
            JButton showFullDescButton = new JButton("Vis fuld beskrivelse");
            proposalProjectFullPanel.add(new JLabel("Beskrivelse: "));
            insertWrappedText(proposal.getDescription(), proposalProjectFullPanel);
            proposalProjectFullPanel.add(showFullDescButton);
            proposalProjectFullPanel.add(new JLabel("\n"));    
            
            showFullDescButton.addActionListener(e->{
                showFullDesc(proposal);
            });
        }else{
            proposalProjectFullPanel.add(new JLabel("Beskrivelse: "));
            insertWrappedText(proposal.getDescription(), proposalProjectFullPanel);
            proposalProjectFullPanel.add(new JLabel("\n"));
        }
        

        proposalProjectFullPanel.add(new JLabel("Målgruppe: " + proposal.getProjectTargetAudience()));
        proposalProjectFullPanel.add(new JLabel("\n"));

        proposalProjectFullPanel.add(new JLabel("Budget: " + proposal.getProjectBudget()));
        proposalProjectFullPanel.add(new JLabel("\n"));


        // Display date range
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        proposalProjectFullPanel.add(new JLabel("Fra Dato: " + proposal.getProjectTimeSpanFrom().toLocalDate().format(formatter)));
        proposalProjectFullPanel.add(new JLabel("Til Dato: " + proposal.getProjectTimeSpanTo().toLocalDate().format(formatter)));
        proposalProjectFullPanel.add(new JLabel("\n"));

        // Display project activities
        proposalProjectFullPanel.add(new JLabel("Aktiviteter: " + proposal.getProjectActivities()));
        proposalProjectFullPanel.add(new JLabel("\n"));


        // Display categories as a concatenated string
        proposalProjectFullPanel.add(new JLabel("Kategorier: "));
        StringBuilder categoriesBuilder = new StringBuilder();
        for (String category : proposal.getCategories()) {
            categoriesBuilder.append(category);
            categoriesBuilder.append(", ");
        }
        JLabel categoriesLabel = new JLabel("<html>" + categoriesBuilder.toString().replaceAll(", ", "<br>") + "</html>");
        proposalProjectFullPanel.add(categoriesLabel);
       
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

        onlyOneNeededBox.addActionListener(e -> {
            updateMatchingFunds.run();
        });

        updateMatchingFunds.run();
	
        if (userType == "user"){

            JButton approveButton = new JButton("Godkend Projektforslag");
            approveButton.setPreferredSize(new Dimension(180, 20));
            approveButton.setMaximumSize(new Dimension(180,28));
            approveButton.setMinimumSize(new Dimension(180,28));
            approveButton.addActionListener(event -> {
            JOptionPane.showMessageDialog(null, "Projektforslaget er blevet godkendt og ligger nu i projekt listen");
            approveProposal(proposal); // Approve the proposal and convert it to a project
            proposalProjectFullPanel.getParent().getParent().remove(proposalProjectFullPanel); // Close details
            updateProposalProjectList(); // Refresh proposal list
            writeAll();
            System.out.println("Proposal added to list and UI updated");
            proposalProjectFullPanel.removeAll();
            proposalProjectFullPanel.repaint();
            proposalProjectFullPanel.revalidate();
        });

        // Add buttons to the panel

        JButton archiveButton = new JButton("Afvis Projektforslag");
        archiveButton.setPreferredSize(new Dimension(180, 20));
        archiveButton.setMaximumSize(new Dimension(180,28));
        archiveButton.setMinimumSize(new Dimension(180,28));

        archiveButton.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil afvise dette Projektforslag?",
                    "Afvis Projektforslag", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
            // Archive the project
            archive.archiveProposal(proposal);

            // Call update methods after archiving
            updateProposalProjectList();
            writeAll();
            System.out.println("Proposal added to list and UI updated");
            proposalProjectFullPanel.removeAll();
            proposalProjectFullPanel.revalidate();
            proposalProjectFullPanel.repaint();
            }
        });



        JButton editButton = new JButton("Rediger Projektforslag");
        editButton.setPreferredSize(new Dimension(180, 20));
        editButton.setMaximumSize(new Dimension(180,28));
        editButton.setMinimumSize(new Dimension(180,28));
        editButton.addActionListener(e -> {
            editProjectProposal.openEditProjectPropDialog(proposal);
            proposalProjectFullPanel.removeAll();
            proposalProjectFullPanel.revalidate();
            proposalProjectFullPanel.repaint();
            showProjectProbDetails(proposal);
            updateProposalProjectList();
            writeAll();
        });
        proposalProjectFullPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        proposalProjectFullPanel.add(approveButton);
        proposalProjectFullPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        proposalProjectFullPanel.add(archiveButton);
        proposalProjectFullPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        proposalProjectFullPanel.add(editButton);
        proposalProjectFullPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        }

        // Refresh the panel to reflect the changes
        proposalProjectFullPanel.revalidate();
        proposalProjectFullPanel.repaint();
    } 

    public void approveProposal(proposalProject proposal) {
        System.out.println("Approving proposal: " + proposal.getTitle());

        JButton approveButton = new JButton("Godkend");
        approveButton.addActionListener(event -> {
            approveProposal(proposal); // Approve the proposal and convert it to a project
            proposalProjectFullPanel.getParent().getParent().remove(proposalProjectFullPanel); // Close details
            updateProposalProjectList(); // Refresh proposal list
            writeAll();
            proposalProjectFullPanel.removeAll();
            proposalProjectFullPanel.repaint();
            proposalProjectFullPanel.revalidate();
        });

        // Check if proposalProjects list is initialized
        if (main.proposalList == null) {
            System.out.println("Error: proposalProjects list is not initialized.");
            return;
        }

        // Remove the proposal from the proposalProjects list
        if (main.proposalList.remove(proposal)) {
            System.out.println("Proposal removed: " + proposal.getTitle());
        } else {
            System.out.println("Proposal not found in proposalProjects list: " + proposal.getTitle());
        }

        // Create a new project instance using proposal details
        project project = new project();

        // Set project details based on proposal details
        try {
            project.setTitle(proposal.getTitle());
            project.setProjectPurpose(proposal.getProjectPurpose());
            project.setDescription(proposal.getDescription());
            project.setProjectOwner(proposal.getProjectOwner());
            project.setProjectTargetAudience(proposal.getProjectTargetAudience());
            project.setProjectBudget(proposal.getProjectBudget());
            project.setTimeSpan(proposal.getProjectTimeSpanFrom(), proposal.getProjectTimeSpanTo());
            project.setProjectActivities(proposal.getProjectActivities());

            // Set categories from the proposal, ensuring the project has the same
            // categories
            for (String category : proposal.getCategories()) {
                project.setCategories(category); // Assuming setCategories handles adding categories to the project
            }

            // Add the new project to the projects list if initialized
            if (main.projectList != null) {
                main.projectList.add(project);
                System.out.println("New project added: " + project.getTitle());
            } else {
                System.out.println("Error: projects list is not initialized.");
            }

        } catch (NullPointerException e) {
            System.out.println("Error: One of the proposal properties is null - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Unexpected issue while approving proposal - " + e.getMessage());
        }

        // Update the UI to reflect the new project list
        updateProjectList();
        writeAll();
    }

    public void insertWrappedText(String text, JPanel panel) {
        StringBuilder newText = new StringBuilder();
        List<String> strings = new ArrayList<>();
        int index = 0;
        int maxLength = 560; // Maximum length to display
        int currentLength = 0;

        while (index < text.length() && currentLength < maxLength) {
            int endIndex = Math.min(index + 70, text.length());
            if (endIndex < text.length() && !Character.isWhitespace(text.charAt(endIndex))) {
                while (endIndex > index && !Character.isWhitespace(text.charAt(endIndex))) {
                    endIndex--;
                }
            }
            String substring = text.substring(index, endIndex).trim();
            if (currentLength + substring.length() > maxLength) {
                substring = substring.substring(0, maxLength - currentLength).trim();
            }
            strings.add(substring);
            currentLength += substring.length();
            index = endIndex;
        }

        for (int i = 0; i < strings.size(); i++) {
            if (i + 1 < strings.size()) {
                if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                        && Character.isLetter(strings.get(i + 1).charAt(0))) {
                    newText.append(strings.get(i).trim()).append("-");
                    newText.append("\n");
                } else if (!Character.isWhitespace(strings.get(i + 1).charAt(0))
                        && !Character.isLetter(strings.get(i + 1).charAt(0))) {
                    newText.append(strings.get(i).trim()).append(strings.get(i + 1).charAt(0));
                    strings.set(i + 1, strings.get(i + 1).substring(1));
                    newText.append("\n");
                } else {
                    newText.append(strings.get(i).trim());
                    newText.append("\n");
                }
            } else {
                newText.append(strings.get(i).trim());
                newText.append("\n");
            }
        }

        for (String s : newText.toString().split("\n")) {
            panel.add(new JLabel(s));
        }
    }

    public void showFullDesc(Object item) {
        JDialog fullDescDialog = new JDialog(frame, "Fuld beskrivelse", true);
        JPanel descJPanel = new JPanel();
        fullDescDialog.setLayout(new GridLayout(0, 1));
        descJPanel.setLayout(new BoxLayout(descJPanel, BoxLayout.Y_AXIS));
        descJPanel.add(new JLabel("Beskrivelse: "));

        JLabel descLabel = new JLabel();
        if (item instanceof project) {
            project proj = (project) item;
            fullDescDialog.setTitle(proj.getTitle() + ": Fuld beskrivelse");
            descLabel.setText("<html>" + proj.getDescription().replaceAll("\n", "<br>") + "</html>");
        } else if (item instanceof proposalProject) {
            proposalProject proposal = (proposalProject) item;
            fullDescDialog.setTitle(proposal.getTitle() + ": Fuld beskrivelse");
            descLabel.setText("<html>" + proposal.getDescription().replaceAll("\n", "<br>") + "</html>");
        } else if (item instanceof fundClass) {
            fundClass fund = (fundClass) item;
            fullDescDialog.setTitle(fund.getTitle() + ": Fuld beskrivelse");
            descLabel.setText("<html>" + fund.getDescription().replaceAll("\n", "<br>") + "</html>");
        }

        descJPanel.add(descLabel);
        fullDescDialog.add(descJPanel);
        fullDescDialog.pack();
        fullDescDialog.setSize(new Dimension(960, 384));
        fullDescDialog.setLocationRelativeTo(frame);
        fullDescDialog.setVisible(true);
        fullDescDialog.revalidate();
        fullDescDialog.repaint();
    }
    //     JDialog fullDescDialog = new JDialog(frame, project.getTitle() +": Fuld beskrivelse", true);
    //     JPanel descJPanel = new JPanel();
    //     fullDescDialog.setLayout(new GridLayout(0, 1));
    //     descJPanel.setLayout(new BoxLayout(descJPanel, BoxLayout.Y_AXIS));
    //     descJPanel.add(new JLabel("Beskrivelse: " ));
    //     JLabel descLabel = new JLabel();
    //     descLabel.setText("<html>" + project.getDescription().replaceAll("\n", "<br>") + "</html>");
    //     descJPanel.add(descLabel);
    //     fullDescDialog.add(descJPanel);
    //     fullDescDialog.pack();
    //     fullDescDialog.setSize(new Dimension(960, 384));
    //     fullDescDialog.setLocationRelativeTo(projectFullPanel);
    //     fullDescDialog.setVisible(true);
    //     fullDescDialog.revalidate();
    //     fullDescDialog.repaint();
    // }

    public void showProjectDetails(project project) {
        projectFullPanel.removeAll();

        // Display project details
        projectFullPanel.add(new JLabel("Titel: " + project.getTitle()));
        projectFullPanel.add(new JLabel("\n"));

        projectFullPanel.add(new JLabel("Ejer: " + project.getProjectOwner()));
        projectFullPanel.add(new JLabel("\n"));

        projectFullPanel.add(new JLabel("Idé: " + project.getProjectPurpose()));
        projectFullPanel.add(new JLabel("\n"));

        if(project.getDescription().length() >= 560){

            JButton showFullDescButton = new JButton("Vis fuld beskrivelse");
            projectFullPanel.add(new JLabel("Beskrivelse: "));
            insertWrappedText(project.getDescription(), projectFullPanel);
            projectFullPanel.add(showFullDescButton);
            projectFullPanel.add(new JLabel("\n"));

            showFullDescButton.addActionListener(e -> {
                showFullDesc(project);
            });
        }else{
            projectFullPanel.add(new JLabel("Beskrivelse: "));
            insertWrappedText(project.getDescription(), projectFullPanel);
            projectFullPanel.add(new JLabel("\n"));
        }

        projectFullPanel.add(new JLabel("Målgruppe: " + project.getProjectTargetAudience()));
        projectFullPanel.add(new JLabel("\n"));

        projectFullPanel.add(new JLabel("Budget: " + project.getProjectBudget()));
        projectFullPanel.add(new JLabel("\n"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        projectFullPanel.add(new JLabel("Fra Dato: " + project.getProjectTimeSpanFrom().toLocalDate().format(formatter)));
        projectFullPanel.add(new JLabel("Til Dato: " + project.getProjectTimeSpanTo().toLocalDate().format(formatter)));
        projectFullPanel.add(new JLabel("\n"));

        projectFullPanel.add(new JLabel("Aktiviteter: " + project.getProjectActivities()));
        projectFullPanel.add(new JLabel("\n"));

        projectFullPanel.add(new JLabel("Kategorier: "));
        StringBuilder categoriesBuilder = new StringBuilder();
        for (String category : project.getCategories()) {
            categoriesBuilder.append(category);
            categoriesBuilder.append(", ");
        }
        JLabel categoriesLabel = new JLabel("<html>" + categoriesBuilder.toString().replaceAll(", ", "<br>") + "</html>");
        projectFullPanel.add(categoriesLabel);
        
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
        onlyOneNeededCheckBox.addActionListener(e -> {
            updateMatchingFunds.run();
        });

        // Initial update for matching funds
        updateMatchingFunds.run();

        if (userType == "user"){

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
        // Archive button to archive the project
        // Dimension buttonSize = new Dimension(150, 150);
        JButton archiveButton = new JButton("Arkivér");
        archiveButton.setPreferredSize(new Dimension(130, 20));
        archiveButton.setMaximumSize(new Dimension(130,28));
        archiveButton.setMinimumSize(new Dimension(130,28));
        

        archiveButton.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil arkivere dette projekt?",
                    "Arkivér Projekt", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
            // Archive the project
            archive.archiveProject(project);

            // Update project list and clear details display
            updateProjectList();
            writeAll();
            projectFullPanel.removeAll();
            projectFullPanel.revalidate();
            projectFullPanel.repaint();
            }
        });

        //projectFullPanel.add(archiveButton);

        JButton editButton = new JButton("Rediger Projekt");
        editButton.setPreferredSize(new Dimension(130, 20));
        editButton.setMaximumSize(new Dimension(130,28));
        editButton.setMinimumSize(new Dimension(130,28));
            editButton.addActionListener(e -> {
        editProjectButton.openEditProjectDialog(project);
        projectFullPanel.removeAll();
        projectFullPanel.revalidate();
        projectFullPanel.repaint();
        showProjectDetails(project);
        updateProjectList();
        writeAll();
        });

        JButton exportPDF = new JButton("Expoter til PDF");
        exportPDF.setPreferredSize(new Dimension(130, 20));
        exportPDF.setMaximumSize(new Dimension(130,28));
        exportPDF.setMinimumSize(new Dimension(130,28));
        compareProjectCatsWithFundCats comparer = new compareProjectCatsWithFundCats();
        boolean onlyOneNeeded = onlyOneNeededCheckBox.isSelected();
        exportPDF.addActionListener(e -> {
            PDFGen.GeneratePDF(project, comparer.compareCategoriesWithFund(onlyOneNeeded, main.fundList,
                    project));
            JOptionPane.showMessageDialog(null, "PDF er blevet eksporteret til din downloads mappe");

        });
        projectFullPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        projectFullPanel.add(exportPDF);
        projectFullPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        projectFullPanel.add(archiveButton);
        projectFullPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        projectFullPanel.add(editButton);
        projectFullPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        //projectFullPanel.add(buttonPanel);
        // projectFullPanel.add(exportPDF);
        // projectFullPanel.add(archiveButton);
        // projectFullPanel.add(editButton);
        }
        projectFullPanel.revalidate();
        projectFullPanel.repaint();
    }

    public void showFundDetails(fundClass fund) {
        tempContacts = fund.getContacts();
        fundFullPanel.removeAll();
        fundFullPanel.setLayout(new BoxLayout(fundFullPanel, BoxLayout.Y_AXIS));
    
        // Titel
        fundFullPanel.add(new JLabel("Titel:"));
        fundFullPanel.add(new JLabel(fund.getTitle() + "\n"));
        fundFullPanel.add(new JLabel("\n"));


    
        // Beskrivelse
        if(fund.getDescription().length() > 560){
            JButton showFullDescButton = new JButton("Vis fuld beskrivelse");
            fundFullPanel.add(new JLabel("Beskrivelse: "));
            System.out.println("¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥" + fund.getDescription());
            insertWrappedText(fund.getDescription(), fundFullPanel);
            fundFullPanel.add(showFullDescButton);
            fundFullPanel.add(new JLabel("\n"));
    
            showFullDescButton.addActionListener(e -> {
                showFullDesc(fund);
            });
        }else{
            System.out.println("¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥¥" + fund.getDescription());

            fundFullPanel.add(new JLabel("Beskrivelse:"));
            insertWrappedText(fund.getDescription(), fundFullPanel);
            fundFullPanel.add(new JLabel("\n"));
        }
       

    
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
        // insertWrappedText(String.join(", ", formattedDeadlines), fundFullPanel);
        fundFullPanel.add(new JLabel("\n"));

    
        // Kategori
        fundFullPanel.add(new JLabel("Kategorier:"));
        StringBuilder categoriesBuilder = new StringBuilder();
        for (String category : fund.getCategories()) {
            categoriesBuilder.append(category);
            categoriesBuilder.append(", ");
        }
        JLabel categoriesLabel = new JLabel("<html>" + categoriesBuilder.toString().replaceAll(", ", "<br>") + "</html>");
        fundFullPanel.add(categoriesLabel);
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
                    contact.getContactEmail()
                ));
            }
        } else {
            fundFullPanel.add(new JLabel("Ingen kontaktpersoner tilgængelige."));
        }
        fundFullPanel.add(new JLabel("\n"));

    
        // Hjemmeside
        //fundDialog.add(new JLabel("Hjemmeside: " + fund.getFundWebsite()));
        clickableURL = fund.getFundWebsite();
        if (!clickableURL.startsWith("http://") && !clickableURL.startsWith("https://" ) && !fund.getNoUrl() && !clickableURL.isEmpty()) {
            clickableURL = "https://" + clickableURL;
        }

        String wrappedURL = "<html><a href=\"" + clickableURL + "\">" + clickableURL.replaceAll("(.{70})", "$1<br>") + "</a></html>";
        JLabel websiteLabel = new JLabel(wrappedURL);
        websiteLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        websiteLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            try {
                Desktop.getDesktop().browse(new URI(clickableURL));
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
            }
        });
        fundFullPanel.add(new JLabel("Hjemmeside:"));
        fundFullPanel.add(websiteLabel);
        fundFullPanel.add(new JLabel("\n"));

        if (userType == "user"){
        // Arkivér-knap
        JButton archiveButton = new JButton("Arkivér");
        archiveButton.setPreferredSize(new Dimension(180, 20));
        archiveButton.setMaximumSize(new Dimension(180,28));
        archiveButton.setMinimumSize(new Dimension(180,28));

     //   archiveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        archiveButton.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil arkivere denne fond?",
                    "Arkivér Fond", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
            archive.archiveFund(fund);
            updateFundList();
            writeAll();
            fundFullPanel.removeAll();
            fundFullPanel.revalidate();
            fundFullPanel.repaint();
            }
        });
     //   fundFullPanel.add(Box.createVerticalStrut(10)); // Add some spacing

        JButton changeFundButton = new JButton("Redigér denne fond");
        changeFundButton.setPreferredSize(new Dimension(180, 20));
        changeFundButton.setMaximumSize(new Dimension(180,28));
        changeFundButton.setMinimumSize(new Dimension(180,28));

        changeFundButton.addActionListener(e -> {
            editFundButton.editFundDialog(fund);
            updateFundList();
            writeAll();
            fundFullPanel.removeAll();
            fundFullPanel.revalidate();
            fundFullPanel.repaint();
            showFundDetails(fund);
        });
        fundFullPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        fundFullPanel.add(archiveButton);
        fundFullPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        fundFullPanel.add(changeFundButton);
        fundFullPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        }
        // Refresh panel
        fundFullPanel.revalidate();
        fundFullPanel.repaint();
    }

    public String formatNumber(String number) {

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

    public void updateFundList(ArrayList<fundClass> smallerList) {
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

    public void updateFundList() {
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
            System.out.println("budget button clicked");
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
        System.out.println(">>>>>>>>> " + (clickCounts[3] % 3));
        // if (clickCounts[2] % 3 == 1) {
        //     sortedFundList = sorter.sortFundByClosestDeadline(sortedFundList);

        // } else if (clickCounts[2] % 3 == 2) {
        //     sortedFundList = sorter.sortByFurthestDateFund(sortedFundList);

        // } else {
        //     sortedFundList = main.fundList;
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
                    deadlineDisplay = fund.getDateCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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

    public JPanel createRightSidePanel() {
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

    public void updateRightSidePanel(String tab) {
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

    public <T> void displayArchiveList(String cardName, List<T> archiveList) {
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

    public void showFundDetailsDialog(fundClass fund, project project) {
        // Create the dialog box
        JDialog fundDialog = new JDialog(frame, fund.getTitle(), true);
        fundDialog.setLayout(new GridLayout(0, 1));
        fundDialog.setPreferredSize(new Dimension(960, 384));

        // Add fund details to the dialog box
        fundDialog.add(new JLabel("Titel: " + fund.getTitle()));
        String description = new String();
        List<String> strings = new ArrayList<String>();
        int index = 0;
        while (index < fund.getDescription().length()) {
            int endIndex = Math.min(index + 150, fund.getDescription().length());
            if (endIndex < fund.getDescription().length() && !Character.isWhitespace(fund.getDescription().charAt(endIndex))) {
            while (endIndex > index && !Character.isWhitespace(fund.getDescription().charAt(endIndex))) {
                endIndex--;
            }
            }
            strings.add(fund.getDescription().substring(index, endIndex).trim());
            index = endIndex;
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
        if (!fund.getDeadlines().isEmpty() && fund.getDeadlines().get(0).equals(LocalDateTime.of(3000, 1, 1, 0, 0))) {
            fundDialog.add(new JLabel("Ansøgningsfrist(er): Løbende"));
        } else {
            fundDialog.add(new JLabel("Ansøgningsfrist(er): " + fund.getDeadlines()));
        }    
        for (int i = 0; i < tempContacts.size(); i++) {
            fundDialog.add(new JLabel("Kontaktpersoner: " + tempContacts.get(i).getContactName() + " - "
                    + tempContacts.get(i).getContactPhoneNumber() + " - " + tempContacts.get(i).getContactEmail()));
        }
        fundDialog.add(new JLabel("Budget: " + fund.getBudgetSpan()));
        fundDialog.add(new JLabel("Tidligere projekter: " + fund.getCollaborationHistory()));
        //fundDialog.add(new JLabel("Hjemmeside: " + fund.getFundWebsite()));
        JLabel websiteLabel = new JLabel("<html>Hjemmeside: <a href=\"" + fund.getFundWebsite() + "\">"
                + fund.getFundWebsite() + "</a></html>");
        websiteLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        websiteLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(fund.getFundWebsite()));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        fundDialog.add(websiteLabel);
        fundDialog.add(new JLabel("\n"));
        fundDialog.add(new JLabel("Dato tilføjet: " + fund.getDateCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        if (userType == "user"){

        JButton assignButton = new JButton("Bevilig");
        assignButton.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil bevilige dette projekt?", "Bevilig projekt",
            JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            project.assignFund(fund);
            updateProjectList();
            writeAll();
            showProjectDetails(project);
            fundDialog.dispose();
        }
        });
        // Button to close the dialog
        JButton closeButton = new JButton("Luk");
        closeButton.addActionListener(e -> fundDialog.dispose());
        fundDialog.add(assignButton);
        fundDialog.add(closeButton);
        }

        fundDialog.pack();
        fundDialog.setLocationRelativeTo(projectFullPanel); // Center dialog relative to main panel
        fundDialog.setVisible(true);
    }

    public static void writeAll() {
        FundCsvWriter.writeCsv("data/funds.csv", main.fundList);
        CsvStringWriter.writeStringCSV("data/categories.csv", main.categories);
        CsvStringWriter.writeStringCSV("data/nonSystemProjects.csv", main.userProjectList);
        ProposalsCsvWriter.writeProposalCsv("data/proposals.csv", main.proposalList);
        ProjectCsvWriter.writeProjectCsv("data/projects.csv", main.projectList);
        ProposalsCsvWriter.writeProposalCsv("data/deniedProposals.csv", main.deniedProposalList);
        ProjectCsvWriter.writeProjectCsv("data/projectsArchive.csv", main.archiveProjectList);
        FundCsvWriter.writeCsv("data/fundsArchive.csv", main.archiveFundList);
    }

}
