package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.Image;
import org.springframework.web.multipart.MultipartFile;


public interface ImageService {

    Image saveImage(Image image);
    void deleteImage(long id);

//    Cloudinary
    public String uploadImage(MultipartFile image, String fileName);
    public String getUrlFromPublicId(String publicId);
    public boolean deleteImageFromCloudinary(String publicId);
}
