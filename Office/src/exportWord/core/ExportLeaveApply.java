package exportWord.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

/**
 *	���������
 *	ʹ��freemarker.jar,����ģ���滻,���xml��ʽ��Word�ĵ�����
 *	xml����Word�ĵ������Ϊ.xml��ʽ
 *	��������������������һ��,ע��������Ӣ��������ʽ
 *	Ĭ�ϵ�XML���ܽ��л��и�ʽ���ȴ���,������ƻ��ĵ���ʽ
 *	�����滻ʹ��{������!},�����ָ��ĳ���.
 */
public class ExportLeaveApply {

		//ģ�����������ļ�
	    private Configuration configuration = null;  
	      
	    //���캯��
	    public ExportLeaveApply(){  
	    	//����汾�� V2.3.0,���������Ϣ
	        configuration = new Configuration(new Version(2, 3, 0));
	        //�ַ�����
	        configuration.setDefaultEncoding("UTF-8");  
	    }  
	      
	    /** ����Word */
	    public void createWord(){
	    	//����Map
	        Map<String,Object> dataMap = getData();
	        //ģ���ļ�����·��,��·��
	        configuration.setClassForTemplateLoading(this.getClass(), "/");
	        Template t=null;  
	        try {
	            t = configuration.getTemplate("/exportWord/template/���ģ��.xml"); //��ȡģ���ļ�
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        //�����ļ�
	        File outFile = new File("�����.doc"); 
	        Writer out = null;  
	        try {  
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));  
	        } catch (FileNotFoundException e1) {  
	            e1.printStackTrace();  
	        } catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	           
	        try {
	            t.process(dataMap, out); //�������������ģ���ļ��������Ŀ���ļ� 
	        } catch (TemplateException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    /** �����б� */
	    private Map<String, Object> getData() {  
	    	Map<String, Object> dataMap = new HashMap<String, Object>();
	        dataMap.put("userStudentName", "���");
	        dataMap.put("leaveData",new SimpleDateFormat("yyyy��mm��dd��").format(System.currentTimeMillis()));  
	        dataMap.put("userParentName", "�ҳ�ͬ��");
	        dataMap.put("userTeacherName", "������ͬ��"); 
	        dataMap.put("teacherLeaderName", "�꼶��ͬ��");
	          
	        /* List��������
	        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
	        for (int i = 0; i < 10; i++) {  
	            Map<String,Object> map = new HashMap<String,Object>();  
	            map.put("XXX", i);  
	            map.put("XXX", "����"+i);  
	            list.add(map);  
	        }  
	        dataMap.put("list", list);*/  
	        
	        return dataMap;
	    }  		
	    
	    //������
	    public static void main(String[] args) {  
	        ExportLeaveApply test = new ExportLeaveApply();  
	        test.createWord();  
	        System.out.println("������!");
	    }  

}
