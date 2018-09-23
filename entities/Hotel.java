package hotel.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import hotel.credit.CreditCard;
import hotel.utils.IOUtils;

public class Hotel {
	
	private Map<Integer, Guest> guests;
	public Map<RoomType, Map<Integer,Room>> roomsByType;
	public Map<Long, Booking> bookingsByConfirmationNumber;
	public Map<Integer, Booking> activeBookingsByRoomId;
	
	
	public Hotel() 
	{
		guests = new HashMap<>();
		roomsByType = new HashMap<>();
		for (RoomType rt : RoomType.values()) 
		{
			Map<Integer, Room> rooms = new HashMap<>();
			roomsByType.put(rt, rooms);
		}
		bookingsByConfirmationNumber = new HashMap<>();
		activeBookingsByRoomId = new HashMap<>();
	}

	
	public void addRoom(RoomType roomType, int id) 
	{
		IOUtils.trace("Hotel: addRoom");
		for (Map<Integer, Room> rooms : roomsByType.values()) 
		{
			if (rooms.containsKey(id)) 
			{
				throw new RuntimeException("Hotel: addRoom : room number already exists");
			}
		}
		Map<Integer, Room> rooms = roomsByType.get(roomType);
		Room room = new Room(id, roomType);
		rooms.put(id, room);
	}

	
	public boolean isRegistered(int phoneNumber) 
	{
		return guests.containsKey(phoneNumber);
	}

	
	public Guest registerGuest(String name, String address, int phoneNumber) 
	{
		if (guests.containsKey(phoneNumber)) 
		{
			throw new RuntimeException("Phone number already registered");
		}
		Guest NewGuest = new Guest(name, address, phoneNumber);
		guests.put(phoneNumber, NewGuest);		
		return NewGuest;
	}

	
	public Guest findGuestByPhoneNumber(int phoneNumber) 
	{
		Guest NewGuest = guests.get(phoneNumber);
		return NewGuest;
	}

	
	public Booking findActiveBookingByRoomId(int roomId) 
	{
		Booking New_booking = activeBookingsByRoomId.get(roomId);;
		return New_booking;
	}


	public Room findAvailableRoom(RoomType selectedRoomType, Date arrivalDate, int stayLength) 
	{
		IOUtils.trace("Hotel: checkRoomAvailability");
		Map<Integer, Room> rooms = roomsByType.get(selectedRoomType);
		for (Room room : rooms.values()) 
		{
			IOUtils.trace(String.format("Hotel: checking room: %d",room.getId()));
			if (room.isAvailable(arrivalDate, stayLength)) 
			{
				return room;
			}			
		}
		return null;
	}

	
	public Booking findBookingByConfirmationNumber(long confirmationNumber) 
	{
		return bookingsByConfirmationNumber.get(confirmationNumber);
	}

	
	
	public long book(Room room, Guest guest, Date arrivalDate, int stayLength, int occupantNumber, CreditCard creditCard) 
	{

        Booking Newbooking = room.book(guest, arrivalDate, stayLength, occupantNumber, creditCard);
        long confirmation_No = Newbooking.getConfirmationNumber();
        bookingsByConfirmationNumber.put(confirmation_No, Newbooking);

        return confirmation_No;
	}

			
	

	
	public void checkin(long confirmationNumber) 
	{
       Booking New_booking=findBookingByConfirmationNumber(confirmationNumber);
      
        
        if (New_booking ==null) 
		{
            throw new RuntimeException("No booking confirmation available");
        }
        int roomId = New_booking.getRoomId();
        New_booking.checkIn();
        activeBookingsByRoomId.put(roomId, New_booking);
	}


	public void addServiceCharge(int roomId, ServiceType serviceType, double cost) 
	{
		 Booking New_booking = activeBookingsByRoomId.get(roomId);

        if (New_booking == null) 
		{
            throw new RuntimeException("No active booking associated with the room");
        }

        New_booking.checkOut();
        activeBookingsByRoomId.remove(roomId);

	}

	
	public void checkout(int roomId) 
	{
		Booking New_booking = activeBookingsByRoomId.get(roomId);
        if (New_booking == null) 
		{
            throw new RuntimeException("No active booking associated with the room");
        }
        New_booking.addServiceCharge(serviceType, cost);
	}


}
