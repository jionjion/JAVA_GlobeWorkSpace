package helloExcel;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**创建超链接*/
public class MakeHyperlink {

	@SuppressWarnings("deprecation")
	public static void makeHyperlink() throws Exception{
		//创建工作簿对象
		XSSFWorkbook workbook = new XSSFWorkbook();
		//创建工作页对象
	    XSSFSheet spreadsheet = workbook.createSheet("Hyperlinks");
	    XSSFCell cell;
	    //创建一个通用帮助类,工厂模式,传入类名,创建指定的对象
	    CreationHelper createHelper = workbook.getCreationHelper();
	    //创建一个链接单元格的样式,由工作簿创建
	    XSSFCellStyle hlinkStyle = workbook.createCellStyle();
	    //创建一个链接字体的样式,由工作簿创建
	    XSSFFont hlinkFont = workbook.createFont();
	    //字体样式设置下划线,传入数字1,表示单条下划线
	    hlinkFont.setUnderline(XSSFFont.U_SINGLE);
	    //字体样式设置颜色为蓝色
	    hlinkFont.setColor(HSSFColor.BLUE.index);
	    //为单元格样式设置字体样式.
	    hlinkStyle.setFont(hlinkFont);
	    //创建单元格
	    cell = spreadsheet.createRow(1).createCell((short) 1);
	    //单元格设置值
	    cell.setCellValue("超链接");
	    //创建超链接对象.传入链接类型	1.网页格式	2.文档路径	3.EMAIL路径 4.文件路径
	    XSSFHyperlink link = (XSSFHyperlink)createHelper.createHyperlink(Hyperlink.LINK_URL);
	    //传入网页格式,设置地址为一个网页
	    link.setAddress("http://www.baidu.com/" );
	    //传入link进入到单元格中
	    cell.setHyperlink(link);
	    //设置单元格样式
	    cell.setCellStyle(hlinkStyle);
	    /*创建指向文件地址的单元格*/
	    cell = spreadsheet.createRow(2).createCell((short) 1);
	    cell.setCellValue("File Link");
	    link = (XSSFHyperlink)createHelper.createHyperlink(Hyperlink.LINK_FILE);
	    link.setAddress("单元格样式.xlsx");
	    cell.setHyperlink(link);
	    cell.setCellStyle(hlinkStyle);
	    /*创建指向EMAIL地址的单元格*/
	    cell = spreadsheet.createRow(3).createCell((short) 1);
	    cell.setCellValue("Email Link");
	    link = (XSSFHyperlink)createHelper.createHyperlink(Hyperlink.LINK_EMAIL);
	    link.setAddress("mailto:1434501783@qq.com?"+"subject=Hyperlink");
	    cell.setHyperlink(link);
	    cell.setCellStyle(hlinkStyle);
	    FileOutputStream out = new FileOutputStream(
	    new File("超链接.xlsx"));
	    workbook.write(out);
	    out.close();
	    workbook.close();
	    System.out.println("超链接.xlsx 创建成功.....");
	}
	
	public static void main(String[] args) throws Exception{
		makeHyperlink();
	}
}
