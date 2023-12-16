<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Insert title here</title>
    <link th:href="@{/css/style.css}" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</head>
<body>
<div id="testheader">
    <div class="container">
        <div class=row>
            <div class="col-md-5 ">
                <a href="MNU001.html"><h3>Student Registration</h3></a>
            </div>
            <div class="col-md-6">
                <p style="color:black"
                   th:text="${#session.getAttribute('role')} + ': ' + ${#session.getAttribute('name')} + ', ' + ${#session.getAttribute('userid')}"></p>
                <p>Current Date : YY.MM.DD </p>
            </div>
            <div class="col-md-1">
                <a class="btn-basic" id="lgnout-button" th:href='@{/}'>Log Out</a>
            </div>
        </div>
    </div>

</div>
<!-- <div id="testsidebar">Hello World </div> -->
<div class="container">
    <div class="sidenav">


        <a th:href="@{addstudent}">Student Registration </a>
        <a th:href="@{studentseartch1}">Student Search </a>
        <div th:each="data : ${list}">
            <div th:if="${data.userid == session.userid}">
                <a th:href="@{newpwd(id=${data.userid})}">User Change Password</a>
                <a th:href="@{setupUpdatebook(id=${data.userid})}">User Update</a>
            </div>
        </div>
    </div>
    <div class="main_contents">
        <div id="sub_content">
            <form action="studentregister4" method="post" th:object="${stud}" enctype="multipart/form-data"
                  onsubmit="return validateForm()"><h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student Registration</h2>

                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="name" class="col-md-2 col-form-label" style="color:black">StudentId</label>
                    <div class="col-md-4">
                        <input type="hidden" th:field="*{studentid}"/>
                        <input type="text" class="form-control" id="name" th:name="studentid1" th:value="${studentid}"/>
                    </div>
                </div>

                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="name" class="col-md-2 col-form-label" style="color:black">Name</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" id="name1" th:field="*{name}"/>
                        <span id="error" style="color:red;"></span>
                    </div>
                </div>
                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="dob" class="col-md-2 col-form-label" style="color:black">DOB</label>
                    <div class="col-md-4">
                        <input type="date" class="form-control" id="dob1" th:field="*{dob}"/><br>
                        <span id="error1" style="color:red;"></span>
                    </div>
                    <div></div>
                </div>
                <fieldset class="row mb-4">
                    <div class="col-md-2"></div>
                    <legend class="col-form-label col-md-2 pt-0" style="color:black">Gender</legend>
                    <div class="col-md-4">
                        <div class="form-check-inline">
                            <input type="radio" class="form-check-input" items="genderlist" th:field="*{gender}"
                                   name="gridRadios" id="gridRadios1" value="Male"/>
                            <label class="form-check-label" for="gridRadios1" style="color:black">
                                Male
                            </label>
                        </div>
                        <div class="form-check-inline">
                            <input type="radio" class="form-check-input" items="genderlist" th:field="*{gender}"
                                   name="gridRadios" id="gridRadios2" value="Female"/>
                            <label class="form-check-label" for="gridRadios2" style="color:black">
                                Female
                            </label>
                        </div>
                        <br>
                        <span id="error2" style="color:red;"></span>

                    </div>
                </fieldset>
                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="phone" class="col-md-2 col-form-label" style="color:black">Phone</label>
                    <div class="col-md-4">
                        <input type="text" th:field="*{phone}" class="form-control" id="phone1" value=""/>
                        <span id="error3" style="color:red;"></span>
                    </div>
                </div>
                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="education" class="col-md-2 col-form-label" style="color:black">Education</label>
                    <div class="col-md-4">
                        <select class="form-select" th:field="*{education}" aria-label="Education" id="education1">
                            <option value="Bachelor of Information Technology">Bachelor of Information Technology
                            </option>
                            <option value="Diploma in IT">Diploma in IT</option>
                            <option value="Bachelor of Computer Science">Bachelor of Computer Science</option>
                        </select>
                    </div>
                </div>
                <fieldset class="row mb-4">
                    <div class="col-md-2"></div>
                    <legend class="col-form-label col-md-2 pt-0" style="color:black">Attend</legend>

                    <div class="col-md-4">
                        <div th:each="data : ${session.course}">
                            <input type="checkbox" class="form-check-input" th:field="*{attend}" th:name="courseNames"
                                   th:value="${data.coursename}"/>
                            <input type="hidden" name="selectedCourses" th:value="${data.coursename}"/>
                            <label class="form-check-label" th:for="'attend' + ${data.coursename}">
                                <span th:text="${data.coursename}"></span>
                            </label>
                        </div>
                        <br>
                        <span id="error4" style="color:red;"></span>

                    </div>
                </fieldset>
                <div class="row mb-4">
                    <div class="col-md-4"></div>

                    <div class="col-md-4">
                        <button type="reset" class="btn btn-danger">

                            Reset
                        </button>
                        <button type="submit" class="btn btn-secondary col-md-2" data-bs-toggle="modal"
                                data-bs-target="#exampleModal">
                            Add
                        </button>

                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="testfooter">
    <span>Copyright &#169; ACE Inspiration 2022</span>
</div>
<script type="text/javascript">
    function validateForm() {
        var name = document.getElementById("name1").value;
        var dob = document.getElementById("dob1").value;
        var male = document.getElementById("gridRadios1").value;
        var female = document.getElementById("gridRadios2").value;
        var phone = document.getElementById("phone1").value;
        var attend = document.getElementById("attend1")
        // Reset error messages
        document.getElementById("error").innerHTML = "";
        document.getElementById("error1").innerHTML = "";
        document.getElementById("error2").innerHTML = "";
        document.getElementById("error3").innerHTML = "";

        var isValid = true;

        // Name validation
        if (name.trim() === "") {
            document.getElementById("error").innerHTML = "Name is required.";
            isValid = false;
        }

        // Date of Birth validation
        if (dob.trim() === "") {
            document.getElementById("error1").innerHTML = "Date of Birth is required.";
            isValid = false;
        }
        // Gender validation

        // Phone validation
        if (phone.trim() === "") {
            document.getElementById("error3").innerHTML = "Phone is required.";
            isValid = false;
        }

        return isValid;
    }
</script>

</body>

</html>

