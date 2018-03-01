package entity;

import java.util.Date;

public class Filter {

    private int numberOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Date dateAvailableFrom;
    private String hotelName;
    private String country;
    private String city;

    public Filter() {
    }

    public Filter(int numberOfGuests, double price, boolean breakfastIncluded, boolean petsAllowed, Date dateAvailableFrom, String hotelName, String country, String city) {
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotelName = hotelName;
        this.country = country;
        this.city = city;
    }

    public boolean findRoomByFilter(Room room) {
        if (numberOfGuests != 0 && room.getNumberOfGuests() != numberOfGuests) return false;
        if (price != 0 && room.getPrice() != price) return false;
        if (breakfastIncluded != room.isBreakfastIncluded()) return false;
        if (petsAllowed != room.isPetsAllowed()) return false;
        if (dateAvailableFrom != null && dateAvailableFrom.getTime() < room.getDateAvailableFrom().getTime())
            return false;
        if (hotelName != null && !hotelName.equals(room.getHotel().getName())) return false;
        if (country != null && !country.equals(room.getHotel().getCountry())) return false;
        if (city != null && !city.equals(room.getHotel().getCity())) return false;

        return true;


    }
}
