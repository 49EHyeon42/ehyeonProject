package dev.ehyeon.ehyeon.web.dto;

import dev.ehyeon.ehyeon.domain.post.Post;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostListResponseDto {

    private final Long id;
    private final String title;
    private final String author;
    private final LocalDateTime modifiedDate;

    public PostListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
