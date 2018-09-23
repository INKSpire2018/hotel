/*
 * This class will test the methods in class Room
 */
package hotel.entities;

import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
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
       //Test booking method
        testBooking(room,guest,arrivalDate,stayLength,numOfOccupants,creditCard);
        //Test checking method
        testCheckingIn(room,booking);
        //Test checkout method
        testCheckingOut(room,booking);
       
    }
    public static void testBooking(Room room,Guest guest,Date arrivalDate,int stayLength,int numOfOccupants, CreditCard creditCard) 
    {
        System.out.println("Making a booking...........");
        room.book(guest, arrivalDate, stayLength, numOfOccupants, creditCard);
        System.out.println("successfully booked in");
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
    }
    public static void testCheckingOut(Room room ,Booking booking) 
    {
        System.out.println("Checking out.......................");
        room.checkout(booking);
        if(booking.isCheckedOut())
        {
             System.out.println("successfully Checked out");
        }
       
    }
}
