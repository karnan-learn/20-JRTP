var Student = /** @class */ (function () {
    function Student() {
    }
    Student.prototype.getStudentGrade = function () {
        if (this.studentMarks >= 75) {
            return "A";
        }
        else if (this.studentMarks >= 65 && this.studentMarks < 60) {
            return "B";
        }
        else {
            return "C";
        }
    };
    return Student;
}());
var s1 = new Student(); //obj creation
s1.studentName = "John";
s1.studentRank = 100;
s1.studentMarks = 80;
console.log(s1.studentName);
console.log(s1.studentRank);
console.log(s1.studentMarks);
console.log(s1.getStudentGrade());
var s2 = new Student(); //obj creation
s2.studentName = "Smith";
s2.studentRank = 200;
s2.studentMarks = 50;
console.log(s2.studentName);
console.log(s2.studentRank);
console.log(s2.studentMarks);
console.log(s2.getStudentGrade());
