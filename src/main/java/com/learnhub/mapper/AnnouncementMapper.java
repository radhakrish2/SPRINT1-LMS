package com.learnhub.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.learnhub.dto.AnnouncementDto;
import com.learnhub.entity.Announcement;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.title", target = "courseName")
    @Mapping(source = "createdBy.id", target = "createdBy")
    @Mapping(source = "createdBy.name", target = "createdByName")
    AnnouncementDto toDto(Announcement announcement);

    @Mapping(target = "course", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Announcement toEntity(AnnouncementDto dto);

    List<AnnouncementDto> toDtoList(List<Announcement> announcements);

}