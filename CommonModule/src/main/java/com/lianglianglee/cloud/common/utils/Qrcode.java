package com.lianglianglee.cloud.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.lianglianglee.cloud.common.exception.ServiceException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Qrcode {


  private static final int BLACK = 0xFF000000;
  private static final int WHITE = 0xFFFFFFFF;

  public Qrcode() {
  }

  /**
   * 创建一个图片IO.
   *
   * @param matrix 内容
   * @return 字符串
   */
  private static BufferedImage toBufferedImage(BitMatrix matrix) {
    int width = matrix.getWidth();
    int height = matrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
      }
    }
    return image;
  }

  /**
   * 将文件写到流中.
   *
   * @param matrix 二维码设置
   * @throws IOException IO异常
   */
  private static ByteArrayOutputStream writeToStream(BitMatrix matrix)
          throws IOException {
    BufferedImage image = toBufferedImage(matrix);
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    ImageIO.write(image, "jpg", stream);
    return stream;
  }


  public static String getCodeString(String content) {
    try {
      MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
      Map hints = new HashMap();
      hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
      BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400,
              400, hints);
      ByteArrayOutputStream byteArrayOutputStream = Qrcode.writeToStream(bitMatrix);
      return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());

    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException("生成二维码失败");
    }
  }


}
