package com.care4memory.memorynest.controller;

import com.care4memory.memorynest.dto.AlbumDTO;
import com.care4memory.memorynest.dto.AlbumItemDTO;
import com.care4memory.memorynest.service.MemorySpotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("memoryspot")
public class MemorySpotController {

    private final MemorySpotService memorySpotService;

    public MemorySpotController(MemorySpotService memorySpotService) {
        this.memorySpotService = memorySpotService;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<AlbumDTO>> getAlbums() throws Exception {
        // This should come from the logged-in user's details
        String loggedInUserEmail = "john.doe@gmail.com";
        List<AlbumDTO> albums = this.memorySpotService.getAllAlbums(loggedInUserEmail);
        // Return the list of albums with HTTP 200 OK status
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/albums/{albumName}")
    public ResponseEntity<List<AlbumItemDTO>> getAlbumById(@PathVariable String albumName) throws Exception {
        // This should come from the logged-in user's details
        String loggedInUserEmail = "john.doe@gmail.com";
        List<AlbumItemDTO> albumItemDTO = this.memorySpotService.getAlbumContent(loggedInUserEmail, albumName);
        if (albumItemDTO == null) {
            // Return HTTP 404 Not Found if album is not found
            return ResponseEntity.notFound().build();
        }
        // Return the album content with HTTP 200 OK status
        return ResponseEntity.ok(albumItemDTO);
    }

    @PostMapping("/albums/{albumName}/items")
    public ResponseEntity<AlbumDTO> addAlbumItem(@PathVariable String albumName,
                                                 @RequestParam("file") MultipartFile file)
            throws Exception {
        // This should come from the logged-in user's details
        String loggedInUserEmail = "john.doe@gmail.com";
        AlbumDTO savedAlbum = this.memorySpotService.addAlbum(loggedInUserEmail,
                albumName, file);
        // Return the saved album item with HTTP 201 Created status
        return ResponseEntity
                .status(201)
                .body(savedAlbum);
    }
}
