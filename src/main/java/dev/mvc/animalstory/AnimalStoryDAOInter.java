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
}
