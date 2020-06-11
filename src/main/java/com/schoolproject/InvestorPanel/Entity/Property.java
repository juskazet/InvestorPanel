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

	@Column(name = "property_area", nullable = false)
	private float area;

	@Column(name = "purchase_price", nullable = false)
	private float purchasePrice;

	@Column(name = "renovation_finish_price", nullable = false)
	private float renovationPrice;

	@Column(name = "additional_costs", nullable = false)
	private float additionalCosts;

	@Column(name = "monthly_property_rent", nullable = false)
	private float propertyRental;

	@Column(name = "monthly_property_costs", nullable = false)
	private float monthlyCosts;

	public Property() {
		super();
	}

	public Property(Long owner, String name, String city, String address, float area, float purchasePrice,
			float renovationPrice, float additionalCosts, float propertyRental, float monthlyCosts) {
		super();
		this.owner = owner;
		this.name = name;
		this.city = city;
		this.address = address;
		this.area = area;
		this.purchasePrice = purchasePrice;
		this.renovationPrice = renovationPrice;
		this.additionalCosts = additionalCosts;
		this.propertyRental = propertyRental;
		this.monthlyCosts = monthlyCosts;
	}

	public Long getId() {
		return id;
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

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public float getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
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

	public float getPropertyRental() {
		return propertyRental;
	}

	public void setPropertyRental(float propertyRental) {
		this.propertyRental = propertyRental;
	}

	public float getMonthlyCosts() {
		return monthlyCosts;
	}

	public void setMonthlyCosts(float monthlycosts) {
		this.monthlyCosts = monthlycosts;
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", owner=" + owner + ", name=" + name + ", city=" + city + ", address=" + address
				+ ", area=" + area + ", purchasePrice=" + purchasePrice + ", renovationPrice=" + renovationPrice
				+ ", additionalCosts=" + additionalCosts  + ", propertyRental=" + propertyRental
				+ ", monthlyCosts=" + monthlyCosts + "]";
	}

}
