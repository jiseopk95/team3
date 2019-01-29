package dev.mvc.pet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.member.MemberVO;

@Repository("dev.mvc.pet.PetDAO") // DBMS 저장소 접근 
public class PetDAO implements PetDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;

  public PetDAO() {
    System.out.println("--> PetDAO created.");
  }

  @Override
  public int create(PetVO petVO) {
    int cnt = sqlSessionTemplate.insert("pet.create", petVO);
    return cnt;
  }  

  @Override
  public List<PetVO> list() {
    List<PetVO> list = sqlSessionTemplate.selectList("pet.list");
    
    return list;
  }

  @Override
  public PetVO read(int petno) {
    PetVO petVO = sqlSessionTemplate.selectOne("pet.read", petno);
    
    return petVO;
  }

/*  @Override
  public PetVO readById(String id) {
    PetVO memberVO = sqlSessionTemplate.selectOne("pet.readById", id);
    
    return memberVO;
  }  */

  @Override
  public int update(PetVO petVO) {
    int count = sqlSessionTemplate.update("pet.update", petVO);
    return count;
  } 
  
  @Override
  public int delete(int petno) {
    int count = sqlSessionTemplate.delete("pet.delete", petno);
    return count;
  }
  
  @Override
  public List<PetVO> list_search(HashMap hashMap) {
    return sqlSessionTemplate.selectList("pet.list_search", hashMap);
  }

  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("pet.search_count", hashMap);
  }
  
  /*@Override
  public PetVO pet_list(int memberno) {
    PetVO petVO = sqlSessionTemplate.selectOne("pet.pet_list", memberno);
    
    return petVO;
  }*/
  
 /**
 * 개별 펫 조회
 */
  @Override
  public List<PetVO> pet_list(HashMap hashMap) {
    return sqlSessionTemplate.selectList("pet.pet_list", hashMap);
  }
  
  @Override
  public int search_count2(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("pet.search_count2", hashMap);
  }
}










