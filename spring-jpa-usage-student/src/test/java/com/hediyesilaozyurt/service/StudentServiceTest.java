package com.hediyesilaozyurt.service;

import com.hediyesilaozyurt.dto.dto.DtoStudent;
import com.hediyesilaozyurt.entities.entities.Courses;
import com.hediyesilaozyurt.entities.entities.MainDepartment;
import com.hediyesilaozyurt.entities.entities.Student;
import com.hediyesilaozyurt.entities.entities.StudentCard;
import com.hediyesilaozyurt.mapper.StudentMapper;
import com.hediyesilaozyurt.repository.respository.StudentRepository;
import com.hediyesilaozyurt.services.services.impl.StudentServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    private Student testStudent;
    private DtoStudent testDtoStudent;
    private List<Student> testStudentsList;
    private List<DtoStudent> testDtoStudentsList;
    private StudentCard testStudentCard;
    private MainDepartment testMainDepartment;
    private Courses testCourse;
    @BeforeAll
    static void beforeAll() {
        System.out.println("==> Runs once before all tests (e.g. resource allocation, logging setup, global initialization).");
    }

    @AfterEach
    void afterEach() {
        System.out.println("-- Test completed, mocks can be cleared or results can be logged.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("==> All tests finished. Resources can be released.");
    }


    @BeforeEach
    void setUp(){
        //StudentCard test data
        testStudentCard=new StudentCard();
        testStudentCard.setId(1L);
        testStudentCard.setCardNumber("STU-2021-12");
        testStudentCard.setIssueDate(new Date());

        //MainDepartment test data
        testMainDepartment=new MainDepartment();
        testMainDepartment.setId(1L);
        testMainDepartment.setDepartmentName("Genetic Engineering");
        testMainDepartment.setFacultyName("Engineering faculty");
        testMainDepartment.setEstablishYear(new Date(125, 10, 23));// 2025-11-23 (year-1900, month-1, day)

        //courses test data
        testCourse=new Courses();
        testCourse.setId(1L);
        testCourse.setCourseName("Java Programming");
        testCourse.setCourseDescription("Object Oriented programming with java");
        testCourse.setSemester("Spring");

        //Student test data
        testStudent=new Student();
        testStudent.setId(1L);
        testStudent.setTckn("12234545678");
        testStudent.setFirstName("Ayaz");
        testStudent.setLastName("Kapıcı");
        testStudent.setBirthOfDate(new Date(95, 4, 15));// 1995-05-15
        testStudent.setEmail("ayaz_kapici@gmail.com");
        testStudent.setStudentCard(testStudentCard);
        testStudent.setMainDepartment(testMainDepartment);
        testStudent.setCourses(Arrays.asList(testCourse));

        //DtoStudent test data
        testDtoStudent=new DtoStudent();
        testDtoStudent.setId(1L);
        testDtoStudent.setTckn("12234545678");
        testDtoStudent.setFirstName("Ayaz");
        testDtoStudent.setLastName("Kapıcı");
        testDtoStudent.setBirthOfDate(new Date(95, 4, 15));// 1995-05-15
        testDtoStudent.setEmail("ayaz_kapici@gmail.com");

        //2nd Student test data
        Student student2 = new Student();
        student2.setId(2L);
        student2.setTckn("98765432109");
        student2.setFirstName("Mehmet");
        student2.setLastName("Demir");
        student2.setBirthOfDate(new Date(97, 2, 20)); // 1997-03-20
        student2.setEmail("mehmet.demir@example.com");
        student2.setStudentCard(testStudentCard);
        student2.setMainDepartment(testMainDepartment);
        student2.setCourses(Arrays.asList(testCourse));

        //2nd DtoStudent test data
        DtoStudent dtoStudent2 = new DtoStudent();
        dtoStudent2.setId(2L);
        dtoStudent2.setTckn("98765432109");
        dtoStudent2.setFirstName("Mehmet");
        dtoStudent2.setLastName("Demir");
        dtoStudent2.setBirthOfDate(new Date(97, 2, 20)); // 1997-03-20
        dtoStudent2.setEmail("mehmet.demir@example.com");

        testStudentsList=Arrays.asList(testStudent,student2);
        testDtoStudentsList=Arrays.asList(testDtoStudent,dtoStudent2);



    }

    @Test
    public void listTest(){
        //Given
        Mockito.when(studentRepository.findAll()).thenReturn(testStudentsList);
        Mockito.when(studentMapper.toDtoList(testStudentsList)).thenReturn(testDtoStudentsList);

        //When
        List<DtoStudent> result=studentService.list();

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2,result.size());
        Assertions.assertEquals("Ayaz",result.get(0).getFirstName());
        Assertions.assertEquals("Mehmet",result.get(1).getFirstName());


        Mockito.verify(studentRepository,Mockito.times(1)).findAll();
        Mockito.verify(studentMapper,Mockito.times(1)).toDtoList(testStudentsList);



    }

    @Test
    public void deleteTest(){
        //Given
        Long studentId=1L;
        Mockito.doNothing().when(studentRepository).deleteById(studentId);

        //when
        studentService.delete(studentId);

        //Then
        Mockito.verify(studentRepository,Mockito.times(1)).deleteById(studentId);

    }

    @Test
    public void saveStudentTest(){
        //Given
        Mockito.when(studentMapper.toEntity(testDtoStudent)).thenReturn(testStudent);
        Mockito.when(studentRepository.save(testStudent)).thenReturn(testStudent);
        Mockito.when(studentMapper.toDto(testStudent)).thenReturn(testDtoStudent);

        //When
        DtoStudent result=studentService.saveStudent(testDtoStudent);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(testDtoStudent.getId(),result.getId());

        Mockito.verify(studentMapper,Mockito.times(1)).toEntity(testDtoStudent);
        Mockito.verify(studentRepository,Mockito.times(1)).save(testStudent);
        Mockito.verify(studentMapper,Mockito.times(1)).toDto(testStudent);
    }

    @Test
    public void existByIdTest(){
        //Given
        Long studentId=1L;
        Mockito.when(studentRepository.existsById(studentId)).thenReturn(true);

        //When
        boolean result=studentRepository.existsById(studentId);

        //Then
        Assertions.assertTrue(result);
        Mockito.verify(studentRepository,Mockito.times(1)).existsById(studentId);
    }


    @Test
    public void studentsTakingASpecificCourseTest(){
        //Given
        Long courseId=1L;
        Mockito.when(studentRepository.findStudentByCourseId(courseId)).thenReturn(testStudentsList);
        Mockito.when(studentMapper.toDtoList(testStudentsList)).thenReturn(testDtoStudentsList);

        //When
        List<DtoStudent> result=studentService.studentsTakingASpecificCourse(courseId);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2,result.size());
        Mockito.verify(studentRepository,Mockito.times(1)).findStudentByCourseId(courseId);
        Mockito.verify(studentMapper,Mockito.times(1)).toDtoList(testStudentsList);
    }

    @Test
    public void updateTest(){
        //Given
        Long studentId=1L;
        DtoStudent updateDto=new DtoStudent();
        updateDto.setTckn("11111111111");
        updateDto.setFirstName("Updated Name");
        updateDto.setLastName("Updated LastName");
        updateDto.setEmail("updated@example.com");

        Student updatedStudent = new Student();
        updatedStudent.setId(studentId);
        updatedStudent.setTckn("11111111111");
        updatedStudent.setFirstName("Updated Name");
        updatedStudent.setLastName("Updated LastName");
        updatedStudent.setEmail("updated@example.com");

        DtoStudent updatedDto = new DtoStudent();
        updatedDto.setId(studentId);
        updatedDto.setTckn("11111111111");
        updatedDto.setFirstName("Updated Name");
        updatedDto.setLastName("Updated LastName");
        updatedDto.setEmail("updated@example.com");

        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(testStudent));
        Mockito.when(studentMapper.updateEntityFromDto(updateDto,testStudent)).thenReturn(updatedStudent);
        Mockito.when(studentRepository.save(updatedStudent)).thenReturn(updatedStudent);
        Mockito.when(studentMapper.toDto(updatedStudent)).thenReturn(updatedDto);

        //When
        DtoStudent result=studentService.update(studentId,updateDto);

        //Then
        Assertions.assertNotNull(result);

        Mockito.verify(studentRepository,Mockito.times(1)).findById(studentId);
        Mockito.verify(studentMapper,Mockito.times(1)).updateEntityFromDto(updateDto,testStudent);
        Mockito.verify(studentRepository,Mockito.times(1)).save(updatedStudent);
        Mockito.verify(studentMapper,Mockito.times(1)).toDto(updatedStudent);

    }

    @Test
    public void sortByBirthDateTest(){
        //Given
        Mockito.when(studentRepository.sortByBirthDate()).thenReturn(testStudentsList);
        Mockito.when(studentMapper.toDtoList(testStudentsList)).thenReturn(testDtoStudentsList);

        //When
        List<DtoStudent> result=studentService.sortByBirthDate();

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2,result.size());
        Assertions.assertEquals("Ayaz",result.get(0).getFirstName());
        Mockito.verify(studentRepository,Mockito.times(1)).sortByBirthDate();
        Mockito.verify(studentMapper,Mockito.times(1)).toDtoList(testStudentsList);
    }

    @Test
    public void searchByFirstNameTest(){
        //Given
        String subStr="y";
        Mockito.when(studentRepository.searchByFirstName(subStr)).thenReturn(Arrays.asList(testStudent));
        Mockito.when(studentMapper.toDtoList(Arrays.asList(testStudent))).thenReturn(Arrays.asList(testDtoStudent));

        //When
        List<DtoStudent> result=studentService.searchByFirstName(subStr);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Ayaz", result.get(0).getFirstName());

        Mockito.verify(studentRepository,Mockito.times(1)).searchByFirstName(subStr);
        Mockito.verify(studentMapper,Mockito.times(1)).toDtoList(Arrays.asList(testStudent));

    }

    @Test
    public void getNumberOfTotalStudentsTest(){
        //Given
        Integer expectedCount=2;
        Mockito.when(studentRepository.getNumberOfTotalStudents()).thenReturn(expectedCount);

        //When
        Integer result=studentService.getNumberOfTotalStudents();

        //Then
        Assertions.assertEquals(expectedCount,result);
        Mockito.verify(studentRepository,Mockito.times(1)).getNumberOfTotalStudents();
    }

    @Test
    public void findByIdTest(){
        //Given
        Long studentId=1L;
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(testStudent));
        Mockito.when(studentMapper.toDto(testStudent)).thenReturn(testDtoStudent);

        //When
        Optional<DtoStudent> result=studentService.findById(studentId);

        //Then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(testDtoStudent.getId(), result.get().getId());
        Mockito.verify(studentRepository, Mockito.times(1)).findById(studentId);
        Mockito.verify(studentMapper, Mockito.times(1)).toDto(testStudent);
    }

    @Test
    public void getStudentByCardIdTest(){
        //Given
        Long cardId=1L;
        Mockito.when(studentRepository.getStudentByCardId(cardId)).thenReturn(Optional.of(testStudent));
        Mockito.when(studentMapper.toDto(testStudent)).thenReturn(testDtoStudent);

        //When
        DtoStudent result=studentService.getStudentByCardId(cardId);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(testDtoStudent.getId(),result.getId());
        Mockito.verify(studentRepository,Mockito.times(1)).getStudentByCardId(cardId);
        Mockito.verify(studentMapper, Mockito.times(1)).toDto(testStudent);
    }

}
