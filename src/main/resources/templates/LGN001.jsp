<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <link th:href="@{/css/test.css}" rel="stylesheet"/>


    <title>Insert title here</title>
</head>
<body class="login-page-body">

<div class="login-page">
    <div class="form">
        <div class="login">
            <div class="login-header">
                <h1>Welcome!</h1>
            </div>
        </div>


        <form class="login-form" action="login" method="post" name="confirm" th:object="${LGN}"
              enctype="multipart/form-data">
            <input type="email" placeholder="User Email" th:field="*{email}" id="email1" onfocus="clearError('error3')">
            <div id="error" style="color:red;font-weight:bold; font-size:13px;"></div>
            <span id="error3" style="color:red" class="error-message" th:text="${emailcorrect}"></span>
            <div class="password-container">
                <input type="password" placeholder="Password" th:field="*{password}" id="password1"
                       onfocus="clearError('error4')">
                <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
            </div>
            <div id="error1" style="color:red;font-weight:bold;font-size:13px;"></div>
            <span id="error4" style="color:red" class="error-message" th:text="${pwdcorrect}"></span>

            <button id="submit1">login</button>
            <p class="message">Not registered? <a href="studentregister">Create an account</a></p>
        </form>
    </div>
</div>
<script>
    email1.onfocus = function () {
        error.innerHTML = "";
    }
    email1.onblur = function () {
        var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        if (!emailPattern.test(appEmail1.value)) {
            error.innerHTML = "Invalid email address";
        }
    }
    email1.onfocus = function () {
        error3.innerHTML = "";
    }
    password1.onfocus = function () {
        error1.innerHTML = "";
    }
    password1.onblur = function () {
        if (password1.value.trim() === "") {
            error1.innerHTML = "Password is required.";
        }
    };
    password1.onfocus = function () {
        error4.innerHTML = "";
    }
    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById("submit1").addEventListener("click", function (event) {
            // Prevent the form from submitting initially
            event.preventDefault();

            // Reset any previous error messages
            resetErrorMessages();
            // Validate the form fields
            var isValid = true;
            clearError("error3");
            clearError("error4");
            // Validate Name
            var emailInput = document.getElementById("email1");
            var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
            if (!emailPattern.test(emailInput.value)) {
                displayErrorMessage("error", "Invalid email address.");
                isValid = false;
            }            // Validate Password
            var passwordInput = document.getElementById("password1");
            if (passwordInput.value.trim() === "") {
                displayErrorMessage("error1", "Password is required");
                isValid = false;
            }

            // Validate Confirm Password


            // You can add similar validation for other fields here

            // If the form is valid, submit it
            if (isValid) {
                document.querySelector("form.login-form").submit();
            }
        });

        function clearError(errorId) {
            document.getElementById(errorId).innerHTML = "";
        }

        function displayErrorMessage(elementId, message) {
            var errorElement = document.getElementById(elementId);
            errorElement.textContent = message;
        }

        function resetErrorMessages() {
            var errorElements = document.querySelectorAll("[id^='error']");
            for (var i = 0; i < errorElements.length; i++) {
                errorElements[i].textContent = "";
            }
        }
    });
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