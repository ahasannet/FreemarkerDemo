package test.constant.asia.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company extends ParentModel {

	private String name;
	private City city;

	public Company() { }
	
	
	@Column(name="name", length=32, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="city")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
