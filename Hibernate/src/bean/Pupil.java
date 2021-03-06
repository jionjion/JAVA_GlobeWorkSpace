package bean;

import java.io.Serializable;

/**
 *	学生类,为多方.
 */
public class Pupil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int pid;
	private String pname;
	private String psex;
	private int gid;
	
	//多对一配置,班级的引用.
	private Grade grade;
	
	public Pupil(){}
	
	public Pupil(int pid, String pname, String psex, int gid) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.psex = psex;
		this.gid = gid;
	}



	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPsex() {
		return psex;
	}
	public void setPsex(String psex) {
		this.psex = psex;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}

	
	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Pupil [pid=" + pid + ", pname=" + pname + ", psex=" + psex + ", gid=" + gid + "]";
	}
	
	
}
