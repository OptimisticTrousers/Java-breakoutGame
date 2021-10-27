package breakout;

 import javax.swing.JFrame;

class Main {
  public static void main(String[] args) {
	  
    JFrame jframe = new JFrame();
    TheGame theGame = new TheGame();

    jframe.setTitle("Breakout");
    jframe.setBounds(10, 10, 700, 600);
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.setResizable(false);

    jframe.add(theGame);
    jframe.setVisible(true);
    
  }
}