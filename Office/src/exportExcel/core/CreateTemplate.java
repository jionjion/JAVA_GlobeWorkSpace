package exportExcel.core;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 * 	读取XML文件,生成对应的Excel文件
 * @author JionJion
 */
public class CreateTemplate {

	public static void main(String[] args) throws Exception {
		
		/*	第一部分
		 *  创建文件信息	*/
		
		//获得工作空间的/bin目录下的文件信息
		String path = System.getProperty("user.dir") + "\\bin\\exportExcel\\template\\student.xml";
		
		File file = new File(path);
		
		//加载XML文件进入内存,创建xml文件对象
		SAXBuilder  builder = new SAXBuilder();
		Document xml = builder.build(file);
		
		//创建Excel文件
		HSSFWorkbook excel = new HSSFWorkbook();
		
		//创建Sheet页
		HSSFSheet sheet = excel.createSheet("学生信息");
		
		/* 	第二部分
		 * 	进行解析xml文件树,并生成excel单元格 */
		
		//获得根节点	<excel>
		Element root = xml.getRootElement();
		//获得模板名称
		String templateName = root.getAttributeValue("name");
		
		//行列坐标
		int rownum = 0 ;
		int colnum = 0 ;
		
		//获得列宽,并设置	<colgroup>
		Element colgroup = root.getChild("colgroup");
		//获得所有的列描述	<col>
		List<Element> cols = colgroup.getChildren("col");
		for (int i = 0; i < cols.size(); i++) {
			//针对每一列
			Element col = cols.get(i);
			//获得宽度信息
			String width = col.getAttributeValue("width");
			String unit = width.replaceAll("[0-9,\\.]", ""); //正则匹配0-9数字和小数点
			String value = width.replace(unit, "");			//去除单位;
			
			//字体宽度与单元格宽度换算
			int columnWith = 0;
			if (StringUtils.isBlank(unit) || StringUtils.equals("px", unit)) {
				//如果没有单位,或者单位为像素
				columnWith = Math.round(Float.parseFloat(value) * 37F);
			}else if (StringUtils.equals("em", unit)) {
				//如果单位为字
				columnWith = Math.round(Float.parseFloat(value) * 255F);
			}
			//为每一个单元列设置宽度.
			sheet.setColumnWidth(i, columnWith);
		}
		
		//获得标题 	<title>
		Element title = root.getChild("title");
		List<Element> trs = title.getChildren("tr");
		for (int i = 0; i < trs.size(); i++) {
			Element tr = trs.get(i);
			//获得每行中的		<td>
			List<Element> tds = tr.getChildren("td");
			//创建单元行
			HSSFRow row = sheet.createRow(rownum);
			for(colnum = 0; colnum < tds.size(); colnum++) {
				Element td = tds.get(rownum);
				//获得跨列信息
				int rowSpan = Integer.parseInt(td.getAttributeValue("rowspan")) -1 ;
				int colSpan = Integer.parseInt(td.getAttributeValue("colspan")) -1 ;
				String value = td.getAttributeValue("value");
				
				//创建单元格
				HSSFCell cell = row.createCell(colnum);
				
				//创建单元格样式
				HSSFCellStyle cellStyle = excel.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				
				//设置字体样式
				HSSFFont font = excel.createFont();
				font.setFontName("微软雅黑");
				font.setBold(true);
				font.setFontHeightInPoints((short)12);
				//将字体加入样式中
				cellStyle.setFont(font);
				
				
				if (StringUtils.isNotBlank(value)) {
					cell.setCellValue(value);
					
					//合并居中		开始行 , 结束行 , 开始列 , 结束列
					sheet.addMergedRegion(new CellRangeAddress(rowSpan, rowSpan, 0, colSpan));
					//单元格设置样式
					cell.setCellStyle(cellStyle);
				}
			}
		}
		rownum ++;		//操作完标题后,需要对当前行位置加1
		
		
		//设置表头信息	<thead>
		Element thead = root.getChild("thead");
		trs = thead.getChildren("tr");
		for (int i = 0; i < trs.size(); i++) {
			Element tr = trs.get(i);
			//创建单元行
			HSSFRow row = sheet.createRow(rownum);
			//获得表头字段信息
			List<Element> ths = tr.getChildren("th");
			//每一列的当前行进行操作
			for (colnum = 0; colnum < ths.size(); colnum++) {
				Element td = ths.get(colnum);
				//获得th中的value属性,作为表头
				String value = td.getAttributeValue("value");
				//创建单元格,作为表格
				HSSFCell cell = row.createCell(colnum);
				if (StringUtils.isNotBlank(value)) {
					cell.setCellValue(value);
				}
			}
			rownum ++;		//操作完表头后,需要对当前行位置+1
		}
		
		//设置数字区域的样式 	<tbody>
		Element tbody = root.getChild("tbody");
		Element tr = tbody.getChild("tr");
		//获得初始化的数据列数
		int repeat = Integer.parseInt(tr.getAttributeValue("repeat"));
		List<Element> tds = tr.getChildren("td");
		for(int i=0 ; i<repeat ; i++) {
			//创建当前行
			HSSFRow row = sheet.createRow(rownum);
			//创建当前列
			for(colnum=0 ;colnum < tds.size(); colnum++) {
				//创建单元格样式
				HSSFCellStyle cellStyle = excel.createCellStyle();
				//创建单元格数据格式化
				HSSFDataFormat format = excel.createDataFormat();
				//创建单元格
				HSSFCell cell = row.createCell(colnum);
				
				//获得数据列信息
				Element td = tds.get(colnum);
				String type = td.getAttributeValue("type");
				if (StringUtils.equalsIgnoreCase("NUMERIC", type)) {
					//数字类型
					cell.setCellType(CellType.NUMERIC);
					//数字格式化
					String formatValue = td.getAttributeValue("format");
					if (StringUtils.isBlank(formatValue)) {
						formatValue = "#,##0.00";
					}
					//设置单元格样式格式化
					cellStyle.setDataFormat(format.getFormat(formatValue));
				} else if (StringUtils.equalsIgnoreCase("STRING", type)) {
					//字符类型
					cell.setCellValue("");
					cell.setCellType(CellType.STRING);
					//@表示文本
					cellStyle.setDataFormat(format.getFormat("@"));	
				} else if (StringUtils.equalsIgnoreCase("DATE", type)) {
					//日期类型,与数字类型一致
					cell.setCellType(CellType.NUMERIC);
					//日期格式
					cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
				} else if (StringUtils.equalsIgnoreCase("ENUM", type)) {
					//枚举类型
					//对应Excel的下拉选框
					CellRangeAddressList regions = new CellRangeAddressList(
													cell.getRowIndex(),cell.getRowIndex(),
													cell.getColumnIndex(),cell.getColumnIndex());
					//获得选项,逗号分隔的数组
					String[] enums = td.getAttributeValue("format").split(",");
					//下拉选框内容
					DVConstraint constraint = DVConstraint.createExplicitListConstraint(enums);
					//数据有效对象
					HSSFDataValidation dataValidation = new HSSFDataValidation(regions, constraint);
					//将下拉选项加入Excel中
					excel.getSheetAt(0).addValidationData(dataValidation);
				}
				//设置单元
				cell.setCellStyle(cellStyle);
			}
//			rownum ++;
		}
		
		/* 导出Excel */
		String exportPath = System.getProperty("user.dir") + "\\bin\\exportExcel\\template\\"+ templateName +".xls";
		File templateFile = new File(exportPath);
		//先删除,再创建
		templateFile.delete();	
		templateFile.createNewFile();
		FileOutputStream outputStream =FileUtils.openOutputStream(templateFile);
		//写入文件
		excel.write(outputStream);
		excel.close();
	}
}
