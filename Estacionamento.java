package estacionamento.principal;
public class Estacionamento {

	private List<Cliente> clientes; 
	private List<Cliente> veiculosNaoMensalista;
	private List<ControleGaragem> garagem;
	private Cobranca cobranca; 
	
	
	public Estacionamento () {
		clientes = new ArrayList<Cliente>();
		veiculosNaoMensalista = new ArrayList<Veiculo>();
		garagem = new ArrayList<ControleGaragem>();
		cobranca = new Cobranca();
		
	}
	
	public static void main(String[] args) throws DadosPessoaisIncompletosException, DadosVeiculosIncompletosException,  
	EstacionamentoFechadoException, PeriodoInvalidoException{
		
		Estacionamento estacionamento = new Estacionamento();
		
		if (!estacionamento.cadastrarCleint(null)) {
			throw new DadosPessoaisIncompletosException();
		}
		if (!estacionamento.cadastrarVeiculo(null)) {
			throw new DadosVeiculosIncompletosException();
		}
		if(!estacionamento.registrarEntradaVeiculo(null)) {
			throw new EstationamentoFechadoException();
		}
		if(!estacionamento.registrarSaidaVeiculo(null)) {
			throw new PeriodoInvalidoException();
		}
		
	}
	
	
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
