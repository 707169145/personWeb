package cn.yimi.dao;

import cn.yimi.dto.FileDto;

import java.util.List;

/**
 * 文件信息记录
 */
public interface FileDao {

    /**
     * 文件信息记录
     * @param fileDto
     * @return
     */
    public Integer uploadFile(FileDto fileDto);

    /**
     * 获取附件列表
     * @return
     */
    public List<FileDto> getFileList();

    /**
     * 根据id获取文件
     * @param fileId
     * @return
     */
    public FileDto getFileByID(String fileId);

    /**
     * 获取默认设置的文件(附件),多个附件的情况下取最近上传的
     * @return
     */
    public FileDto getFileDefalt();

    /**
     * 更新附件的使用状态
     * @return
     */
    public Integer updateFileUse(FileDto fileDto);

    /**
     * 删除附件
     * @return
     */
    public Integer deleteFile(String fileId);

    /**
     * 清楚展示简历
     */
    public Integer clearFile();
}
