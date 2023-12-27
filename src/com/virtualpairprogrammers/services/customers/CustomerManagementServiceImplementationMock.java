package com.virtualpairprogrammers.services.customers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

public class CustomerManagementServiceImplementationMock implements CustomerManagementService{

	private HashMap<String, Customer> testCustomers = new HashMap<>();
	
	public CustomerManagementServiceImplementationMock() {
	
	Customer customer1 = new Customer("Uni7","Universal GmbH", "universal_Test_001@domain.com", "777-856-45", "test notes" );
	Customer customer2 = new Customer("Nk","Nike", "nike_Test_002@domain.com", "333-69-95", "test nike notes" );
	Customer customer3 = new Customer("Cdc","Codeco", "codeco_Test_003@domain.com", "56-856-45", "Codeco test notes" );
	
	testCustomers.put("Universal GmbH", customer1);
	testCustomers.put("Nike", customer2);
	testCustomers.put("Codeco", customer3);
	

	}
			
	@Override
	public void newCustomer(Customer newCustomer) {
		testCustomers.put(newCustomer.getCustomerId(), newCustomer);
	}

	@Override
	public void updateCustomer(Customer changedCustomer) {
		testCustomers.put(changedCustomer.getCustomerId(), changedCustomer);
		//this is only used now as a placeholder
		}
		
		

	@Override
	public void deleteCustomer(Customer oldCustomer) {
		for(Map.Entry<String, Customer> entry : testCustomers.entrySet()) {
			if(oldCustomer.getCustomerId().equals(entry.getKey())) {
				testCustomers.remove(oldCustomer.getCustomerId());
			}
		}
		
		
	}

	@Override
	public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
		Customer foundCustomer = testCustomers.get(customerId);
		if(foundCustomer==null)
			throw new CustomerNotFoundException();
		return foundCustomer;
	}

	@Override
	public List<Customer> findCustomersByName(String name) {
		List<Customer> results = new ArrayList<Customer>();
		for(Customer customerNext : testCustomers.values()) {
			if(customerNext.getCompanyName().equals(name)) {
				results.add(customerNext);
			}
		}
		return results;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return new ArrayList<Customer>(testCustomers.values()); //in all constructors for the collection it can be passed existing collection as an argument
	}

	@Override
	public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
		
		return this.findCustomerById(customerId);
	}

	@Override
	public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
//		Customer result = testCustomers.get(customerId);
//		result.addCall(callDetails);
		
		Customer customer = this.getFullCustomerDetail(customerId);
		customer.addCall(callDetails);
	}

}
