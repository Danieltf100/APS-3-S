
public class Pessoa {
	
	private String nome,
					 rg;
	private int idade;
	
	public Pessoa(String nome, String rg, int idade) throws Exception{
		this.setNome(nome);
		this.setIdade(idade);
		this.setRg(rg);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) throws Exception {
		if(rg.length()!=9) throw new ExceptionRG("Este formato de RG não existe.");
		this.rg = rg;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
}
