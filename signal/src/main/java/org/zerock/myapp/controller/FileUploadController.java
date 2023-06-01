package org.zerock.myapp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.myapp.domain.AttachFileDTO;
import org.zerock.myapp.exception.ControllerException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@NoArgsConstructor
@Log4j2

@RequestMapping("/fileupload")
@Controller
public class FileUploadController {

	// 1. Ajax를 이용한 파일 업로드 처리
	@PostMapping(path = "/multiFileAjaxUpload", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> multiFileAjaxUpload(MultipartFile[] uploadFile)
			throws ControllerException, IllegalStateException, IOException {
		log.trace("multiFileAjaxUpload() invoked.");

		List<AttachFileDTO> list = new ArrayList<>();

		String uploadFolder = "C:/upload/tmp";
		// 업로드파일의 정보 출력
		log.info("uploadFile : {}", Arrays.toString(uploadFile));

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateStr = now.format(formatter);

		for (MultipartFile multipartFile : uploadFile) {

			AttachFileDTO attachDTO = new AttachFileDTO();

			String reqParamName = multipartFile.getName(); // 전송파라미터명
			String originalFileName = multipartFile.getOriginalFilename(); // 파일 원래이름 저장
			log.info("upload File name -> reqParamName: {}, originalFileName:{}", reqParamName, originalFileName);

			Long fileSize = multipartFile.getSize();
			log.info("upload File Size -> {}", fileSize);

			// 생성시 tmp의 하위폴더가 아니고, tmp-2023-05-11 이런식으로 생성됨
			File dir = new File(uploadFolder + dateStr);
			if (!dir.exists()) { // 해당날짜의 디렉토리가 없다면
				dir.mkdirs(); // 디렉토리 생성
			}
			log.info("업로드할 폴더 경로: {}", dir);

			// 업로드 폴더 경로
			String uploadFolderPath = dir.toString();

			// 업로드된 파일명의 원래이름 저장
			String uploadFileName = multipartFile.getOriginalFilename();

			// 파일 경로에서 파일 이름 추출
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
			log.info("only file name : {}", uploadFileName);

			attachDTO.setFileName(uploadFileName); // 1. DTO에 파일 원본이름 저장

			// UUID 생성
			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;
			// 최종파일이름 :_를 넣어 UUID와 원래이름을 구분지어줘야함.
			log.info("UUID를 적용한 uploadFileName 이름 : {}", uploadFileName);
			// UUID_원본파일이름.PNG

			// 파일 최종 경로 = dir경로 + 저장된 파일이름 //
			File savePath = new File(dir, uploadFileName);
			log.info("파일 최종 경로: {}", savePath);

			// 지정된 경로에 파일 저장
			multipartFile.transferTo(savePath);

			// dto에 저장
			attachDTO.setUuid(uuid.toString()); // 파일 uuid 저장
			attachDTO.setUploadPath(uploadFolderPath); // 업로드 경로 저장

			// 저장된파일이 이미지파일인지 확인후 썸네일 생성 -> 게시판 목록에 반영하기 위해
			if (checkImageType(savePath)) {
				FileOutputStream thumbnail = new FileOutputStream(new File(dir, "thumb_" + uploadFileName));
				// 썸네일 파일 생성
				log.info("thumbnail : {}", thumbnail);
				
				// 썸네일 크롭 영역 계산
	            int thumbnailSize = 300;
	            int originalWidth = Thumbnails.of(multipartFile.getInputStream()).scale(1).asBufferedImage().getWidth();
	            int originalHeight = Thumbnails.of(multipartFile.getInputStream()).scale(1).asBufferedImage().getHeight();

	            int cropSize = Math.min(originalWidth, originalHeight);
	            int x = (originalWidth - cropSize) / 2;
	            int y = (originalHeight - cropSize) / 2;

	            Thumbnails.of(multipartFile.getInputStream())
	            		.sourceRegion(Positions.CENTER, cropSize, cropSize)// 크롭 영역 지정
	                    .size(thumbnailSize, thumbnailSize)
	                    .toOutputStream(thumbnail);
	            
	            thumbnail.close();
	        } // if


			// 리스트에 추가
			list.add(attachDTO);
		} // enhanced for

		// 응답문서에 json으로 반환
		return new ResponseEntity<>(list, HttpStatus.OK);
	}// multiFileAjaxUpload

	// 2. 썸네일 이미지를 화면에 출력
	@GetMapping(path={"/display"})
	@ResponseBody // 파일이름을 전송파라미터값으로 받음
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.trace("getFile({}) invoked.", fileName);

//		File file = new File("C:/upload" + fileName);
		File file = new File(fileName);
		log.info("file: {}", file);

		ResponseEntity<byte[]> result = null;

		try {
			// Http 헤더에 파일의 콘텐츠타입 추가
			HttpHeaders header = new HttpHeaders();

			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// byte[]로 결과값 반환
		return result;

	}// getFile

	// 3. 이미지파일인지 체크해주는 메소드
	private boolean checkImageType(File file) {
		log.trace("checkImageType() invoked.");

		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}// checkImageType

	// 4. 업로드된 썸네일이미지의 삭제버튼 클릭시, 업로드된 이미지 삭제
	@PostMapping("/deleteFile")
	@ResponseBody // 브라우저에서 전송되는 파일이름 fileName
	public ResponseEntity<String> deleteFile(String fileName, String type) {
		log.trace("\t+ deleteFile(fileName:{},type:{}) invoked.", fileName, type);

		try {

			// 인코딩된 파일이름 디코딩하기
			File file = new File(URLDecoder.decode(fileName, "utf-8"));

			if (file.exists()) { // 파일이 경로에 존재할시 수행

				// 썸네일 이미지 먼저 삭제
				boolean isDeleted = file.delete();

				// 원본파일 삭제
				String largeFileName = file.getAbsolutePath().replace("thumb_", "");
				log.info("largeFileName:{}", largeFileName);
				file = new File(largeFileName);
				boolean isDeletedOrigin = file.delete();

				if (!isDeleted) {
					log.warn("파일 삭제를 실패하였습니다.: {}", fileName);
					return new ResponseEntity<>("Failed to delete the file.", HttpStatus.INTERNAL_SERVER_ERROR);
				}
				log.info("성공적으로 파일을 삭제하였습니다.");
				return new ResponseEntity<>("Deleted.", HttpStatus.OK);

				} else { // 파일이 경로에 존재하지 않을 시 수행
				log.warn("파일을 찾을 수 없습니다.: {}", fileName);
				return new ResponseEntity<>("File not found.", HttpStatus.NOT_FOUND);
				}
		} catch (UnsupportedEncodingException e) {
			log.error("Unsupported encoding: {}", fileName, e);
			return new ResponseEntity<>("Unsupported encoding.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			log.error("파일삭제를 실패했습니다.: {}", fileName, e);
			return new ResponseEntity<>("Failed to delete the file.", HttpStatus.INTERNAL_SERVER_ERROR);
		} // try-catch

	}// deleteFile

} // end class
