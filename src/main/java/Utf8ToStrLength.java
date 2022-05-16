/*
Write a function that accepts a byte array corresponding to a UTF-8 encoded string,
and then returns the length of that string (i.e. number of Unicode characters).
 For example, the byte array [71 195 182 100 101 108] corresponds to the string ”Gödel”.
 Given this byte array (of length six), your function should return 5.

 Please do not use any builtin or third party libraries for working with UTF-8 encoded strings.
 Instead, your function should directly interpret the input data using bitwise operations for integers.

 */

public class Utf8ToStrLength {

    public static int utf8ToStrLength(byte[] utf8Bytes){
        int hexC0 = 0xC0;//192 base 10 :: 1100 0000 binary
        int hex80 = 0x80;//128 base 10 :: 1000 0000 binary

        int lengthOfStr = 0;
        for (byte b: utf8Bytes){
            if ( (hexC0 & b) != hex80 ){ //if it is not a continuation octet increase lengthCount
                lengthOfStr++;
            }
        }
        return lengthOfStr;
    }
}