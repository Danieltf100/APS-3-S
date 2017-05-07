import java.awt.GraphicsEnvironment;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Janela extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Catraca cat;
	
	GregorianCalendar gc;
	private TextArea log;
	private String cabTextLog;
	
	private JMenu catraca;
	private JMenuItem entrar;
	private JMenuItem sair;
	
	private JMenu cadastro;
	private JMenuItem aluno;
	private JMenuItem funcionario;
	private JMenuItem professor;
	private JMenuItem dentro;
	private JMenuItem fora;
	
	private JMenu excluir;
	private JMenuItem excluirAluno,
					  excluirProfessor,
					  excluirFuncionario;
	
	private JMenu procurar;
	private JMenuItem procurarAluno,
					  procurarProfessor,
					  procurarFuncionario;
	
	public Janela(){
		super("Controle de Entrada e Saída");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// o programa em si...
		cat = new Catraca();
		
		// Barra de menu principal
		JMenuBar barraPrincipal = new JMenuBar();
		
		catraca = new JMenu("Catraca");
		entrar = new JMenuItem("Entrar");
		sair = new JMenuItem("Sair");
		dentro = new JMenuItem("Dentro do Recinto");
		fora = new JMenuItem("Fora do Recinto");
		
		cadastro = new JMenu("Cadastro");
		aluno = new JMenuItem("Aluno");
		funcionario = new JMenuItem("Funcionario");
		professor = new JMenuItem("Professor");
		
		excluir = new JMenu("Excluir");
		excluirAluno = new JMenuItem("Aluno");
		excluirFuncionario = new JMenuItem("Funcionario");
		excluirProfessor = new JMenuItem("Professor");
		
		procurar = new JMenu("Procurar");
		procurarAluno = new JMenuItem("Aluno");
		procurarFuncionario = new JMenuItem("Funcionario");
		procurarProfessor = new JMenuItem("Professor");
		
		catraca.add(entrar);
		catraca.add(sair);
		catraca.add(dentro);
		catraca.add(fora);
		cadastro.add(aluno);
		cadastro.add(funcionario);
		cadastro.add(professor);
		excluir.add(excluirAluno);
		excluir.add(excluirFuncionario);
		excluir.add(excluirProfessor);
		procurar.add(procurarAluno);
		procurar.add(procurarFuncionario);
		procurar.add(procurarProfessor);
		barraPrincipal.add(catraca);
		barraPrincipal.add(cadastro);
		barraPrincipal.add(excluir);
		barraPrincipal.add(procurar);
		barraPrincipal.setVisible(true);
		this.setJMenuBar(barraPrincipal);

		// adicionando ações aos botões
		entrar.addActionListener(new Entrar());
		sair.addActionListener(new Sair());
		dentro.addActionListener(new ShowDentro());
		fora.addActionListener(new ShowFora());
		aluno.addActionListener(new CadastraAluno());
		funcionario.addActionListener(new CadastraFuncionario());
		professor.addActionListener(new CadastraProfessor());
		excluirAluno.addActionListener(new ExcluirAluno());
		excluirFuncionario.addActionListener(new ExcluirFuncionario());
		excluirProfessor.addActionListener(new ExcluirProfessor());
		procurarAluno.addActionListener(new ProcurarAluno());
		procurarFuncionario.addActionListener(new ProcurarFuncionario());
		procurarProfessor.addActionListener(new ProcurarProfessor());
		
		// Log de Entrada e Saida
		log = new TextArea(300,300);
		log.setEditable(false);
		gc = new GregorianCalendar(); // Mostrar horario de inicio de funcionamento...
		cabTextLog = "["+gc.getTime().toString()+"] Inicio de Funcionamento.\n";
		log.setText(cabTextLog);
		this.add(log);
		
		this.setLocationRelativeTo(null);
		
		this.setSize(400,400);
		this.setVisible(true);
	}
	
	private class Entrar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String id = JOptionPane.showInputDialog(null, "Insira o ID:", "Entrada",JOptionPane.QUESTION_MESSAGE);
			if(id!=null){
				try {
					cat.setEntrada(Integer.parseInt(id));
					log.setText(cabTextLog+cat.getLog());
				}catch (ExceptionCatraca e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro de ID", JOptionPane.ERROR_MESSAGE);
				}catch (NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "O ID digitado não é valido.\n"+e2.getMessage(), "Erro de ID", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	private class Sair implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String id = JOptionPane.showInputDialog(null, "Insira o ID:", "Saida",JOptionPane.QUESTION_MESSAGE);
			if(id!=null){
				try {
					cat.setSaida(Integer.parseInt(id));
					log.setText(cabTextLog+cat.getLog());
				}catch (ExceptionCatraca e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro de ID", JOptionPane.ERROR_MESSAGE);
				}catch (NumberFormatException e2){
					JOptionPane.showMessageDialog(null, "O ID digitado não é valido.\n"+e2.getMessage(), "Erro de ID", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	private class ShowDentro implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Vector<Pessoa> pessoas = cat.getEnter();
			String show = "";
			for(int i=0;i<pessoas.size();i++){
				show+=("Nome: "+pessoas.get(i).getNome());
				if(pessoas.get(i) instanceof Aluno) show+=(" ID: "+((Aluno)pessoas.get(i)).getIdAluno()+"\n");
				else if(pessoas.get(i) instanceof Professor) show+=(" ID: "+((Professor)pessoas.get(i)).getIdProfessor()+"\n");
				else show+=(" ID: "+((Funcionario)pessoas.get(i)).getIdFuncionario()+"\n");
			}
			if(show.compareTo("")==0) show = "Não há ninguem dentro do recinto.";
			JOptionPane.showMessageDialog(null, show,"Dentro do Recinto",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private class ShowFora implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Vector<Pessoa> pessoas = cat.getOut();
			String show = "";
			for(int i=0;i<pessoas.size();i++){
				show+=("Nome: "+pessoas.get(i).getNome());
				if(pessoas.get(i) instanceof Aluno) show+=(" ID: "+((Aluno)pessoas.get(i)).getIdAluno()+"\n");
				else if(pessoas.get(i) instanceof Professor) show+=(" ID: "+((Professor)pessoas.get(i)).getIdProfessor()+"\n");
				else show+=(" ID: "+((Funcionario)pessoas.get(i)).getIdFuncionario()+"\n");
			}
			if(show.compareTo("")==0) show = "Não há ninguem fora do recinto.";
			JOptionPane.showMessageDialog(null, show,"Fora do Recinto",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private Pessoa newPessoa(String tipo){
		JPanel p = new JPanel();
		if(tipo.compareTo("Professor")==0){
			JLabel idFuncionario = new JLabel("ID do funcionario");
			JTextField tIdFuncionario = new JTextField(9);
			JLabel idProfessor = new JLabel("novo ID para professor");
			JTextField tIdProfessor = new JTextField(9);
			p.add(idFuncionario);
			p.add(tIdFuncionario);
			p.add(idProfessor);
			p.add(tIdProfessor);
			JOptionPane.showMessageDialog(null, p, "Cadastro de "+tipo, JOptionPane.QUESTION_MESSAGE);
			if(tIdFuncionario.getText()==null || tIdFuncionario.getText().compareTo("")==0 || 
			   tIdProfessor.getText()==null || tIdProfessor.getText().compareTo("")==0){
				JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos.", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
			}
			else{
				try{
					Funcionario f = (Funcionario)cat.getInfo().getPessoaWithId(Integer.parseInt(tIdFuncionario.getText()));
					if(f!=null){
						return new Professor(f.getNome(),f.getRg(),f.getIdade(),f.getIdFuncionario(),Integer.parseInt(tIdProfessor.getText()));	
					}
					return null;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if(tipo.compareTo("Aluno")==0 || tipo.compareTo("Funcionario")==0){
			JLabel nome = new JLabel("Nome");
			JTextField tNome = new JTextField(20);
			JLabel rg = new JLabel("RG");
			JTextField tRg = new JTextField(9);
			JLabel idade = new JLabel("Idade");
			JTextField tIdade = new JTextField(3);
			JLabel id = new JLabel("ID");
			JTextField tId = new JTextField(9);
			p.add(nome);
			p.add(tNome);
			p.add(rg);
			p.add(tRg);
			p.add(idade);
			p.add(tIdade);
			p.add(id);
			p.add(tId);
			boolean confirm = true;
			JOptionPane.showMessageDialog(null, p, "Cadastro de "+tipo, JOptionPane.QUESTION_MESSAGE);
			if(tNome.getText()==null || tNome.getText().compareTo("")==0 || tRg.getText()==null || tRg.getText().compareTo("")==0 ||
			   tIdade.getText()==null || tIdade.getText().compareTo("")==0 || tId.getText()==null || tId.getText().compareTo("")==0){
				JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos.", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
				confirm = false; 
			}
			if(tipo.compareTo("Aluno")==0 && confirm){
				try {
					Aluno aluno = new Aluno(tNome.getText(),tRg.getText(),Integer.parseInt(tIdade.getText()),Integer.parseInt(tId.getText()));
					return aluno;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
				}
			}
			if(tipo.compareTo("Funcionario")==0 && confirm){
				try {
					Funcionario funcionario = new Funcionario(tNome.getText(),tRg.getText(),Integer.parseInt(tIdade.getText()),Integer.parseInt(tId.getText()));
					return funcionario;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		JOptionPane.showMessageDialog(null, "Operação Cancelada", "Erro de Cadastro", JOptionPane.PLAIN_MESSAGE);
		return null;
	}
	private class CadastraAluno implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Aluno aluno = (Aluno) newPessoa("Aluno");
			if(aluno!=null){
				try {
					cat.getInfo().setCadastrarAluno(aluno, aluno.getIdAluno());
					JOptionPane.showMessageDialog(null, aluno.getNome()+" cadastrado com sucesso.", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	private class CadastraFuncionario implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Funcionario funcionario = (Funcionario) newPessoa("Funcionario");
			if(funcionario!=null){
				try{
					cat.getInfo().setCadastrarFuncionario(funcionario, funcionario.getIdFuncionario());
					JOptionPane.showMessageDialog(null, funcionario.getNome()+" cadastrado com sucesso.", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	private class CadastraProfessor implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Professor prof = (Professor) newPessoa("Professor");
			Funcionario f=null;
			if(prof!=null) f = (Funcionario)cat.getInfo().getPessoaWithId(prof.getIdFuncionario());
			if(prof!=null || f!=null){
				try{
					cat.getInfo().setCadastrarProfessor(f, prof.getIdProfessor());
					JOptionPane.showMessageDialog(null, prof.getNome()+" cadastrado com sucesso.", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	private class ExcluirAluno implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			String sId = JOptionPane.showInputDialog(null, "Insira o ID do aluno:", "Excluir Aluno", JOptionPane.QUESTION_MESSAGE);
			if(sId!=null){
				int id = Integer.parseInt(sId);
				if(cat.getInfo().setExcluirAluno(id)) JOptionPane.showMessageDialog(null, "Excluido com Sucesso","Excluir Aluno", JOptionPane.INFORMATION_MESSAGE);
				else JOptionPane.showMessageDialog(null, "Aluno com ID:"+id+" não encontrado.","Excluir Aluno",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	private class ExcluirFuncionario implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			String sId = JOptionPane.showInputDialog(null, "Insira o ID do Funcionario:", "Excluir Funcionario", JOptionPane.QUESTION_MESSAGE);
			if(sId!=null){
				int id = Integer.parseInt(sId);
				if(cat.getInfo().setExcluirFuncionario(id)) JOptionPane.showMessageDialog(null, "Excluido com Sucesso","Excluir Funcionario", JOptionPane.INFORMATION_MESSAGE);
				else JOptionPane.showMessageDialog(null, "Funcionario com ID:"+id+" não encontrado.","Excluir Funcionario",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	private class ExcluirProfessor implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			String sId = JOptionPane.showInputDialog(null, "Insira o ID do Professor:", "Excluir Professor", JOptionPane.QUESTION_MESSAGE);
			if(sId!=null){
				int id = Integer.parseInt(sId);
				if(cat.getInfo().setExcluirProfessor(id)) JOptionPane.showMessageDialog(null, "Excluido com Sucesso","Excluir Professor", JOptionPane.INFORMATION_MESSAGE);
				else JOptionPane.showMessageDialog(null, "Professor com ID:"+id+" não encontrado.","Excluir Professor",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	private Pessoa procurarPessoa(){
		String sId = JOptionPane.showInputDialog(null, "ID", "Procurar...", JOptionPane.QUESTION_MESSAGE);
		if(sId!=null){
			return cat.getInfo().getPessoaWithId(Integer.parseInt(sId));
		}
		return null;
	}
	private class ProcurarAluno implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Pessoa aluno = procurarPessoa();
			if(aluno!=null && aluno instanceof Aluno){
				JOptionPane.showMessageDialog(null, "Nome: "+aluno.getNome()+"\n"
												   +"RG: "+aluno.getRg()+"\n"
												   +"Idade: "+aluno.getIdade(), "Aluno encontrado",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null, "Nenhum Aluno foi encontrado", "Sem resultado",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
	private class ProcurarFuncionario implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Pessoa f =  procurarPessoa();
			if(f!=null && !(f instanceof Aluno)){
				if(f instanceof Professor){
					JOptionPane.showMessageDialog(null, "Nome: "+f.getNome()+"\n"
													   +"RG: "+f.getRg()+"\n"
													   +"Idade: "+f.getIdade()+"\n"
													   +"ID de Professor: "+((Professor)f).getIdProfessor(), "Funcionario encontrado",JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Nome: "+f.getNome()+"\n"
															    +"RG: "+f.getRg()+"\n"
															    +"Idade: "+f.getIdade(), "Funcionario encontrado",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Nenhum funcionario foi encontrado", "Sem resultado",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
	private class ProcurarProfessor implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Pessoa f = procurarPessoa();
			if(f!=null && f instanceof Professor){
				JOptionPane.showMessageDialog(null, "Nome: "+f.getNome()+"\n"
												   +"RG: "+f.getRg()+"\n"
												   +"Idade: "+f.getIdade()+"\n"
												   +"ID de Funcionario: "+((Professor)f).getIdFuncionario(), "Professor encontrado",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null, "Nenhum professor foi encontrado", "Sem resultado",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
	
	public static void main(String[] args){
		new Janela();
	}
	
}
