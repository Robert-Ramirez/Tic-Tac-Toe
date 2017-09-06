import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TicTacToe_with_Human extends JFrame implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel board;
	private JButton button[] = new JButton[9];
	private JLabel Result;
	private Winning_Conditions game;
	private JButton Reset;
	private int count = 0;

	public TicTacToe_with_Human(){
		intObject();
		addPanel();
		displayTicTacToe_with_Human();
		this.add(board, BorderLayout.CENTER);
		this.add(Result, BorderLayout.SOUTH);
		this.add(Reset, BorderLayout.NORTH);
	}

	private void displayTicTacToe_with_Human() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 400);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("Tic-Tac-Toe");
	}

	private void intObject(){
		game = new Winning_Conditions(true, false);
		board = new JPanel();
		board.setLayout(new GridLayout(3,3));
		Result = new JLabel();
		for (int i = 0; i < 9; i++) {
			button[i] = new JButton("");
			button[i].addActionListener(this);
			button[i].setFocusPainted(false);
		}

		Reset = new JButton("Reset the Game");
		Reset.addActionListener(this);
		Reset.setFocusPainted(false);
	}

	private void addPanel(){
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
				}
				else
				{
					button[i].setText("O");
					game.setPlayerOne(true);
					game.setPlayerTwo(false);
				}
				count++;
				button[i].setEnabled(false);
		 }
	 }

		Boolean win = game.operation(button);                 
		if(win){
			Result.setText("Congratulations "+ game.win +" is the winner!");
			for (int i = 0; i < button.length; i++) {
				button[i].setEnabled(false);
			}
		}else if(count==9){
			Result.setText("Draw. Try again?");
		}
		if(e.getSource() == Reset){
			Reset();
		}
	}

	public void Reset(){
		for (int i = 0; i < button.length; i++) {
			button[i].setText(" ");
			button[i].setEnabled(true);
			button[i].setBackground(null);
		}
		Result.setText(" ");
		count = 0;
	}

	public static void main(String[] args) {
		new TicTacToe_with_Human();
	}
}
