package com.brian;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fileName");
        System.out.println(fileName);

        File file = new File("/Users/xuyong/Documents/Java/JavaDemos/file-upload/target/artifacts/file_upload_Web_exploded/WEB-INF/upload");

        if (!file.isFile()) {
            String filePath = file.getAbsolutePath() + File.separator + fileName;
            System.out.println(filePath);

            file = new File(filePath);
        }

        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        FileInputStream fileInputStream = new FileInputStream(file);
        ServletOutputStream outputStream = response.getOutputStream();

        byte buffer[] = new byte[1024];
        int len = 0;
        while((len=fileInputStream.read(buffer))>0){
            outputStream.write(buffer, 0, len);
        }

        fileInputStream.close();
        outputStream.close();
    }
}
