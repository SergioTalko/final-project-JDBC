package demo;


import dao.HotelDAO;
import entity.Hotel;

public class HotelDemo {
    public static void main(String[] args) throws Exception {

        Hotel hotel = new Hotel("HH","jj","jj","dfd");
        HotelDAO hotelDAO = new HotelDAO();
        System.out.println(hotelDAO.add(hotel));

    }
}
