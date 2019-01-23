package dev.mvc.survey;


public class Manager_SurveyVO {
  //manager table
  private int managerno;
  private String kind;
  private String id;
  private String name;
  
  public String getKind() {
    return kind;
  }
  public void setKind(String kind) {
    this.kind = kind;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  //survey table
  private int surveyno;
  private String survey_title;
  private int seqno;
  private String startdate;
  private String enddate;
  private String rdate;
  private int q_cnt;

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
