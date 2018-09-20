/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import hotel.booking.BookingCTL;
import hotel.booking.BookingUI;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.RoomType;
import java.util.Date;
import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.ServiceCharge;
import hotel.entities.ServiceType;
import java.util.List;

/**
 *
 * @author MIHIRAN
 */
public class TestBooking {

    public static void main(String[] args) {
        //checkin_function();
        //addServiceCharge_function();
        //checkout_function();
        bookingController();
    }

    public static void bookingController(){
        
        //Guest guest = new Guest("Mihiran", "Melbourne", 426383884);

        Room room = new Room(1, RoomType.SINGLE);

        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 12345678, 345);

        //Booking booking = new Booking(guest, room, new Date(), 1, 1, creditCard);
        
        Hotel hotel = new Hotel();
        hotel.addRoom(RoomType.SINGLE, 1);
        BookingCTL bookingctl = new BookingCTL(hotel);
        
        CreditCardType creditCardType = creditCard.getType();
        int cardNumber = creditCard.getNumber();
        int cvv = creditCard.getCcv();
        
        System.out.println("--------------- Before executing creditDetailsEntered() method -------------");
        bookingctl.phoneNumberEntered(426383884);
        bookingctl.guestDetailsEntered("Mihiran", "Melbourne");
        bookingctl.roomTypeAndOccupantsEntered(RoomType.SINGLE, 1);
        bookingctl.bookingTimesEntered(new Date(), 1);
        
        bookingctl.creditDetailsEntered(creditCardType, cardNumber, cvv);
    }
    
    public static void addServiceCharge_function() {
        Guest guest = new Guest("Mihiran", "Melbourne", 426383884);

        Room room = new Room(1, RoomType.DOUBLE);

        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 12345678, 345);

        Booking booking = new Booking(guest, room, new Date(), 1, 2, creditCard);

        System.out.println("--------------- Before executing addServiceCharge() method -------------");
        printServiceCharges(booking);

        booking.checkIn();
        System.out.println("\nCalling addServiceCharge() function...\n");
        booking.addServiceCharge(ServiceType.ROOM_SERVICE, 500);
        
        System.out.println("--------------- After executing addServiceCharge() method -------------");
        printServiceCharges(booking);
    }

    public static void printServiceCharges(Booking booking) {
        List<ServiceCharge> serivceTypeList = booking.getCharges();
        System.out.println("Service charges list:");
        for (ServiceCharge serviceCharge : serivceTypeList) {
            System.out.println(serviceCharge.getType() + " : " + serviceCharge.getCost());
        }
    }

    public static void checkin_function() {
        Guest guest = new Guest("Mihiran", "Melbourne", 426383884);

        Room room = new Room(1, RoomType.DOUBLE);

        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 12345678, 345);

        Booking booking = new Booking(guest, room, new Date(), 1, 2, creditCard);

        System.out.println("--------------- Before executing checkin() method -------------");
        bookingState(booking);
        roomState(room);

        System.out.println("\nCalling checkin() function...\n");
        booking.checkIn();

        System.out.println("--------------- After executing checkin() method -------------");
        bookingState(booking);
        roomState(room);
    }
    
    public static void checkout_function() {
        Guest guest = new Guest("Mihiran", "Melbourne", 426383884);

        Room room = new Room(1, RoomType.DOUBLE);

        CreditCard creditCard = new CreditCard(CreditCardType.VISA, 12345678, 345);

        Booking booking = new Booking(guest, room, new Date(), 1, 2, creditCard);

        System.out.println("--------------- Before executing checkin() method -------------");
        bookingState(booking);
        roomState(room);

        System.out.println("\nCalling checkin() function...\n");
        booking.checkIn();

        System.out.println("--------------- After executing checkin() method -------------");
        bookingState(booking);
        roomState(room);
        
        System.out.println("\nCalling checkout() function...\n");
        booking.checkOut();
        
        System.out.println("--------------- After executing checkout() method -------------");
        bookingState(booking);
        roomState(room);
    }

    public static void roomState(Room room) {
        if (room.isReady()) {
            System.out.println("Room is available!");
        } else {
            System.out.println("Room is occupied!");
        }
    }

    public static void bookingState(Booking booking) {
        if (booking.isCheckedIn()) {
            System.out.println("Booking is checked IN");
        } else {
            System.out.println("Booking is not checked IN");
        }
    }
}
