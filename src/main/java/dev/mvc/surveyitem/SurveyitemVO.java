package dev.mvc.surveyitem;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SurveyitemVO {
//  surveyitemno                    NUMBER(10)   NOT NULL  PRIMARY KEY,
//  seqno                           NUMBER(10)   NOT NULL,
//  question                        VARCHAR2(50)   NOT NULL,
//  surveyno                        NUMBER(10)   NULL ,
//  thumbs              VARCHAR2(1000)                             NULL ,
//  files                   VARCHAR2(1000)                            NULL ,
//  sizes                  VARCHAR2(1000)                            NULL ,
  
  private int surveyitemno;
  private int seqno;
  private String question;
  private int surveyno;
  private String thumbs;
  private String thumb = "";


  private String files;
  private String sizes;
  public int getSurveyitemno() {
    return surveyitemno;
  }
  public void setSurveyitemno(int surveyitemno) {
    this.surveyitemno = surveyitemno;
  }
  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
  public String getQuestion() {
    return question;
  }
  public void setQuestion(String question) {
    this.question = question;
  }
  public int getSurveyno() {
    return surveyno;
  }
  public void setSurveyno(int surveyno) {
    this.surveyno = surveyno;
  }
  
  public String getThumbs() {
    return thumbs;
  }
  public void setThumbs(String thumbs) {
    this.thumbs = thumbs;
  }
  
  public String getThumb() {
    return thumb;
  }
  public void setThumb(String thumb) {
    this.thumb = thumb;
  }
  /**
   * @return the files
   */
  public String getFiles() {
    if (this.files == null) {
      return "";
    } else{
      return files;
    }
  }

  /**
   * @param files the files to set
   */
  public void setFiles(String files) {
    this.files = files;
  }
  public String getSizes() {
    return sizes;
  }
  public void setSizes(String sizes) {
    this.sizes = sizes;
  }
  
  /** 
  Spring Framework에서 자동 주입되는 업로드 파일 객체,
  DBMS 상에 실제 컬럼은 존재하지 않고 파일 임시 저장 목적.
  여러개의 파일 업로드
  */  
  private List<MultipartFile> filesMF;
  
  public List<MultipartFile> getFilesMF() {
    return filesMF;
  }

  public void setFilesMF(List<MultipartFile> filesMF) {
    this.filesMF = filesMF;
  }


  /** size1의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String sizesLabel;
  
  /**
   * @return the sizesLabel
   */
  public String getSizesLabel() {
    return sizesLabel;
  }

  /**
   * @param sizesLabel the sizesLabel to set
   */
  public void setSizesLabel(String sizesLabel) {
    this.sizesLabel = sizesLabel;
  }

}