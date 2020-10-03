import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class Test {
    JPanel AdjPanel,AdjPanelInfo,DistPanel,DistPanelInfo,WegPanel,WegPanelInfo,InfoPanel,InfoPanel1,mainPanel;
    JPanel panel = new JPanel();
    Graph g = new Graph();
    JFrame frame = new JFrame("Testing");
    Integer size =g.readCSVFile().length;
    JButton[][] btn = new JButton[size][size];
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File"); // Create File menu
    JMenu elementMenu = new JMenu("Weiter" ); // Create Elements menu
    public Test() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                panel.setLayout(new GridBagLayout());
                TestPane t = new TestPane();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setJMenuBar(menuBar); // Add the menu bar to the window
                fileMenu.add(g.newMenuItem);
                menuBar.add(fileMenu); // Add the file menu
                menuBar.add(elementMenu); // Add the element menu
                mainPanel = new JPanel(new GridLayout(2,4));
                AdjPanelInfo.add(AdjPanel);
                AdjPanelInfo.setSize(20,10);
                AdjPanelInfo.add(new JLabel("AdjMarix", JLabel.CENTER), BorderLayout.PAGE_START);
                DistPanelInfo.add(DistPanel);
                DistPanelInfo.setSize(20,10);
                DistPanelInfo.add(new JLabel("DistMarix", JLabel.CENTER), BorderLayout.PAGE_START);
                WegPanelInfo.add(WegPanel);
                WegPanelInfo.setSize(20,10);
                WegPanelInfo.add(new JLabel("WegMarix", JLabel.CENTER), BorderLayout.PAGE_START);
                InfoPanel.add(InfoPanel1);
                InfoPanel.setSize(20,10);
                InfoPanel.add(new JLabel("Info", JLabel.CENTER), BorderLayout.PAGE_START);
                mainPanel.add(AdjPanelInfo);
                mainPanel.add(DistPanelInfo);
                mainPanel.add(WegPanelInfo);
                mainPanel.add(InfoPanel);
                frame.add(mainPanel);
                frame.setSize(900, 1000);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    public class TestPane {
        public TestPane() {
            Graph g = new Graph();
            g.initialize();
            g.printGraph();
            g.ermittle();
            g.PrintDistanceMatrix();
            g.exzentrizitaet();
            g.radiusUndDurchmesser();
            g.zentrum();
            g.komponentenanzahl();

            // create Labels for Infos
            JLabel radius = new JLabel("Radius:                     " + g.getRadius());
            Font f = radius.getFont();
            radius.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
            JLabel durchmesser = new JLabel("Durchmesser:         "+ g.getDurchmesser());
            durchmesser.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
            JLabel extrencität = new JLabel("Extrencicität:          "+ g.exzentrizitaet());
            extrencität.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
            JLabel zentrum = new JLabel("Zentrum:                  " + g.getZentrum());
            zentrum.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
            JLabel Komponents = new JLabel("KomponentsNum:   " + g.komponentenanzahl());
            Komponents.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
            JLabel KomponentsOutput = new JLabel("Komponents:           " + g.komponenteAusgeben());
            KomponentsOutput.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
            JLabel Zusammenhengend = new JLabel("Zusammenhengend:  " + g.isZusammenhaengend());
            Zusammenhengend.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
          //  JLabel Bridge = new JLabel("Brücken:           " + g.bruecken());
          //  Bridge.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

            // Create Panels
            AdjPanel  = new JPanel();
            DistPanel = new JPanel();
            WegPanel  = new JPanel();
            AdjPanelInfo = new JPanel(new BorderLayout());
            DistPanelInfo= new JPanel(new BorderLayout());
            WegPanelInfo = new JPanel(new BorderLayout());
            InfoPanel    = new JPanel(new BorderLayout());
            InfoPanel1   = new JPanel(new BorderLayout());
            AdjPanel. setLayout(new GridLayout( size, size));
            DistPanel.setLayout(new GridLayout( size, size));
            WegPanel. setLayout(new GridLayout( size, size));
            AdjPanel. setBorder( new MatteBorder(2, 2, 2, 2, Color.WHITE));
            DistPanel.setBorder(new MatteBorder( 2, 2, 2, 2, Color.WHITE));
            WegPanel. setBorder( new MatteBorder(2, 2, 2, 2, Color.WHITE));
            panel.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridy = 1;
            // Add all Info into Panel Info
            constraints.insets = new Insets(0,0,2,0);
            panel.add(radius, constraints);
            constraints.insets = new Insets(0,0,2,0);
            constraints.gridy++;
            constraints.gridwidth = 10;
            //alignment for each label must be explicitly set
            constraints.anchor = GridBagConstraints.WEST;
            panel.add(durchmesser,constraints);
            constraints.gridy++;
            constraints.anchor = GridBagConstraints.WEST;
            panel.add(extrencität, constraints);
            constraints.gridy++;
            constraints.anchor = GridBagConstraints.WEST;
            panel.add(zentrum,constraints);
            constraints.gridy++;
            panel.add(Komponents,constraints);
            constraints.gridy++;
            panel.add(KomponentsOutput,constraints);
            constraints.gridy++;
            panel.add(Zusammenhengend,constraints);
            constraints.gridy++;
         //   panel.add(Bridge,constraints);
           // constraints.gridy++;

            // Add Panel of Infos to the Infopanel
            InfoPanel.add(panel, BorderLayout.WEST);
            //Create Matrix of buttons
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++){
                    if(row == col) {
                        //Adjatrix
                        String m = g.getAdjacencyMatrix2()[row][col].toString();
                        btn[row][col] = new JButton(m);
                        AdjPanel.add(btn[row][col], BorderLayout.SOUTH);
                        btn[row][col].setBackground(Color.BLUE);
                        AdjPanelInfo.add(AdjPanel, BorderLayout.SOUTH);
                        //DistanceMatrix
                        String m1 = g.getDistanceMatix()[row][col].toString();
                        btn[row][col] = new JButton(m1);
                        DistPanel.add(btn[row][col]);
                        btn[row][col].setBackground(Color.BLUE);
                        //WegMatrix
                        String m2 = g.getWegmatrix()[row][col].toString();
                        btn[row][col] = new JButton(m2);
                        WegPanel.add(btn[row][col]);
                        btn[row][col].setBackground(Color.BLUE);
                    }
                    else {
                        //AdjMatrix
                        String m = g.getAdjacencyMatrix2()[row][col].toString();
                        btn[row][col] = new JButton(m);
                        AdjPanel.add(btn[row][col]);
                        //DistanceMatrix
                        String m1 = g.getDistanceMatix()[row][col].toString();
                        btn[row][col] = new JButton(m1);
                        DistPanel.add(btn[row][col]);
                        //WegMatrix
                        String m2 = g.getWegmatrix()[row][col].toString();
                        btn[row][col] = new JButton(m2);
                        WegPanel.add(btn[row][col]);
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        Graph g = new Graph();
        g.initialize();
        g.ermittle();
        new Test();
        ArrayList<ArrayList<Integer>> adjListArray =  g.convert(g.AdjacencyMatrix2);
        g.printArrayList(adjListArray);
        System.out.print("Articulation: ");
        g.AP();
        System.out.println("\nBirdge: ");
        g.bridge();
        //System.out.println(b.shortestBridge(g.getAdjacencyMatrix2()));

    }


}