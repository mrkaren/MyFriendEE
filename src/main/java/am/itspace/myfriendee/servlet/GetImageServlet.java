package am.itspace.myfriendee.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/getImage")
public class GetImageServlet extends HttpServlet {

    private final String IMAGE_UPLOAD_FOLDER = "/Users/karen/Data/lessons/javase2024/ee/MyFriendEE/images/";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imageName = req.getParameter("imageName");


        // reads input file from an absolute path
        File imageFile = new File(IMAGE_UPLOAD_FOLDER, imageName);

        if (imageFile.exists()) {
            try (FileInputStream inStream = new FileInputStream(imageFile)) {
                // modifies response
                resp.setContentType("image/jpeg");
                resp.setContentLength((int) imageFile.length());
                // obtains response's output stream
                OutputStream outStream = resp.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
