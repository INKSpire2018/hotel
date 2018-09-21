

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import CreditCard;
import credit.CreditCardType;
import entities.Booking;
import entities.Guest;
import entities.Room;
import entities.RoomType;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Hasith Malinga
 */
public class RoomTest {
    public static void main(String[] args) {
        Date arrivalDate = Calendar.getInstance().getTime();
        Guest guest = new Guest("Hasith", "20, Dandenong VIC", 452455019);
        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 12345678, 666);
        Room room = new Room(1, RoomType.DOUBLE);
        System.out.println("Before Booking");
        System.out.println("Availability : " + room.isAvailable(arrivalDate, 5));
        room.isAvailable(arrivalDate, 5);
        Booking booking = room.book(guest, arrivalDate, 5, 3, creditCard);
        System.out.println("After Booking");        
        System.out.println("Availability : " + room.isAvailable(arrivalDate, 5));
        System.out.println("Booking State : " + booking.isPending());
        
    }
}
