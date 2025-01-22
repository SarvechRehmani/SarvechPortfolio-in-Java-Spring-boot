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

    private ImageRepo imageRepo;

    private final Cloudinary cloudinary;

    private final AppConstants constants;

    public ImageServiceImpl(Cloudinary cloudinary, AppConstants constants) {
        this.cloudinary = cloudinary;
        this.constants = constants;
    }

    @Override
    public Image saveImage(Image image) {
        return this.imageRepo.save(image);
    }

    @Override
    public void deleteImage(long id) {
        Image image = this.imageRepo.findById(id).get();
        if(this.deleteImageFromCloudinary(image.getPublicId())){
            this.logger.info("Image deleted from cloudinary with id : {}", image.getId());
        }
        this.imageRepo.delete(image);
        this.logger.info("Image deleted from database with id : {}", id);
    }

    @Override
    public String uploadImage(MultipartFile image, String fileName) {
        try{
            byte[] data = new byte[image.getInputStream().available()];
            image.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                    "public_id", fileName.substring(this.constants.CLOUDINARY_PROJECT_IMAGE_FOLDER.length()),
                    "tags", "project_images",
                    "folder", "project_images"
            ));
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