package hrcp.account.sys.model;
/**
 * 系统平台更改上线状态的model，用于前端传值注入到model
 * @author 
 *
 */
public class SysOnLineVco {
	/**
	 * 系统ID，非空
	 */
	private String sysId;
	/**
	 * 上线状态，可空。空默认为true
	 */
	private Boolean onLine;
	
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public Boolean getOnLine() {
		return onLine;
	}
	public void setOnLine(Boolean onLine) {
		this.onLine = onLine;
	}
	
	
}
