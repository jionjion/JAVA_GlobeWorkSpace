package helloExcel;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/** ��ӡ���� */
public class PrintArea {

	public static void printArea() throws Exception {
		//����������
		XSSFWorkbook workbook = new XSSFWorkbook();
		//��������ҳ
		XSSFSheet spreadsheet = workbook.createSheet("Print Area");
		//������ӡ����
		workbook.setPrintArea(
			0, 							// ��ǰ��ӡ�ڼ�ҳ
			0, 							// ��ʼ��λ��
			5, 							// ������λ��
			0, 							// ��ʼ��λ��
			5 							// ������λ��
		);
		// ����ֽ�Ŵ�С A4ֽ
		spreadsheet.getPrintSetup().setPaperSize(XSSFPrintSetup.A4_PAPERSIZE);
		// ��ʾ��ӡ����
		spreadsheet.setDisplayGridlines(true);
		// ���ñ߽���
		spreadsheet.setPrintGridlines(true);
		FileOutputStream out = new FileOutputStream(new File("��ӡ�ĵ�.xlsx"));
		workbook.write(out);
		out.close();
		System.out.println("��ӡ�ĵ�.xlsx �����ɹ�");
		workbook.close();
	}
	
	public static void main(String[] args) throws Exception {
		printArea();
	}
}
