<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Insert title here</title>
    <link th:href="@{/css/style.css}" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/test.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

    <title>Course Registration</title>
    <style>
        .error-message {
            color: red;
            font-size: 16px;
        }
    </style>
</head>
<body>

<div id="testheader">
    <div class="container">
        <div class=row>
            <div class="col-md-5 ">
                <a href="MNU001.html"><h3>User Registration</h3></a>
            </div>
            <div class="col-md-6">
                <p id="currentDate" style="color:black;">Current Date : YY.MM.DD</p>
            </div>
            <div class="col-md-1">
                <a class="btn-basic" id="lgnout-button" th:href='@{/}'>Log Out</a>
            </div>
        </div>
    </div>

</div>

<div class="container">
    <div class="sidenav">


        <div class="dropdown-container">
            <a href="/codetest2/course">Course Registration </a>
            <a href="/codetest2/addstudent">Student Registration </a>
            <a href="/codetest2/studentseartch">Student Search </a>
        </div>
    </div>
    <div class="main_contents">
        <div id="sub_content">
            <form action="user212" method="post" th:object="${ppp}" enctype="multipart/form-data" class="user-form"
                  onsubmit="return validateForm()">

                <h2 class="col-md-6 offset-md-2 mb-5 mt-4" style="color:black;">User Registration</h2>
                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label class="col-md-2 col-form-label" style="color:black">UserID</label>
                    <div class="col-md-4">
                        <input type="hidden" class="form-control" id="id" th:field="*{userid}"/>
                        <input type="text" class="form-control" th:name="userid1" id="id" th:value="${userid}"/>
                    </div>
                </div>
                <div class="row mb-4">
                    <div class="col-md-2"></div>

                    <label for="name" class="col-md-2 col-form-label" style="color:black;">Name</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" id="name1" name="name" th:field="*{name}"
                               onfocus="clearError('error')" onblur="validateName()"/>
                        <span id="error" style="color:red;"></span>
                    </div>

                </div>
                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="email" class="col-md-2 col-form-label" style="color:black;">Email</label>
                    <div class="col-md-4">
                        <input type="email" class="form-control" id="email1" name="email" th:field="*{email}"
                               onfocus="clearError('error1'); clearError('error4')" onblur="validateEmail()"/>
                        <span id="error1" style="color:red;"></span>
                        <span id="error4" th:text="${emailerror}" class="error-message"></span>
                    </div>
                </div>
                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="Passowrd" class="col-md-2 col-form-label" style="color:black;">Passowrd</label>
                    <div class="col-md-4">
                        <input type="password" class="form-control" id="password1" name="password"
                               th:field="*{password}" onfocus="clearError('error2')" onblur="validatePassword()"/>
                        <span id="error2" style="color:red;"></span>

                    </div>
                </div>
                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="confirmPassword" class="col-md-2 col-form-label" style="color:black;">Confirm
                        Passowrd</label>
                    <div class="col-md-4">
                        <input type="password" class="form-control" id="confirmPassword1" name="comfirmpassword"
                               onfocus="clearError('error3')" onblur="validateComfirmPwd()">
                        <span id="error3" style="color:red;"></span>

                    </div>
                </div>


                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="userRole" class="col-md-2 col-form-label" style="color:black;">User Role</label>
                    <div class="col-md-4">
                        <select class="form-select" aria-label="Education" th:field="*{role}" id="userRole">
                            <option selected="true" value="User">User</option>
                            <option value="Admin">Admin</option>

                        </select>
                    </div>
                </div>
                <div class="row mb-4">
                    <div class="col-md-4"></div>

                    <div class="col-md-6">

                        <button type="submit" id="validate" class="btn btn-secondary col-md-2">Add</button>

                    </div>
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
        // Get the form elements
        var name = document.getElementById("name1").value;
        var email = document.getElementById("email1").value;
        var password = document.getElementById("password1").value;
        var confirmPassword = document.getElementById("confirmPassword1").value;

        // Reset error messages

        // Validation rules
        var isValid = true;
        clearError("error");
        clearError("error1");
        clearError("error2");
        clearError("error3");
        clearError("error4");
        if (name === "") {
            document.getElementById("error").innerHTML = "Name is required";
            isValid = false;
        }

        if (email === "") {
            document.getElementById("error1").innerHTML = "Email is required";
            isValid = false;
        }
        if (password === "") {
            document.getElementById("error2").innerHTML = "Password is required";
            isValid = false;
        } else if (password.length < 6) {
            document.getElementById("error2").innerHTML = "Password must be at least 6 characters long";
            isValid = false;
        }
        if (confirmPassword === "") {
            document.getElementById("error3").innerHTML = "Confirm Password is required";
            isValid = false;
        } else if (password !== confirmPassword) {
            document.getElementById("error3").innerHTML = "Passwords do not match";
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
        var name = document.getElementById("name1").value;

        if (name === "") {
            document.getElementById("error").innerHTML = "Name is required";
        }
    }

    function validateEmail() {
        var email = document.getElementById("email1").value;

        if (email === "") {
            document.getElementById("error1").innerHTML = "Email is required";
        }
    }

    function validatePassword() {
        var password1 = document.getElementById("password1").value;

        if (password1 === "") {
            document.getElementById("error2").innerHTML = "Password is required";
        } else if (password1.length < 6) {
            document.getElementById("error2").innerHTML = "Password must be at least 6 characters long";

        }
    }

    function validateComfirmPwd() {
        var comfrimPassword = document.getElementById("confirmPassword1").value;

        if (confirmPassword === "") {
            document.getElementById("error3").innerHTML = "Confirm Password is required";
        } else if (password !== confirmPassword) {
            document.getElementById("error3").innerHTML = "Passwords do not match";
        }
    }
</script>
<script>
    document.querySelector(".toggle-password").addEventListener("click", function () {
        var passwordField = document.getElementById("password1");

        if (passwordField.type === "password") {
            passwordField.type = "text";
            this.innerHTML = '<i class="fa fa-eye-slash field-icon"></i>';
        } else {
            passwordField.type = "password";
            this.innerHTML = '<i class="fa fa-eye field-icon"></i>';
        }
    });
</script>
<script>
    document.querySelector(".toggle-password").addEventListener("click", function () {
        var passwordField = document.getElementById("confirmPassword1");

        if (passwordField.type === "password") {
            passwordField.type = "text";
            this.innerHTML = '<i class="fa fa-eye-slash field-icon"></i>';
        } else {
            passwordField.type = "password";
            this.innerHTML = '<i class="fa fa-eye field-icon"></i>';
        }
    });
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