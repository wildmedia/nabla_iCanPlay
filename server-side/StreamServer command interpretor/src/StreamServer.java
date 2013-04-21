import java.io.*;
import java.net.*;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
public class StreamServer{
	ServerSocket providerSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	StreamServer(){}
	void run()
	{
		try{
			
			providerSocket = new ServerSocket(2025, 10);
			//Wait for connection
			System.out.println("Waiting for connection");
			connection = providerSocket.accept();
			System.out.println("Connection received from " + connection.getInetAddress().getHostName());
			//Input and Output streams
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			sendMessage("Connection successful");
			
			do{
				try{
					message = (String)in.readObject();
					
					//Here comes the robots that will interpret
					
					//Pointer for mouse position
					PointerInfo a = MouseInfo.getPointerInfo();
					Point b = a.getLocation();
					int mx = (int) b.getX();
					int my = (int) b.getY();
					
							try {
								Robot robot = new Robot();
								
								if(message.equals("1")){
									//robot.keyPress(KeyEvent.VK_X);
									//robot.keyRelease(KeyEvent.VK_X);
									robot.mousePress(InputEvent.BUTTON1_MASK);
									robot.mouseRelease(InputEvent.BUTTON1_MASK);
									
								}
								////////////Mouse movement/////////////////
								if(message.equals("up")){
									robot.mouseMove(mx , my + 6);
								}
								if(message.equals("down")){
									robot.mouseMove(mx , my - 6);
								}
								if(message.equals("left")){
									robot.mouseMove(mx - 6 , my);
								}
								if(message.equals("right")){
									robot.mouseMove(mx + 6 , my);
								}
								/////////End of Mouse Movement//////////////
								if(message.equals("up2")){
									robot.keyPress(KeyEvent.VK_W);
									robot.keyRelease(KeyEvent.VK_W);
								}
								if(message.equals("down2")){
									robot.keyPress(KeyEvent.VK_S);
									robot.keyRelease(KeyEvent.VK_S);
								}
								if(message.equals("left2")){
									robot.keyPress(KeyEvent.VK_A);
									robot.keyRelease(KeyEvent.VK_A);
								}
								if(message.equals("right2")){
									robot.keyPress(KeyEvent.VK_D);
									robot.keyRelease(KeyEvent.VK_D);
								}
								//Left Click
								if(message.equals("10")){
									robot.mousePress(InputEvent.BUTTON1_MASK);
									robot.mouseRelease(InputEvent.BUTTON1_MASK);
								}
								//Left Click from AS1
								if(message.equals("3")){
									robot.keyPress(KeyEvent.VK_E);
									robot.keyRelease(KeyEvent.VK_E);
								}
								if(message.equals("4")){
									robot.keyPress(KeyEvent.VK_T);
									robot.keyRelease(KeyEvent.VK_T);
								}
								
								;
							} catch (AWTException e) {
								e.printStackTrace();
							}
				
					
					//End of robots
					
					System.out.println("client>" + message);
					if (message.equals("bye"))
						sendMessage("bye");
				}
				catch(ClassNotFoundException classnot){
					System.err.println("Data received in unknown format");
				}
			}while(!message.equals("bye"));
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//End of connection
			try{
				in.close();
				out.close();
				providerSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		StreamServer server = new StreamServer();
		while(true){
			server.run();
		}
	}
}