import java.net.*;
import java.io.*;

public class MulticastChatClient {

    public static void main(String args[]) throws Exception {
        int portnumber = 5000;
        if (args.length > 0) {
            portnumber = Integer.parseInt(args[0]);
        }

        MulticastSocket chatMulticastSocket = new MulticastSocket(portnumber);
        InetAddress group = InetAddress.getByName("225.4.5.6");
        chatMulticastSocket.joinGroup(group);

        // Användaren skriver ett mattetal
        System.out.print("Skriv ett mattetal (t.ex. 3 + 4): ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg = br.readLine();

        // Skicka mattetalet
        DatagramPacket data = new DatagramPacket(msg.getBytes(), msg.length(), group, portnumber);
        chatMulticastSocket.send(data);

        // Vänta på svar från servern
        byte[] buffer = new byte[1024];
        DatagramPacket response = new DatagramPacket(buffer, buffer.length);
        chatMulticastSocket.receive(response);

        String responseMsg = new String(response.getData(), 0, response.getLength()).trim();
        System.out.println("Svar från servern: " + responseMsg);

        chatMulticastSocket.close();
    }
}
