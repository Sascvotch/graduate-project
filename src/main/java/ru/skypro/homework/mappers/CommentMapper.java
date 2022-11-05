package ru.skypro.homework.mappers;

import org.mapstruct.*;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.models.Comment;
import ru.skypro.homework.models.User;

import java.util.List;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentMapper {

    @Mapping(target = "pk", source = "id")
    AdsCommentDto commentToAdsCommentDto(Comment comment);
    default Integer userToInteger(User author) {
        return Math.toIntExact(author.getId());
    }

    @Mapping(target = "id",source = "pk")
    Comment adsCommentDtoToComment(AdsCommentDto adsCommentDto);
    default User integerToUser(Integer authorId) {
        // иду в UserRepository и по authorId достаю нужного User
        return new User();
    }
    List<AdsCommentDto> listCommentsToListAdsCommentDto(List<Comment> commentsList);

    @Mapping(target = "id",source = "pk")// не знаю нужно ли это в данном случае
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCommentFromAdsCommentDto(AdsCommentDto adsCommentDto, @MappingTarget Comment comment);

}
