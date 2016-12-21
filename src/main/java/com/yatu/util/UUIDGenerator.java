package com.yatu.util;

import org.springframework.util.DigestUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by hjf_mac on 9/22/16.
 */
public class UUIDGenerator {

  public static String getUUID() {
    UUID uuid = UUID.randomUUID();
    String str = uuid.toString();
    // 去掉"-"符号
    String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    return temp;
  }

  public static void main(String[] args) {
    System.out.println(getUUID());
    StringBuilder sb = new StringBuilder("1,2,3,4,");
    sb.deleteCharAt(sb.length() - 1);
    System.out.println(sb.toString());

    String hash = DigestUtils.md5DigestAsHex("qweadasdtyujdhsfjsdfds".getBytes());
    System.out.println(hash);

    Set<String> set1 = new HashSet<>();
    Set<String> set2 = new LinkedHashSet<>();

    for (int i = 0; i < 50; i ++) {
      String str = i + "";
      set1.add(str);
      set2.add(str);
    }

    Iterator<String> it1 = set1.iterator();
    while (it1.hasNext()) {
      System.out.print(it1.next() + "  ");
    }
    System.out.println("linked hash set:");
    Iterator<String> it2 = set2.iterator();
    while (it2.hasNext()) {
      System.out.print(it2.next() + "  ");
    }
  }

}
