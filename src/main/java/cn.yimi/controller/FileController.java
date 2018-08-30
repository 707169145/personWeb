package cn.yimi.controller;

import cn.yimi.controller.result.ResultBuilder;
import cn.yimi.controller.result.ResultModal;
import cn.yimi.dto.FileDto;
import cn.yimi.service.FileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;

/**
 * 附件管理
 * @author huangzs
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {
    private static Logger logger = Logger.getLogger(FileController.class);

    @Autowired
    FileService fileService;

    /**
     * 文件上传
     * @param uploadFile
     *      文件对象
     * @param session
     *      session
     * @return ResultModal
     */
    @RequestMapping(value = "/system/uploadFile")
    @ResponseBody
    public ResultModal uploadFile(MultipartFile uploadFile, HttpSession session) {
        try {
            String fileName = uploadFile.getOriginalFilename();
            // 取的文件存储路径
            String path = session.getServletContext().getRealPath("/resume");

            File file = new File(path, fileName);
            uploadFile.transferTo(file);

            return ResultBuilder.success(fileService.uploadFile(fileName, path));
        } catch (Exception e) {
            logger.error("文件上传" + e);
            return ResultBuilder.fail(e.getMessage());
        }
    }

    /**
     * 获取文件信息
     * @param fileId
     *      文件id
     * @return ResultModal
     */
    @RequestMapping(value = "/getFileById")
    @ResponseBody
    public ResultModal getFileById(String fileId) {
        try {
            return ResultBuilder.success(fileService.getFileById(fileId));
        } catch(Exception e) {
            logger.error("获取文件信息" + e);
            return ResultBuilder.fail(e.getMessage());
        }
    }

    /**
     * 获取文件列表
     * @return ResultModal
     */
    @RequestMapping(value = "/getFileList")
    @ResponseBody
    public ResultModal getFileList() {
        try {
            return ResultBuilder.success(fileService.getFileList());
        } catch(Exception e) {
            logger.error("获取文件列表" + e);
            return ResultBuilder.fail(e.getMessage());
        }
    }

    /**
     * 获取字节流文件
     */
    @RequestMapping(value = "/getFileStream")
    public void getFileStream(HttpSession session, HttpServletResponse response, String fileId) {
        FileDto fileDto = fileService.getFileById(fileId);
        String path = session.getServletContext().getRealPath("resume");
        try {
            File file = new File(path + File.separator + fileDto.getFileName());
            FileInputStream fileStream = new FileInputStream(file);
            ServletOutputStream sos = response.getOutputStream();
            int b;
            while ((b = fileStream.read()) != -1) {
                sos.write(b);
            }

            sos.close();
            fileStream.close();
        } catch (Exception e) {
            logger.error("获取字节流文件,在线查看" + e);
            e.printStackTrace();
        }
    }

    /**
     * 修改展示简历
     * @param fileDto
     *      文件对象
     * @return ResultModal
     */
    @RequestMapping(value = "/system/updateFileInfo")
    @ResponseBody
    public ResultModal updateFileInfo(FileDto fileDto) {
        try {
            return ResultBuilder.success(fileService.updateFileInfo(fileDto));
        } catch (Exception e) {
            logger.error("修改简历信息" + e);
            return ResultBuilder.fail(e.getMessage());
        }
    }

    /**
     * 删除文件（逻辑删除）
     * @param fileId
     *      文件对象
     * @return
     */
    @RequestMapping(value = "/system/deleteFile")
    @ResponseBody
    public ResultModal deleteFile(String fileId) {
        try {
            return ResultBuilder.success(fileService.deleteFile(fileId));
        } catch (Exception e) {
            logger.error("删除文件" + e);
            return ResultBuilder.fail(e.getMessage());
        }
    }

}
