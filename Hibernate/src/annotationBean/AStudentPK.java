package annotationBean;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**	ѧ�������������*/
@Embeddable
public class AStudentPK implements Serializable{

	private static final long serialVersionUID = 1L;

	private int sid ;	//ѧ��
	
	private int id ;	//���֤��

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AStudentPK() {
		super();
	}
	
	
}
