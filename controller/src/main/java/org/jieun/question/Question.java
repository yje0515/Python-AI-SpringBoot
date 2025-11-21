package org.jieun.question;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jieun.answer.Answer;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    //질문에서 답변을 참조하려면 question.getAnswerList()를 호출하면 된다.
    // 질문과 답변이 일대다 관계라고 명시
    // mappedBy : 참조 엔티티의 속성명
    // cascade : 게시글이 삭제되면 댓글도 삭제되도록 하는 설정
    @OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}
