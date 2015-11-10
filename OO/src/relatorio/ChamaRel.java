package relatorio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ChamaRel {
	
	public static void main(String[] args) throws JRException, IOException, BiffException{
		
		ArrayList<DatasAula> lista1 = new ArrayList<>();
	    ArrayList<Alunos> lista = new ArrayList<>();
	    Map<String, Object> mapa = new HashMap<String, Object>(); //cria o mapa
	    Workbook arquivoexcel = Workbook.getWorkbook(new File("Base de alunos.xls"));  //abre o arquivo	    
	    Sheet sheet = arquivoexcel.getSheet(0);  //abre a planilha do arquivo(aba). No caso, zero é a primeira e única planilha
	    int linhas = sheet.getRows();			 //pega a quantidade de linhas da planilha
	    int colunas = sheet.getColumns(); 		 //pega a quantidade de colunas da planilha
	    String conteudo[] = new String[colunas]; //cada indice guarda o conteudo da celula da linha
	    int contador = 0;
	    int maxFaltas;
	    char falta = 'F';
	   // String datas[] = new String[colunas];
		//Date datas[] = new Date[];
	    
	    //Date data = new Date();
	    
	    maxFaltas = (int) ((colunas-5)*0.25);
	    
//	    for(int i=5; i<colunas; i++){
//	    	datas[i]=sheet.getCell(i, 0).getContents();
//	    	System.out.println(datas[i]);
//	    }
	    
	    for(int i=1; i< linhas; i++){
	    	//Lê a linha pelo getCell(coluna, linha )	  
	    	for(int j=0; j<colunas; j++){
	    		conteudo[j] = sheet.getCell(j, i).getContents();
	    	}	 
	    	
	    	Alunos aluno = new Alunos(); 
	    	//aluno.setDatas(aluno.pegarDatas(sheet, colunas));
		   	aluno.setMatricula(conteudo[0]);
		   	aluno.setNome(conteudo[1]);
		   	aluno.setCodigo(conteudo[2]);
		    aluno.setAvaliacao(conteudo[3], 0);
		    aluno.setAvaliacao(conteudo[4], 1);
		    aluno.setMedia((Float.parseFloat(aluno.getAvaliacao(0))+Float.parseFloat(aluno.getAvaliacao(1)))/2);
		    
		    contador = 0;
		   	for(int k=0; k < colunas; k++){
		   		aluno.setAula(conteudo[k], k);
		   		//System.out.println("aqui "+aluno.getAula(k).charAt(1));
		   		if (aluno.getAula(k) != "")
			   		if (aluno.getAula(k).charAt(0) == falta){
			    		contador = contador+1;
			   		}
	    		//System.out.println( aluno.getAula(k) + " - " + contador);
            }
		   	

		   	if (contador > maxFaltas)
		   		aluno.setResultado("RF");
		   	else if (aluno.getMedia()>=5)
		    	aluno.setResultado("AP");
		    else
		    	aluno.setResultado("RR");
//System.out.println(aluno.getDatas(1));
		   	
		   	
		    for(int h=0; h< 1; h++){
		    	//Lê a linha pelo getCell(coluna, linha )
		    	for(int v=5; v<colunas; v++){
		    		conteudo[v] = sheet.getCell(v, h).getContents();
			    	aluno.setDatas(conteudo[v], v);
			    	System.out.println("aqui "+aluno.getDatas(v));
		    	}
		    }
            lista.add(aluno);
            //mapa.put("aluno"+i, aluno);
        }
    arquivoexcel.close(); //Fecha o arquivo    
    //mapa.put("aluno1", lista);
    //pega o caminho da pasta do projeto
  	String caminho = System.getProperty("user.dir");  
  	caminho.replace('\\', '/');
    
    //Cria e abre o arquivo properties
    Properties arq = new Properties();
    FileInputStream file = new FileInputStream(caminho + "/src/relatorio/cabecalho.properties");
    arq.load(file);	   
    
    //Map<String, Object> mapa = new HashMap<String, Object>(); //cria o mapa
    Cabecalho dados_cabecalho = new Cabecalho();
    dados_cabecalho.setDepartamento(arq.getProperty("departamento"));
    dados_cabecalho.setDisciplina(arq.getProperty("disciplina"));
    dados_cabecalho.setPeriodo_letivo(arq.getProperty("periodo_letivo"));
    dados_cabecalho.setTurma(arq.getProperty("turma"));
    dados_cabecalho.setCarga_horaria(arq.getProperty("carga_horaria"));
    mapa.put("cabecalho", dados_cabecalho);
        
    //Chama o JasperReport
    JasperReport report = JasperCompileManager.compileReport(caminho + "/src/relatorio/rel.jrxml");
    JasperPrint print = JasperFillManager.fillReport(report, mapa, new JRBeanCollectionDataSource(lista));
    JasperExportManager.exportReportToPdfFile(print, "Lista de presença.pdf");	
	}
}