package projetoFinalOO;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.List;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;

public class Estacionamento {
	
	private Cobranca cobranca;

	public List<Veiculo> veiculos = new ArrayList<Veiculo>();
	public List<Veiculo> veiculosMensalistas = new ArrayList<Veiculo>();
	public List<Cliente> clientes = new ArrayList<Cliente>();
	public List<ControleGaragem> listaData = new ArrayList<ControleGaragem>();

	public Estacionamento() {
		clientes = new ArrayList<Cliente>();
		cobranca = new Cobranca();

	}

	public static void main(String[] args) {

		Estacionamento estacionamento = new Estacionamento();
		
		try {
			if (estacionamento.isAberto()) {
				// Função para rodar o menu
				estacionamento.rodarMenu();
			}
		
		} catch (EstacionamentoFechadoException e) {
			JOptionPane.showMessageDialog(null, "Estacionamento está fechado.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * SequÃªncia de cÃ³digo para criaÃ§Ã£o do menu de visualizaÃ§Ã£o da interface
	 *
	 * Funcionamento 1) Int menu: primeira tela 2) void rodarMenu: funcionamento do
	 * menu 3) rodarMenu: chamada na main
	 * 
	 */

	public int menu() {
		// variavel para botao
		int valorDoBotao = 0;

		// Alocar botÃµes
		Object[] funcoes = { "Iniciar serviço", };
		int funcao = JOptionPane.showOptionDialog(null, "Bem-vindos ao serviço EstacioneX", "O que deseja fazer?",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, funcoes, funcoes[0]);

		// Consultar Placa
		if (funcao == 0) {
			consultarPlaca(null); // My: /eu nÃ£o achei esse mÃ©todo no seu codigo aqui, mas adicionar dentro dele
									// o mÃ©todo BuscaPlaca

			/**
			 * Passo-a-passo dessa funÃ§Ã£o
			 * 
			 * 1) Insere Placa 2) Cadastrada? (s/n)
			 * 
			 * 2.1)Caso sim: Registrar entrada ou saida? Caso entrada
			 * registrarEntradaVeiculo() Caso saida registrarSaidaVeiculo() 2.2)Caso nÃ£o:
			 * Cadastrar veiculo novo?(s/n) Caso sim cadastrarVeiculo() Caso nÃ£o Encerra
			 * 
			 */

		}

		// Cadastrar Veiculo
		// if (funcao == 1) {
		// cadastrarVeiculo();

		/**
		 * Passo-a-passo dessa funÃ§Ã£o
		 * 
		 * 1) Dados Veiculo 2) Ele Ã© mensalista? (s/n)
		 * 
		 * 2.1) Caso sim a) Insira CNH (existe/ou n) a.1)Caso exista
		 * vinculcarVeiculoCliente(); a.2) Caso nao exista cadastrarCliente(); 2.2) Caso
		 * nÃ£o Deseja registrar entrada? (s/n) Caso sim registrarEntradaVeiculo() Caso
		 * nao sair
		 *
		 */

		// }

		// Cancelar operaÃ§Ã£o
		else if (funcao == -1) {
			valorDoBotao = -1;
		}

		return valorDoBotao;
	}

	/**
	 * FunÃ§Ã£o responsÃ¡vel pelo funcionamento do menu atÃ© que a interface seja
	 * fechada
	 */
	public void rodarMenu() {
		int condicao = menu();
		while (condicao >= 0) {
			condicao = menu();
		}

	}
	
public boolean isAberto() throws EstacionamentoFechadoException {
		
		Calendar dataAtual = Calendar.getInstance();

		String message = "Digite a hora atual para teste no formato dd/MM/yyyy hh:mm";
		String dataInformada = JOptionPane.showInputDialog(message);

		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Date date = new Date();
		try {
			date = dt.parse(dataInformada);

		} catch (Exception e) {
		}
		
		dataAtual.setTime(date);
		
		Calendar dataAbertura = Calendar.getInstance();
		dataAbertura.set(Calendar.HOUR_OF_DAY, 5);
		dataAbertura.set(Calendar.MINUTE, 59);
		
		Calendar dataFechamento = Calendar.getInstance();
		dataFechamento.set(Calendar.HOUR_OF_DAY, 20);
		dataFechamento.set(Calendar.MINUTE, 01);
		
		if (dataAtual.before(dataAbertura) || dataAtual.after(dataFechamento)) {
			throw new EstacionamentoFechadoException();
		}
		
		return true;
	}

	/**
	 * Sequencia de chamada de funções predeterminadas na UML
	 * 
	 */

	
	public Veiculo consultarPlaca (String placa) {
		return new Veiculo; 
	}
	
	public boolean cadastrarVeiculo (Veiculo veiculo) {
		return false;
	}
	
	public boolean cadastrarCliente (Cliente cliente) {
		return false; 
	}
	
	public boolean vincularVeiculosCliente (Cliente, cliente, Veiculo veiculo) {
		return false;
	}
	
	public boolean registrarEntradaVeiculo(Veiculo veiculo) {
		return false; 
	}
	
	public boolean registrarSaidaVeiculo(Veiculo veiculo) {
		return false; 
	}
	
	public boolean cobrar(ControleGaragem controle) {
		return 0.0;
	}
}
