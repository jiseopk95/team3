package dev.mvc.manager;



public class File2VO {
  private String thumbs;
  private String files;
  private String filesizes;

  public File2VO() {

  }

  public File2VO(String thumbs, String files, String filesizes) {
    super();
    this.thumbs = thumbs;
    this.files = files;
    this.filesizes = filesizes;
  }

  /**
   * @return the thumb
   */
  public String getThumbs() {
    return thumbs;
  }

  /**
   * @param thumb the thumb to set
   */
  public void setThumbs(String thumbs) {
    this.thumbs = thumbs;
  }

  /**
   * @return the file
   */
  public String getFiles() {
    return files;
  }

  /**
   * @param file the file to set
   */
  public void setFiles(String files) {
    this.files = files;
  }

  /**
   * @return the size
   */
  public String getFilesizes() {
    return filesizes;
  }

  /**
   * @param size the size to set
   */
  public void setFilesizes(String filesizes) {
    this.filesizes = filesizes;
  }
  
  
  
}
 