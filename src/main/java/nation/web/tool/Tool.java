package nation.web.tool;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Tool {
  public static synchronized String convertChar(String str){
    str = str.replaceAll("<", "＆lt;"); // <-- ＆는 그림임으로 ＆문자로 변경할 것
    str = str.replaceAll(">", "＆gt;");
    str = str.replaceAll("'", "＆#39;");
    str = str.replaceAll("\"", "＆quot;");
    str = str.replaceAll("\r\n", "<BR>");
    
    return str;
  }
  
  // 문자열 변수는 null이 값으로 사용될 수 있음으로 ""로 변환
  public static synchronized String checkNull(String str){
    if (str == null){
      str = "";
    }
    
    return str;
  }

  // 문자열 변수는 null이 값으로 사용될 수 있음으로 ""로 변환
  public static synchronized String checkNull(Object str){
    
    String _str = (String)str;
    
    if (_str == null){
      _str = "";
    }
    
    return _str;
  }
  
  /**
   * FileUpload 1.2 한글 변환
   * 
   * @param str
   * @return
   */
  public static synchronized String encodeFileUpload12(String str) {
    String corean = null;
    try {
      corean = new String(str.getBytes("ISO-8859-1"), "UTF-8");
      // corean= new String(str.getBytes("ISO-8859-1"), "KSC5601");
    } catch (Exception e) {
      return corean;
    }
    return corean;
  }  
  
  /**
   * 파일이 이미지인지 판단합니다.
   * @param filename
   * @return
   */
  public static synchronized boolean isImage(String filename){
    boolean sw = false;
    
    if (filename != null){
      filename = filename.toLowerCase();
    
      if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") ||
        filename.endsWith(".gif") || filename.endsWith(".png") ||
        filename.endsWith(".bmp")){
        sw = true;      
      }
    }
    
    return sw;
  }

  /**
   * 파일 사이즈를 계산하여 단위를 추가합니다.
   * @param filesize
   * @return
   */
  public static synchronized String unit(long filesize){
    String str = "";
    
    if (filesize > (1024 * 1024 * 1024)){
      str = filesize / (1024 * 1024 * 1024) + " GB"; 
    }else if (filesize > (1024 * 1024)){
      str = filesize / (1024 * 1024) + " MB"; 
    }else if (filesize > 1024){
      str = filesize / (1024) + " KB"; 
    }else{
      str = filesize + " B";
    }
    
    return str;
  }
  
  /**
   * 로그인한 회원인지 검사합니다.
   * @param request
   * @return
   */
  public static synchronized boolean isMember(HttpServletRequest request){
    HttpSession session = request.getSession();
    String id = checkNull(session.getAttribute("id"));
    boolean sw = false;
    
    if (id.equals("") == true){
      sw = false;  
    }else{
      sw = true;
    }

    return sw;
    
  }
  
  /**
   * 로그인한 관리자인지 검사합니다.
   * @param request
   * @return
   */
  public static synchronized boolean isAdmin(HttpServletRequest request){
    HttpSession session = request.getSession(); 
    String id = checkNull(session.getAttribute("admin_id"));
    boolean sw = false;
    
    if (id.equals("") == true){
      sw = false;  
    }else{
      sw = true;
    }

    return sw;
  }

  /**
   * 폴더를 입력받아 절대 경로를 산출합니다. 
   * 예) getRealPath(request, "/media/storage")
   * 
   * @param request
   * @param dir 절대 경로를 구할 폴더명
   * @return 절대 경로 리턴
   * @throws IOException
   */
  public static synchronized String getRealPath(HttpServletRequest request, String dir) {
    String path = "";
    
    try{
      path = request.getRealPath(dir) + "/";  
      // System.out.println("Upload path: " + path);
    }catch(Exception e){
      System.out.println(e.toString());
    }

    return path;
  }

  /**
   * 파일 삭제
   * @param fname
   * @return
   */
  public static synchronized boolean deleteFile(String fname) {
    File file = new File(fname);
    boolean ret = false;
    
    if (file.exists()){
      ret = file.delete();
    }
    
    return ret;
  }
  
  /**
   * 주어진 날짜와 기간을 계산하여 새글 여부 판단 현재 날짜가 2013-02-04 글을 등록날짜 2013-02-02
   * getTimeNew("2013-02-04", 2) : 새글 처리, true getTimeNew("2013-02-04", 3) : 새글
   * 처리, false
   * 
   * @param date 문자열로 된 날짜
   * @param period 새글로 지정할 기간
   * @return
   */
  public static boolean isNew(String date, int period) {
    boolean sw = false;

    Date _date = new Date();
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    try {
      _date = sd.parse(date);
    } catch (Exception e) {
    }
    // System.out.println(date);
    // 1970년1월1일부터 시간을 1000을 1초로하여 계산하여 리턴
    long time = _date.getTime(); // 글을 작성한 시간

    // 현재 시간을 1970년 1월 1일부터 수치형식으로 리턴
    long currentTime = System.currentTimeMillis();

    // 현재 시간과 글 등록시간의 차이를 계산
    long diff = currentTime - time;

    // 1일 86,400,000 ms: 1초를 1000으로 하루를 계산
    // 0.0001 --> 1: 오늘 날짜
    // 1.00002 --> 2: 어제 날짜
    int day = (int) Math.ceil(((double) diff / 86400000));

    if (day <= period) {
      sw = true; // 최신글 처리
    }
    return sw;
  }  
  
  /**
   * 2010-12-14 형식의 날짜를 리턴합니다.
   * 
   * @return 2010-12-14 형식의 문자열 리턴
   */
  public static String getDate(int year, int month, int day) {
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

    String date = sd.format(new Date(year - 1900, month, day));

    return date;
  }
  
  /**

   * 문자열의 길이가 length 보다 크면 "..." 을 표시하는 메소드

   * 

   * @param str 문자열

   * @param length 선별할 문자열 길이

   * @return

   */

  public static synchronized String textLength(String str, int length) {

    if (str != null) {
      if (str.length() > length) {
        str = str.substring(0, length) + "..."; // 범위: 0 ~ length - 1 산출
      }
    } else {
      str = "";
    }
    return str;
  }

}






