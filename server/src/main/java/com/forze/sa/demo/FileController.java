package com.forze.sa.demo;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class FileController {

    @Autowired
    FileRepository repository;

    @PostMapping("/upload")
    @CrossOrigin(origins = "http://localhost:4200")
    public String upload(@RequestParam("file") MultipartFile file){
        try {
            File newfile = new File(file.getOriginalFilename(),file.getContentType(), file.getBytes());
            repository.save(newfile);
            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/getall")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<File> getAllOfFile(){
        return repository.findAll();
    }

    @GetMapping("/img")
    @CrossOrigin(origins = "http://localhost:4200")
    public void showImage(@RequestParam("id") Long itemId, HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        File item = repository.getOne(itemId);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(item.getPic());
        response.getOutputStream().close();
    }

}
