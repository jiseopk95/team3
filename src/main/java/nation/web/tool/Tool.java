package nation.web.tool;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
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
   * 이미지 사이즈를 변경하여 새로운 Preview 이미지를 생성합니다.
   <pre>
   사용예): Too.preview(folder 명, 원본 파일명, 200, 150)
   </pre>
   * @param upDir 원본 이미지 폴더
   * @param _src 원본 파일명
   * @param width 생성될 이미지 너비
   * @param height  생성될 이미지 높이, ImageUtil.RATIO는 자동 비례 비율
   * @return src.jpg 파일을 이용하여 src_t.jpg 파일을 생성하여 파일명 리턴
   */
  public static synchronized String preview(String upDir, String _src, int width,
      int height) {
    int RATIO = 0;  // 비율 변경 없음
    int SAME = -1;  // 크기 변경 없음.

    File src = new File(upDir + "/" + _src); // 원본 파일 객체 생성
    String srcname = src.getName(); // 원본 파일명 추출

    // 순수 파일명 추출, mt.jpg -> mt 만 추출
    String _dest = srcname.substring(0, srcname.indexOf("."));

    // 축소 이미지 조합 /upDir/mt_t.jpg
    File dest = new File(upDir + "/" + _dest + "_t.jpg");

    Image srcImg = null;

    String name = src.getName().toLowerCase(); // 파일명을 추출하여 소문자로 변경
    
    // 이미지 파일인지 검사
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // 메모리에 원본 이미지 생성, Call By Reference
        int srcWidth = srcImg.getWidth(null); // 원본 이미지 너비 추출
        int srcHeight = srcImg.getHeight(null); // 원본 이미지 높이 추출
        int destWidth = -1, destHeight = -1; // 대상 이미지 크기 초기화

        if (width == SAME) {     // width가 -1인 경우
          destWidth = srcWidth; // 원본 크기 사용
        } else if (width > 0) {
          destWidth = width;     // 새로운 width를 할당
        }

        if (height == SAME) {       // height가 -1인 경우
          destHeight = srcHeight;  // 원본 크기 사용
        } else if (height > 0) {
          destHeight = height;      // 새로운 높이로 할당
        }

        // 비율에 따른 크기 계산
        if (width == RATIO && height == RATIO) {
          destWidth = srcWidth;
          destHeight = srcHeight;
        } else if (width == RATIO) {
          double ratio = ((double) destHeight) / ((double) srcHeight);
          destWidth = (int) ((double) srcWidth * ratio);
        } else if (height == RATIO) {
          double ratio = ((double) destWidth) / ((double) srcWidth);
          destHeight = (int) ((double) srcHeight * ratio);
        }

        // 메모리에 대상 이미지 생성
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);

        pg.grabPixels();

        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);

        // 파일에 기록
        ImageIO.write(destImg, "jpg", dest);

        // System.out.println(dest.getName() + " 이미지를 생성했습니다.");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return dest.getName();
  }
  
  /**
   * 문자열의 길이가 length 보다 크면 "..."을 표시하는 메소드
   * @param str 원본 문자열
   * @param length 선별할 문자열 길이
   * @return 특정 길이의 문자열
   */
  public static synchronized String textLength(String str, int length) {
    if (str != null) {
      if (str.length() > length) {
        str = str.substring(0,  length) + "..."; // 범위: 0 ~ length - 1
      }
    } else {
      str = "";
    }
    
    return str;
  }
  
}






