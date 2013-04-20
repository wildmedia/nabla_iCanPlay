/*
Nume proiect:
iCanPlay, Thin Gaming Client

Categorie:
Best Software

Componenta echipa:
Alin Diaconu <mihaialin@wildmedia.ro>
Cristian Carp <cristian.carp@wildmedia.ro>
Sorin Dumitrescu <sorin@qoffice.ro>

Scurta descriere:
iCanPlay reprezinta o platforma de 'cloud-gaming' care permite oricarui utilizator de raspberry sa se bucure de jocuri si videoclipuri HD cu ajutorul unei platforme hardware configurate cu un raspberryPI si un joystick.

Despre echipa:
Studenti si pasionati de antreprenoriat si tehnologie. Am dezvoltat proiecte precum Wild Media Romania (wildmedia.ro) - reteaua Wild Media, una din cele mai mari retele WiFi din Romania, NEXTads Romania (blog.nextads.ro) - prima aplicatie de social media affiliate marketing din Romania cu peste 10 000 de afiliati in prima luna, qOffice (www.qoffice.ro), suita de aplicatii EducationPRO si suntem activi intr-o serie de proiecte de importanta nationala si internationala.

Ne bucuram de oportunitatea care ne-a fost oferita pentru a putea dezvolta software impreuna cu oameni talentati si pasionati si le multumim in acest mod tuturor organizatorilor si colaboratorilor in cadrul acestui eveniment.

 */

import java.io.*;
import java.net.*;
public class SocketClient{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
	SocketClient(){}
	void run()
	{
		try{
			//1. creating a socket to connect to the server
			requestSocket = new Socket("192.168.0.103", 2004);
			System.out.println("Connected to localhost in port 2004");
			//2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			//3: Communicating with the server
			do{
				try{
					message = (String)in.readObject();
					System.out.println("server>" + message);
					sendMessage("Hi my server");
					//message = "bye";
					//sendMessage(message);
					message = "next";
					sendMessage(message);
					raspberry Joystick = new raspberry();
					Joystick.sendLive();
				}
				catch(ClassNotFoundException classNot){
					System.err.println("data received in unknown format");
				}
			}while(!message.equals("bye"));
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				requestSocket.close();
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
			System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
/*	public static void main(String args[])
	{
		SocketClient client = new SocketClient();
		client.run();
	}*/
}