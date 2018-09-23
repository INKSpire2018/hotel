package hotel.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hotel.credit.CreditCard;
import hotel.utils.IOUtils;
import java.util.Iterator;

/**
 *
 * @author Chamath
 */
public class Room {

    private Exception RuntimeException(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	private enum State {READY, OCCUPIED}
	
	int id;
	RoomType roomType;
	private List<Booking> bookings;
	State state;

	
	public Room(int id, RoomType roomType) {
		this.id = id;
		this.roomType = roomType;
		bookings = new ArrayList<>();
		state = State.READY;
	}
	

	public String toString() {
		return String.format("Room : %d, %s", id, roomType);
	}


	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return roomType.getDescription();
	}
	
	
	public RoomType getType() {
		return roomType;
	}
	//Check the availability of booking depending on arrivaldate and staylength
	public boolean isAvailable(Date arrivalDate, int stayLength) {
		IOUtils.trace("Room: isAvailable");
		for (Booking b : bookings) {
			if (b.doTimesConflict(arrivalDate, stayLength)) {
				return false;
			}
		}
		return true;
	}
	
	
	public boolean isReady() {
		return state == State.READY;
                
	}


	public Booking book(Guest guest, Date arrivalDate, int stayLength, int numberOfOccupants, CreditCard creditCard) {
            
            //create a Booking instance
            Booking booking = new Booking(guest, this, arrivalDate, stayLength, numberOfOccupants, creditCard);
            //If arrival date and stay length clashes from existing booking   
            if(!isAvailable(arrivalDate, stayLength))
            {
               //Set booking status to PENDING
               booking.isPending();
            }
            //insert the new booking into booking list
            bookings.add(booking);
         
            
       //return booking
        return booking;		
	}


	public void checkin() 
        {
            //Throw run time exception if the status of the room is not READY
            if (isReady()==false) 
            {
               throw new RuntimeException("Room is not ready");  
            }   
            //Change the status of the room to OCCUPIED
            state=State.OCCUPIED;
            
         }


	public void checkout(Booking booking)
        {
            //Throw run time exception if the status of the room is not Occupied or ready
            if (isReady()) 
            {
               throw new RuntimeException("Room is not occupied");  
            }  
            //Remove the booking from the booking list using an iterator
            Iterator<Booking>  iterator =  bookings.iterator();
            while (iterator.hasNext()) 
            {
                iterator.next();
                iterator.remove();
            }
            
            //Change the status of the room to READY
            state=State. READY;	
            //Change the booking status to CHECK_OUT
            booking.checkOut();
            
        }


}
