package com.myweb.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.myweb.domain.ProductVO;

@Component
public class FileProcess {
	private static final Logger log = LoggerFactory.getLogger(FileProcess.class);
	
	@Resource(name="upImages")
	String upImages;
	
	public ProductVO fileSave(MultipartHttpServletRequest req) throws IllegalStateException, IOException {
		log.info(">>> 파일 저장 - Multi");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		MultipartFile imgfile = req.getFile("imgfile");		
		ProductVO pvo = new ProductVO(title, writer, content);
		if(imgfile.isEmpty()) {
			pvo.setImgfile("NONE");
		}else {
			String orgFileName = imgfile.getOriginalFilename(); // 첨부한 파일에서 파일 이름 추출
			String saveFileName = UUID.randomUUID().toString() + "_" + orgFileName; // 중복되지 않도록 파일 이름 앞에 랜덤으로 생성한 이름 붙임
			File file = new File(upImages+saveFileName); // 파일 객체 생성
			imgfile.transferTo(file); // 파일 객체 복사
			pvo.setImgfile(saveFileName); // vo객체에 파일명 저장
		}
		return pvo;
	}

	public ProductVO fileModify(MultipartHttpServletRequest req) throws IllegalStateException, IOException {
		log.info(">>> 파일 수정 - Multi");
		int pno = Integer.parseInt(req.getParameter("pno"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String imgfile = req.getParameter("imgfile");
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+imgfile);
		MultipartFile new_imgfile = req.getFile("new_imgfile");
		ProductVO pvo = new ProductVO(pno, title, content);
		if (new_imgfile.isEmpty()) {
			pvo.setImgfile(imgfile);
		}else {
			String orgFileName = new_imgfile.getOriginalFilename();
			String saveFileName = UUID.randomUUID().toString() + "_" + orgFileName;
			File file = new File(upImages+saveFileName);
			new_imgfile.transferTo(file);
			pvo.setImgfile(saveFileName);
			fileRemove(imgfile);
		}
		return pvo;
	}

	public void fileRemove(String imgfile) {
		log.info(">>> 파일 삭제");
		new File(upImages+imgfile).delete();
	}
}
