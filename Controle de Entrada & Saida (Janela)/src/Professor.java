
public class Professor extends Funcionario {
	
	private int idProfessor;
	
	public Professor(String nome, String rg, int idade, int idFuncionario, int idProfessor) throws Exception {
		super(nome, rg, idade, idFuncionario);
		this.setIdProfessor(idProfessor);
	}

	public int getIdProfessor() {
		return idProfessor;
	}

	public void setIdProfessor(int idProfessor) {
		this.idProfessor = idProfessor;
	}
	
	
	
}
