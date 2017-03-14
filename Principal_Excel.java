import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Principal {

	public static void main(String[] args) throws Exception {
		
		String aux = " ";
        String TEMPO_ATUAL = " ";
    	String NIVEL_BATERIA = " ";
    	String PLUGUE = " ";
    	String ESTADO_DE_CONSERVACAO = " ";
    	String STATUS = " ";
    	String TEMPERATURA = " ";
    	String EIXO_X = " ";
    	String EIXO_Y = " ";
    	String EIXO_Z = " ";
    	String N_DE_SENSORES = " ";
    	String LATITUDE = " ";
    	String LONGITUDE = " ";
    	String INCERTEZA = " ";
        String[][] matrizDados = {
	            {"Tempo atual: ", TEMPO_ATUAL},
	            {"Battery Level: ", NIVEL_BATERIA},
	            {"Plugged: ", PLUGUE},
	            {"Health: ", ESTADO_DE_CONSERVACAO},
	            {"Status: ", STATUS},
	            {"Temperature: ", TEMPERATURA},
	            {"Eixo x: ", EIXO_X},
	            {"Eixo y: ", EIXO_Y},
	            {"Eixo z: ", EIXO_Z},
	            {"Número de Sensores Disponíveis: ", N_DE_SENSORES},
	            {"Latitude = ", LATITUDE},
	            {"Longitude = ", LONGITUDE},
	            {"Incerteza = ", INCERTEZA},
	    };

        System.out.println("Excel Rodando.\n");
        
        File arquivoDados = new File("/home/patrick/Área de Trabalho" + "/" + "_InformacoesDaVidaDoUsuario.txt");
        	
        BufferedReader leituraDados = new BufferedReader(new FileReader(arquivoDados));
        
        while(true){//Repete ate ocorrer um break
	        
	        int rotulo = 0;
	        while(((aux = leituraDados.readLine()) != null && !aux.equals("----------------")) && rotulo<matrizDados.length ){
	        	
	        	if(aux.startsWith(matrizDados[rotulo][0])){
	        		matrizDados[rotulo][1] = aux.substring(matrizDados[rotulo][0].length());//matrizDados[rotulo][1]
		        	rotulo++;
	            }
	        	
	        }if(aux == null) {System.out.println("Encerrou"); break;}//Se chegamos ao final do arquivo contendo os dados, encerre a transcrição.
	        
	        if(!matrizDados[matrizDados.length-1][matrizDados[matrizDados.length-1].length-1].equals(" ")){
	        	String NOME_ARQUIVO = "/home/patrick/Área de Trabalho" + "/" + "_InformacoesDaVidaDoUsuario.xlsx";
	        	
	        	//XSSFWorkbook workbook = new XSSFWorkbook(NOME_ARQUIVO);
	        	FileInputStream excelFile = new FileInputStream(new File(NOME_ARQUIVO));
	            Workbook workbook = new XSSFWorkbook(excelFile);
	    	    Sheet sheet = workbook.getSheetAt(0);
	    	
	    	    int rowNum = 3;
	    	    int colNum = 0;
	    	    
	    	    System.out.println("Criando excel");
	    	    
	    	    int colNumAux = (int) (sheet.getRow(1).getCell(0).getNumericCellValue());
	    	    int j_auxiliar; if (colNumAux > 0) {j_auxiliar=1;} else {j_auxiliar=0;}
	    	    
	    	    for (int i = 0; matrizDados.length>i; i++) {
	    	        
	    	    	Row row = sheet.getRow(rowNum++);
	    	    	if( row == null){row = sheet.createRow(rowNum - 1);}
	    	        colNum = colNumAux;
	    	        
	    	        for (int j = j_auxiliar; matrizDados[i].length>j; j++) {
	    	            Cell cell = row.createCell(colNum++);
		                cell.setCellValue((String) matrizDados[i][j]);
	    	        }
	    	    }colNumAux = colNum;
		        sheet.createRow(1).createCell(0).setCellValue(colNumAux);
	    	
	    	    try {
	    	    	excelFile.close();
	    	        FileOutputStream outputStream = new FileOutputStream(NOME_ARQUIVO);
	    	        workbook.write(outputStream);
	    	        workbook.close();
	    	    } catch (FileNotFoundException e) {
	    	        e.printStackTrace();
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    }
	    	
	    	    System.out.println("Feito");
	        }
	        
        }leituraDados.close();
        
        FileWriter arquivoParaFechar = new FileWriter(arquivoDados, false);
        arquivoParaFechar.write("");
        arquivoParaFechar.close();
        
	}
}
	
