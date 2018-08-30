package cn.yimi.dto;

/**
 * 文件信息
 * @author huangzs
 */
public class FileDto {
    // 文件名字
    private String fileName;
    // 文件上传时间
    private String uploadTime;
    // 文件路径
    private String fileSrc;
    // 文件id
    private String fileId;
    // 文件使用情况
    private String fileUse;

    public String getFileUse() {
        return fileUse;
    }

    public void setFileUse(String fileUse) {
        this.fileUse = fileUse;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFileSrc() {
        return fileSrc;
    }

    public void setFileSrc(String fileSrc) {
        this.fileSrc = fileSrc;
    }
}
