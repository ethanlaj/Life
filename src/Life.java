import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Life extends JFrame {
	boolean[][] board, board2;
	LifeSquare [][] display;
	JPanel boardPanel = new JPanel();
	JPanel controlPanel = new JPanel();
	JButton move = new JButton("Move"), 
			clear = new JButton("Clear"), 
			play = new JButton("Play");
	
	Timer t = new Timer(200, new Mover());
	
	public Life(int size) {
		board = new boolean[size][size];
		board2 = new boolean[size][size];
		display = new LifeSquare[size][size];
		
		setTitle("Game of Life");
		setSize(size*30, size*30);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		boardPanel.setLayout(new GridLayout(size, size));
		for (int r = 0; r<size; r++)
			for (int c = 0; c<size; c++) {
				display[r][c] = new LifeSquare(r, c, this);
				boardPanel.add(display[r][c]);
				board[r][c] = false;
			}
		add(BorderLayout.CENTER, boardPanel);
		controlPanel.add(move);
		controlPanel.add(play);
		controlPanel.add(clear);
		
		clear.addActionListener(new ClearListener());
		move.addActionListener(new Mover());
		play.addActionListener(new PlayListener());
		
		add(BorderLayout.SOUTH, controlPanel);
		
		
		setVisible(true);
	}
	
	private class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int r = 0; r < board.length; r++)
				for (int c = 0; c < board.length; c++) {
					board[r][c] = false;
					display[r][c].setBackground(null);
				}
		}
	}
	private class Mover implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int k;
			boolean[][] temp;
						
			for (int r = 0; r < board.length; r++)
				for (int c = 0; c < board.length; c++) {
					k = 0;
					if (r-1 >= 0 && c-1 >= 0 && board[r-1][c-1]) k++;
					if (r-1 >= 0 && board[r-1][c]) k++;
					if (r-1 >= 0 && c+1 < board.length && board[r-1][c+1]) k++;
					
					if (c-1 >= 0 && board[r][c-1]) k++;
					if (c+1 < board.length && board[r][c+1]) k++;
					
					if (r+1 < board.length && c-1 >= 0 && board[r+1][c-1]) k++;
					if (r+1 < board.length && board[r+1][c]) k++;
					if (r+1 < board.length && c+1 < board.length && board[r+1][c+1]) k++;
					
					if (board[r][c]) {
						if (k == 2 || k == 3) board2[r][c] = true;
						else
							board2[r][c] = false;
					} else {
						if (k == 3)
							board2[r][c] = true;
						else
							board2[r][c] = false;
					}
					
					// Update the display to show the changes
					if (board[r][c] != board2[r][c])
						if (board2[r][c])
							display[r][c].setBackground(Color.orange);
						else
							display[r][c].setBackground(null);
				}		
			temp = board;
			board = board2;
			board2 = temp;
			}
	}

	private class PlayListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (t.isRunning()) {
				t.stop();
				play.setText("Play");
			}
			else {
				t.start();
				play.setText("Pause");
			}
		}
	}

	public static void main(String[] args) {
		/* Make graphics look normal on an Apple Computer */
		try {
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* */
		 
		new Life(10);
	}

}
