package bean;
/**�ӱ�,��Ӧ���������*/
public class CommandContent {
	/**Ψһ����*/
	private int id;
	/**����*/
	private String content;
	/**���*/
	private int commandId;
	/**���������*/
	private Command command;
	
	public int getCommandId() {
		return commandId;
	}

	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
