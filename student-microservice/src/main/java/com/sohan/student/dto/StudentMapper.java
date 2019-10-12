package com.sohan.student.dto;

import com.sohan.student.dao.entities.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * This class maps the Entity object fetched from the DataBase and builds a Data Transfer Object (DTO), which will be
 * the response from the REST API.
 *
 * @author Sohan
 * @see Mapper
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO studentEntityToDTO(StudentEntity studentEntity);

    StudentEntity studentDTOToEntity(EnrollStudentDTO dto);
}
