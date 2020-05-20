package saying;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import javax.swing.JButton;

public class Main {
	private final MainUI mainUI;
	private Main main;
	private SayingDAO dao;

	private LoginScreen loginScreen;
	private OneofSayingUI oneofSayingUI;

	private String id;
	private String pwd;
	private int index;
	private String[] datas;
	private int saying_cnt;

	Logger logger;
	boolean status;
	Thread thread;

	public Main(MainUI mainUI) {
		// logger = Logger.getLogger(this.getClass().getName());
		this.mainUI = mainUI;
	}

	public void refresh() {

	}

	public void appMain() {

		// if press the SayingBtn, add Event
		mainUI.addButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				for (int i = 0; i < mainUI.saying_cnt; i++) {
					if (obj == mainUI.btn[i]) {
						System.out.println("EnterSaying!!");
						EnterSaying(mainUI.id, mainUI.pwd, i);
					}
				}
				if (obj == mainUI.inquiryOrder) {
					System.out.println("inquiryOrder!!");
					inquiryOrder(mainUI.id, mainUI.pwd);
				} else if (obj == mainUI.registerOrder) {
					System.out.println("registerOrder!!");
					registerOrder(mainUI.id, mainUI.pwd, mainUI.saying_cnt);
				} else if (obj == mainUI.userRankingOrder) {
					System.out.println("user Ranking Order!!");
					userRankingOrder(mainUI.id, mainUI.pwd);

				}
			}

		});

	}

	public void EnterSaying(String id, String pwd, int index) {

		this.id = id;
		this.pwd = pwd;
		this.index = index;

		dao = new SayingDAO();
		datas = dao.getOneofSaying(index);

		mainUI.dispose(); // close
		this.oneofSayingUI = new OneofSayingUI(id, pwd, datas); // 프레임 오픈

	}

	public void registerOrder(String id, String pwd, int saying_cnt) {
		
		this.id = id;
		this.pwd = pwd;
	
		mainUI.dispose();
		main = new Main(new MainUI(id, pwd, 0)); // 프레임 오픈
		main.appMain();
	}

	public void inquiryOrder(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;

		mainUI.dispose();
		main = new Main(new MainUI(id, pwd, 1));
		main.appMain();
	}

	public void userRankingOrder(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;

		mainUI.dispose();
		main = new Main(new MainUI(id, pwd, 2));
		main.appMain();
	}

	public static void main(String[] args) {

	}

}
