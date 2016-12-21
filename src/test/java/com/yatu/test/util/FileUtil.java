package com.yatu.test.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

/**
 * Created by hjf_mac on 9/30/16.
 */
public class FileUtil {

  public static String getFileContent(String path, String encoding) {
    if (StringUtils.isEmpty(encoding)) {
      encoding = "UTF-8";
    }

    try {
      Resource r   = (Resource) new ClassPathResource(path);
      EncodedResource enc = new EncodedResource(r, encoding);
      return FileCopyUtils.copyToString(enc.getReader());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }
}
