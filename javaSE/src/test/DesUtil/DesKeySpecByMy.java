package test.DesUtil;


import java.security.InvalidKeyException;
import java.security.spec.KeySpec;

public class DesKeySpecByMy implements KeySpec {
    public static final int DES_KEY_LEN = 8;
    private byte[] key;
    private static final byte[][] WEAK_KEYS = new byte[][]{{1, 1, 1, 1, 1, 1, 1, 1}, {-2, -2, -2, -2, -2, -2, -2, -2}, {31, 31, 31, 31, 14, 14, 14, 14}, {-32, -32, -32, -32, -15, -15, -15, -15}, {1, -2, 1, -2, 1, -2, 1, -2}, {31, -32, 31, -32, 14, -15, 14, -15}, {1, -32, 1, -32, 1, -15, 1, -15}, {31, -2, 31, -2, 14, -2, 14, -2}, {1, 31, 1, 31, 1, 14, 1, 14}, {-32, -2, -32, -2, -15, -2, -15, -2}, {-2, 1, -2, 1, -2, 1, -2, 1}, {-32, 31, -32, 31, -15, 14, -15, 14}, {-32, 1, -32, 1, -15, 1, -15, 1}, {-2, 31, -2, 31, -2, 14, -2, 14}, {31, 1, 31, 1, 14, 1, 14, 1}, {-2, -32, -2, -32, -2, -15, -2, -15}};

    public DesKeySpecByMy(byte[] var1) throws InvalidKeyException {
        this(var1, 0);
    }

    public DesKeySpecByMy(byte[] var1, int var2) throws InvalidKeyException {
        this.key = new byte[8];
        System.arraycopy(var1, var2, this.key, 0, 8);
    }

    public byte[] getKey() {
        return (byte[])this.key.clone();
    }

    public static boolean isParityAdjusted(byte[] var0, int var1) throws InvalidKeyException {
        if (var0 == null) {
            throw new InvalidKeyException("null key");
        } else if (var0.length - var1 < 8) {
            throw new InvalidKeyException("Wrong key size");
        } else {
            for(int var2 = 0; var2 < 8; ++var2) {
                int var3 = Integer.bitCount(var0[var1++] & 255);
                if ((var3 & 1) == 0) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isWeak(byte[] var0, int var1) throws InvalidKeyException {
        if (var0 == null) {
            throw new InvalidKeyException("null key");
        } else if (var0.length - var1 < 8) {
            throw new InvalidKeyException("Wrong key size");
        } else {
            for(int var2 = 0; var2 < WEAK_KEYS.length; ++var2) {
                boolean var3 = true;

                for(int var4 = 0; var4 < 8 && var3; ++var4) {
                    if (WEAK_KEYS[var2][var4] != var0[var4 + var1]) {
                        var3 = false;
                    }
                }

                if (var3) {
                    return var3;
                }
            }

            return false;
        }
    }
}
