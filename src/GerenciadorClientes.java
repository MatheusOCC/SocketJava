import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GerenciadorClientes extends Thread{

	private Socket cliente;
	private String nomeCliente;
	
	public GerenciadorClientes(Socket cliente) {
		// TODO Auto-generated constructor stub
		this.cliente = cliente;
		start();
	}
	public void run() {
		try {
			
			BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
			escritor.println("Qual o seu nome?");
			String mensagem = leitor.readLine();
			this.nomeCliente = mensagem;
			
			while(true) {
				mensagem = leitor.readLine();
				escritor.println(this.nomeCliente + "Sua mensagem foi: " + mensagem);
			}
			
		}catch(IOException e) {
			System.err.println("Conexão encerrada");
			e.printStackTrace();
		}
	}
}
