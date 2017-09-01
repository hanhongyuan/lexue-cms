package com.lexue.base.util;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class OSSUtils {
	/**
	 * 
	 * @param uploadFile
	 *            上传到OSS的文件
	 * @param fileName
	 *            上传到OSS的文件名称
	 * @param ossKey
	 * @param ossSecret
	 * @param ossBucket
	 *            OSS Bucket名称
	 * @param ossPath
	 *            上传到OSS的文件路径
	 * @return
	 * @throws IOException
	 */
	public static final String uploadOSS(File uploadFile, String fileName,String endpoint, 
			String ossKey, String ossSecret, String ossBucket, String ossPath)
			throws IOException {

		ClientConfiguration conf = new ClientConfiguration();

		// 设置HTTP最大连接数为10
		conf.setMaxConnections(10);
		// 设置TCP连接超时为5000毫秒
		conf.setConnectionTimeout(1000);
		// 设置最大的重试次数为3
		conf.setMaxErrorRetry(3);
		// 设置Socket传输数据超时的时间为2000毫秒
		conf.setSocketTimeout(20000);
		OSSClient client = new OSSClient(endpoint, ossKey, ossSecret, conf);

		InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(
				ossBucket, ossPath + fileName);
		InitiateMultipartUploadResult initiateMultipartUploadResult = client
				.initiateMultipartUpload(initiateMultipartUploadRequest);
		final int partSize = 1024 * 1024 * 5;
		// 计算分块数目
		int partCount = (int) (uploadFile.length() / partSize);
		if (uploadFile.length() % partSize != 0) {
			partCount++;
		}

		// 新建一个List保存每个分块上传后的ETag和PartNumber
		List<PartETag> partETags = new ArrayList<PartETag>();
		for (int i = 0; i < partCount; i++) {
			// 获取文件流
			FileInputStream fis = new FileInputStream(uploadFile);
			// 跳到每个分块的开头
			long skipBytes = partSize * i;
			fis.skip(skipBytes);
			// 计算每个分块的大小
			long size = partSize < uploadFile.length() - skipBytes ? partSize
					: uploadFile.length() - skipBytes;
			// 创建UploadPartRequest，上传分块
			UploadPartRequest uploadPartRequest = new UploadPartRequest();
			uploadPartRequest.setBucketName(ossBucket);
			uploadPartRequest.setKey(ossPath + fileName);
			uploadPartRequest.setUploadId(initiateMultipartUploadResult
					.getUploadId());
			uploadPartRequest.setInputStream(fis);
			uploadPartRequest.setPartSize(size);
			uploadPartRequest.setPartNumber(i + 1);
			UploadPartResult uploadPartResult = client
					.uploadPart(uploadPartRequest);

			// 将返回的PartETag保存到List中。
			partETags.add(uploadPartResult.getPartETag());
			// 关闭文件
			fis.close();
		}

		CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(
				ossBucket, ossPath + fileName,
				initiateMultipartUploadResult.getUploadId(), partETags);

		// 完成分块上传
		CompleteMultipartUploadResult completeMultipartUploadResult = client
				.completeMultipartUpload(completeMultipartUploadRequest);

		// 打印Object的ETag
		return completeMultipartUploadResult.getETag();

	}
	public static final String uploadOSS(MultipartFile uploadFile, String fileName, String endpoint,
										 String ossKey, String ossSecret, String ossBucket, String ossPath)
			throws IOException {

		ClientConfiguration conf = new ClientConfiguration();

		// 设置HTTP最大连接数为10
		conf.setMaxConnections(10);
		// 设置TCP连接超时为5000毫秒
		conf.setConnectionTimeout(1000);
		// 设置最大的重试次数为3
		conf.setMaxErrorRetry(3);
		// 设置Socket传输数据超时的时间为2000毫秒
		conf.setSocketTimeout(20000);
		OSSClient client = new OSSClient(endpoint, ossKey, ossSecret, conf);

		InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(
				ossBucket, ossPath + fileName);
		InitiateMultipartUploadResult initiateMultipartUploadResult = client
				.initiateMultipartUpload(initiateMultipartUploadRequest);
		final int partSize = 1024 * 1024 * 5;
		// 计算分块数目
		int partCount = (int) (uploadFile.getSize() / partSize);
		if (uploadFile.getSize() % partSize != 0) {
			partCount++;
		}

		// 新建一个List保存每个分块上传后的ETag和PartNumber
		List<PartETag> partETags = new ArrayList<PartETag>();
		for (int i = 0; i < partCount; i++) {
			// 获取文件流
			InputStream fis = uploadFile.getInputStream();
			// 跳到每个分块的开头
			long skipBytes = partSize * i;
			fis.skip(skipBytes);
			// 计算每个分块的大小
			long size = partSize < uploadFile.getSize() - skipBytes ? partSize
					: uploadFile.getSize() - skipBytes;
			// 创建UploadPartRequest，上传分块
			UploadPartRequest uploadPartRequest = new UploadPartRequest();
			uploadPartRequest.setBucketName(ossBucket);
			uploadPartRequest.setKey(ossPath + fileName);
			uploadPartRequest.setUploadId(initiateMultipartUploadResult
					.getUploadId());
			uploadPartRequest.setInputStream(fis);
			uploadPartRequest.setPartSize(size);
			uploadPartRequest.setPartNumber(i + 1);
			UploadPartResult uploadPartResult = client
					.uploadPart(uploadPartRequest);

			// 将返回的PartETag保存到List中。
			partETags.add(uploadPartResult.getPartETag());
			// 关闭文件
			fis.close();
		}

		CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(
				ossBucket, ossPath + fileName,
				initiateMultipartUploadResult.getUploadId(), partETags);

		// 完成分块上传
		CompleteMultipartUploadResult completeMultipartUploadResult = client
				.completeMultipartUpload(completeMultipartUploadRequest);

		// 打印Object的ETag
		return completeMultipartUploadResult.getETag();

	}
	
	public static String downloadOSS(String ossKey, String ossSecret, String ossBucket, String ossPath) throws Exception{
		
		return ossPath;
		
	}
}
