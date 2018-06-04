package hrcp.account.sys.model;
/**
 * 查询平台系统分页数据的条件的model
 * @author 
 *
 */
public class QrySysDco {
	/**
	 * 系统名称，可空
	 */
	private String sysName;
	/**
	 * 系统名称模糊查询，可空
	 */
	private String sysNameFuzzy;
	/**
	 * 上级系统ID，可空
	 */
	private String sysPid;
	/**
	 * 页码，可空
	 */
	private Integer pageIndex;
	/**
	 * 分页大小，可空
	 */
	private Integer pageSize;
	/**
	 * 是否上线，可空
	 */
	private Boolean onLine;//
//	private String startDate;//
//	private String endDate;//
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getSysNameFuzzy() {
		return sysNameFuzzy;
	}
	public void setSysNameFuzzy(String sysNameFuzzy) {
		this.sysNameFuzzy = sysNameFuzzy;
	}
	public String getSysPid() {
		return sysPid;
	}
	public void setSysPid(String sysPid) {
		this.sysPid = sysPid;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Boolean getOnLine() {
		return onLine;
	}
	public void setOnLine(Boolean onLine) {
		this.onLine = onLine;
	}
}
