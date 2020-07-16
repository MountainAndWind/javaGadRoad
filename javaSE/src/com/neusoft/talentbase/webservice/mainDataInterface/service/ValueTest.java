package com.neusoft.talentbase.webservice.mainDataInterface.service;

/**
 * @description:
 * @author: slfang
 * @time: 2020/5/29 15:38
 */
public class ValueTest {

    public static void main(String[] args) throws Exception {

        System.out.println(DESUtil.encrypt("UNIT"));
        System.out.println(DESUtil.encrypt("2020-04-29 12:00:00"));
        System.out.println(DESUtil.encrypt("2020-05-28 12:00:00"));
    }
}
