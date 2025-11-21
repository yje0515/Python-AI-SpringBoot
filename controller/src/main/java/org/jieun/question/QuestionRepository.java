package org.jieun.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 해당 인터페이스를 리포지터리로 만들기 위해 JpaRepository 인터페이스를 상속한다.
// CRUD를 내장하고 있음
// Question Entity로 Repository를 생성, 기본키 Integer
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject,String content);
    List<Question> findBySubjectLike(String subject);


}
