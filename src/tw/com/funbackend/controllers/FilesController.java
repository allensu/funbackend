package tw.com.funbackend.controllers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;


import tw.com.funbackend.form.MemberDataTableResult;
import tw.com.funbackend.form.SimpleResult;
import tw.com.funbackend.service.FileService;

@SessionAttributes("userBean")
@Controller
@RequestMapping(value = "/Files")
public class FilesController {
	protected Logger logger = Logger.getLogger("controller");
	
	@Autowired
	public FileService fileService;	
	
	/**
	 * get file from file store
	 * 
	 * @param json
	 * @param fileName get file name of file store
	 * @return image
	 */
	@RequestMapping(value = "/get/normal/{filename:.+}")
	public ResponseEntity<byte[]> getFileNormal(@RequestBody String json, @PathVariable("filename") String fileName) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json;charset=utf-8");
		
		
		byte[] bytes = null;
	
			try {
				
				bytes = fileService.get(fileName);
				String mimeType = URLConnection.getFileNameMap().getContentTypeFor(fileName);
				responseHeaders.add("Content-Type", mimeType);
				if(bytes == null || bytes.length == 0){
					logger.error("bytes == null || bytes.length == 0");
				}
				
				return new ResponseEntity<byte[]>(
						bytes, responseHeaders,
						HttpStatus.OK);
				
			} catch (Exception e) {
				logger.error("Get File Exception");
			}
			
			return new ResponseEntity<byte[]>(
					bytes, responseHeaders,
					HttpStatus.OK);
	
	}
	
	/**
	 * get file from file store
	 * 
	 * @param json
	 * @param fileName get file name of file store
	 * @return image
	 */
	@RequestMapping(value = "/get/{filename:.+}")
	public ResponseEntity<byte[]> getFile(@RequestBody String json, @PathVariable("filename") String fileName) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json;charset=utf-8");
		
		
		byte[] bytes = null;
	
			try {
				
				bytes = fileService.get(fileName);
				String mimeType = URLConnection.getFileNameMap().getContentTypeFor(fileName);
				responseHeaders.add("Content-Type", mimeType);
				if(bytes == null || bytes.length == 0){
					logger.error("bytes == null || bytes.length == 0");
				}
				
				String imageFormat = getImageFormat(mimeType);
				bytes = scaleImage(fileService.getInputStream(fileName), 256, 256, imageFormat);
				
				return new ResponseEntity<byte[]>(
						bytes, responseHeaders,
						HttpStatus.OK);
				
				
				
			} catch (Exception e) {
				logger.error("Get File Exception");
			}
			
			return new ResponseEntity<byte[]>(
					bytes, responseHeaders,
					HttpStatus.OK);
	
	}
	
	/**
	 * upload file
	 * 
	 * @param fileName fileName of file store
	 * @param file real file of uploading
	 * @param apiAccessKey api key
	 * @return json
	 */
//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//	public @ResponseBody SimpleResult handleFormUpload(@RequestParam("fileName") String fileName,
//			@RequestParam("file") MultipartFile file) {
//
//		SimpleResult result = new SimpleResult();
//
//		if (!file.isEmpty()) {
//			try {
//				byte[] bytes = file.getBytes();
//				if (bytes.length > 0) {
//					fileService.save(fileName, bytes);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return result;
//		} else {
//
//			result.setResultCode(-1);
//			result.setResultMessage("上傳檔案資料是空的");
//			return result;
//		}
//	}
	
	public String getImageFormat(String mimeType){
		if(mimeType.contains("jpeg"))
			return "jpg";
		
		if(mimeType.contains("png"))
			return "png";
		
		if(mimeType.contains("gif"))
			return "gif";
		
		return null;
	}
	
	public static byte[] scaleImage(InputStream p_image, int p_width,
			int p_height,String imageformat) throws Exception {

		InputStream imageStream = new BufferedInputStream(p_image);
		Image image = (Image) ImageIO.read(imageStream);

		int thumbWidth = p_width;
		int thumbHeight = p_height;

		// Make sure the aspect ratio is maintained, so the image is not skewed
		double thumbRatio = (double) thumbWidth / (double) thumbHeight;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double imageRatio = (double) imageWidth / (double) imageHeight;
		if (thumbRatio < imageRatio) {
			thumbHeight = (int) (thumbWidth / imageRatio);
		} else {
			thumbWidth = (int) (thumbHeight * imageRatio);
		}

		// Draw the scaled image
		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
		graphics2D.dispose();

		// Write the scaled image to the outputstream
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		ImageIO.write(thumbImage, imageformat, out);

		return out.toByteArray();
	}
	
}
