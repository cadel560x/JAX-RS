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

import ie.gmit.sw.ds.carhire.model.Booking;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("booking")
public class BookingResource {
//	Data members
	private DatabaseService ds;
	
	
	
	
//	Constructors
	public BookingResource() {
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
    public Response getBooking(@PathParam("id") String number) {
    		Booking booking = null;
    		try {
    			booking = ds.readBooking(number);
    			
    			if( booking == null ) {
    		        return Response.status(Response.Status.NOT_FOUND).entity("Booking not found for number: " + number).build();
    		    }
    			
		} catch (Exception e) {
				e.printStackTrace();
				
				return Response.serverError().build(); 
		} // end try - catch
    		
        return Response.ok(booking).build();
        
    } // end method
    
    
    @POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createBooking(Booking booking) {
    		try {
    			ds.createBooking(booking);
    			
    			return Response.created(new URI("/carhire/booking/" + booking.getNumber())).build();
    			
		} catch (Exception e) {
			return Response.status(500).entity("Car not created !!").build();
			
		} // end try - catch

	} // end method
    
    
    @PUT
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateBooking(Booking booking) {
    		try {
    			ds.updateBooking(booking);
    			
    			return Response.ok().entity(booking).build();
    			
		} catch (Exception e) {
			return Response.notModified().entity("Booking with number " + booking.getNumber() + " not updated !!").build();
			
		} // end try - catch

	} // end method
    
    
    @DELETE
    @Path("/{number}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteBooking(@PathParam("number") String number) {
    		Booking booking = null;
    		String msg;
    		try {
    			booking = ds.readBooking(number);
    			ds.deleteBooking(booking);
    			msg = "Booking deleted successfully";
		} catch (Exception e) {
				e.printStackTrace();
				msg = "Booking was not deleted";
				return Response.status(500).entity(msg).build();
				
		} // end try - catch
    		
        return Response.ok().entity(msg).build();
        
    } // end method
    
    
    
    
//  Inner classes
    @Path("bookings")
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
        public List<Booking> getBookings() {
        		List<Booking> bookings = null;
        		try {
        			bookings = ds.readBookings();
	    		} catch (Exception e) {
	    				e.printStackTrace();
	    				
	    		} // end try - catch
        		
            return bookings;
            
        } // end method
        
    } // end inner class
    
} // end class
