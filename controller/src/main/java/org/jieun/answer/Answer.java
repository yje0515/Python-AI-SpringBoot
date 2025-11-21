package org.jieun.answer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jieun.question.Question;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne // 답변과 질문이 다대일 관계로 연결되어있다고 명시 (외래키)
    private Question question;

}
