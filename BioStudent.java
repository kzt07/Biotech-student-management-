import java.io.Serializable;
import java.util.Map;

class Student implements Serializable {
   private String name;
   private String rollNo;
   private Map<String, String> subjectGrades;

   public Student(String var1, String var2, Map<String, String> var3) {
      this.name = var1;
      this.rollNo = var2;
      this.subjectGrades = var3;
   }

   public String getName() {
      return this.name;
   }

   public String getRollNo() {
      return this.rollNo;
   }

   public Map<String, String> getSubjectGrades() {
      return this.subjectGrades;
   }

   public void setName(String var1) {
      this.name = var1;
   }

   public void setRollNo(String var1) {
      this.rollNo = var1;
   }

   public void setSubjectGrades(Map<String, String> var1) {
      this.subjectGrades = var1;
   }

   public String toString() {
      return this.name + " (" + this.rollNo + ")";
   }
}
