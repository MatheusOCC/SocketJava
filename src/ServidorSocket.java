import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ServidorSocket {

	public static void main(String[] args) {
		
		ServerSocket servidor = null;
		
		try {
			System.out.println("Inicializando o servidor");
			servidor = new ServerSocket(9999);
			System.out.println("Inicializa��o conclu�da");
		
			while(true) {
				Socket cliente = servidor.accept();
				new GerenciadorClientes(cliente);
			}
			
		}catch (IOException e) {
			try {
				if(servidor != null) {
					servidor.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.err.println("A porta est� ocupada ou o servidor n�o pode ser utilizado");
			e.printStackTrace();
		}
	}

}
