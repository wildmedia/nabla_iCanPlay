/*

Descriere fisier:
Aplicatie Java pentru a conecta un joystick la raspberry si pentru a trimite comenzile prin intermediul conexiunii socket client - server.
Pe raspberry va rula clientul prin aceasta aplicatie.

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

Dezvoltat in cadrul raspberry Hack at Hackathon.ro 20-21 Apr 2013

 */


import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class raspberry {
	
	static SocketClient client = new SocketClient();
	public static void main(String args[]) throws ClassNotFoundException
	{
	Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();

	for(int i = 0; i < ca.length; i++)
		{
		System.out.println("Found input device: " + ca[i].getName());
		}

	// First you need to create controller.
	//sendLive();
	

	client.run();


	//float hatSwitchPosition = joystick.getHatSwitchPosition();
	//float hatSwitchDirection = joystick.getHatSwitchDirection();
	
	
	System.out.println("Disconnected");
	/*
	// Get current state of joystick! And check, if joystick is disconnected.
	if( !joystick.pollController() ) {
	   System.out.println("Controller disconnected!");
	   // Do some stuff.
	}
	
	*/
	
	}
	static void sendLive() {
		JInputJoystick joystick = new JInputJoystick(Controller.Type.STICK, Controller.Type.GAMEPAD);
		// Check if the controller was found.
		//SocketClient client = new SocketClient();

		if( !joystick.isControllerConnected() ){
		   System.out.println("No controller found!");
		   // Do some stuff.
		} 
		else
			System.out.println("This thingie works pretty cool.");
			
		// Let's organize the buttons :)
		int nmbrs = joystick.getNumberOfButtons();
		System.out.println("Buttons: "+nmbrs);

		joystick.getYAxisPercentage();
		while ( joystick.pollController() ) {
			boolean joystickButton_1 = joystick.getButtonValue(0); 
			boolean joystickButton_2 = joystick.getButtonValue(1); 
			boolean joystickButton_3 = joystick.getButtonValue(2); 
			boolean joystickButton_4 = joystick.getButtonValue(3); 
			boolean joystickButton_5 = joystick.getButtonValue(4); 
			boolean joystickButton_6 = joystick.getButtonValue(5); 
			boolean joystickButton_7 = joystick.getButtonValue(6); 
			boolean joystickButton_8 = joystick.getButtonValue(8); 
			boolean joystickButton_9 = joystick.getButtonValue(9);
			boolean joystickButton_10 = joystick.getButtonValue(10);
			
			//System.out.println("Cool: "+joystick.getYAxisPercentage());
			
			
			   //System.out.println("Controller disconnected!");
			   // Do some stuff.

//			if (joystick.getButtonsValues() == null)
	//			System.out.println("1");
			// If variable “joystickButton_1″ is true then button one is pressed.
			if (joystickButton_1) {
				//System.out.println("Button 1?");
				client.sendMessage("1");
				}
			if (joystickButton_2) {
				//System.out.println("Button 1?");
				client.sendMessage("2");
				}
			if (joystickButton_3) {
				//System.out.println("Button 1?");
				client.sendMessage("3");
				}
			if (joystickButton_4) {
				//System.out.println("Button 1?");
				client.sendMessage("4");
				}			
			if (joystickButton_5) {
				//System.out.println("Button 1?");
				client.sendMessage("5");
				}			
			if (joystickButton_6) {
				//System.out.println("Button 1?");
				client.sendMessage("6");
				}			
			if (joystickButton_7) {
				//System.out.println("Button 1?");
				client.sendMessage("7");
				}			
			if (joystickButton_8) {
				//System.out.println("Button 1?");
				client.sendMessage("8");
				}			
			if (joystickButton_9) {
				//System.out.println("Button 1?");
				client.sendMessage("9");
				}			
			if (joystickButton_10) {
				//System.out.println("Button 1?");
				client.sendMessage("10");
				}			
				
			int pos = joystick.getXAxisPercentage();

			if (pos == 0) { // 50 center, 0 right 100 left
				//System.out.println("left");
				client.sendMessage("Go right.");
			}
				
			if (pos == 100) { // 50 center, 0 right 100 left 
				//System.out.println("right");				
				client.sendMessage("Go left.");
			}
			int pos1 = joystick.getYAxisPercentage();

			if (pos1 == 0) { // 50 center, 0 right 100 left
				//System.out.println("left");
				client.sendMessage("Go up.");
			}
				
			if (pos1 == 100) { // 50 center, 0 right 100 left 
				//System.out.println("right");				
				client.sendMessage("Go down.");
			}
				
		     try {
	             Thread.sleep(20);
	         } catch (InterruptedException e) {
	             e.printStackTrace();
	         }
			}
		}
	
}
	


