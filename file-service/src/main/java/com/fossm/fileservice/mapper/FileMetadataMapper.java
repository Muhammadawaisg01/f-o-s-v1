package com.fossm.fileservice.mapper;

import com.fossm.fileservice.dto.FileMetadataDto;
import com.fossm.fileservice.dto.request.UploadRequest;
import com.fossm.fileservice.model.FileMetadata;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileMetadataMapper {

  FileMetadata toEntity(FileMetadataDto fileMetadataDto);

  FileMetadataDto toDto(FileMetadata fileMetadata);

  FileMetadata merge(UploadRequest request, @MappingTarget FileMetadata fileMetadata);

}
