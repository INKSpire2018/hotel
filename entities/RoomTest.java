package hotel.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Hasith Malinga
 */
public class RoomTest {
    public static void main(String[] args) {
                
        testBookMethod();   
        Booking booking = testCheckInMethod();
        testCheckOutMethod(booking);
                
    }
    
    private static void testBookMethod(){
        String bookingState;
        Date arrivalDate = Calendar.getInstance().getTime();
        
        Guest guest = new Guest("Hasith", "20, Dandenong VIC", 452455019);
        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 12345678, 666);
        
        //allocate a new room
        Room room = new Room(1, RoomType.DOUBLE);
        //make a booking for the room
        Booking booking = new Booking(guest, room, arrivalDate, 5, 3, creditCard);
        
        System.out.println("Testing book() method in Room.java");
        System.out.println();
        
        System.out.println("--------Before Booking--------");
        
        System.out.println("Availability : " + room.isAvailable(arrivalDate, 5));
        
        bookingState = booking.isPending() ? "PENDING" : "OTHER";        
        System.out.println("Booking State : " + bookingState);
        
        System.out.println();
        System.out.println("Calling book() method.....");
        booking = room.book(guest, arrivalDate, 5, 3, creditCard);
        System.out.println();
        
        System.out.println("--------After Booking--------");        
        
        System.out.println("Availability : " + room.isAvailable(arrivalDate, 5));
        
        bookingState = booking.isPending() ? "PENDING" : "OTHER";
        System.out.println("Booking State : " + bookingState);
        System.out.println();
    }
    
    private static Booking testCheckInMethod(){
        String bookingState;
        String roomState;
        
        Date arrivalDate = Calendar.getInstance().getTime();
        
        Guest guest = new Guest("Hasith", "20, Dandenong VIC", 452455019);
        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 12345678, 666);
        
        //allocate a new room
        Room room = new Room(1, RoomType.DOUBLE);
        //make a booking for the room
        Booking booking = new Booking(guest, room, arrivalDate, 5, 3, creditCard);
        
        System.out.println("Testing checkin() method in Room.java");
        System.out.println();
        
        System.out.println("--------Before Check In--------");
        
        roomState = room.isReady() ? "READY" : "OCCUPIED";
        System.out.println("Room State : " + roomState);        
        
        bookingState = booking.isCheckedIn() ? "CHECKED_IN" : "NOT CHECKED_IN";
        System.out.println("Booking State : " + bookingState);
        
        System.out.println();
        System.out.println("Calling checkin() method.....");
        booking.checkIn();
        System.out.println();
        
        System.out.println("--------After Check In--------");        
        
        roomState = room.isReady() ? "READY" : "OCCUPIED";
        System.out.println("Room State : " + roomState); 
        
        bookingState = booking.isCheckedIn() ? "CHECKED_IN" : "NOT CHECKED_IN";
        System.out.println("Booking State : " + bookingState);
        System.out.println();
        
        return booking;
    }
    
    private static void testCheckOutMethod(Booking booking){
        String bookingState;
        String roomState;
        
        //get the room which belongs to the booking
        Room room = booking.getRoom();
        
        System.out.println("Testing checkout() method in Room.java");
        System.out.println();
        
        System.out.println("--------Before Check Out--------");
        
        roomState = room.isReady() ? "READY" : "OCCUPIED";
        System.out.println("Room State : " + roomState);        
        
        bookingState = booking.isCheckedIn() ? "CHECKED_IN" : "NOT CHECKED_IN";
        System.out.println("Booking State : " + bookingState);
        
        System.out.println();
        System.out.println("Calling booking.checkOut() method.....");
        booking.checkOut();
        System.out.println();
        
        System.out.println("--------After Checked out--------");        
        
        roomState = room.isReady() ? "READY" : "OCCUPIED";
        System.out.println("Room State : " + roomState); 
        
        bookingState = booking.isCheckedOut() ? "CHECKED_OUT" : "NOT CHECKED_OUT";
        System.out.println("Booking State : " + bookingState);
        System.out.println();
    }
}
