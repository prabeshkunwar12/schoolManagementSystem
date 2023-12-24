package com.school_management.model.support_entities.assessment;

public enum AssessmentType {
    NORMAL,  
    BONOUS, //can get the total weightage of course beyond 100
    MANDATORY_PASS; // should pass this assessment to pass the course
}
