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
    <title>Course Registration</title>
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
                <p id="currentDate">Current Date : YY.MM.DD</p>
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


        <a th:href="@{course}">Course Registration </a>
        <a th:href="@{studentseartch}">Student Search </a>
        <a th:href="@{usermanage}">Users Management</a>
        <div th:each="data : ${list}">
            <div th:if="${data.userid == session.userid}">
                <a th:href="@{adminnewpwd(id=${data.userid})}">Admin Change Password</a>
            </div>
        </div>
    </div>
</div>
<div class="main_contents">
    <div id="sub_content">
        <form action="course3" method="post" th:object="${course}" onsubmit="return validateForm()"
              onblur="validateName()">

            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Course Registration</h2>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="id" class="col-md-2 col-form-label"> ID</label>
                <div class="col-md-4">

                    <input th:field="*{courseid}" type="hidden" class="form-control" id="id"/>
                    <input th:name="courseid1" th:value="${courseid}" class="form-control" id="id"/>
                </div>
            </div>

            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name</label>
                <div class="col-md-4">
                    <input th:field="*{coursename}" type="text" class="form-control" id="name"
                           onfocus="clearError('error')"/>
                    <span id="error" style="color:red;"></span>
                </div>
            </div>


            <div class="row mb-4">
                <div class="col-md-4"></div>

                <div class="col-md-6">


                    <button type="submit" class="btn btn-secondary col-md-2" data-bs-toggle="modal"
                            data-bs-target="#exampleModal">Add
                    </button>


                </div>
        </form>
    </div>
</div>
</div>
<div id="testfooter">
    <span>Copyright &#169; ACE Inspiration 2022</span>
</div>
<script>
    function validateForm() {
        var name = document.getElementById("name").value;
        var isValid = true;
        clearError("error");
        if (name.trim() === "") {
            document.getElementById("error").innerHTML = "CourseName is Required";
            isValid = false;
        }
        return isValid;
    }

    function clearError(errorId) {
        document.getElementById(errorId).innerHTML = "";
    }
</script>
<script>
    function validateName() {
        var name = document.getElementById("name").value;
        if (name.trim() === "") {
            document.getElementById("error").innerHTML = "CourseName is Required";
        }
    }
</script>
<script>
    // Get the current date
    var currentDate = new Date();

    // Extract the year, month, and day
    var year = currentDate.getFullYear().toString().slice(-2); // Get the last 2 digits of the year
    var month = (currentDate.getMonth() + 1).toString().padStart(2, '0'); // Months are 0-based, so add 1 and pad with '0'
    var day = currentDate.getDate().toString().padStart(2, '0'); // Pad the day with '0'

    // Update the content of the <p> element
    document.getElementById("currentDate").textContent = "Current Date : " + year + "." + month + "." + day;
</script>

</body>
</html>