package dev.mvc.surveyparty;

public class Survey_PartyVO {
  
  /*member 정보*/
  private int memberno;
  private String id;
  private String name;
   
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
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
  /*surveyitemno정보 */
  private int surveyitemno;
  private String question;
  private int itemcnt;
  
  
 
  public int getSurveyitemno() {
    return surveyitemno;
  }
  public void setSurveyitemno(int surveyitemno) {
    this.surveyitemno = surveyitemno;
  }
  public String getQuestion() {
    return question;
  }
  public void setQuestion(String question) {
    this.question = question;
  }
  public int getItemcnt() {
    return itemcnt;
  }
  public void setItemcnt(int itemcnt) {
    this.itemcnt = itemcnt;
  }
  
  /*surveyparty정보*/
  private int surveypartyno;
  private String rdate;

  public int getSurveypartyno() {
    return surveypartyno;
  }
  public void setSurveypartyno(int surveypartyno) {
    this.surveypartyno = surveypartyno;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  private int surveyno;
  private String survey_title;

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
  
  

}
