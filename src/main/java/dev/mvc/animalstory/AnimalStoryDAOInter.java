package dev.mvc.animalstory;

import java.util.HashMap;
import java.util.List;

public interface AnimalStoryDAOInter {

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
   * �˻��� �Խù� ����
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
}
