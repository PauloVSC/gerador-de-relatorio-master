package relatorio;

import jxl.Sheet;

public class Alunos extends DatasAula{
	private String matricula;
	private String nome;
	private String codigo;
	//35 aulas + matricula, nome e codigo
	private String aula[] = new String[40];
	private String avaliacao[] = new String[2];
	private float media;
	private String resultado;
	
	
	public String getResultado() {
		return resultado;
	}


	public void setResultado(String resultado) {
		this.resultado = resultado;
	}


	public float getMedia(){
		return media;
	}
	
		
	public void setMedia(float f){		
		this.media = f;
		 	 
	}
	
	public void setAvaliacao(String avaliacao, int i) {
		this.avaliacao[i] = avaliacao;
	}
	
	public String getAvaliacao(int i) {
		return this.avaliacao[i];
	} 
	
	public String[] getAvaliacao() {
		return this.avaliacao;
	} 
	
	public void setDatas(Sheet planilha, int col) {
		this.datas = this.pegarDatas(planilha, col);
	} 
	
	public String getDatas(int i) {
		return this.datas[i];
	} 

	//Métodos para o nome
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return this.nome;
	}
	
	//Métodos para a matrícula
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMatricula() {
		return this.matricula;
	}
	
	//Métodos para a aula
	public void setAula(String aula, int i) {
		this.aula[i] = aula;
	}
	public String getAula(int j) {
		return this.aula[j];
	}
	
	//Métodos para o codigo
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigo() {
		return this.codigo;
	}

}
