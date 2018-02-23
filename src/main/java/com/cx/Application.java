package com.cx;

import com.cx.bean.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author CX
 * @create 2018/1/11
 */
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());
    }


}
