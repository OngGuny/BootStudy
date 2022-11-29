package kr.bbaa.board.upload.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.bbaa.board.upload.entity.AttachFile;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j2
@RequestMapping("/board/")
public class AttachFileContoller {

//	@GetMapping("/uploadAjax")//보이드하면 맵핑된곳으로 찾아감
//	public String uploadAjax() {
//		
//		
//		return "";
//	}

	@PostMapping(value = "/fileUploadAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFile>> uploadPost(MultipartFile[] uploadFile) {

		List<AttachFile> list = new ArrayList<AttachFile>();

		String uploadFolder = "C:/upload";

		String uploadFolderPath = getFolder();

		File uploadPath = new File(uploadFolder, uploadFolderPath);

		// 날짜별 파일 저장 폴더 만들어주기.
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		for (MultipartFile multipartFile : uploadFile) {

			AttachFile attachFile = new AttachFile();

			log.info("-----------------------------------");

			String originalFileName = multipartFile.getOriginalFilename();
			attachFile.setFileName(originalFileName);
			log.info("upload file 원래 파일 이름:" + originalFileName);
			log.info("upload file 크기:" + multipartFile.getSize());
			log.info("upload file 파일 존재 여부:" + multipartFile.isEmpty());
			log.info("upload file 새로운 파일 이름:" + multipartFile.getName());
			try {
				log.info("upload file name:" + multipartFile.getBytes());

				log.info("upload file name:" + multipartFile.getInputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			log.info("-----------------------------------");
			// subString 으로 이름 정리해줌. 앞에서부터 몇글자 떼 내는거.

			log.info(originalFileName);
			log.info(originalFileName.lastIndexOf("\\") + 1);
			originalFileName.substring(originalFileName.lastIndexOf("\\") + 1);

			UUID uuid = UUID.randomUUID();
			// 저장소에 이름 다르게 해서 저장하려고 랜덤한 이름 붙여줌
			originalFileName = uuid.toString() + "_" + originalFileName;

			try {
				File saveFile = new File(uploadPath, originalFileName); // 유니크한 이름
				multipartFile.transferTo(saveFile);

				attachFile.setUuid(uuid.toString());
				attachFile.setUploadPath(uploadFolderPath);
				// 이미지 파일 일 경우 섬네일 이미지 만들어주는것
				if (checkImageType(saveFile) == "y") {

					attachFile.setImage("y");

					FileOutputStream thumbnail = new FileOutputStream(
							new File(uploadPath, "thumb_" + originalFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);

					thumbnail.close();

				}

				list.add(attachFile);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<AttachFile>>(list, HttpStatus.OK);
	}// uploadPost

	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);

	}// 파일 경로 가져오기

	private String checkImageType(File file) {// 파일과 그 저장위치의 경로이름
		try { // 저장위치 찾아가서 그 파일의 타입을 알아옴
			String contentType = Files.probeContentType(file.toPath());
			String result = "";// 내가바꾼거
			if (contentType != null) {// 여기도
				if (contentType.startsWith("image"))
					result = "y"; // 여기도
				return result;
			} else {
				result = "n";
				return result;
			} // 여기까지 바꿈
//					(contentType != null ? if(contentType.startsWith("image"): "n")
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "n"; // 얘도 바까줌
	}// 파일 이미지 타입 체크

	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("fileName:" + fileName);
		// 이 경로에 있는 파일을 만들어 냄.
		File file = new File("C:\\upload\\" + fileName);

		log.info("file:" + file);

		ResponseEntity<byte[]> result = null;

		try {
			HttpHeaders header = new HttpHeaders();
			log.info("헤더에 에드는 왜 이름 이렇게 지음? " + Files.probeContentType(file.toPath()));
			header.add("Content=type", Files.probeContentType(file.toPath()));

			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // display

	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody // 리턴 값이 무조건 리스폰스의 바디에 붙을 수 있겠끔 하는 어노테이션
	public ResponseEntity<Resource> downloadFile(@RequestHeader("user-Agent") String userAgent, String fileName) {
		log.info("donseload file: " + fileName);
		// 다운로드 할 파일을 리소스로 객체생성, 경로로 찾아온다.
		Resource resource = new FileSystemResource("C:/upload/" + fileName);
		// 없는 파일 다운로드 시 예외처리
		if (resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		String resourceName = resource.getFilename();
		// remove UUID /// _앞까지의 글자수에다가 _까지 더해주려고 1 더해준 값만큼. 앞에서 글자 제거
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

		// 헤더 생성
		HttpHeaders headers = new HttpHeaders();

		try {

			String downloadName = null;
			// userAgent 가 그냥 브라우저 이름 말하는거.
			if (userAgent.contains("Trident")) {

				log.info("IE browser");
				downloadName = URLEncoder.encode(resourceName, "UTF-8").replace("/+", " ");
			} else if (userAgent.contains("Edge")) {
				log.info("edge browser");

				downloadName = URLEncoder.encode(resourceName, "UTF-8");
				log.info("edge name:" + downloadName);
			} else {
				log.info("chrome browser");
				downloadName = new String(resourceName.getBytes("UTF-8"), "ISO_8859-1");
			}

			headers.add("content=disposition", "attachment; filename=" + downloadName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);

	}// 아니 url에 attachment 랑 컨텐트, 파일 이름만 넣어주는데 이게 다운로드가 되는거임..? ㄷㄷ
	//download
	
	//여기서부터 보면됨 
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type) {

		log.info("deleteFile: " + fileName);

		File file;

		try {
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));

			file.delete();

			if (type.equals("image")) {

				String largeFileName = file.getAbsolutePath().replace("thumb_", "");

				log.info("largeFileName: " + largeFileName);

				file = new File(largeFileName);

				file.delete();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<String>("deleted", HttpStatus.OK);

	}//delete

}// class
