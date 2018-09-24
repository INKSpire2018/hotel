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
        /*
 * This class will test the methods in class Room
 */
package hotel.entities;

import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.service.RecordServiceCTL;
import java.util.Date;

/**
 *
 * @author Chamath
 */
public class RoomTest 
{
    public static void main(String[] args) 
    {
        /**********************Test Data**************************/
        int stayLength = 2;
        int numOfOccupants = 1;
        int roomNumber = 500;
        String guestName = "Chamath";
        String address = "Endeavour Hills";
        int tel = 111111;
        int creditCardNumber = 12345;
        int ccv = 111;
        CreditCard creditCard = new CreditCard(CreditCardType.VISA, creditCardNumber,ccv);
        Room room=new Room(roomNumber, RoomType.DOUBLE);
        Guest guest = new Guest(guestName, address, tel);
        Date arrivalDate = new Date();
       /**********************Test Data**************************/ 
        
       //Create a new bookin instance
        Booking booking = new Booking(guest, room, arrivalDate, stayLength, numOfOccupants, creditCard);
        System.out.println("Checking the availability of room number " +roomNumber+" .......");
        System.out.println("Room id "+room.getId()+" is ready");
       
        //Test methods
       
        testBooking(room,guest,arrivalDate,stayLength,numOfOccupants,creditCard);
        // testCheckingIn(room,booking);
        //testCheckingOut(room,booking);
        //testRecordServiceController( room,booking,roomNumber);
       
    }
    public static void testBooking(Room room,Guest guest,Date arrivalDate,int stayLength,int numOfOccupants, CreditCard creditCard) 
    {
        Booking booking = new Booking(guest, room, arrivalDate, stayLength, numOfOccupants, creditCard);
        System.out.println("Making a booking...........");
        room.book(guest, arrivalDate, stayLength, numOfOccupants, creditCard);
        System.out.println("successfully booked in");
        System.out.println("Making a booking in the sameday at the same time.............");
        room.book(guest, arrivalDate, stayLength, numOfOccupants, creditCard);
        if(booking.isPending())
        {
            System.out.println("Booking days are conflicted");
        }
     }
    public static void testCheckingIn(Room room ,Booking booking) 
    {
        System.out.println("Making a checking...........");
        //Create a cheking
        room.checkin();
        //Change the bookin status to CHECKED_IN
        booking.checkIn();
        if(booking.isCheckedIn())
        {
            System.out.println("Successfully checked in");
        }
//        System.out.println("Check in the same room again...........");
//        //Checking same room again
//        room.checkin();
//        
    }
    public static void testCheckingOut(Room room ,Booking booking) 
    {
        testCheckingIn(room, booking);
        System.out.println("Checking out.......................");
        room.checkout(booking);
        if(booking.isCheckedOut())
        {
             System.out.println("successfully Checked out");
        }
//        System.out.println("Check out already checked out room ...........");
//        //Checking same room again
//        room.checkout(booking);
       
    }
     public static void testRecordServiceController(Room  room,Booking booking,int roomId) 
    {
        Hotel hotel = new Hotel();
        RecordServiceCTL recordServiceCTL = new RecordServiceCTL(hotel);
        //set active booking room id
        //hotel.activeBookingsByRoomId.put( roomId, booking);
        System.out.println("Check Room Id "+room.getId()+" for record Service Controller.........");
        recordServiceCTL.roomNumberEntered( roomId);
        
    }
}

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
