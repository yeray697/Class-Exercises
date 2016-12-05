import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClientExampleTest {
	public static void main(String[] args) {
		try {
			new Cliente();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class Cliente{
	static final String HOST = "localhost";
	static final int PUERTO = 5000;
	public Cliente() throws IOException {	
		Socket skCLI = new Socket(HOST, PUERTO);
		InputStreamReader iStreamReader = new InputStreamReader(skCLI.getInputStream());
		BufferedReader bReader = new BufferedReader(iStreamReader);
		System.out.println(bReader.readLine());;
		skCLI.close();
	}
}
