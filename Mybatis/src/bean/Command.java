package bean;

import java.util.List;

/**����,�����б�*/
public class Command {
	/**Ψһ����*/
	private int id;
	/**����*/
	private String name;
	/**����*/
	private String description;
	/**��Ӧ�Ķ෽�б�*/
	private List<CommandContent> contentsList; 
	
	public List<CommandContent> getContentsList() {
		return contentsList;
	}

	public void setContentsList(List<CommandContent> contentsList) {
		this.contentsList = contentsList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
