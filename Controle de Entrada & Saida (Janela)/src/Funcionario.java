
public class Funcionario extends Pessoa {

	private int idFuncionario;
	
	public Funcionario(String nome, String rg, int idade, int idFuncionario) throws Exception{
		super(nome,rg,idade);
		this.idFuncionario = idFuncionario;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
}
