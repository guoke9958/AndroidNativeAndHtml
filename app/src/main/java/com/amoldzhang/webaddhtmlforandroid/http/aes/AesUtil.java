/**
 *
 */
package com.amoldzhang.webaddhtmlforandroid.http.aes;

import com.amoldzhang.webaddhtmlforandroid.common.Config;
import com.tencent.weibo.sdk.android.component.sso.tools.Base64;

/**
 * 具体应用加密解密
 * @author amoldZhang
 *
 */
public class AesUtil {

	/**
	 * 对Ase加密联网请求参数加密
	 * @return
	 */
	public static String setAseMap(String requestContent) {
		//		Log.i("联网传入值", requestContent);
		String request64 = Base64.encode(//编码转化
				AesEncryptionUtil.encrypt( //Aes加密
						requestContent.getBytes(), //字符串转化为byte[]
						Config.password,   //Aes编码秘钥
						Config.IV));       //Aes编码偏移量
		return request64;
	}

	/**
	 * 对Ase加密联网请求参数加密
	 * @return
	 */
	public static String setAseMap(String requestContent, String password) {
		//		Log.i("联网传入值", requestContent);
		String request64 = Base64.encode(//编码转化
				AesEncryptionUtil.encrypt( //Aes加密
						requestContent.getBytes(), //字符串转化为byte[]
						password,   //Aes编码秘钥
						Config.IV));       //Aes编码偏移量
		return request64;
	}


	/***
	 * 对Ase加密联网返回值解密
	 * @param response
	 * @return
	 */
	public static String getAseRespones(String response) {
		try {
			String response64 = new String(
					AesEncryptionUtil.decrypt(
							Base64.decode(response),
							Config.password,
							Config.IV));
			String src = response64;
			return src;
		} catch (Exception e) {
			System.out.println("AES解密失败" + "---"+e.toString());
		}
		return null;
	}
}
