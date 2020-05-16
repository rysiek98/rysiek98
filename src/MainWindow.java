import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;


/** OKNO ZAWIERAJĄCE GŁÓWNE MENU */
public  class MainWindow extends  JFrame {

    private final JFrame MainWindow;

    public MainWindow(){
        if(Client.getMode()) {
            JOptionPane.showMessageDialog(this, "Gra w trybie online!");
        }else {
            JOptionPane.showMessageDialog(this, "Gra w trybie offline!");
        }
        JPanel header = createHeader();
        JPanel content = createContent();
        JPanel footer = createFooter();

        pack();
        setSize(new Dimension(800, 700));

        try {

            BufferedImage myImage;
            if (Client.getMode()) {
                myImage = Client.getImage("menu_background.jpg");
            }else {
                myImage = ImageIO.read(new File("img/menu_background.jpg"));
            }
            this.setContentPane(new BackgroundImage(myImage));
        }catch (Exception e) {
            System.out.println("Blad obrazka!");
        }

        BorderLayout borderLayout = new BorderLayout(10,20);
        setLayout(borderLayout);
        add(header, BorderLayout.NORTH);
        getContentPane().add(content, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        MainWindow = this;
    }


    //TWORZY ODSTEP I WYSWIETLA NAZWE GRY
    private JPanel createHeader() {
        Color myColor = new Color(176, 196, 222);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JTextField TextField= new JTextField("Lunar Lander");
        Font font = new Font("Arial", Font.BOLD + Font.ITALIC,30);
        TextField.setForeground(myColor);
        TextField.setFont(font);
        TextField.setEditable(false);
        TextField.setBorder(BorderFactory.createEmptyBorder());
        TextField.setOpaque(false);
        panel.add(TextField);
        panel.setOpaque(false);
        return panel;
    }

    //ZAWIERA WSZYSTKIE PRZYCISKI
    private JPanel createContent() {
        JPanel panel = new JPanel();
        JButton playButton = new JButton("Graj");
        JButton rulesButton = new JButton("Zasady");
        JButton resultTableButton = new JButton("Tabela wyników");
        JButton authorsButton = new JButton("Autorzy");
        JButton exitButton = new JButton("Wyjdź");

        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(playButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        rulesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(rulesButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        resultTableButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(resultTableButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        authorsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(authorsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(exitButton);
        panel.setOpaque(false);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartMenu(centerFrame(), MainWindow);
            }
        });

        exitButton.addActionListener(new ActionListener() {    // Application Exit Action Listener
            @Override
            public void actionPerformed(ActionEvent e) {       //Exit method
                Exit(panel);
            }
        });

        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Rules(centerFrame());
            }
        });

        resultTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Results();
            }
        });

        authorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Authors(centerFrame());
            }
        });

        return panel;
    }

    //STOPKA
    private JPanel createFooter() {
        JPanel panel = new JPanel();
        Color myColor = new Color(176, 196, 222);
        panel.setLayout(new FlowLayout());
        JTextField TextField = new JTextField("by Michał Ryszka & Wojciech Kowalski 2020");
        Font font = new Font("Arial", Font.BOLD+Font.ITALIC,8);
        TextField.setFont(font);
        TextField.setForeground(myColor);
        TextField.setEditable(false);
        TextField.setBorder(BorderFactory.createEmptyBorder());
        TextField.setOpaque(false);
        panel.add(TextField);
        panel.setOpaque(false);
        return panel;
    }

    //OKNO DIALOGOWE PODCZAS OPUSZCZNIA APLIKACJI
    private void Exit(JPanel panel){
        if (JOptionPane.showConfirmDialog(panel,"Potwierdź jeśli chcesz wyjść.","Lunar Lander",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            System.exit(0);
    }

    //UMOŻLIWIA WYŚRODKOWANIE INNYCH OKIEN
    private int[]  centerFrame() {
        int[] Loc = new int[4];
        Loc[0] = this.getLocationOnScreen().x;
        Loc[1] = this.getLocationOnScreen().y;
        Loc[2] = this.getSize().width;
        Loc[3] = this.getSize().height;
        return Loc;
    }

    //WYŚWIETLA OKNO Z WYNIKAMI
    private void Results() {
        try {
            new ResultsTable(centerFrame());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
