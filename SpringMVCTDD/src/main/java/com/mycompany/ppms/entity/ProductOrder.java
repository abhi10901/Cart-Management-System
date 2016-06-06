package com.mycompany.ppms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_order")
@NamedQueries({
	@NamedQuery(name = "ProductOrder.findAllProductsByOrderId", query = "SELECT prodOrder.product FROM ProductOrder prodOrder "
																			+ "WHERE prodOrder.order.id = :orderId")
})
public class ProductOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "order_id")
	private CustomerOrder order;
	
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
