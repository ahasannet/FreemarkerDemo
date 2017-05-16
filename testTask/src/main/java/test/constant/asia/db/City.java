package test.constant.asia.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City extends ParentModel {

	private String name;

	public City() { }
	
	
	@Column(name="name", length=32, nullable= false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
