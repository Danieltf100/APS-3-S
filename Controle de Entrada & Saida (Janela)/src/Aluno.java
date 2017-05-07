
public class Aluno extends Pessoa {
	
	private int idAluno;
	
	public Aluno(String nome, String rg, int idade, int idAluno) throws Exception {
		super(nome, rg, idade);
		this.setIdAluno(idAluno);
	}

	public int getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
	
}
