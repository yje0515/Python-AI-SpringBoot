package org.jieun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration //환경 설정용 클래스
public class WebClientConfig {

    @Bean
        // 스프링 프레임워크의 객체용
    WebClient webClient() {
        // 이미지를 보내던 영상을 보내던 데이터의 용량이 크기 때문에 버퍼사이즈 조정해야 한다
        // WebClient를 구성하고 빈으로 정의하여 애플리케이션에서 사용할 수 있도록 함
        return WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer
                                -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(-1))
                        // a무제한 버퍼
                        .build())
                .baseUrl("http://localhost:8000") // 파이썬 경로 지정
                // 업로드한 파일을 ai 서버에 전송하기 위해서 버퍼의 크기 제한을 제한없이
                .build(); // https://m.blog.naver.com/seek316/223337685249
    }
}
