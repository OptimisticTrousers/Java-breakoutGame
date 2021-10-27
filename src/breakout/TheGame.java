package breakout;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;



public class TheGame extends JPanel implements KeyListener, ActionListener
{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private boolean play = false;
  private int score = 0;
  private int ballXdir = -1;
  private int ballYdir = -2;
  private int ballPosx = 120;
  private int ballPosy = 350;
  private int totalBricks = 36;
  private Timer timer;
  private int delay = 5;
  private int playerx = 310;
  private boolean stop;

  private Map map;

  public TheGame()
  {
    map = new Map(4, 9);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    timer = new Timer(delay, this);
    addKeyListener(this);
    timer.start();
  }

  public void paint(Graphics g)
  {
    //background

    g.setColor(Color.black);
    g.fillRect(1, 1, 692, 592);

    //map
    map.draw((Graphics2D)g);

    //borders
    g.fillRect(0, 0, 692, 3);
    g.fillRect(691, 0, 3, 592);
    g.setColor(Color.green);
    g.fillRect(0, 0, 3, 592);


    //score
    g.setColor(Color.orange);
    g.setFont(new Font("serif", Font.BOLD, 25));
    g.drawString("" +score, 590, 30);

     //paddle
    g.fillRect(playerx, 550, 100, 8);
    g.setColor(Color.yellow);
   
    
    // the ball
    g.fillOval(ballPosx, ballPosy, 20, 20);
    g.setColor(Color.MAGENTA);
    

    if(totalBricks <= 0)
    {
      play = false;
      ballYdir = 0;
      ballXdir = 0;
      

      g.setColor(Color.white);
      g.setFont(new Font("serif", Font.CENTER_BASELINE, 30));
      g.drawString("You Won!", 265, 300);
      g.setFont(new Font("serif", Font.ROMAN_BASELINE, 25));
      g.drawString("Score: " + score, 275, 350);
      g.setFont(new Font("serif", Font.ITALIC, 20));
      g.drawString("Press Enter to Restart", 230, 400);
      stop = true;

      
    }

     if (ballPosy > 570) {
        play = false;
        ballXdir = 0;
        ballYdir = 0;
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.CENTER_BASELINE, 30));
        g.drawString("Game Over", 250, 300);
        g.setFont(new Font("serif", Font.ROMAN_BASELINE, 25));
        g.drawString("Score: " + score, 275, 350);
        g.setFont(new Font("serif", Font.ITALIC, 20));
        g.drawString("Press Enter to Restart ", 230, 400);
        stop = true;
        
        
  }

  g.dispose();
  
  }
  
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    timer.start();

    if(play)
    {
      if(new Rectangle(ballPosx, ballPosy, 20, 20).intersects(new Rectangle(playerx, 550, 100, 8)))
      {
        ballYdir = -ballYdir;
      }

      A: for(int i = 0; i < map.map.length; i++)
      {
        for(int j = 0; j < map.map[0].length; j++)
        {
          if(map.map[i][j] > 0)
          {
        	int brickWidth = map.brickWidth;
            int brickHeight = map.brickHeight;
            int brickX = j * map.brickWidth + 80;
            int brickY = i * map.brickHeight + 50;
            

            Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
            Rectangle ballRect = new Rectangle(ballPosx, ballPosy, 20, 20);
            Rectangle brickRect = rect;
          

          if (ballRect.intersects(brickRect)) {
           map.setBrickValue(0, i, j);
           totalBricks--;
           score +=5;
                        
           if (ballPosx + 19 <= brickRect.x || ballPosx + 1 >= brickRect.x + brickRect.width) {
            ballXdir = -ballXdir;
            } 
            else 
            {
             ballYdir = -ballYdir;
             }
                        
            break A;
          }
        }
      }
    }


      	ballPosx += ballXdir;
        ballPosy += ballYdir;
        if (ballPosx < 0) {
            ballXdir = -ballXdir;
        }
        if (ballPosy < 0) {
            ballYdir = -ballYdir;
        }
        if (ballPosx > 670) {
            ballXdir = -ballXdir;
        }
        


       
    }

    repaint();
}
  




@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	if(e.getKeyCode() == KeyEvent.VK_RIGHT)
	{
		if(playerx >= 600)
		{
			playerx = 600;
			
		}
		if(stop == true)
		{
			return;
		}
		else
		{
			moveRight();
		}
	}
	
	if(e.getKeyCode() == KeyEvent.VK_LEFT)
	{
		if(playerx < 10)
		{
			playerx = 10;
		}
		if(stop == true)
		{
			return;
		}
		else {
			moveLeft();
		}
	}
	
	if(e.getKeyCode() == KeyEvent.VK_ENTER)
	{
		play = true;
		ballPosx = 120;
		ballPosy = 350;
		ballXdir = -1;
		ballYdir = -2;
		playerx = 310;
		score = 0;
		totalBricks = 36;
		stop = false;
		
		map = new Map(4, 9);
		
		repaint();
	}
	
}

public void moveRight()
{
  play = true;
  playerx+= 25;

}

public void moveLeft()
{
  play = true;
  playerx -= 25;
}



}