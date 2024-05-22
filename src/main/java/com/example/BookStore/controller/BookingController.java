package com.example.BookStore.controller;

import com.example.BookStore.models.Booking;
import com.example.BookStore.service.BookingService;
import com.example.BookStore.service.BookingStatusService;
import com.example.BookStore.service.ProductService;
import com.example.BookStore.service.UsersService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookingController {

    private final BookingService bookingService;
    private final ProductService productService;
    private final UsersService usersService;
    private final BookingStatusService bookingStatusService;

    public BookingController(BookingService bookingService, ProductService productService, UsersService usersService, BookingStatusService bookingStatusService) {
        this.bookingService = bookingService;
        this.productService = productService;
        this.usersService = usersService;
        this.bookingStatusService = bookingStatusService;
    }

    @RequestMapping("/bookingM")
    public String getBookingsM(Model model, @Param("keyWord1") String keyWord1, @Param("keyWord2") String keyWord2, @Param("keyWord3") String keyWord3, @Param("keyWord4") String keyWord4, @Param("keyWord5") String keyWord5) {

        model.addAttribute("bookingList",bookingService.getBookings(keyWord1,keyWord2,keyWord3, keyWord4, keyWord5));
        model.addAttribute("keyWord1",keyWord1);
        model.addAttribute("keyWord2",keyWord2);
        model.addAttribute("keyWord3",keyWord3);
        model.addAttribute("keyWord4",keyWord4);
        model.addAttribute("keyWord5",keyWord5);

        return "bookingM";
    }

    @RequestMapping("/bookingC")
    public String getBookingsC(Model model) {

        model.addAttribute("bookingList",bookingService.getBookings(null,null,null,null,null));

        return "bookingC";
    }

    @RequestMapping("/newBooking/{id}")
    public String showFormNewBooking(Model model,@PathVariable(name="id") Long idProduct){
        Booking booking = new Booking();
        booking.setProduct(productService.findProductById(idProduct));

        model.addAttribute("Booking", booking);

        return "new_booking";
    }

    @RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
    public String saveBooking(@ModelAttribute("Booking") Booking booking) {
        booking.setUsers(usersService.findUsersById(1L));
        booking.setBookingStatus(bookingStatusService.findBookingStatustById(1L));
        booking.setQuantity(1);
        bookingService.save(booking);
        return "redirect:/bookingC";
    }

    @RequestMapping("/editBooking/{id}")
    public ModelAndView showFormUpdateBooking(@PathVariable(name="id") Long id){
        ModelAndView model = new ModelAndView("edit_booking");
        Booking booking = bookingService.findBookingById(id);
        model.addObject("Booking", booking);
        return model;
    }

    @RequestMapping("/updateStatusBooking/{id}/{status}")
    public String UpdateStatusBooking(@PathVariable(name="id") Long id, @PathVariable(name="status") Long status, RedirectAttributes attribute){

        Booking booking = bookingService.findBookingById(id);

        if(booking.getBookingStatus().getId() == 5 && status == 4)
        {
            attribute.addFlashAttribute("error", "You cannot cancel an order with status 'IN_DELIERY'");
            return "redirect:/bookingC";
        }

        if(booking.getBookingStatus().getId() == 4 && status == 2)
        {
            attribute.addFlashAttribute("error", "You cannot reject an order with status 'CANCELLED'");
            return "redirect:/bookingM";
        }

        if(booking.getBookingStatus().getId() == 4 && status == 4)
        {
            attribute.addFlashAttribute("error", "You cannot cancel an order with status 'CANCELLED'");
            return "redirect:/bookingC";
        }

        bookingService.updateStatusBooking(id,status);

        if(status == 4)
            return "redirect:/bookingC";
        return "redirect:/bookingM";
    }

    @RequestMapping(value = "/updateBooking", method = RequestMethod.POST)
    public String UpdateBooking(@ModelAttribute("Booking") Booking booking){
        bookingService.updateBooking(booking.getId(), booking);

        return "redirect:/bookingC";
    }

    @RequestMapping("/deleteBooking/{id}")
    public String deletBooking(@PathVariable(name="id") Long id){
        bookingService.deleteBooking(id);
        return "redirect:/bookingC";
    }
}
