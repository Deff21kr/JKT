package org.zerock.myapp.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;



@Log4j2
public class UploadFileUtils {
	
//private static String baseUploadPath = "C:/app/scoop/apps/tomcat9/apache-tomcat-9.0.55/wtpwebapps/signal/resources";  
//	private static String baseUploadPath = "C:/Upload";
	private static String baseUploadPath = "JKT/signal/src/main/webapp/resources/img";

	static final int THUMB_WIDTH = 300;
    static final int THUMB_HEIGHT = 300;
   
   
   public static String fileUpload(String uploadPath,
           String fileName,
           byte[] fileData, String ymdPath) throws Exception {

    UUID uid = UUID.randomUUID();
    
    String newFileName = uid + "_" + fileName;
    String imgPath = uploadPath + ymdPath;
    
    // 추가 
    String absoluteUploadPath = baseUploadPath + "/" + imgPath;		// 저장할 업로드 파일의 "절대경로" 생성

    File target = new File(imgPath, newFileName);
//    File target = new File(absoluteUploadPath, newFileName);
    log.info("********** >>>>>>>>>>> target: {}", target);
    
    FileCopyUtils.copy(fileData, target);
    
    
    String thumbFileName = "s_" + newFileName;
    File image = new File(imgPath + File.separator + newFileName);

    File thumbnail = new File(imgPath + File.separator + "s" + File.separator + thumbFileName);

    if (image.exists()) {
     thumbnail.getParentFile().mkdirs();
     Thumbnails.of(image).size(THUMB_WIDTH, THUMB_HEIGHT).toFile(thumbnail);
    }
    return newFileName;
   }

   public static String calcPath(String uploadPath) {
    Calendar cal = Calendar.getInstance();
    String yearPath = File.separator + cal.get(Calendar.YEAR);
    String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
    String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

    makeDir(uploadPath, yearPath, monthPath, datePath);
    makeDir(uploadPath, yearPath, monthPath, datePath + "\\s");

    return datePath;
   }

   private static void makeDir(String uploadPath, String... paths) {

    if (new File(paths[paths.length - 1]).exists()) { return; }

    for (String path : paths) {
     File dirPath = new File(uploadPath + path);

     
     
     if (!dirPath.exists()) {
      dirPath.mkdir();
     }
    }
   }
}