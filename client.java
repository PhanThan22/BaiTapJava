package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;
import java.util.Date;

public class client {

	private JFrame frame;
	private JTextField tfN1;
	private JTextField tfN2;
	private JTextField tfX;
	 Socket socketOfClient = null;
     BufferedWriter os = null;
     BufferedReader is = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client window = new client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 478, 201);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tfN1 = new JTextField();
		tfN1.setBounds(130, 29, 107, 20);
		frame.getContentPane().add(tfN1);
		tfN1.setColumns(10);
		
		tfN2 = new JTextField();
		tfN2.setBounds(130, 78, 107, 20);
		frame.getContentPane().add(tfN2);
		tfN2.setColumns(10);
		
		tfX = new JTextField();
		tfX.setBounds(130, 123, 316, 20);
		frame.getContentPane().add(tfX);
		tfX.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("First number");
		lblNewLabel.setBounds(20, 32, 89, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Second");
		lblNewLabel_1.setBounds(20, 81, 62, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Result from server");
		lblNewLabel_2.setBounds(20, 126, 100, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String serverHost = "localhost";
			 
			       try {
			           // Gửi yêu cầu kết nối tới Server đang lắng nghe
			           // trên máy 'localhost' cổng 9999.
			           socketOfClient = new Socket(serverHost, 9999);
			 
			           // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
			           os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
			 
			           // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
			           is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
			           tfX.setText("Connecting");
			 
			       } catch (UnknownHostException ex) {
			           System.err.println("Don't know about host " + serverHost);
			           return;
			       } catch (IOException ex) {
			           tfX.setText("Couldn't get I/O for the connection to " + serverHost);
			           return;
			       }
			}
		});
		btnNewButton.setBounds(258, 28, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Send");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					 DataInputStream inputClient= new DataInputStream(socketOfClient.getInputStream());
					   String a = new String(tfN1.getText());
					   String b = new String(tfN2.getText());
					   String S=inputClient.readLine();
					   os.writeByte(a);;
			           //os.newLine();
			          // os.flush();
			           os.write(b);
			          // os.newLine();
			          // os.flush();
			 
			           // Đọc dữ liệu trả lời từ phía server
			           // Bằng cách đọc luồng đầu vào của Socket tại Client
			            	   tfX.setText(S);			             
			 
			           os.close();
			           is.close();
			           socketOfClient.close();
			       } catch (UnknownHostException ex) {
			           System.err.println("Trying to connect to unknown host: " + e);
			       } catch (IOException ex) {
			           System.err.println("IOException:  " + e);
			       }
				
			}
		});
		
		btnNewButton_1.setBounds(258, 77, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setBounds(357, 28, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
	}
}
