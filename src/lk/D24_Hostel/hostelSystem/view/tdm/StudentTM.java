package lk.D24_Hostel.hostelSystem.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTM implements Comparable<StudentTM> {
    private String studentId;
    private String name;
    private String address;
    private String contactNo;
    private LocalDate dob;
    private String gender;

    @Override
    public int compareTo(StudentTM o) {
        return studentId.compareTo(o.getStudentId());
    }
}
