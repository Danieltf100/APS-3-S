import java.util.Vector;

public class BancoDeInfo {
	
	private Vector<Pessoa> cadastrados;
	
	public BancoDeInfo(){
		this.cadastrados = new Vector<Pessoa>();
	}
	
	public boolean validaId(int id){
		for(int i=0;i<cadastrados.size();i++){
			if(cadastrados.get(i) instanceof Funcionario){
				if(((Funcionario)cadastrados.get(i)).getIdFuncionario() == id) return true;
			}
			if(cadastrados.get(i) instanceof Professor){
				if(((Professor)cadastrados.get(i)).getIdProfessor() == id) return true;
			}
			if(cadastrados.get(i) instanceof Aluno){
				if(((Aluno)cadastrados.get(i)).getIdAluno() == id) return true;
			}
		}
		return false;
	}
	
	private void validaRG(Pessoa x)throws ExceptionRG{
		for(int i=0;i<cadastrados.size();i++){
			if(cadastrados.get(i).getRg().compareTo(x.getRg())==0) throw new ExceptionRG("RG já está cadastrado.");
		}
	}
	
	public Pessoa getPessoaWithId(int id){
		for(int i=0;i<cadastrados.size();i++){
			if(cadastrados.get(i) instanceof Aluno) if(((Aluno)cadastrados.get(i)).getIdAluno()==id) return cadastrados.get(i);
			if(cadastrados.get(i) instanceof Funcionario) if(((Funcionario)cadastrados.get(i)).getIdFuncionario()==id) return cadastrados.get(i);
			if(cadastrados.get(i) instanceof Professor) if(((Professor)cadastrados.get(i)).getIdProfessor()==id) return cadastrados.get(i);
		}
		return null;
	}
	
	public void setCadastrarFuncionario(Pessoa novo, int id) throws Exception{
		validaRG(novo);
		if( !validaId(id) && !this.cadastrados.contains( new Funcionario(novo.getNome(),novo.getRg(),novo.getIdade(),id) ) ) 
			this.cadastrados.add( new Funcionario(novo.getNome(),novo.getRg(),novo.getIdade(),id) );
		
		else throw new ExceptionCadastro("O Funcionario com este id já está cadastrado ou ouve um choque de IDs existentes.");
	}
	
	public void setCadastrarProfessor(Funcionario novo, int idNovo) throws Exception{
		validaRG(novo);
		if( this.cadastrados.contains(novo) && !validaId(idNovo) &&
			!this.cadastrados.contains(new Professor(novo.getNome(),novo.getRg(),novo.getIdade(),novo.getIdFuncionario(),idNovo))
		){
			this.cadastrados.setElementAt(
					new Professor(novo.getNome(),novo.getRg(),novo.getIdade(),novo.getIdFuncionario(),idNovo), 
					this.cadastrados.indexOf(novo)
			);
		}
		else throw new ExceptionCadastro("Erro: O Funcionario não existe ou o Professor já está cadastrado ou ocorreu choque de IDs existentes.");
	}
	
	public void setCadastrarAluno(Pessoa novo, int id) throws Exception{
		validaRG(novo);
		if(!validaId(id) && !this.cadastrados.contains(new Aluno(novo.getNome(),novo.getRg(),novo.getIdade(),id))){
			this.cadastrados.add(new Aluno(novo.getNome(),novo.getRg(),novo.getIdade(),id));
		}
		else throw new ExceptionCadastro("O aluno com este id já está cadastrado ou ouve um choque de IDs existentes.");
	}
	
	public Vector<Pessoa> getCadastrados(){
		return this.cadastrados;
	}
	
	public Vector<Funcionario> getAllFuncionarios(){
		Vector<Funcionario> r = new Vector<Funcionario>();
		for(int i=0;i<this.cadastrados.size();i++){
			if(this.cadastrados.get(i) instanceof Funcionario) r.add((Funcionario) this.cadastrados.get(i));
		}
		return r;
	}
	
	public boolean setExcluirAluno(int id){
		Pessoa p = this.getPessoaWithId(id);
		if(p instanceof Aluno){
			return this.cadastrados.remove(p);
		}
		return false;
	}
	
	public boolean setExcluirFuncionario(int id){
		Pessoa p = this.getPessoaWithId(id);
		if(p instanceof Funcionario){
			return this.cadastrados.remove(p);
		}
		return false;
	}
	
	public boolean setExcluirProfessor(int id){
		Pessoa p = this.getPessoaWithId(id);
		if(p instanceof Professor){
			return this.cadastrados.remove(p);
		}
		return false;
	}
	
	public Vector<Professor> getAllProfessores(){
		Vector<Professor> r = new Vector<Professor>();
		for(int i=0;i<this.cadastrados.size();i++){
			if(this.cadastrados.get(i) instanceof Professor) r.add((Professor) this.cadastrados.get(i));
		}
		return r;
	}
	
	public Vector<Aluno> getAllAlunos(){
		Vector<Aluno> r = new Vector<Aluno>();
		for(int i=0;i<this.cadastrados.size();i++){
			if(this.cadastrados.get(i) instanceof Aluno) r.add((Aluno) this.cadastrados.get(i));
		}
		return r;
	}
	
}
