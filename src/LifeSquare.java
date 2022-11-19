import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LifeSquare extends JButton {
	Life life;
	int row,col;
	
	public LifeSquare(int r, int c, Life lifeGame) {
		life = lifeGame;
		row = r;
		col = c;
		life.board[row][col] = false;
		addActionListener(new LifeListener());
		
	}
	
	class LifeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			life.board[row][col] = !life.board[row][col];
			if (life.board[row][col] == true) {
				setBackground(Color.orange);
			} else {
				setBackground(null);
			}
		} 
	}
}
