package com.lianglianglee.cloud.file.service.oss.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.PutObjectRequest;
import com.lianglianglee.cloud.file.service.oss.entity.OssConfig;
import com.lianglianglee.cloud.file.service.oss.exception.OssException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * OssService.
 *
 * @author liangliang
 * @date 2018年4月19日
 */
public class OssService {
  @Autowired
  private OssConfig ossConfig;
  private static Logger LOGGER = LoggerFactory.getLogger(OssService.class);

  /**
   * 上传本地文件.
   *
   * @param file 文件的绝对路径.
   * @return url
   */
  public String uploadFile(File file) {

    // 创建OSSClient实例
    OSSClient ossClient = getOssClient();
    String key = UUID.randomUUID().toString() + "." + getSuffix(file);
    PutObjectRequest request = new PutObjectRequest(ossConfig.getBucketName(),
      key, file);
    ossClient.putObject(request);
    // 关闭client
    ossClient.shutdown();
    return getUrl(key);
  }

  public List<Bucket> getBucketNames() {
    OSSClient ossClient = getOssClient();

// 列举存储空间。
    List<Bucket> buckets = ossClient.listBuckets();

// 关闭OSSClient。
    ossClient.shutdown();
    return buckets;
  }

  public Boolean doesBucketExist(String bucketName) {
    OSSClient ossClient = getOssClient();

    boolean exists = ossClient.doesBucketExist(bucketName);

// 关闭OSSClient。
    ossClient.shutdown();
    return exists;
  }

  public void setBucketAcl(String bucketName, int acl) {
    OSSClient ossClient = getOssClient();
    CannedAccessControlList accessControl = CannedAccessControlList.Default;
    switch (acl) {
      case -1:
        accessControl = CannedAccessControlList.Private;
        break;
      case 0:
        accessControl = CannedAccessControlList.PublicRead;
      case 1:
        accessControl = CannedAccessControlList.PublicReadWrite;
      default:
        throw new OssException("未知bucket权限！");
    }
    ossClient.setBucketAcl(bucketName, accessControl);

// 关闭OSSClient。
    ossClient.shutdown();
  }


  public String getBucketAcl(String bucketName) {
    OSSClient ossClient = getOssClient();
    return ossClient.getBucketAcl(bucketName).getCannedACL().toString();
  }

  public String getBucketAcl() {
    return getBucketAcl(ossConfig.getBucketName());
  }


  public String getBucketLocation(String bucketName) {
    OSSClient ossClient = getOssClient();
    return ossClient.getBucketLocation(bucketName);
  }

  public String getBucketLocation() {
    return getBucketLocation(ossConfig.getBucketName());
  }


  public void setBucketAcl(int acl) {
    setBucketAcl(ossConfig.getBucketName(), acl);
  }

  public BucketInfo getBucketInfo(String bucketName) {
    OSSClient ossClient = getOssClient();
    BucketInfo info= ossClient.getBucketInfo(bucketName);
    ossClient.shutdown();
    return info;
  }

  public BucketInfo getBucketInfo(int acl) {
    return getBucketInfo(ossConfig.getBucketName());
  }

  /**
   * 删除空间，不能删除当前空间
   * @param bucketName 空间名称
   */
  public void deleteBucket(String bucketName){
    OSSClient ossClient=getOssClient();
    ossClient.deleteBucket(bucketName);
  }

  /**
   * 根据上传的key获取地址.
   *
   * @param key 文件的key
   * @return 地址
   */
  private String getUrl(String key) {
    return "http://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint()
      + "/" + key;
  }


  /**
   * 获取文件后缀.
   *
   * @param file 文件路径
   * @return 后缀
   */
  private String getSuffix(File file) {
    String fileName = file.getName();
    return fileName.substring(fileName.lastIndexOf(".") + 1);
  }

  private OSSClient getOssClient() {
    return getOssClient(ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret(), ossConfig
      .getEndpoint());
  }

  private OSSClient getOssClient(String accessKeyId, String accessKeySecret, String endpoint) {
    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    return ossClient;
  }
}
