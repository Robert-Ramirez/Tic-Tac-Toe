import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;

public class TicTacToe_with_Computer extends JFrame implements ActionListener {
  /**
   *
   */
  private static final long serialVersionUID = 2L;
  private JPanel board;
  private JButton button[] = new JButton[9];
  private JLabel Result;
  private Winning_Conditions game;
  private JButton Reset;
  private int count = 0;

  public TicTacToe_with_Computer(){
    intiObject();
    addToPanel();
    displayTicTacToe_with_Computer();
    this.add(board, BorderLayout.CENTER);
    this.add(Result, BorderLayout.SOUTH);
    this.add(Reset, BorderLayout.NORTH);
  }

  private void displayTicTacToe_with_Computer() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400, 400);
    this.setLayout(new BorderLayout());
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    this.setTitle("Tic-Tac-Toe");
  }

  private void intiObject(){
    game = new Winning_Conditions(true, false);
    board = new JPanel();
    board.setLayout(new GridLayout(3,3));
    Result = new JLabel();
    for (int i = 0; i < 9; i++) {
      button[i] = new JButton(" ");
      button[i].addActionListener(this);
      button[i].setFocusPainted(false);
    }

    Reset = new JButton("Reset the Game");
    Reset.addActionListener(this);
    Reset.setFocusPainted(false);
  }

  private void addToPanel(){
    for (int i = 0; i < button.length; i++) {
      board.add(button[i]);
    }
  }

  public void actionPerformed(ActionEvent e){
    for (int i = 0; i < button.length; i++) {
      if(button[i] == e.getSource()){
        if(game.isPlayerOne()){
          button[i].setText("X");
          game.setPlayerOne(false);
          game.setPlayerTwo(true);
          button[i].setEnabled(false);
          computerMove("O");
          game.setPlayerOne(true);
          game.setPlayerTwo(false);
          button[i].setEnabled(false);
        }
        count += 2;
      }
    }

    Boolean winner = game.operation(button);
    if(winner){
      Result.setText("Congratulations "+ game.win +" is the winner!");
      for (int i = 0; i < button.length; i++) {
        button[i].setEnabled(false);
      }
    }else if(count == 10){
      Result.setText("Draw. Try again?");
    }
    if(e.getSource() == Reset){
      Reset();
    }
  }

  private void computerMove(String s) {
    int i, turns = 0;
    String d = " ";
    Random rand = new Random();
    i = rand.nextInt(9);

      while(turns < 100){

      if(button[i].getText().equals(d) && !game.operation(button))
       {
        button[i].setText(s);
        button[i].setEnabled(false);
        return;
       }
          turns++;
        i = rand.nextInt(9);
       }  
     }

  public void Reset(){
    for (int i = 0; i < button.length; i++) {
      button[i].setText(" ");
      button[i].setEnabled(true);
      button[i].setBackground(null);
    }
    Result.setText("");
    count = 0;
  }

  public static void main(String[] args) {
    new TicTacToe_with_Computer();
  }
}
