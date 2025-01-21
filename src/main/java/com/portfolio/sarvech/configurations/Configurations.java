package com.portfolio.sarvech.configurations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class Configurations {

    @Value("${cloudinary.cloud.name}")
    private String cloudinaryCloudName;
    @Value("${cloudinary.cloud.API_Key}")
    private String cloudinaryApiKey;
    @Value("${cloudinary.cloud.secret}")
    private String cloudinaryApiSecret;

    @Bean
    public Cloudinary cloudinary(){
        Map cloudConfig = ObjectUtils.asMap(
                "cloud_name", cloudinaryCloudName,
                "api_key", cloudinaryApiKey,
                "api_secret", cloudinaryApiSecret
        );
        return new Cloudinary(cloudConfig);
    }
}
