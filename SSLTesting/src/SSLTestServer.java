
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SSLTestServer {

	private static final int port = 443;
	
	public static void main(String[] args) {
		
		SSLServerSocketFactory sslSockFactory = 
				(SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
		
		try {
			
			ServerSocket sslServerSocket = 
					sslSockFactory.createServerSocket(port);
			System.out.println("SSL ServerSocket started");
			System.out.println(sslServerSocket.toString());
			
			Socket socket = sslServerSocket.accept();
			System.out.println("ServerSocket accepted");
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			try (BufferedReader bufferedReader = 
					new BufferedReader(
							new InputStreamReader(
									socket.getInputStream()))) {
				
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
					out.println(line);
				}
				
			}
			System.out.println("Closed");
			
		} catch (IOException e) {
//			Logger.getLogger(JavaSSLServer.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		
	}

}
