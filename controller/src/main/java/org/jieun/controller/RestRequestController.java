package org.jieun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RestController // 비동기 방식의 컨트롤러
public class RestRequestController {
    @Autowired
    private WebClient webClient; // WebClient web = new WebClient 자동으로 객체를 가져옴

    @PostMapping("/java_service")
    public String serviceRequest(@RequestParam("file") MultipartFile file,
                                 @RequestParam("message") String message) {

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();

        // 멀티파트 구성
        bodyBuilder.part("message", message);
        bodyBuilder.part("file", file.getResource());

        // WebClient로 요청 보내기
        String result = webClient.post()
                .uri("/detect")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return result;
    }
    // 1. 자바 RestController로 텍스트와 이미지를 비동기 방식으로 전송
        // 2. AI 서버에서 이미지를 받아 객체 탐지를 수행
        // 3. AI 서버에서 이미지를 base64 인코딩 문자열로 변환
        // 4. RestController 에서 비동기 방식으로 텍스트와 이미지를 변환
        // 5. 비동기 요청한 뷰 페이지에서 결과를 화면에 출력

}
