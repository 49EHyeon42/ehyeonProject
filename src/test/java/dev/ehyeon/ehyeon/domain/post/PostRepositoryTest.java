package dev.ehyeon.ehyeon.domain.post;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "49ehyeon42@gmail.com";

        postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        // when
        List<Post> posts = postRepository.findAll();

        // then
        Post post = posts.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);

        postRepository.save(Post.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // when
        List<Post> posts = postRepository.findAll();

        // then
        Post post = posts.get(0);

        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }
}
