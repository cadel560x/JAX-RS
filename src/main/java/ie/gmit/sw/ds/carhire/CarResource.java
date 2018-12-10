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

import ie.gmit.sw.ds.carhire.model.Car;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("car")
public class CarResource {
//	Data members
	private DatabaseService ds;
	
	
	
	
//	Constructors
	public CarResource() {
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
    public Response getCar(@PathParam("id") int id) {
    		Car car = null;
    		try {
    			car = ds.readCar(id);
    			
    			if( car == null ) {
    		        return Response.status(Response.Status.NOT_FOUND).entity("Car not found for id: " + id).build();
    		    }
    			
		} catch (Exception e) {
				e.printStackTrace();
				
				return Response.serverError().build(); 
		} // end try - catch
    		
        return Response.ok(car).build();
        
    } // end method
    
    
    @POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createCar(Car car) {
    		try {
    			ds.createCar(car);
    			
    			return Response.created(new URI("/carhire/car/" + car.getId())).build();
    			
		} catch (Exception e) {
			return Response.status(500).entity("Car not created !!").build();
			
		} // end try - catch

	} // end method
    
    
    @PUT
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateCar(Car car) {
    		try {
    			ds.updateCar(car);
    			
    			return Response.ok().entity(car).build();
    			
		} catch (Exception e) {
			return Response.notModified().entity("Car with id " + car.getId() + " not updated !!").build();
			
		} // end try - catch

	} // end method
    
    
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteCar(@PathParam("id") int id) {
    		Car car = null;
    		String msg;
    		try {
    			car = ds.readCar(id);
    			ds.deleteCar(car);
    			msg = "Car deleted successfully";
		} catch (Exception e) {
				e.printStackTrace();
				msg = "Car was not deleted";
				return Response.status(500).entity(msg).build();
				
		} // end try - catch
    		
        return Response.ok().entity(msg).build();
        
    } // end method
    
    
    
    
//  Inner classes
    @Path("cars")
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
        public List<Car> getCars() {
        		List<Car> cars = null;
        		try {
        			cars = ds.readCars();
	    		} catch (Exception e) {
	    				e.printStackTrace();
	    				
	    		} // end try - catch
        		
            return cars;
            
        } // end method
        
    } // end inner class
    
} // end class
