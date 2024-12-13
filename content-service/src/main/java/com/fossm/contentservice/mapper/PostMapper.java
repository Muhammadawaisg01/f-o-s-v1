package com.fossm.contentservice.mapper;

import com.fossm.contentservice.dto.PostDetailsDto;
import com.fossm.contentservice.dto.PostDto;
import com.fossm.contentservice.dto.PostPreviewDto;
import com.fossm.contentservice.dto.request.AgeWarningDto;
import com.fossm.contentservice.dto.request.DoubleMonetizationDto;
import com.fossm.contentservice.dto.request.PostCreateRequest;
import com.fossm.contentservice.dto.request.PostUpdateRequest;
import com.fossm.contentservice.dto.user.AvatarProfileDto;
import com.fossm.contentservice.model.Post;
import com.fossm.contentservice.model.enums.ContentWarning;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(uses = {CommentMapper.class})
public interface PostMapper {

  @Mapping(target = "ageLimited", source = "request.ageWarning.ageLimited")
  @Mapping(target = "warnings", source = "request.ageWarning.warnings")
  @Mapping(target = "doubleMonetization", source = "request.doubleMonetization.doubleMonetization")
  @Mapping(target = "price", source = "request.doubleMonetization.price")
  @Mapping(target = "allowComments", defaultValue = "true")
  Post toEntity(PostCreateRequest request, UUID userId, String username);

  PostDetailsDto toDetailsDto(Post post);

  PostDto toDto(Post post);

  default Page<PostDto> toDto(Page<Post> posts) {
    return posts.map(this::toDto);
  }

  @Mapping(target = "doubleMonetization", expression = "java(hidePriceDisplay? false : post.isDoubleMonetization())")
  @Mapping(target = "price", expression = "java(hidePriceDisplay? null : post.getPrice())")
  PostPreviewDto toPreviewDto(Post post, boolean hidePriceDisplay);

  @Mapping(target = "id", source = "post.id")
  @Mapping(target = "doubleMonetization", expression = "java(hidePriceDisplay? false : post.isDoubleMonetization())")
  @Mapping(target = "price", expression = "java(hidePriceDisplay? null : post.getPrice())")
  PostDto toDto(
      Post post,
      AvatarProfileDto avatarProfile,
      boolean hidePriceDisplay,
      long commentCount,
      long shareCount,
      long reactionCount,
      boolean liked
  );

  @Mapping(target = "ageLimited", source = "ageWarning", qualifiedByName = "age")
  @Mapping(target = "warnings", source = "ageWarning", qualifiedByName = "warnings")
  @Mapping(target = "doubleMonetization", source = "doubleMonetization", qualifiedByName = "monetization")
  @Mapping(target = "price", source = "doubleMonetization", qualifiedByName = "price")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void update(PostUpdateRequest request, @MappingTarget Post post);

  @Named("age")
  static Boolean convertAgeLimited(AgeWarningDto ageWarningDto) {
    return ageWarningDto == null
        ? null
        : ageWarningDto.ageLimited();
  }

  @Named("warnings")
  static Set<ContentWarning> convertWarning(AgeWarningDto ageWarningDto) {
    return ageWarningDto == null
        ? null
        : ageWarningDto.warnings();
  }

  @Named("monetization")
  static Boolean convertDoubleMonetization(DoubleMonetizationDto doubleMonetizationDto) {
    return doubleMonetizationDto == null
        ? null
        : doubleMonetizationDto.doubleMonetization();
  }

  @Named("price")
  static BigDecimal convertPrice(DoubleMonetizationDto doubleMonetizationDto) {
    return doubleMonetizationDto == null
        ? null
        : doubleMonetizationDto.price();
  }

}