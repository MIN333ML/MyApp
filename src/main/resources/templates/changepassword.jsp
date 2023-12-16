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

</head>
<body>

<div id="testheader">
    <div class="container">
        <div class=row>
            <div class="col-md-5 ">
                <a href="MNU001.html"><h3>User Registration</h3></a>
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
            <form action="newpwd1" method="post" th:object="${pwd}" enctype="multipart/form-data" class="user-form"
                  onsubmit="return validateForm()">

                <h2 class="col-md-6 offset-md-2 mb-5 mt-4" style="color:black;">Change User Password</h2>
                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <div class="col-md-4">
                        <input type="hidden" class="form-control" th:field="*{userid}" id="id" value="${pwd.userid}"/>
                    </div>
                </div>

                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="Passowrd" class="col-md-2 col-form-label" style="color:black;">OldPassowrd</label>
                    <div class="col-md-4">
                        <input type="password" class="form-control" id="oldpassword1" name="password"
                               th:field="*{password}" onfocus="clearError('error'); clearError('error4')"
                               onblur="validateOldPwd()"/>
                        <span id="error" style="color:red;"></span>
                        <span id="error4" style="color:red;"
                              th:text="${#strings.capitalize(#vars['oldpwderor'])}"></span>

                    </div>
                </div>

                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="Passowrd" class="col-md-2 col-form-label" style="color:black">NewPassowrd</label>
                    <div class="col-md-4">
                        <input type="password" class="form-control" id="password1" name="newpassword1"
                               onfocus="clearError('error1')" onblur="validateNewPwd()"/>
                        <span id="error1" style="color:red;"></span>

                    </div>
                </div>

                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="confirmPassword" class="col-md-2 col-form-label" style="color:black">Confirm
                        Passowrd</label>
                    <div class="col-md-4">
                        <input type="password" class="form-control" id="confirmPassword1" name="comfirmpassword"
                               onfocus="clearError('error2')" onblur="validateComfirmPwd()">
                        <span id="error2" style="color:red;"></span>

                    </div>
                </div>


                <div class="row mb-4">
                    <div class="col-md-4"></div>

                    <div class="col-md-6">

                        <button type="submit" id="validate" class="btn btn-secondary col-md-2">Change Password</button>

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
        var oldpassword = document.getElementById("oldpassword1").value;
        var newpassword = document.getElementById("password1").value;
        var comfirmpassword = document.getElementById("confirmPassword1").value;
        var isValid = true;
        clearError("error");
        clearError("error1");
        clearError("error2");
        clearError("error4");

        if (oldpassword === "") {
            document.getElementById("error").innerHTML = "Oldpassword is required";
            isValid = false;
        }

        if (newpassword === "") {
            document.getElementById("error1").innerHTML = "NewPassword is required";
            isValid = false;
        } else if (oldpassword === newpassword) {
            document.getElementById("error1").innerHTML = "NewPassword must not same with Oldpassword";
            isValid = false;
        } else if (newpassword.length < 6) {
            document.getElementById("error1").innerHTML = "Password must be at least 6 characters long";
            isValid = false;
        }

        if (comfirmpassword === "") {
            document.getElementById("error2").innerHTML = "Comfirmpassword is required";
            isValid = false;
        } else if (newpassword !== comfirmpassword) {
            document.getElementById("error2").innerHTML = "Password do not match";
            isValid = false;
        }


        return isValid;
    }

    function clearError(errorId) {
        document.getElementById(errorId).innerHTML = "";

    }
</script>
<script>
    function validateOldPwd() {
        var oldpassword = document.getElementById("oldpassword1").value;
        if (oldpassword === "") {
            document.getElementById("error").innerHTML = "Oldpassword is required";
        }
    }

    function validateNewPwd() {
        var oldpassword = document.getElementById("oldpassword1").value;
        var newpassword = document.getElementById("password1").value;
        if (newpassword === "") {
            document.getElementById("error1").innerHTML = "NewPassword is required";
        } else if (oldpassword === newpassword) {
            document.getElementById("error1").innerHTML = "NewPassword must not same with Oldpassword";
        } else if (newpassword.length < 6) {
            document.getElementById("error1").innerHTML = "Password must be at least 6 characters long";

        }

    }

    function validateComfirmPwd() {
        var newpassword = document.getElementById("password1").value;
        var comfirmpassword = document.getElementById("confirmPassword1").value;
        if (comfirmpassword === "") {
            document.getElementById("error2").innerHTML = "Comfirmpassword is required";
        } else if (newpassword !== comfirmpassword) {
            document.getElementById("error2").innerHTML = "Password do not match";
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