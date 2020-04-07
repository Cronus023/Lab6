package Package6;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class MainFrame extends JFrame {
	//size of window
	private static final int WIDTH = 700;
	private static final int HEIGHT = 500;
	
	private JMenuItem pauseMenuItem;
	private JMenuItem resumeMenuItem;
	
	// work field
	private Field field = new Field();
	
	public MainFrame() {
		super("Balls");
		setSize(WIDTH, HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		//window centre
		setLocation((kit.getScreenSize().width - WIDTH)/2,(kit.getScreenSize().height - HEIGHT)/2);
        //full open
		setExtendedState(MAXIMIZED_BOTH);
		
		// create menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//create menu item "balls"
		JMenu ballMenu = new JMenu("Мячи");
		Action addBallAction = new AbstractAction("Добавить мяч") {
			public void actionPerformed(ActionEvent event) {
				field.addBall();
			    if (!pauseMenuItem.isEnabled() && !resumeMenuItem.isEnabled()) {
			        pauseMenuItem.setEnabled(true);
		        }
			}
		};
		menuBar.add(ballMenu);
		ballMenu.add(addBallAction);
		
		//create menu item "control"
		JMenu controlMenu = new JMenu("Управление");
		menuBar.add(controlMenu);
		Action pauseAction = new AbstractAction("Приостановить движение"){
			public void actionPerformed(ActionEvent event) {
				field.pause();
			    pauseMenuItem.setEnabled(false);
			    resumeMenuItem.setEnabled(true);
			}
		};
		pauseMenuItem = controlMenu.add(pauseAction);
		pauseMenuItem.setEnabled(false);
		Action resumeAction = new AbstractAction("Возобновить движение") {
			public void actionPerformed(ActionEvent event) {
				field.resume();
			    pauseMenuItem.setEnabled(true);
			    resumeMenuItem.setEnabled(false);
			}
		};
		resumeMenuItem = controlMenu.add(resumeAction);
		resumeMenuItem.setEnabled(false); 
		getContentPane().add(field, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
