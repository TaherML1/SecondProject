package com.example.secondproject;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.exception.SdkException;
import java.nio.file.Paths;

@Service
public class S3Service {
    private final S3Client s3Client;
    private final String bucketName = "taher";

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }


    public void uploadImage(String key, String filePath) {
        try {
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build(),
                    RequestBody.fromFile(Paths.get(filePath))
            );
        } catch (SdkException e) {

            System.err.println("Failed to upload image: " + e.getMessage());
        }
    }


    public String getImageUrl(String key) {
        try {

            if (key == null || key.isEmpty()) {
                return "";
            }
            return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(key)).toExternalForm();
        } catch (SdkException e) {
            System.err.println("Failed to retrieve image URL: " + e.getMessage());
            return "";
        }
    }


    public void deleteImage(String key) {
        try {
            s3Client.deleteObject(builder -> builder.bucket(bucketName).key(key));
        } catch (SdkException e) {

            System.err.println("Failed to delete image: " + e.getMessage());
        }
    }
}
