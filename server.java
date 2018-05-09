package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class server {

	private JFrame frame;
	private JTextField tfX;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					server window = new server();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public server() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 163);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				 ServerSocket listener = null;
			       String line;
			       BufferedReader is;
			       BufferedWriter os;
			       Socket socketOfServer = null;
			 
			    
			 
			       // Mở một ServerSocket tại cổng 9999.
			       // Chú ý bạn không thể chọn cổng nhỏ hơn 1023 nếu không là người dùng
			       // đặc quyền (privileged users (root)).
			       try {
			           listener = new ServerSocket(9999);
			       } catch (IOException e) {
			           System.out.println(e);
			           System.exit(1);
			       }
			 
			       try {
			           System.out.println("Server is waiting to accept user...");
			 
			 
			           // Chấp nhận một yêu cầu kết nối từ phía Client.
			           // Đồng thời nhận được một đối tượng Socket tại server.
			 
			           socketOfServer = listener.accept();
			           System.out.println("Accept a client!");
			 
			      
			           // Mở luồng vào ra trên Socket tại Server.
			           is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
			           os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
			           DataInputStream inputServer= new DataInputStream(socketOfServer.getInputStream());
			  
			 
			               // Nếu người dùng gửi tới QUIT (Muốn kết thúc trò chuyện).
			            	   String a=inputServer.readLine();
			            	   int x= Integer.parseInt(a);
			            	   String b=inputServer.readLine();
			            	   int y= Integer.parseInt(b);
			            	  int c=x+y;
			            	  String S = String.valueOf(c);
			            	  os.write(S);
			                  // os.newLine();
			                   //os.flush();
			       }
			           
			        catch (IOException e) {
			           System.out.println(e);
			           e.printStackTrace();
			       }
			       System.out.println("Sever stopped!");
			   }
		});
		btnStart.setBounds(65, 11, 103, 48);
		frame.getContentPane().add(btnStart);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(254, 11, 103, 48);
		frame.getContentPane().add(btnNewButton);
		
		tfX = new JTextField();
		tfX.setBounds(75, 70, 271, 43);
		frame.getContentPane().add(tfX);
		tfX.setColumns(10);
	}
}
