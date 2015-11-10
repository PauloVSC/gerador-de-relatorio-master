package relatorio;

//import jxl.Sheet;

public class DatasAula  {
	private String datas[] = new String[40];

	public String getDatas(int i) {
		return this.datas[i];
	}

	public void setDatas(String datas, int j) {
		this.datas[j] = datas;
	}

	//public String[] pegarDatas(Sheet planilha, int col){
	//    for(int i=5; i<col; i++){
	//    	this.datas[i]=planilha.getCell(i, 0).getContents();
	//    	System.out.println(datas[i]);
	 //   }
	 //   return datas;
		//return datas;
		
	}

