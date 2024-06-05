package com.cydeo.converter;

import com.cydeo.dto.CourseDTO;
import com.cydeo.service.CourseService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter implements Converter<String, CourseDTO> {

    private final CourseService courseService;

    public CourseConverter(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CourseDTO convert(String source) {

        if (source==null || source.equals("")){

            return null;
        }

        return courseService.findById(Long.parseLong(source));
    }
}
