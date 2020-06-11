package com.schoolproject.InvestorPanel.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Properties")
public class Property {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "property_id", nullable = false)
	private Long id;
	
	@Column(name = "owner_id", nullable = false)
	private Long owner;
	
	@Column(name = "property_name", nullable = false)
	private String name;
	
	@Column(name = "property_city", nullable = false)
	private String city;

	@Column(name = "property_address", nullable = false)
	private String address;
	
	@Column(name = "num_of_bedrooms", nullable = false)
	private int bedrooms;
	
	@Column(name = "num_of_bathrooms", nullable = false)
	private int bathrooms;
	
	@Column(name = "year_built", nullable = false)
	private int yearBuilt;
	
	@Column(name = "property_area", nullable = false)
	private float area;
	
	@Column(name = "purchase_price", nullable = false)
	private float purchasePrice;

	@Column(name = "monthly_property_rent", nullable = false)
	private float propertyRental;
	
	@Column(name = "renovation_finish_price", nullable = false)
	private float renovationPrice;
	
	@Column(name = "additional_costs", nullable = false)
	private float additionalCosts;
	
	@Column(name = "tax", nullable = false)
	private float tax;
	
	
	


	public Property() {
		super();
	}

	public Property(Long owner, String name, String city, String address, int bedrooms, int bathrooms, int yearBuilt,
					float area, int purchasePrice, int propertyRental) {
		super();
		this.owner = owner;
		this.name = name;
		this.city = city;
		this.address = address;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.yearBuilt = yearBuilt;
		this.area = area;
		this.purchasePrice = purchasePrice;
		this.propertyRental = propertyRental;
	}
	
	

public Property(Long owner, String name, String city, String address, int bedrooms, int bathrooms, int yearBuilt,
			float area, float purchasePrice, float propertyRental, float renovationPrice, float additionalCosts,
			float tax) {
		super();
		this.owner = owner;
		this.name = name;
		this.city = city;
		this.address = address;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.yearBuilt = yearBuilt;
		this.area = area;
		this.purchasePrice = purchasePrice;
		this.propertyRental = propertyRental;
		this.renovationPrice = renovationPrice;
		this.additionalCosts = additionalCosts;
		this.tax = tax;
	}

//	public Property(Long owner, String name, String city, String address, int bedrooms, int bathrooms,
//		int yearBuilt, float area, int purchasePrice, int propertyRental) {
//	super();
//	this.owner = owner;
//	this.name = name;
//	this.city = city;
//	this.address = address;
//	this.bedrooms = bedrooms;
//	this.bathrooms = bathrooms;
//	this.yearBuilt = yearBuilt;
//	this.area = area;
//	this.purchasePrice = purchasePrice;
//	this.propertyRental = propertyRental;
//}



	public Long getId() {
		return id;
	}

	public float getRenovationPrice() {
		return renovationPrice;
	}

	public void setRenovationPrice(float renovationPrice) {
		this.renovationPrice = renovationPrice;
	}

	public float getAdditionalCosts() {
		return additionalCosts;
	}

	public void setAdditionalCosts(float additionalCosts) {
		this.additionalCosts = additionalCosts;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public void setPropertyRental(float propertyRental) {
		this.propertyRental = propertyRental;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public int getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}

	public int getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(int yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public float getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public float getPropertyRental() {
		return propertyRental;
	}

	public void setPropertyRental(int propertyRental) {
		this.propertyRental = propertyRental;
	}

	
	@Override
	public String toString() {
		return "Property [id=" + id + ", owner=" + owner + ", name=" + name + ", voivodeship=" + propertyRental + ", city="
				+ city + ", address=" + address + ", bedrooms=" + bedrooms + ", bathrooms=" + bathrooms + ", yearBuilt="
				+ yearBuilt + ", area=" + area + ", purchasePrice=" + purchasePrice + ", propertyRental=" + propertyRental
				+ "]";
	}
	
}
