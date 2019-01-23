package dev.mvc.review;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

 
@Repository("dev.mvc.review.ReviewDAO") // DBMS ����� ���� 
public class ReviewDAO implements ReviewDAOInter {
   @Autowired  // ���� �������� ���� �ڵ� �Ҵ�, �����ڴ� new ��� ����.
   private SqlSessionTemplate sqlSessionTemplate = null;
   
   public ReviewDAO() {
     System.out.println("--> ReviewDAO created.");
   }

   @Override
   public int create(ReviewVO reviewVO) {
     return sqlSessionTemplate.insert("review.create", reviewVO);
   }

  @Override
  public List<ReviewVO> list() {
    return sqlSessionTemplate.selectList("review.list");
  }

  @Override
  public ReviewVO read(int reviewno) {
    return  sqlSessionTemplate.selectOne("review.read", reviewno);
  }

  @Override
  public int delete(int reviewno) {
    return sqlSessionTemplate.delete("review.delete", reviewno);
  }

  @Override
  public int update(ReviewVO reviewVO) {
    return sqlSessionTemplate.update("review.update", reviewVO);
  }


  
 
}






