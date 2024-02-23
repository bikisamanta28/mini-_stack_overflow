package com.tech.blog.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;



public class Helper {

	public static boolean deleteFile(String path) {
		boolean f=false;
		try {
			File file=new File(path);
			
			f=file.delete();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public static boolean saveFile(InputStream inputStream,String path) {
		boolean f=false;
		try {
			byte b[]=new byte[inputStream.available()];
			inputStream.read(b);
			FileOutputStream fosFileOutputStream=new FileOutputStream(path);
			fosFileOutputStream.write(b);
			fosFileOutputStream.flush();
			f=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
