package com.brian;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //检查form中是否有enctype="multipart/form-data"
        if (!ServletFileUpload.isMultipartContent(req)) {
            return;
        }

        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(savePath);
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            file.mkdir();
        }

        // 上传时生成的临时文件保存目录
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdir();
        }


        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
        factory.setSizeThreshold(100*1024); //设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
        factory.setRepository(tmpFile);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
        upload.setFileSizeMax(1024*1024);
        //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
        upload.setSizeMax(10*1024*1024);
        upload.setProgressListener(new ProgressListener() {
            public void update(long pBytesRead, long pContentLength, int i) {
                System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
            }
        });

        List<FileItem> fileItems = null;
        try {
            fileItems = upload.parseRequest(req);
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            System.out.println("单个文件超出最大值！！！");
            e.printStackTrace();
            return;
        } catch (FileUploadBase.SizeLimitExceededException e) {
            System.out.println("上传文件的总的大小超出限制的最大值！！！");
            e.printStackTrace();
            return;
        } catch (Exception e) {
            System.out.println("上传失败！！！");
            e.printStackTrace();
            return;
        }

        for (FileItem fileItem : fileItems) {
            // 如果fileItem中封装的是普通输入项的数据
            if (fileItem.isFormField()) {
                String name = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                System.out.println(name + "=" + value);
            } else {
                String fileName = fileItem.getName();
                System.out.println(fileName);
                // 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                if (fileName == null || "".equals(fileName.trim())) {
                    continue;
                }

                fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);

                //得到上传文件的扩展名
                String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
                System.out.println("上传的文件的扩展名是："+fileExtName);

                InputStream inputStream = fileItem.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(savePath + File.separator + fileName);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, len);
                }

                inputStream.close();
                fileOutputStream.close();
//                fileItem.delete();
            }
        }

        resp.getWriter().print("Upload Success!");

    }
}
