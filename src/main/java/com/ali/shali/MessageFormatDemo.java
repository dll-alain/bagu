package com.ali.shali;

import java.text.MessageFormat;

/**
 * @Author shali
 * @Date 2023/10/26 14:56
 * @Project:shali
 * @Description: TODO
 * @Version 1.0
 */
public class MessageFormatDemo {

    public static void main(String[] args) {
        String url = "https://market.m.taobao.com/app/trip/rx-hotel-detail-map/pages/home?lng={0}&lat={1}&name={2}&address={3}&shid={4}";
        Double lat = 30.245732, lng = 120.216979;
        String name = "杭州钱江新城万豪酒店", address = "剧院路399号";
        int shid = 52174006;
        String format = MessageFormat.format(url, lng, lat, name, address, shid);
        System.out.println("format = " + format);
    }
}
