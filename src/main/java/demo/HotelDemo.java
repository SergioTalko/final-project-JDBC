package demo;


import dao.HotelDAO;

public class HotelDemo {
    public static void main(String[] args) throws Exception {

        HotelDAO hotelDAO = new HotelDAO();
        System.out.println(hotelDAO.getAll());

    }
}
