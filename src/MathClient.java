import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MathClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Ansluten till servern.");

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        Scanner scanner = new Scanner(System.in);
        String inputLine;

        while (true) {
            System.out.print("Ange ett uttryck (t.ex. 1+2 eller 'exit' för att avsluta): ");
            inputLine = scanner.nextLine();

            if (inputLine.equalsIgnoreCase("exit")) break;

            out.println(inputLine);
            String response = in.readLine();
            System.out.println("Servern: " + response);
        }

        socket.close();
        System.out.println("Anslutningen avslutad.");
    }
}
