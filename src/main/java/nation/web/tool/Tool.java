package nation.web.tool;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Tool {
  public static synchronized String convertChar(String str){
    str = str.replaceAll("<", "��lt;"); // <-- ���� �׸������� �����ڷ� ������ ��
    str = str.replaceAll(">", "��gt;");
    str = str.replaceAll("'", "��#39;");
    str = str.replaceAll("\"", "��quot;");
    str = str.replaceAll("\r\n", "<BR>");
    
    return str;
  }
  
  // ���ڿ� ������ null�� ������ ���� �� �������� ""�� ��ȯ
  public static synchronized String checkNull(String str){
    if (str == null){
      str = "";
    }
    
    return str;
  }

  // ���ڿ� ������ null�� ������ ���� �� �������� ""�� ��ȯ
  public static synchronized String checkNull(Object str){
    
    String _str = (String)str;
    
    if (_str == null){
      _str = "";
    }
    
    return _str;
  }
  
  /**
   * FileUpload 1.2 �ѱ� ��ȯ
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
   * ������ �̹������� �Ǵ��մϴ�.
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
   * ���� ����� ����Ͽ� ������ �߰��մϴ�.
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
   * �α����� ȸ������ �˻��մϴ�.
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
   * �α����� ���������� �˻��մϴ�.
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
   * ������ �Է¹޾� ���� ��θ� �����մϴ�. 
   * ��) getRealPath(request, "/media/storage")
   * 
   * @param request
   * @param dir ���� ��θ� ���� ������
   * @return ���� ��� ����
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
   * ���� ����
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
   * �־��� ��¥�� �Ⱓ�� ����Ͽ� ���� ���� �Ǵ� ���� ��¥�� 2013-02-04 ���� ��ϳ�¥ 2013-02-02
   * getTimeNew("2013-02-04", 2) : ���� ó��, true getTimeNew("2013-02-04", 3) : ����
   * ó��, false
   * 
   * @param date ���ڿ��� �� ��¥
   * @param period ���۷� ������ �Ⱓ
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
    // 1970��1��1�Ϻ��� �ð��� 1000�� 1�ʷ��Ͽ� ����Ͽ� ����
    long time = _date.getTime(); // ���� �ۼ��� �ð�

    // ���� �ð��� 1970�� 1�� 1�Ϻ��� ��ġ�������� ����
    long currentTime = System.currentTimeMillis();

    // ���� �ð��� �� ��Ͻð��� ���̸� ���
    long diff = currentTime - time;

    // 1�� 86,400,000 ms: 1�ʸ� 1000���� �Ϸ縦 ���
    // 0.0001 --> 1: ���� ��¥
    // 1.00002 --> 2: ���� ��¥
    int day = (int) Math.ceil(((double) diff / 86400000));

    if (day <= period) {
      sw = true; // �ֽű� ó��
    }
    return sw;
  }  
  
  /**
   * 2010-12-14 ������ ��¥�� �����մϴ�.
   * 
   * @return 2010-12-14 ������ ���ڿ� ����
   */
  public static String getDate(int year, int month, int day) {
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

    String date = sd.format(new Date(year - 1900, month, day));

    return date;
  }
  
  /**

   * ���ڿ��� ���̰� length ���� ũ�� "..." �� ǥ���ϴ� �޼ҵ�

   * 

   * @param str ���ڿ�

   * @param length ������ ���ڿ� ����

   * @return

   */

  public static synchronized String textLength(String str, int length) {

    if (str != null) {
      if (str.length() > length) {
        str = str.substring(0, length) + "..."; // ����: 0 ~ length - 1 ����
      }
    } else {
      str = "";
    }
    return str;
  }

}






