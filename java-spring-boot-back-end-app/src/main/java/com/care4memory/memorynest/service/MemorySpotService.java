package com.care4memory.memorynest.service;

import com.care4memory.memorynest.dto.AlbumDTO;
import com.care4memory.memorynest.dto.AlbumItemDTO;
import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MemorySpotService {

    private final Cloudinary cloudinary;

    public MemorySpotService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public List<AlbumDTO> getAllAlbums(String loggedInUserEmail) throws Exception {
        // Logic to retrieve all albums
        ApiResponse response = cloudinary.api()
                .subFolders(getFolderPath(loggedInUserEmail), null);

        List<Map> albumsDetails = (List<Map>) response.get("folders");
        return albumsDetails
                .stream()
                .map(this::mapToAlbumDTO)
                .toList();
    }

    public List<String> saveAlbum(String loggedInUserEmail, String albumName, List<MultipartFile> pictures) {
        String folderPath = getFolderPath(loggedInUserEmail) + "/" + albumName;
        Map inputProperties = ObjectUtils.asMap("resource_type", "image",
                "overwrite", false,
                "folder", folderPath);
        List<String> filesUploaded = new ArrayList<>();
        for(MultipartFile picture: pictures) {
            try {
                inputProperties.put("public_id", generateId(picture.getOriginalFilename()));
                cloudinary.uploader().upload(picture.getBytes(), inputProperties);
                filesUploaded.add(picture.getOriginalFilename());
            } catch (Exception e) {
                // continue with next picture
                e.printStackTrace();
            }
        }
        return filesUploaded;
    }

    private String generateId(String originalFileName){
        return System.currentTimeMillis() + "_" + originalFileName.substring(originalFileName.lastIndexOf("/")+1);
    }

    public List<AlbumItemDTO> getAlbumContent(String loggedInUserEmail, String albumName) throws Exception {
        // Logic to retrieve an album by its ID
        String folderPath = getFolderPath(loggedInUserEmail) + "/" + albumName;
        Map input = ObjectUtils.asMap("resource_type", "image", "folder", folderPath, "max_results", 30);
        ApiResponse response = cloudinary.api().resourcesByAssetFolder(folderPath, input);
        List<Map> resources = (List<Map>) response.get("resources");
        return resources
                .stream()
                .map(this::mapToAlbumItemDTO)
                .toList();
    }

    public AlbumDTO addAlbum(String loggedInUserEmail, String albumName, MultipartFile file) throws Exception {
        // Logic to save a new album
        String folderPath = getFolderPath(loggedInUserEmail) + "/" + albumName;
        Map uploadResult = cloudinary.uploader()
                .upload(file.getBytes(),
                        ObjectUtils.asMap("folder", folderPath));

        return mapToAlbumDTO(uploadResult);
    }

    public void deleteAlbum(String loggedInUserEmail, String albumName) throws Exception {
        // Logic to delete an album by its ID
        String folderPath = getFolderPath(loggedInUserEmail) + "/" + albumName;
        Map deleteAlbum = cloudinary.api()
                .deleteResourcesByPrefix(folderPath + "/",
                        ObjectUtils.asMap("resource_type", "image")
                );

        cloudinary.api().deleteFolder(folderPath, ObjectUtils.emptyMap());
    }

    public void deleteAlbumItem(String id) throws Exception {
        // Logic to delete an album item by its ID
        cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    }

    private String getFolderPath(String loggedInUserEmail) {
        return loggedInUserEmail.trim().replace("@", "-")
                .replace(".", "-").replaceAll("_", "-");
    }

    private AlbumDTO mapToAlbumDTO(Map albumList) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setName((String) albumList.get("name"));
        return albumDTO;
    }

    private AlbumItemDTO mapToAlbumItemDTO(Map albumDetails) {
        AlbumItemDTO albumItemDTO = new AlbumItemDTO();
        albumItemDTO.setId((String) albumDetails.get("asset_id"));
        albumItemDTO.setName((String) albumDetails.get("display_name"));
        albumItemDTO.setUrl((String) albumDetails.get("secure_url"));
        albumItemDTO.setWidth((int) albumDetails.get("width"));
        albumItemDTO.setHeight((int) albumDetails.get("height"));
        return albumItemDTO;
    }

}
