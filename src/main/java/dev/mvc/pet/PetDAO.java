package dev.mvc.pet;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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


}










