import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Utf8ToStrLengthTest {

    @Test
    void testGodelLength(){
        //[71        195     182      100        101     108]
        //01000111 11000011 10110110 01100100 01100101 01101100 (godel in binary utf8)
        byte[] godelArray = {71, (byte)195, (byte)182, 100, 101, 108};
        int expected = 5;
        int result = Utf8ToStrLength.utf8ToStrLength(godelArray);
        Assertions.assertEquals(expected, result);

        //pĕpe - 01110000 11000100 10010101 01110000 01100101 - 112 196 149 112 101
        int exp4 = 4;
        byte[] pp = {(byte) 112,(byte) 196,( byte) 149, (byte) 112, (byte) 101};
        int r = Utf8ToStrLength.utf8ToStrLength(pp);
        Assertions.assertEquals(exp4,r);
    }

    @Test
    void testHwair(){
        byte[] hwair = { (byte) 240, (byte) 144, (byte) 141, (byte) 136 }; // F0 90 8D 88
        // --- 11110000 10010000 10001101 10001000
        int result = Utf8ToStrLength.utf8ToStrLength(hwair);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testCurrenciesSymbolsLengths(){
        byte[] usd = {36}; // 00100100
        byte[] eur = {(byte) 226, (byte) 130, (byte) 172}; // 11100010 10000010 10101100
        byte[] yen = {(byte) 194, (byte) 165}; // 11000010 10100101

        Assertions.assertEquals(1, Utf8ToStrLength.utf8ToStrLength(usd));
        Assertions.assertEquals(1, Utf8ToStrLength.utf8ToStrLength(eur));
        Assertions.assertEquals(1, Utf8ToStrLength.utf8ToStrLength(yen));
    }
}