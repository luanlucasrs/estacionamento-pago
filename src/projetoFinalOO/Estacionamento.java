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

	public Veiculo consultarPlaca(String placa) {

		/**
		 * Rotina simplesmente representativa para entender o final do codigo Esta
		 * rotina deve ser alterada para testar se realmente existe e nÃ£o por uma
		 * option pane e checando os arquivos txt
		 * 
		 */
		int valorDoBotao = 0;
		JTextField campoTestePlaca = new JTextField(10);

		JPanel myPanel = new JPanel();
		if (valorDoBotao == 0) {
			myPanel.setLayout(new GridLayout(1, 2));
			myPanel.add(new JLabel("Insira a placa do veículo: "));
			myPanel.add(campoTestePlaca);

			valorDoBotao = JOptionPane.showConfirmDialog(null, myPanel, "Checar placa", JOptionPane.OK_CANCEL_OPTION);

			int sair = 1;

			while (sair != 0) {
				if (valorDoBotao == 2 || valorDoBotao == -1) {
					sair = 0;
				} else if (campoTestePlaca.getText().isEmpty()) {

					valorDoBotao = JOptionPane.showConfirmDialog(null, myPanel, "Checar placa",
							JOptionPane.OK_CANCEL_OPTION);
				} else if (valorDoBotao == 0) {
					sair = 0;
				}

			}

		}

		if (valorDoBotao == 0) {
			String placa2 = campoTestePlaca.getText();
			buscaPlaca(placa2);

			if (buscaPlaca(placa2) != null) { // Se placa existir

				Object[] funcoes = { "Entrada", "Saída" };
				int tipoRegistro = JOptionPane.showOptionDialog(null, "Deseja registrar uma entrada ou saídaa?",
						"Registro", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, funcoes, funcoes[0]);

				if (tipoRegistro == 0) {
					try {
						registrarEntradaVeiculo(buscaPlaca(placa2));
					
					} catch (DadosAcessoIncompletosException e) {
						JOptionPane.showMessageDialog(null, "Data de Entrada incompleta", "Erro", JOptionPane.ERROR_MESSAGE);
					}

				} else if (tipoRegistro == 1) {
					try {
						registrarSaidaVeiculo(buscaPlaca(placa2));
					
					} catch (PeriodoInvalidoException e) {
						JOptionPane.showMessageDialog(null, "Período inválido", "Erro", JOptionPane.ERROR_MESSAGE);
					
					} catch (DadosAcessoIncompletosException e) {
						JOptionPane.showMessageDialog(null, "Data de Saída incompleta", "Erro", JOptionPane.ERROR_MESSAGE);
					}

				}

			} else if (buscaPlaca(placa2) == null) { // Se nÃ£o existir

				Object[] placaNaoExiste = { "Sim", "Não" };
				int testaCadastro = JOptionPane.showOptionDialog(null, "Deseja cadastrar um carro novo?",
						"Checar desejo de cadastro", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
						placaNaoExiste, placaNaoExiste[0]);

				if (testaCadastro == 0) {// Se deseja fazer o cadastro
					try {
						cadastrarVeiculo();

					} catch (DadosVeiculosIncompletosException e) {
						JOptionPane.showMessageDialog(null, "Dados do Veículo estão incompletos.");
					}

				} else if (testaCadastro == 1) {
						
				}

			}

		}

		return null;
	}
	
	
		public boolean cadastrarVeiculo() throws DadosVeiculosIncompletosException {

		/**
		 * Interface bem trabalhada, no entanto ela precisa ser finalizada no momento de
		 * decisÃ£o se existe ou nao CNH Precisa checar nos arquivos txt se existe ou
		 * nao CNH
		 * 
		 */

		// Inicializar
		JTextField campoPlaca = new JTextField(10);
		JTextField campoMarca = new JTextField(10);
		JTextField campoModelo = new JTextField(10);
		JTextField campoMensalista = new JTextField(10);
		JPanel myPanel = new JPanel();

		int valorDoBotao = 0;

		if (valorDoBotao == 0) {
			myPanel.setLayout(new GridLayout(5, 2, 10, 10));
			myPanel.add(new JLabel("Placa: "));
			myPanel.add(campoPlaca);
			myPanel.add(new JLabel("Marca: "));
			myPanel.add(campoMarca);
			myPanel.add(new JLabel("Modelo: "));
			myPanel.add(campoModelo);
			myPanel.add(new JLabel("Vai ser mensalista? (s/n): "));
			myPanel.add(campoMensalista);

			valorDoBotao = JOptionPane.showConfirmDialog(null, myPanel, "Cadastro de Veíulo",
					JOptionPane.OK_CANCEL_OPTION);

			// Validacao

			int sair = 1;

			while (sair != 0) {
				if (valorDoBotao == 2 || valorDoBotao == -1) {
					sair = 0;
				} else if (campoPlaca.getText() == null || campoPlaca.getText().isEmpty() || campoMarca.getText() == null ||
						campoMarca.getText().isEmpty()
						|| campoModelo.getText() == null || campoModelo.getText().isEmpty() || campoMensalista.getText() == null || 
						campoMensalista.getText().isEmpty()) {
					
					throw new DadosVeiculosIncompletosException();
					//valorDoBotao = JOptionPane.showConfirmDialog(null, myPanel, "Cadastro de Veículo",
						//	JOptionPane.OK_CANCEL_OPTION);
					
					
				} else if (valorDoBotao == 0) {
					sair = 0;
					
				}
			}

			if (valorDoBotao == 0) {
				String placa = campoPlaca.getText();
				String marca = campoMarca.getText();
				String modelo = campoModelo.getText();
				String mensalista = campoMensalista.getText();

				boolean mensal = true;
				
				if (mensalista.equals("s")) {
					 mensal = true;
					
					
				} else if (mensalista.equals("n")) {
					 mensal = false;
				
				}
				 
			
				Veiculo veiculo = new Veiculo(placa, marca, modelo, mensal);
				addVeiculo(veiculo);

			

				if (mensal) { /// SIM VAI SER MENSALISTA

					int valorPainel = 0;

					if (valorPainel == 0) { // Checar se CNH Existe -- problema critico

						JTextField campoCnh = new JTextField(10);

						myPanel.add(new JLabel("CNH: "));
						myPanel.add(campoCnh);
						valorPainel = JOptionPane.showConfirmDialog(null, myPanel, "Checa CNH",
								JOptionPane.OK_CANCEL_OPTION);

						int sair1 = 1;

						while (sair1 != 0) {
							if (valorPainel == 2 || valorPainel == -1) {
								sair1 = 0;
							} else if (campoCnh.getText().isEmpty()) {

								valorPainel = JOptionPane.showConfirmDialog(null, myPanel, "Checa CNH",
										JOptionPane.OK_CANCEL_OPTION);
							} else if (valorPainel == 0) {
								sair1 = 0;
							}

							if (valorPainel == 0) {

								int checaCnh = Integer.parseInt(campoCnh.getText());

								if (buscaCNH(checaCnh) != null) { // Se existir algo

									Cliente cliente = buscaCNH(checaCnh);

									vincularVeiculosCliente(cliente, veiculo);

									Object[] blocoRegistro = { "Sim", "Não" };
									int checaRegistro = JOptionPane.showOptionDialog(null,
											"Deseja registrar entrada de carro?", "Registro de Entrada",
											JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
											blocoRegistro, blocoRegistro[0]);

									if (checaRegistro == 0) { // se sim desja

										String message1 = "Você sera direcionado para Registro de Entrada";

										JOptionPane.showMessageDialog(null, message1);

										try {
											registrarEntradaVeiculo(veiculo);
										} catch (DadosAcessoIncompletosException e) {
											JOptionPane.showMessageDialog(null, "Data de Entrada incompleta", "Erro", JOptionPane.ERROR_MESSAGE);
										}

									} else if (checaRegistro == 1) {// e nao deseja

										String message1 = "Cadastro de Veículo feito com sucesso.";

										JOptionPane.showMessageDialog(null, message1);

									}

								} else if (buscaCNH(checaCnh) == null) { // Se não existir nada

									try {
										cadastrarCliente();

									} catch (DadosPessoaisIncompletoException e) {
										JOptionPane.showMessageDialog(null, "Dados pessoais estão incompletos");
									}
								}
							}
						}

					}

				} else if (!mensal) { /// NAO NAO VAI SER MENSALISTA
					
				

					Object[] blocoRegistro = { "Sim", "Não" };
					int checaRegistro = JOptionPane.showOptionDialog(null, "Deseja registrar entrada de carro?",
							"Registro de Entrada", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
							blocoRegistro, blocoRegistro[0]);

					if (checaRegistro == 0) {

						String message = "Cadastro de Veículo feito com sucesso, você sera direcionado para Registro de Entrada";

						JOptionPane.showMessageDialog(null, message);

						try {
							registrarEntradaVeiculo(veiculo);
						
						} catch (DadosAcessoIncompletosException e) {
							JOptionPane.showMessageDialog(null, "Data de Entrada incompleta", "Erro", JOptionPane.ERROR_MESSAGE);
						}

					} else if (checaRegistro == 1) {

						String message = "Cadastro de Veículo feito com sucesso.";

						JOptionPane.showMessageDialog(null, message);

					}
				}

			} // check
		} // check 
		//a

		return false;

	}
	
	
	
	
	// CONTINUA AQUIII
	
	
	
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
