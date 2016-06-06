package com.mycompany.ppms.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.tomcat.util.http.Parameters.FailReason;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer_order")
@NamedQueries({
	@NamedQuery(name = "CustomerOrder.findByCustomerId", query = "SELECT custOrder FROM CustomerOrder custOrder"
																+ " WHERE custOrder.customer.id = :customerId")
})
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "order_number")
	private String orderNumber;
	private double amount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;
	
	/*@OneToMany//(fetch = FetchType.EAGER)
	@JoinTable(name = "product_order",
				joinColumns = @JoinColumn(name = "order_id"),
				inverseJoinColumns = @JoinColumn(name = "product_id"))*/
	
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ProductOrder> productsOrdered;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<ProductOrder> getProductsOrdered() {
		return productsOrdered;
	}

	public void setProductsOrdered(List<ProductOrder> productsOrdered) {
		this.productsOrdered = productsOrdered;
	}
}
