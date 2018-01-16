package export.core;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 * 	��ȡXML�ļ�,���ɶ�Ӧ��Excel�ļ�
 * @author JionJion
 */
public class CreateTemplate {

	public static void main(String[] args) throws Exception {
		
		/*	��һ����
		 *  �����ļ���Ϣ	*/
		
		//��ù����ռ��/binĿ¼�µ��ļ���Ϣ
		String path = System.getProperty("user.dir") + "\\bin\\export\\template\\student.xml";
		
		File file = new File(path);
		
		//����XML�ļ������ڴ�,����xml�ļ�����
		SAXBuilder  builder = new SAXBuilder();
		Document xml = builder.build(file);
		
		//����Excel�ļ�
		HSSFWorkbook excel = new HSSFWorkbook();
		
		//����Sheetҳ
		HSSFSheet sheet = excel.createSheet("ѧ����Ϣ");
		
		/* 	�ڶ�����
		 * 	���н���xml�ļ���,������excel��Ԫ�� */
		
		//��ø��ڵ�	<excel>
		Element root = xml.getRootElement();
		//���ģ������
		String templateName = root.getAttributeValue("name");
		
		//��������
		int rownum = 0 ;
		int colnum = 0 ;
		
		//����п�,������	<colgroup>
		Element colgroup = root.getChild("colgroup");
		//������е�������	<col>
		List<Element> cols = colgroup.getChildren("col");
		for (int i = 0; i < cols.size(); i++) {
			//���ÿһ��
			Element col = cols.get(i);
			//��ÿ����Ϣ
			String width = col.getAttributeValue("width");
			String unit = width.replaceAll("[0-9,\\.]", ""); //����ƥ��0-9���ֺ�С����
			String value = width.replace(unit, "");			//ȥ����λ;
			
			//�������뵥Ԫ���Ȼ���
			int columnWith = 0;
			if (StringUtils.isBlank(unit) || StringUtils.equals("px", unit)) {
				//���û�е�λ,���ߵ�λΪ����
				columnWith = Math.round(Float.parseFloat(value) * 37F);
			}else if (StringUtils.equals("em", unit)) {
				//�����λΪ����
				columnWith = Math.round(Float.parseFloat(value) * 267.5F);
			}
			//Ϊÿһ����Ԫ�����ÿ��.
			sheet.setColumnWidth(i, columnWith);
		}
		
		//��ñ��� 	<title>
		Element title = root.getChild("title");
		List<Element> trs = title.getChildren("tr");
		for (int i = 0; i < trs.size(); i++) {
			Element tr = trs.get(i);
			//���ÿ���е�		<td>
			List<Element> tds = tr.getChildren("td");
			//������Ԫ��
			HSSFRow row = sheet.createRow(rownum);
			for(rownum = 0; rownum < tds.size(); rownum++) {
				Element td = tds.get(rownum);
				//��ÿ�����Ϣ
				int rowSpan = Integer.parseInt(td.getAttributeValue("rowspan")) -1 ;
				int colSpan = Integer.parseInt(td.getAttributeValue("colspan")) -1 ;
				String value = td.getAttributeValue("value");
				
				//������Ԫ��
				HSSFCell cell = row.createCell(colnum);
				
				//������Ԫ����ʽ
				HSSFCellStyle cellStyle = excel.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				
				//����������ʽ
				HSSFFont font = excel.createFont();
				font.setFontName("΢���ź�");
				font.setBold(true);
				font.setFontHeight((short)12);
				//�����������ʽ��
				cellStyle.setFont(font);
				
				
				if (StringUtils.isNotBlank(value)) {
					cell.setCellValue(value);
					
					//�ϲ�����		��ʼ�� , ������ , ��ʼ�� , ������
					sheet.addMergedRegion(new CellRangeAddress(rowSpan, rowSpan, 0, colSpan));
					//��Ԫ��������ʽ
					cell.setCellStyle(cellStyle);
				}
			}
			rownum ++;		//����������,��Ҫ�Ե�ǰ��λ�ü�1
		}
		
		
		//���ñ�ͷ��Ϣ	<thead>
		Element thead = root.getChild("thead");
		trs = thead.getChildren("tr");
		for (int i = 0; i < trs.size(); i++) {
			Element tr = trs.get(i);
			//������Ԫ��
			HSSFRow row = sheet.createRow(rownum);
			//��ñ�ͷ�ֶ���Ϣ
			List<Element> ths = tr.getChildren("th");
			//ÿһ�еĵ�ǰ�н��в���
			for (colnum = 0; colnum < ths.size(); colnum++) {
				Element td = ths.get(colnum);
				//���th�е�value����,��Ϊ��ͷ
				String value = td.getAttributeValue("value");
				//������Ԫ��,��Ϊ���
				HSSFCell cell = row.createCell(colnum);
				if (StringUtils.isNotBlank(value)) {
					cell.setCellValue(value);
				}
			}
			rownum ++;		//�������ͷ��,��Ҫ�Ե�ǰ��λ��+1
		}
		
	}
}
