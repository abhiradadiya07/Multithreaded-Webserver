import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public void run() throws UnknownHostException, IOException {
        int port = 8090;
        
        InetAddress address = InetAddress.getByName("localhost");
        System.out.println(address);
        
        Socket socket = new Socket(address, port);

        PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
        
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Sending a properly formatted HTTP GET request
        toSocket.println("GET / HTTP/1.1");
        toSocket.println("Host: localhost:" + port);
        toSocket.println("Connection: Close");
        toSocket.println(); // Blank line to indicate end of the HTTP request header

        // Read the response from the server
        String line;
        while ((line = fromSocket.readLine()) != null) {
            System.out.println("Response from Socket is: " + line);
        }

        toSocket.close();
        fromSocket.close();
        socket.close();

    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.run();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
