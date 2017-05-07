import java.util.Vector;
import java.util.GregorianCalendar;

public class Catraca {

	private String log;
	private Vector<Pessoa> enter, out;
	private BancoDeInfo info;
	
	public Catraca(){
		log = "";
		enter = new Vector<Pessoa>();
		out = new Vector<Pessoa>();
		info = new BancoDeInfo();
	}
	
	public void setEntrada(int id) throws ExceptionCatraca{
		int i=0;
		if (!info.validaId(id)) throw new ExceptionCatraca("O ID digitado não existe.");
		for(i=0;i<info.getCadastrados().size();i++){
			Pessoa p = info.getCadastrados().get(i);
			if(p instanceof Funcionario){ 
				if( ((Funcionario)p).getIdFuncionario()==id ){
					if(!enter.contains(p)){
						enter.add(p);
						out.remove(p);
						GregorianCalendar gc = new GregorianCalendar();
						log += "["+gc.getTime().toString()+"] "+p.getNome()+" ID: "+id+" entrou\n";
						break;
					}
					else throw new ExceptionCatraca("O Funcionario "+p.getNome()+" ID: "+id+" ja entrou.");
				}
			}
			if(p instanceof Professor){
				if( ((Professor)p).getIdProfessor()==id ){
					if(!enter.contains(p)){
						enter.add(p);
						out.remove(p);
						GregorianCalendar gc = new GregorianCalendar();
						log += "["+gc.getTime().toString()+"] "+p.getNome()+" ID: "+id+" entrou\n";
						break;
					}
					else throw new ExceptionCatraca("O Professor "+p.getNome()+" ID: "+id+" ja entrou.");
				}
			}
			if(p instanceof Aluno){
				if( ((Aluno)p).getIdAluno()==id ){
					if(!enter.contains(p)){
						enter.add(p);
						out.remove(p);
						GregorianCalendar gc = new GregorianCalendar();
						log += "["+gc.getTime().toString()+"] "+p.getNome()+" ID: "+id+" entrou\n";
						break;
					}
					else throw new ExceptionCatraca("O Aluno "+p.getNome()+" ID: "+id+" ja entrou.");
				}
			}
		}
	}
	
	public void setSaida(int id) throws ExceptionCatraca{
		int i=0;
		if(!info.validaId(id)) throw new ExceptionCatraca("O ID digitado não existe.");
		for(i=0;i<info.getCadastrados().size();i++){
			Pessoa p = info.getCadastrados().get(i);
			if(p instanceof Funcionario){ 
				if( ((Funcionario)p).getIdFuncionario()==id ){
					if(!out.contains(p)){
						enter.remove(p);
						out.add(p);
						GregorianCalendar gc = new GregorianCalendar();
						log += "["+gc.getTime().toString()+"] "+p.getNome()+" ID: "+id+" saiu\n";
						break;
					}
					else throw new ExceptionCatraca("O Funcionario "+p.getNome()+" ID: "+id+" ja saiu.");
				}
			}
			if(p instanceof Professor){
				if( ((Professor)p).getIdProfessor()==id ){
					if(!out.contains(p)){
						enter.remove(p);
						out.add(p);
						GregorianCalendar gc = new GregorianCalendar();
						log += "["+gc.getTime().toString()+"] "+p.getNome()+" ID: "+id+" saiu\n";
						break;
					}
					else throw new ExceptionCatraca("O Professor "+p.getNome()+" ID: "+id+" ja saiu.");
				}
			}
			if(p instanceof Aluno){
				if( ((Aluno)p).getIdAluno()==id ){
					if(!out.contains(p)){
						enter.remove(p);
						out.add(p);
						GregorianCalendar gc = new GregorianCalendar();
						log += "["+gc.getTime().toString()+"] "+p.getNome()+" ID: "+id+" saiu\n";
						break;
					}
					else throw new ExceptionCatraca("O Aluno "+p.getNome()+" ID: "+id+" ja saiu.");
				}
			}
		}
	}

	public Vector<Pessoa> getEnter() {
		return enter;
	}

	public Vector<Pessoa> getOut() {
		// atualiza lista de pessoas fora do recinto cadastradas...
		Vector<Pessoa> pessoas = this.getEnter();
		Vector<Pessoa> cadastrados = this.info.getCadastrados();
		for(int i=0;i<cadastrados.size();i++){
			if(!pessoas.contains(cadastrados.get(i)) && !out.contains(cadastrados.get(i))){
				if(cadastrados.get(i) instanceof Professor){
					boolean y = false;
					for(int j=0;j<out.size();j++){
						if(out.get(j) instanceof Professor){
							if(((Professor)out.get(i)).getIdFuncionario()==((Professor)cadastrados.get(i)).getIdFuncionario()) y=true;
						}
					}
					if(!y) out.add(cadastrados.get(i));
				}
				else out.add(cadastrados.get(i)); 
			}
		}
		return out;
	}

	public BancoDeInfo getInfo() {
		return info;
	}

	public String getLog() {
		return log;
	}

}
