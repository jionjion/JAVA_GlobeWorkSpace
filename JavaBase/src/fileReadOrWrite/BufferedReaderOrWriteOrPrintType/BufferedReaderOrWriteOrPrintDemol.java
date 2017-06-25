package fileReadOrWrite.BufferedReaderOrWriteOrPrintType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**对文件进行整行的读取*/
public class BufferedReaderOrWriteOrPrintDemol{

	public static void main(String[] args) throws Exception{
		//对文件进行读写
		BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream("F:\\JAVA_WorkSpace\\JavaBase\\src\\fileReadOrWrite\\BufferedReaderOrWriteOrPrintType\\BRA")));
		
		BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream("F:\\JAVA_WorkSpace\\JavaBase\\src\\fileReadOrWrite\\BufferedReaderOrWriteOrPrintType\\BRB")));
		
		PrintWriter printWriter = new PrintWriter("F:\\JAVA_WorkSpace\\JavaBase\\src\\fileReadOrWrite\\BufferedReaderOrWriteOrPrintType\\BRC"); 
		
		String line;
		while((line = bufferedReader.readLine()) != null){
			System.out.print(line);	//不能识别换行符
			bufferedWriter.write(line);
			bufferedWriter.newLine();//主动换行
			printWriter.println(line);
		}
		bufferedWriter.flush();
		bufferedWriter.close();
		printWriter.flush();
		printWriter.close();
		bufferedReader.close();
		
		
	}
}
