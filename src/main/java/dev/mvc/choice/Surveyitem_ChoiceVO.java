package dev.mvc.choice;

public class Surveyitem_ChoiceVO {
  //  surveyitem 정보
  private int surveyitemno;
  private String question;
  
  //choice 정보
  private int choiceno;
  private String a1;
  private String a2;
  private String a3;
  private String a4;
  
  
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
  public int getChoiceno() {
    return choiceno;
  }
  public void setChoiceno(int choiceno) {
    this.choiceno = choiceno;
  }
  public String getA1() {
    return a1;
  }
  public void setA1(String a1) {
    this.a1 = a1;
  }
  public String getA2() {
    return a2;
  }
  public void setA2(String a2) {
    this.a2 = a2;
  }
  public String getA3() {
    return a3;
  }
  public void setA3(String a3) {
    this.a3 = a3;
  }
  public String getA4() {
    return a4;
  }
  public void setA4(String a4) {
    this.a4 = a4;
  }
  

}
