package com.with.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

//filerename 정책만들기 !
public class MyFileRename implements FileRenamePolicy{
	//특정한 메소드만 구현해놓으면 됨 
	//fileRenamePolicy인터페이스를 구현!
	//alt s v
	
	@Override
	public File rename(File oldFile) {
		// rename을 실행하는 메소드 
		File newFile =null;
		do {
			long currentTime=System.currentTimeMillis();//현재시간
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSSS");
			int rndNum=(int)(Math.random()*1000);
			//현재 파일의 명칭을 변경하기 위해서
			// 파일명을 제외한 확장자면만 가져오기
			String oriName=oldFile.getName();
			String ext="";
			int dot=oriName.lastIndexOf(".");
			if(dot>-1) {
				ext=oriName.substring(dot);
			}
			//새 파일이름을 변경하기
			String newName="kh"+sdf.format(new Date(currentTime))+"_"+rndNum+ext;
			newFile=new File(oldFile.getParent(),newName);
			
		}while(!createNewFile(newFile));
		
		return newFile;
	}
	
	private boolean createNewFile(File f) {
		try {
			return f.createNewFile();
		}catch(IOException e) {
			return false;
		}
	}
	

	

}
