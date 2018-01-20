package export.core;
/**
 * 	����ģ��
 * @author JionJion
 */

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;



public class ExportExcel {

	private static List<Student> list = new ArrayList<Student>();
	
	/**�����ʱ��ʼ������*/
	static {
		Student a = new Student(1, "Jion", 23, "��", new Date(), "����,����","Jionͬѧ����");
		Student b = new Student(2, "Arise",22, "Ů", new Date(), "��ɽ,��Ӿ","Ariseͬѧ����");
		Student c = new Student(3, "Tom",  23, "��", new Date(), "̨��,����","Tomͬѧ����");
		Student d = new Student(4, "Mary", 23, "Ů", new Date(), "˯��,�Է�","Maryͬѧ����");
		Student e = new Student(5, "Wete", 23, "��", new Date(), "����,׷��","Weteͬѧ����");
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
	}	
	
	/** ����list�����еĶ�����Ϣ�ʹ�������͹淶,����Excel�ļ�*/
	public static void export(List<?> list , Class clazz , String fileName) throws Exception {
		
		//У��,list��Ϊ��
		if (list.isEmpty()) {
			System.err.println("����Ϊ��!!");
			return;
		}
		
		//����,������Ԫ���Ƿ����,�Ƿ���Ϸ���Ҫ��
		for(int i=0 ; i<list.size() ; i++) {
			if (!(StringUtils.equals(list.get(i).getClass().getName(), clazz.getName()))) {
				System.err.println("���ϻ�������ֵ�Ԫ��!");
				return;
			}
		}
		
		//�����÷������ж��������˽������,��Ϊ�����ֶ�
		Field[] fields = clazz.getDeclaredFields();
		List<Field> privateFieldList = new ArrayList<Field>();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			//���˽������
			if (field.getModifiers() == Modifier.PRIVATE) {
				privateFieldList.add(field);
			}
		}
		
		//��Ҫ�������ֶμ���
		Set<Field> exportFieldSet = new LinkedHashSet<Field>();
		//����ÿ��˽������
		for(int i=0 ; i<privateFieldList.size() ; i++) {
			
			Field privateField = privateFieldList.get(i);
			
			//������ 	getXxx ,ע�ⵥ����ĸ��д
			String methodName = "get" + privateField.getName().substring(0, 1).toUpperCase()
										+ privateField.getName().substring(1);
			
			//�������get()����,���ÿ�������ֵ,��List�����ж��󶼲����и�����ֵʱ,��������
			for(int j=0 ; j<list.size() ; j++) {
				Object object = list.get(j);
				Method getMethod = object.getClass().getMethod(methodName);
				Object result = getMethod.invoke(object);
				//��Ϊ��,���뵼������
				if (!Objects.isNull(result)) {
					exportFieldSet.add(privateField);
				}
			}
		}
		
		//����Excel
		HSSFWorkbook excel = new HSSFWorkbook();
		
		//����sheetҳ
		HSSFSheet sheet = excel.createSheet(fileName);
		
		//��ʼ��������
		int rownum = 0 ;
		int colnum = 0 ;
		
		//���ÿ��
		Iterator<Field> exportFieldWithIterator = exportFieldSet.iterator();
		if(exportFieldWithIterator.hasNext()) {
			//�п�
			int columnWith = Math.round(exportFieldWithIterator.next().getName().length() * 300F);
			sheet.setColumnWidth(colnum, columnWith);
			colnum ++;
		}
		
		//��������
		{
			//������������
			rownum = 0 ;
			colnum = 0 ;		
			//���ⵥԪ��
			HSSFRow row = sheet.createRow(rownum);
			//������Ԫ��
			HSSFCell cell = row.createCell(colnum);
			
			//������Ԫ����ʽ
			HSSFCellStyle cellStyle = excel.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			
			//����������ʽ
			HSSFFont font = excel.createFont();
			font.setFontName("΢���ź�");
			font.setBold(true);
			font.setFontHeightInPoints((short)12);
			//�����������ʽ��
			cellStyle.setFont(font);		
			//�ϲ�����		��ʼ�� , ������ , ��ʼ�� , ������
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, colnum, colnum + exportFieldSet.size()-1 ));
			//��Ԫ��������ʽ
			cell.setCellStyle(cellStyle);
			cell.setCellValue(fileName);
		}
		//������ͷ��Ϣ
		{
			//����
			rownum = 1 ;
			colnum = 0 ;
			//������������,ȷ�������и�ʽ
			Iterator<Field> exportFieldFormatIterator = exportFieldSet.iterator();
			//������ǰ��
			HSSFRow row = sheet.createRow(rownum);
			while(exportFieldFormatIterator.hasNext()) {
				
				//������Ԫ��
				HSSFCell cell = row.createCell(colnum);
				//������Ԫ����ʽ
				HSSFCellStyle cellStyle = excel.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				//�����ֶ�
				Field exportField = exportFieldFormatIterator.next();
				cell.setCellValue(exportField.getName());
				cell.setCellStyle(cellStyle);
				colnum ++;
			}
		}		
		
		//��������
		{
			//����
			rownum = 2 ;
			colnum = 0 ;
			
			//����List����,ÿ�����ÿ������
			for(int j=0 ; j<list.size() ; j++) {
				
				//������ǰ��
				HSSFRow row = sheet.createRow(rownum);
				colnum = 0;
				//���ݶ���
				Object object = list.get(j);
				Iterator<Field> exportFieldDataIterator = exportFieldSet.iterator();
				//��ÿһ������,ͨ���������getXxx()����,��ö����ֵ
				while(exportFieldDataIterator.hasNext()) {
					
					//������Ԫ����ʽ
					HSSFCellStyle cellStyle = excel.createCellStyle();
					//������Ԫ�����ݸ�ʽ��
					HSSFDataFormat format = excel.createDataFormat();
					
					//�����ֶ�
					Field exportField = exportFieldDataIterator.next();							
					//������ 	getXxx ,ע�ⵥ����ĸ��д
					String methodName = "get" + exportField.getName().substring(0, 1).toUpperCase()
												+ exportField.getName().substring(1);					
					//�������get()����,���ÿ�������ֵ
					Method getMethod = object.getClass().getMethod(methodName);
					Object result = getMethod.invoke(object);
					
					//������Ԫ��,���������͸�ֵ
					HSSFCell cell = row.createCell(colnum);					
					//�����ж�,����ʽ��
					if(StringUtils.equals(exportField.getType().getSimpleName(), "Integer")) {
						//��������
						cell.setCellType(CellType.NUMERIC);
						//���õ�Ԫ����ʽ��ʽ��
						cellStyle.setDataFormat(format.getFormat("##00"));
						cell.setCellStyle(cellStyle);
						cell.setCellValue((Integer)result);
					}else if(StringUtils.equals(exportField.getType().getSimpleName(), "Date")) {
						//��������
						cell.setCellType(CellType.NUMERIC);
						cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));	
						cell.setCellStyle(cellStyle);
						cell.setCellValue((Date)result);
					}else {
						//�������ı�����
						cell.setCellType(CellType.STRING);
						cellStyle.setDataFormat(format.getFormat("@"));
						cell.setCellStyle(cellStyle);
						cell.setCellValue((String)result);
					}
					colnum ++;
				}
				rownum ++;
			}
		}
		
		/* ����Excel */
		String exportPath = System.getProperty("user.dir") + "\\bin\\export\\template\\"+ fileName +".xls";
		File exportFile = new File(exportPath);
		//��ɾ��,�ٴ���
		exportFile.delete();	
		exportFile.createNewFile();
		FileOutputStream outputStream =FileUtils.openOutputStream(exportFile);
		//д���ļ�
		excel.write(outputStream);
		excel.close();
		
		System.out.println("�ļ�·��>>>" + exportPath);
	}
	
	public static void main(String[] args) throws Exception {
		
		export(list, Student.class, "ѧ����Ϣ����");
	}
}
