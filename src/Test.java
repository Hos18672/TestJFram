

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;


public class Test {
    JPanel AdjPanel,DistPanel,WegPanel,InfoPanel,mainPanel;
    Graph g = new Graph();
    Integer size =g.readCSVFile().length;
    JButton[][] btn = new JButton[size][size];
    private JMenuBar menuBar = new JMenuBar();

    public static void main(String[] args) {
        Graph g = new Graph();
        g.initialize();
        g.ermittle();
        new Test();
    }

    public Test() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                TestPane t = new TestPane();
                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setJMenuBar(menuBar); // Add the menu bar to the window
                JMenu fileMenu = new JMenu("File"); // Create File menu
                JMenu elementMenu = new JMenu("Elements"); // Create Elements menu
                menuBar.add(fileMenu); // Add the file menu
                menuBar.add(elementMenu); // Add the element menu
                mainPanel = new JPanel(new GridLayout(2,4));
                mainPanel.add(AdjPanel);
                mainPanel.add(DistPanel);
                mainPanel.add(WegPanel);
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
            g.ermittle();
            AdjPanel = new JPanel();
            DistPanel = new JPanel();
            WegPanel = new JPanel();
            AdjPanel.setLayout(new GridLayout(size, size));
            DistPanel.setLayout(new GridLayout(size, size));
            WegPanel.setLayout(new GridLayout(size, size));

            AdjPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.WHITE));
            DistPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.WHITE));
            WegPanel.setBorder(new MatteBorder(2, 2, 2, 2, Color.WHITE));


            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {

                    if(row == col) {

                        //Adjatrix
                        String m = g.getAdjacencyMatrix2()[row][col].toString();
                        btn[row][col] = new JButton(m);
                        AdjPanel.add(btn[row][col]);
                        btn[row][col].setBackground(Color.BLUE);
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
                    else
                    {
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

}

