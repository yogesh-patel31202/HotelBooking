package com.My.HotelBooking.Controller;

import com.My.HotelBooking.Entity.HomeSlider;
import com.My.HotelBooking.Service.HomeSliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/sliders")
public class HomeSliderController {

    @Autowired
    private HomeSliderService homeSliderService;

    @GetMapping
    public ResponseEntity<List<HomeSlider>> getAllSliders() {
        return ResponseEntity.ok(homeSliderService.getAllSliders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomeSlider> getSliderById(@PathVariable long id) {
        return homeSliderService.getSliderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/parameter")
    public ResponseEntity<HomeSlider> createSlider(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image) {

        HomeSlider homeSlider = new HomeSlider();
        homeSlider.setTitle(title);
        homeSlider.setDescription(description);

        try {
            HomeSlider savedSlider = homeSliderService.saveSlider(homeSlider, image);
            return ResponseEntity.ok(savedSlider);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/part")
    public ResponseEntity<HomeSlider> createSliderUsingPart(
            @RequestPart("homeSlider") HomeSlider homeSlider,@RequestPart("image") MultipartFile image) {

        try {
            HomeSlider savedSlider = homeSliderService.saveSlider(homeSlider, image);
            return ResponseEntity.ok(savedSlider);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlider(@PathVariable long id) {
        homeSliderService.deleteSlider(id);
        return ResponseEntity.ok().build();
    }
}
