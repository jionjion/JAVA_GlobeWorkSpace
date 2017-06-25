package helloExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**����Excel����������*/
public class MakeOrOpenWorkBook {

	/**����������*/
	public static void makeNewWorkbook() throws IOException {
		
		/*������������*/
		//�����հ׹�����
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		/*����sheetҳ����*/
		XSSFSheet spreadsheet = workbook.createSheet("Ա����Ϣ");
		/*������Ԫ�в���*/
		XSSFRow row = spreadsheet.createRow(0);
		/*������Ԫ��,д������ */
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("Ա�����");
		cell = row.createCell(1);
		cell.setCellValue("Ա������");
		/*�����ڶ���*/
		row = spreadsheet.createRow(1);
		/*������Ԫ��,д������ */
		cell = row.createCell(0);
		cell.setCellValue("001");
		cell = row.createCell(1);
		cell.setCellValue("����");
		
		/*���������,��������ļ�*/
		FileOutputStream out = new FileOutputStream(
			new File("F:\\JAVA_WorkSpace\\Office\\�����Զ�����.xlsx"));
		//������������ļ���.
		workbook.write(out);
		out.close();
		//������ر�
		workbook.close();
		}
	
	/**�򿪹�����
	 * @throws IOException */
	@SuppressWarnings("deprecation")
	public static void openNewWorkbook() throws IOException {
	  
		/*�򿪹�������*/
		File file = new File("F:\\JAVA_WorkSpace\\Office\\�����Զ���ȡ.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		//���ݻ�ȡ�ļ�����Ϊ������
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		if(file.isFile() && file.exists()){	//���file�ļ�����,��򿪳ɹ�
			System.out.println("�򿪹�����ɹ�");
		} else {
			System.out.println("�򿪹�����ʧ��");
		}
		/*�򿪹���ҳ*/
		XSSFSheet spreadsheet = workbook.getSheetAt(0);
		/*����е�����*/
		Iterator<Row> rowIterator = spreadsheet.iterator();
		while (rowIterator.hasNext()) {
			/*���ÿ��*/
			XSSFRow row = (XSSFRow) rowIterator.next();
			/*���ÿ�е�Ԫ������*/
			 Iterator < Cell > cellIterator = row.cellIterator();
			 while(cellIterator.hasNext()){
				 /*���ÿ����Ԫ��*/
				 Cell cell= cellIterator.next();
				 switch (cell.getCellType()) 			//�жϻ�ȡ�ĵ�Ԫ��Ԫ�ص�����	0:����  1:�ַ���  2:��ʽ  3:��  4:����ֵ  5:����
		            {
		               case Cell.CELL_TYPE_NUMERIC:		//��������
		               System.out.print( 
		               cell.getNumericCellValue() + " \t\t " );		
		               break;
		               case Cell.CELL_TYPE_STRING:		//�ַ�������
		               System.out.print(
		               cell.getStringCellValue() + " \t\t " );
		               break;
		            }
			 }
			
		}
		fileInputStream.close();
		workbook.close();
	}
	
	public static void main(String[] args) throws Exception {
		
		//����
		makeNewWorkbook();
		//��ȡ
		openNewWorkbook();
	}
	
}
