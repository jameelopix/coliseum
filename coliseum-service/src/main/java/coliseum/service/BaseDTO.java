package coliseum.service;

import java.io.Serializable;
import java.util.Date;

public class BaseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Deprecated
	private String changeUser;

	@Deprecated
	private Date lastUpdated;

	private Long id;

	private Integer version;

//	private String createdBy;
//
//	private Date createdDate;

	private String lastModifiedBy;

	private Date lastModifiedDate;

	public Integer getVersion() {
		return version;
	}

//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//	public Date getCreatedDate() {
//		return createdDate;
//	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public void setCreatedDate(Date createdDate) {
//		this.createdDate = createdDate;
//	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getChangeUser() {
		return changeUser;
	}

	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
