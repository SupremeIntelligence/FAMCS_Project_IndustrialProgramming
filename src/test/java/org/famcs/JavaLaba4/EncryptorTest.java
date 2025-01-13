package org.famcs.JavaLaba4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncryptorTest {

    public static void main(String[] args) {
        String originalFilePath = "testfile.txt";
        String encryptedFilePath = "testfile.txt.enc";

        createTestFile(originalFilePath);

        Encryptor encryptor = new Encryptor();

        System.out.println("Encrypting file...");
        encryptor.encrypt(originalFilePath);

        File encryptedFile = new File(encryptedFilePath);
        if (encryptedFile.exists()) {
            System.out.println("File encrypted successfully.");

            System.out.println("Decrypting file...");
            encryptor.decrypt(encryptedFilePath);

            File decryptedFile = new File(originalFilePath);
            if (decryptedFile.exists()) {
                System.out.println("File decrypted successfully.");
            } else {
                System.out.println("Decrypted file not found.");
            }
        } else {
            System.out.println("Encryption failed. Encrypted file not found.");
        }
    }

    private static void createTestFile(String path) {
        String content = "This is a test file for encryption and decryption.";
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(content.getBytes());
            System.out.println("Test file created at: " + path);
        } catch (IOException e) {
            System.out.println("Error creating test file: " + e.getMessage());
        }
    }
}
