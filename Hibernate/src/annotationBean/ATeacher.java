package annotationBean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class ATeacher {

	@Id
	@GeneratedValue(generator="tid")
	@GenericGenerator(name="tid",strategy="assigned")
	@Column(length=4)
	private String tid;
	
	private String tname;

	@ManyToMany(mappedBy="teachers")										//交由老师类维护
	private Set<APupil> pupils;												//教师持有学生类的集合	
	
	public ATeacher() {
		super();
	}

	public ATeacher(String tid, String tname) {
		super();
		this.tid = tid;
		this.tname = tname;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}
	
	
}
