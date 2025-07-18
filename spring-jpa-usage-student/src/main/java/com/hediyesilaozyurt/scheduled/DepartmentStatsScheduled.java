package com.hediyesilaozyurt.scheduled;

import com.hediyesilaozyurt.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
@Component
public class DepartmentStatsScheduled {

    private final StudentRepository studentRepository;

    @Scheduled(cron="10 03 22 * * *")
    public void reportStudentCounts(){
        List<Object[]> results=studentRepository.getStudentCountPerDepartment();

        for(Object[] row:results){
            String departmentName=(String)row[0];
            Long studentCount=(Long) row[1];

            System.out.printf("%-30s: %d\n", departmentName, studentCount );
        }
    }
}
