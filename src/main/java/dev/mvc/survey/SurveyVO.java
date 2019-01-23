package dev.mvc.survey;

/*surveyno                        NUMBER(10)   NOT NULL  PRIMARY KEY,
survey_title                      VARCHAR2(1000)   NOT NULL,
seqno                           NUMBER(10)   NOT NULL,
startdate                       VARCHAR2(50)   NOT NULL,
enddate                         VARCHAR2(50)   NOT NULL,
rdate                             DATE   NOT NULL,
q_cnt                           NUMBER(10)   NOT NULL,
managerno                    NUMBER(10)  NULL ,*/

public class SurveyVO {
  private int surveyno;
  private String survey_title;
  private int seqno;
  private String startdate;
  private String enddate;
  private String rdate;
  private int q_cnt;
  private int managerno;
  
  public int getSurveyno() {
    return surveyno;
  }
  public void setSurveyno(int surveyno) {
    this.surveyno = surveyno;
  }
  public String getSurvey_title() {
    return survey_title;
  }
  public void setSurvey_title(String survey_title) {
    this.survey_title = survey_title;
  }
  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
  public String getStartdate() {
    return startdate;
  }
  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }
  public String getEnddate() {
    return enddate;
  }
  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public int getQ_cnt() {
    return q_cnt;
  }
  public void setQ_cnt(int q_cnt) {
    this.q_cnt = q_cnt;
  }
  public int getManagerno() {
    return managerno;
  }
  public void setManagerno(int managerno) {
    this.managerno = managerno;
  }

  

}
