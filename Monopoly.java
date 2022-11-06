import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import javafx.scene.input.MouseEvent;

import java.awt.event.*;
import java.io.*;
import java.util.Random;
import java.awt.*;

public class Monopoly extends JFrame implements ActionListener {
    // Declaring and assigning value to variables
    public int squareX = 78 + 145 * 4;
    public int squareY = 680;
    public int squareX2 = 78 + 145 * 4;
    public int squareY2 = 680;
    public static String player = "Player1";
    public int playerLocation1 = 1;
    public int playerLocation2 = 1;
    public int playerLocation = 1;
    public boolean rollGate = true;
    public int player1bal = 800;
    public int player2bal = 800;
    public String onSquare;
    public int n = -1;
    public int buyPrice;
    public int rentPrice;
    String[] listings = new String[] { "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "s12", "s13",
            "s14", "s15", "s16" };
    String[] player1listings = new String[] {};
    String[] ownedListings = new String[] {};
//Creating class for properties of the game squares
    public class Square {
        public int num = 7;
        public String test = "Test output";
        public int squarePrice;
        public int squareRent;
        boolean squareOwned = false;
        boolean occupied = false;
        String owner = "";
        boolean owned = false;
        String squareName;
// Constructor for default squares
        public Square() {
        }
// Constructor for all squares
        public Square(int price, int rent, int squareNum, int x, int y, String name) {
            squarePrice = price;
            squareRent = rent;
            x = squareX;
            y = squareY;
            squareName=name;
        }
// Methods to get price, rentprice, coordinates and other attributes of a certain square
        public int getPrice() {
            return squarePrice;
        }

        public int getRent() {
            return squareRent;
        }

        public int getX() {

            return squareX;
        }

        public int getY() {
            return squareY;
        }

        public void setOwner(String owner1) {
            owner = owner1;
            owned = true;
        }

        public String getOwner() {
            return owner;
        }

        public boolean isOccupied() {
            return occupied;
        }

        public boolean isOwned() {
            return owned;
        }
        public String getName()
        {return squareName;
        }


    }
// Declaring screens and buttons
    JTextArea gamescreen;
    JTextArea gamescreen2;
    JTextArea msgscreen;
    JTextArea pricescreen;
    JButton roll = new JButton("Roll");
    JButton buy = new JButton("Buy");
    JButton pass = new JButton("Pass");
// Declaring default object
    Square tempSquare;

    public static void main(String[] args) {
        new Monopoly();
    }
// Assigning values to all square objects
    Square s1 = new Square(0, 0, 1, 78 + 145 * 4, 680, "Property1");
    Square s2 = new Square(50, 10, 2, 78 + 145 * 3, 680, "Property2");
    Square s3 = new Square(0, 0, 3, 78 + 145 * 2, 680, "Property3");
    Square s4 = new Square(75, 15, 4, 78 + 145, 680, "Property4");
    Square s5 = new Square(0, 0, 5, 78, 680, "Property5");
    Square s6 = new Square(100, 20, 6, 78, 680 - 145, "Property6");
    Square s7 = new Square(125, 25, 7, 78, 680 - 145 * 2, "Property7");
    Square s8 = new Square(150, 30, 8, 78, 680 - 145 * 3, "Property8");
    Square s9 = new Square(0, 0, 9, 78, 680 + 145 * 4, "Property9");
    Square s10 = new Square(175, 35, 10, 78 + 145, 680 - 145 * 4, "Property10");
    Square s11 = new Square(200, 40, 11, 78 + 145 * 2, 680 - 145 * 4, "Property11");
    Square s12 = new Square(225, 45, 12, 78 + 145 * 3, 680 - 145 * 4, "Property12");
    Square s13 = new Square(0, 0, 13, 78 + 145 * 4, 680 - 145 * 4, "Property13");
    Square s14 = new Square(250, 50, 14, 78 + 145 * 4, 680 - 145 * 3, "Property14");
    Square s15 = new Square(0, 0, 15, 78 + 145 * 4, 680 - 145 * 2, "Property15");
    Square s16 = new Square(275, 55, 16, 78 + 145 * 4, 680 - 145, "Property16");

// Adding all screens, buttons and other UI components
// Setting frame settings
    public Monopoly() {
        setLayout(null);

        int num1 = 2;
        String test1 = Integer.toString(num1);
        roll.setBounds(900, 450, 475, 100);
        buy.setBounds(900, 600, 475, 50);
        pass.setBounds(900, 675, 475, 100);
        add(roll);
        add(buy);
        add(pass);
        // if string player is
        roll.addActionListener(this);
        buy.addActionListener(this);
        pass.addActionListener(this);
        gamescreen = new JTextArea();
        gamescreen2 = new JTextArea();
        msgscreen = new JTextArea();
        pricescreen = new JTextArea();
        gamescreen.setBounds(900, 100, 220, 200);
        gamescreen2.setBounds(1150, 100, 220, 200);
        msgscreen.setBounds(900, 325, 475, 50);
        pricescreen.setBounds(900, 390, 475, 50);
        gamescreen.setEditable(false);
        gamescreen2.setEditable(false);
        pricescreen.setEditable(false);
        msgscreen.setEditable(false);
        gamescreen.setLineWrap(true);
        gamescreen2.setLineWrap(true);
        setTitle("Monopoly");
        gamescreen.setForeground(Color.red);
        gamescreen2.setForeground(Color.blue);
        add(gamescreen);
        add(gamescreen2);
        add(msgscreen);
        add(pricescreen);
        setSize(1450, 800);
        setVisible(true);
        setResizable(false);
        buy.setEnabled(false);
        pass.setEnabled(false);
    }
// Creating gameboard, adding images and creating player icons
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        ImageIcon img = new ImageIcon("Monopoly.png");
        ImageIcon chance = new ImageIcon("Chance.png");
        ImageIcon free = new ImageIcon("Freeparking.png");
        ImageIcon go = new ImageIcon("Go.gif");
        ImageIcon jail = new ImageIcon("Jail.png");
        ImageIcon income = new ImageIcon("Income.png");
        Font f = new Font("Tahoma", Font.BOLD, 18);
        Color black = new Color(0, 0, 0);
        g2.setFont(f);
        g2.setColor(black);
        g2.drawString("10", 25 + 170, 30 + 80);
        g2.drawString("11", 5 + 170 * 2, 30 + 80);
        g2.drawString("12", 5 + 170 * 3, 30 + 80);
        g2.drawRoundRect(5, 30, 725, 725, 30, 30);
        g2.drawString("Jail", 5 + 170 * 4, 80+80);
        g2.drawString("14", 5 + 165 * 4, 50 + 80*2);
        g2.drawString("16", 5 + 165 * 4, 50 + 90*6);
        g2.drawString("2", 5 + 170 * 3, 30 + 80*8);
        g2.drawString("Chance", 5 + 170 * 2, 30 + 80*9);
        g2.drawString("4", 5 + 170, 30 + 80*8);
        g2.drawString("6", 5+40, 30 + 85*6);
        g2.drawString("7", 5+40, 30 + 85*4);
        g2.drawString("8", 5+40, 30 + 85*2);
        g2.drawImage(img.getImage(), 215, 285, this);
        g2.drawImage(chance.getImage(), 5 + 155 * 2, 30 + 145 * 4, this);
        g2.drawImage(free.getImage(), 5, 30, this);
        g2.drawImage(free.getImage(), 5, 30 + 145*4, this);
        g2.drawImage(jail.getImage(), 5 + 145 * 4, 30, this);
        g2.drawImage(go.getImage(), 5 + 145 * 4, 30 + 145 * 4, this);
        g2.drawImage(income.getImage(), 15 + 130 * 4, 30 + 145 * 2, this);
        g2.drawRect(5, 30, 145, 145);
        g2.setColor(Color.RED);
        g2.fillRect(5 + 145, 30 + 110, 145, 35);
        g2.fillRect(5 + 145 * 2, 30 + 110, 145, 35);
        g2.fillRect(5 + 145 * 3, 30 + 110, 145, 35);
        g2.setColor(Color.BLACK);
        g2.drawRect(5 + 145 * 2, 30, 145, 145);
        g2.drawRect(5 + 145 * 3, 30, 145, 145);
        g2.drawRect(5 + 145 * 4, 30, 145, 145);
        g2.setColor(Color.YELLOW);
        g2.fillRect(115, 175, 35, 145);
        g2.fillRect(115, 175 + 145, 35, 145);
        g2.fillRect(115, 175 + 145 * 2, 35, 145);
        g2.setColor(Color.BLACK);
        g2.drawRect(5, 30 + 145, 145, 145);
        g2.drawRect(5, 30 + 145 * 2, 145, 145);
        g2.drawRect(5, 30 + 145 * 3, 145, 145);
        g2.drawRect(5, 30 + 145 * 4, 145, 145);
        g2.setColor(Color.BLUE);
        g2.fillRect(150, 30 + 145 * 4, 145, 35);
        g2.fillRect(146 * 3, 30 + 145 * 4, 145, 35);
        g2.setColor(Color.GREEN);
        g2.fillRect((115 * 5) + 10, 175, 35, 145);
        g2.fillRect((115 * 5) + 10, 175 + 145 * 2, 35, 145);
        g2.setColor(Color.BLACK);
        g2.drawRect(5 + 145, 30 + 145 * 4, 145, 145);
        g2.drawRect(5 + 145 * 2, 30 + 145 * 4, 145, 145);
        g2.drawRect(5 + 145 * 3, 30 + 145 * 4, 145, 145);
        g2.drawRect(5 + 145 * 4, 30 + 145 * 4, 145, 145);
        g2.drawRect(5 + 145 * 4, 30 + 145, 145, 145);
        g2.drawRect(5 + 145 * 4, 30 + 145 * 2, 145, 145);
        g2.drawRect(5 + 145 * 4, 30 + 145 * 3, 145, 145);
        g2.drawRect(5 + 145 * 4, 30 + 145 * 4, 145, 145);
        g2.setColor(Color.RED);
        g2.fillRect(squareX, squareY, 30, 30);
        g2.setColor(Color.BLUE);
        g2.fillRect(squareX2, squareY2, 30, 30);
        
    };

// Creating random object, dice and strings
    Random rnd = new Random();
    public int dice1;
    public int dice2;
    public int diceTotal;
public String Player1Stat = "";
public String player1balString = "";
public String Player2Stat = "";
public String player2balString = "";

// Adding action listener for when user clicks roll, buy or pass button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (rollGate == true) {
            if (e.getSource() == roll) {
                buy.setEnabled(true);
                pass.setEnabled(true);
                // Generating random numbers
                dice1 = rnd.nextInt(6) + 1;
                dice2 = rnd.nextInt(6) + 1;
                diceTotal = dice1 + dice2;
               msgscreen.setText("You rolled a "+diceTotal);;
// Updating player location
                if (player == "Player1") {
                    playerLocation1 = position(diceTotal, playerLocation1);
                } else {
                    playerLocation2 = position(diceTotal, playerLocation2);
                }

                rollGate = false;
                player2balString = "\nPlayer2 balance: "+player2bal;
                player1balString = "\nPlayer1 balance: "+player1bal;
                gamescreen.setText("Player1's titles:"+Player1Stat+player1balString);
                gamescreen2.setText("Player2's titles:"+Player2Stat+player2balString);
            }
        }
        if (rollGate == false) {
            roll.setEnabled(false);
        }
// If user clicks buy button 
        if (e.getSource() == buy) {
            rollGate = true;
            roll.setEnabled(true);
                pass.setEnabled(false);
            // Display user information on game screen
            if (player == "Player1") {
                if (!tempSquare.isOwned()) {
                    player1bal = player1bal - buyPrice;
                    tempSquare.setOwner(player);
                    Player1Stat =  Player1Stat + " , " + tempSquare.getName();
                    player1balString = "\nPlayer1 balance: "+player1bal;
                    gamescreen.setText("Player1's titles:"+Player1Stat+player1balString);
                    player = "Player2";
                } else {
                    buy.setEnabled(false);
                 player = "Player2";
                }

            }
            // Display user information on game screen
            else if (player == "Player2") {
                if (!tempSquare.isOwned()) {
                    player2bal = player2bal - buyPrice;
                    Player2Stat =  Player2Stat + " , " + tempSquare.getName();
                    player2balString = "\nPlayer2 balance: "+player2bal;
                    gamescreen2.setText("Player2's titles:"+Player2Stat+player2balString);
                    tempSquare.setOwner(player);
                    player = "Player1";
                } else {
                    buy.setEnabled(false);
                    // msgscreen.setText("Already bought");
                    player = "Player1";
                }

            }
            buy.setEnabled(false);
// If statements to determine who wins if a user is bankrupt
            if (player1bal <= 0){
                msgscreen.setText("Player2 wins!");
                buy.setEnabled(false);
                pass.setEnabled(false);
                roll.setEnabled(false);
            }
            if (player2bal <= 0){
                msgscreen.setText("Player1 wins!");
                buy.setEnabled(false);
                pass.setEnabled(false);
                roll.setEnabled(false);
            }
        }
// If user clicks pass then do nothing
        if (e.getSource() == pass) {
            rollGate = true;
            roll.setEnabled(true);
            buy.setEnabled(false);
            if (player == "Player1") {
                player = "Player2";
            } else if (player == "Player2") {
                player = "Player1";
            }
            pass.setEnabled(false);
        }

    }


// Method to pay rent from user to user
    public void payRent() {
        if (tempSquare.owner == "Player1" && player == "Player2") {
            player1bal = player1bal + rentPrice;
            player2bal = player2bal - rentPrice;
            msgscreen.setText("Rent paid - "+rentPrice);
            player2balString = "\nPlayer2 balance: "+player2bal;
            player1balString = "\nPlayer1 balance: "+player1bal;
            buy.setEnabled(false);
        }
        else if (tempSquare.owner == "Player2" && player == "Player1") {
            player1bal = player1bal - rentPrice;
            player2bal = player1bal + rentPrice;
            msgscreen.setText("Rent paid - "+rentPrice);
            player2balString = "\nPlayer2 balance: "+player2bal;
            player1balString = "\nPlayer1 balance: "+player1bal;
            buy.setEnabled(false);
        }

        if (player1bal <= 0){
            msgscreen.setText("Player2 wins!");
            buy.setEnabled(false);
            pass.setEnabled(false);
            roll.setEnabled(false);
        }
        if (player2bal <= 0){
            msgscreen.setText("Player1 wins!");
            buy.setEnabled(false);
            pass.setEnabled(false);
            roll.setEnabled(false);
        }
    }

    public int position(int diceTotal, int loc) {
// If statements to determine who wins if a user is bankrupt
            if (player1bal <= 0){
                msgscreen.setText("Player2 wins!");
                buy.setEnabled(false);
                pass.setEnabled(false);
                roll.setEnabled(false);
            }
            if (player2bal <= 0){
                msgscreen.setText("Player1 wins!");
                buy.setEnabled(false);
                pass.setEnabled(false);
                roll.setEnabled(false);
            }
        
       
        int move = diceTotal + loc;
        // if player 1 then update location 1.. if player 2 then update location 2
        // If user passes GO
        if (move > 16) {
            move = move - 16;
            msgscreen.setText("Passed Go!");
            if (player == "Player1"){
            player1bal = player1bal + 100;
            player2balString = "\nPlayer2 balance: "+player2bal;
            player1balString = "\nPlayer1 balance: "+player1bal;
            }
            if (player == "Player2"){
                player2bal = player2bal + 100;
                player2balString = "\nPlayer2 balance: "+player2bal;
                player1balString = "\nPlayer1 balance: "+player1bal;
                }
        }
        // Determining what square Player 1 is on
        if (player == "Player1") {
            playerLocation1 = move;
           
            if (move == 1) {
                squareX = 78 + 145 * 4;
                squareY = 680;
                onSquare = listings[0];
                buyPrice = s1.getPrice();
                rentPrice = s1.getRent();
                tempSquare = s1;
                buy.setEnabled(false);
            }
            if (move == 2) {
                squareX = 78 + 145 * 3;
                squareY = 680;
                onSquare = listings[1];
                buyPrice = s2.getPrice();
                rentPrice = s2.getRent();
                tempSquare = s2;
            }
            // If player 1 lands on chance
            if (move == 3) {
                squareX = 78 + 145 * 2;
                squareY = 680;
                onSquare = listings[2];
                buyPrice = s3.getPrice();
                rentPrice = s3.getRent();
                tempSquare = s3;
                buy.setEnabled(false);
                int num = rnd.nextInt(2);
                if (num == 0){
                    player1bal = player1bal - 100;
                    msgscreen.setText("Chance! Parking ticket, paid $100");
                }
                if (num == 1){
                    player1bal = player1bal - 200;
                    msgscreen.setText("Chance! You donated to a local charity, paid $200");
                }
                if (num == 2){
                    player1bal = player1bal + 100;
                    msgscreen.setText("Chance! It's your birthday gift, recieve $100!");
                }
            }
            if (move == 4) {
                squareX = 78 + 145;
                squareY = 680;
                onSquare = listings[3];
                buyPrice = s4.getPrice();
                rentPrice = s4.getRent();
                tempSquare = s4;

            }
            if (move == 5) {
                squareX = 78;
                squareY = 680;
                onSquare = listings[4];
                buyPrice = s5.getPrice();
                rentPrice = s5.getRent();
                tempSquare = s5;
                buy.setEnabled(false);

            }
            if (move == 6) {
                squareX = 78;
                squareY = 680 - 145;
                onSquare = listings[5];
                buyPrice = s6.getPrice();
                rentPrice = s6.getRent();
                tempSquare = s6;

            }
            if (move == 7) {
                squareX = 78;
                squareY = 680 - 145 * 2;
                onSquare = listings[6];
                buyPrice = s7.getPrice();
                rentPrice = s7.getRent();
                tempSquare = s7;

            }
            if (move == 8) {
                squareX = 78;
                squareY = 680 - 145 * 3;
                onSquare = listings[7];
                buyPrice = s8.getPrice();
                rentPrice = s8.getRent();
                tempSquare = s8;

            }
            if (move == 9) {
                squareX = 78;
                squareY = 680 - 145 * 4;
                onSquare = listings[8];
                buyPrice = s9.getPrice();
                rentPrice = s9.getRent();
                tempSquare = s9;
                buy.setEnabled(false);

            }
            if (move == 10) {
                squareX = 78 + 145;
                squareY = 680 - 145 * 4;
                onSquare = listings[9];
                buyPrice = s10.getPrice();
                rentPrice = s10.getRent();
                tempSquare = s10;

            }
            if (move == 11) {
                squareX = 78 + 145 * 2;
                squareY = 680 - 145 * 4;
                onSquare = listings[10];
                buyPrice = s11.getPrice();
                rentPrice = s11.getRent();
                tempSquare = s11;

            }
            if (move == 12) {
                squareX = 78 + 145 * 3;
                squareY = 680 - 145 * 4;
                onSquare = listings[11];
                buyPrice = s12.getPrice();
                rentPrice = s12.getRent();
                tempSquare = s12;

            }
                        // If player 1 lands on jail

            if (move == 13) {
                squareX = 78 + 145 * 4;
                squareY = 680 - 145 * 4;
                onSquare = listings[12];
                buyPrice = s13.getPrice();
                rentPrice = s13.getRent();
                tempSquare = s13;
                buy.setEnabled(false);
                player1bal = player1bal - 100;
                msgscreen.setText("Jail bail paid - $100 paid");

            }
            if (move == 14) {
                squareX = 78 + 145 * 4;
                squareY = 680 - 145;
                onSquare = listings[13];
                buyPrice = s14.getPrice();
                rentPrice = s14.getRent();
                tempSquare = s14;
            }
                        // If player 1 lands on income tax

            if (move == 15) {
                squareX = 78 + 145 * 4;
                squareY = 680 - 145 * 2;
                onSquare = listings[14];
                buyPrice = s15.getPrice();
                rentPrice = s15.getRent();
                tempSquare = s15;
                buy.setEnabled(false);
                player1bal = player1bal - 200;
                msgscreen.setText("Income tax paid - $200 paid");
            }
            if (move == 16) {
                squareX = 78 + 145*4;
                squareY = 680 - 145 * 3;
                onSquare = listings[15];
                buyPrice = s16.getPrice();
                rentPrice = s16.getRent();
                tempSquare = s16;
            }
            // Calling rent method
            payRent();
            gamescreen.setText("Player1's titles:"+Player1Stat+player1balString);
            gamescreen.setText("Player2's titles:"+Player2Stat+player2balString);
            pricescreen.setText("Property price is "+buyPrice);
            
        }  
        // Determing what square Player 2 is on
        else {
            playerLocation2 = move;
            
            
            if (move == 1) {
                squareX2 = 78 + 145 * 4;
                squareY2 = 680;
                onSquare = listings[0];
                buyPrice = s1.getPrice();
                rentPrice = s1.getRent();
                tempSquare = s1;
                buy.setEnabled(false);
            }
            if (move == 2) {
                squareX2 = 78 + 145 * 3;
                squareY2 = 680;
                onSquare = listings[1];
                buyPrice = s2.getPrice();
                rentPrice = s2.getRent();
                tempSquare = s2;

            }
                        // If player 2 lands on chance
            if (move == 3) {
                squareX2 = 78 + 145 * 2;
                squareY2 = 680;
                onSquare = listings[2];
                buyPrice = s3.getPrice();
                rentPrice = s3.getRent();
                tempSquare = s3;
                buy.setEnabled(false);
                int num = rnd.nextInt(2);
                if (num == 0){
                    player2bal = player2bal - 100;
                    msgscreen.setText("Chance! Parking ticket, paid $100");
                }
                if (num == 1){
                    player2bal = player2bal - 200;
                    msgscreen.setText("Chance! You donated to a local charity, paid $200");
                }
                if (num == 2){
                    player2bal = player2bal + 100;
                    msgscreen.setText("Chance! It's your birthday gift, recieve $100!");
                }

            }
            if (move == 4) {
                squareX2 = 78 + 145;
                squareY2 = 680;
                onSquare = listings[3];
                buyPrice = s4.getPrice();
                rentPrice = s4.getRent();
                tempSquare = s4;

            }
            if (move == 5) {
                squareX2 = 78;
                squareY2 = 680;
                onSquare = listings[4];
                buyPrice = s5.getPrice();
                rentPrice = s5.getRent();
                tempSquare = s5;
                buy.setEnabled(false);

            }
            if (move == 6) {
                squareX2 = 78;
                squareY2 = 680 - 145;
                onSquare = listings[5];
                buyPrice = s6.getPrice();
                rentPrice = s6.getRent();
                tempSquare = s6;

            }
            if (move == 7) {
                squareX2 = 78;
                squareY2 = 680 - 145 * 2;
                onSquare = listings[6];
                buyPrice = s7.getPrice();
                rentPrice = s7.getRent();
                tempSquare = s7;

            }
            if (move == 8) {
                squareX2 = 78;
                squareY2 = 680 - 145 * 3;
                onSquare = listings[7];
                buyPrice = s8.getPrice();
                rentPrice = s8.getRent();
                tempSquare = s8;

            }
            if (move == 9) {
                squareX2 = 78;
                squareY2 = 680 - 145 * 4;
                onSquare = listings[8];
                buyPrice = s9.getPrice();
                rentPrice = s9.getRent();
                tempSquare = s9;
                buy.setEnabled(false);


            }
            if (move == 10) {
                squareX2 = 78 + 145;
                squareY2 = 680 - 145 * 4;
                onSquare = listings[9];
                buyPrice = s10.getPrice();
                rentPrice = s10.getRent();
                tempSquare = s10;

            }
            if (move == 11) {
                squareX2 = 78 + 145 * 2;
                squareY2 = 680 - 145 * 4;
                onSquare = listings[10];
                buyPrice = s11.getPrice();
                rentPrice = s11.getRent();
                tempSquare = s11;

            }
            if (move == 12) {
                squareX2 = 78 + 145 * 3;
                squareY2 = 680 - 145 * 4;
                onSquare = listings[11];
                buyPrice = s12.getPrice();
                rentPrice = s12.getRent();
                tempSquare = s12;

            }

           // If player 2 lands on jail
            if (move == 13) {
                squareX2 = 78 + 145 * 4;
                squareY2 = 680 - 145 * 4;
                onSquare = listings[12];
                buyPrice = s13.getPrice();
                rentPrice = s13.getRent();
                tempSquare = s13;
                buy.setEnabled(false);
                player2bal = player2bal - 100;
                msgscreen.setText("Paid jail bail - $100 paid");

            } 
            if (move == 14) {
                squareX2 = 78 + 145 * 4;
                squareY2 = 680 - 145;
                onSquare = listings[13];
                buyPrice = s14.getPrice();
                rentPrice = s14.getRent();
                tempSquare = s14;
            }

            // If player 2 lands on income tax
            if (move == 15) {
                squareX2 = 78 + 145 * 4;
                squareY2 = 680 - 145 * 2;
                onSquare = listings[14];
                buyPrice = s15.getPrice();
                rentPrice = s15.getRent();
                tempSquare = s15;
                buy.setEnabled(false);
                player2bal = player2bal - 200;
                msgscreen.setText("Income tax paid - 200 paid");
            }
            if (move == 16) {
                squareX2 = 78 + 145*4;
                squareY2 = 680 - 145 * 3;
                onSquare = listings[15];
                buyPrice = s16.getPrice();
                rentPrice = s16.getRent();
                tempSquare = s16;
            }
            // Calling rent method
            payRent();
            // Updating what properties each player owns and outputting what a property price is
            gamescreen.setText("Player1's titles:"+Player1Stat+player1balString);
            gamescreen.setText("Player2's titles:"+Player2Stat+player2balString);
            pricescreen.setText("Property price is "+buyPrice);
        }
        
// Repainting player icons 
        repaint();
        return move;
    }

    

}
