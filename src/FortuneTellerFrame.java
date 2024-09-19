import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {

    JPanel mainPanel = null;
    JPanel imgPanel = null;
    JPanel displayPanel = null;
    JPanel buttonPanel = null;
    JLabel fortuneTeller = null;
    JTextArea fortuneTA = null;
    JScrollPane fortuneScroller = null;
    ImageIcon image = null;
    JButton readBtn = null;
    JButton quitBtn = null;

    ArrayList<String> randomFortunes = null;
    Random gen = new Random();
    int index;
    private String lastSelectedWord = "";

    public FortuneTellerFrame(){
        // Get screen dimensions
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // Set frame size to 3/4 of screen width
        int frameWidth = (int)(screenWidth * 0.75);
        int frameHeight = (int)(screenHeight * 0.75);
        setSize(frameWidth, frameHeight);

        // Center frame on screen
        setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);

        mainPanel = new JPanel(); //Here inside of the constructor we instantiate the object from JPanel.
        mainPanel.setLayout(new BorderLayout());// We are setting the Layout to "BorderLayout". This divides the panel into flexible
                                                // regions(North, Center(Default), South, East, West)

        //Method to show imgPanel (Don't forget to add Panels to JFrame)
        createImgPanel();
        mainPanel.add(imgPanel,BorderLayout.NORTH);
        //Method to show displayPanel
        createDisplayPanel();
        mainPanel.add(displayPanel, BorderLayout.CENTER);
        //Method to show buttonPanel
        createButtonPanel();
        mainPanel.add(buttonPanel,BorderLayout.SOUTH);

        add(mainPanel); //Main panel will cover the whole frame but the main panel has 3 panel nested inside of it.
        //setSize(600, 600); because we are override the size.
        setResizable(true);
        setTitle("Fortune Teller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void createImgPanel(){
        imgPanel = new JPanel();
        image = new ImageIcon("src/FortuneTeller.png");
        fortuneTeller = new JLabel("Fortune Teller", image, JLabel.CENTER);
        fortuneTeller.setVerticalTextPosition(JLabel.BOTTOM);
        fortuneTeller.setHorizontalTextPosition(JLabel.CENTER);
        fortuneTeller.setFont(new Font("MV Boli",Font.PLAIN,36));

        imgPanel.add(fortuneTeller);//Add label to imgPanel!
    }

    public void createDisplayPanel(){
        randomFortunes = new ArrayList<>();
        randomFortunes.add("You'll find love when you're throwing up.");
        randomFortunes.add("You'll get complimented by a grandma.");
        randomFortunes.add("Your ex will come begging for you next week");
        randomFortunes.add("You will be given free toilet paper by your boss");
        randomFortunes.add("Your enemies will bow before your greatness");
        randomFortunes.add("Tomorrow, you'll get told you have a nice butt");
        randomFortunes.add("Your cat is pregnant with kittens?");
        randomFortunes.add("Sometime this year, you'll win a meaningless prize");
        randomFortunes.add("You'll find love... sike! You'll be forever alone.");
        randomFortunes.add("Someone will buy you a car, at your dogs expense.");
        randomFortunes.add("Congrats, your toes will get longer!");
        randomFortunes.add("Next week, you're significant other will break up with you");


        displayPanel = new JPanel();
        fortuneTA = new JTextArea(6,40);
        fortuneTA.setFont(new Font("Arial", Font.PLAIN, 12));
        fortuneTA.setEditable(false);

        fortuneScroller = new JScrollPane(fortuneTA);
        displayPanel.add(fortuneScroller);

    }

    public void createButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));// Inside of controlPnl, we will give it it's own Layout manager
                                                                // since are using buttons. (GridLayout works great with Buttons.)

        readBtn = new JButton("Read my Fortune");
        readBtn.setFont(new Font("Aptos Mono", Font.PLAIN, 14));
        readBtn.addActionListener((ActionEvent ae) -> {
            String selectedWord;
            do {
                index = gen.nextInt(randomFortunes.size());
                selectedWord = randomFortunes.get(index);
            } while (selectedWord.equals(lastSelectedWord) && randomFortunes.size() > 1);

            lastSelectedWord = selectedWord;
            fortuneTA.append(selectedWord + "\n");
        });
        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Broadway", Font.PLAIN, 14));
        quitBtn.addActionListener((ActionEvent ae)-> System.exit(0));

        buttonPanel.add(readBtn);
        buttonPanel.add(quitBtn);
    }
}
