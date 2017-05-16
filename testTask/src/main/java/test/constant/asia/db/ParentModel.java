package test.constant.asia.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class ParentModel implements Serializable {

	private long id;
	private Date created;
	private Date updated;
		
	public ParentModel() {}
	
	@Basic
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId()  {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", updatable = false, nullable = false)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
}