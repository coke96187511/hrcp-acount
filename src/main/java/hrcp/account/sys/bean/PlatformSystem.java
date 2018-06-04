package hrcp.account.sys.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="base_sys")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class PlatformSystem {
	@Id
	private String sysId;
	private String sysName;
	private String sysPid;
	private Integer sortIndex;
	private Boolean onLine;
	private Boolean deleted;
	private String sysDesc;
	private String createdBy;
	private String createdByName;
	private Date createdDate;
	private String lastModifiedBy;
	private String lastModifiedByName;
	private Date lastModifiedDate;
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getSysPid() {
		return sysPid;
	}
	public void setSysPid(String sysPid) {
		this.sysPid = sysPid;
	}
	public Integer getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}
	public Boolean getOnLine() {
		return onLine;
	}
	public void setOnLine(Boolean onLine) {
		this.onLine = onLine;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public String getLastModifiedByName() {
		return lastModifiedByName;
	}
	public void setLastModifiedByName(String lastModifiedByName) {
		this.lastModifiedByName = lastModifiedByName;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getSysDesc() {
		return sysDesc;
	}
	public void setSysDesc(String sysDesc) {
		this.sysDesc = sysDesc;
	}
	
}
