package com.care4memory.memorynest.controller;

import com.care4memory.memorynest.dto.AlbumDTO;
import com.care4memory.memorynest.dto.AlbumItemDTO;
import com.care4memory.memorynest.error.UserNotFound;
import com.care4memory.memorynest.service.MemorySpotService;
import com.care4memory.memorynest.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/memoryspot")
public class MemorySpotController {

    private final MemorySpotService memorySpotService;
    private final UserRoleService userRoleService;

    public MemorySpotController(MemorySpotService memorySpotService, UserRoleService userRoleService) {
        this.memorySpotService = memorySpotService;
        this.userRoleService = userRoleService;
    }

    @GetMapping(value = "/albums")
    public ResponseEntity<List<AlbumDTO>> getAlbums() throws Exception {
        String loggedInUserEmail = getLoggedInUserEmail();
        List<AlbumDTO> albums = this.memorySpotService.getAllAlbums(loggedInUserEmail);
        // Return the list of albums with HTTP 200 OK status
        return ResponseEntity.ok(albums);
    }

    @PostMapping(value = "/albums")
    public ResponseEntity<String> saveAlbum(@RequestParam("name") String albumName,
                                            @RequestParam("pictures") List<MultipartFile> pictures) throws Exception {
        String loggedInUserEmail = getLoggedInUserEmail();
        List<String> filesUploaded = this.memorySpotService.saveAlbum(loggedInUserEmail, albumName, pictures);
        // Return the list of albums with HTTP 200 OK status
        if(filesUploaded.isEmpty()) {
            return ResponseEntity.internalServerError()
                    .body("Sorry, something went wrong while creating album.");
        } else {
            return ResponseEntity.ok("Successfully created the album "+albumName);
        }
    }

    @GetMapping("/albums/{albumName}")
    public ResponseEntity<List<AlbumItemDTO>> getAlbumContent(@PathVariable String albumName) throws Exception {
        // This should come from the logged-in user's details
        String loggedInUserEmail = getLoggedInUserEmail();
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
        String loggedInUserEmail = getLoggedInUserEmail();
        AlbumDTO savedAlbum = this.memorySpotService.addAlbum(loggedInUserEmail,
                albumName, file);
        // Return the saved album item with HTTP 201 Created status
        return ResponseEntity
                .status(201)
                .body(savedAlbum);
    }

    private String getLoggedInUserEmail() throws UserNotFound {
        return userRoleService.getUserInfo().getEmail();
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handlerUserNotFound(UserNotFound userNotFound) {
        return ResponseEntity.badRequest().body(userNotFound.getMessage());
    }
}
