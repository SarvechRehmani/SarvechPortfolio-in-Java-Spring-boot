package com.portfolio.sarvech.services.implementations;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.models.Image;
import com.portfolio.sarvech.repositories.ImageRepo;
import com.portfolio.sarvech.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageRepo imageRepo;

    private final Cloudinary cloudinary;

    private final AppConstants constants;

    public ImageServiceImpl(ImageRepo imageRepo, Cloudinary cloudinary, AppConstants constants) {
        this.imageRepo = imageRepo;
        this.cloudinary = cloudinary;
        this.constants = constants;
    }

    @Override
    public Image saveImage(Image image) {
        return this.imageRepo.save(image);
    }

    @Override
    public void deleteImage(long id) {
        Image image = this.imageRepo.findById(id).orElse(null);
       if(image == null) {
            this.logger.error("Cannot delete image: Image with id {} not found.", id);
            return;

       }
        if(this.deleteImageFromCloudinary(image.getPublicId())){
            this.logger.info("Image deleted from cloudinary with id : {}", image.getId());
        }
        this.imageRepo.delete(image);
        this.logger.info("Image deleted from database with id : {}", id);
    }

    @Override
    public String uploadImage(MultipartFile image, String fileName, String folder) {
        try{
            byte[] data = new byte[image.getInputStream().available()];
            image.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                    "public_id", fileName.substring(folder.length()),
                    "tags", folder,
                    "folder", folder
            ));
            if(folder.equals(this.constants.CLOUDINARY_CERTIFICATE_FOLDER)){
                return this.getUrlFromPublicIdForCertificate(fileName,this.constants.CERTIFICATE_WIDTH,this.constants.CERTIFICATE_HEIGHT);
            }
            return this.getUrlFromPublicId(fileName);
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary.url().transformation(
                new Transformation()
                        .width(this.constants.PROJECT_IMAGE_WIDTH)
                        .height(this.constants.PROJECT_IMAGE_HEIGHT)
                        .crop(this.constants.PROJECT_IMAGE_CROP)
        ).generate(publicId);
    }

    @Override
    public String getUrlFromPublicIdForCertificate(String publicId, int width, int height) {
        return cloudinary.url().transformation(
                new Transformation()
                        .width(width)
                        .height(height)
                        .crop(this.constants.PROJECT_IMAGE_CROP)
        ).generate(publicId);
    }

    @Override
    public boolean deleteImageFromCloudinary(String publicId) {
        if (publicId == null || publicId.isEmpty()) {
            logger.error("Cannot delete image: publicId is null or empty.");
            return false;
        }
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            logger.info("Successfully deleted image with publicId: {}", publicId);
            return true;
        } catch (IOException e) {
            logger.error("Error deleting image with publicId {}: {}", publicId, e.getMessage());
            return false;
        }
    }
}