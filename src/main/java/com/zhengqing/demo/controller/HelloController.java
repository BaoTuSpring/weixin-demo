package com.zhengqing.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  <p>  </p>
 *
 * @author：  zhengqing <br/>
 * @date：  2019/6/2 0002$ 16:48$ <br/>
 * @version：  <br/>
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public String home() {
        return "hello,朋友";
    }

    @RequestMapping("/hello")
    //加上 @ResponseBody 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中。 比如异步获取 json 数据，加上 @ResponseBody 后，会直接返回 json 数据
    public String sayHello(){
        return "HelloWorld";
    }

}
