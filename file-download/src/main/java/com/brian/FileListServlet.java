package com.brian;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = new File("/Users/xuyong/Documents/Java/JavaDemos/file-upload/target/artifacts/file_upload_Web_exploded/WEB-INF/upload");

        List fileNameList = new ArrayList();
        if (file.isFile()) {
            fileNameList.add(file.getName());
        } else {
            File[] files = file.listFiles();
            for (File f : files) {
                String name = f.getName();
                fileNameList.add(name);
            }
        }

        request.setAttribute("fileNameList", fileNameList);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
}
