package dev.mvc.animalstory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface AnimalStoryProcInter {

  /**
   * �� ����
   * @param animalStoryVO
   * @return
   */
  public int create(AnimalStoryVO animalStoryVO);
  
  /**
   * ��Ʈ ����Ʈ - ��ü �� ������ => managerno ���� �ҷ��� �� �־����
   * @return
   */
  public List<AnimalStoryVO> list();
  
  /**
   * ��Ʈ����Ʈ - anitype�� ����
   * @param anitype
   * @return
   */
  public List<AnimalStoryVO> list_anitype(HashMap hashmap);
  
  /**
   * �Ŵ��� �̸� �˻� - �۾��� ��Ÿ���� ����
   * @return
   */
  public String manager(int managerno);
  
  /**
   * �ϳ��� �� ����
   * @param anino
   * @return
   */
  public int delete(int anino);
  
  /**
   * �ϳ��� �� �б�
   * @param anino
   * @return
   */
  public AnimalStoryVO read(int anino);
  
  /**
   * ���ϸ�� �о����
   * @param aniVO
   * @return
   */
  public ArrayList<FileVO> getThumbs(AnimalStoryVO aniVO);
  
  /**
   * �ϳ��� �� ����
   * @param aniVO
   * @return
   */
  public int update(AnimalStoryVO aniVO);
  
  /**
   * ���� �˻�
   * @param content
   * @return
   */
  public List<AnimalStoryVO> list_by_search(String content);

  /**
   * �˻� + ����¡ - ��ü
   * @param hashMap
   * @return
   */
  public List<AnimalStoryVO> list_by_search_paging(HashMap<String, Object> hashMap);
  
  /**
   * �˻� + ����¡ - �з�
   * @param hashMap
   * @return
   */
  public List<AnimalStoryVO> list_by_search_paging_anitype(HashMap hashMap);

  /**
   * �˻��� ����
   * @param content
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * �˻��� �Խù� ���� - �з�
   * @param hashMap
   * @return
   */
  public int search_count_anitype(HashMap hashMap);
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int nowPage, int search_count, String content);
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging_anitype(int nowPage, int search_count, String content, int anitype);
  
  /**
   * �̺�Ʈ�� ������ ����
   * @param evnetno
   * @return
   */
  public List<EventVO> event(int evnetno); 
  
  /**
   * ��÷�� ������Ʈ
   * @param hashMap
   * @return
   */
  public int win(HashMap hashMap);

}
