package Servlet;

import GameLogic.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Main", urlPatterns = {"/Main"})
public class Main extends HttpServlet {
    private int initialBet;
    private String username;
    private Game game;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            DBGenisys db = new DBGenisys();

            String form = request.getParameter("forms");
            String button = request.getParameter("button");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<link rel=\"stylesheet\" href=\"http://www.w3schools.com/lib/w3.css\">");
            out.println("<head>");
            out.println("</head>");
            out.println("<body style=\"background-image: url('http://il8.picdn.net/shutterstock/videos/273274/thumb/1.jpg');\n" 
                    +" background-size: cover;\n" 
                    +" background-repeat: no-repeat;\n" 
                    +" background-position: top center;    \" >");
            out.println("<p align=\"center\"  style=\"color: white; margin: 20px; padding: 20px;\"> Balance: <span style=\"font-weight: bold\">"+db.getBalance(username)+" </span> </p>");
            
            switch (form.toLowerCase()) {
                case "account":// get input form user, redirect to mainMenu
                    if(userDetails(db, request.getParameter("username"), request.getParameter("password"), out)) {
                        mainMenu(out);
                    }
                    
                    break;
                    
                case "gotomain":
                    mainMenu(out);
                    break;
                    
                case "mainmenu": // gets input from main menu
                    
                    switch (button.toLowerCase()) {
                        case "deposit":
                            deposit(out);
                            break;

                        case "withdraw":
                            withdraw(out);
                            break;

                        case "play":
                            bet(out);
                            break;
                    }
                    break;
                    
                case "deposit":// get input from deposit.html
                    if (request.getParameter("deposit").contains(" ") || request.getParameter("deposit")==null || request.getParameter("deposit").matches("\\s+")) {
                        mainMenu(out);
                    }
                    int amount = Integer.parseInt(request.getParameter("deposit"));
                    if (amount<10 || amount>500) {
                        wrongAmount(out);
                    } else {
                       db.deposit(amount, username);
                       response.sendRedirect("accountMenus/thanksForDeposit.html");
                    }
                    
                    break;

                case "withdraw":// get imput form withdraw.html
                    if (request.getParameter("withdraw").contains(" ") || request.getParameter("withdraw")==null || request.getParameter("withdraw").matches("\\s+")) {
                        mainMenu(out);
                    }
                    amount = Integer.parseInt(request.getParameter("withdraw"));
                    if (amount>db.getBalance(username)) {
                        cantWithdraw(out, amount);
                    } else {
                        db.withdraw(amount, username);
                        withdrew(out, amount);
                    }
                    break;

                case "play":// get input from play.html
                    initialBet = Integer.parseInt(request.getParameter("bet"));
                    if (initialBet <= db.getBalance(username)) { // if player has enough money
                        initialiseGame();
                        db.withdraw(initialBet, username); // takes the bet from the balance
                        displayCards(db, initialBet, out); // displays the cards
                    } else {
                        response.sendRedirect("accountMenus/insufficientBalance.html"); //redirects to error page
                    }
                    break;
                    
                case "insurance":
                    
                    switch (button.toLowerCase()) {
                        case "yes":
                            hasPlacedInsurance(db, out);
                            break;
                        case "no":
                            hasPlacedInsurance(db, out);
                            break;
                    }
                    break;
                    
                    
                case "hasplacedinsurance":
                    compareHands(db, out);
                    break;
                    
                case "gamemenu":
                    switch (button.toLowerCase()) {
                        case "surrender":
                            surrender(db, out);
                            break;
                            
                        case "stand":
                            dealerTurn(db, out);
                            break;
                            
                        case "double":
                            betDouble(db, out);
                            break;
                            
                        case "hit":
                            hit(out);
                            break;
                    }
                    break;
            }
              
        out.println("</body>");
        out.println("</html>");
 
        }
        catch (Exception e) {
            System.out.println("Do processing Error: " + e);
        }
    }
    
    private void wrongAmount(PrintWriter out) {
        out.println("<div align=\"center\" style=\"color: white; margin: 20px; padding:20px; \">\n" +
"            <form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\" >\n" +
"                <input type=\"hidden\" name=\"forms\" value=\"goToMain\"/>\n" +
"                <h2> You can`t deposit less than 10 or more than 500. </h2>\n" +
"                <button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"button3\">Back to Main</button>\n" +
"            \n" +
"            </form>");
    }
    
    private void withdrew(PrintWriter out, int amount) {
        out.println("<div align=\"center\" style=\"color: white; margin: 20px; padding:20px; \">\n" +
"            <form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\" >\n" +
"                <input type=\"hidden\" name=\"forms\" value=\"goToMain\"/>\n" +
"                <h2> You successfully withdrew: "+amount+". </h2>\n" +
"                <button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"button3\">Back</button>\n" +
"            \n" +
"            </form>");
    
    }
    
    private void cantWithdraw(PrintWriter out, int amount) {
        out.println("<div align=\"center\" style=\"color: white; margin: 20px; padding:20px; \">\n" +
"            <form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\" >\n" +
"                <input type=\"hidden\" name=\"forms\" value=\"goToMain\"/>\n" +
"                <h2> The amount "+amount+" is more than your balance! </h2>\n" +
"                <button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"button3\">Back</button>\n" +
"            \n" +
"            </form>");
    }
    
    private void deposit(PrintWriter out) {
        out.println("<div align=\"center\" style=\"color: white; margin: 20px; padding:20px; \">\n" +
"            <form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">                \n" +
"                <input type=\"hidden\" name=\"forms\" value=\"deposit\"/>\n" +
"                <h2>Deposit cash in your account </h2><br><br>\n" +
"                Amount:<br>\n" +
"                <input type=\"number\" name=\"deposit\"> \n" +
"                <input class=\"w3-btn w3-red w3-small\" type=\"submit\" value=\"submit\"> \n" +
"            </form>");
    }
    
    private void withdraw(PrintWriter out) {
        out.println("<div align=\"center\" style=\"color: white; margin: 20px; padding:20px; \">\n" +
"            <form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">                \n" +
"                <input type=\"hidden\" name=\"forms\" value=\"withdraw\"/>\n" +
"                <h2>Withdraw cash from your account </h2><br><br>\n" +
"                Amount:<br>\n" +
"                <input type=\"number\" name=\"withdraw\"> \n" +
"                <input class=\"w3-btn w3-red w3-small\" type=\"submit\" value=\"submit\"> \n" +
"            </form>");
    }
    
    private void bet(PrintWriter out) {
        out.println("<div align=\"center\" style=\"color: white; margin: 20px; padding:20px; \">\n" +
"            <form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">\n" +
"                <input type=\"hidden\" name=\"forms\" value=\"play\"/>\n" +
"                <h2>Place your initial bet </h2><br><br>\n" +
"                <input type=\"number\" name=\"bet\"><br>\n" +
"                <input class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"submit\" value =\"Place your Bet\">\n" +
"            </form>");
    }
    
    private void mainMenu(PrintWriter out) {
        out.println("<div align=\"center\" style=\"color: white; margin: 20px; padding:20px; \">\n" +
"            <form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">\n" +
"                <input type=\"hidden\" name=\"forms\" value=\"mainMenu\"/>\n" +
"                <h2> "+username+" </h2><br><br>\n" +
"                <button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"withdraw\">Withdraw</button>\n" +
"                <button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"deposit\">Deposit</button>\n" +
"                <button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"play\">Play</button>\n" +
"            </form>");
    }

    private boolean userDetails(DBGenisys db, String username, String password, PrintWriter out) throws Exception {
        boolean correctInput = true;
        //The username should be 3-15 characters and no white spaces
        if (username.contains(" ") || username.length()<3 || username.length()>15) {
            correctInput = false;
            out.println("<div align=\"center\" style=\"color: white; margin: 20px; padding:20px; \">"
                    + "<h1>Your username must be 3-15 character and shouldn`t contain empty spaces!</h1>"
                    + "<form name=\"form\" method=\"POST\" action=\"accountMenus/account.html\">"                        
                    + "<input type=\"hidden\" name=\"forms\" value=\"account\"/>"                        
                    + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"continue\">Go back</button>"
                    + "</form>");
        } else if (password.contains(" ") || password.length()<3 || password.length()>15) { //The password should be 3-15 characters and no white spaces
            correctInput = false;
            out.println("<div align=\"center\" style=\"color: white; margin: 20px; padding:20px; \">"
                    + "<h1>Your password must be 3-15 character and shouldn`t contain empty spaces!</h1>"
                    + "<form name=\"form\" method=\"POST\" action=\"accountMenus/account.html\">"                        
                    + "<input type=\"hidden\" name=\"forms\" value=\"account\"/>"                        
                    + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"continue\">Go back</button>"
                    + "</form>");
        }
        db.post(username, password); //puts the info in the database
        this.username = username;
        return correctInput;
    }

    private void initialiseGame() {
        // Create new deck and players
        Deck deck = new Deck();
        Player player = new Player(username);
        Player dealer = new Player("Dealer");
        this.game = new Game(deck, player, dealer);
    }
    
    private void showCards (PrintWriter out, boolean showFirstCard) {
        out.println("<div align=\"center\" style=\"color: white; margin: 0px; padding:100px; \">"
                    + "<h2 align=\"left\">"+username+"'s cards:</h2>"
                    + "<h2 align=\"right\">Dealer's cards:</h2>");
        
        for (int i = 0; i < game.getPlayer().getHand().length; i++) {
            if (game.getPlayer().getHand()[i]==null) {
                break;
            }
            out.println("<iframe src=\"htmlCards/"+game.getPlayer().getHand()[i].toString()+".html\" align=\"left\"  height=\"164\" width=\"130\" style=\"border:none;\"></iframe>");
        }
        
        
        for (int i = 0; i < game.getDealer().getHand().length; i++) {
            if (game.getDealer().getHand()[i]==null) {
                break;
            }
            if (i==0 && showFirstCard == false) {
                out.println("<iframe src=\"htmlCards/faceDown.html\" align=\"right\"  height=\"164\" width=\"130\" style=\"border:none;\"></iframe>");
            } else {
                out.println("<iframe src=\"htmlCards/"+game.getDealer().getHand()[i].toString()+".html\" align=\"right\"  height=\"164\" width=\"130\" style=\"border:none;\"></iframe>");
            }
        }
        
        out.println("<h2 >"+username+"`s initial bet: "+initialBet+"</h2>");
        
        
    }

    private void displayCards(DBGenisys db, int initialBet, PrintWriter out) throws Exception {
        //deals cards
        game.getPlayer().addCard(game.getDeck().deal());
        game.getDealer().addCard(game.getDeck().deal());
        game.getPlayer().addCard(game.getDeck().deal());
        game.getDealer().addCard(game.getDeck().deal());

        showCards(out, false);
        out.println("<div align=\"center\" style=\"color: white; margin: 0px; padding:20px; \">");
                
        
        
        // Dealer`s face up card`s ID
        int card = game.getDealer().getHand()[1].getMyNumber();

        // if the Dealer has A or 10 
        if (card == 1 || card == 10 || card == 11 || card == 12 || card == 13) {
            //and the player has enough money for insurance.
            if(db.getBalance(username) > initialBet / 2) {
                //  YES, NO buttons linking to -->
                out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"
                        + "<h2>Do you want to place insurance bet ?</h2>"
                        + "<input type=\"hidden\" name=\"forms\" value=\"insurance\"/>"
                        + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"yes\">Yes</button>"
                        + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"no\">No</button>"
                        + "</form>");
            } else { // if the player doesnt have money
               
                out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                        
                        + "<input type=\"hidden\" name=\"forms\" value=\"hasplacedinsurance\"/>"                        
                        + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"continue\">Continue</button>"
                        + "</form>");
            }
        } else {
            out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                        
                        + "<input type=\"hidden\" name=\"forms\" value=\"hasplacedinsurance\"/>"                        
                        + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"continue\">Continue</button>"
                        + "</form>");
        }
        

        
        }
    
    private void compareHands(DBGenisys db, PrintWriter out) throws Exception {
        // If PLAYER has BLACKJACK
        if (game.getPlayer().handSum()==21) {
            
            showCards(out,true);
            
            //if DEALER has BLACKJACK neither player wins and link to main menu
            if (game.getDealer().handSum()==21) { 
                db.deposit(initialBet, username);
                out.println("<p style=\"color: white;\"> Both "+username+" and dealer have blackjacj! "+username+" wins his bet back: "+initialBet+" </p>");
                out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                        
                        + "<input type=\"hidden\" name=\"forms\" value=\"backToMainMenu\"/>"                        
                        + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"continue\">Go back to main menu</button>"
                        + "</form>");
            } else { //DEALER doesnt have blackjack, player wins 3z initial bet and linked to mainMenu
                db.deposit(3*initialBet, username);
                out.println("<h2 style=\"color: white;\" > You have a BLACKJACK! </h2>");
                out.println("<p style=\"color: white;\"> "+username+" wins 3x his initial bet: "+initialBet+" </p>");
                out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                        
                        + "<input type=\"hidden\" name=\"forms\" value=\"backToMainMenu\"/>"                        
                        + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"continue\">Go back to main menu</button>"
                        + "</form>");
            }
            //If PLAYER doesnt have blackjack
        } else { 
            //If DEALER has BLACKJACK, player looses, linked to main menu
            if (game.getDealer().handSum()==21) {
                showCards(out, true);
                out.println("<p style=\"color: white;\"> Dealer has blackJack </p>");
                out.println("<h2 style=\"color: white;\"> "+username+" looses his initial bet! </h2>");
                out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                        
                        + "<input type=\"hidden\" name=\"forms\" value=\"backToMainMenu\"/>"                        
                        + "<button type=\"submit\" name=\"button\" value=\"continue\">Go back to main menu</button>"
                        + "</form>");
            } else { //DEALER doesnt have blackjack, no one wins, linked to gameMenu.
                showCards(out,false);
                out.println("<h2 style=\"color: white;\"> What's your next move ? </h2>");                
                out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                        
                        + "<input type=\"hidden\" name=\"forms\" value=\"gameMenu\"/>"                        
                        + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"surrender\">Surrender</button>"
                        + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"stand\">Stand</button>"); 
                if (db.getBalance(username) >= initialBet) { // if player has enough money for double bet
                    out.println("<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"double\">Double</button>");
                }
                out.println("<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"hit\">Hit</button>"
                        + "</form>");
                
            }
        }
    }
    
    private void hasPlacedInsurance(DBGenisys db, PrintWriter out) throws Exception {
        // If the dealer has blackjack and we placed insurance
        db.withdraw(initialBet/2, username);
        if (game.getDealer().handSum()==21) {
            db.deposit((3*initialBet/2), username);
            showCards(out, true);
            out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"
                        + "<h2 style=\"color: white;\">The Dealer has BLACKJACK! You won 3x your insurance bet: "+3*initialBet/2+"</h2>"
                        + "<input type=\"hidden\" name=\"forms\" value=\"hasplacedinsurance\"/>"
                        + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"continue\">Continue</button>"                       
                        + "</form>");
           
            
        // If the dealer deoesnt have blackJack and we posted insurance
        } else {
            
            showCards(out, false);
            out.println( "<h2 style=\"color: white;\"> The dealer doesn't have blackjack! "+username+" loses his insurance bet! </h2>"
                        +"<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"
                        + "<input type=\"hidden\" name=\"forms\" value=\"hasplacedinsurance\"/>"
                        + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"continue\">Continue</button>"                       
                        + "</form>");
           
        }
    }
     
    private void surrender(DBGenisys db, PrintWriter out) throws Exception {
        
        db.deposit(initialBet/2, username);
        
        out.println("<div align=\"center\" style=\" color: white; margin: 20px; padding:20px; \">");
        out.println("<h2> "+username+" serrendered! </h2>");
        out.println("<p> "+username+" gets half his bet back: "+initialBet/2+" </p>");
        out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                        
                + "<input type=\"hidden\" name=\"forms\" value=\"gotomain\"/>"                        
                + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"continue\">Go back to main menu</button>"
                + "</form>");
        
       
        
    }
    
    private void dealerTurn(DBGenisys db, PrintWriter out) throws Exception {
        
        while(game.getDealer().handSum() < 22) {
             if (game.getDealer().handSum()>=17) {
            break;
            }
            game.getDealer().addCard(game.getDeck().deal());
        }
        showCards(out,true);
        // The dealer has not busted
        if (game.getDealer().handSum() < 22) {
            // PLayer && Dealer have same score. Bet is returned. Link to main menu
            if (game.getDealer().handSum() == game.getPlayer().handSum()) {
                db.deposit(initialBet, username);
                int score = game.getPlayer().handSum();
                out.println("<h2 style=\"color: white;\"> "+username+" and dealer have the same score: "+score+" </h2>");
                out.println("<h2 style=\"color: white;\"> "+username+" gets his bet back: "+initialBet+"</h2>");
                out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                        
                        + "<input type=\"hidden\" name=\"forms\" value=\"gotomain\"/>"                        
                        + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"continue\">Go back to main menu</button>"
                        + "</form>");
            } // Dealer wins. Link to main menu
            if (game.getDealer().handSum() > game.getPlayer().handSum()) {            
                int playerScore = game.getPlayer().handSum();
                int dealerScore = game.getDealer().handSum();
                out.println("<h3 style=\"color: white;\"> "+username+" has "+playerScore+" </h3>");
                out.println("<h3 style=\"color: white;\"> Dealer has "+dealerScore+" </h3>");
                out.println("<h1 style=\"color: white;\"> "+username+" loses his bet! </h1>");
                out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                        
                        + "<input type=\"hidden\" name=\"forms\" value=\"gotomain\"/>"                        
                        + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"continue\">Go back to main menu</button>"
                        + "</form>");
            } // PLayer wins 2x bet. Link to main menu
            if (game.getDealer().handSum() < game.getPlayer().handSum()) {     
                db.deposit(2*initialBet, username);
                int playerScore = game.getPlayer().handSum();
                int dealerScore = game.getDealer().handSum();
                out.println("<h3 style=\"color: white;\"> "+username+" has "+playerScore+" </h3>");
                out.println("<h3 style=\"color: white;\"> Dealer has "+dealerScore+" </h3>");
                out.println("<h1 style=\"color: white;\"> "+username+" wins : "+2*initialBet+"</h1>");
                out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                        
                        + "<input type=\"hidden\" name=\"forms\" value=\"gotomain\"/>"                        
                        + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"continue\">Go back to main menu</button>"
                        + "</form>");
            }
        } 
        // Dealer busted, player wins 2x bet. Link to main menu
        if (game.getDealer().handSum() >= 22){ 
            db.deposit(2*initialBet, username);
            out.println("<h2 style=\"color: white;\"> Deealer busted! </h2>");
            out.println("<h1 style=\"color: white;\"> "+username+" wins 2x his bet: "+2*initialBet+" </h1>");
            out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                    
                    + "<input type=\"hidden\" name=\"forms\" value=\"gotomain\"/>"
                    + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"continue\">Go back to main menu</button>"                       
                    + "</form>");
        }
        
        
        
        
    }
    
    private void betDouble(DBGenisys db, PrintWriter out) throws Exception {
        
        db.withdraw(initialBet, username);        
        initialBet += initialBet;
        game.getPlayer().addCard(game.getDeck().deal());
        if (game.getPlayer().handSum()>21) {
            showCards(out,true);
            out.println("<h1 style=\"color: white;\">"+username+" busted and lost all his bets!</h1>");
            out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                        
                    + "<input type=\"hidden\" name=\"forms\" value=\"gotomain\"/>" 
                    + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"menu\">Back to main menu</button>"); 
            
        } else {
            dealerTurn(db, out);
        }
        
        
    }
    
    private void hit(PrintWriter out) {
        // Add one more card
        game.getPlayer().addCard(game.getDeck().deal());
        
        
        // IF player has not bust. Hit or stand menu.
        if(game.getPlayer().handSum() < 22)  {        
            showCards(out,false);
            out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                        
                    + "<input type=\"hidden\" name=\"forms\" value=\"gamemenu\"/>"                        
                    + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"hit\">Hit</button>"
                    + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"stand\">Stand</button>"); 
        } else {
            
            showCards(out,true);
            out.println("<h1 style=\"color: white;\">"+username+" busted and lost all his bets!</h1>");
            out.println("<form name=\"form\" method=\"POST\" action=\"/ThousandServlets/Main\">"                        
                    + "<input type=\"hidden\" name=\"forms\" value=\"gotomain\"/>" 
                    + "<button class=\"w3-btn w3-red w3-small\" type=\"submit\" name=\"button\" value=\"menu\">Back to main menu</button>"); 
            }
        

    }
    
    

        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
        /**
         * Handles the HTTP <code>GET</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * Handles the HTTP <code>POST</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    

    

    

   

    

   

   

    

    

   

    

    }
