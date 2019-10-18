package cold.face.others;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil
{
	public static String AESDecrypt(String text, String iv, String password)
			throws Exception
	{
		String result = AESDecrypt(text, iv, password, "AES/CBC/PKCS5Padding");
		return result;
	}

	public static String AESDecrypt(String text, String iv, String password,
			String mode) throws Exception
	{
		Cipher cipher = Cipher.getInstance(mode);
		byte[] keyBytes = new byte[16];
		byte[] b = password.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length)
			len = keyBytes.length;
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

		byte[] results = cipher.doFinal(Base64.decode(text));
		return new String(results, "UTF-8");
	}

	public static String AESEncrypt(String text, String iv, String password)
	{
		String result = AESEncrypt(text, iv, password, "AES/CBC/PKCS5Padding");
		return result;
	}

	public static String AESEncrypt(String text, String iv, String password,
			String mode)
	{
		byte[] results;
		try
		{
			Cipher cipher = Cipher.getInstance(mode);

			// setup key
			byte[] keyBytes = new byte[16];
			byte[] b = password.getBytes("UTF-8");
			int len = b.length;
			if (len > keyBytes.length)
				len = keyBytes.length;
			System.arraycopy(b, 0, keyBytes, 0, len);

			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

			IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("UTF-8"));

			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
			results = cipher.doFinal(text.getBytes("UTF-8"));
		} catch (Exception e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
		return Base64.encode(results);
	}

	public static void main(String[] args) throws Exception
	{
//		System.out.println(AESEncrypt("test", "0123456789123456", "123456"));//加密 VQMeBeMKsHym6mrtiGN8Iw==
//		System.out.println(AESDecrypt("VQMeBeMKsHym6mrtiGN8Iw==","0123456789123456","123456"));//解密 test
		
		System.out.println(AESDecrypt("AIU+6svlunpA+CFkBaqLB+9q9pJl20ZL+rOeVUE10jTOQHjDanCG61XY6uyzzmV6ei5TICWW8W9Dc9nRZkq7NrDm11tb4lCGEzV7TthKPiEz7yqEebvJYEpihRLwiFVXOnfqRHTwi2pmZ+tlO3ngfr+IKVWU1plPZhnZmyofxfI+jxX+890yDWYjXu4cjdmFnGmwSv7ZL+nzpeBcFdac3w67PLThCYhAzzY9smfaEYtipP6kvpBg7uVvnEMZEcVOaVRklULLqqW7JXj3rglpeS6KDDOZHewHYYjouOpE7jq8YT9uszwI/nY7U9RUjpFvgQ22piNX0wCASV6JLpFXhnTOGf1rzmGzluXUZOU726YSu/zsFs7eyP0CQdrdToLSGlEmY/V//qZEha+92JizkWWytieOPT77xzUG6S4NZHk3QPd4sgQeNNwgE94Ad3fWEAtcZVko84tFGhK8mxJaokCNOIgzBvVxlwWC2/Rgq8mjSL3JoVWgwPR4TJp/ZxgM/ZXlKXRnKvDBLH5U6TGAzdlmCIfxHYZRltlcqPq0WnhNWGy/8EjfGDgkXV0t3qRCJ5Nq3hJmPkLTrcdodqZW3Qw6MeWQyPk/enw+1xFQt1/yMyp+T8Z7hF6LLx91cc/fx67CKhoruJSq0k8ULe1QFjDiZKyhfd8JeXkNHRNFqLc5l1ZGJlXGcI1u7rNJfetonWl0ls/dh15Q7Psa+uyEEeebhyh1A1eGfwWRvjBPEj9NRq8Hinq/euVAWwrQ2U2Yo9saskVAztyDy1N8g7NJOaj1/w282OZcSWVz+Rn6pVqKPU3eFdPUQg9mWIMYJBUG4PXQCJ8c+g9IEVP8r0HUBeo2KbF0u+Jl9YChUMkYSgHKFYPtXda4N7N59XUmIa/Xq/ICGgeQAGdaIOosa+GC50XNiDhPtx0mREDkkJA4IA7QZhx8Ddc8aA/VozHRKUBmyXMZInspnUl4RnRPJ3FDMJgKUw4s1CVHHuvZOE3BifdGlcfkGb2dvNMZtp6nflBNt+PLAIIKI3tJmOSkQpvi9oDIrWUVAqrXmlNF+1K3Hetq0FAbF/kCiGOsr89FiQ7vMWQ+5puqy5LSfiDHratF6FSni7OmEZNi0LnzFnw4A/GUKiEAV9HMF+CbXvTJlVzT1BuMvPDYx2TTKTmDAvxuQlk6VI6ELvJTlfo6jyg9oc1Cpwzhg24BN5oH9J6xjkqjxcaAdUELvcqNsgf2dISXKbXXhHJJcgeTnxVHonDBzR3Bp07O3dewtTGeJf2F0pQ8AvZwipy0U85Q2rHTqqnZWGnyroK+nS0pu/DUK6eWiYscN7UOIZ03Loj47s3W1MU3lI0A8GUtweiLpjn7cr6Dfjwdhm7i1p/0GT0eiPiiJmrp/ykDT2eayxViGAHtswL5QU0fTquGBmFaOIEfKZzmrwb+DGB5UC2P7DAVt4BxLDR5H29L0L6HmbDSQW6d8iQ/PQB5xCFwoulrWgvD9PqrJJmEXmya2TqG9JobzBJvEjaJBnIaGJOIx2wL5UwQEPC+GZAjak8BGiPUA2i5Yl9w6zD21s4nBExMapAhCkQMEYA=","d01cb01c-a673-45","#$re&^@#"));//解密 test
	}

}
