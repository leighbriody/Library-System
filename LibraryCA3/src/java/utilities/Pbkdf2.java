/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Code references https://adambard.com/blog/3-wrong-ways-to-store-a-password/
package utilities;

import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 *Methods to hash and salt password with a random salt
 * @author Brianan Johnson
 */
public class Pbkdf2 {

  
   /**
    * Author Brianan Johnson
    * @param password
    * @return Hashed password
    * @throws NoSuchAlgorithmException 
    * This exception is thrown when a particular cryptographic algorithm is requested but is not available in the environment.
    * @throws InvalidKeySpecException 
    * This is the exception for invalid key specifications.
    */
   public static String genPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        //Iteration strengthen the hash of the password
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();
         
        //Create an instance of the PBEKeySpec
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        //Create a secret key using the algorithm
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");        
        byte[] hash = skf.generateSecret(spec).getEncoded();
        
        //Add the iterations salt and hash and convert to Hex so it can be output in a string
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }
    
  /**
   * Method to generate a random salt meaning no two stored passwords
   * will be the same even if the original ones are
   * @return
   * @throws NoSuchAlgorithmException 
   * This exception is thrown when a particular cryptographic algorithm is requested but is not available in the environment.
   */
    public static byte[] getSalt() throws NoSuchAlgorithmException
    {
        //Generate a random salt using the algorithm 
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
    
    /**
     * Convert the hash bytes to Hex so it can be output as a string
    and does not ignore front trailing zeros which would happen if it was left as
    a BigInteger
     * @param array
     * @return Hex conversion of byte
     * @throws NoSuchAlgorithmException 
     * This exception is thrown when a particular cryptographic algorithm is requested but is not available in the environment.
     */
     public static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
   
  
     /**
      * Method to validate the original password against the stored password
      * @param originalPassword
      * @param storedPassword
      * @return True if password matches original, false if it does not
      * @throws NoSuchAlgorithmException
      * This exception is thrown when a particular cryptographic algorithm is requested but is not available in the environment.
      * @throws InvalidKeySpecException 
      * This is the exception for invalid key specifications.
      */
    
     public static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        //Split the stored password so it can check just the hashed password and not the salt whch will be 
        //different everytime
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
         
        //Create a testHash to check the array length against
        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();
         
        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
    
     /**
      * Method to convert the hex into bytes 
      * @param hex
      * @return
      * @throws NoSuchAlgorithmException 
      * This exception is thrown when a particular cryptographic algorithm is requested but is not available in the environment.
      */
     public static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
     
     /**
      * Method Testing creating and validating hashed and salted passwords
      * @param args
      * @throws NoSuchAlgorithmException
      * This exception is thrown when a particular cryptographic algorithm is requested but is not available in the environment.
      * @throws InvalidKeySpecException 
      * This is the exception for invalid key specifications.
      */
    
      public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException 
    {
  
        /*
        String  originalPassword = "password";
        String generatedSecuredPasswordHash = genPasswordHash(originalPassword);
        System.out.println(generatedSecuredPasswordHash.length());
        System.out.println(generatedSecuredPasswordHash);
          
         
        boolean matched = validatePassword(originalPassword, generatedSecuredPasswordHash);
        System.out.println("The password enter password is " + matched);
         
        matched = validatePassword("password1", generatedSecuredPasswordHash);
        System.out.println("The password enter password1 is " + matched);
        
        matched = validatePassword("fsdfs", generatedSecuredPasswordHash);
        System.out.println("The password enter fsdfs is " + matched);
        */
        
        
        
        
        
    }
    
     //Confirmation that the same password results with a different hash each time
     
    //1000:db64d2eebfc8390936e9c0ed47daff4a:70cbaaf593cc141978ca0d050dfe5cf494a9d62a290183bb13a3252e19057670f8c34aec4a1f32655955bd8898aa52a2c3c520e9c7188e5b281c95f70786a36c
    //1000:c3a29fbbc15bfdcddf8cb33c6d122126:81a85a84013b26673d0dd17838836874bfb17572c43a4c6369727d32744568cc7ddd394301516f8f4c2595245fcb5d452daedc59ab89b4c1642b2208044f161f
}