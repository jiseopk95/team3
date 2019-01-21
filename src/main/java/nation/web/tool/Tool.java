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
   * �̹��� ����� �����Ͽ� ���ο� Preview �̹����� �����մϴ�.
   <pre>
   ��뿹): Too.preview(folder ��, ���� ���ϸ�, 200, 150)
   </pre>
   * @param upDir ���� �̹��� ����
   * @param _src ���� ���ϸ�
   * @param width ������ �̹��� �ʺ�
   * @param height  ������ �̹��� ����, ImageUtil.RATIO�� �ڵ� ��� ����
   * @return src.jpg ������ �̿��Ͽ� src_t.jpg ������ �����Ͽ� ���ϸ� ����
   */
  public static synchronized String preview(String upDir, String _src, int width,
      int height) {
    int RATIO = 0;  // ���� ���� ����
    int SAME = -1;  // ũ�� ���� ����.

    File src = new File(upDir + "/" + _src); // ���� ���� ��ü ����
    String srcname = src.getName(); // ���� ���ϸ� ����

    // ���� ���ϸ� ����, mt.jpg -> mt �� ����
    String _dest = srcname.substring(0, srcname.indexOf("."));

    // ��� �̹��� ���� /upDir/mt_t.jpg
    File dest = new File(upDir + "/" + _dest + "_t.jpg");

    Image srcImg = null;

    String name = src.getName().toLowerCase(); // ���ϸ��� �����Ͽ� �ҹ��ڷ� ����
    
    // �̹��� �������� �˻�
    if (name.endsWith("jpg") || name.endsWith("bmp") || name.endsWith("png")
        || name.endsWith("gif")) {
      try {
        srcImg = ImageIO.read(src); // �޸𸮿� ���� �̹��� ����, Call By Reference
        int srcWidth = srcImg.getWidth(null); // ���� �̹��� �ʺ� ����
        int srcHeight = srcImg.getHeight(null); // ���� �̹��� ���� ����
        int destWidth = -1, destHeight = -1; // ��� �̹��� ũ�� �ʱ�ȭ

        if (width == SAME) {     // width�� -1�� ���
          destWidth = srcWidth; // ���� ũ�� ���
        } else if (width > 0) {
          destWidth = width;     // ���ο� width�� �Ҵ�
        }

        if (height == SAME) {       // height�� -1�� ���
          destHeight = srcHeight;  // ���� ũ�� ���
        } else if (height > 0) {
          destHeight = height;      // ���ο� ���̷� �Ҵ�
        }

        // ������ ���� ũ�� ���
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

        // �޸𸮿� ��� �̹��� ����
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight,
            Image.SCALE_SMOOTH);
        int pixels[] = new int[destWidth * destHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth,
            destHeight, pixels, 0, destWidth);

        pg.grabPixels();

        BufferedImage destImg = new BufferedImage(destWidth, destHeight,
            BufferedImage.TYPE_INT_RGB);
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth);

        // ���Ͽ� ���
        ImageIO.write(destImg, "jpg", dest);

        // System.out.println(dest.getName() + " �̹����� �����߽��ϴ�.");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return dest.getName();
  }
  
  /**
   * ���ڿ��� ���̰� length ���� ũ�� "..."�� ǥ���ϴ� �޼ҵ�
   * @param str ���� ���ڿ�
   * @param length ������ ���ڿ� ����
   * @return Ư�� ������ ���ڿ�
   */
  public static synchronized String textLength(String str, int length) {
    if (str != null) {
      if (str.length() > length) {
        str = str.substring(0,  length) + "..."; // ����: 0 ~ length - 1
      }
    } else {
      str = "";
    }
    
    return str;
  }
  
}






