import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class ClienteSocket {

	private static final String IP = "127.0.0.1";
	private static final int port = 9999;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			final Socket cliente = new Socket (IP, port);		
			
			//lendo mensagem do servidor
			new Thread() {
				@Override
				public void run() {
					try{
						BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));	
						while(true) {
							String mensagem =  leitor.readLine();
							System.out.println("A mensagem do servidor foi:" + mensagem);
						}
					}catch (IOException e) {
						System.out.println("Não foi possível ler a mensagem");
						e.printStackTrace();
					}
				}
			}.start();
			
			//escrevendo mensagem para o servidor			
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(),true);
			BufferedReader leitorTerminal  = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				String mensagemTerminal = leitorTerminal.readLine();
				escritor.println(mensagemTerminal);
			}
			
		}catch (UnknownHostException e){
			System.out.println("Endereço inválido");
			e.printStackTrace();
		}catch (IOException e){
			System.out.println("O servidor está indisponível");
			e.printStackTrace();
		}
	}
}
