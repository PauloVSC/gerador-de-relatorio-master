package relatorio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExportaExcel {
	
	private String diretorioPlanilha;
	
	public ExportaExcel(String diretorioXLS){
		this.diretorioPlanilha = diretorioXLS;		
	}
	
	public List<Aluno> todosAlunos()  throws IOException, BiffException{
		
		Workbook workbook = Workbook.getWorkbook(new File(diretorioPlanilha));//Colocar o arquivo de excel correto
		
		Sheet sheet = workbook.getSheet(0);
		
		int colunas = sheet.getRows();
		boolean a = false;
		
		List<Aluno> arrayAluno = new ArrayList<Aluno>();
				
		
		for(int i = 0; i < colunas; i++) {			
			
			Cell a1 = sheet.getCell(0,i);
			String as1 = a1.getContents();
			
			if (as1.equals("1")) {
				a = true;
			}
				
			if (a) {
				
				Aluno al = new Aluno();
				//Avaliacao av = new Avaliacao();
				//List<Avaliacao> arrayAvaliacao = new ArrayList<Avaliacao>();
				
				Cell a2 = sheet.getCell(1,i);
				String as2 = a2.getContents();
				al.setMatricula(Integer.parseInt(as2));				
				
				Cell a3 = sheet.getCell(2,i);
				String as3 = a3.getContents();
				al.setNome(as3);
				

				
				boolean celulaVazia = false;
				int h = 3;
							
				while (!celulaVazia) {
					
					
						Cell cell = sheet.getCell(h,i);
						String cellString = cell.getContents();
						if (cellString.equals("")) {
							celulaVazia= true;
							
						}else{
							al.setAvaliacao(cellString, h-3);														
						}
						
					

					h++;
				}				

			
				/*
				Cell a5 = sheet.getCell(4,i);
				String as5 = a5.getContents();							
				al.setAvaliacao(as5, h);
				
				
				Cell a6 = sheet.getCell(5,i);
				String as6 = a6.getContents();				
				al.setAvaliacao(as6, h);
				
				
				Cell a7 = sheet.getCell(6,i);
				String as7 = a7.getContents();								
				al.setAvaliacao(as7, h);
				
				
				Cell a8 = sheet.getCell(7, i);
				String as8 = a8.getContents();				
				al.setAvaliacao(as8, h);
				
				
				Cell a9 = sheet.getCell(8, i);
				String as9 = a9.getContents();				
				al.setAvaliacao(as9, h);
				}
				*/
				//Cell a10 = sheet.getCell(9,i);
				//String as10 = a10.getContents();
				//al.setMedia(as10, 5);
				
				Float mediaCalculada = (float) 0;
				
				for (int j = 0; j <= h-5; j++) {
					mediaCalculada = mediaCalculada + Float.parseFloat(al.getAvaliacao()[j]);
				}
								
				al.setMedia(Float.toString(mediaCalculada/(h-4)));												
				
				float resultado = mediaCalculada/(h-4);
				String  result;
				if (resultado > 5) {
					result = "Aprovado";			        			
				} else {
					result = "Reprovado";
				}
				
				al.setResultado(result);
				
				System.out.println(al.getResultado());				
				System.out.println(al.getAvaliacao(0));
				System.out.println(al.getAvaliacao(1));
				//System.out.println(al.getAvaliacao(2));
			//	System.out.println(al.getAvaliacao(3));
			//	System.out.println(al.getAvaliacao(4));
			//	System.out.println(al.getAvaliacao(5));
			
				//arrayAvaliacao.add(av);
				
				//Aluno aluno = new Aluno(as3, Integer.parseInt(as2), arrayAvaliacao);
				
				//al.setAvaliacao(arrayAvaliacao);				
				
				arrayAluno.add(al);
		
			}
						
		}
		
		//for (Aluno value : arrayAluno){
			//System.out.println(value.getMatricula());
			//System.out.println(value.getNome());
			//for (Avaliacao x : value.getAvaliacao()){
				//System.out.println(x.getNota());
			//}//
		//}
				
		
		System.err.println("Total de Alunos Importados: " + arrayAluno.size());
		workbook.close();
		
		return arrayAluno;
	}
	
}
