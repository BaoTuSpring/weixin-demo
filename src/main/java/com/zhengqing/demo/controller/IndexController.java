package com.zhengqing.demo.controller;

import com.zhengqing.demo.utils.SecurityUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
@RequestMapping("/index")
public class IndexController {

	// TODO 这里的token是微信公众平台上自己所配的！
	private static final String token = "zhengqing";

	/**
	 * 处理微信认证：验证服务器地址的有效性，get提交
	 * signature: 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	 * timestamp 时间戳
	 * nonce: 随机数
	 * echostr: 随机字符串
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void signature(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("--------------------进入了------------------------------");
		// 拿到微信的请求参数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		// ① 将token、timestamp、nonce三个参数进行字典序排序 b a d c h ==>a b c d h
		String[] strArr = { token, timestamp, nonce };
		// 字典排序
		Arrays.sort(strArr);
		// ② 将三个参数字符串拼接成一个字符串进行sha1加密
		StringBuffer sb = new StringBuffer();
		// 字符串拼接
		for (String str : strArr) {
			sb.append(str);
		}
		// 加密
		String sha1Str = SecurityUtil.sha1(sb.toString());
		// ③ 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		if (sha1Str.equals(signature)) {
			// 如果相等，就是来自微信请求
			// 若确认此次GET请求来自微信服务器，原样返回echostr参数内容，则接入生效
			response.getWriter().println(echostr);
		}
	}

}
