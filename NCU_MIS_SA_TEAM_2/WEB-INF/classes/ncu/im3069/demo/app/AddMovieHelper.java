package ncu.im3069.demo.app;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class AddMovieHelper extends Helper {

	private AddMovieHelper() {

	}

	public static AddMovieHelper getHelper() {
		/** Singleton檢查是否已經有Helper物件，若無則new一個，若有則直接回傳 */
		if (helper == null)
			helper = new AddMovieHelper();

		return (AddMovieHelper) helper;
	}

//	https://happycoding.io/tutorials/java-server/uploading-files
	public void saveImage(HttpServletRequest request, String imgTagName, String destinationPath) throws IOException {
		Part filePart = null;
		try {
			filePart = request.getPart(imgTagName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		filePart.write(destinationPath + filePart.getSubmittedFileName());

//		if (filePart.getSubmittedFileName().endsWith(".jpg") || filePart.getSubmittedFileName().endsWith(".png")) {
//			try {
//				filePart.write("/");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

}
