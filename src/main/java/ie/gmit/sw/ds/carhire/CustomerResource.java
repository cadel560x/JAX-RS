package ie.gmit.sw.ds.carhire;

import java.net.URI;
import java.rmi.Naming;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ie.gmit.sw.ds.carhire.model.Customer;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("customer")
public class CustomerResource {
//	Data members
	private DatabaseService ds;
	
	
	
	
//	Constructors
	public CustomerResource() {
		try {
			ds = (DatabaseService) Naming.lookup("rmi://127.0.0.1:1099/databaseservice");
		} catch (Exception e) {
			e.printStackTrace();
			
		} // end try - catch
		
	}
	
	
	
	
//	Other methods
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCustomer(@PathParam("id") int id) {
    		Customer customer = null;
    		try {
//    			if (id == null || uuid.trim().length() == 0) {
//    		        return Response.serverError().entity("id cannot be blank").build();
//    		    }
    			
    			customer = ds.readCustomer(id);
    			
    			if( customer == null ) {
    		        return Response.status(Response.Status.NOT_FOUND).entity("Customer not found for id: " + id).build();
    		    }
    			
		} catch (Exception e) {
				e.printStackTrace();
				
				return Response.serverError().build(); 
		} // end try - catch
    		
        return Response.ok(customer).build();
        
    } // end method
    
    
    @POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createCustomer(Customer newCustomer) {
    		try {
    			ds.createCustomer(newCustomer);
    			
    			return Response.created(new URI("/carhire/customer/" + newCustomer.getId())).build();
    			
		} catch (Exception e) {
			return Response.status(500).entity("Customer not created !!").build();
			
		} // end try - catch

	} // end method
    
    
    @PUT
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateCustomer(Customer customer) {
    		try {
    			ds.updateCustomer(customer);
    			
    			return Response.ok().entity(customer).build();
    			
		} catch (Exception e) {
			return Response.status(500).entity("Customer with id " + customer.getId() + " not created !!").build();
			
		} // end try - catch

	} // end method
    
    
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteCustomer(@PathParam("id") int id) {
    		Customer customer = null;
    		String msg;
    		try {
    			customer = ds.readCustomer(id);
    			ds.deleteCustomer(customer);
    			msg = "Customer deleted successfully";
		} catch (Exception e) {
				e.printStackTrace();
				msg = "Customer was not deleted";
				return Response.status(500).entity(msg).build();
				
		} // end try - catch
    		
        return Response.ok().entity(msg).build();
        
    } // end method
    
    
    
    
//  Inner classes
    @Path("customers")
    public static class Customers {
//    	Data members
	    	private DatabaseService ds;
	    	
	    		    	
//    	Constructors
	    	public Customers() {
	    		try {
	    			ds = (DatabaseService) Naming.lookup("rmi://127.0.0.1:1099/databaseservice");
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    			
	    		} // end try - catch
	    		
	    	}
    	
	    	
//    	Other methods
        @GET
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public List<Customer> getCustomers() {
        		List<Customer> customers = null;
        		try {
        			customers = ds.readCustomers();
	    		} catch (Exception e) {
	    				e.printStackTrace();
	    				
	    		} // end try - catch
        		
            return customers;
            
        } // end method
        
    } // end inner class
    
} // end class
