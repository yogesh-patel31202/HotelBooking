package com.My.HotelBooking.Service;

import com.My.HotelBooking.Entity.HomeSlider;
import com.My.HotelBooking.Repository.HomeSliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class HomeSliderService {

    private final String UPLOAD_DIR = "src/main/resources/static/images/";

    @Autowired
    private HomeSliderRepository homeSliderRepository;

    public List<HomeSlider> getAllSliders() {
        return homeSliderRepository.findAll();
    }

    public Optional<HomeSlider> getSliderById(long id) {
        return homeSliderRepository.findById(id);
    }

    public HomeSlider saveSlider(HomeSlider homeSlider, MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            String fileName = image.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.write(filePath, image.getBytes());
            homeSlider.setImageUrl("/images/" + fileName);
        }
        return homeSliderRepository.save(homeSlider);
    }

    public void deleteSlider(long id) {
        homeSliderRepository.deleteById(id);
    }
}
