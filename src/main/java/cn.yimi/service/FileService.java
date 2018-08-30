package cn.yimi.service;

import cn.yimi.dao.FileDao;
import cn.yimi.dto.FileDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 文件管理
 * @author huangzs
 */
@Service
public class FileService {

    @Autowired
    FileDao fileDao;

    private static Logger logger = Logger.getLogger(FileService.class);

    /**
     * 文件上传
     * @param fileName
     *      文件名字
     * @param path
     *      文件父路径
     */
    public Integer uploadFile(String fileName, String path) {
        logger.info("文件上传:" + fileName + ",保存路径:" + path);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        FileDto fileDto = new FileDto();
        fileDto.setFileName(fileName);
        fileDto.setFileSrc(path + File.separator + fileName);
        fileDto.setUploadTime(format.format(new Date()));
        return fileDao.uploadFile(fileDto);
    }

    /**
     * 获取文件列表
     * @return List<FileDto>
     */
    public List<FileDto> getFileList() {
        return fileDao.getFileList();
    }

    /**
     * 根据文件id获取文件信息
     * @param fileId
     *      文件id
     * @return FileDto
     */
    public FileDto getFileById(String fileId) {
        logger.info("查看文件信息" + fileId);

        if ("DEFAULT".equals(fileId) || StringUtils.isEmpty(fileId)) {
            return fileDao.getFileDefalt();
        }
        return fileDao.getFileByID(fileId);
    }

    /**
     * 更新文件信息
     * @param fileDto
     *      文件对象
     * @return Boolean
     */
    public Boolean updateFileInfo(FileDto fileDto) {
        logger.info("文件信息修改：" + fileDto);
        // 如果设置过展示简历，将其清除
        fileDao.clearFile();

        // 设置简历为展示状态
        Integer num = fileDao.updateFileUse(fileDto);
        if (1 == num) {
            return true;
        }

        logger.warn("文件信息修改失败");
        return false;
    }

    /**
     * 删除文件
     * @param fileId
     *      文件id
     * @return Boolean
     */
    public Boolean deleteFile(String fileId) {
        logger.info("删除文件" + fileId);

        Integer num = fileDao.deleteFile(fileId);
        if (1 == num) {
            return true;
        }

        logger.equals("删除失败");
        return false;
    }
}
