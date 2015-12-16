package packGlobalFunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.util.CellReference;

public class readXLS {
	
	public static int ROW_COUNT = 0, COLUMN_COUNT = 0;
	String fileName;
	FileInputStream fs;
	Workbook wb;
	static Sheet sheet;
	public readXLS(String fileName, String sheetName) throws IOException{
		this.fileName=fileName;
		fs = new FileInputStream(this.fileName);
		//fs = new FileInputStream(".\\InputTestData\\"+this.fileName);
		wb = new HSSFWorkbook(fs);
		sheet = wb.getSheet(sheetName);
		
	}
	
	 private static String[][] getArrayDim() throws IOException{
		String[][] dim1;
				
		int rowCount=0, columnCount=0;
		for(Row row : sheet){
			rowCount++;
			for(Cell cell : row){
				columnCount++;cell.getColumnIndex();//System.out.println("cell value "+cell.getStringCellValue());
			}
			//System.out.println("---");
		}	
		ROW_COUNT = rowCount;
		COLUMN_COUNT = (columnCount/rowCount);
		
		System.out.println("row  "+ROW_COUNT + "  column  "+COLUMN_COUNT);
		dim1= new String[ROW_COUNT][COLUMN_COUNT];
		
		return dim1;
	}
	
	 public static String[][] insertXLSDataInArray() throws IOException{
		
		String[][] list;
		list = getArrayDim();
		String cellValue = null;
		
		int rowCount=0, columnCount;
		for(Row row : sheet){
			columnCount=0;
			for(Cell cell : row){
					//cellValue = cell.getStringCellValue(); //System.out.print(cell.getStringCellValue());
					//****************************
					//CellReference cellref = new CellReference(row.getRowNum(), cell.getColumnIndex());
					//System.out.print(cellref.formatAsString());
					//System.out.println(" -- ");
					//System.out.println("Cell Type is--->"+cell.getCellType());
					switch (cell.getCellType()){
					
					case Cell.CELL_TYPE_STRING:
					System.out.print(cellValue = cell.getStringCellValue());
					break;
					case Cell.CELL_TYPE_NUMERIC:
						if(DateUtil.isCellDateFormatted(cell))
						{
							Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							cellValue  = formatter.format(cell.getDateCellValue());
							System.out.print(cell.getDateCellValue());
						}
						else {
							cellValue = NumberFormat.getIntegerInstance().format(cell.getNumericCellValue());
							System.out.print(cell.getNumericCellValue());
							}
					break;
					
					case Cell.CELL_TYPE_BOOLEAN:
					{	 
						Boolean b = cell.getBooleanCellValue();
						cellValue = b.toString();
						System.out.print(cellValue);
					}
					break;
					
					case Cell.CELL_TYPE_BLANK:
						System.out.print("No data prese at this cell");
					break;	
					}
				
					//*****************************
					//System.out.println("row==" + rowCount + "  column  "+columnCount);
					list[rowCount][columnCount]=cellValue;
					columnCount++;
					
				}
			//System.out.println("----");
			rowCount++;
		}
	return list;
	}
	 
	 //HashMap for data
	 /*public static HashMap<String, String>  insertXLSDataInHash() throws IOException{
			HashMap<String, String> hashmap = new HashMap<String, String>();
			
			String[][] list;
			list = getArrayDim();
			String cellValue = null;
			
			int rowCount=0, columnCount;
			for(Row row : sheet){
				columnCount=0;
				for(Cell cell : row){
						//cellValue = cell.getStringCellValue(); //System.out.print(cell.getStringCellValue());
						//****************************
						//CellReference cellref = new CellReference(row.getRowNum(), cell.getColumnIndex());
						//System.out.print(cellref.formatAsString());
						//System.out.println(" -- ");
						//System.out.println("Cell Type is--->"+cell.getCellType());
						switch (cell.getCellType()){
						
						case Cell.CELL_TYPE_STRING:
						System.out.print(cellValue = cell.getStringCellValue());
						break;
						case Cell.CELL_TYPE_NUMERIC:
							if(DateUtil.isCellDateFormatted(cell))
							{
								Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								cellValue  = formatter.format(cell.getDateCellValue());
								System.out.print(cell.getDateCellValue());
							}
							else {
								cellValue = NumberFormat.getIntegerInstance().format(cell.getNumericCellValue());
								System.out.print(cell.getNumericCellValue());
								}
						break;
						
						case Cell.CELL_TYPE_BOOLEAN:
						{	 
							Boolean b = cell.getBooleanCellValue();
							cellValue = b.toString();
							System.out.print(cellValue);
						}
						break;
						
						case Cell.CELL_TYPE_BLANK:
							System.out.print("No data prese at this cell");
						break;	
						}
					
						//*****************************
						//System.out.println("row==" + rowCount + "  column  "+columnCount);
						System.out.println(list[rowCount][columnCount]);
						System.out.println(list[rowCount+1][columnCount]);
						hashmap.put(list[rowCount][columnCount], list[rowCount+1][columnCount]);
						//list[rowCount][columnCount]=cellValue;
						columnCount++;
											
					}
				System.out.println("--->"+ hashmap);
				//System.out.println("----");
				rowCount++;
				break;
			}
		return hashmap;
	}
		 */
	/*public static void main(String[] args) throws IOException{
		HashMap<String, String> hash=new HashMap<String, String>();
		new readXLS("C:\\ORION_SCRIPTS\\OECDocCollection\\InputTestData\\InputData.xls", "Env");
		hash = readXLS.insertXLSDataInHash();
		System.out.println();
			System.out.println("hashtable values:  -"+ hash.values());
		
	}*/
	 

}
